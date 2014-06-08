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
	private ClientesService clientesService;
	private ProveedoresService proveedoresService;
	private ProveedoresDTO proveedor;
	private Date fechaDesde;
	private Date fechaHasta;
	private List<ComprasDTO> compras;
	private List<ProveedoresDTO> proveedores;

	

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
	}

	private void initComponents() throws Exception {
		compras=new ArrayList<ComprasDTO>();

		fechaDesde= new Date();
		fechaHasta= new Date();
		proveedor=new ProveedoresDTO();
		proveedores= proveedoresService.listProveedores();
		
	}

	
	public void Buscar(){
		//TODO
		log.info("buscar...");
	}
	
	

	public ProveedoresDTO getProveedor() {
		return proveedor;
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

}