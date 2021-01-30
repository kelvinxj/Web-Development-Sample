package com.gupaoedu.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class User1 {

    @Value("user1")//inject normal string value
    private String userName;

    @Value("#{systemProperties['os.name']}")
    private String systemPropertiesName;

    @Value("#{T(java.lang.Math).random()*100}")
    private double randomNumber;

    @Value("#{person.personName}")
    private String fromPersonName;

    @Value("classpath:test.txt")
    private Resource resourceFile;

    @Value("http://www.baidu.com")
    private Resource baiduFile;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSystemPropertiesName() {
        return systemPropertiesName;
    }

    public void setSystemPropertiesName(String systemPropertiesName) {
        this.systemPropertiesName = systemPropertiesName;
    }

    public double getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(double randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getFromPersonName() {
        return fromPersonName;
    }

    public void setFromPersonName(String fromPersonName) {
        this.fromPersonName = fromPersonName;
    }

    public Resource getResourceFile() {
        return resourceFile;
    }

    public void setResourceFile(Resource resourceFile) {
        this.resourceFile = resourceFile;
    }

    public Resource getBaiduFile() {
        return baiduFile;
    }

    public void setBaiduFile(Resource baiduFile) {
        this.baiduFile = baiduFile;
    }

    @Value("${jdbc.url}")
    private String JdbcUrl;

    public String getJdbcUrl() {
        return JdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        JdbcUrl = jdbcUrl;
    }

    public String getClassDriverName() {
        return classDriverName;
    }

    public void setClassDriverName(String classDriverName) {
        this.classDriverName = classDriverName;
    }

    @Value("${jdbc.driverName}")
    private String classDriverName;

//    @Override
//    public String toString() {
//        return "User1{" +
//                "userName='" + userName + '\'' +
//                ", systemPropertiesName='" + systemPropertiesName + '\'' +
//                ", randomNumber=" + randomNumber +
//                ", fromPersonName='" + fromPersonName + '\'' +
//                ", resourceFile=" + resourceFile +
//                ", baiduFile=" + baiduFile +
//                '}';
//    }


    @Override
    public String toString() {
        return "User1{" +
                "JdbcUrl='" + JdbcUrl + '\'' +
                ", classDriverName='" + classDriverName + '\'' +
                '}';
    }
}
