package py.com.quimpar.services;

import java.util.List;

import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dto.ProveedoresDTO;

public interface ProveedoresService {
  List<ProveedoresDTO> listProveedores() throws ServiceException;
  ProveedoresDTO getProveedor(Long id) throws ServiceException;
  void deleteProveedor(Long id) throws ServiceException;
  ProveedoresDTO updateProveedor(Long id, ProveedoresDTO data) throws ServiceException;
  ProveedoresDTO createProveedor(ProveedoresDTO data) throws ServiceException;
}