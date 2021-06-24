package com.ruihai.helper.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AgLog {

    public static Logger errorLog = LogManager.getLogger("errorLogger");
    public static Logger infoLog = LogManager.getLogger("infoLogger");
    public static Logger debugLog = LogManager.getLogger("debugLogger");

    //root
    public static Logger messLog = LogManager.getLogger("messLogger");
}
