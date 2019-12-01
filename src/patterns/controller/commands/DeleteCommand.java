package patterns.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;

import model.Address;
import model.User;
import patterns.datamapper.UserDataMapper;
import utils.Constant;

public class DeleteCommand extends Command{
	
	@Override
	public void process() throws ServletException, IOException {
		int result = UserDataMapper.delete(this.request.getParameter("id"));

		if(result > 0) {
			this.request.setAttribute("message", Constant.USER_DELETED);
			this.forward("../index");

		} else {
			this.request.setAttribute("message", Constant.USER_NOT_DELETED);
			this.forward("error");
		}
	}
}
