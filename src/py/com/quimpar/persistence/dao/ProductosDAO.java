package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.ProductosDTO;

public interface ProductosDAO {
  List<ProductosDTO> listProductos();
  List<ProductosDTO> getProductosLikeCodigo(String codigo);
  ProductosDTO getProductoById(Long id);
  ProductosDTO getProductoByCodigo(String codigo);
  ProductosDTO getProductoCompositeById(Long id);
  int createProductos(ProductosDTO dto);
  int deleteProductos(ProductosDTO dto);
  int updateProductos(ProductosDTO dto);
}