package com.v2tech.webservices;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.v2tech.domain.ResourceUnderReview;
import com.v2tech.domain.Review;
import com.v2tech.repository.ResourceUnderReviewRepository;
import com.v2tech.services.ResourceUnderReviewService;
import com.v2tech.services.ReviewService;

@Path("/reviewRelatedService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class ReviewRelatedWebService {
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ResourceUnderReviewRepository resourceUnderReviewRepository;
	
	
	
	@POST
	@Path("/saveOrUpdateReview/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addOrUpdateReviewRating(Review review, @PathParam("token") String token){
		reviewService.saveOrUpdate(review);
		return true;
	}
	
	@GET
	@Path("/topRatedResources/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<ResourceUnderReview> getTopRatedResources(@PathParam("token") String token){
		Set<ResourceUnderReview> resources = resourceUnderReviewRepository.getTopResourcesByRating();
		return resources;
	}
	
	@GET
	@Path("/topRatedBooks/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<ResourceUnderReview> getTopRatedBooks(@PathParam("token") String token){
		Set<ResourceUnderReview> resources = resourceUnderReviewRepository.getTopBooksByRating();
		return resources;
	}
	
	
	@GET
	@Path("/topRatedCoachingClasses/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<ResourceUnderReview> getTopRatedCoachingClasses(@PathParam("token") String token){
		Set<ResourceUnderReview> resources = resourceUnderReviewRepository.getTopCoachingClassesByRating();
		return resources;
	}
	
	@GET
	@Path("/topRatedDigitalResources/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<ResourceUnderReview> getTopRatedDigitalResources(@PathParam("token") String token){
		Set<ResourceUnderReview> resources = resourceUnderReviewRepository.getTopDigitalResourcesByRating();
		return resources;
	}
	
	
	@GET
	@Path("/topRatedColleges/criteria/{criteria}/limit/{limit}/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<ResourceUnderReview> getTopRatedCollegesByCriteria(@PathParam("criteria") String criteria, @PathParam("limit") String limit, @PathParam("token") String token){
		Integer lim = 4;
		try{
			lim = Integer.parseInt(limit);
		}
		catch(Exception e){
			
		}
		Set<ResourceUnderReview> resources = resourceUnderReviewRepository.getTopCollegesByCriteriaAndRating(criteria, lim);
		return resources;
	}
	
	@GET
	@Path("/topRatedBooks/criteria/{criteria}/limit/{limit}/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<ResourceUnderReview> getTopRatedBooksByCriteria(@PathParam("criteria") String criteria, @PathParam("limit") String limit, @PathParam("token") String token){
		Integer lim = 4;
		try{
			lim = Integer.parseInt(limit);
		}
		catch(Exception e){
			
		}
		Set<ResourceUnderReview> resources = resourceUnderReviewRepository.getTopBooksByCriteriaAndRating(criteria, lim);
		return resources;
	}

}
