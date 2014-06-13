package py.com.quimpar.session.clientes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessage.Severity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import py.com.quimpar.config.QuimparApplicationContextProvider;
import py.com.quimpar.converter.SectorDTOConverter;
import py.com.quimpar.persistence.dto.ProductosDTO;
import py.com.quimpar.persistence.dto.SectorDTO;
import py.com.quimpar.services.ProductosService;
import py.com.quimpar.services.SectorService;
import py.com.quimpar.session.ControllerBase;

@Name("gestionProductos")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.hasRole('pag:gestionarclientes')}")
public class GestionarProductos extends ControllerBase implements Serializable{
	
  /**
	 * 
	 */
	private static final long serialVersionUID = -550532447263075542L;
private static Logger log = LoggerFactory.getLogger(GestionarProductos.class);
  private ApplicationContext ctx;
  private ProductosService productosService;
  private SectorService sectorService;
  
  private String codigo;
  private ProductosDTO producto;
  private List<ProductosDTO> productos;
  private Boolean modoAgregar;
  private String tituloModal;
  private Boolean cerrar;
  
  private SectorDTO sector;
  private List<SectorDTO> sectores;
  private SectorDTOConverter sectorDTOConverter;

  public void initInfo()
  {
    try
    {
      if ("S".equals(getInitVar())) {
        initServices();
        initComponents();
      }
      setInitVar("N");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      FacesMessages.instance().addFromResourceBundle(Severity.ERROR, e.getMessage());
    }
  }

  private void initServices() {
    ctx = QuimparApplicationContextProvider.getContext();
    productosService = ctx.getBean("productosService", ProductosService.class);
    sectorService = ctx.getBean("sectorService", SectorService.class);
  }

  private void initComponents() throws Exception {
    try {
      setProductos(productosService.listProductos());
      setSectores(sectorService.listSectores());
      setSectorDTOConverter(new SectorDTOConverter(getSectores()));
      if(getSectores() != null && getSectores().size() > 0){
    	  setSector(getSectores().get(0));
    	  log.info("\n===========\nSECTOR: {}\n===========\n", getSector().toString());
      }
      limpiarFormulario();
      setPagina(0);
    } catch (Exception e) {
      setProductos(new ArrayList<ProductosDTO>());
      log.error(e.getMessage(), e);
      FacesMessages.instance().addFromResourceBundle(Severity.ERROR, e.getMessage());
    }
  }

  public void limpiarFormulario() {
    setProducto(new ProductosDTO());
    setModoAgregar(null);
    setTituloModal(null);
    setCerrar(Boolean.valueOf(true));
  }

  public void consultar() {
    log.info("\n===========\nCONSULTAR PRODUCTO!\n===========\n");
    try {
      if ((this.codigo == null) || ("".equals(this.codigo))) {
        setProductos(productosService.listProductos());
      } else {
    	setProductos(productosService.getProductosLikeCodigo(codigo));
//        ProductosDTO producto = productosService.getProductobyCodigo(codigo);
//        setProductos(new ArrayList<ProductosDTO>());
//        getProductos().add(producto);
      }
    } catch (Exception e) {
      setProductos(new ArrayList<ProductosDTO>());
      log.error(e.getMessage(), e);
      FacesMessages.instance().addFromResourceBundle(Severity.ERROR, e.getMessage());
    }
  }

  public void nuevo()
  {
    limpiarFormulario();
    setModoAgregar(Boolean.valueOf(true));
    setTituloModal("Agregar producto");

    log.info("\n===========\nAGREGAR PRODUCTO!\n===========\n");
    getProducto().setCodigo("femproporex");
    getProducto().setDescripcion("femproporex en polvo");
    getProducto().setFactorGanancia(30L);
    getProducto().setLote("845385039fjffgfd");
    getProducto().setVencimiento(new Date());
    getProducto().setCantidadFraccionamiento(100L);
  }

  public void editar(ProductosDTO producto) {
    log.info("\n===========\nEDITAR PRODUCTO!\n===========\n");
    limpiarFormulario();
    setModoAgregar(Boolean.valueOf(false));
    setTituloModal("Modificar producto");
    setProducto(producto);
  }

  public void borrar(ProductosDTO producto) {
    log.info("\n===========\nBORRAR PRODUCTO!\n===========\n");
    try {
      productosService.deleteProducto(producto.getId());
      initComponents();
    } catch (Exception e) {
      setCerrar(Boolean.valueOf(false));
      log.error(e.getMessage(), e);
      FacesMessages.instance().addFromResourceBundle(Severity.ERROR, e.getMessage());
    }
  }

  public void guardar() {
    try {
      if (getModoAgregar().booleanValue()) {
        log.info("\n===========\nCREANDO PRODUCTO!\n===========\n");
        if(getSector() != null){
        	log.info("\n===========\nSECTOR: {}\n===========\n", getSector().toString());
        	getProducto().setIdSector(sector.getId());
        }
        productosService.createProducto(getProducto());
      } else if (!getModoAgregar().booleanValue()) {
        log.info("\n===========\nACTUALIZANDO PRODUCTO!\n{}\n===========\n", getProducto());
        if(getSector() != null){
        	log.info("\n===========\nSECTOR: {}\n===========\n", getSector().toString());
        	getProducto().setIdSector(sector.getId());
        }
        productosService.updateProducto(getProducto().getId(), getProducto());
      }
      initComponents();
    } catch (Exception e) {
      setCerrar(Boolean.valueOf(false));
      log.error(e.getMessage(), e);
      FacesMessages.instance().addFromResourceBundle(Severity.ERROR, e.getMessage());
    }
  }

  public List<ProductosDTO> getProductos() {
    return this.productos;
  }

  public void setProductos(List<ProductosDTO> productos) {
    this.productos = productos;
  }

  public ProductosDTO getProducto() {
    return this.producto;
  }

  public void setProducto(ProductosDTO producto) {
    this.producto = producto;
  }

  public Boolean getModoAgregar() {
    return this.modoAgregar;
  }

  public void setModoAgregar(Boolean modoAgregar) {
    this.modoAgregar = modoAgregar;
  }

  public String getTituloModal() {
    return this.tituloModal;
  }

  public void setTituloModal(String tituloModal) {
    this.tituloModal = tituloModal;
  }

  public Boolean getCerrar() {
    return this.cerrar;
  }

  public void setCerrar(Boolean cerrar) {
    this.cerrar = cerrar;
  }

  public String getCodigo() {
    return this.codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

public SectorDTO getSector() {
	return sector;
}

public void setSector(SectorDTO sector) {
	this.sector = sector;
}

public List<SectorDTO> getSectores() {
	return sectores;
}

public void setSectores(List<SectorDTO> sectores) {
	this.sectores = sectores;
}

public SectorDTOConverter getSectorDTOConverter() {
	return sectorDTOConverter;
}

public void setSectorDTOConverter(SectorDTOConverter sectorDTOConverter) {
	this.sectorDTOConverter = sectorDTOConverter;
}
}
