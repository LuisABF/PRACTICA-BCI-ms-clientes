package cl.everis.clientes.service;

import cl.everis.clientes.dto.*;
import cl.everis.clientes.model.Contacto;
import cl.everis.clientes.model.Usuario;
import cl.everis.clientes.repository.UsuarioRepo;
import cl.everis.clientes.exception.ErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceUsuarioImpl implements ServiceUsuario{

    private UsuarioRepo usuarioRespository;


    public ServiceUsuarioImpl(UsuarioRepo repo){
        this.usuarioRespository = repo;
    }

    @Override
    public List <UsuarioResponseDTO> listarUsuarios() {

        List <Usuario> listaUsuarios = usuarioRespository.findAll();

        if(listaUsuarios.size() == 0){
            throw new ErrorException(HttpStatus.NO_CONTENT, "Sin resultados");
        }
        List <UsuarioResponseDTO> listaUsuarioResponseDTO = new ArrayList<>();

        listaUsuarios.forEach(usuario ->
                {
                    List <ContactoResponseDTO> ListaContactoResponseDTOS = new ArrayList<>();
                    usuario.getContactos().forEach(contacto -> {
                            ListaContactoResponseDTOS.add(ContactoResponseDTO.builder()
                                    .codigoPais(contacto.getCodigoPais())
                                    .codigoCiudad(contacto.getCodigoCiudad())
                                    .numero(contacto.getNumero())
                                    .build());
                        });

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

    @Override
    public UsuarioResponseDTO obtenerUsuario(long id){

        Optional <Usuario> usuarioRepository = usuarioRespository.findById(id);

        if(usuarioRepository.isPresent()) {
            Usuario usuario = usuarioRepository.get();

            List <ContactoResponseDTO> ListaContactoResponseDTOS = new ArrayList<>();

            for(int i = 0; i < usuario.getContactos().size() ; i++) {
                ContactoResponseDTO contactoResponseDTO = ContactoResponseDTO.builder()
                        .codigoPais(usuario.getContactos().get(i).getCodigoPais())
                        .codigoCiudad(usuario.getContactos().get(i).getCodigoCiudad())
                        .numero(usuario.getContactos().get(i).getNumero())
                        .build();

                ListaContactoResponseDTOS.add(contactoResponseDTO);
            }

            return UsuarioResponseDTO.builder()
                    .rut(usuario.getRut())
                    .clave(usuario.getClave())
                    .nombre(usuario.getNombre())
                    .contactos(ListaContactoResponseDTOS)
                    .email(usuario.getEmail())
                    .build();

        }else{
            throw new ErrorException(HttpStatus.NO_CONTENT, "Sin resultados");
        }
    }

    @Override
    @Transactional
    public void insertar(UsuarioRequestDTO usuarioRequestDTO){

        Optional <Usuario> buscarUsuarioExistente = usuarioRespository.findById(usuarioRequestDTO.getRut());

        if(buscarUsuarioExistente.isPresent()){
            throw new ErrorException(HttpStatus.CONFLICT, "Debe ingresar un idUsuario diferente");
        }else {

            List<Contacto> listaContactos = new ArrayList<>();

            if (usuarioRequestDTO.getContactos() == null){
                    List <Contacto> listaContactosResq = new ArrayList<>();
                usuarioRequestDTO.setContactos(listaContactosResq);
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

    @Override
    @Transactional
    public void modificar(@RequestBody UsuarioRequestDTO usuarioRequestDTO){

        List<Contacto> listaContactos = new ArrayList<>();

        for(int i = 0; i < usuarioRequestDTO.getContactos().size(); i++) {
            Contacto contacto = Contacto.builder()
                    .usuario(Usuario.builder().rut(usuarioRequestDTO.getRut()).build())
                    .numero(usuarioRequestDTO.getContactos().get(i).getNumero())
                    .codigoCiudad(usuarioRequestDTO.getContactos().get(i).getCodigoCiudad())
                    .codigoPais(usuarioRequestDTO.getContactos().get(i).getCodigoPais())
                    .build();

            listaContactos.add(contacto);
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

    @Override
    @Transactional
    public void eliminar(@PathVariable("id") long id){
        Optional <Usuario> usuarioRequestDTO = usuarioRespository.findById(id);

        if(usuarioRequestDTO.isPresent()) {
            usuarioRespository.deleteById(id);
        }else{
            throw new ErrorException(HttpStatus.NOT_FOUND, "No Encontrado");
        }

    }

}
