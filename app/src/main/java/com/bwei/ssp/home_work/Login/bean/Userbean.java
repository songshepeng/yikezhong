package com.bwei.ssp.home_work.Login.bean;

public class Userbean {


    /**
     * msg : 登录成功
     * code : 0
     * data : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-10-19T20:24:16","email":null,"gender":0,"icon":null,"mobile":"15201336898","money":0,"nickname":null,"password":"111111","token":null,"uid":961,"username":"15201336898"}
     */

    private String msg;
    private String code;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}
