package py.com.quimpar.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

	private Logger log = LoggerFactory.getLogger(getClass()); 
	
	/*
	 * map exception error codes to http responses
	 */
	@Override
	public Response toResponse(ServiceException exception) {
		
		log.error("exception caught on web tier",exception);		

		switch (exception.getErrorCode()) {
			case PARAMETER_ERROR:
			case PARSING_ERROR:
			case JSON_DECODING_ERROR:
			case INVALID_REQUEST:
			{
				return Response.status(Status.BAD_REQUEST).entity(exception.toJSON()).build();
			}
			case NO_DATA_FOUND: {
				return Response.status(Status.NOT_FOUND).entity(exception.toJSON()).build();
			}
			case PERSISTENCE_EXCEPTION: {
				return Response.status(Status.BAD_REQUEST).entity(exception.toJSON()).build();
			}
			case UNAUTHORIZED: {
				return Response.status(Status.UNAUTHORIZED).entity(exception.toJSON()).build();
			}
			default: {
				return Response.status(Status.BAD_REQUEST).entity(exception.toJSON()).build();
			}
		}
	}
}
