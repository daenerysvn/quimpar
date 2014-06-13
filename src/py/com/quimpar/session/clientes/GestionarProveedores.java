package py.com.quimpar.session.clientes;

import java.io.Serializable;
import java.util.ArrayList;
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
import py.com.quimpar.persistence.dto.ProveedoresDTO;
import py.com.quimpar.services.ProveedoresService;
import py.com.quimpar.session.ControllerBase;

@Name("gestionProveedores")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.hasRole('pag:gestionarclientes')}")
public class GestionarProveedores extends ControllerBase
  implements Serializable
{
	
  /**
	 * 
	 */
	private static final long serialVersionUID = 2427402265358438812L;
private static Logger log = LoggerFactory.getLogger(GestionarProveedores.class);
  private ApplicationContext ctx;
  private ProveedoresService proveedoresService;
  private String ruc;
  private ProveedoresDTO proveedor;
  private List<ProveedoresDTO> proveedores;
  private Boolean modoAgregar;
  private String tituloModal;
  private Boolean cerrar;

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
    proveedoresService = ((ProveedoresService)ctx.getBean("proveedoresService", ProveedoresService.class));
  }

  private void initComponents() throws Exception {
    try {
      setProveedores(proveedoresService.listProveedores());
      limpiarFormulario();
      setPagina(0);
    } catch (Exception e) {
      setProveedores(new ArrayList<ProveedoresDTO>());
      log.error(e.getMessage(), e);
      FacesMessages.instance().addFromResourceBundle(Severity.ERROR, e.getMessage());
    }
  }

  public void limpiarFormulario() {
    setProveedor(new ProveedoresDTO());
    setModoAgregar(null);
    setTituloModal(null);
    setCerrar(Boolean.valueOf(true));
  }

  public void consultar() {
    log.info("\n===========\nCONSULTAR PROVEEDOR!\n===========\n");
    try {
      if ((ruc == null) || ("".equals(ruc))) {
        setProveedores(proveedoresService.listProveedores());
      }
      else {
        setProveedores(new ArrayList<ProveedoresDTO>());
        getProveedores().add(proveedor);
      }
    } catch (Exception e) {
      setProveedores(new ArrayList<ProveedoresDTO>());
      log.error(e.getMessage(), e);
      FacesMessages.instance().addFromResourceBundle(Severity.ERROR, e.getMessage());
    }
  }

  public void nuevo()
  {
    limpiarFormulario();
    setModoAgregar(Boolean.valueOf(true));
    setTituloModal("Agregar proveedor");

    log.info("\n===========\nAGREGAR PROVEEDOR!\n===========\n");
    getProveedor().setRazonSocial("jorge raul garcia");
    getProveedor().setRuc("4489768-5");
    getProveedor().setDireccion("san antonio");
    getProveedor().setTelefono("021234567");
    getProveedor().setTelefonoMovil("0981234567");
    getProveedor().setEmail("jorgegarcia@gmail.com");
    getProveedor().setPais("paraguay");
    getProveedor().setContacto("contacto");
  }

  public void editar(ProveedoresDTO proveedor) {
    log.info("\n===========\nEDITAR PROVEEDOR!\n===========\n");
    limpiarFormulario();
    setModoAgregar(Boolean.valueOf(false));
    setTituloModal("Modificar proveedor");
    setProveedor(proveedor);
  }

  public void borrar(ProveedoresDTO proveedor) {
    log.info("\n===========\nBORRAR PROVEEDOR!\n===========\n");
    try {
      proveedoresService.deleteProveedor(proveedor.getId());
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
        log.info("\n===========\nCREANDO PROVEEDOR!\n===========\n");
        proveedoresService.createProveedor(getProveedor());
      } else if (!getModoAgregar().booleanValue()) {
        log.info("\n===========\nACTUALIZANDO PROVEEDOR!\n{}\n===========\n", getProveedor());
        proveedoresService.updateProveedor(getProveedor().getId(), getProveedor());
      }
      initComponents();
    } catch (Exception e) {
      setCerrar(Boolean.valueOf(false));
      log.error(e.getMessage(), e);
      FacesMessages.instance().addFromResourceBundle(Severity.ERROR, e.getMessage());
    }
  }

  public List<ProveedoresDTO> getProveedores() {
    return this.proveedores;
  }

  public void setProveedores(List<ProveedoresDTO> proveedores) {
    this.proveedores = proveedores;
  }

  public ProveedoresDTO getProveedor() {
    return this.proveedor;
  }

  public void setProveedor(ProveedoresDTO proveedor) {
    this.proveedor = proveedor;
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

  public String getRuc() {
    return this.ruc;
  }

  public void setRuc(String ruc) {
    this.ruc = ruc;
  }
}

/* Location:           /home/bsandoval/LABORAL/tools/jd-gui-0.3.5.linux.i686/
 * Qualified Name:     py.com.quimpar.session.controller.GestionarProveedores
 * JD-Core Version:    0.6.2
 */