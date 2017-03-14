package com.demo.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hoo on 2017/3/10.
 */
public class ConfigLoader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    private static final ConfigLoader INSTANCE = new ConfigLoader();

    private String configPath = "config.properties";

    private Properties configuration = null;

    public static final ConfigLoader getInstance() {
        return INSTANCE;
    }

    public static final ConfigLoader getInstance(String configPath) {
        return new ConfigLoader(configPath);
    }

    private ConfigLoader(String configPath) {
        this.configuration = load(configPath);
    }

    private ConfigLoader() {
        this.configuration = load(this.configPath);
    }

    private Properties load(String configPath) {
        Properties configuration = null;

        if (null == configPath || "".equals(configPath)) {
            try {
                Properties prop = System.getProperties();
                Object fileName = prop.get("sys.config.name");
                if ((fileName != null) && (!"".equals((String) fileName))) {
                    logger.info("通过环境变量获取配置信息>>>>" + (String) fileName);
                    configPath = (String) fileName;
                } else {
                    configPath = this.configPath;
                }
            } catch (Exception e) {
                logger.error("配置文件不存在，请检查系统参数[sys.config.name]的配置。", e);
            }
        }

        InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream(configPath);
        try {
            configuration = new Properties();
            configuration.load(is);
            logger.info("Loading " + configPath + " success");
        } catch (Exception e) {
            logger.error("Loading " + configPath + " error ", e);
            System.exit(-1);
        }

        return configuration;
    }

    public Properties load() {
        return configuration;
    }

    public String getValue(String key) {
        return this.configuration.getProperty(key);
    }

    public String getValue(String key,String defaultValue) {
        String valueStr = this.configuration.getProperty(key);
        if (null == configPath || "".equals(valueStr))
            return defaultValue;
        try {
            return valueStr;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return defaultValue;
    }

    public int getIntValue(String key) {
        String valueStr = this.configuration.getProperty(key);
        try {
            return Integer.parseInt(valueStr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return -1;
    }

    public int getIntValue(String key, int defaultValue) {
        String valueStr = this.configuration.getProperty(key);
        if (null == configPath || "".equals(valueStr))
            return defaultValue;
        try {
            return Integer.parseInt(valueStr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return defaultValue;
    }

    public double getDoubleValue(String key) {
        String valueStr = this.configuration.getProperty(key);
        try {
            return Double.parseDouble(valueStr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return -1.0D;
    }

    public double getDoubleValue(String key,double defaultValue) {
        String valueStr = this.configuration.getProperty(key);
        if (null == configPath || "".equals(valueStr))
            return defaultValue;
        try {
            return Integer.parseInt(valueStr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return defaultValue;
    }

    public long getLongValue(String key) {
        String valueStr = this.configuration.getProperty(key);
        try {
            return Long.parseLong(valueStr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return -1l;
    }

    public long getLongValue(String key,long defaultValue ) {
        String valueStr = this.configuration.getProperty(key);
        if (null == configPath || "".equals(valueStr))
            return defaultValue;
        try {
            return Long.parseLong(valueStr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return defaultValue;
    }

    public boolean getBooleanValue(String key) {
        String valueStr = this.configuration.getProperty(key);
        if(valueStr!=null && !"".equals(valueStr)){
            if("true".equals(valueStr)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public boolean getBooleanValue(String key,boolean flag) {
        String valueStr = this.configuration.getProperty(key);
        if(valueStr!=null && !"".equals(valueStr)){
            if("true".equals(valueStr)){
                return true;
            }else{
                return false;
            }
        }else{
            return flag;
        }
    }

}
