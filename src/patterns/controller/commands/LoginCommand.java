package patterns.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;

import model.Address;
import model.User;
import patterns.datamapper.UserDataMapper;

public class LoginCommand extends Command{

	@Override
	public void process() throws ServletException, IOException {
		
		String id = this.request.getParameter("id");
		String pwd = this.request.getParameter("pwd");
		
		User user = UserDataMapper.login(id, pwd);

		if(user != null) {
			this.request.setAttribute("user", user);
			this.forward("userPage");
		} else {
			this.request.setAttribute("message", "Errod during authentication");
			this.forward("error");
		}
		
	}

}
