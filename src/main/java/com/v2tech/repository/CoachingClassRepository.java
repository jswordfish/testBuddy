package com.v2tech.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.v2tech.domain.CoachingClass;

public interface CoachingClassRepository extends GraphRepository<CoachingClass>{
	
	@Query("MATCH (class:CoachingClass) WHERE class.name =~ {0} AND class.location =~ {1} return class;")
	public Set<CoachingClass> findCoachingClassByNameAndLocation(String name, String location);

}
