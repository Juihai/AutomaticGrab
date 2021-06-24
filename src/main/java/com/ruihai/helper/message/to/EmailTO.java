package com.ruihai.helper.message.to;

import java.util.Arrays;

public class EmailTO {
    private String title;//邮件主题
    private String content;//邮件内容
    private String sender;//发件人账号
    private String password;//发件人密码
    private String host;//发件邮箱服务器
    private String[] toAdress;//收件人地址
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String[] getToAdress() {
        return toAdress;
    }
    public void setToAdress(String[] toAdress) {
        this.toAdress = toAdress;
    }
    @Override
    public String toString() {
        return "EmailTO [title=" + title + ", content=" + content + ", sender="
                + sender + ", password=" + password + ", host=" + host
                + ", toAdress=" + Arrays.toString(toAdress) + "]";
    }


}
