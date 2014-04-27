package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.VentasDTO;

public interface VentasDAO {
  List<VentasDTO> listVentas();
  VentasDTO getVentasById(Long id);
  int createVentas(VentasDTO dto);
  int deleteVentas(VentasDTO dto);
  int updateVentas(VentasDTO dto);
}