package com.v2tech.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.v2tech.domain.Review;

public interface ReviewRepository extends GraphRepository<Review>{

}
