package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.FraccionamienoDTO;

public interface FraccionamienoDAO {
  List<FraccionamienoDTO> listFraccionamieno();
  FraccionamienoDTO getFraccionamienoById(Long id);
  int createFraccionamieno(FraccionamienoDTO dto);
  int deleteFraccionamieno(FraccionamienoDTO dto);
  int updateFraccionamieno(FraccionamienoDTO dto);
}