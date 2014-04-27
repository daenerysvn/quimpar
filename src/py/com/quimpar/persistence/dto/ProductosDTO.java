package py.com.quimpar.persistence.dto;

public class ProductosDTO {
	private Long id;
	private String codigo;
	private String descripcion;
	private Long stockMinimo;
	private Long cantidadFraccionamiento;
	private Long factorGanancia;
	private Long idSector;

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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getStockMinimo() {
		return stockMinimo;
	}
	public void setStockMinimo(Long stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	public Long getCantidadFraccionamiento() {
		return cantidadFraccionamiento;
	}
	public void setCantidadFraccionamiento(Long cantidadFraccionamiento) {
		this.cantidadFraccionamiento = cantidadFraccionamiento;
	}
	public Long getFactorGanancia() {
		return factorGanancia;
	}
	public void setFactorGanancia(Long factorGanancia) {
		this.factorGanancia = factorGanancia;
	}
	public Long getIdSector() {
		return idSector;
	}
	public void setIdSector(Long idSector) {
		this.idSector = idSector;
	}

}