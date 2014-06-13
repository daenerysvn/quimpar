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
import py.com.quimpar.persistence.dto.ClientesDTO;
import py.com.quimpar.services.ClientesService;
import py.com.quimpar.session.ControllerBase;

@Name("gestionClientes")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.hasRole('pag:gestionarclientes')}")
public class GestionarClientes extends ControllerBase
  implements Serializable
{
  private static final long serialVersionUID = 8672111656377034341L;
  private static Logger log = LoggerFactory.getLogger(GestionarClientes.class);
  private ApplicationContext ctx;
  private ClientesService clientesService;
  private String ruc;
  private ClientesDTO cliente;
  private List<ClientesDTO> clientes;
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
    clientesService = ((ClientesService)this.ctx.getBean("clientesService", ClientesService.class));
  }

  private void initComponents() throws Exception {
    try {
      setClientes(clientesService.listClientes());
      limpiarFormulario();
      setPagina(0);
    } catch (Exception e) {
      setClientes(new ArrayList<ClientesDTO>());
      log.error(e.getMessage(), e);
      FacesMessages.instance().addFromResourceBundle(Severity.ERROR, e.getMessage());
    }
  }

  public void limpiarFormulario() {
    setCliente(new ClientesDTO());
    setModoAgregar(null);
    setTituloModal(null);
    setCerrar(Boolean.valueOf(true));
  }

  public void consultar() {
    log.info("\n===========\nCONSULTAR CLIENTE!\n===========\n");
    try {
      if ((ruc == null) || ("".equals(ruc))) {
        setClientes(clientesService.listClientes());
      } else {
        ClientesDTO cliente = clientesService.getClienteByRuc(ruc);
        setClientes(new ArrayList<ClientesDTO>());
        getClientes().add(cliente);
      }
    } catch (Exception e) {
      setClientes(new ArrayList<ClientesDTO>());
      log.error(e.getMessage(), e);
      FacesMessages.instance().addFromResourceBundle(Severity.ERROR, e.getMessage());
    }
  }

  public void nuevo()
  {
    limpiarFormulario();
    setModoAgregar(Boolean.valueOf(true));
    setTituloModal("Agregar cliente");

    log.info("\n===========\nAGREGAR CLIENTE!\n===========\n");
    getCliente().setRazonSocial("beatriz sandoval");
    getCliente().setRuc("3955767");
    getCliente().setDireccion("abetos casi los naranjos");
    getCliente().setTelefono("021674033");
    getCliente().setTelefonoMovil("0971888280");
    getCliente().setEmail("mbsandoval10@gmail.com");
    getCliente().setPais("paraguay");
    getCliente().setContacto("contacto");
  }

  public void editar(ClientesDTO cliente) {
    log.info("\n===========\nEDITAR CLIENTE!\n===========\n");
    limpiarFormulario();
    setModoAgregar(Boolean.valueOf(false));
    setTituloModal("Modificar cliente");
    setCliente(cliente);
  }

  public void borrar(ClientesDTO cliente) {
    log.info("\n===========\nBORRAR CLIENTE!\n===========\n");
    try {
      clientesService.deleteCliente(cliente.getId());
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
        log.info("\n===========\nCREANDO CLIENTE!\n===========\n");
        clientesService.createCliente(getCliente());
      } else if (!getModoAgregar().booleanValue()) {
        log.info("\n===========\nACTUALIZANDO CLIENTE!\n{}\n===========\n", getCliente());
        this.clientesService.updateCliente(getCliente().getId(), getCliente());
      }
      initComponents();
    } catch (Exception e) {
      setCerrar(Boolean.valueOf(false));
      log.error(e.getMessage(), e);
      FacesMessages.instance().addFromResourceBundle(Severity.ERROR, e.getMessage());
    }
  }

  public List<ClientesDTO> getClientes() {
    return this.clientes;
  }

  public void setClientes(List<ClientesDTO> clientes) {
    this.clientes = clientes;
  }

  public ClientesDTO getCliente() {
    return this.cliente;
  }

  public void setCliente(ClientesDTO cliente) {
    this.cliente = cliente;
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
 * Qualified Name:     py.com.quimpar.session.clientes.GestionarClientes
 * JD-Core Version:    0.6.2
 */