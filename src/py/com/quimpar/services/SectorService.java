/**
 * 
 */
package py.com.quimpar.services;

import java.util.List;

import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dto.SectorDTO;

/**
 * @author bea
 *
 */
public interface SectorService {

	List<SectorDTO> listSectores() throws ServiceException;
}
