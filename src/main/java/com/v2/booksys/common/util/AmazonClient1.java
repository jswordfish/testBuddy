package com.v2.booksys.common.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.v2tech.domain.Book;
import com.v2tech.domain.CareerStream;
import com.v2tech.domain.Exam;
import com.v2tech.domain.Subject;
import com.v2tech.services.BookService;

public class AmazonClient1 {
	static org.slf4j.Logger logger = LoggerFactory.getLogger(AmazonClient.class);
	private static final String SECRET_KEY = "4QhAQO5rDHqjYQRDjvgDg/GR7r038zpX3d2ZngvW";
    private static final String AWS_KEY = "AKIAIHYXAS2JB4ND2N6A";
    private static final String ASSOCIATE_TAG = "academ058-20";
    static SignedRequestsHelper helper = null;
    static BookService bookService = null;
    static int count = 0;

    public static void main(String[] args) throws InvalidKeyException, IllegalArgumentException, NoSuchAlgorithmException, IOException {
    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:appContext.xml");
    	bookService = (BookService) ctx.getBean("bookService");
    	helper = SignedRequestsHelper.getInstance("webservices.amazon.in", AWS_KEY, SECRET_KEY);
        Map<String, String> params = new HashMap<String, String>();
        params.put("Version", "2011-08-01");
        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemSearch");
        params.put("AWSAccessKeyId", AWS_KEY);
        params.put("AssociateTag", ASSOCIATE_TAG);
        params.put("SearchIndex", "Books");
        params.put("Sort", "price");
        params.put("ResponseGroup", "ItemAttributes, Images");
        params.put("Availability", "Available");
        //params.put("Keywords", "Engineering");
        params.put("ItemPage", "10");
       // params.put("Keyword", "Test");
        Map<CareerStream,Subject> map = UtilService.getCareerStreamSubjectKeywords();
        fetchByCareerStreamSubject(map, params);
        
        Map<String,Subject> map2 = UtilService.getInstitutionTypeSubjectKeywords();
        fetchByInstitutionTypeSubject(map2, params);
    }
    
    public static void fetchByCareerStreamSubject(Map<CareerStream, Subject> data, Map<String, String> params){
    	Set<CareerStream> cS = data.keySet();
    	
    	for(CareerStream careerStream : cS){
    		Subject subject = data.get(careerStream);
    		
    		String keyword = careerStream.getStream()+" "+subject.getName();
    		process(params, keyword, careerStream, subject);
    	}

        
    }
    
    public static void fetchByInstitutionTypeSubject(Map<String, Subject> data, Map<String, String> params){
    	Set<String> types = data.keySet();
    	
    	for(String iType : types){
    		Subject subject = data.get(iType);
    		
    		String keyword = iType+" "+subject.getName();
    		process(params, keyword, iType, subject);
    	}

        
    }
    
    public static void fetchBySubjectExam(Map<Subject, Exam> data, Map<String, String> params){
    	Set<Subject> subjects = data.keySet();
    	
    	for(Subject subject : subjects){
    		Exam exam = data.get(subject);
    		
    		String keyword = subject.getName()+" "+exam.getExam();
    		process(params, keyword, subject, exam);
    	}

        
    }
    
    static void process(Map<String, String> params, String keyword, Subject subject, Exam exam){
    	try {
     		System.out.println("keyword "+keyword);
     		params.put("Keywords", keyword);
     		String url = helper.sign(params);
     		HashMap map = new HashMap();
           	String uri = "http://webservices.amazon.com/AWSECommerceService/2011-08-01";
           	map.put( "aws", uri);
           	DocumentFactory.getInstance().setXPathNamespaceURIs( map);
           	/**
           	 * Get total number of pages got for a search result
           	 */
         	org.dom4j.Document document = retrieveDocumentResponse(url, map);

         	Node nodeP = document.selectSingleNode("//aws:Items");
         	Node totalPages = nodeP.selectSingleNode("//aws:TotalPages");
         		if(totalPages != null){
         			if(totalPages.getText() != null){
         					if(totalPages.getText() == null){
         						System.out.println("Something wrong no total pages got");
         					}
         				System.out.println("Total PAges "+totalPages.getText());
         				int totPages = Integer.parseInt(totalPages.getText());
         				int pages = (totPages > 10) ? 10: totPages;
         				/**
                       	 * For every page got, retrieve the individual items/books and save them in d/b.
                       	 */
         				for(int page = 1; page <= pages; page++ ){
         					params.put("ItemPage", ""+page);
         					url = helper.sign(params);
         					document = retrieveDocumentResponse(url, map);
         					nodeP = document.selectSingleNode("//aws:Items");
         					List<Book> books = convertPageResultsIntoBooks(nodeP, map, bookService, keyword, false);
         					for(Book book: books){
         						book.getRelevantExams().add(exam);
         						book.getRelevantSubjects().add(subject);
         						bookService.saveOrUpdate(book);
         					}
         				}
         			}
         			else{
         				System.out.println("No search yielded for above keyword");
         			}
         		}
         		
     	}
     	catch(Exception e){
 			e.printStackTrace();
 		}
    }
    
    static void process(Map<String, String> params, String keyword, CareerStream careerStream, Subject subject){
    	try {
     		System.out.println("keyword "+keyword);
     		params.put("Keywords", keyword);
     		String url = helper.sign(params);
     		HashMap map = new HashMap();
           	String uri = "http://webservices.amazon.com/AWSECommerceService/2011-08-01";
           	map.put( "aws", uri);
           	DocumentFactory.getInstance().setXPathNamespaceURIs( map);
           	/**
           	 * Get total number of pages got for a search result
           	 */
         	org.dom4j.Document document = retrieveDocumentResponse(url, map);

         	Node nodeP = document.selectSingleNode("//aws:Items");
         	Node totalPages = nodeP.selectSingleNode("//aws:TotalPages");
         		if(totalPages != null){
         			if(totalPages.getText() != null){
         					if(totalPages.getText() == null){
         						System.out.println("Something wrong no total pages got");
         					}
         				System.out.println("Total PAges "+totalPages.getText());
         				int totPages = Integer.parseInt(totalPages.getText());
         				int pages = (totPages > 10) ? 10: totPages;
         				/**
                       	 * For every page got, retrieve the individual items/books and save them in d/b.
                       	 */
         				for(int page = 1; page <= pages; page++ ){
         					params.put("ItemPage", ""+page);
         					url = helper.sign(params);
         					document = retrieveDocumentResponse(url, map);
         					nodeP = document.selectSingleNode("//aws:Items");
         					List<Book> books = convertPageResultsIntoBooks(nodeP, map, bookService, keyword, false);
         					for(Book book: books){
         						book.getCareerStreams().add(careerStream);
         						book.getRelevantSubjects().add(subject);
         						bookService.saveOrUpdate(book);
         					}
         				}
         			}
         			else{
         				System.out.println("No search yielded for above keyword");
         			}
         		}
         		
     	}
     	catch(Exception e){
 			e.printStackTrace();
 		}
    }
    
    static void process(Map<String, String> params, String keyword, String insTitutionType, Subject subject){
    	try {
     		System.out.println("keyword "+keyword);
     		params.put("Keywords", keyword);
     		String url = helper.sign(params);
     		HashMap map = new HashMap();
           	String uri = "http://webservices.amazon.com/AWSECommerceService/2011-08-01";
           	map.put( "aws", uri);
           	DocumentFactory.getInstance().setXPathNamespaceURIs( map);
           	/**
           	 * Get total number of pages got for a search result
           	 */
         	org.dom4j.Document document = retrieveDocumentResponse(url, map);

         	Node nodeP = document.selectSingleNode("//aws:Items");
         	Node totalPages = nodeP.selectSingleNode("//aws:TotalPages");
         		if(totalPages != null){
         			if(totalPages.getText() != null){
         					if(totalPages.getText() == null){
         						System.out.println("Something wrong no total pages got");
         					}
         				System.out.println("Total PAges "+totalPages.getText());
         				int totPages = Integer.parseInt(totalPages.getText());
         				int pages = (totPages > 10) ? 10: totPages;
         				/**
                       	 * For every page got, retrieve the individual items/books and save them in d/b.
                       	 */
         				for(int page = 1; page <= pages; page++ ){
         					params.put("ItemPage", ""+page);
         					url = helper.sign(params);
         					document = retrieveDocumentResponse(url, map);
         					nodeP = document.selectSingleNode("//aws:Items");
         					List<Book> books = convertPageResultsIntoBooks(nodeP, map, bookService, keyword, false);
         					for(Book book: books){
         						book.setInstitute(insTitutionType);
//         							
         						
         						book.getRelevantSubjects().add(subject);
         						bookService.saveOrUpdate(book);
         					}
         				}
         			}
         			else{
         				System.out.println("No search yielded for above keyword");
         			}
         		}
         		
     	}
     	catch(Exception e){
 			e.printStackTrace();
 		}
    }
    
    public static List<Book> convertPageResultsIntoBooks(Node nodeItems, Map map, BookService bookService, String keyword, boolean saveBook){
    	/**
    	 * Since amazon has a restriction on continuous hitting.
    	 */
    		try{
    			Thread.sleep(2500);
    		}
    		catch(Exception e){
    			
    		}
    	List<Book> books = new ArrayList<>();
    	List<Node> nodes = nodeItems.selectNodes("//aws:Item");
    	
    	for(Node node : nodes){
    		count++;
    		Book book = new Book();
    		book.setKeyword(keyword);
    		Element itm = (Element) node;
    		org.dom4j.XPath nameXpathDetailPageURL = node.createXPath("./aws:DetailPageURL");
    		nameXpathDetailPageURL.setNamespaceURIs(map);
    		Node DetailPageURL = nameXpathDetailPageURL.selectSingleNode(node);
    		if(DetailPageURL != null){
    			book.setDetailPageURL(DetailPageURL.getText());
    		}
    		
    		org.dom4j.XPath nameXpathASIN = node.createXPath("./aws:ASIN");
    		nameXpathASIN.setNamespaceURIs(map);
    		Node ASIN = nameXpathASIN.selectSingleNode(node);
    		if(ASIN!= null){
    			book.setProviderUniqueIdentifier("Amazon-"+ASIN.getText());
    		}
    		
    		org.dom4j.XPath nameXpathSmallImageURL = node.createXPath("./aws:SmallImage/aws:URL");
    		nameXpathSmallImageURL.setNamespaceURIs(map);
    		Node SmallImageURL = nameXpathSmallImageURL.selectSingleNode(node);
    			if(SmallImageURL != null){
    				book.setSmallImageUrl(SmallImageURL.getText());
    			}
    		
    		org.dom4j.XPath nameXpathMediumImageURL = node.createXPath("./aws:MediumImage/aws:URL");
    		nameXpathMediumImageURL.setNamespaceURIs(map);
    		Node MediumImageURL = nameXpathMediumImageURL.selectSingleNode(node);
    			if(MediumImageURL!= null){
    				book.setMediumImageUrl(MediumImageURL.getText());
    			}
    		
    		org.dom4j.XPath nameXpathLargeImageURL = node.createXPath("./aws:LargeImage/aws:URL");
    		nameXpathLargeImageURL.setNamespaceURIs(map);
    		Node LargeImage = nameXpathLargeImageURL.selectSingleNode(node);
    			if(LargeImage != null){
    				book.setLargeImageUrl(LargeImage.getText());
    			}
    		
    		org.dom4j.XPath nameXpathAuthor = node.createXPath("./aws:ItemAttributes/aws:Author");
    		nameXpathAuthor.setNamespaceURIs(map);
    		Node Author = nameXpathAuthor.selectSingleNode(node);
    			if(Author != null){
    				book.setAuthors(Author.getText());
    			}
    		
    		org.dom4j.XPath nameXpathFormat = node.createXPath("./aws:ItemAttributes/aws:Format");
    		nameXpathFormat.setNamespaceURIs(map);
    		Node Format = nameXpathFormat.selectSingleNode(node);
    			if(Format != null){
    				book.setFormat(Format.getText());
    			}
    		
    		org.dom4j.XPath nameXpathNumberOfPages = node.createXPath("./aws:ItemAttributes/aws:NumberOfPages");
    		nameXpathNumberOfPages.setNamespaceURIs(map);
    		Node NumberOfPages = nameXpathNumberOfPages.selectSingleNode(node);
    			if(NumberOfPages != null && NumberOfPages.getText() != null){
    				book.setNumberOfPages(Integer.parseInt(NumberOfPages.getText()));
    			}
    		
    		org.dom4j.XPath nameXpathPublicationDate = node.createXPath("./aws:ItemAttributes/aws:PublicationDate");
    		nameXpathPublicationDate.setNamespaceURIs(map);
    		Node PublicationDate = nameXpathPublicationDate.selectSingleNode(node);
    			if(PublicationDate != null){
    				book.setYear(PublicationDate.getText());
    			}
    		
    		org.dom4j.XPath nameXpathTitle = node.createXPath("./aws:ItemAttributes/aws:Title");
    		nameXpathTitle.setNamespaceURIs(map);
    		Node Title = nameXpathTitle.selectSingleNode(node);
    			if(Title != null){
    				book.setBookTitle(Title.getText());
    			}
    		
    		org.dom4j.XPath nameXpathISBN = node.createXPath("./aws:ItemAttributes/aws:ISBN");
    		nameXpathISBN.setNamespaceURIs(map);
    		Node ISBN = nameXpathISBN.selectSingleNode(node);
    			if(ISBN != null){
    				book.setISBN("ISBN-"+ISBN.getText());
    			}
    		
    		org.dom4j.XPath nameXpathEISBN = node.createXPath("./aws:ItemAttributes/aws:EISBN");
    		nameXpathEISBN.setNamespaceURIs(map);
    		Node EISBN = nameXpathEISBN.selectSingleNode(node);
    			if(EISBN != null){
    				book.setISBN("EISBN-"+EISBN.getText());
    			}
    		
    		org.dom4j.XPath nameXpathEdition = node.createXPath("./aws:ItemAttributes/aws:Edition");
    		nameXpathEdition.setNamespaceURIs(map);
    		Node Edition = nameXpathEdition.selectSingleNode(node);
    			if(Edition != null){
    				book.setEdition(Edition.getText());
    			}
    		
    		if(book.getISBN() != null){
    			if(book.getISBN().trim().length() < 2){
    				/**
    				 * Both isbn and eisbn attrs as got from amazon seem to be null.
    				 * So lets assign title as a identity to this book
    				 */
    				book.setISBN("Title-"+book.getBookTitle());
    				System.out.println("Assigning title as identity for book in isbn as both isbn and eisbn are not exisiting");
    			}
    		}
    		else{
    			/**
				 * Both isbn and eisbn attrs as got from amazon seem to be null.
				 * So lets assign title as a identity to this book
				 */
				book.setISBN("Title-"+book.getBookTitle());
				System.out.println("Assigning title as identity for book in isbn as both isbn and eisbn are not exisiting");

    		}
    	books.add(book);
    		if(saveBook){
    			bookService.saveOrUpdate(book);
    		}
    	
    	//System.out.println("bo")
    	}
    return books;
    }

  public static Document retrieveDocumentResponse(String url, Map map) throws DocumentException{
	SAXReader reader = new SAXReader();
  	org.dom4j.Document document = reader.read(url); 
  	return document;
  }

public static SignedRequestsHelper getHelper() {
	return helper;
}

public static void setHelper(SignedRequestsHelper helper) {
	AmazonClient1.helper = helper;
}

public static BookService getBookService() {
	return bookService;
}

public static void setBookService(BookService bookService) {
	AmazonClient1.bookService = bookService;
}
  
  
}
