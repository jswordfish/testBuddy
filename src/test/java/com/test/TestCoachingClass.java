package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.v2tech.domain.CoachingClass;
import com.v2tech.services.CoachingClassService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestCoachingClass {
	
@Autowired	
CoachingClassService coachingClassService;
	@Test
	public void testcoachingClass(){
		CoachingClass coachingClass = new CoachingClass();
		coachingClass.setAddress("test");
		coachingClass.setcStreams("Engineering");
		coachingClass.setLocation("Rampur");
		coachingClass.setName("Chate Classes");
		coachingClassService.addCoachingClass(coachingClass);
	}
}
