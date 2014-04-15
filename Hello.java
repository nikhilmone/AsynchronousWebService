package com.test.nikhil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/hello")
public class Hello {

	public Hello() {
		// TODO Auto-generated constructor stub
	}
	
	// This method is called if TEXT_PLAIN is request
	  @GET
	  @Path("/text")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() throws InterruptedException {
		Thread.sleep(600000);  
	    return "Hello There";
	  }

	  // This method is called if XML is request
	  @GET
	  @Path("/xml")
	  @Produces(MediaType.TEXT_XML)
	  public String sayXMLHello() {
	    return "<?xml version=\"1.0\"?>" + "<hello> Hello There" + "</hello>";
	  }

	  // This method is called if HTML is request
	  @GET
	  @Path("/html")
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
	    return "<html> " + "<title>" + "Hello There" + "</title>"
	        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
	  }


}
