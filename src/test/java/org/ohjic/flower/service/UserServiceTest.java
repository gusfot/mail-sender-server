package org.ohjic.flower.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ohjic.flower.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/dao-context.xml"})
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	
	/**
	 * 이름으로 사용자 조회 성공테스트  
	 */
	@Test
	public void successToGetUser() {
		User user =  userService.getUser("김현래");
		System.out.println("user : " + user.toString());
		Assert.assertNotNull(user);
	}
	
	/**
	 * 이름으로 사용자 조회 실패 테스트 
	 */
	@Test
	public void failToGetUser() {
		User user = userService.getUser("as;ldkfj");
		Assert.assertNull(user);
	}
	
	/**
	 * 사용자 등록 성공 테스트 
	 */
	@Test
	public void successToRegisterUser() {
		User user = new User();
		
		user.setUserId("국성환");
		user.setUserName("국성환");
		user.setEmail("test1@52ch.kr");
		user.setCellPhone("01011112222");
		user.setHomePhone("0233334444");
		user.setPassword("test1");
		user.setBirth("1980101");
		user.setAddr("경기도 광명시");
		user.setJob("직업");
		user.setGender("1");
		user.setEmail("test@52ch.kr");
		
		boolean result = userService.regist(user);
		
		
		Assert.assertTrue(result);
		
	}
	
	/**
	 * 사용자 등록 실패 테스트 
	 */
	@Test
	public void failToRegisterUser() {
		
		User user = new User();
		user.setUserId("test123114");
		
		boolean result= userService.regist(user);
		
		Assert.assertTrue(result);
		
	}
	
	/**
	 * 목장원 등록 성공 테스트 
	 */
	@Test
	public void successToModifyUser() {
		
		User user =  userService.getUser("test84");
		user.setJob("개발자");
		user.setGender("2");
		
		boolean result = userService.modifyUser(user);
		
		Assert.assertTrue(result);
	}
	
	/**
	 * 목장원 등록 실패 테스트 
	 */
	@Test
	public void failToModifyUser() {
		User user = userService.getUser("test123114");
		user.setUserId("test1");
	}
	
	/**
	 * 목장원 삭제 실패 테스트 
	 */
	@Test
	public void failToRemoveUser() {
		
		boolean result = userService.removeUser("test123114");
		Assert.assertTrue(result);
		
	}
	
	
	@Test
	public void successRemoveSheep(){
		//Assert.assertTrue(userService.removeSheep("test4",1l));
	}
	
}
