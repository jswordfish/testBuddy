package com.v2tech.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

import com.v2tech.base.V2GenericException;
import com.v2tech.domain.Keyword;
import com.v2tech.domain.User;
import com.v2tech.domain.UserKeywordRelation;
import com.v2tech.domain.util.KeywordResult;
import com.v2tech.repository.KeywordRepository;
import com.v2tech.repository.UserKeywordRelationRepository;
import com.v2tech.repository.UserRepository;
@Service
public class UserKeywordRelationService {
	@Autowired
	UserKeywordRelationRepository searchRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	KeywordRepository keywordRepository;
	
	@Autowired
	Neo4jTemplate template;
	
	@Autowired
	UserRepository userRepository;
	
	public void increaseSearchTermCounterForUser(String user, String key){
		
		User user2 = userService.getSingleUser(new User(user));
			if(user2 == null){
				throw new V2GenericException("User can nopt be null");
			}
		Keyword keyword = keywordRepository.findKeywordByText(key);
			if(keyword == null){
				keyword = keywordRepository.save(new Keyword(key));
			}
		
			
		UserKeywordRelation obj = searchRepository.findUserKeywordRelation(user, key);
		if(obj == null){
			
			UserKeywordRelation keywordRelation = new UserKeywordRelation();
			keywordRelation.setCount(1l);
			keywordRelation.setUser(user2);
			keywordRelation.setKeyword(keyword);
			
			obj = template.save(keywordRelation);
			keywordRelation.setCount(1l);
			obj = template.save(keywordRelation);
		}
		else{
			obj.setCount(obj.getCount()+ 1);
			obj = template.save(obj);
		}
	}
	
	/**
	 * For the passed user - return the search terms searched most by friends of the user
	 * @param user
	 * @return
	 */
	public List<String> getSearchedTermByFriends(String user){
		List<KeywordResult> results = userRepository.getMostSearchedKeyword(user);
		List<String> terms = new ArrayList<String>();
		for(KeywordResult keywordResult : results){
			terms.add(keywordResult.getSearchedTerm());
		}
	return terms;
	}
	
	
	
	
	

}
