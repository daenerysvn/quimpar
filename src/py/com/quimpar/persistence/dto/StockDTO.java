package py.com.quimpar.persistence.dto;

import java.util.Date;

public class StockDTO {
	private Long id;
	private String lote;
	private Long cantidad;
	private String sala;
	private String estante;
	private String piso;
	private String codigoNcm;
	private String nroCas;
	private Long precioCosto;
	private Long precioCompra;
	private Date vencimiento;
	private Long idProducto;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public String getEstante() {
		return estante;
	}
	public void setEstante(String estante) {
		this.estante = estante;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getCodigoNcm() {
		return codigoNcm;
	}
	public void setCodigoNcm(String codigoNcm) {
		this.codigoNcm = codigoNcm;
	}
	public String getNroCas() {
		return nroCas;
	}
	public void setNroCas(String nroCas) {
		this.nroCas = nroCas;
	}
	public Long getPrecioCosto() {
		return precioCosto;
	}
	public void setPrecioCosto(Long precioCosto) {
		this.precioCosto = precioCosto;
	}
	public Long getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(Long precioCompra) {
		this.precioCompra = precioCompra;
	}
	public Date getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

}