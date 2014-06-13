package py.com.quimpar.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dto.ProductosDTO;

public interface ProductosService {
  List<ProductosDTO> listProductos() throws ServiceException;
  List<ProductosDTO> getProductosLikeCodigo(String codigo) throws ServiceException;
  ProductosDTO getProducto(Long id) throws ServiceException;
  ProductosDTO getProductobyCodigo(String codigo) throws ServiceException;
  ProductosDTO getProductoComposite(Long id) throws ServiceException;
  
  @Transactional(rollbackFor = ServiceException.class)
  void deleteProducto(Long id) throws ServiceException;
  
  @Transactional(rollbackFor = ServiceException.class)
  ProductosDTO updateProducto(Long id, ProductosDTO data) throws ServiceException;
  
  @Transactional(rollbackFor = ServiceException.class)
  ProductosDTO createProducto(ProductosDTO data) throws ServiceException;
}