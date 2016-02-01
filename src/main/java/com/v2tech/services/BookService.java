package com.v2tech.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.stereotype.Service;

import com.v2tech.base.V2GenericException;
import com.v2tech.domain.Book;
import com.v2tech.domain.CareerStream;
import com.v2tech.domain.Exam;
import com.v2tech.domain.Source;
import com.v2tech.domain.Subject;
import com.v2tech.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	ExamService examService;
	
	@Autowired
	CareerStreamService careerStreamService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	SourceService sourceService;
	
	public boolean ifBookExists(String isbn){
		java.util.Set<Book> books =  bookRepository.findBookByISBN(isbn);
		if(books.size() == 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Associations are saved irrespective of whether book is in save/update mode.
	 * 
	 * @param book
	 * @return
	 */
	public Book saveOrUpdate(Book book){
		// TODO Auto-generated method stub
		
					if(book.getBookTitle() == null){
						throw new V2GenericException("Book title can not be null");
					}
					
					if(book.getBookTitle().trim().length() < 3){
						throw new V2GenericException("Book title is too small.");
					}
					
					
					if(book.getISBN() == null || book.getISBN().trim().length() < 4){
						throw new V2GenericException("ISBN is either too small or null.");
					}
					
					book.setBookTitle(book.getBookTitle().trim());
					book.setISBN(book.getISBN().trim());
					book.setPublisher(book.getPublisher().trim());
					book.setYear(book.getYear().trim());
					java.util.Set<Book> books =  bookRepository.findBookByISBN(book.getISBN());
					if(books.size() > 1){
						throw new V2GenericException("More than 1 book by same isbn available");
					}
					
					Book bookToBeSaved = null;
					Set<Exam> exams = null;
					Set<CareerStream> careerStreams = null;
					Set<Subject> subjects = null;
					Set<Source> sources = null;
					if(books.size() == 0){
						//create
						bookToBeSaved = book;
						
						
						for(CareerStream cS : book.getCareerStreams()){
							//if(!bookToBeSaved.getcStreams().contains(cS.getStream())){
								bookToBeSaved.setcStreams(cS.getStream());//create mode
							//}
							
						}

						for(Exam ex : book.getRelevantExams()){
							//if(!bookToBeSaved.getrExams().contains(ex.getExam())){
								bookToBeSaved.setrExams(ex.getExam());//create mode
							//}
							
						}

						for(Subject s : book.getRelevantSubjects()){
							//if(!bookToBeSaved.getrSubjects().contains(s.getName())){
								bookToBeSaved.setrSubjects(s.getName());//create mode
							//}
							
						}
						
						for(Source s : book.getSources()){
							//if(!bookToBeSaved.getSrcs().contains(s.getSource())){
								bookToBeSaved.setSrcs(s.getSource());//create mode
							//}
						}
					}
					else{
						
						//update
						Book bookArray[] = new Book[books.size()];
						bookToBeSaved = (books.toArray(bookArray))[0];
						Long id = bookToBeSaved.getId();
//						Mapper mapper = new DozerBeanMapper();
//						mapper.map(book, bookToBeSaved);
						bookToBeSaved.setAuthors(book.getAuthors());
						bookToBeSaved.setBookImage(book.getBookImage());
						bookToBeSaved.setBookTitle(book.getBookTitle());
						bookToBeSaved.setBookTp(book.getBookTp());
						//bookToBeSaved.setcStreams(book.getcStreams());
						bookToBeSaved.setCurrency(book.getCurrency());
						bookToBeSaved.setDetailPageURL(book.getDetailPageURL());
						bookToBeSaved.setEdition(book.getEdition());
						bookToBeSaved.setFormat(book.getFormat());
						bookToBeSaved.setISBN(book.getISBN());
							if(!bookToBeSaved.getKeyword().contains(book.getKeyword())){
								bookToBeSaved.setKeyword(bookToBeSaved.getKeyword()+", "+book.getKeyword());
							}
						
						bookToBeSaved.setKeyword(bookToBeSaved.getKeyword()+", "+book.getKeyword());
						bookToBeSaved.setLargeImageUrl(book.getLargeImageUrl());
						bookToBeSaved.setMediumImageUrl(book.getMediumImageUrl());
						bookToBeSaved.setMrp(book.getMrp());
						bookToBeSaved.setNumberOfPages(book.getNumberOfPages());
						bookToBeSaved.setProviderUniqueIdentifier(book.getProviderUniqueIdentifier());
							if(!bookToBeSaved.getPublisher().contains(book.getPublisher())){
								bookToBeSaved.setPublisher(bookToBeSaved.getPublisher()+", "+book.getPublisher());
							}
						
						//bookToBeSaved.setrExams(book.getrExams());
						//bookToBeSaved.setrSubjects(book.getrSubjects());
						bookToBeSaved.setSmallImageUrl(book.getSmallImageUrl());
						//bookToBeSaved.setSrcs(book.getSrcs());
						bookToBeSaved.setStrTableOfContents(book.getStrTableOfContents());
						bookToBeSaved.setYear(book.getYear());
						
							if(!bookToBeSaved.getInstitute().contains(book.getInstitute())){
								if(!(bookToBeSaved.getInstitute().trim().length() == 0)){
									bookToBeSaved.setInstitute(bookToBeSaved.getInstitute()+", "+book.getInstitute());
								}
								else{
									bookToBeSaved.setInstitute(book.getInstitute());
								}
								
							}
							
//						Set<CareerStream> streams = new HashSet<CareerStream>();
							for(CareerStream cS : book.getCareerStreams()){
								if(!bookToBeSaved.getcStreams().contains(cS.getStream())){
									bookToBeSaved.setcStreams(bookToBeSaved.getcStreams()+", "+cS.getStream());
								}
								
							}
//						streams.addAll(book.getCareerStreams());
						
						//bookToBeSaved.setCareerStreams(book.getCareerStreams());
						//bookToBeSaved.setCareerStreams(streams);
						
//						Set<Exam> exms = new HashSet<Exam>();
							for(Exam ex : book.getRelevantExams()){
								if(!bookToBeSaved.getrExams().contains(ex.getExam())){
									bookToBeSaved.setrExams(bookToBeSaved.getrExams()+", "+ex.getExam());
								}
								
							}
//						exms.addAll(book.getRelevantExams());
						//bookToBeSaved.setRelevantExams(book.getRelevantExams());
						//bookToBeSaved.setRelevantExams(exms);
						
//						Set<Subject> sbjs = new HashSet<Subject>();
							for(Subject s : book.getRelevantSubjects()){
								if(!bookToBeSaved.getrSubjects().contains(s.getName())){
									bookToBeSaved.setrSubjects(bookToBeSaved.getrSubjects()+", "+s.getName());
								}
								
							}
//							sbjs.addAll(book.getRelevantSubjects());
							
							for(Source s : book.getSources()){
								if(!bookToBeSaved.getSrcs().contains(s.getSource())){
									bookToBeSaved.setSrcs(bookToBeSaved.getSrcs()+", "+s.getSource());
								}
							}
						bookToBeSaved.setId(id);
						
					}
					//saveAllAssociations(bookToBeSaved);//no need now
					removeAllAssociations(bookToBeSaved);//making sure we don't use any associations
					bookToBeSaved = bookRepository.save(bookToBeSaved);
					return bookToBeSaved;
				
	}
	
	private void removeAllAssociations(Book book){
		book.setCareerStreams(new HashSet<CareerStream>());
		book.setRelevantExams(new HashSet<Exam>());
		book.setRelevantSubjects(new HashSet<Subject>());
		book.setSources(new HashSet<Source>());
	}
	
	private void saveAllAssociations(Book book){
		Set<Exam> exams = book.getRelevantExams();
		Set<CareerStream> careerStreams = book.getCareerStreams();
		Set<Subject> subjects = book.getRelevantSubjects();
		Set<Source> sources = book.getSources();
		
			for(Exam exam : exams){
				exam = examService.saveOrUpdateExam(exam);
			}
			
			for(CareerStream careerStream: careerStreams){
				careerStream = careerStreamService.saveOrUpdateCareerStream(careerStream);
			}
			
			for(Subject subject : subjects){
				subject = subjectService.saveOrUpdateSubject(subject);
			}
			
			for(Source source : sources){
				source = sourceService.saveOrUpdateSource(source);
			}
	}
	
	public Book getSingleBook(String isbn){
		java.util.Set<Book> books =  bookRepository.findBookByISBN(isbn);
		if(books.size() > 1){
			throw new V2GenericException("More than 1 book by same isbn available");
		}
		else if(books.size() == 0){
			return null;
		}
		else{
			Book bookArray[] = new Book[books.size()];
			return (books.toArray(bookArray))[0];
		}
	}
	
	public Set<Book> searchBooksByKeyword(String keyword, Integer limit){
		return bookRepository.searchBooksByKeyword(keyword, limit);
	}
	
	public Set<Book> searchBooksByGenericKeyword(String keyword, Integer limit){
		return bookRepository.searchBooksByGenericKeyword(keyword, limit);
	}
	
	public Set<Book> searchBooksByCareerStreamAndSubject(String careerStream, String subject, Integer limit){
		return bookRepository.searchBooksByCareerStreamAndSubject(careerStream, subject, limit);
	}
	
	public Set<Book> searchBooksByCareerStreamAndExam(String careerStream, String exam, Integer limit){
		return bookRepository.searchBooksByCareerStreamAndExam(careerStream, exam, limit);
	}
	
	public Set<Book> searchBooksBySubjectAndExam(String subject, String exam, Integer limit){
		return bookRepository.searchBooksBySubjectAndExam(subject, exam, limit);
	}
	
	public Set<Book> searchBooksByInstitutionTypeAndSubject(String institution, String subject, Integer limit){
		return bookRepository.searchBooksByInstitutionAndSubject(institution, subject, limit);
	}
	
	
	public Set<Book> searchBooksByBookTitle(String bookTitle,  Integer limit){
		return bookRepository.findBooksByBookTitle(bookTitle, limit);
	}
	
	public Set<Book> searchBooksByISBN(String isbn,  Integer limit){
		return bookRepository.findBookByISBN(isbn);
	}
	
	public void deleteAllNodes(){
		bookRepository.deleteAllNodes();
	}
	
	public long countBooks(){
		return bookRepository.count();
	}
	
	
	public Set<Book> searchBooksByAuthor(String author, Integer limit) {
		return bookRepository.searchBooksByAuthor(author, limit);
	}
	
	
	public Set<Book> searchBooksByCareerStream(String careerstream, Integer limit) {
		return bookRepository.searchBooksByCareerStream(careerstream, limit);
	}
	
	
	public Set<Book> searchBooksByPublisher(String publisher, Integer limit) {
		return bookRepository.searchBooksByPublisher(publisher, limit);
	}
	
	
	public Set<Book> searchBooksByYear(String year, Integer limit) {
		return bookRepository.searchBooksByYear(year, limit);
	}
}
