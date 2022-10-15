package com.bs.course.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.bs.course.dao.AdminMapper;
import com.bs.course.entity.Student;
import com.bs.course.entity.Teacher;
import com.bs.course.service.AdminService;
import com.bs.course.util.CommonUtil;
import com.bs.course.util.ExcelRead;
import com.bs.course.util.FileUtil;

/**
 *管理员相关接口
 */
@Controller
public class AdminController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminMapper adminMapper;
	
	/**
	 * 管理员登陆
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(String username,String password,HttpServletRequest request){
		Map<String,Object> ret = new HashMap<String,Object>();
		if (!"admin".equals(username) || !"admin".equals(password)) {
			ret.put("retcode", -1);
			ret.put("msg", "账号或密码不正确");
			return ret;
		}
		ret.put("retcode", 1);
		return ret;
	}
	
	
	/*****************************学生管理*************************************/
	/**
	 * 学生列表
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/admin/student_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> student_list(String studentId,String name,String sex,int page,int limit){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("studentId", studentId);
			params.put("name", name);
			params.put("sex", sex);
			params.put("offset", (page-1)*limit);
			params.put("limit", limit);
			return adminService.student_list(params);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	@RequestMapping(value="/admin/student_list1",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> student_list1(String studentId,String name,String sex){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("studentId", studentId);
			params.put("name", name);
			params.put("sex", sex);
			return adminService.student_list1(params);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	
	/**
	 * 删除用户
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/admin/delastudent",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delastudent(Student entity){
		try {
			return adminService.delastudent(entity);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 学生修改密码
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/student/uppwd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> uppasswrod(String id,String password,String newpassword,HttpServletRequest request){
		try {
			return adminService.uppasswrod(id,password,newpassword);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 学生缺课次数查询
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/student/quekecishu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> stquekecishu(String course_name, String studentId){
		System.out.println("1");
		try {
			Map<String,Object> ret = new HashMap<String,Object>();
			System.out.println("1");
			System.out.println(course_name);
			System.out.println(studentId);
			
			return adminService.stquekecishu(course_name,studentId);
		
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试22");
			return ret;
		}
	}
	

	/**
	 * 注册学生
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/student/register",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> stregister(String id,  String name, String sex, String phone,String password){
		try {

			return adminService.stregister(id, name, sex, phone, password);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	
  	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/student/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> userlogin(Student user){
		try {
			user.setPassword(user.getPassword());
			return adminService.getStudent(user);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	
	/**
	 * 获取学生信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/student/getStudentinfo",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> getStudentById(@RequestParam Map<String,Object> params){
		try {
			return adminService.getStudentById(params);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 修改学生资料
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/student/upinfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> upinfo(Student user){
		try {
			return adminService.upinfo(user);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	
	/**
	 * 导入学生
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/excel/upload",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> uploadtext(HttpServletRequest request, MultipartFile file){
		Map<String,Object> ret = new HashMap<String,Object>();
		 if (file == null || file.getSize() <= 0) {
		      ret.put("code", -1);
			  ret.put("msg", "文件不能为空");
		 }
	     try {
	    	 List<ArrayList<String>> list = new ExcelRead().readExcel(file);
	    	 Student student = null;  
	    	 List<Student> lStudents = new ArrayList<>();
			 for(ArrayList<String> arr : list){    
				 //按照表格的规则循环添加
			     if(arr.get(0)!=null&&!arr.get(0).toString().equals("")){
			    	 student = new Student();    
			    	 student.setId(CommonUtil.id());
			    	 student.setStudentId(arr.get(0).toString());
			    	 student.setName(arr.get(1).toString());
			    	 student.setSex(arr.get(2).toString());
			    	 student.setPhone(arr.get(3).toString());
			    	 student.setPassword("123456");
			    	 Map<String,Object> params = new HashMap<String,Object>();
			    	 params.put("studentId", student.getStudentId());
			 		 Student u = adminMapper.getStudent(params);
			 		 if (u != null) {
			 			ret.put("code", -1);
			 			ret.put("msg", "学号为"+u.getStudentId()+"学生已存在，请修改后重新导入");
			 			return ret;
			 		}else {
			 			lStudents.add(student);
					}
			    }
			}
			 for (int i = 0; i < lStudents.size(); i++) {
				Student student2 = lStudents.get(i);
				adminMapper.saveStudent(student2);
			 }
			 ret.put("code", 0);
	 		 ret.put("msg", "导入成功");
			return ret;
		} catch (Exception e) {
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		} 
	}
	
	/*****************************老师管理*************************************/
	/**
	 * 添加老师
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/admin/addteacher",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> addteacher(Teacher teacher){
		try {
			return adminService.addteacher(teacher);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 老师列表
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/admin/teacher_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> teacher_list(String jobNumber,String name,String sex,String rank,int page,int limit){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("jobNumber", jobNumber);
			params.put("name", name);
			params.put("rank", rank);
			params.put("sex", sex);
			params.put("offset", (page-1)*limit);
			params.put("limit", limit);
			return adminService.teacher_list(params);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 删除老师
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/admin/delateacher",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delateacher(Teacher entity){
		try {
			return adminService.delateacher(entity);
		} catch (Exception e) {
			Map<String,Object> ret = new HashMap<String,Object>();
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		}
	}
	
	/**
	 * 图片上传
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/img/upload",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> upload(HttpServletRequest request, MultipartFile file){
		 Map<String,Object> ret = new HashMap<String,Object>();
		 if (file == null || file.getSize() <= 0) {
		      ret.put("code", -1);
			  ret.put("msg", "图片不能为空");
			  return ret;
		 }
	     try {
	    	String fileurl = FileUtil.uploadimg(file);
	    	ret.put("fileurl", fileurl);
	    	ret.put("code", 0);
	    	ret.put("msg", "上传成功");
			return ret;
		} catch (Exception e) {
			ret.put("code", -2);
			ret.put("msg", "系统繁忙，请稍后再试");
			return ret;
		} 
	}
	
}
