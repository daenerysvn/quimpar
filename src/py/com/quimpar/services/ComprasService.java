package py.com.quimpar.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dto.ComprasDTO;
import py.com.quimpar.persistence.dto.DetalleComprasDTO;

public interface ComprasService {
  List<ComprasDTO> listCompras(HashMap<String, Object> param) throws ServiceException;
  @Transactional(rollbackFor=ServiceException.class)
  ComprasDTO createCompras(ComprasDTO data) throws ServiceException;
  List<DetalleComprasDTO> getDetalles(Long idCompra) throws ServiceException;
}
