package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.DetalleComprasDTO;

public interface DetalleComprasDAO {
  List<DetalleComprasDTO> listDetalleCompras();
  DetalleComprasDTO getDetalleComprasById(Long id);
  int createDetalleCompras(DetalleComprasDTO dto);
  int deleteDetalleCompras(DetalleComprasDTO dto);
  int updateDetalleCompras(DetalleComprasDTO dto);
}