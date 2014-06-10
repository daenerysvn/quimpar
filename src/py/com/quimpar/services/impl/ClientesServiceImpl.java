package py.com.quimpar.services.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import py.com.quimpar.exception.ErrorCode;
import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dao.ClientesDAO;
import py.com.quimpar.persistence.dto.ClientesDTO;
import py.com.quimpar.services.ClientesService;
public class ClientesServiceImpl implements ClientesService{

  private ClientesDAO clientesDAO;

  public void setClientesDAO(ClientesDAO clientesDAO) {
    this.clientesDAO = clientesDAO;
  }

  @Override
  public List<ClientesDTO> listClientes() throws ServiceException {
    List<ClientesDTO> clientes =  clientesDAO.listClientes();

    if (clientes == null || clientes.size() == 0)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"clientes.list.notfound");

    return clientes;
  }

  @Override
  public ClientesDTO getCliente(Long id) throws ServiceException {
    if (id == null || id <= 0)
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","id");

    ClientesDTO cliente =  clientesDAO.getClientesById(id);

    if (cliente == null)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"cliente.notfound", id);

    return cliente;
  }
  
  @Override
  public ClientesDTO getClienteByRuc(String ruc) throws ServiceException {
	if (ruc == null || "".equals(ruc))
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","ruc");

	ClientesDTO cliente =  clientesDAO.getClientesByRuc(ruc);

    if (cliente == null)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"cliente.notfound", ruc);

    return cliente;
  }

  @Override
  public void deleteCliente(Long id) throws ServiceException {
    if (id == null || id <= 0)
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","id");

    ClientesDTO clientes =  clientesDAO.getClientesById(id);

    if(clientes == null)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"cliente.notfound", id);

    try {
      clientesDAO.deleteClientes(clientes);
    }
    catch (DataIntegrityViolationException e){
      throw new ServiceException(ErrorCode.DATA_INTEGRITY_VIOLATION, "clientes.constraint.violation", id);
    }

  }

  @Override
  public ClientesDTO updateCliente(Long id, ClientesDTO data) throws ServiceException {
    if (id == null || id <= 0)
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","id");

    if (null == data || "".equals(data))
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","data");

    ClientesDTO cliente = clientesDAO.getClientesById(id);

    clientesDAO.updateClientes(cliente);

    return data;
  }
  @Override
  public ClientesDTO createCliente(ClientesDTO data) throws ServiceException {
    if (data == null || "".equals(data))
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","data");

    try {
      clientesDAO.createClientes(data);
    }
    catch (DuplicateKeyException e){
      throw new ServiceException(ErrorCode.DUPLICATE_KEY_EXCEPTION, "clientes.duplicate.key");
    }

    return data;
  }

}