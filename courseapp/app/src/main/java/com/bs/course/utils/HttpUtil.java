package com.bs.course.utils;

public class HttpUtil {
	public static String BASE_URL = "http://192.168.1.105:8081/course";

	public static String IMG_URL = "http://192.168.1.105:8081/img";

	/**
	 * 学生登陆
	 */
	public static String USER_LOGIN = BASE_URL+"/student/login";

	/**
	 * 老师登陆
	 */
	public static String TEACHER_LOGIN =BASE_URL+"/teacher/login";

	/**
	 * 修改密码
	 */
	public static String UPDATE_PASSWORD=BASE_URL+"/student/uppwd";

	/**
	 * 缺课次数查询
	 */
	public static String St_Quekecishu=BASE_URL+"/student/quekecishu";

	/**
	 * 教师的缺课次数查询
	 */
	public static String T_Quekecishu=BASE_URL+"/teacher/quekecishu";

	/**
	 * 教师对课程缺课率查询
	 */
	public static String T_Quekelv=BASE_URL+"/teacher/quekelv";

	/**
	 * 教师对课程缺课率查询的课程数量统计
	 */
	public static String T_Quekelvcount=BASE_URL+"/teacher/quekelvcount";

    /**
     * 教师对课程缺课率查询的课程，该课程未签到人数
     */
    public static String T_QuekelvUnsign=BASE_URL+"/teacher/quekelvunsign";

	/**
	 * 教师对课程缺课率查询的课程，该课程全体选课人数
	 */
	public static String T_QuekelvAll=BASE_URL+"/teacher/quekelvall";

	/**
	 * 修改学生资料
	 */
	public static String UPINFO =BASE_URL+"/student/upinfo";

	/**
	 * 获取学生信息
	 */
	public static String GETUSERINFO  =BASE_URL+"/student/getStudentinfo";

	/**
	 * 上传头像
	 */
	public static String IMGUPLOAD  =BASE_URL+"/img/upload";

	/**
	 * 获取老师信息
	 */
	public static String GETTEACHER  =BASE_URL+"/teacher/getTeacherinfo";

	/**
	 * 修改老师信息
	 */
	public static String TEACHERUPINFO  =BASE_URL+"/teacher/upinfo";

	/**
	 * 签到状况修改
	 */
	public static String QIANDAOCHANGE  =BASE_URL+"/teacher/qiandaochange";


	/**
	 * 注册老师信息
	 */
	public static String TEACHERREGISTER  =BASE_URL+"/teacher/register";


	/**
	 * 注册学生信息
	 */
	public static String STUDENTREGISRTER  =BASE_URL+"/student/register";

	/**
	 * 修改老师密码
	 */
	public static String TEACHERUPPWD  =BASE_URL+"/teacher/uppwd";

	/**
	 * 获取课堂学生列表
	 */
	public static String COURSE_STLIST  =BASE_URL+"/teacher/course_stlist";

	/**
	 * 获取老师课程列表
	 */
	public static String COURSE_LIST  =BASE_URL+"/teacher/course_applist";

	/**
	 * 获取老师课程列表
	 */
	public static String STUDENTCOURSE_LIST  =BASE_URL+"/teacher/course_list";

	/**
	 * 获取老师课程列表
	 */
	public static String COURSE_STLISTs  =BASE_URL+"/student/course_stlist";

	/**
	 * 上课
	 */
	public static String TEACHER_SK  =BASE_URL+"/teacher/sk";

	/**
	 * 下课
	 */
	public static String TEACHER_XK  =BASE_URL+"/teacher/xk";

	/**
	 * 学生签到
	 */
	public static String USER_QD  =BASE_URL+"/student/qd";

	/**
	 * 学生获取课堂成绩
	 */
	public static String GETCJ  =BASE_URL+"/student/getcj";

	/**
	 * 选课
	 */
	public static String ADDSTUDENT  =BASE_URL+"/teacher/addstudent";

	/**
	 * 添加课程
	 */
	public static String ADDCOURSE  =BASE_URL+"/teacher/addcourse";

	/**
	 * 添加成绩
	 */
	public static String ADDCJ  =BASE_URL+"/teacher/addcj";


}
