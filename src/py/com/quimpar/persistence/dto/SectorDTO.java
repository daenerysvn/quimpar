package py.com.quimpar.persistence.dto;

public class SectorDTO {
	private Long id;
	private String codigo;
	private String descripcion;
	private Long idFraccionamiento;

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

}