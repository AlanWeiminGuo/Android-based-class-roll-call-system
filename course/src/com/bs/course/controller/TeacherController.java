package com.bs.course.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.course.entity.ChengJi;
import com.bs.course.entity.Course;
import com.bs.course.entity.CourseSt;
import com.bs.course.entity.Teacher;
import com.bs.course.service.TeacherService;
import com.bs.course.util.CommonUtil;

/**
 *老师相关接口
 */
@Controller
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/teacher/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> teacherlogin(Teacher user){
		try {
			user.setPassword(user.getPassword());
			return teacherService.getTeacher(user);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 添加课程
	 */
	@RequestMapping(value="/teacher/addcourse",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> addteacher(Course course){
		try {
			System.out.println("11111111111");
			return teacherService.addcourse(course);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 签到新修改
	 */
	@RequestMapping(value="/teacher/qiandaochange",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> qiandaochange(String studentId,String courseId){
		try {
			System.out.println("11111111111");
			return teacherService.qiandaochange(studentId,courseId);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 课程列表
	 */
	@RequestMapping(value="/teacher/course_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> course_list(String teacherId,String state,String courseName,int page,int limit){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("teacherId", teacherId);
			params.put("state", state);
			params.put("courseName", courseName);
			params.put("offset", (page-1)*limit);
			params.put("limit", limit);
			return teacherService.course_list(params);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	@RequestMapping(value="/teacher/course_applist",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> course_applist(String teacherId){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("teacherId", teacherId);
			return teacherService.course_list(params);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	
	/**
	 * 删除课程
	 */
	@RequestMapping(value="/teacher/delacourse",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delacourse(Course entity){
		try {
			return teacherService.delacourse(entity);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 添加课程人员
	 */
	@RequestMapping(value="/teacher/addstudent",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> addstudent(String student,String courseId){
		try {
			return teacherService.addstudent(student,courseId);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试23");
			return ret;
		}
	}
	
	/**
	 * 获取课堂学生列表
	 */
	@RequestMapping(value="/teacher/course_stlist",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> course_stlist(String courseId,int page,int limit){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("courseId", courseId);
			params.put("offset", (page-1)*limit);
			params.put("limit", limit);
			return teacherService.course_stlist(params);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 获取老师信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/teacher/getTeacherinfo",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> getStudentById(@RequestParam Map<String,Object> params){
		try {
			return teacherService.getTeacherById(params);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 修改老师资料
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/teacher/upinfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> upinfo(Teacher user){
		try {
			return teacherService.upinfo(user);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	
	/**
	 * 注册老师资料
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/teacher/register",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> tregister(String id, String rank, String name, String sex, String phone,String password){
		try {
			System.out.println("11111111111");
			return teacherService.tregister(id,rank, name, sex, phone, password);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	
	/**
	 * 老师修改密码
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/teacher/uppwd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> uppasswrod(String id,String password,String newpassword,HttpServletRequest request){
		try {
			return teacherService.uppasswrod(id,password,newpassword);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 开始上课
	 */
	@RequestMapping(value="/teacher/sk",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> sk(Course course){
		try {
			return teacherService.sk(course);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 下课
	 */
	@RequestMapping(value="/teacher/xk",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> xk(Course course){
		try {
			return teacherService.xk(course);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 签到
	 */
	@RequestMapping(value="/student/qd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> qd(CourseSt course){
		try {
			return teacherService.qd(course);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 学生获取需要上的课列表
	 */
	@RequestMapping(value="/student/course_stlist",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> course_stlist1(String studentId){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("studentId", studentId);
			return teacherService.getstCourse(params);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	
	/**
	 * 教师对学生缺课次数查询
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/teacher/quekecishu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> tquekecishu(String course_name, String studentId,String teacherId){
		System.out.println("12345");
		try {
			Map<String,Object> ret = new HashMap<String,Object>();
			System.out.println("1");
			System.out.println(course_name);
			System.out.println(studentId);
			System.out.println(teacherId);
			return teacherService.tquekecishu(course_name,studentId,teacherId);
		
		} catch (Exception e) {
	
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试22");
			return ret;
		}
	}
	
	
	/**
	 * 教师对课程缺课率查询
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/teacher/quekelv",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> tquekelv(String course_name, String teacherId,String count){
		System.out.println("------------------------------------");
		try {
			Map<String,Object> ret = new HashMap<String,Object>();
			System.out.println(course_name);
			System.out.println(teacherId);
			System.out.println("第"+count+"次");
			return teacherService.tquekelv(course_name,teacherId,count);
		
		} catch (Exception e) {
	
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试22");
			return ret;
		}
	}
	
	
	@RequestMapping(value="/teacher/quekelvcount",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quekelvcount(String course_name, String teacherId ){
		System.out.println("--------------54615-------------------");
		try {
			Map<String,Object> ret = new HashMap<String,Object>();
			return teacherService.quekelvcount(course_name,teacherId);	
		} catch (Exception e) {
	
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试22");
			return ret;
		}
	}
	
	@RequestMapping(value="/teacher/quekelvunsign",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quekelvunsign(String course_name, String teacherId ){
		System.out.println("--------------5.4-------------------");
		try {
			Map<String,Object> ret = new HashMap<String,Object>();
			return teacherService.quekelvunsign(course_name,teacherId);	
		} catch (Exception e) {
	
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试22");
			return ret;
		}
	}
	
	@RequestMapping(value="/teacher/quekelvall",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quekelvall(String course_name, String teacherId ){
		System.out.println("-----5.4-------5.4------5.4-------------");
		try {
			Map<String,Object> ret = new HashMap<String,Object>();
			return teacherService.quekelvall(course_name,teacherId);	
		} catch (Exception e) {
	
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试22");
			return ret;
		}
	}
	
	
	/**
	 * 添加成绩
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/teacher/addcj",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> addcj(ChengJi chengJi){
		try {
			return teacherService.addcj(chengJi);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	
	/**
	 * 学生获取课程成绩
	 */
	@RequestMapping(value="/student/getcj",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getcj(String studentId,String courseId){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("studentId", studentId);
			params.put("courseId", courseId);
			return teacherService.getcj(params);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	
	/**
	 * 获取课堂学生列表
	 */
	@RequestMapping(value="/teacher/st_cjlist",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> st_cjlist(String courseId,int page,int limit){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("courseId", courseId);
			params.put("offset", (page-1)*limit);
			params.put("limit", limit);
			return teacherService.st_cjlist(params);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
}
