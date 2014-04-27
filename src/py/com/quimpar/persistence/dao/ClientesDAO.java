package py.com.quimpar.persistence.dao;

import java.util.List;
import py.com.quimpar.persistence.dto.ClientesDTO;

public interface ClientesDAO {
  List<ClientesDTO> listClientes();
  ClientesDTO getClientesById(Long id);
  int createClientes(ClientesDTO dto);
  int deleteClientes(ClientesDTO dto);
  int updateClientes(ClientesDTO dto);
}