package cl.everis.clientes.service

import cl.everis.clientes.dto.UsuarioRequestDTO
import cl.everis.clientes.dto.UsuarioResponseDTO
import cl.everis.clientes.exception.ErrorException
import cl.everis.clientes.model.Usuario
import cl.everis.clientes.repository.UsuarioRepo
import org.springframework.http.HttpStatus
import spock.lang.Specification

class ServiceUsuarioSpec extends Specification{
    ServiceUsuario servicioUsuario

    UsuarioRepo usuarioRepository
    private Usuario usuarioUno;
    private Usuario usuarioDos;

    def setup(){
        this.usuarioRepository = Stub(UsuarioRepo.class)
        this.servicioUsuario = new ServiceUsuarioImpl(this.usuarioRepository)

        this.usuarioUno = Usuario.builder()
                .idUsuario(1)
                .nombre("Luis")
                .clave("123456")
                .email("luis@dominio.cl")
                .contactos("56999999999")
                .build()
        this.usuarioDos = Usuario.builder()
                .idUsuario(2)
                .nombre("Luis")
                .clave("123456")
                .email("luis@dominio.cl")
                .contactos("56999999999")
                .build()
    }

    def "listar usuarios respuesta ok"(){
        given:"dados los siguientes datos"
        List<Usuario> usuarios = new ArrayList<>()

        usuarios.add(this.usuarioUno)
        usuarios.add(this.usuarioDos)

        this.usuarioRepository.findAll() >> usuarios

        when:"listo de usuarios"
        List <UsuarioResponseDTO> respuesta = this.servicioUsuario.listarUsuarios()

        then:"respuesta con lista de usuarios"
        noExceptionThrown()
        respuesta != null

        respuesta.get(0).getIdUsuario().equals(usuarios.get(0).getIdUsuario())
        respuesta.get(0).getNombre().equals(usuarios.get(0).getNombre())
        respuesta.get(0).getClave().equals(usuarios.get(0).getClave())
        respuesta.get(0).getEmail().equals(usuarios.get(0).getEmail())
        respuesta.get(0).getContactos().equals(usuarios.get(0).getContactos())

        respuesta.get(1).getIdUsuario().equals(usuarios.get(1).getIdUsuario())
        respuesta.get(1).getNombre().equals(usuarios.get(1).getNombre())
        respuesta.get(1).getClave().equals(usuarios.get(1).getClave())
        respuesta.get(1).getEmail().equals(usuarios.get(1).getEmail())
        respuesta.get(1).getContactos().equals(usuarios.get(1).getContactos())

    }


    def "listar usuarios respuesta ex"(){
        given:"dados los siguientes datos"
        List<Usuario> usuarios = new ArrayList<>()

        this.usuarioRepository.findAll() >> usuarios

        when:"lista de usuarios"
        List <UsuarioResponseDTO> respuesta = this.servicioUsuario.listarUsuarios()

        then:"respuesta de la lista de usuarios"
        ErrorException error = thrown()
        respuesta == null
        error.getHttpStatus() == HttpStatus.NO_CONTENT
        error.getBody().equals("Sin resultados")
    }

    def "Obtiener usuario"(){
        given:"Usando los siguientes datos"
        Usuario usuario = this.usuarioUno

        this.usuarioRepository.findById(_) >> Optional.of(usuario)

        when:"Lista usuario"
        UsuarioResponseDTO respuesta = this.servicioUsuario.obtenerUsuario(1)

        then:"Respuesta a consulta usuario"
        noExceptionThrown()
        usuario.getIdUsuario().equals(respuesta.getIdUsuario())
        usuario.getNombre().equals(respuesta.getNombre())
        usuario.getClave().equals(respuesta.getClave())
        usuario.getEmail().equals(respuesta.getEmail())
        usuario.getContactos().equals(respuesta.getContactos())
    }

    def "Obtener usuario Ex"(){
        given:"Con los siguientes datos"
        this.usuarioRepository.findById(_) >> Optional.empty()

        when:"Se lista el usuario"
        UsuarioResponseDTO respuesta = this.servicioUsuario.obtenerUsuario(2)

        then:"Respuesta Exeption"
        ErrorException error = thrown()
        error.getHttpStatus() == HttpStatus.NO_CONTENT
        respuesta == null

    }

    def "Insertar usuario"(){
        given:"Con los siguientes datos"
        UsuarioRequestDTO usuarioRequestDTO = UsuarioRequestDTO.builder()
                .idUsuario(1)
                .nombre("Luis")
                .clave("123456")
                .email("luis@dominio.cl")
                .contactos("56999999999")
                .build()
        this.usuarioRepository.findById(_) >> Optional.empty()

        when:"Se ingresa el usuario"
        this.servicioUsuario.insertar(usuarioRequestDTO)

        then:"respuesta a la creación"
        noExceptionThrown()
    }

    def "Insertar usuario ex"(){
        given:"Con los siguientes datos"
        UsuarioRequestDTO usuarioRequestDTO = UsuarioRequestDTO.builder()
                .idUsuario(1)
                .nombre("Luis")
                .clave("123456")
                .email("luis@dominio.cl")
                .contactos("56999999999")
                .build()
        this.usuarioRepository.findById(_) >> Optional.of(usuarioRequestDTO)

        when:"Se ingresa el usuario"
        this.servicioUsuario.insertar(usuarioRequestDTO)

        then:"respuesta a la creación"
        ErrorException error = thrown()
        error.getHttpStatus() == HttpStatus.CONFLICT
    }

    def "Modificar usuario"(){
        given:"Con los siguientes datos"
        UsuarioRequestDTO usuarioRequestDTO = UsuarioRequestDTO.builder()
                .idUsuario(1)
                .nombre("Luis")
                .clave("123456")
                .email("luis@dominio.cl")
                .contactos("56999999999")
                .build()

        UsuarioResponseDTO usuarioModificadoResponseDTO = UsuarioResponseDTO.builder()
                .idUsuario(1)
                .nombre("Luisa")
                .clave("654321")
                .email("luisa@dominio.cl")
                .contactos("56888888888")
                .build()

        this.usuarioRepository.findById(_) >> Optional.of(usuarioRequestDTO)

        when:"Se modifica el usuario"
        this.servicioUsuario.modificar(usuarioRequestDTO)

        then:"Respuesta a la actualización"
        noExceptionThrown()
    }

    def "Eliminacion de usuario"(){
        given:"Con los siguientes datos"
        Usuario usuario = this.usuarioUno

        this.usuarioRepository.findById(_) >> Optional.of(usuario)

        when:"Se elimina el usuario"
        this.servicioUsuario.eliminar(1)

        then:"Se obtiene la siguiente respuesta"
        noExceptionThrown()
    }


    def "Eliminacion Ex"(){
        given:"Con lo siguientes datos"
        this.usuarioRepository.findById(_) >> Optional.empty()

        when: "Se elimina el usuario"
        this.servicioUsuario.eliminar(1)

        then:"Respuesta de la eliminacion"
        ErrorException errorException = thrown()
        errorException.getHttpStatus() == HttpStatus.NOT_FOUND

    }
}
