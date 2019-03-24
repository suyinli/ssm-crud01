package com.su.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.su.crud.entity.Employee;
import com.su.crud.service.EmployeeService;

/**
 * 处理员工CRUD请求
 * @author ER
 *
 */


@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	/**
	 * 查询全部员工数据；分页查询
	 * @return
	 */
	
	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1") Integer pn, Model model) {
		//这是一个普通的查询，需要引入pagehelper分页插件
		//查询之前调用：
		PageHelper.startPage(pn,5);
		//pagehelper之后跟着的查询就是一个分页查询
		
		List<Employee> emps = employeeService.getAll();
		
		//将查询到的信息封装在pageinfo中,详细的信息。包括数据，，，传入显示的页数5
		PageInfo page=new PageInfo(emps,5);
		model.addAttribute("pageInfo",page);
		//page.getNavigatepageNums();//拿到下方连续显示的页码
		
		return "list";
	}
}
