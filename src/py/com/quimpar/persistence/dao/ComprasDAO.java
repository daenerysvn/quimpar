package py.com.quimpar.persistence.dao;

import java.util.HashMap;
import java.util.List;

import py.com.quimpar.persistence.dto.ComprasDTO;
import py.com.quimpar.persistence.dto.DetalleComprasDTO;

public interface ComprasDAO {
  List<ComprasDTO> listCompras();
  ComprasDTO getComprasById(Long id);
  int createCompras(ComprasDTO dto);
  int deleteCompras(ComprasDTO dto);
  int updateCompras(ComprasDTO dto);
  List<ComprasDTO> listCompras(HashMap<String, Object> param);

}