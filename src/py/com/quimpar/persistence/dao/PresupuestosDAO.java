package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.PresupuestosDTO;

public interface PresupuestosDAO {
  List<PresupuestosDTO> listPresupuestos();
  PresupuestosDTO getPresupuestosById(Long id);
  int createPresupuestos(PresupuestosDTO dto);
  int deletePresupuestos(PresupuestosDTO dto);
  int updatePresupuestos(PresupuestosDTO dto);
}