package com.v2tech.webservices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.v2.booksys.common.util.EmailThread;
import com.v2.booksys.common.util.UtilService;
import com.v2tech.base.V2GenericException;
import com.v2tech.domain.Keyword;
import com.v2tech.domain.SocialMediaType;
import com.v2tech.domain.User;
import com.v2tech.services.KeywordService;
import com.v2tech.services.UserService;

@Path("/resourceService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class ResourceWebService {
	@Autowired
	UserService userService;
	
	@Autowired
	KeywordService keywordService;
	
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	@POST
	@Path("/saveOrUpdateUser/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean saveOrUpdateUser(User user, @PathParam("token") String token){
		if(user == null){
			throw new V2GenericException("Code-NullUserPassed,Msg-User can not be null");
		}
		
		if(user.getUser() != null && user.getUser().trim().length() == 0){
			throw new V2GenericException("Code-InvalidUserNamePassed,Msg-User can not be blank");
		}
		
		if(user.getBirthdate() != null && user.getBirthdate().trim().length() > 8){
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			try {
				Date date = format.parse(user.getBirthdate());
				user.setBirthday(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new V2GenericException("Code-InvalidBirthDate,Msg-User Birth Date is invalid. Use dd/MM/yyyy format to enter date value");
			}
		}
		User user1 = userService.getSingleUserBySocialMediaType(user);
		if(user1 != null){
			//throw new V2GenericException("Code-UserAlreadyExists");
			//verify the token to see if it is indeed that user who wants to update his personal info
			Date updatedDate = new Date();
			//user.setLastModifiedDate(updatedDate);
			userService.saveOrUpdate(user);
		}
		else{
			Date createdDate = new Date();
			user.setValidated(false);
			//user.setCreatedDate(createdDate);
			userService.saveOrUpdate(user);
			String dateTime = dateFormat.format(createdDate);
			String sentMail = UtilService.getValue("sentMail");
				if(sentMail != null && sentMail.equalsIgnoreCase("true")){
					Thread thread = new Thread(new EmailThread(user.getUser(), dateTime, false, null, user.getSocialMediaType().getType()));
					thread.start();
				}
		}
		return true;
	}
	
	@GET
	@Path("/user/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserFormat(){
		User user = new User();
		return user;
	}
	
	@GET
	@Path("/careerStreams/user/{user}/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public String[] getCareerStreams(@PathParam("token") String token){
		String carStreams[] = new String[]{"Engineering","Medical (MBBS)","Pharmacy","Nursing","Architecture",
				"Law","Design","Hospitality","Management / Business","Journalism / Media","Other",
				"Other Commerce","Other Science","Other Medical","Other"};
		return carStreams;
	}
	
	
	@POST
	@Path("/verifyUser/user/{user}/socialMediaType/{socialMediaType}token/{token}")
	public String verifyUser(@PathParam("user") String user, @PathParam("socialMediaType") String socialMediaType, @PathParam("token") String token){
		try {
			SocialMediaType mediaType = SocialMediaType.valueOf(socialMediaType);
				if(mediaType == null){
					return "Code-InvalidMediaTypeSent";
				}
			
				byte[] verCode = Base64.decodeBase64(user.getBytes());
				String actualUser = new String(verCode);
				
			User usr = userService.getSingleUserBySocialMediaType(new User(user, SocialMediaType.valueOf(socialMediaType)));
				if(usr.isValidated()){
					return "Code-UserAlreadyValidated";
				}
			usr.setValidated(true);
			userService.saveOrUpdate(usr);
			return "Code-Success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Code-UserValidationFailed";
		}
	}
	
	@POST
	@Path("/addFriendsToUser/user/{user}/socialMediaType/{socialMediaType}/token/{token}")
	public String addFriendsToUser(Set<User> users, @PathParam("user") String user, @PathParam("socialMediaType") String socialMediaType, @PathParam("token") String token){
		try {
			SocialMediaType mediaType = SocialMediaType.valueOf(socialMediaType);
			if(mediaType == null){
				return "Code-InvalidMediaTypeSent";
			}
			User user1 = new User(user, mediaType);
			userService.addFriendsToUser(user1, users);
				
			return "Code-Success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Code-AddFriendsFailed";
		}
	}
	
	@GET
	@Path("/retrievePassword/user/{user}/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean retrievePassword(@PathParam("user") String user, @PathParam("token") String token){
		if(user == null ){
			throw new V2GenericException("Code-NullUserPassed,Msg-User can not be null");
		}
		
		if(user.trim().length() == 0){
			throw new V2GenericException("Code-InvalidUserNamePassed,Msg-User can not be blank");
		}
		
		User user1 = userService.getSingleUserBySocialMediaType(new User(user, SocialMediaType.NONE));
		if(user1 == null){
			throw new V2GenericException("Code-UserNotExisting,Msg-User does not exist");
		}
		
		String sentMail = UtilService.getValue("sentMail");
		if(sentMail != null && sentMail.equalsIgnoreCase("true")){
			Date createdDate = user1.getCreatedDate();
			String dateTime = dateFormat.format(createdDate);
			Thread thread = new Thread(new EmailThread(user, dateTime, true, user1.getPassword(), null));
			thread.start();
		}
	return true;
	}
	
	@GET
	@Path("/autoComplete/start/user/{user}/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Keyword> getBooksStartsWith(@PathParam("user") String user, @PathParam("token") String token){
		return keywordService.getAutowireKeywords("a");
	}
	
	@GET
	@Path("/autoComplete/contains/text/{text}/user/{user}/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Keyword> getBooksContains(@PathParam("text") String text, @PathParam("user") String user, @PathParam("token") String token){
		return keywordService.getAutowireKeywordsContains(text);
	}
	
	
	@GET
	@Path("/autoCompleteText/start/user/{user}/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Keyword> getBooksTextStartsWith(@PathParam("user") String user, @PathParam("token") String token){
		return keywordService.getAutowireKeywords("a");
	}
	
	@GET
	@Path("/autoCompleteText/contains/text/{text}/user/{user}/token/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Keyword> getBooksTextContains(@PathParam("text") String text, @PathParam("user") String user, @PathParam("token") String token){
		return keywordService.getAutowireKeywordsContains(text);
	}

}
