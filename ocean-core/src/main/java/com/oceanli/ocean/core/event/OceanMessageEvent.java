package com.oceanli.ocean.core.event;

/**
 * Created by ocean on 2018/9/29 Author :  ocean Email  :  348686686@qq.com
 */
public class OceanMessageEvent {
    private Integer code;
    private String msg;
    private Object data;

    public OceanMessageEvent() {
    }

    public OceanMessageEvent(String msg) {
        this.msg = msg;
    }

    public OceanMessageEvent(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
