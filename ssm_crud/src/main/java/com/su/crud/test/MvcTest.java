package com.su.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.su.crud.entity.Employee;

/**
 * spring test模块提供的测试，请求的功能，测试crud
 * @author ER
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)//junit提供的注解，标志着运行测试时是用springtest运行的
@WebAppConfiguration //获取web ioc容器中的东西进行自动装配
@ContextConfiguration(locations= {"classpath:applicationContext.xml","file:src/main//webapp/WEB-INF/springDispatcherServlet-servlet.xml"}) 
public class MvcTest {

	//传入springmvc的ioc，
	@Autowired
	WebApplicationContext context;

	MockMvc mockMvc;//虚假的意思，虚拟的mvc
	
	@Before//junit的before,每次测试进行初始化，生成虚拟mockmvc
	public void initMockMvc() {
		mockMvc =MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testPage() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1"))
		.andReturn();
		//请求成功后，请求域中会有pageInfo,取出pageInfo验证
		MockHttpServletRequest request = result.getRequest();
		PageInfo pi = (PageInfo)request.getAttribute("pageInfo");
		System.out.println("当前页码:"+ pi.getPageNum());
		System.out.println("总页码："+ pi.getPages());
		System.out.println("总记录数：" +pi.getTotal());
		int[] nums=pi.getNavigatepageNums();
		System.out.println("在页面连续显示的总页码：");
		for(int i:nums) {
			System.out.println(i);
			
		}
		//获取员工数据
		List<Employee> list =pi.getList();
		
		for(Employee employee :list) {
			System.out.println("Id: "+employee.getEmpId()+"==>name: "+employee.getEmpName());
		}
	}
	
}
