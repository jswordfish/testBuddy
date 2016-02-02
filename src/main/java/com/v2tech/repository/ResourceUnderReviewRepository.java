package com.v2tech.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.v2tech.domain.ResourceUnderReview;
import com.v2tech.domain.Review;
@Repository
public interface ResourceUnderReviewRepository extends GraphRepository<ResourceUnderReview>{

	@Query("MATCH (rr:ResourceUnderReview) WHERE rr.resourceName =~ {0} return rr LIMIT 1;")
	public ResourceUnderReview getResourceUnderReviewByResourceName(String resourceName);
	
	@Query("MATCH (rr:ResourceUnderReview) RETURN rr ORDER BY rr.averageRatingForResource DESC LIMIT 12")
	public Set<ResourceUnderReview> getTopResourcesByRating();
	
	@Query("MATCH (rr:ResourceUnderReview) WHERE rr.resourceType = 'BOOK' RETURN rr  ORDER BY rr.averageRatingForResource DESC LIMIT 12")
	public Set<ResourceUnderReview> getTopBooksByRating();
	
	@Query("MATCH (rr:ResourceUnderReview)  WHERE rr.resourceType = 'COACHING_CLASS' RETURN rr ORDER BY rr.averageRatingForResource DESC LIMIT 12")
	public Set<ResourceUnderReview> getTopCoachingClassesByRating();
	
	@Query("MATCH (rr:ResourceUnderReview)  WHERE rr.resourceType = 'DIGITAL_RESOURCE' RETURN rr ORDER BY rr.averageRatingForResource DESC LIMIT 12")
	public Set<ResourceUnderReview> getTopDigitalResourcesByRating();
	
		
	@Query("MATCH (rr:ResourceUnderReview) WHERE rr.resourceType = 'COLLEGE' RETURN rr ORDER BY rr.averageRatingForResource DESC LIMIT 12")
	public Set<ResourceUnderReview> getTopCollegesByRating();

}
