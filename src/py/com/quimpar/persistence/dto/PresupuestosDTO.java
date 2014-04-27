package py.com.quimpar.persistence.dto;

import java.util.Date;

public class PresupuestosDTO {
	private Long id;
	private String codigo;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private String usuarioModif;
	private Date fechaModif;
	private Long idCliente;
	private Long montoTotal;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public String getUsuarioModif() {
		return usuarioModif;
	}
	public void setUsuarioModif(String usuarioModif) {
		this.usuarioModif = usuarioModif;
	}
	public Date getFechaModif() {
		return fechaModif;
	}
	public void setFechaModif(Date fechaModif) {
		this.fechaModif = fechaModif;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Long montoTotal) {
		this.montoTotal = montoTotal;
	}

}