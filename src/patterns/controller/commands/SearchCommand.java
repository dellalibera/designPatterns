package patterns.controller.commands;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import model.Address;
import model.User;
import patterns.datamapper.UserDataMapper;
import utils.Constant;

public class SearchCommand extends Command{


	@Override
	public void process() throws ServletException, IOException {
		
		String name = this.request.getParameter("name");
		String address = this.request.getParameter("address");
		String bestFriend = this.request.getParameter("bestFriend");
		
		System.out.println(name + ", " + address +"," + bestFriend);
		ArrayList<User> users = UserDataMapper.search(name, address, bestFriend);
		
		if(users != null && users.size() > 0) {
			this.request.setAttribute("users", users);
			this.forward("search");
		} else {
			this.request.setAttribute("message", Constant.NO_RESULT);
			this.forward("search");
		}
		
	}
}
