package py.com.quimpar.persistence.dto;

public class FraccionamientoDTO {
	private Long id;
	private String codigo;
	private String descripcion;
	
	public FraccionamientoDTO(){};
	

	public FraccionamientoDTO(Long id) {
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


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FraccionamientoDTO [id=").append(id)
				.append(", codigo=").append(codigo).append(", descripcion=")
				.append(descripcion).append("]");
		return builder.toString();
	}

}