package com.ruihai.helper.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.ruihai.helper.log.AgLog;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;


/**
 * http client的工具类，用于取得某个url返回的内容 使用UTF-8的格式转码
 *
 * @author sunbaoming@dhgate.com
 *
 */
public class HttpClientUtils {
    private static final int sotimeout = 5000;
    private static final int connectTimeout = 1000;
//    protected final static Log log = LogFactory.getLogger(DHgateHttpClient.class);

    /**
     * 返回一个http request 的responseString
     *
     *
     * @param url
     * @param parameters 要post的parameters
     *
     * @return string
     * @throws Exception
     */
    public static String postRequest(String url, Map<String, String> parameters) throws Exception {
        return postRequest(url, parameters, 0);
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    private static class MyProtocolSocketFactory implements ProtocolSocketFactory {
        private SSLContext sslContext;

        private MyProtocolSocketFactory(SSLContext sslContext) {
            this.sslContext = sslContext;
        }

        public Socket createSocket(String arg0, int arg1) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(arg0, arg1);
        }

        public Socket createSocket(String arg0, int arg1, InetAddress arg2, int arg3) throws IOException,
                UnknownHostException {
            return sslContext.getSocketFactory().createSocket(arg0, arg1, arg2, arg3);
        }

        public Socket createSocket(String host, int port, InetAddress localAddress, int localPort,
                                   HttpConnectionParams params) throws IOException, UnknownHostException, ConnectTimeoutException {
            if (params == null) {
                throw new IllegalArgumentException("Parameters may not be null");
            }
            int timeout = params.getConnectionTimeout();
            SocketFactory socketfactory = sslContext.getSocketFactory();
            if (timeout == 0) {
                return socketfactory.createSocket(host, port, localAddress, localPort);
            } else {
                Socket socket = socketfactory.createSocket();
                SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
                SocketAddress remoteaddr = new InetSocketAddress(host, port);
                socket.bind(localaddr);
                socket.connect(remoteaddr, timeout);
                return socket;
            }
        }

    }

    public static String postRequest(String url, Map<String, String> parameters, int timeoutmiliseconds) throws Exception {

        HttpClient client = new HttpClient();

        if (timeoutmiliseconds > 0) {
            HttpClientParams params = new HttpClientParams();
            params.setSoTimeout(timeoutmiliseconds);
            client.setParams(params);
        }

        client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        client.getParams().setContentCharset("UTF-8");
        client.getHttpConnectionManager().getParams().setConnectionTimeout(timeoutmiliseconds);

        PostMethod post = null;
        post = new PostMethod(url);

        try {
            // 处理提交parameters
            Set<String> parameterkeySet = parameters.keySet();
            Iterator<String> parameterkeySetNames = parameterkeySet.iterator();
            while (parameterkeySetNames.hasNext()) {
                String parameterkey = parameterkeySetNames.next();
                //System.out.println(parameterkey);
                if (parameterkey != null) {
                    String parametervalue = parameters.get(parameterkey);
                    if (parametervalue != null) {
                        post.setParameter(parameterkey, parametervalue);
                        //System.out.println(parameterkey);
                    }
                }
            }

            // 调用php
            int result = client.executeMethod(post);

            if (result == HttpStatus.SC_OK) {

                InputStream inputStream = post.getResponseBodyAsStream();
                return getResponseAsString(inputStream, "UTF-8");
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            post.releaseConnection();
        }
    }

    public static String postRequestTSL(String url,
                                        Map<String, String> parameters, int timeoutmiliseconds) {
        PostMethod post = null;

        try {
            SSLContext context = SSLContext.getInstance("TLSv1.2");
            context.init(null,
                    new TrustManager[] { new TrustAnyTrustManager() },
                    new SecureRandom());
            // ��ȡURL ��������
            Protocol.registerProtocol("https", new Protocol("https",
                    new MyProtocolSocketFactory(context), 443));

            HttpClient client = new HttpClient(
                    new MultiThreadedHttpConnectionManager());

            if (timeoutmiliseconds > 0) {
                HttpClientParams params = new HttpClientParams();
                params.setSoTimeout(timeoutmiliseconds);// http.socket.timeout
                params.setConnectionManagerTimeout(timeoutmiliseconds);// http.connection-manager.timeout
                client.setConnectionTimeout(timeoutmiliseconds);// http.connection.timeout
                client.setParams(params);
            }

            client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
            client.getParams().setContentCharset("UTF-8");

            post = new PostMethod(url);

            // �����ύparameters
            Set<String> parameterkeySet = parameters.keySet();
            Iterator<String> parameterkeySetNames = parameterkeySet.iterator();
            while (parameterkeySetNames.hasNext()) {
                String parameterkey = parameterkeySetNames.next();
                if (parameterkey != null) {
                    String parametervalue = parameters.get(parameterkey);
                    if (parametervalue != null) {
                        post.setParameter(parameterkey, parametervalue);
                    }
                }
            }

            // ����php
            int result = client.executeMethod(post);

            if (result == HttpStatus.SC_OK) {
                InputStream inputStream = post.getResponseBodyAsStream();
                return getResponseAsString(inputStream, "UTF-8");
            } else {
                AgLog.errorLog.error("http code:" + result);
                return null;
            }
        } catch (Exception e) {
            AgLog.errorLog.error("", e);
            throw new RuntimeException(e);
        } finally {
            post.releaseConnection();
        }

    }
    public static void main(String[] args) {
        //https://www.dhpay.com/gateway/queryorder.do?act=q&merchant_id=494308&merchant_order_id=20141117-150758425112&signature=06a95fe4caf0571e15245f553c5ed7f1
        String url = "https://www.dhpay.com/gateway/queryorder.do";
        Map<String, String> paramterMap = new HashMap<String, String>();
        paramterMap.put("act", "q");
        paramterMap.put("merchant_id", "494308");
        paramterMap.put("merchant_order_id", "20141117-150758425112");
        paramterMap.put("signature", "06a95fe4caf0571e15245f553c5ed7f1");

        try {
            String str = HttpClientUtils.postRequest(url, paramterMap, 1000);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * use get method to return http request response String
     *
     * @param url
     * @return String
     * @throws Exception
     */
    public static String getRequest(String url) {
        if (url == null || "".equals(url))
            return "";

        HttpClient client = new HttpClient();

        client.getParams().setContentCharset("UTF-8");

        GetMethod method = new GetMethod(url);
        //method.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
        method.setRequestHeader("Connection", "close");
        //method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

        String retstring = null;
        try {
            // 调用php
            int result = client.executeMethod(method);

            if (result == HttpStatus.SC_OK) {
                //byte[] responseBody = method.getResponseBody();
                // Deal with the response.
                // Use caution: ensure correct character encoding and is not binary data
                //retstring= new String(responseBody,"UTF-8");
                //retstring = method.getResponseBodyAsString();
                InputStream resStream = method.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(resStream, "UTF-8"));
                StringBuffer resBuffer = new StringBuffer();
                String resTemp = "";
                while ((resTemp = br.readLine()) != null) {
                    resBuffer.append(resTemp);
                }
                retstring = resBuffer.toString();

            }
        } catch (IOException e) {
            AgLog.infoLog.info("IOException :" + e.getMessage() + ",url = " + url);
        } finally {
            method.releaseConnection();
        }
        return retstring;
    }

    public static String getRequestWithTimeout(String url, int connTime, int soTime) {
        if (url == null || "".equals(url))
            return "";

        HttpClient client = new HttpClient();
        if (connTime == 0) {
            connTime = connectTimeout;
        }
        if (soTime == 0) {
            soTime = sotimeout;
        }
        client.getHttpConnectionManager().getParams().setConnectionTimeout(connTime);
        client.getHttpConnectionManager().getParams().setSoTimeout(soTime);

        client.getParams().setContentCharset("UTF-8");

        GetMethod method = new GetMethod(url);
        //method.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
        //method.setRequestHeader("Connection", "close");
        //method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

        String retstring = null;
        try {
            // 调用php
            int result = client.executeMethod(method);

            if (result == HttpStatus.SC_OK) {
                //byte[] responseBody = method.getResponseBody();
                // Deal with the response.
                // Use caution: ensure correct character encoding and is not binary data
                //retstring= new String(responseBody,"UTF-8");
                //retstring = method.getResponseBodyAsString();
                InputStream resStream = method.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
                StringBuffer resBuffer = new StringBuffer();
                String resTemp = "";
                while ((resTemp = br.readLine()) != null) {
                    resBuffer.append(resTemp);
                }
                retstring = resBuffer.toString();

            }
        } catch (IOException e) {
            AgLog.errorLog.error(e.getMessage() + ",url = " + url);
        } finally {
            method.releaseConnection();
        }
        return retstring;
    }

    public static InputStream getRequestInputStream(String url) {
        if (url == null || "".equals(url))
            return null;

        GetMethod getMethod = new GetMethod(url);
        HttpClient client = new HttpClient();
        InputStream retStream = null;
        try {
            int result = client.executeMethod(getMethod);
            if (result == HttpStatus.SC_OK) {
                // byte[] responseBody = getMethod.getResponseBody();
                // Deal with the response.
                // Use caution: ensure correct character encoding and is not
                // binary data
                retStream = getMethod.getResponseBodyAsStream();
            }
        } catch (IOException e) {
            AgLog.errorLog.error(e.getMessage() + ",url = " + url);
            retStream = null;
        }
        return retStream;
    }

    public static byte[] getRequestByte(String url) {
        if (url == null || "".equals(url))
            return null;

        GetMethod getMethod = new GetMethod(url);
        getMethod.setRequestHeader("Connection", "close");

        HttpClient client = new HttpClient();

        byte[] responseBody = null;
        try {
            int result = client.executeMethod(getMethod);
            if (result == HttpStatus.SC_OK) {
                responseBody = getMethod.getResponseBody();
                // Deal with the response.
                // Use caution: ensure correct character encoding and is not binary data
                //retStream = getMethod.getResponseBodyAsStream();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseBody = null;
        }

        return responseBody;
    }

    public static String getResponseAsString(InputStream is, String encoding) throws UnsupportedEncodingException, IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString(encoding);
    }

    /**
     * 发送post请求
     * @param strURL 请求路径
     * @param params  json参数
     * @return
     */
    public static String postRequestByJson(String strURL, String params) {
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();

            int code = connection.getResponseCode();
            InputStream is = null;
            if (code == 200) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }

            // 读取响应
            int length = (int) connection.getContentLength();// 获取长度
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8编码
                return result;
            }

        } catch (IOException e) {
        }
        return null; // 自定义错误信息
    }
    public static String postRequestByJsonTSL(String url, String param) {
        PostMethod post = null;
        try {
            SSLContext context = SSLContext.getInstance("TLSv1.2");
            context.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new SecureRandom());

            RequestEntity se = new StringRequestEntity (param ,"application/json" ,"UTF-8");
            Protocol.registerProtocol("https", new Protocol("https", new MyProtocolSocketFactory(context), 443));

            HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());

            HttpClientParams params = new HttpClientParams();
            client.setParams(params);

            client.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
            client.getParams().setContentCharset("UTF-8");
            post = new PostMethod(url);
            post.setRequestEntity(se);
            post.setRequestHeader("Content-Type","application/json");

            int result = client.executeMethod(post);

            if (result == HttpStatus.SC_OK) {
                InputStream inputStream = post.getResponseBodyAsStream();
                return getResponseAsString(inputStream, "UTF-8");
            } else {
                AgLog.errorLog.error("http code:" + result);
                return null;
            }
        } catch (Exception e) {
            AgLog.errorLog.error("", e);
            throw new RuntimeException(e);
        } finally {
            post.releaseConnection();
        }
    }
}
