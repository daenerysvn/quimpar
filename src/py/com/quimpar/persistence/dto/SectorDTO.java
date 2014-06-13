package py.com.quimpar.persistence.dto;

public class SectorDTO {
	private Long id;
	private String codigo;
	private String descripcion;
	private Long idFraccionamiento;
	private FraccionamientoDTO fraccionamiento;
	
	public SectorDTO(){};
	

	public SectorDTO(Long id) {
		super();
		this.id = id;
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
	public Long getIdFraccionamiento() {
		return idFraccionamiento;
	}
	public void setIdFraccionamiento(Long idFraccionamiento) {
		this.idFraccionamiento = idFraccionamiento;
	}
	public FraccionamientoDTO getFraccionamiento() {
		return fraccionamiento;
	}
	public void setFraccionamiento(FraccionamientoDTO fraccionamiento) {
		this.fraccionamiento = fraccionamiento;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SectorDTO [id=").append(id).append(", codigo=")
				.append(codigo).append(", descripcion=").append(descripcion)
				.append(", idFraccionamiento=").append(idFraccionamiento)
				.append(", fraccionamiento=").append(fraccionamiento)
				.append("]");
		return builder.toString();
	}

}