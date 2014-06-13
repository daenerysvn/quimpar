package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.FraccionamientoDTO;

public interface FraccionamientoDAO {
  List<FraccionamientoDTO> listFraccionamiento();
  FraccionamientoDTO getFraccionamientoById(Long id);
  int createFraccionamiento(FraccionamientoDTO dto);
  int deleteFraccionamiento(FraccionamientoDTO dto);
  int updateFraccionamiento(FraccionamientoDTO dto);
}