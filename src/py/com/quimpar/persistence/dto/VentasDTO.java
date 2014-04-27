package py.com.quimpar.persistence.dto;

import java.util.Date;

public class VentasDTO {
	private Long id;
	private Long idCliente;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private Long nroFactura;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Long getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(Long nroFactura) {
		this.nroFactura = nroFactura;
	}

}