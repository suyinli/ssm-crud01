package com.su.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.su.crud.dao.DepartmentMapper;
import com.su.crud.dao.EmployeeMapper;
import com.su.crud.entity.Department;
import com.su.crud.entity.Employee;

/**
 * 测试dao层
 * @author ER
 * 使用spting test 来测试，配置相关jar包
 * 在@ContextConfiguration中指定spring的配置文件
 * 直接用autowried用的组件即可
 */

@RunWith(SpringJUnit4ClassRunner.class)//junit提供的注解，标志着运行测试时是用springtest运行的
@ContextConfiguration(locations= {"classpath:applicationContext.xml"}) 
public class MapperTest {

	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testCRUD() {
		/*
		 * ClassPathXmlApplicationContext classPathXmlApplicationContext =new
		 * ClassPathXmlApplicationContext("applicationContext.xml"); DepartmentMapper
		 * bean = classPathXmlApplicationContext.getBean(DepartmentMapper.class);
		 * bean.XXX; //这是利用之前的方法测试
		 */	
		
		//用spring test 专门的方法测试：maven下载spring test jar
		//System.out.println(departmentMapper);
		
		//测试部门的插入
		/*
		 * departmentMapper.insertSelective( new Department(null,"开发部") );
		 * departmentMapper.insertSelective( new Department(null,"测试部") );
		 * 
		 * //生成员工数据 employeeMapper.insertSelective(new
		 * Employee(null,"Jerry","M","Jerry@su.com",1));
		 */
		
		//批量插入员工数据，使用可以批量操作的sqlsession 
		//项目中经常会用到，所以在Ioc容器中配置
		//***注意批量和直接用for在数据库中生成不同，
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++) {
			//用UUID生成姓名
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insert(new Employee(null,uid,"M",uid+"@su.com",1));
		}
		
	}

}
