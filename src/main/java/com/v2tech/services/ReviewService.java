package com.v2tech.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v2tech.base.V2GenericException;
import com.v2tech.domain.ResourceUnderReview;
import com.v2tech.domain.Review;
import com.v2tech.repository.ResourceUnderReviewRepository;
import com.v2tech.repository.ReviewRepository;

@Service
public class ReviewService {
@Autowired
ReviewRepository reviewRepository;

@Autowired
ResourceUnderReviewRepository resourceUnderReviewRepository;

@Autowired
ResourceUnderReviewService resourceUnderReviewService;

	public void saveOrUpdate(Review review) throws V2GenericException{
		if(review.getUserName() == null || review.getResourceName() == null){
			throw new V2GenericException("Invalid data");
		}
		
		review.setUserName(review.getUserName().trim());
		review.setResourceName(review.getResourceName().trim());
		if(review.getUserName().length() == 0 || review.getResourceName().length() == 0){
			throw new V2GenericException("Invalid data");
		}
		
		Review review2 = reviewRepository.getReviewByUserNameAndResourceName(review.getUserName(), review.getResourceName());
		float avg = getAverageRatingForReview(review);
		ResourceUnderReview resource = resourceUnderReviewRepository.getResourceUnderReviewByResourceName(review.getResourceName());
			if(resource == null){
				resource = new ResourceUnderReview();
			}
		
		resource.setAverageRatingForResource(avg);
		
		if(review2 == null){
			//create
			//step 1 Fetch ResourceUnderReview
			resource.setCriteria(review.getCriteria());
			resource.setResourceTitle(review.getResourceTitle());
			resource.setResourceName(review.getResourceName());
			resource.setResourceType(review.getResourceType());
			resource.setLinkURLToResource(review.getResourceLink());
			resource.setNumberOfTimesReviewed(resource.getNumberOfTimesReviewed() +1);
			resource = resourceUnderReviewService.saveOrUpdate(resource);
			reviewRepository.save(review);
		}
		else{
			//update
			resource.setCriteria(review.getCriteria());
			resource.setResourceTitle(review.getResourceTitle());
			Mapper mapper = new DozerBeanMapper();
			review.setId(review2.getId());
			resource = resourceUnderReviewService.saveOrUpdate(resource);
			review.setResourceUnderReview(resource);
			mapper.map(review, review2);
			reviewRepository.save(review2);
		}
	}
	
	
	private Float getAverageRatingForReview(Review review){
		Method[] methods = review.getClass().getMethods();
		int numberOfReviewParamsThatUserHasRated = 0;
		int totalRating = 0;
		for(Method method : methods){
			if(method.getReturnType().getName() == Integer.class.getName()){
				Integer ret;
				try {
					ret = (Integer)method.invoke(review, null);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new V2GenericException("Can not calculate Average");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new V2GenericException("Can not calculate Average");
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new V2GenericException("Can not calculate Average");
				}
				if(ret != -1){
					numberOfReviewParamsThatUserHasRated++;
					totalRating += ret;
				}
			}
		}
		float averageRating = 0.0f;
		if(totalRating == 0 || numberOfReviewParamsThatUserHasRated == 0){
			averageRating = 0.0f;
		}
		else{
			averageRating = ((float)totalRating / (float)numberOfReviewParamsThatUserHasRated);	
		}
		BigDecimal avg = new BigDecimal(averageRating);
		avg.setScale(2, BigDecimal.ROUND_HALF_UP);
		averageRating = avg.floatValue();
		return averageRating;
	}
}
