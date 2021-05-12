package cl.everis.clientes.service;

import cl.everis.clientes.dto.UsuarioRequestDTO;
import cl.everis.clientes.dto.UsuarioResponseDTO;
import cl.everis.clientes.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface ServiceUsuario {
    public List <UsuarioResponseDTO> listarUsuarios();
    public UsuarioResponseDTO obtenerUsuario(int id);
    public void insertar(UsuarioRequestDTO usuarioRequestDTO);
    public void modificar(UsuarioRequestDTO usuarioRequestDTO);
    public void eliminar(int id);


}
