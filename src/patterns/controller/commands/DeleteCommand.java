package patterns.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;

import model.Address;
import model.User;
import patterns.datamapper.UserDataMapper;

public class DeleteCommand extends Command{
	
	@Override
	public void process() throws ServletException, IOException {
		System.out.println("here");
		int result = UserDataMapper.delete(this.request.getParameter("id"));

		if(result > 0) {
			this.request.setAttribute("message", "User deleted");
			this.forward("../index");

		} else {
			this.request.setAttribute("message", "Error: User not deleted");
			this.forward("error");
		}
	}
}
