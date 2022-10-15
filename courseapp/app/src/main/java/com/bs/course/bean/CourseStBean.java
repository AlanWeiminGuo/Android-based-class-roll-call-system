package com.bs.course.bean;


import java.util.List;

public class CourseStBean {
    /**
     * msg :
     * code : 0
     * data : [{"id":"4f59c9d9151149648e7599133790f7e8","studentId":"00001","name":"小红","sex":"女","phone":"15208406137","head":"/155236130269735280.jpg","password":"e10adc3949ba59abbe56e057f20f883e","state":"已签到"},{"id":"23c602b2e428417b9eecedc12d509574","studentId":"00002","name":"小明","sex":"男","phone":"15208406138","head":null,"password":"e10adc3949ba59abbe56e057f20f883e","state":"未签到"}]
     * count : 2
     */
    private String msg;
    private int code;
    private int count;
    private List<DataBean> data;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4f59c9d9151149648e7599133790f7e8
         * studentId : 00001
         * name : 小红
         * sex : 女
         * phone : 15208406137
         * head : /155236130269735280.jpg
         * password : e10adc3949ba59abbe56e057f20f883e
         * state : 已签到
         */
        private String id;
        private String studentId;
        private String name;
        private String sex;
        private String phone;
        private String head;
        private String password;
        private String state;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
