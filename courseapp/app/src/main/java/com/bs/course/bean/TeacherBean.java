package com.bs.course.bean;

public class TeacherBean {
    /**
     * msg : 登录成功
     * code : 0
     * user : {"id":"e295332ca20542e59b52ca1f5084a91e","jobNumber":"00001","name":"刘教授","sex":"男","rank":"教授","phone":"15208403162","head":null,"password":"e10adc3949ba59abbe56e057f20f883e"}
     */
    private String msg;
    private int code;
    private UserBean user;

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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : e295332ca20542e59b52ca1f5084a91e
         * jobNumber : 00001
         * name : 刘教授
         * sex : 男
         * rank : 教授
         * phone : 15208403162
         * head : null
         * password : e10adc3949ba59abbe56e057f20f883e
         */

        private String id;
        private String jobNumber;
        private String name;
        private String sex;
        private String rank;
        private String phone;
        private String head;
        private String password;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJobNumber() {
            return jobNumber;
        }

        public void setJobNumber(String jobNumber) {
            this.jobNumber = jobNumber;
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

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
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
    }
}
