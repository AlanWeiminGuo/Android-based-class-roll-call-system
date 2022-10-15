package com.bs.course.bean;

public class ChengJi {
    /**
     * msg :
     * code : 0
     * data : {"id":"53a513aef86f45e1aa61779bfff2d49a","courseId":"96c85788155340aa8fe3df55d1d4dab3","studentId":"8e5b20e25c16436c8254740052181ded","studentcj":"95"}
     */
    private String msg;
    private int code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 53a513aef86f45e1aa61779bfff2d49a
         * courseId : 96c85788155340aa8fe3df55d1d4dab3
         * studentId : 8e5b20e25c16436c8254740052181ded
         * studentcj : 95
         */

        private String id;
        private String courseId;
        private String studentId;
        private String studentcj;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getStudentcj() {
            return studentcj;
        }

        public void setStudentcj(String studentcj) {
            this.studentcj = studentcj;
        }
    }
}
