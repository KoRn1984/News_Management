package by.itacademy.matveenko.jd2.controller;

import java.util.HashMap;
import java.util.Map;

import by.itacademy.matveenko.jd2.controller.impl.DoAddNews;
import by.itacademy.matveenko.jd2.controller.impl.DoRegistration;
import by.itacademy.matveenko.jd2.controller.impl.DoSignIn;
import by.itacademy.matveenko.jd2.controller.impl.DoSignOut;
import by.itacademy.matveenko.jd2.controller.impl.GoToAddNewsPage;
import by.itacademy.matveenko.jd2.controller.impl.GoToBasePage;
import by.itacademy.matveenko.jd2.controller.impl.GoToNewsList;
import by.itacademy.matveenko.jd2.controller.impl.GoToViewNews;
import by.itacademy.matveenko.jd2.controller.impl.GoToRegistrationPageCommand;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.DO_SIGN_IN, new DoSignIn());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());		
		commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.GO_TO_ADD_NEWS_PAGE, new GoToAddNewsPage());
        commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
	}
		
	public Command getCommand(String name) {
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		Command command = commands.get(commandName);
		return command;
	}
}