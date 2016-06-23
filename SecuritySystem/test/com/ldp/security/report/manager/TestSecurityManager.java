package com.ldp.security.report.manager;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ldp.security.report.domain.SecurityForm;

import junit.framework.TestCase;

public class TestSecurityManager extends TestCase{

	private BeanFactory factory;
	
	private SecurityFormManager securityFormManager;

	@Override
	protected void setUp() throws Exception {

		factory=new ClassPathXmlApplicationContext("applicationContext-*.xml");
		securityFormManager = (SecurityFormManager) factory.getBean("securityFormManager");
	}
	
	/**
	 * 只有1条数据，2016-04-25 to 2016-04-26
	 */
	public void testCheckConflict(){
				
		long departmentId = 6;
		
		assertEquals(true,
				securityFormManager.checkConflictDateByDepartmentId(
						departmentId, "2016-04-26", "2016-04-27"));

		assertEquals(true,
				securityFormManager.checkConflictDateByDepartmentId(
						departmentId, "2016-04-23", "2016-04-25"));

		assertEquals(false,
				securityFormManager.checkConflictDateByDepartmentId(
						departmentId, "2016-04-23", "2016-04-23"));

		assertEquals(false,
				securityFormManager.checkConflictDateByDepartmentId(
						departmentId, "2016-04-28", "2016-04-30"));
		
		assertEquals(true,
				securityFormManager.checkConflictDateByDepartmentId(
						departmentId, "2016-04-21", "2016-04-30"));
	}
	

	public void testCheckConflictWithoutMyself(){
				
		long securityFormId = 1;
		long departmentId = 6;

		assertEquals(true,
				securityFormManager.checkConflictDateByDepartmentId(
						departmentId, "2016-04-27", "2016-05-01"));
		
		assertEquals(false,
				securityFormManager.checkConflictDateWithoutMyselfByDepartmentId(
						securityFormId,departmentId, "2016-04-27", "2016-05-01"));
	}
	
	public void testGetSecurityFormListWithinPeriod(){
		
		long departmentId = 6;
		String startDateString = "2016-04-01";
		String endDateString = "2016-04-08";
		
		List<SecurityForm> securityFormList = 
			securityFormManager.getSecurityFormListByParam(
					null, departmentId, startDateString, endDateString
					, SecurityForm.STATE_VERIFY);
		
		for(SecurityForm securityForm:securityFormList){
			System.out.println(securityForm.toString());
		}
	}
	
}
