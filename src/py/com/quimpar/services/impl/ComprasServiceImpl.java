package py.com.quimpar.services.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import py.com.quimpar.exception.ErrorCode;
import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dao.ComprasDAO;
import py.com.quimpar.persistence.dao.DetalleComprasDAO;
import py.com.quimpar.persistence.dto.ComprasDTO;
import py.com.quimpar.persistence.dto.DetalleComprasDTO;
import py.com.quimpar.services.ComprasService;
public class ComprasServiceImpl implements ComprasService{

  private ComprasDAO comprasDAO;
  private DetalleComprasDAO detalleComprasDAO;

  public void setComprasDAO(ComprasDAO comprasDAO) {
    this.comprasDAO = comprasDAO;
  }
  
  public void setDetalleComprasDAO(DetalleComprasDAO detalleComprasDAO) {
		this.detalleComprasDAO = detalleComprasDAO;
  }
  

@Override
public List<ComprasDTO> listCompras(HashMap<String, Object> param)
		throws ServiceException {
	List<ComprasDTO> compras =  comprasDAO.listCompras(param);

    if (compras == null || compras.size() == 0)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"compras.list.notfound");

    return compras;
}

@Override
public ComprasDTO createCompras(ComprasDTO data) throws ServiceException {
    if (data == null || "".equals(data))
        throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","data");

      try {
    	  comprasDAO.createCompras(data);
    	  System.out.println("id:"+data.getId());
    	  if (data.getId()!=null){
    		  for (DetalleComprasDTO detalle:data.getDetalles()){
    			  detalle.setIdCompra(data.getId());
    			  detalle.setIdProducto(detalle.getProducto().getId());
    			  detalleComprasDAO.createDetalleCompras(detalle);
    		  }
    	  }
    	  
      }
      catch (DuplicateKeyException e){
        throw new ServiceException(ErrorCode.DUPLICATE_KEY_EXCEPTION, "compras.duplicate.key");
      }
      catch(Exception e){
    	  System.out.println("exception"+e.getMessage());
    	  throw new ServiceException(ErrorCode.PERSISTENCE_EXCEPTION, "compras.no.se"); 
      }

      return data;
}

@Override
public List<DetalleComprasDTO> getDetalles(Long idCompra)throws ServiceException {
	List<DetalleComprasDTO> detalles =  detalleComprasDAO.getDetallesByCabecera(idCompra);

    if (detalles == null || detalles.size() == 0)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"compras.list.notfound");

    return detalles;
}


}