package py.com.quimpar.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.ext.Provider;

import net.sf.oval.ConstraintViolation;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.context.ApplicationContext;

import py.com.quimpar.config.QuimparApplicationContextProvider;
import py.com.quimpar.utils.JSONTranslator;

@Provider
public class ServiceException extends Exception {
	private static final long serialVersionUID = -1417575380801996290L;
	private ErrorCode errorCode;
	private List<ConstraintViolation> violations;
	private Locale locale;
	private static final String DEFAULT_MESSAGE = "NO SE ENCUENTRA LA DESCRIPCION DEL ERROR";
	
	public ServiceException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public ServiceException(ErrorCode errorCode, String message, Throwable cause) {
		super(QuimparApplicationContextProvider.getContext()
        .getMessage(message, null , DEFAULT_MESSAGE, Locale.getDefault()).toUpperCase(), cause);
		this.errorCode = errorCode;
	}
	
	public ServiceException(ErrorCode errorCode, String message) {
		super(QuimparApplicationContextProvider.getContext()
        .getMessage(message, null , DEFAULT_MESSAGE, Locale.getDefault()).toUpperCase());
		this.errorCode = errorCode;
	}
	
	public ServiceException(ErrorCode errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public ServiceException(ErrorCode errorCode, Throwable cause, String message, Object...args) {
		super(QuimparApplicationContextProvider.getContext()
		    .getMessage(message, args , DEFAULT_MESSAGE, Locale.getDefault()).toUpperCase(), cause);
		this.errorCode = errorCode;
	}
	
	public ServiceException(ErrorCode errorCode, String message, Object...args) {
		super(QuimparApplicationContextProvider.getContext()
        .getMessage(message, args , DEFAULT_MESSAGE, new Locale("es_ES")).toUpperCase());
		this.errorCode = errorCode;
	}

	
	public ServiceException(List<ConstraintViolation> violations){
		this(violations,Locale.getDefault());
	}
	
	public ServiceException(List<ConstraintViolation> violations, Locale locale){
		this(ErrorCode.VALIDATION_FAILED,"validation did not succeed");
		this.violations = violations;
		this.locale = locale;
	}
	

  public ErrorCode getErrorCode(){
    return this.errorCode;
  }
  
  public String getErrorMessage(){
    StringBuilder sb = new StringBuilder();
    sb.append(getErrorCode()).append("=").append(this.getMessage()).append("\n");
    return sb.toString();
  }
  
	public String toJSON(){
		JSONError jse = new JSONError();
		jse.setCauseString(this.getCause() == null ? "" : this.getCause().toString());
		jse.setErrorKey(this.errorCode.toString());
		jse.setErrorMessage(this.getMessage() == null ? "" : this.getMessage());
		
		
		if (this.violations != null && this.violations.size() > 0){			
			ApplicationContext ctx = QuimparApplicationContextProvider.getContext();
			ArrayList<String> messages = new ArrayList<String>();
			for(ConstraintViolation c : this.violations){
				//1 - create an array containing useful information to build messages with
				List<Object> args = new ArrayList<Object>();
				if (c.getMessageVariables() != null && c.getMessageVariables().size() > 0) {					
					args.addAll(c.getMessageVariables().values());
				}
				args.add(c.getInvalidValue());
				
				//2 - obtener el mensaje adecuado si corresponde				
				messages.add(ctx.getMessage(c.getMessageTemplate(), args.toArray() , c.getMessage(), locale));
			}			
			jse.setMessages(messages.toArray(new String[messages.size()]));			
		}
		
		JSONTranslator<JSONError> t = new JSONTranslator<ServiceException.JSONError>(ServiceException.JSONError.class);
		
		try {
			return t.toJSON(jse);
		} catch (ServiceException e) {
			throw new RuntimeException("FATAL: cannot convert ServiceException into JSONError from within a ServiceException",e);
		}
	}	
	
	@JsonSerialize(include=Inclusion.NON_EMPTY)
	static final class JSONError {
		private String errorKey;
		private String errorMessage;		
		private String causeString;
		private String messages[];
		
		public String getErrorKey() {
			return errorKey;
		}
		public void setErrorKey(String errorKey) {
			this.errorKey = errorKey;
		}
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		public String getCauseString() {
			return causeString;
		}
		public void setCauseString(String causeString) {
			this.causeString = causeString;
		}
		public String[] getMessages() {
			return messages;
		}
		public void setMessages(String[] messages) {
			this.messages = messages;
		}		
	}
}
