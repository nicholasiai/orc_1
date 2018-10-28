package com.likai.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SysLog {
    private String id;
    private Date visitTime;         //访问时间
    private String visitTimeStr;
    private String username;        //用户名
    private String ip;              //ip
    private String url;
    private Long executionTime;     //执行时间
    private String method;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTimeStr() {

        if (visitTime!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            visitTimeStr = sdf.format(visitTime);
        }

        return visitTimeStr;
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
