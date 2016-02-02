package com.v2tech.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.v2tech.domain.Review;

public interface ReviewRepository extends GraphRepository<Review>{

	@Query("MATCH (review:Review) WHERE review.userName = {0} AND review.resourceName={1} return review LIMIT 1;")
	public Review getReviewByUserNameAndResourceName(String user, String resourceName);
	
	
}
