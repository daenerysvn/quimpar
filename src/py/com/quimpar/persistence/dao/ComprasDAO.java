package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.ComprasDTO;

public interface ComprasDAO {
  List<ComprasDTO> listCompras();
  ComprasDTO getComprasById(Long id);
  int createCompras(ComprasDTO dto);
  int deleteCompras(ComprasDTO dto);
  int updateCompras(ComprasDTO dto);
}