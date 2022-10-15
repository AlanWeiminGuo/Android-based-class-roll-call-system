package com.bs.course.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.course.dao.AdminMapper;
import com.bs.course.dao.TeacherMapper;
import com.bs.course.entity.ChengJi;
import com.bs.course.entity.Course;
import com.bs.course.entity.CourseSt;
import com.bs.course.entity.Student;
import com.bs.course.entity.Teacher;
import com.bs.course.util.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * 老师相关接口服务
 */
@Service
public class TeacherService {
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private TeacherMapper teacherMapper;

	static String a ="001";
	
	
	
	/**
	 * 登录/获取用户信息
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Object> getTeacher(Teacher teacher) {

		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jobNumber", teacher.getJobNumber());
		params.put("password", teacher.getPassword());
		Teacher u = adminMapper.getTeacher(params);
		if (u == null) {
			ret.put("code", -1);
			ret.put("msg", "帐号或密码不正确");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", "登录成功");
		ret.put("user", u);
		return ret;
	}

	/**
	 * 添加课程
	 */
	public Map<String, Object> addcourse(Course course) {
		Map<String, Object> ret = new HashMap<String, Object>();
		
		course.setId(a);
		
		int z=Integer.valueOf(a);
		z =z+1;
		a="00"+String.valueOf(z);
		
		course.setState("正常");
		int i = teacherMapper.addcourse(course);
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "操作失败");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", "添加课程成功");
		return ret;
	}

	/**
	 * 课程列表
	 */
	public Map<String, Object> course_list(Map<String, Object> params) {
		Map<String, Object> ret = new HashMap<String, Object>();
		List<Course> list = teacherMapper.course_list(params);
		List<Course> list1 = teacherMapper.course_list1(params);
		ret.put("code", 0);
		ret.put("msg", "");
		ret.put("data", list);
		ret.put("count", list1.size());
		return ret;
	}

	public Map<String, Object> course_applist(Map<String, Object> params) {
		Map<String, Object> ret = new HashMap<String, Object>();
		List<Course> list1 = teacherMapper.course_list1(params);
		ret.put("code", 0);
		ret.put("msg", "");
		ret.put("data", list1);
		return ret;
	}

	/**
	 * 删除课程
	 * 
	 * @param entity
	 * @return
	 */
	public Map<String, Object> delacourse(Course entity) {
		Map<String, Object> ret = new HashMap<String, Object>();
		int i = teacherMapper.delacourse(entity);
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "操作失败");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", "删除成功");
		return ret;
	}

	/**
	 * 添加学生到课程
	 */
	public Map<String, Object> addstudent(String student, String courseId) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("studentId", student);
		params.put("courseId", courseId);
		CourseSt cSt = teacherMapper.getCoursest(params);
		if (cSt == null) {
			int z=Integer.valueOf(a);
			z =z+1;
			a="00"+String.valueOf(z);
			CourseSt courseSt = new CourseSt();
			courseSt.setId(a);
			courseSt.setCourseId(courseId);
			courseSt.setStudentId(student);
			courseSt.setState("未签到");
			int j = teacherMapper.addstudent(courseSt);
			if (j == 0) {
				ret.put("code", -1);
				ret.put("msg", "操作失败");
				return ret;
			}
		} else {
			ret.put("code", -1);
			ret.put("msg", "您已选择此课程，请不要重复选课");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", "添加课程成功");
		return ret;
	}

	/**
	 * 获取课程学生列表
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> course_stlist(Map<String, Object> params) {
		Map<String, Object> ret = new HashMap<String, Object>();
		List<CourseSt> list = teacherMapper.course_stlist(params);
		List<CourseSt> list1 = teacherMapper.course_stlist1(params);
		List<Student> list2 = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> ret1 = new HashMap<String, Object>();
			ret1.put("id", list.get(i).getStudentId());
			Student student = adminMapper.getStudent(ret1);
			if (student != null) {
				student.setState(list.get(i).getState());
				list2.add(student);
			}
		}
		ret.put("code", 0);
		ret.put("msg", "");
		ret.put("data", list2);
		ret.put("count", list1.size());
		return ret;
	}

	/**
	 * 修改个人资料
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Object> upinfo(Teacher user) {
		Map<String, Object> ret = new HashMap<String, Object>();
		int i = adminMapper.tupinfo(user);
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "操作失败");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", "操作成功");
		return ret;
	}
	
	
	/**
	 * 签到情况修改
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Object> qiandaochange(String studentId, String courseId) {
		Map<String, Object> ret = new HashMap<String, Object>();
		
        Map<String,Object> params = new HashMap<String,Object>();
		
		
		System.out.println(studentId);
		System.out.println(courseId);

		params.put("studentId",studentId);
		params.put("courseId", courseId);


		int i = teacherMapper.qiandaochange(params);
		System.out.println("--------------");
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "操作失败");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", "操作成功");
		return ret;
	}
	
	
	/**
	 * 注册教师资料
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Object> tregister(String id, String rank, String name, String sex, String phone,String password) {
		Map<String, Object> ret = new HashMap<String, Object>();
		
		
        Map<String,Object> params = new HashMap<String,Object>();
		
		
		System.out.println(id);
		System.out.println(rank);
		System.out.println(name);
		System.out.println(sex);
		System.out.println(phone);
		System.out.println(password);
		
		params.put("id",id);
		params.put("rank", rank);
		params.put("name", name);
		params.put("sex",sex);
		params.put("phone", phone);
		params.put("password", password);
		
		
		int i = adminMapper.tregister(params);
		System.out.println("2222222222222");
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "操作失败");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", "操作成功");
		return ret;
	}
	

	/**
	 * 获取用户信息
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Object> getTeacherById(Map<String, Object> params) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Teacher u = adminMapper.getTeacher(params);
		if (u == null) {
			ret.put("code", -1);
			ret.put("msg", "用户名或密码不正确");
			return ret;
		}
		ret.put("code", 0);
		ret.put("user", u);
		return ret;
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Object> uppasswrod(String id, String password, String newpassword) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> params1 = new HashMap<String, Object>();
		params.put("id", id);
		Teacher u = adminMapper.getTeacher(params);
		String md5pas = password;
		if (!u.getPassword().equals(md5pas)) {
			ret.put("code", -1);
			ret.put("msg", "原密码错误");
			return ret;
		}
		params1.put("id", id);
		params1.put("password", newpassword);
		int i = adminMapper.uppasswrod1(params1);
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "修改失败");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", "修改成功");
		return ret;
	}

	/**
	 * 开始上课
	 */
	public Map<String, Object> sk(Course course) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("id", course.getId());
		params1.put("state", "上课中");
		int i = teacherMapper.updatsks(params1);
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "操作失败");
			return ret;
		}
		ret.put("code", 0);
		return ret;
	}

	/**
	 * 下课
	 */
	public Map<String, Object> xk(Course course) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("id", course.getId());
		params1.put("state", "已完成");
		int i = teacherMapper.updatsks(params1);
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "操作失败");
			return ret;
		}
		ret.put("code", 0);
		return ret;
	}

	/**
	 * 签到
	 */
	public Map<String, Object> qd(CourseSt course) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("state", "已签到");
		params1.put("courseId", course.getCourseId());
		params1.put("studentId", course.getStudentId());
		int i = teacherMapper.qd(params1);
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "签到失败，请重新操作");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", "签到成功，表现不错哦！");
		return ret;
	}

	
	
	/**
	 * 缺课次数查询
	 * @param user
	 * @return
	 */
	public Map<String,Object> tquekecishu(String course_name, String studentId, String teacherId){
		Map<String,Object> ret = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		
		
		System.out.println(studentId);
		System.out.println(teacherId+"------");
		
		params.put("course_name", course_name);
		params.put("studentId", studentId);
		params.put("teacherId", teacherId);

	
		int i = teacherMapper.tquekecishu(params);
		System.out.println(i+ "...");
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "操作失败");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", i);
		return ret;
	}
	
	
	/**
	 * 缺课率查询
	 * @param user
	 * @return
	 */
	public Map<String,Object> tquekelv(String course_name, String teacherId,String count){
		Map<String,Object> ret = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		int k=Integer.valueOf(count);
		
		params.put("course_name", course_name);
		params.put("teacherId", teacherId);
		params.put("count", k);
		params.put("limit", 1);
		

		String i = teacherMapper.tquekelv(params);
		System.out.println(i+ "...");
		if (i == " ") {
			ret.put("code", -1);
			ret.put("msg", "操作失败");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", i);
		return ret;
	}
	
	public Map<String, Object> quekelvcount(String course_name, String teacherId){
		Map<String,Object> ret = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		
		int z=0;
		
		System.out.println(course_name);
		System.out.println(teacherId);
		
		params.put("course_name", course_name);
		params.put("teacherId", teacherId);
		
		Map<String,Object> params1 = new HashMap<String,Object>();
		z=teacherMapper.quekelvcount(params1);
		System.out.println(z);
      
	
		z = teacherMapper.quekelvcount(params);
		System.out.println(z+ "...");
		
		ret.put("code", 0);
		ret.put("msg", z);
		return ret;
	
	}
	
	public Map<String, Object> quekelvunsign(String course_name, String teacherId){
		Map<String,Object> ret = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();

		
		params.put("course_name", course_name);
		params.put("teacherId", teacherId);
		
		Map<String,Object> params1 = new HashMap<String,Object>();

		int i = teacherMapper.quekelvunsign(params);
		
		System.out.println(i+ "5.45.45.4");
		
		ret.put("code", 0);
		ret.put("msg", i);
		return ret;
	
	}

	public Map<String, Object> quekelvall(String course_name, String teacherId){
		Map<String,Object> ret = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();

		
		params.put("course_name", course_name);
		params.put("teacherId", teacherId);
		
		Map<String,Object> params1 = new HashMap<String,Object>();

		int i = teacherMapper.quekelvall(params);
		
		System.out.println(i+ "5.45.45.4");
		
		ret.put("code", 0);
		ret.put("msg", i);
		return ret;
	
	}
	
	/**
	 * 学生获取需要上的课列表
	 */
	public Map<String, Object> getstCourse(Map<String, Object> params) {
		Map<String, Object> ret = new HashMap<String, Object>();
		List<CourseSt> list = teacherMapper.course_stlist1(params);
		List<Course> list2 = new ArrayList<>();
		Map<String, Object> params1 = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			params1.put("id", list.get(i).getCourseId());
			Course course = teacherMapper.getCourse(params1);
			list2.add(course);
		}
		ret.put("code", 0);
		ret.put("data", list2);
		return ret;
	}

	/**
	 * 给学生添加成绩
	 */
	public Map<String, Object> addcj(ChengJi chengJi) {
		Map<String, Object> ret = new HashMap<String, Object>();
		chengJi.setId(a);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("courseId", chengJi.getCourseId());
		params.put("studentId", chengJi.getStudentId());
		ChengJi chengJi2 = teacherMapper.getcj(params);
		if (chengJi2 != null) {
			ret.put("code", -1);
			ret.put("msg", "该学生在该课堂已存在成绩");
			return ret;
		}
		int i = teacherMapper.addcj(chengJi);
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "操作失败");
			return ret;
		}
		ret.put("code", 0);
		ret.put("chengJi", chengJi);
		return ret;
	}

	/**
	 * 学生获取课堂成绩
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Object> getcj(Map<String, Object> params) {
		Map<String, Object> ret = new HashMap<String, Object>();
		ChengJi chengJi2 = teacherMapper.getcj(params);
		if (chengJi2 == null) {
			ret.put("code", -1);
			ret.put("msg", "你在该课堂还没有成绩");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", "");
		ret.put("data", chengJi2);
		return ret;
	}

	/**
	 * 获取课程学生成绩列表
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> st_cjlist(Map<String, Object> params) {
		Map<String, Object> ret = new HashMap<String, Object>();
		List<ChengJi> list = teacherMapper.st_cjlist(params);
		List<ChengJi> list1 = teacherMapper.st_cjlist1(params);
		List<Student> list2 = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> ret1 = new HashMap<String, Object>();
			ret1.put("id", list.get(i).getStudentId());
			Student student = adminMapper.getStudent(ret1);
			student.setStudentcj(list.get(i).getStudentcj());
			list2.add(student);
		}
		ret.put("code", 0);
		ret.put("msg", "");
		ret.put("data", list2);
		ret.put("count", list1.size());
		return ret;
	}

}
