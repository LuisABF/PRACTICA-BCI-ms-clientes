package cl.everis.clientes.service;

import cl.everis.clientes.dto.UsuarioRequestDTO;
import cl.everis.clientes.dto.UsuarioResponseDTO;
import java.util.List;

/**
 * Interfaz UserService
 */
public interface ServiceUsuario {
    List <UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO obtenerUsuario(long id);
    void insertar(UsuarioRequestDTO usuarioRequestDTO);
    void modificar(UsuarioRequestDTO usuarioRequestDTO);
    void eliminar(long id);


}
