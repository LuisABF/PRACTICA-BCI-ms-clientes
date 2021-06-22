package cl.everis.clientes.service;

import cl.everis.clientes.dto.*;
import cl.everis.clientes.model.Contacto;
import cl.everis.clientes.model.Usuario;
import cl.everis.clientes.repository.UsuarioRepo;
import cl.everis.clientes.exception.ErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase de implementacion de los metodos declarados en la interfaz,
 * donde se realiza la logica de negocio para poder persistir mediante JpaRepository en base de datos
 *
 * @author Luis Bascunan
 *
 */

@Service
public class ServiceUsuarioImpl implements ServiceUsuario{

    private final UsuarioRepo usuarioRespository;

    public ServiceUsuarioImpl(UsuarioRepo repo){
        this.usuarioRespository = repo;
    }

    @Value("${conflictoId}")
    private String conflictoId;

    /**
     * Metodo que inserta los registros de usuario
     */
    @Override
    public List <UsuarioResponseDTO> listarUsuarios() {

        List <Usuario> listaUsuarios = usuarioRespository.findAll();

        if(listaUsuarios.size() == 0){
            throw new ErrorException(HttpStatus.NO_CONTENT, "Sin resultados");
        }
        List <UsuarioResponseDTO> listaUsuarioResponseDTO = new ArrayList<>();

        listaUsuarios.forEach(usuario ->
                {
                List <ContactoResponseDTO> ListaContactoResponseDTOS = usuario.getContactos()
                        .stream()
                        .map(contacto -> ContactoResponseDTO.builder()
                                .codigoPais(contacto.getCodigoPais())
                                .codigoCiudad(contacto.getCodigoCiudad())
                                .numero(contacto.getNumero())
                                .build())
                        .collect(Collectors.toList());

                listaUsuarioResponseDTO.add(UsuarioResponseDTO.builder()
                        .rut(usuario.getRut())
                        .nombre(usuario.getNombre())
                        .clave(usuario.getClave())
                        .email(usuario.getEmail())
                        .contactos(ListaContactoResponseDTOS)
                        .build());
                }
        );

        return listaUsuarioResponseDTO;

    }

    /**
     * Metodo que obtiene los datos de un usuario determinado
     */
    @Override
    public UsuarioResponseDTO obtenerUsuario(long id){

        Optional <Usuario> usuarioRepository = usuarioRespository.findById(id);

        if(usuarioRepository.isPresent()) {
            Usuario usuario = usuarioRepository.get();


            List<ContactoResponseDTO> contactos = usuario.getContactos()
                    .stream()
                    .map(contacto -> ContactoResponseDTO.builder()
                            .codigoPais(contacto.getCodigoPais())
                            .codigoCiudad(contacto.getCodigoCiudad())
                            .numero(contacto.getNumero())
                            .build())
                    .collect(Collectors.toList());

            return UsuarioResponseDTO.builder()
                    .rut(usuario.getRut())
                    .clave(usuario.getClave())
                    .nombre(usuario.getNombre())
                    .contactos(contactos)
                    .email(usuario.getEmail())
                    .build();

        }else{
            throw new ErrorException(HttpStatus.NOT_FOUND, "No existe el recurso");
        }
    }

    /**
     * Metodo que inserta los registros de usuario
     */
    @Override
    @Transactional
    public void insertar(UsuarioRequestDTO usuarioRequestDTO){

        Optional <Usuario> buscarUsuarioExistente = usuarioRespository.findById(usuarioRequestDTO.getRut());

        if(buscarUsuarioExistente.isPresent()){
            throw new ErrorException(HttpStatus.CONFLICT, conflictoId);
        }else {

            List<Contacto> listaContactos = new ArrayList<>();

            if (usuarioRequestDTO.getContactos() == null){
                usuarioRequestDTO.setContactos(listaContactos);
            }else{
                for(int i = 0; i < usuarioRequestDTO.getContactos().size(); i++) {
                    Contacto contacto = Contacto.builder()
                            .usuario(Usuario.builder().rut(usuarioRequestDTO.getRut()).build())
                            .numero(usuarioRequestDTO.getContactos().get(i).getNumero())
                            .codigoCiudad(usuarioRequestDTO.getContactos().get(i).getCodigoCiudad())
                            .codigoPais(usuarioRequestDTO.getContactos().get(i).getCodigoPais())
                            .build();
                    listaContactos.add(contacto);
                }
            }

            Usuario usuario = Usuario.builder()
                    .rut(usuarioRequestDTO.getRut())
                    .clave(usuarioRequestDTO.getClave())
                    .nombre(usuarioRequestDTO.getNombre())
                    .contactos(listaContactos)
                    .email(usuarioRequestDTO.getEmail())
                    .created(LocalDateTime.now().toString())
                    .modified(LocalDateTime.now().toString())
                    .isactive(true)
                    .lastLogin(LocalDateTime.now().toString())
                    .build();

            usuarioRespository.save(usuario);
        }

    }

    /**
     * Metodo que actualiza los datos de un usuario determinado
     */
    @Override
    @Transactional
    public void modificar( UsuarioRequestDTO usuarioRequestDTO){

        List<Contacto> listaContactos = new ArrayList<>();

        if(usuarioRequestDTO.getContactos() == null) {
            listaContactos.add(Contacto.builder().build());
        }else{
            for (int i = 0; i < usuarioRequestDTO.getContactos().size(); i++) {
                Contacto contacto = Contacto.builder()
                        .usuario(Usuario.builder().rut(usuarioRequestDTO.getRut()).build())
                        .numero(usuarioRequestDTO.getContactos().get(i).getNumero())
                        .codigoCiudad(usuarioRequestDTO.getContactos().get(i).getCodigoCiudad())
                        .codigoPais(usuarioRequestDTO.getContactos().get(i).getCodigoPais())
                        .build();
                listaContactos.add(contacto);
            }
        }
        Usuario usuario = Usuario.builder()
                .rut(usuarioRequestDTO.getRut())
                .clave(usuarioRequestDTO.getClave())
                .nombre(usuarioRequestDTO.getNombre())
                .contactos(listaContactos)
                .email(usuarioRequestDTO.getEmail())
                .build();
        usuarioRespository.save(usuario);
    }


    /**
     * Metodo que elimina un usuario
     */
    @Override
    @Transactional
    public void eliminar(long id){
        Optional <Usuario> usuarioRequestDTO = usuarioRespository.findById(id);

        if(usuarioRequestDTO.isPresent()) {
            usuarioRespository.deleteById(id);
        }else{
            throw new ErrorException(HttpStatus.NOT_FOUND, "No Encontrado");
        }

    }

}
