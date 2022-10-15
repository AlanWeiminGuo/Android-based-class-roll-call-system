package com.bs.course.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *管理员相关跳转
 */
@Controller
public class AdminAntion {
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage(){
		return "common/login";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logoutPage(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String indexPage(){
		return "common/index";
	}
	
	@RequestMapping(value="/student_list",method=RequestMethod.GET)
	public String student_list(){
		return "admin/student_list";
	}

	@RequestMapping(value="/teacher_list",method=RequestMethod.GET)
	public String teacher_list(){
		return "admin/teacher_list";
	}
	
	@RequestMapping(value="/addteacher",method=RequestMethod.GET)
	public String addteacher(){
		return "admin/addteacher";
	}
	
	@RequestMapping(value="/tjkcstudent_list",method=RequestMethod.GET)
	public String tjkcstudent_list(){
		return "admin/kcstudent_list";
	}
	
	@RequestMapping(value="/tjcourse_list",method=RequestMethod.GET)
	public String tjcourse_list(){
		return "admin/course_list";
	}
	
	@RequestMapping(value="/kcstudent_cjlist",method=RequestMethod.GET)
	public String kcstudent_cjlist(){
		return "admin/kcstudent_cjlist";
	}
	
	
	
}
