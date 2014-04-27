package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.StockDTO;

public interface StockDAO {
  List<StockDTO> listStock();
  StockDTO getStockById(Long id);
  int createStock(StockDTO dto);
  int deleteStock(StockDTO dto);
  int updateStock(StockDTO dto);
}