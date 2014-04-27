package py.com.quimpar.persistence.dto;

import java.util.Date;

public class DetalleComprasDTO {
	private Long id;
	private Long idCompra;
	private Long idProducto;
	private String lote;
	private Date vencimiento;
	private Long precioCompra;
	private Long cantidad;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
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
	public Long getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(Long precioCompra) {
		this.precioCompra = precioCompra;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

}