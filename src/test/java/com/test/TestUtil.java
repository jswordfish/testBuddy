package com.test;

import java.util.Set;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.v2tech.domain.Course;
import com.v2tech.domain.CourseType;
import com.v2tech.domain.RESOURCE_TYPE;
import com.v2tech.domain.ResourceUnderReview;
import com.v2tech.domain.Review;
import com.v2tech.services.CourseService;
import com.v2tech.services.ReviewService;
import com.v2tech.webservices.ReviewRelatedWebService;
import com.v2tech.webservices.UtilWebService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestUtil {

	@Autowired
	UtilWebService utilWebService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ReviewRelatedWebService reviewRelatedWebService;
	
	@Test
	public void testCreateCourse(){
		Course c1 = new Course("MBA/PGDM", CourseType.MANAGEMENT.toString());
		Course c2 = new Course("Exceutive MBA", CourseType.MANAGEMENT.toString());
		Course c3 = new Course("MHA", CourseType.MANAGEMENT.toString());
		Course c4 = new Course("M.Phil", CourseType.MANAGEMENT.toString());
		Course c5 = new Course("PHD", CourseType.MANAGEMENT.toString());
		Course c6 = new Course("BBM/BMS", CourseType.MANAGEMENT.toString());
		
		courseService.saveOrUpdate(c1);
		courseService.saveOrUpdate(c2);
		courseService.saveOrUpdate(c3);
		courseService.saveOrUpdate(c4);
		courseService.saveOrUpdate(c5);
		courseService.saveOrUpdate(c6);
	}
	
	@Test
	public void testGetAllCourseTypes(){
		Response res = utilWebService.fetchAllCourseTypes();
		Object obj = res.getEntity();
	}
	
	@Test
	public void testGetAllCoursesForType(){
		Response res = utilWebService.fetchAllCoursesForType( CourseType.MANAGEMENT.toString());
		Object obj = res.getEntity();
	}
	
	@Test
	public void testCreateOrUpdateReview(){
		Review review = new Review();
		review.setUserName("sagar@yahoo.com");
		review.setResourceName("Chate Classes");
		review.setResourceLink("http://ecx.images-amazon.com/images/I/31tYOWuZ6XL._SL160_.jpg");
		review.setResourceType(RESOURCE_TYPE.COACHING_CLASS.getType());
		review.setChapterNotes(3);
		review.setCheatSheet(4);
		review.setChemistry(5);
		review.setEffectivenessAndEaseOfCommunication(5);
		reviewRelatedWebService.addOrUpdateReviewRating(review, "");
		//reviewService.saveOrUpdate(review);
	}
	
	@Test
	public void testGetTopResources(){
		Set<ResourceUnderReview> resources = reviewRelatedWebService.getTopRatedResources("");
		for(ResourceUnderReview resource : resources){
			System.out.println("Resource is "+resource.getResourceName()+" rating avg "+resource.getAverageRatingForResource());
		}
	}
	
	@Test
	public void testGetTopBooks(){
		Set<ResourceUnderReview> resources = reviewRelatedWebService.getTopRatedBooks("");
		for(ResourceUnderReview resource : resources){
			System.out.println("Resource is "+resource.getResourceName()+" rating avg "+resource.getAverageRatingForResource());
		}
	}
	
	@Test
	public void testGetTopCoachingClasses(){
		Set<ResourceUnderReview> resources = reviewRelatedWebService.getTopRatedCoachingClasses("");
		for(ResourceUnderReview resource : resources){
			System.out.println("Resource is "+resource.getResourceName()+" rating avg "+resource.getAverageRatingForResource());
		}
	}
}
