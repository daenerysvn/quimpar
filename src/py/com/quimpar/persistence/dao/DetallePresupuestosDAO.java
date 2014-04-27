package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.DetallePresupuestosDTO;

public interface DetallePresupuestosDAO {
  List<DetallePresupuestosDTO> listDetallePresupuestos();
  DetallePresupuestosDTO getDetallePresupuestosById(Long id);
  int createDetallePresupuestos(DetallePresupuestosDTO dto);
  int deleteDetallePresupuestos(DetallePresupuestosDTO dto);
  int updateDetallePresupuestos(DetallePresupuestosDTO dto);
}