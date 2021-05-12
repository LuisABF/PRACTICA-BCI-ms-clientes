package cl.everis.clientes.service;

import cl.everis.clientes.dto.UsuarioRequestDTO;
import cl.everis.clientes.dto.UsuarioResponseDTO;
import cl.everis.clientes.model.Usuario;
import cl.everis.clientes.repository.UsuarioRepo;
import cl.everis.clientes.exception.ErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceUsuarioImpl implements ServiceUsuario{

    private UsuarioRepo repo;

    public ServiceUsuarioImpl (UsuarioRepo repo){
        this.repo = repo;
    }

    @Override
    public List <UsuarioResponseDTO> listarUsuarios() {

        List <Usuario> listaUsuarios = repo.findAll();

        if(listaUsuarios.size() == 0){
            throw new ErrorException(HttpStatus.NO_CONTENT, "Sin resultados");
        }

        List <UsuarioResponseDTO> listaUsuarioResponseDTO = new ArrayList<>();

        listaUsuarios.forEach(usuario ->
              listaUsuarioResponseDTO.add(UsuarioResponseDTO.builder()
                        .idUsuario(usuario.getIdUsuario())
                        .nombre(usuario.getNombre())
                        .clave(usuario.getClave())
                        .email(usuario.getEmail())
                        .contactos(usuario.getContactos())
                        .build())
                );

        return listaUsuarioResponseDTO;
    }

    @Override
    public UsuarioResponseDTO obtenerUsuario(int id){

        Optional <Usuario> usuario = repo.findById(id);

        if(usuario.isPresent()) {
            Usuario usr = usuario.get();

            return UsuarioResponseDTO.builder()
                    .idUsuario(usr.getIdUsuario())
                    .clave(usr.getClave())
                    .nombre(usr.getNombre())
                    .contactos(usr.getContactos())
                    .email(usr.getEmail())
                    .build();
        }else{
            throw new ErrorException(HttpStatus.NO_CONTENT, "Sin resultados");
        }
    }

    @Override
    public void insertar(UsuarioRequestDTO usuarioRequestDTO){

        Optional <Usuario> buscarUsuarioExistente = repo.findById(usuarioRequestDTO.getIdUsuario());

        if(buscarUsuarioExistente.isPresent()){
            throw new ErrorException(HttpStatus.CONFLICT, "Debe ingresar un idUsuario diferente");
        }else{
            Usuario usuario = Usuario.builder()
                    .idUsuario(usuarioRequestDTO.getIdUsuario())
                    .clave(usuarioRequestDTO.getClave())
                    .nombre(usuarioRequestDTO.getNombre())
                    .contactos(usuarioRequestDTO.getContactos())
                    .email(usuarioRequestDTO.getEmail())
                    .build();

            repo.save(usuario);
        }

    }

    @Override
    public void modificar(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = Usuario.builder()
                .idUsuario(usuarioRequestDTO.getIdUsuario())
                .clave(usuarioRequestDTO.getClave())
                .nombre(usuarioRequestDTO.getNombre())
                .contactos(usuarioRequestDTO.getContactos())
                .email(usuarioRequestDTO.getEmail())
                .build();
        repo.save(usuario);
    }

    @Override
    public void eliminar(@PathVariable("id") int id){
        Optional <Usuario> usuarioRequestDTO = repo.findById(id);

        if(usuarioRequestDTO.isPresent()) {
            repo.deleteById(id);
        }else{
            throw new ErrorException(HttpStatus.NOT_FOUND, "No Encontrado");
        }

    }

}
