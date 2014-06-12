package py.com.quimpar.session.clientes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

@Name("consultarBoletas")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.hasRole('pag:gestionarclientes')}")
public class ConsultaBoletasCompras extends ControllerBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2552164321835008566L;
	/**
	 * 
	 */

	/**
	 * 
	 */

	
	private static Logger log = LoggerFactory.getLogger(ConsultaBoletasCompras.class);
	private ApplicationContext ctx;
	private ComprasService comprasService;
//	private DetalleComprasService detalleComprasService;
	private ProveedoresService proveedoresService;
	private ProductosService productosService;
	private ProveedoresDTO proveedor;
	private Date fechaDesde;
	private Date fechaHasta;
	private List<ComprasDTO> compras;
	private List<ProveedoresDTO> proveedores;
	private ComprasDTO compra;
	private List<DetalleComprasDTO> detallesCompra;

	

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
		comprasService = ctx.getBean(Service.COMPRAS_SERVICE,ComprasService.class);
		proveedoresService = ctx.getBean(Service.PROVEEDORES_SERVICE,ProveedoresService.class);
		productosService = ctx.getBean(Service.PRODUCTOS_SERVICE,ProductosService.class);
	}

	public void initComponents() throws Exception {
		compras=new ArrayList<ComprasDTO>();
		fechaDesde= new Date();
		fechaHasta= new Date();
		proveedor=new ProveedoresDTO();
		proveedores= proveedoresService.listProveedores();
		compra= new ComprasDTO();
		detallesCompra= new ArrayList<DetalleComprasDTO>();
		
	}

	
	public void buscar(){
		HashMap<String, Object> param= new HashMap<String, Object>();
		param.put("desde", fechaDesde);
		param.put("hasta", fechaHasta);
		if (proveedor!=null && proveedor.getId()!=null){
			param.put("proveedorId", proveedor.getId());
		}else{
			param.put("proveedorId", null);
		}
		
		try {
			compras=comprasService.listCompras(param);
			ProveedoresDTO prov= new ProveedoresDTO();
			for(ComprasDTO comp: compras){
				prov=proveedoresService.getProveedor(comp.getIdProveedor());
				if (prov!=null&& prov.getRazonSocial()!=null){
					comp.setNombreProveedor( prov.getRazonSocial());
				}
			}
			
		} catch (ServiceException e) {
			log.error(e.getMessage(), e);
			FacesMessages.instance().addFromResourceBundle(Severity.ERROR,e.getMessage());
		}
	}
	
	public void obtenerDetalles(ComprasDTO dto){
		System.out.println("obtener Detalles");
		this.compra=dto;
		try {
			detallesCompra=comprasService.getDetalles(compra.getId());
			ProductosDTO prod= new ProductosDTO();
			for(DetalleComprasDTO det: detallesCompra){
				prod=productosService.getProducto(det.getIdProducto());
				if(prod!=null && prod.getDescripcion()!=null){
					det.setDescriProducto(prod.getDescripcion());
				}
			}
			System.out.println("detallesCompra.size:"+detallesCompra.size());
		} catch (ServiceException e) {
			log.error(e.getMessage(), e);
			FacesMessages.instance().addFromResourceBundle(Severity.ERROR,e.getMessage());
		}
	}

	public void setProveedor(ProveedoresDTO proveedor) {
		this.proveedor = proveedor;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public List<ComprasDTO> getCompras() {
		return compras;
	}

	public void setCompras(List<ComprasDTO> compras) {
		this.compras = compras;
	}

	public List<ProveedoresDTO> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedoresDTO> proveedores) {
		this.proveedores = proveedores;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		ConsultaBoletasCompras.log = log;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ComprasDTO getCompra() {
		return compra;
	}

	public void setCompra(ComprasDTO compra) {
		this.compra = compra;
	}

	public List<DetalleComprasDTO> getDetallesCompra() {
		return detallesCompra;
	}

	public void setDetallesCompra(List<DetalleComprasDTO> detallesCompra) {
		this.detallesCompra = detallesCompra;
	}
	
	

}