package py.com.quimpar.services;

import java.util.List;

import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dto.ProductosDTO;

public interface ProductosService {
  List<ProductosDTO> listProductos() throws ServiceException;
  ProductosDTO getProducto(Long id) throws ServiceException;
  void deleteProducto(Long id) throws ServiceException;
  ProductosDTO updateProducto(Long id, ProductosDTO data) throws ServiceException;
  ProductosDTO createProducto(ProductosDTO data) throws ServiceException;
}