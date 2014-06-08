package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.ProveedoresDTO;

public interface ProveedoresDAO {
  List<ProveedoresDTO> listProveedores();
  ProveedoresDTO getProveedoresById(Long id);
  ProveedoresDTO getProveedoresByRuc(String ruc);
  int createProveedores(ProveedoresDTO dto);
  int deleteProveedores(ProveedoresDTO dto);
  int updateProveedores(ProveedoresDTO dto);
}