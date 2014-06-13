package py.com.quimpar.services.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import py.com.quimpar.exception.ErrorCode;
import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dao.ProductosDAO;
import py.com.quimpar.persistence.dto.ProductosDTO;
import py.com.quimpar.services.ProductosService;
public class ProductosServiceImpl implements ProductosService{

  private ProductosDAO productosDAO;

  public void setProductosDAO(ProductosDAO productosDAO) {
    this.productosDAO = productosDAO;
  }

  @Override
  public List<ProductosDTO> listProductos() throws ServiceException {
    List<ProductosDTO> productos =  productosDAO.listProductos();

    if (productos == null || productos.size() == 0)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"productos.list.notfound");

    return productos;
  }
  
  @Override
  public List<ProductosDTO> getProductosLikeCodigo(String codigo) throws ServiceException {
	if (codigo == null || "".equals(codigo))
	   throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","codigo");
	
	StringBuffer sb = new StringBuffer("%");
	sb.append(codigo).append("%");
	  
	List<ProductosDTO> productos =  productosDAO.getProductosLikeCodigo(sb.toString());

    if (productos == null || productos.size() == 0)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"productos.list.notfound");

    return productos;
  }

  @Override
  public ProductosDTO getProducto(Long id) throws ServiceException {
    if (id == null || id <= 0)
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","id");

    ProductosDTO producto =  productosDAO.getProductoById(id);

    if (producto == null)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"producto.notfound", id);
    
    return producto;
  }
  
  @Override
  public ProductosDTO getProductobyCodigo(String codigo) throws ServiceException {
	if (codigo == null || "".equals(codigo))
	  throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","codigo");
	
	ProductosDTO producto =  productosDAO.getProductoByCodigo(codigo);
	
	if (producto == null)
	  throw new ServiceException(ErrorCode.NO_DATA_FOUND,"producto.notfound", codigo);
	
	return producto;
  }
  
  @Override
  public ProductosDTO getProductoComposite(Long id) throws ServiceException {
    if (id == null || id <= 0)
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","id");

    ProductosDTO producto =  productosDAO.getProductoCompositeById(id);

    if (producto == null)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"producto.notfound", id);
    
    return producto;
  }

  @Override
  public void deleteProducto(Long id) throws ServiceException {
    if (id == null || id <= 0)
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","id");

    ProductosDTO productos =  productosDAO.getProductoById(id);

    if(productos == null)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"producto.notfound", id);

    try {
      productosDAO.deleteProductos(productos);
    }
    catch (DataIntegrityViolationException e){
      throw new ServiceException(ErrorCode.DATA_INTEGRITY_VIOLATION, "productos.constraint.violation", id);
    }

  }

  @Override
  public ProductosDTO updateProducto(Long id, ProductosDTO data) throws ServiceException {
    if (id == null || id <= 0)
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","id");

    if (null == data || "".equals(data))
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","data");

    ProductosDTO productos = productosDAO.getProductoById(id);

    if (productos == null)
      throw new ServiceException(ErrorCode.NO_DATA_FOUND,"producto.notfound", id);
    
    productosDAO.updateProductos(productos);

    return data;
  }
  @Override
  public ProductosDTO createProducto(ProductosDTO data) throws ServiceException {
    if (data == null || "".equals(data))
      throw new ServiceException(ErrorCode.PARAMETER_ERROR,"parameter.error.null","data");

    try {
      productosDAO.createProductos(data);
    }
    catch (DuplicateKeyException e){
      throw new ServiceException(ErrorCode.DUPLICATE_KEY_EXCEPTION, "productos.duplicate.key");
    }
    
    return data;
  }

}