package patterns.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DB;
import patterns.controller.commands.Command;
import patterns.controller.commands.UnknownCommand;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB.getInstance();
		String commandName = String.format("patterns.controller.commands.%sCommand", request.getParameter("command"));
		
		System.out.println(String.format("Command %s received", commandName));
		Command command;
		
		try {
			command = (Command) Class.forName(commandName).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			command = new UnknownCommand();
			System.out.println(e.getMessage());
		}
		
		command.init(getServletContext(), request, response);
		command.process();
	}

}
