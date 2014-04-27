package py.com.quimpar.services.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import py.com.quimpar.exception.ErrorCode;
import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dao.ProveedoresDAO;
import py.com.quimpar.persistence.dto.ProveedoresDTO;
import py.com.quimpar.services.ProveedoresService;
public class ProveedoresServiceImpl implements ProveedoresService{

  private ProveedoresDAO proveedoresDAO;

  public void setProveedoresDAO(ProveedoresDAO proveedoresDAO) {
    this.proveedoresDAO = proveedoresDAO;
  }

  @Override
  public List<ProveedoresDTO> listProveedores() throws ServiceException {
    List<ProveedoresDTO> proveedores =  proveedoresDAO.listProveedores();

    if (proveedores == null || proveedores.size() == 0)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"proveedores.list.notfound");
    
    return proveedores;
  }

  @Override
  public ProveedoresDTO getProveedor(Long id) throws ServiceException {
    if (id == null || id <= 0)
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","id");

    ProveedoresDTO proveedor =  proveedoresDAO.getProveedoresById(id);

    if (proveedor == null)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"proveedores.notfound", id);

    return proveedor;
  }

  @Override
  public void deleteProveedor(Long id) throws ServiceException {
    if (id == null || id <= 0)
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","id");

    ProveedoresDTO proveedores =  proveedoresDAO.getProveedoresById(id);

    if(proveedores == null)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"proveedores.notfound", id);

    try {
      proveedoresDAO.deleteProveedores(proveedores);
    }
    catch (DataIntegrityViolationException e){
      throw new ServiceException(ErrorCode.DATA_INTEGRITY_VIOLATION, "proveedores.constraint.violation", id);
    }

  }

  @Override
  public ProveedoresDTO updateProveedor(Long id, ProveedoresDTO data) throws ServiceException {
    if (id == null || id <= 0)
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","id");

    if (null == data || "".equals(data))
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","data");

    ProveedoresDTO proveedor = proveedoresDAO.getProveedoresById(id);

    if (proveedor == null)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"proveedores.notfound", id);

    proveedoresDAO.updateProveedores(proveedor);

    return data;
  }
  @Override
  public ProveedoresDTO createProveedor(ProveedoresDTO data) throws ServiceException {
    if (data == null || "".equals(data))
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","data");

    try {
      proveedoresDAO.createProveedores(data);
    }
    catch (DuplicateKeyException e){
      throw new ServiceException(ErrorCode.DUPLICATE_KEY_EXCEPTION, "proveedores.duplicate.key");
    }

    return data;
  }

}