package com.bs.course.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.course.dao.AdminMapper;
import com.bs.course.entity.Course;
import com.bs.course.entity.Student;
import com.bs.course.entity.Teacher;
import com.bs.course.util.CommonUtil;

/**
 *管理员相关服务
 */
@Service
public class AdminService {
	@Autowired
	private AdminMapper adminMapper;
	
	
	/*****************************学生管理*************************************/
	/**
	 * 学生列表服务
	 * @param params
	 * @return
	 */
	public Map<String,Object> student_list(Map<String,Object> params){
		Map<String,Object> ret = new HashMap<String,Object>();
		List<Student> list = adminMapper.student_list(params);
		List<Student> list1 = adminMapper.student_list1(params);
		ret.put("code", 0);
		ret.put("msg", "");
		ret.put("data", list);
		ret.put("count", list1.size());
		return ret;
	}
	
	public Map<String,Object> student_list1(Map<String,Object> params){
		Map<String,Object> ret = new HashMap<String,Object>();
		List<Student> list1 = adminMapper.student_list1(params);
		ret.put("code", 0);
		ret.put("msg", "");
		ret.put("data", list1);
		return ret;
	}
	
	/**
	 * 删除学生服务
	 * @param entity
	 * @return
	 */
	public Map<String,Object> delastudent(Student entity){
		Map<String,Object> ret = new HashMap<String,Object>();
		int i = adminMapper.delastudent(entity);
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
	 * 修改个人资料
	 * @param user
	 * @return
	 */
	public Map<String,Object> upinfo(Student user){
		Map<String,Object> ret = new HashMap<String,Object>();
		int i = adminMapper.upinfo(user);
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
	public Map<String, Object> stregister(String id,  String name, String sex, String phone,String password) {
		Map<String, Object> ret = new HashMap<String, Object>();
		
		
        Map<String,Object> params = new HashMap<String,Object>();
		
		
		System.out.println(id);
		System.out.println(name);
		System.out.println(sex);
		System.out.println(phone);
		System.out.println(password);
		
		params.put("id",id);
		params.put("name", name);
		params.put("sex",sex);
		params.put("phone", phone);
		params.put("password", password);
		
		
		int i = adminMapper.stregister(params);
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
	 * @param user
	 * @return
	 */
	public Map<String,Object> getStudentById(Map<String,Object> params){
		Map<String,Object> ret = new HashMap<String,Object>();
		Student u = adminMapper.getStudent(params);
		if (u == null) {
			ret.put("code", -1);
			ret.put("msg", "帐号或密码不正确");
			return ret;
		}
		ret.put("code", 0);
		ret.put("user", u);
		return ret;
	}
	

	/**
	 * 登录/获取用户信息
	 * @param user
	 * @return
	 */
	public Map<String,Object> getStudent(Student user){
		Map<String,Object> ret = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("studentId", user.getStudentId());
		params.put("password", user.getPassword());
		Student u = adminMapper.getStudent(params);
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
	 * 缺课次数查询
	 * @param user
	 * @return
	 */
	public Map<String,Object> stquekecishu(String course_name, String studentId){
		Map<String,Object> ret = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		
		
		System.out.println(studentId);
		
		params.put("course_name", course_name);
		params.put("studentId", studentId);

	
		int i = adminMapper.stquekecishu(params);
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
	
	
	/** 修改密码
	 * @param user
	 * @return
	 */
	public Map<String,Object> uppasswrod(String id,String password,String newpassword){
		Map<String,Object> ret = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		Map<String,Object> params1 = new HashMap<String,Object>();
		params.put("id", id);
		Student u = adminMapper.getStudent(params);
		String md5pas = password;
		if (!u.getPassword().equals(md5pas)) {
			ret.put("code", -1);
			ret.put("msg", "原密码错误");
			return ret;
		}
		params1.put("id", id);
		params1.put("password", newpassword);
		int i = adminMapper.uppasswrod(params1);
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "修改失败");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", "修改成功");
		return ret;
	}
	
	/************************************老师管理*************************************/
	/**
	 * 添加老师
	 * @param user
	 * @return
	 */
	public Map<String,Object> addteacher(Teacher teacher){
		Map<String,Object> ret = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("jobNumber", teacher.getJobNumber());
		Teacher u = adminMapper.getTeacher(params);
		if (u!=null) {
			ret.put("code", -1);
 			ret.put("msg", "该工号的老师已存在，请重新输入");
 			return ret;
		}
		teacher.setId(CommonUtil.id());
		teacher.setPassword("123456");
		int i = adminMapper.addteacher(teacher);
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "操作失败");
			return ret;
		}
		ret.put("code", 0);
		return ret;
	}
	
	/**
	 * 老师列表服务
	 * @param params
	 * @return
	 */
	public Map<String,Object> teacher_list(Map<String,Object> params){
		Map<String,Object> ret = new HashMap<String,Object>();
		List<Teacher> list = adminMapper.teacher_list(params);
		List<Teacher> list1 = adminMapper.teacher_list1(params);
		ret.put("code", 0);
		ret.put("msg", "");
		ret.put("data", list);
		ret.put("count", list1.size());
		return ret;
	}
	
	
	/**
	 * 删除老师服务
	 * @param entity
	 * @return
	 */
	public Map<String,Object> delateacher(Teacher entity){
		Map<String,Object> ret = new HashMap<String,Object>();
		int i = adminMapper.delateacher(entity);
		if (i == 0) {
			ret.put("code", -1);
			ret.put("msg", "操作失败");
			return ret;
		}
		ret.put("code", 0);
		ret.put("msg", "删除成功");
		return ret;
	}
}
