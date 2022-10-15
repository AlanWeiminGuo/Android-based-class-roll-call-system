package com.bs.course.bean;


public class Response {
    /**
     * msg : 该课程还没到下课时间
     * code : -1
     */
    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
