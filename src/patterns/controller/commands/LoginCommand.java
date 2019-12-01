package patterns.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;

import model.Address;
import model.User;
import patterns.datamapper.UserDataMapper;
import utils.Constant;

public class LoginCommand extends Command{

	@Override
	public void process() throws ServletException, IOException {
		
		String id = this.request.getParameter("id");
		String pwd = this.request.getParameter("pwd");
		
		User user = UserDataMapper.login(id, pwd);
		if(user != null) {
			String address[] = user.getAddress().split(",");
			this.request.setAttribute("user", user);
			this.request.setAttribute("address", new Address(address[0], address[1], address[2]));
			this.forward("userPage");
		} else {
			this.request.setAttribute("message", Constant.ERROR_AUTH);
			this.forward("login");
		}
		
	}

}
