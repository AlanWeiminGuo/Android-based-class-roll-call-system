package com.bs.course.bean;


/**
 * 文件描述：上传文件json
 */
public class ImgBean {
    /**
     * msg : 图片上传成功
     * code : 1
     * fileurl : /155143206464850755.jpg
     */
    private String msg;
    private int code;
    private String fileurl;

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

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
