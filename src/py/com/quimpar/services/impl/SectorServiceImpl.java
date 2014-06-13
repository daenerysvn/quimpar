package py.com.quimpar.services.impl;

import java.util.List;

import py.com.quimpar.exception.ErrorCode;
import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dao.SectorDAO;
import py.com.quimpar.persistence.dto.SectorDTO;
import py.com.quimpar.services.SectorService;

public class SectorServiceImpl implements SectorService {

	private SectorDAO sectorDAO;

    public void setSectorDAO(SectorDAO sectorDAO) {
	    this.sectorDAO = sectorDAO;
	}
	
	@Override
	public List<SectorDTO> listSectores() throws ServiceException {
		List<SectorDTO> sectores =  sectorDAO.listSector();

	    if (sectores == null || sectores.size() == 0)
	      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"sector.list.notfound");

	    return sectores;
	}

}
