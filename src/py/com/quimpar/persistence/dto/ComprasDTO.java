package py.com.quimpar.persistence.dto;

import java.util.Date;
import java.util.List;

public class ComprasDTO {
	private Long id;
	private Date fechaCompra;
	private Date fechaFactura;
	private String nroFactura;
	private Long montoTotal;
	private Long idProveedor;
	
	private List<DetalleComprasDTO> detalles;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public Date getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	public String getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}
	public Long getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Long montoTotal) {
		this.montoTotal = montoTotal;
	}
	public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public List<DetalleComprasDTO> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetalleComprasDTO> detalles) {
		this.detalles = detalles;
	}

}