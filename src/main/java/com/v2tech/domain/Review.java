package com.v2tech.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Review {
	
@GraphId
private Long id;

@RelatedTo(type = "REVIEW_FOR", direction = Direction.OUTGOING)
ResourceUnderReview resourceUnderReview;
/**
 * Typically people want top rated resources by criteria
 */
String criteria;

String resourceLink = "";
	
String userName = "";

String resourceName = "";
/**
 * Incase of books
 */
String resourceTitle = "";

String bookIsbn = "";

String resourceType = RESOURCE_TYPE.BOOK.getType();

String coachingClassName = "";

String coachingClassLocation = "";

String scopeOfCriteriaText;

String depthOfCoverageText;

String effectivenessAndEaseOfCommunicationText;

String learningAidesText;

String practiceProblemsText;

String solvedExamplesText;

String solutionToPracticeProblemsText;

String visualToolsText;

String realWorldExamplesText;

String overAllText;

String howToBestUseBookText;

String suggestionsImprovementText;

Integer scopeOfCriteria = -1;

Integer depthOfCoverage = -1;

Integer effectivenessAndEaseOfCommunication = -1;

Integer learningAides = -1;

Integer practiceProblems = -1;

Integer solvedExamples = -1;

Integer solutionToPracticeProblems = -1;

Integer visualTools = -1;

Integer realWorldExamples = -1;

Integer overAll = -1;

Integer howToBestUseBook = -1;

Integer suggestionsImprovement = -1;
/**
 * Coaching class attributes
 */
String instructionQualityText;

String mathsText;

String physicsText;

String chemistryText;

String instructorsText;

String comfortInApproachingInstructorsText;

String instructorsAbleToUnderstandAndAddressDoubtsText;

String instructorsKeepingStudentsEngagedText;

String studyMaterialsText;

String chapterNotesText;

String instructorPracticeProblemsText;

String cheatSheetText;

String personalizationText;

String provideIndividualFeedbackText;

String organizeDoubtSolvingSessionText;

String recognizeStrongAndWeakAreasStudentText;

String infrastructureText;

String happyWithClassRoomInfrastructureText;

String happyWithInfrastructureOusideClassRoomText;

String coachingClassOverAllText;

String howToBestUseCoachingClassText;

String coachingClassSuggestionForImprovementText;

Integer instructionQuality = -1;

Integer maths = -1;

Integer physics = -1;

Integer chemistry = -1;

Integer instructors = -1;

Integer comfortInApproachingInstructors = -1;

Integer instructorsAbleToUnderstandAndAddressDoubts = -1;

Integer instructorsKeepingStudentsEngaged = -1;

Integer studyMaterials = -1;

Integer chapterNotes = -1;

Integer instructorPracticeProblems = -1;

Integer cheatSheet = -1;

Integer personalization = -1;

Integer provideIndividualFeedback = -1;

Integer organizeDoubtSolvingSession = -1;

Integer recognizeStrongAndWeakAreasStudent = -1;

Integer infrastructure = -1;

Integer happyWithClassRoomInfrastructure = -1;

Integer happyWithInfrastructureOusideClassRoom = -1;

Integer coachingClassOverAll = -1;

Integer howToBestUseCoachingClass = -1;

Integer coachingClassSuggestionForImprovement = -1;

/**
 * Digital Tools attributes
 * @return
 */

String digitalToolStudyMaterialsText;

String digitalToolChapterNotesText;

String digitalToolpracticeProblemsText;

String digitalToolCheatSheetsText;

String digitalToolPersonalizationText;

String digitalToolFeedbackAnalysisText;

String digitalToolTimeSavingStrategyText;

String digitalToolAccessToExpertsText;

String digitalToolEaseOfUseText;

String digitalToolReceiveTimelyHelpText;

String digitalToolReliableInternetConnectionText;

String digitalToolOverAllText;

String digitalToolHowToBestUseText;

String digitalToolSuggestionsForImprovementText;

Integer digitalToolStudyMaterials = -1;

Integer digitalToolChapterNotes = -1;

Integer digitalToolpracticeProblems = -1;

Integer digitalToolCheatSheets = -1;

Integer digitalToolPersonalization = -1;

Integer digitalToolFeedbackAnalysis = -1;

Integer digitalToolTimeSavingStrategy = -1;

Integer digitalToolAccessToExperts = -1;

Integer digitalToolEaseOfUse = -1;

Integer digitalToolReceiveTimelyHelp = -1;

Integer digitalToolReliableInternetConnection = -1;

Integer digitalToolOverAll = -1;

Integer digitalToolHowToBestUse = -1;

Integer digitalToolSuggestionsForImprovement = -1;

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getBookIsbn() {
	return bookIsbn;
}

public void setBookIsbn(String bookIsbn) {
	this.bookIsbn = bookIsbn;
}

public String getScopeOfCriteriaText() {
	return scopeOfCriteriaText;
}

public void setScopeOfCriteriaText(String scopeOfCriteriaText) {
	this.scopeOfCriteriaText = scopeOfCriteriaText;
}

public String getDepthOfCoverageText() {
	return depthOfCoverageText;
}

public void setDepthOfCoverageText(String depthOfCoverageText) {
	this.depthOfCoverageText = depthOfCoverageText;
}

public String getEffectivenessAndEaseOfCommunicationText() {
	return effectivenessAndEaseOfCommunicationText;
}

public void setEffectivenessAndEaseOfCommunicationText(
		String effectivenessAndEaseOfCommunicationText) {
	this.effectivenessAndEaseOfCommunicationText = effectivenessAndEaseOfCommunicationText;
}

public String getLearningAidesText() {
	return learningAidesText;
}

public void setLearningAidesText(String learningAidesText) {
	this.learningAidesText = learningAidesText;
}

public String getPracticeProblemsText() {
	return practiceProblemsText;
}

public void setPracticeProblemsText(String practiceProblemsText) {
	this.practiceProblemsText = practiceProblemsText;
}

public String getSolvedExamplesText() {
	return solvedExamplesText;
}

public void setSolvedExamplesText(String solvedExamplesText) {
	this.solvedExamplesText = solvedExamplesText;
}

public String getSolutionToPracticeProblemsText() {
	return solutionToPracticeProblemsText;
}

public void setSolutionToPracticeProblemsText(
		String solutionToPracticeProblemsText) {
	this.solutionToPracticeProblemsText = solutionToPracticeProblemsText;
}

public String getVisualToolsText() {
	return visualToolsText;
}

public void setVisualToolsText(String visualToolsText) {
	this.visualToolsText = visualToolsText;
}

public String getRealWorldExamplesText() {
	return realWorldExamplesText;
}

public void setRealWorldExamplesText(String realWorldExamplesText) {
	this.realWorldExamplesText = realWorldExamplesText;
}

public String getOverAllText() {
	return overAllText;
}

public void setOverAllText(String overAllText) {
	this.overAllText = overAllText;
}

public String getHowToBestUseBookText() {
	return howToBestUseBookText;
}

public void setHowToBestUseBookText(String howToBestUseBookText) {
	this.howToBestUseBookText = howToBestUseBookText;
}

public String getSuggestionsImprovementText() {
	return suggestionsImprovementText;
}

public void setSuggestionsImprovementText(String suggestionsImprovementText) {
	this.suggestionsImprovementText = suggestionsImprovementText;
}

public Integer getScopeOfCriteria() {
	return scopeOfCriteria;
}

public void setScopeOfCriteria(Integer scopeOfCriteria) {
	this.scopeOfCriteria = scopeOfCriteria;
}

public Integer getDepthOfCoverage() {
	return depthOfCoverage;
}

public void setDepthOfCoverage(Integer depthOfCoverage) {
	this.depthOfCoverage = depthOfCoverage;
}

public Integer getEffectivenessAndEaseOfCommunication() {
	return effectivenessAndEaseOfCommunication;
}

public void setEffectivenessAndEaseOfCommunication(
		Integer effectivenessAndEaseOfCommunication) {
	this.effectivenessAndEaseOfCommunication = effectivenessAndEaseOfCommunication;
}

public Integer getLearningAides() {
	return learningAides;
}

public void setLearningAides(Integer learningAides) {
	this.learningAides = learningAides;
}

public Integer getPracticeProblems() {
	return practiceProblems;
}

public void setPracticeProblems(Integer practiceProblems) {
	this.practiceProblems = practiceProblems;
}

public Integer getSolvedExamples() {
	return solvedExamples;
}

public void setSolvedExamples(Integer solvedExamples) {
	this.solvedExamples = solvedExamples;
}

public Integer getSolutionToPracticeProblems() {
	return solutionToPracticeProblems;
}

public void setSolutionToPracticeProblems(Integer solutionToPracticeProblems) {
	this.solutionToPracticeProblems = solutionToPracticeProblems;
}

public Integer getVisualTools() {
	return visualTools;
}

public void setVisualTools(Integer visualTools) {
	this.visualTools = visualTools;
}

public Integer getRealWorldExamples() {
	return realWorldExamples;
}

public void setRealWorldExamples(Integer realWorldExamples) {
	this.realWorldExamples = realWorldExamples;
}

public Integer getOverAll() {
	return overAll;
}

public void setOverAll(Integer overAll) {
	this.overAll = overAll;
}

public Integer getHowToBestUseBook() {
	return howToBestUseBook;
}

public void setHowToBestUseBook(Integer howToBestUseBook) {
	this.howToBestUseBook = howToBestUseBook;
}

public Integer getSuggestionsImprovement() {
	return suggestionsImprovement;
}

public void setSuggestionsImprovement(Integer suggestionsImprovement) {
	this.suggestionsImprovement = suggestionsImprovement;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getResourceType() {
	return resourceType;
}

public void setResourceType(String resourceType) {
	this.resourceType = resourceType;
}

public String getInstructionQualityText() {
	return instructionQualityText;
}

public void setInstructionQualityText(String instructionQualityText) {
	this.instructionQualityText = instructionQualityText;
}

public String getMathsText() {
	return mathsText;
}

public void setMathsText(String mathsText) {
	this.mathsText = mathsText;
}

public String getPhysicsText() {
	return physicsText;
}

public void setPhysicsText(String physicsText) {
	this.physicsText = physicsText;
}

public String getChemistryText() {
	return chemistryText;
}

public void setChemistryText(String chemistryText) {
	this.chemistryText = chemistryText;
}

public String getInstructorsText() {
	return instructorsText;
}

public void setInstructorsText(String instructorsText) {
	this.instructorsText = instructorsText;
}

public String getComfortInApproachingInstructorsText() {
	return comfortInApproachingInstructorsText;
}

public void setComfortInApproachingInstructorsText(
		String comfortInApproachingInstructorsText) {
	this.comfortInApproachingInstructorsText = comfortInApproachingInstructorsText;
}

public String getInstructorsAbleToUnderstandAndAddressDoubtsText() {
	return instructorsAbleToUnderstandAndAddressDoubtsText;
}

public void setInstructorsAbleToUnderstandAndAddressDoubtsText(
		String instructorsAbleToUnderstandAndAddressDoubtsText) {
	this.instructorsAbleToUnderstandAndAddressDoubtsText = instructorsAbleToUnderstandAndAddressDoubtsText;
}

public String getInstructorsKeepingStudentsEngagedText() {
	return instructorsKeepingStudentsEngagedText;
}

public void setInstructorsKeepingStudentsEngagedText(
		String instructorsKeepingStudentsEngagedText) {
	this.instructorsKeepingStudentsEngagedText = instructorsKeepingStudentsEngagedText;
}

public String getStudyMaterialsText() {
	return studyMaterialsText;
}

public void setStudyMaterialsText(String studyMaterialsText) {
	this.studyMaterialsText = studyMaterialsText;
}

public String getChapterNotesText() {
	return chapterNotesText;
}

public void setChapterNotesText(String chapterNotesText) {
	this.chapterNotesText = chapterNotesText;
}

public String getInstructorPracticeProblemsText() {
	return instructorPracticeProblemsText;
}

public void setInstructorPracticeProblemsText(
		String instructorPracticeProblemsText) {
	this.instructorPracticeProblemsText = instructorPracticeProblemsText;
}

public String getCheatSheetText() {
	return cheatSheetText;
}

public void setCheatSheetText(String cheatSheetText) {
	this.cheatSheetText = cheatSheetText;
}

public String getPersonalizationText() {
	return personalizationText;
}

public void setPersonalizationText(String personalizationText) {
	this.personalizationText = personalizationText;
}

public String getProvideIndividualFeedbackText() {
	return provideIndividualFeedbackText;
}

public void setProvideIndividualFeedbackText(
		String provideIndividualFeedbackText) {
	this.provideIndividualFeedbackText = provideIndividualFeedbackText;
}

public String getOrganizeDoubtSolvingSessionText() {
	return organizeDoubtSolvingSessionText;
}

public void setOrganizeDoubtSolvingSessionText(
		String organizeDoubtSolvingSessionText) {
	this.organizeDoubtSolvingSessionText = organizeDoubtSolvingSessionText;
}

public String getRecognizeStrongAndWeakAreasStudentText() {
	return recognizeStrongAndWeakAreasStudentText;
}

public void setRecognizeStrongAndWeakAreasStudentText(
		String recognizeStrongAndWeakAreasStudentText) {
	this.recognizeStrongAndWeakAreasStudentText = recognizeStrongAndWeakAreasStudentText;
}

public String getInfrastructureText() {
	return infrastructureText;
}

public void setInfrastructureText(String infrastructureText) {
	this.infrastructureText = infrastructureText;
}

public String getHappyWithClassRoomInfrastructureText() {
	return happyWithClassRoomInfrastructureText;
}

public void setHappyWithClassRoomInfrastructureText(
		String happyWithClassRoomInfrastructureText) {
	this.happyWithClassRoomInfrastructureText = happyWithClassRoomInfrastructureText;
}

public String getHappyWithInfrastructureOusideClassRoomText() {
	return happyWithInfrastructureOusideClassRoomText;
}

public void setHappyWithInfrastructureOusideClassRoomText(
		String happyWithInfrastructureOusideClassRoomText) {
	this.happyWithInfrastructureOusideClassRoomText = happyWithInfrastructureOusideClassRoomText;
}

public String getCoachingClassOverAllText() {
	return coachingClassOverAllText;
}

public void setCoachingClassOverAllText(String coachingClassOverAllText) {
	this.coachingClassOverAllText = coachingClassOverAllText;
}

public String getHowToBestUseCoachingClassText() {
	return howToBestUseCoachingClassText;
}

public void setHowToBestUseCoachingClassText(
		String howToBestUseCoachingClassText) {
	this.howToBestUseCoachingClassText = howToBestUseCoachingClassText;
}

public String getCoachingClassSuggestionForImprovementText() {
	return coachingClassSuggestionForImprovementText;
}

public void setCoachingClassSuggestionForImprovementText(
		String coachingClassSuggestionForImprovementText) {
	this.coachingClassSuggestionForImprovementText = coachingClassSuggestionForImprovementText;
}

public Integer getInstructionQuality() {
	return instructionQuality;
}

public void setInstructionQuality(Integer instructionQuality) {
	this.instructionQuality = instructionQuality;
}

public Integer getMaths() {
	return maths;
}

public void setMaths(Integer maths) {
	this.maths = maths;
}

public Integer getPhysics() {
	return physics;
}

public void setPhysics(Integer physics) {
	this.physics = physics;
}

public Integer getChemistry() {
	return chemistry;
}

public void setChemistry(Integer chemistry) {
	this.chemistry = chemistry;
}

public Integer getInstructors() {
	return instructors;
}

public void setInstructors(Integer instructors) {
	this.instructors = instructors;
}

public Integer getComfortInApproachingInstructors() {
	return comfortInApproachingInstructors;
}

public void setComfortInApproachingInstructors(
		Integer comfortInApproachingInstructors) {
	this.comfortInApproachingInstructors = comfortInApproachingInstructors;
}

public Integer getInstructorsAbleToUnderstandAndAddressDoubts() {
	return instructorsAbleToUnderstandAndAddressDoubts;
}

public void setInstructorsAbleToUnderstandAndAddressDoubts(
		Integer instructorsAbleToUnderstandAndAddressDoubts) {
	this.instructorsAbleToUnderstandAndAddressDoubts = instructorsAbleToUnderstandAndAddressDoubts;
}

public Integer getInstructorsKeepingStudentsEngaged() {
	return instructorsKeepingStudentsEngaged;
}

public void setInstructorsKeepingStudentsEngaged(
		Integer instructorsKeepingStudentsEngaged) {
	this.instructorsKeepingStudentsEngaged = instructorsKeepingStudentsEngaged;
}

public Integer getStudyMaterials() {
	return studyMaterials;
}

public void setStudyMaterials(Integer studyMaterials) {
	this.studyMaterials = studyMaterials;
}

public Integer getChapterNotes() {
	return chapterNotes;
}

public void setChapterNotes(Integer chapterNotes) {
	this.chapterNotes = chapterNotes;
}

public Integer getInstructorPracticeProblems() {
	return instructorPracticeProblems;
}

public void setInstructorPracticeProblems(Integer instructorPracticeProblems) {
	this.instructorPracticeProblems = instructorPracticeProblems;
}

public Integer getCheatSheet() {
	return cheatSheet;
}

public void setCheatSheet(Integer cheatSheet) {
	this.cheatSheet = cheatSheet;
}

public Integer getPersonalization() {
	return personalization;
}

public void setPersonalization(Integer personalization) {
	this.personalization = personalization;
}

public Integer getProvideIndividualFeedback() {
	return provideIndividualFeedback;
}

public void setProvideIndividualFeedback(Integer provideIndividualFeedback) {
	this.provideIndividualFeedback = provideIndividualFeedback;
}

public Integer getOrganizeDoubtSolvingSession() {
	return organizeDoubtSolvingSession;
}

public void setOrganizeDoubtSolvingSession(Integer organizeDoubtSolvingSession) {
	this.organizeDoubtSolvingSession = organizeDoubtSolvingSession;
}

public Integer getRecognizeStrongAndWeakAreasStudent() {
	return recognizeStrongAndWeakAreasStudent;
}

public void setRecognizeStrongAndWeakAreasStudent(
		Integer recognizeStrongAndWeakAreasStudent) {
	this.recognizeStrongAndWeakAreasStudent = recognizeStrongAndWeakAreasStudent;
}

public Integer getInfrastructure() {
	return infrastructure;
}

public void setInfrastructure(Integer infrastructure) {
	this.infrastructure = infrastructure;
}

public Integer getHappyWithClassRoomInfrastructure() {
	return happyWithClassRoomInfrastructure;
}

public void setHappyWithClassRoomInfrastructure(
		Integer happyWithClassRoomInfrastructure) {
	this.happyWithClassRoomInfrastructure = happyWithClassRoomInfrastructure;
}

public Integer getHappyWithInfrastructureOusideClassRoom() {
	return happyWithInfrastructureOusideClassRoom;
}

public void setHappyWithInfrastructureOusideClassRoom(
		Integer happyWithInfrastructureOusideClassRoom) {
	this.happyWithInfrastructureOusideClassRoom = happyWithInfrastructureOusideClassRoom;
}

public Integer getCoachingClassOverAll() {
	return coachingClassOverAll;
}

public void setCoachingClassOverAll(Integer coachingClassOverAll) {
	this.coachingClassOverAll = coachingClassOverAll;
}

public Integer getHowToBestUseCoachingClass() {
	return howToBestUseCoachingClass;
}

public void setHowToBestUseCoachingClass(Integer howToBestUseCoachingClass) {
	this.howToBestUseCoachingClass = howToBestUseCoachingClass;
}

public Integer getCoachingClassSuggestionForImprovement() {
	return coachingClassSuggestionForImprovement;
}

public void setCoachingClassSuggestionForImprovement(
		Integer coachingClassSuggestionForImprovement) {
	this.coachingClassSuggestionForImprovement = coachingClassSuggestionForImprovement;
}

public String getDigitalToolStudyMaterialsText() {
	return digitalToolStudyMaterialsText;
}

public void setDigitalToolStudyMaterialsText(
		String digitalToolStudyMaterialsText) {
	this.digitalToolStudyMaterialsText = digitalToolStudyMaterialsText;
}

public String getDigitalToolChapterNotesText() {
	return digitalToolChapterNotesText;
}

public void setDigitalToolChapterNotesText(String digitalToolChapterNotesText) {
	this.digitalToolChapterNotesText = digitalToolChapterNotesText;
}

public String getDigitalToolpracticeProblemsText() {
	return digitalToolpracticeProblemsText;
}

public void setDigitalToolpracticeProblemsText(
		String digitalToolpracticeProblemsText) {
	this.digitalToolpracticeProblemsText = digitalToolpracticeProblemsText;
}

public String getDigitalToolCheatSheetsText() {
	return digitalToolCheatSheetsText;
}

public void setDigitalToolCheatSheetsText(String digitalToolCheatSheetsText) {
	this.digitalToolCheatSheetsText = digitalToolCheatSheetsText;
}

public String getDigitalToolPersonalizationText() {
	return digitalToolPersonalizationText;
}

public void setDigitalToolPersonalizationText(
		String digitalToolPersonalizationText) {
	this.digitalToolPersonalizationText = digitalToolPersonalizationText;
}

public String getDigitalToolFeedbackAnalysisText() {
	return digitalToolFeedbackAnalysisText;
}

public void setDigitalToolFeedbackAnalysisText(
		String digitalToolFeedbackAnalysisText) {
	this.digitalToolFeedbackAnalysisText = digitalToolFeedbackAnalysisText;
}

public String getDigitalToolTimeSavingStrategyText() {
	return digitalToolTimeSavingStrategyText;
}

public void setDigitalToolTimeSavingStrategyText(
		String digitalToolTimeSavingStrategyText) {
	this.digitalToolTimeSavingStrategyText = digitalToolTimeSavingStrategyText;
}

public String getDigitalToolAccessToExpertsText() {
	return digitalToolAccessToExpertsText;
}

public void setDigitalToolAccessToExpertsText(
		String digitalToolAccessToExpertsText) {
	this.digitalToolAccessToExpertsText = digitalToolAccessToExpertsText;
}

public String getDigitalToolEaseOfUseText() {
	return digitalToolEaseOfUseText;
}

public void setDigitalToolEaseOfUseText(String digitalToolEaseOfUseText) {
	this.digitalToolEaseOfUseText = digitalToolEaseOfUseText;
}

public String getDigitalToolReceiveTimelyHelpText() {
	return digitalToolReceiveTimelyHelpText;
}

public void setDigitalToolReceiveTimelyHelpText(
		String digitalToolReceiveTimelyHelpText) {
	this.digitalToolReceiveTimelyHelpText = digitalToolReceiveTimelyHelpText;
}

public String getDigitalToolReliableInternetConnectionText() {
	return digitalToolReliableInternetConnectionText;
}

public void setDigitalToolReliableInternetConnectionText(
		String digitalToolReliableInternetConnectionText) {
	this.digitalToolReliableInternetConnectionText = digitalToolReliableInternetConnectionText;
}

public String getDigitalToolOverAllText() {
	return digitalToolOverAllText;
}

public void setDigitalToolOverAllText(String digitalToolOverAllText) {
	this.digitalToolOverAllText = digitalToolOverAllText;
}

public String getDigitalToolHowToBestUseText() {
	return digitalToolHowToBestUseText;
}

public void setDigitalToolHowToBestUseText(String digitalToolHowToBestUseText) {
	this.digitalToolHowToBestUseText = digitalToolHowToBestUseText;
}

public String getDigitalToolSuggestionsForImprovementText() {
	return digitalToolSuggestionsForImprovementText;
}

public void setDigitalToolSuggestionsForImprovementText(
		String digitalToolSuggestionsForImprovementText) {
	this.digitalToolSuggestionsForImprovementText = digitalToolSuggestionsForImprovementText;
}

public Integer getDigitalToolStudyMaterials() {
	return digitalToolStudyMaterials;
}

public void setDigitalToolStudyMaterials(Integer digitalToolStudyMaterials) {
	this.digitalToolStudyMaterials = digitalToolStudyMaterials;
}

public Integer getDigitalToolChapterNotes() {
	return digitalToolChapterNotes;
}

public void setDigitalToolChapterNotes(Integer digitalToolChapterNotes) {
	this.digitalToolChapterNotes = digitalToolChapterNotes;
}

public Integer getDigitalToolpracticeProblems() {
	return digitalToolpracticeProblems;
}

public void setDigitalToolpracticeProblems(Integer digitalToolpracticeProblems) {
	this.digitalToolpracticeProblems = digitalToolpracticeProblems;
}

public Integer getDigitalToolCheatSheets() {
	return digitalToolCheatSheets;
}

public void setDigitalToolCheatSheets(Integer digitalToolCheatSheets) {
	this.digitalToolCheatSheets = digitalToolCheatSheets;
}

public Integer getDigitalToolPersonalization() {
	return digitalToolPersonalization;
}

public void setDigitalToolPersonalization(Integer digitalToolPersonalization) {
	this.digitalToolPersonalization = digitalToolPersonalization;
}

public Integer getDigitalToolFeedbackAnalysis() {
	return digitalToolFeedbackAnalysis;
}

public void setDigitalToolFeedbackAnalysis(Integer digitalToolFeedbackAnalysis) {
	this.digitalToolFeedbackAnalysis = digitalToolFeedbackAnalysis;
}

public Integer getDigitalToolTimeSavingStrategy() {
	return digitalToolTimeSavingStrategy;
}

public void setDigitalToolTimeSavingStrategy(
		Integer digitalToolTimeSavingStrategy) {
	this.digitalToolTimeSavingStrategy = digitalToolTimeSavingStrategy;
}

public Integer getDigitalToolAccessToExperts() {
	return digitalToolAccessToExperts;
}

public void setDigitalToolAccessToExperts(Integer digitalToolAccessToExperts) {
	this.digitalToolAccessToExperts = digitalToolAccessToExperts;
}

public Integer getDigitalToolEaseOfUse() {
	return digitalToolEaseOfUse;
}

public void setDigitalToolEaseOfUse(Integer digitalToolEaseOfUse) {
	this.digitalToolEaseOfUse = digitalToolEaseOfUse;
}

public Integer getDigitalToolReceiveTimelyHelp() {
	return digitalToolReceiveTimelyHelp;
}

public void setDigitalToolReceiveTimelyHelp(Integer digitalToolReceiveTimelyHelp) {
	this.digitalToolReceiveTimelyHelp = digitalToolReceiveTimelyHelp;
}

public Integer getDigitalToolReliableInternetConnection() {
	return digitalToolReliableInternetConnection;
}

public void setDigitalToolReliableInternetConnection(
		Integer digitalToolReliableInternetConnection) {
	this.digitalToolReliableInternetConnection = digitalToolReliableInternetConnection;
}

public Integer getDigitalToolOverAll() {
	return digitalToolOverAll;
}

public void setDigitalToolOverAll(Integer digitalToolOverAll) {
	this.digitalToolOverAll = digitalToolOverAll;
}

public Integer getDigitalToolHowToBestUse() {
	return digitalToolHowToBestUse;
}

public void setDigitalToolHowToBestUse(Integer digitalToolHowToBestUse) {
	this.digitalToolHowToBestUse = digitalToolHowToBestUse;
}

public Integer getDigitalToolSuggestionsForImprovement() {
	return digitalToolSuggestionsForImprovement;
}

public void setDigitalToolSuggestionsForImprovement(
		Integer digitalToolSuggestionsForImprovement) {
	this.digitalToolSuggestionsForImprovement = digitalToolSuggestionsForImprovement;
}

public ResourceUnderReview getResourceUnderReview() {
	return resourceUnderReview;
}

public void setResourceUnderReview(ResourceUnderReview resourceUnderReview) {
	this.resourceUnderReview = resourceUnderReview;
}

public String getCoachingClassName() {
	return coachingClassName;
}

public void setCoachingClassName(String coachingClassName) {
	this.coachingClassName = coachingClassName;
}

public String getCoachingClassLocation() {
	return coachingClassLocation;
}

public void setCoachingClassLocation(String coachingClassLocation) {
	this.coachingClassLocation = coachingClassLocation;
}

public String getResourceName() {
	return resourceName;
}

public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
}

public String getResourceLink() {
	return resourceLink;
}

public void setResourceLink(String resourceLink) {
	this.resourceLink = resourceLink;
}

public String getCriteria() {
	return criteria;
}

public void setCriteria(String criteria) {
	this.criteria = criteria;
}

public String getResourceTitle() {
	return resourceTitle;
}

public void setResourceTitle(String resourceTitle) {
	this.resourceTitle = resourceTitle;
}



}
