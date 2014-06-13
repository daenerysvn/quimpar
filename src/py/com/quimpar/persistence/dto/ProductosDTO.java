package py.com.quimpar.persistence.dto;

import java.util.Date;

public class ProductosDTO {
	private Long id;
	private String codigo;
	private String descripcion;
	private Long stockMinimo;
	private Long cantidadFraccionamiento;
	private Long factorGanancia;
	private Long idSector;
	private SectorDTO sector;
	
	private String lote;
	private Date vencimiento;
	
	private String um;
	
	public ProductosDTO(){};
	

	public ProductosDTO(Long id) {
		super();
		this.id = id;
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
	public String getUm() {
		return um;
	}
	public void setUm(String um) {
		this.um = um;
	}
	public SectorDTO getSector() {
		return sector;
	}
	public void setSector(SectorDTO sector) {
		this.sector = sector;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductosDTO [id=").append(id).append(", codigo=")
				.append(codigo).append(", descripcion=").append(descripcion)
				.append(", stockMinimo=").append(stockMinimo)
				.append(", cantidadFraccionamiento=")
				.append(cantidadFraccionamiento).append(", factorGanancia=")
				.append(factorGanancia).append(", idSector=").append(idSector)
				.append(", sector=").append(sector).append(", lote=")
				.append(lote).append(", vencimiento=").append(vencimiento)
				.append(", um=").append(um).append("]");
		return builder.toString();
	}

}