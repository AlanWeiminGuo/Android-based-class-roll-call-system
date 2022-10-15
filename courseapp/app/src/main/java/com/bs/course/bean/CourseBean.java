package com.bs.course.bean;

import java.util.List;


public class CourseBean {
    /**
     * msg :
     * code : 0
     * data : [{"id":"7d76e65c70f64cee92a3f837d0deee6c","courseName":"JAVA","startTime":"2019-03-11 09:20:00","endTime":"2019-03-11 12:00:00","state":"已完成","teacherId":"e295332ca20542e59b52ca1f5084a91e","kcdescribe":"面向对象","teacher":null,"students":null}]
     * count : 1
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
         * id : 7d76e65c70f64cee92a3f837d0deee6c
         * courseName : JAVA
         * startTime : 2019-03-11 09:20:00
         * endTime : 2019-03-11 12:00:00
         * state : 已完成
         * teacherId : e295332ca20542e59b52ca1f5084a91e
         * kcdescribe : 面向对象
         * teacher : null
         * students : null
         */
        private String id;
        private String courseName;
        private String startTime;
        private String endTime;
        private String state;
        private String teacherId;
        private String kcdescribe;
        private Object teacher;
        private Object students;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(String teacherId) {
            this.teacherId = teacherId;
        }

        public String getKcdescribe() {
            return kcdescribe;
        }

        public void setKcdescribe(String kcdescribe) {
            this.kcdescribe = kcdescribe;
        }

        public Object getTeacher() {
            return teacher;
        }

        public void setTeacher(Object teacher) {
            this.teacher = teacher;
        }

        public Object getStudents() {
            return students;
        }

        public void setStudents(Object students) {
            this.students = students;
        }
    }
}
