package py.com.quimpar.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dto.ClientesDTO;

public interface ClientesService {
  List<ClientesDTO> listClientes() throws ServiceException;
  ClientesDTO getCliente(Long id) throws ServiceException;
  ClientesDTO getClienteByRuc(String ruc) throws ServiceException;
  
  @Transactional(rollbackFor = ServiceException.class)
  void deleteCliente(Long id) throws ServiceException;
  
  @Transactional(rollbackFor = ServiceException.class)
  ClientesDTO updateCliente(Long id, ClientesDTO data) throws ServiceException;
  
  @Transactional(rollbackFor = ServiceException.class)
  ClientesDTO createCliente(ClientesDTO data) throws ServiceException;
}