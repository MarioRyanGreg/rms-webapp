package com.modernlib.author.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modernlib.author.dto.Author;
import com.modernlib.author.dao.AuthorDAOImpl;
/**
 * Servlet implementation class AuthorControllerServlet
 */

public class AuthorControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AuthorDAOImpl authorDAOImpl;
	
	@Override
	public void init() throws ServletException {
		super.init();

		try {
			authorDAOImpl = new AuthorDAOImpl();
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//read the "command" parameter
			String theCommand = request.getParameter("command");
			
            //if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			//route to the appropriate method
			switch (theCommand) {
			
				case "LIST" : listAuthors(request, response);
				              break;
					              
				case "LOAD" : loadAuthor(request, response);
				              break;
				              
				case "UPDATE" : updateAuthor(request, response);
				                break;
				                
				case "DELETE" : deleteAuthor(request, response);
				                break;
				              
				default     :  listAuthors(request, response);
			}	
			
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
					
			// route to the appropriate method
			switch (theCommand) {
							
			case "ADD":
				addAuthor(request, response);
				break;
								
			default:
				listAuthors(request, response);
			}	
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read author id from form data
		String theAuthorId = request.getParameter("authorId");
		
		//delete author from database
		authorDAOImpl.deleteAuthor(theAuthorId);
		
		//send them back to "list students" page
		listAuthors(request, response);
	}

	private void updateAuthor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read author info from form data
		int id = Integer.parseInt(request.getParameter("authorId"));
		String authorName = request.getParameter("authorName");
		String email = request.getParameter("email");
		String createdBy = request.getParameter("createdBy");
		
		//create a new author object
		Author theAuthor = new Author(id, authorName, email, createdBy, "Admin-ModLib");
		
		//perform update on database
		authorDAOImpl.updateAuthor(theAuthor);
		
		//send them back to the "list authors" page 
		listAuthors(request, response);
	}

	private void loadAuthor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read author id from form data
		String theAuthorId = request.getParameter("authorId");
		
		//get author from database (db util)
		Author theAuthor = authorDAOImpl.getAuthor(theAuthorId);
		
		//place author in the request attribute
		request.setAttribute("THE_AUTHOR", theAuthor);
		
		//send to jsp page : update-author-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-author-form.jsp");
		dispatcher.forward(request, response);
	}

	private void addAuthor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read author info into form data
		String authorName = request.getParameter("authorName");
		String email      = request.getParameter("email");
		
		//create a new author object
	    Author theAuthor = new Author(authorName, email, "Admin-ModLib", null);
	    		
		//add the author to the database
	    authorDAOImpl.addAuthor(theAuthor);
		
		//send back to main page (the author list)
	    listAuthors(request, response);
	}

	private void listAuthors(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get authors from db util
		List<Author> authors = authorDAOImpl.getAuthors();

		// add authors to the request
		request.setAttribute("AUTHOR_LIST", authors);

		// send to JSP Page(view);
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/list-author.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

}
