package py.com.quimpar.persistence.dto;

import java.util.Date;

public class DetalleVentasDTO {
	private Long id;
	private Long idVenta;
	private Long idProducto;
	private String lote;
	private Date vencimiento;
	private Long precioVenta;
	private Long cantidad;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public Date getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	public Long getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(Long precioVenta) {
		this.precioVenta = precioVenta;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

}