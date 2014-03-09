package py.com.quimpar.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import py.com.quimpar.exception.ErrorCode;
import py.com.quimpar.exception.ServiceException;

@SuppressWarnings({"unchecked"})
public class JSONTranslator<T> {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private Class<T> clazz;
	
	public JSONTranslator(Class<T> clazz) {	
		this.clazz = clazz;
	}

	public T fromJSON(String jsondata) throws ServiceException {
		try {
			
			if (log.isTraceEnabled()){
				log.trace("converting into dto of type {} from  {}",this.clazz,jsondata);
			}
			
			ObjectMapper mapper = new ObjectMapper();
			T response = (T) mapper.readValue(jsondata, this.clazz);
			return response;
		} catch (JsonGenerationException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		} catch (JsonMappingException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		} catch (IOException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		}
	}	

  public T fromJSON(String jsondata, String datePattern) throws ServiceException {
    try {
      
      if (log.isTraceEnabled()){
        log.trace("converting into dto of type {} from  {}",this.clazz,jsondata);
      }
      
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDateFormat(new SimpleDateFormat(datePattern));
      T response = (T) mapper.readValue(jsondata, this.clazz);
      return response;
    } catch (JsonGenerationException e) {
      throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
    } catch (JsonMappingException e) {
      throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
    } catch (IOException e) {
      throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
    }
  } 
  
	public String toJSON(T instance) throws ServiceException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String response = mapper.writeValueAsString(instance);
			return response;
		} catch (JsonGenerationException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		} catch (JsonMappingException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		} catch (IOException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		}
	}
	
	
	public List<T> toList(String jsondata) throws ServiceException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JavaType t = mapper.getTypeFactory().constructCollectionType(List.class, this.clazz);
			return  (List<T>)mapper.readValue(jsondata, t);
		} catch (JsonGenerationException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		} catch (JsonMappingException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		} catch (IOException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		}
	}
	
	
		
	public String fromList(List<T> list) throws ServiceException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String response = mapper.writeValueAsString(list);
			return response;
		} catch (JsonGenerationException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		} catch (JsonMappingException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		} catch (IOException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		}
	}

	public Map<String, Object> toMap(String jsonString) throws ServiceException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> response = mapper.readValue(jsonString, HashMap.class);
			return response;
		} catch (JsonGenerationException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		} catch (JsonMappingException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		} catch (IOException e) {
			throw new ServiceException(ErrorCode.JSON_ENCODING_ERROR, e);
		}
	}
	
}
