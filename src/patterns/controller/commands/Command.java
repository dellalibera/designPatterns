package patterns.controller.commands;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DB;

public abstract class Command {
	
	protected ServletContext sc;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public void init(ServletContext sc, HttpServletRequest request, HttpServletResponse response){
		this.sc = sc;
		this.request = request;
		this.response = response;
	}
	
	public void forward(String page) throws ServletException, IOException{
		this.sc.getRequestDispatcher(String.format("/jsp/%s.jsp", page)).forward(this.request, this.response);	
	}
	
	public abstract void process() throws ServletException, IOException;


}
