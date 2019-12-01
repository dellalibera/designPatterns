package patterns.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;

import model.Address;
import model.User;
import patterns.datamapper.UserDataMapper;
import utils.Constant;

public class UpdateCommand extends Command{

	@Override
	public void process() throws ServletException, IOException {
		User user = new User(
				this.request.getParameter("id"),
				this.request.getParameter("pwd"),
				this.request.getParameter("name"),
				new Address(this.request.getParameter("street"), this.request.getParameter("postCode"), this.request.getParameter("city")).getFullAddress(),
				this.request.getParameter("bestFriend")
				);
		
		int result = UserDataMapper.update(user);
						
		if(result > 0) {
			String address[] = user.getAddress().split(",");
			this.request.setAttribute("user", user);
			this.request.setAttribute("address", new Address(address[0], address[1], address[2]));
			this.request.setAttribute("message", Constant.USER_UPDATED);
			this.forward("userPage");

		} else {
			this.request.setAttribute("message", Constant.USER_NOT_UPDATED);
			this.forward("error");
		}
	}
}
