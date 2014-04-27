package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.DetalleVentasDTO;

public interface DetalleVentasDAO {
  List<DetalleVentasDTO> listDetalleVentas();
  DetalleVentasDTO getDetalleVentasById(Long id);
  int createDetalleVentas(DetalleVentasDTO dto);
  int deleteDetalleVentas(DetalleVentasDTO dto);
  int updateDetalleVentas(DetalleVentasDTO dto);
}