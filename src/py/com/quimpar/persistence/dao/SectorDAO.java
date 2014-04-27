package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.SectorDTO;

public interface SectorDAO {
  List<SectorDTO> listSector();
  SectorDTO getSectorById(Long id);
  int createSector(SectorDTO dto);
  int deleteSector(SectorDTO dto);
  int updateSector(SectorDTO dto);
}