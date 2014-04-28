package py.com.quimpar.session.clientes;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
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
import py.com.quimpar.utils.Service;

/**
 * 
 * @author bsandoval
 * 
 */

@Name("gestionClientes")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.hasRole('pag:gestionarclientes')}")
public class GestionarClientes extends ControllerBase implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 8672111656377034341L;
	private static Logger log = LoggerFactory.getLogger(GestionarClientes.class);
	private ApplicationContext ctx;
	private ClientesService clientesService;
	
	private String nombre;
	private List<ClientesDTO> clientes;

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
	}

	private void initComponents() throws Exception {
		setClientes(clientesService.listClientes());
		setNombre(null);
		setPagina(0);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ClientesDTO> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClientesDTO> clientes) {
		this.clientes = clientes;
	}
}