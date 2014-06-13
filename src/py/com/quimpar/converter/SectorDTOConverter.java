package py.com.quimpar.converter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.faces.Converter;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

import py.com.quimpar.persistence.dto.SectorDTO;

@Name("sectorDTOConverter")
@BypassInterceptors
@Converter(forClass = SectorDTOConverter.class)
public class SectorDTOConverter implements javax.faces.convert.Converter, Serializable{


	private static final long serialVersionUID = 6873922061532884850L;
	
	private HashMap<String, SectorDTO> map;
	  
	public SectorDTOConverter(List<SectorDTO> objects) {
		  map = new HashMap<String, SectorDTO>();
		  if (objects != null) {
			    for (SectorDTO o : objects) {
			      map.put(o.getCodigo(), o);
			    }
		  }
	}
	

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return map.get(arg2);
    }

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof SectorDTO) {
			SectorDTO ref = (SectorDTO) arg2;
		    return ref.getCodigo();
		}
		return null;
	}

}
