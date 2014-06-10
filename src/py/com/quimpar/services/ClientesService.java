package py.com.quimpar.services;

import java.util.List;

import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dto.ClientesDTO;

public interface ClientesService {
  List<ClientesDTO> listClientes() throws ServiceException;
  ClientesDTO getCliente(Long id) throws ServiceException;
  ClientesDTO getClienteByRuc(String ruc) throws ServiceException;
  void deleteCliente(Long id) throws ServiceException;
  ClientesDTO updateCliente(Long id, ClientesDTO data) throws ServiceException;
  ClientesDTO createCliente(ClientesDTO data) throws ServiceException;
}