package by.itacademy.matveenko.jd2.validation.impl;

import by.itacademy.matveenko.jd2.bean.NewUserInfo;
import by.itacademy.matveenko.jd2.service.ServiceException;
import by.itacademy.matveenko.jd2.util.validation.UserDataValidation;

public class UserDataValidationImpl implements UserDataValidation{

	private final String SYMBOL_COMMERCIAL_AT = "@";
	private final String SYMBOL_DOT = ".";
	
	@Override
	public boolean checkAuthDataLogination(String login, String password){
		boolean result = false;
		if (login != null & password != null) {
			result = true;
			} 		
		return result;
	}
	
	@Override
	public boolean checkAuthDataRegistration(NewUserInfo user) throws ServiceException {
		boolean result = false;
		if (nameExists(user) & surNameExists(user) & loginExists(user) & passwordExists (user) & emailIsCorrect (user)) {
			result = true;
			}		  
		return result;
	}

	public boolean nameExists (NewUserInfo user) throws ServiceException {
		boolean result = false;
		if (user.getUserName() != null) {
			result = true;	   
		}
		else {
			throw new ServiceException("Name not entered!"); 
		   }		   
		return result;
	}
	
	public boolean surNameExists (NewUserInfo user) throws ServiceException {
		boolean result = false;
		if (user.getUserSurname() != null) {
			result = true;	   
		}
		else {
			throw new ServiceException("Surname not entered!"); 
		}		  		
		return result;
	}
	
	public boolean loginExists (NewUserInfo user) throws ServiceException {
		boolean result = false;
		if (user.getLogin() != null) {
			result = true;	   
		}
		else {
			throw new ServiceException("Login not entered!"); 
		}		  		
		return result;
	}
	
	public boolean passwordExists (NewUserInfo user) throws ServiceException {
		boolean result = false;
		if (user.getPassword() != null) {
			result = true;	   
		}
		else {
			throw new ServiceException("Password not entered!"); 
		}		  		
		return result;
	}
	
	public boolean emailIsCorrect (NewUserInfo user) throws ServiceException {
		boolean result = false;
		if (emailExists(user) & isEmailRight (user)) {
			result = true;	   
		}
		return result;
	}
	
	public boolean emailExists (NewUserInfo user) throws ServiceException {
		boolean result = false;
		if (user.getEmail() != null) {
			result = true;	   
		}
		else {
			throw new ServiceException("Email not entered!"); 
		   }		  		
		return result;
	}
	
	public boolean isEmailRight (NewUserInfo user) throws ServiceException {
		boolean result = false;
		if (user.getEmail().contains(SYMBOL_COMMERCIAL_AT) && user.getEmail().contains(SYMBOL_DOT)) {
			result = true;
			}
		else {
            throw new ServiceException("Email entered incorrectly!"); 
 		}            
		return result;
	}
}