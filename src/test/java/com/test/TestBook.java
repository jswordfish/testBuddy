package com.test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import junit.framework.Assert;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.transport.http.HTTPConduit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.v2tech.domain.Book;
import com.v2tech.repository.BookRepository;
import com.v2tech.services.BookService;
import com.v2tech.services.CareerStreamService;
import com.v2tech.services.ExamService;
import com.v2tech.services.SubjectService;

/**
 * @author brij
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestBook {
@Autowired
BookService bookService;
	


@Autowired
CareerStreamService careerStreamService;

@Autowired
ExamService examService;

@Autowired
SubjectService subjectService;

@Autowired
BookRepository bookRepository;

static List<Object> providers = new ArrayList<Object>();
static WebClient webClient = null;

@Before
	public void setUp() throws Exception {
		try {
			providers.add(new JacksonJsonProvider() );
			//webClient = WebClient.create("http://utilityapplications-socialapp.rhcloud.com/ws/rest/pdfBoxService/pdfToJavaOutput", providers);
			webClient = WebClient.create("http://localhost/v2-booksysN-1.0/ws/rest/bookService/uploadBooksExcel", providers);
			webClient.header("Content-Type", "multipart/form-data");
			webClient.type(MediaType.MULTIPART_FORM_DATA_TYPE);
			HTTPConduit conduit = WebClient.getConfig(webClient).getHttpConduit();
			conduit.getClient().setReceiveTimeout(0);
			webClient.accept("application/json").type("multipart/form-data");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	@Rollback(value=false)
	public void testUploadExcel(){
		try {
		   ContentDisposition cd = new ContentDisposition("attachment;filename=Book_Template_v1.0.xlsx");
		   List<Attachment> atts = new LinkedList<Attachment>();
		   FileInputStream fis = new FileInputStream("Book_Template_v1.0.xlsx");
		   Attachment att = new Attachment("root", fis, cd);
		   atts.add(att);
		   Boolean res =  webClient.post(att, Boolean.class);
		   Assert.assertEquals(true, true);
		} catch(Exception e){
			e.printStackTrace();
			Assert.assertEquals(true, false);
			//logErrorStack(e);
		}	
		
	}

	@Test
	public void testGenericCriteria(){
		String value = "(?i).*lal.*";
		Set<Book> books = bookRepository.searchBooksByGenericKeyword("authors", 5);
			for(Book book : books){
				System.out.println(book.getAuthors());
			}
	}
	
}
