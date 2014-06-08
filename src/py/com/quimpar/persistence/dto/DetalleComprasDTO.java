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
	
	private String descriProducto;
	private ProductosDTO producto;

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
	public String getDescriProducto() {
		return descriProducto;
	}
	public void setDescriProducto(String descriProducto) {
		this.descriProducto = descriProducto;
	}
	public ProductosDTO getProducto() {
		return producto;
	}
	public void setProducto(ProductosDTO producto) {
		this.producto = producto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleComprasDTO other = (DetalleComprasDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}