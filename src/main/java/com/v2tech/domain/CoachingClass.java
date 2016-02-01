package com.v2tech.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class CoachingClass {
	
	@GraphId
	private Long id;
	
	String name;
	
	
	
	/**
	 * Comma separated
	 */
	String cStreams = "";
	
	/**
	 * Comma sepaarted
	 */
	String rExams = "";
	
	String city;
	
	String yearFounded;
	
	/**
	 * Comma sepaarted
	 */
	String typesOfCoursesOffered = "";
	
	String typeOfProgram;
	
	String website;
	
	String phoneNumber;
	
	Integer averageBatchSize;
	
	/**
	 * Comma sepaarted
	 */
	String juniorCollegesPartnerShip = "";
	
	String location;
	
	String address;
	
	String schedule;
	
	byte[] image;
	
	String summary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getcStreams() {
		return cStreams;
	}

	public void setcStreams(String cStreams) {
		this.cStreams = cStreams;
	}

	public String getrExams() {
		return rExams;
	}

	public void setrExams(String rExams) {
		this.rExams = rExams;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(String yearFounded) {
		this.yearFounded = yearFounded;
	}

	public String getTypesOfCoursesOffered() {
		return typesOfCoursesOffered;
	}

	public void setTypesOfCoursesOffered(String typesOfCoursesOffered) {
		this.typesOfCoursesOffered = typesOfCoursesOffered;
	}

	public String getTypeOfProgram() {
		return typeOfProgram;
	}

	public void setTypeOfProgram(String typeOfProgram) {
		this.typeOfProgram = typeOfProgram;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getAverageBatchSize() {
		return averageBatchSize;
	}

	public void setAverageBatchSize(Integer averageBatchSize) {
		this.averageBatchSize = averageBatchSize;
	}

	public String getJuniorCollegesPartnerShip() {
		return juniorCollegesPartnerShip;
	}

	public void setJuniorCollegesPartnerShip(String juniorCollegesPartnerShip) {
		this.juniorCollegesPartnerShip = juniorCollegesPartnerShip;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
}
