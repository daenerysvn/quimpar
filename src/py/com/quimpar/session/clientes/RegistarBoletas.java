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
import py.com.quimpar.exception.ServiceException;
import py.com.quimpar.persistence.dto.ComprasDTO;
import py.com.quimpar.persistence.dto.DetalleComprasDTO;
import py.com.quimpar.persistence.dto.ProductosDTO;
import py.com.quimpar.persistence.dto.ProveedoresDTO;
import py.com.quimpar.services.ClientesService;
import py.com.quimpar.services.ComprasService;
import py.com.quimpar.services.ProductosService;
import py.com.quimpar.services.ProveedoresService;
import py.com.quimpar.session.ControllerBase;
import py.com.quimpar.utils.Service;


/**
 * 
 * @author jgarcia
 * 
 */

@Name("registrarBoletas")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.hasRole('pag:gestionarclientes')}")
public class RegistarBoletas extends ControllerBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2151804732684725939L;
	/**
	 * 
	 */

	
	private static Logger log = LoggerFactory.getLogger(RegistarBoletas.class);
	private ApplicationContext ctx;
	private ClientesService clientesService;
	private ProveedoresService proveedoresService;
	private ProductosService productosService;
	private ComprasService comprasService;
	private String ruc;
	private String nroFactura;
	private ProveedoresDTO proveedor;
	private Date fechaFactura;
	private Long montoTotal;
	private Long montoSuma;
	private Boolean estaGrabado;
	
	private List<DetalleComprasDTO> detalles;
	
	private List<ProductosDTO> productos;
	private String filtroDescri;
	int i=1;
	

	

	@Override
	public void initInfo() {
		try {
			if ("S".equals(this.getInitVar())) {
				initServices();
				initComponents();
			}
			this.setInitVar("N");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			FacesMessages.instance().addFromResourceBundle(Severity.ERROR,e.getMessage());
		}
	}

	private void initServices() {
		ctx = QuimparApplicationContextProvider.getContext();
		clientesService = ctx.getBean(Service.CLIENTES_SERVICE,ClientesService.class);
		proveedoresService = ctx.getBean(Service.PROVEEDORES_SERVICE,ProveedoresService.class);
		productosService = ctx.getBean(Service.PRODUCTOS_SERVICE,ProductosService.class);
		comprasService = ctx.getBean(Service.COMPRAS_SERVICE,ComprasService.class);
	}

	private void initComponents() throws Exception {
		detalles=new ArrayList<DetalleComprasDTO>();
		nroFactura="";
		montoTotal=0L;
		montoSuma=0L;
		ruc="";
		fechaFactura= new Date();
		estaGrabado=false;
		i=1;
		proveedor= new ProveedoresDTO();
		productos=new ArrayList<ProductosDTO>();
		productos=productosService.listProductos();
		estaGrabado=false;
		
	}

	public void obtenerProveedor(){
		System.out.println("ruc: "+ruc);
		try {
			proveedor = proveedoresService.getProveedorByRuc(ruc);
		} catch (ServiceException e) {
			proveedor= new ProveedoresDTO();
			log.error(e.getMessage(), e);
			FacesMessages.instance().addFromResourceBundle(Severity.ERROR,e.getMessage());
		}
	}
	
	public void insertarDetalle( ProductosDTO producto){
		//TODO
		System.out.println("Insertar Detalle:");
		DetalleComprasDTO det = new DetalleComprasDTO();
		det.setDescriProducto(producto.getDescripcion());
		det.setCantidad(1L);
		det.setPrecioCompra(0L);	
		det.setProducto(producto);
		detalles.add(det);
		System.out.println("size:"+detalles.size());
	}
	
	public void eliminarDetalle(DetalleComprasDTO detalle){
		detalles.remove(detalle);
		System.out.println("size:"+detalles.size());
	}
	
	public void grabar(){
		//TODO
		
		ComprasDTO compra = new ComprasDTO();
		compra.setFechaCompra(new Date());
		compra.setFechaFactura(fechaFactura);
		compra.setIdProveedor(proveedor.getId());
		compra.setMontoTotal(montoTotal);
		compra.setNroFactura(nroFactura);
		
		compra.setDetalles(detalles);
		
		try {
			compra = comprasService.createCompras(compra);
			estaGrabado=true;
			FacesMessages.instance().addFromResourceBundle(Severity.INFO,"REGISTRO EXITOSO");
		} catch (ServiceException e) {
			log.error(e.getMessage(), e);
			FacesMessages.instance().addFromResourceBundle(Severity.ERROR,e.getMessage());
		}
		
	}
	
	public void calcularTotal(){
		Long sum=0L;
		for(DetalleComprasDTO det:detalles){
			sum=sum+(det.getCantidad()*det.getPrecioCompra());
		}
		montoSuma=sum;
		montoTotal=montoSuma;
	}
	
	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public ProveedoresDTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedoresDTO proveedor) {
		this.proveedor = proveedor;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public Long getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Long montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Long getMontoSuma() {
		return montoSuma;
	}

	public void setMontoSuma(Long montoSuma) {
		this.montoSuma = montoSuma;
	}

	public Boolean getEstaGrabado() {
		return estaGrabado;
	}

	public void setEstaGrabado(Boolean estaGrabado) {
		this.estaGrabado = estaGrabado;
	}

	public List<DetalleComprasDTO> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleComprasDTO> detalles) {
		this.detalles = detalles;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		RegistarBoletas.log = log;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ProductosDTO> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductosDTO> productos) {
		this.productos = productos;
	}

	public String getFiltroDescri() {
		return filtroDescri;
	}

	public void setFiltroDescri(String filtroDescri) {
		this.filtroDescri = filtroDescri;
	}

}