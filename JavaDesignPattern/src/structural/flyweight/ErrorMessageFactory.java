package structural.flyweight;

import java.util.HashMap;
import java.util.Map;

//Flyweight factory
public class ErrorMessageFactory {
	
	//key 
	public enum ErrorType {GenericSystemError, PageNotFoundError, ServerError}
	
	private static final ErrorMessageFactory FACTORY = new ErrorMessageFactory();

	public static ErrorMessageFactory getInstance() {
		return FACTORY;
	}
	
	private Map<ErrorType, SystemErrorMessage> errorMessages = new HashMap<>();
	
	private ErrorMessageFactory() {
		//different intrinsic state 
		errorMessages.put(ErrorType.GenericSystemError, 
				new SystemErrorMessage("A generic error of type $errorCode occured.please refer to :\n",
						"http://google.com/q="));
		errorMessages.put(ErrorType.PageNotFoundError, 
				new SystemErrorMessage("Page not found $errorCode occured.please refer to :\n",
						"http://google.com/q="));
	}
	
	
	public SystemErrorMessage getError(ErrorType type) {
		return errorMessages.get(type);
	}
	public UserBannedErrorMessage getUserBannedMessage(String caseId) {
		return new UserBannedErrorMessage(caseId);
	}
	
}
