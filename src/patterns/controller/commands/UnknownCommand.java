package patterns.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;

public class UnknownCommand extends Command{

	@Override
	public void process() throws ServletException, IOException {
		this.request.setAttribute("message", "Error: UnknownCommand");
		this.forward("error");
	}
}
