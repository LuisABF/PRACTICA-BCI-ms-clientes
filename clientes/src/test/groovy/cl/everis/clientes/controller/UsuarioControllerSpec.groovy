package cl.everis.clientes.controller

import cl.everis.clientes.dto.ContactoResponseDTO
import cl.everis.clientes.dto.UsuarioRequestDTO
import cl.everis.clientes.dto.UsuarioResponseDTO
import cl.everis.clientes.service.ServiceUsuario
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import spock.lang.Specification

class UsuarioControllerSpec extends Specification{
    UsuarioController usuarioController

    ServiceUsuario servicioUsuario = Stub(ServiceUsuario.class)
    UsuarioResponseDTO usuarioResponseDTO
    UsuarioRequestDTO usuarioRequestDTO
    ContactoResponseDTO contactoResponseDTO

    def setup(){

        this.usuarioResponseDTO = UsuarioResponseDTO.builder()
                .rut(6968503)
                .nombre("Luis")
                .clave("123456")
                .email("luis@dominio.cl")
                .contactos(null)
                .build()

        this.usuarioRequestDTO = UsuarioRequestDTO.builder()
                .rut(13950721)
                .nombre("Luis")
                .clave("123456")
                .email("luis@dominio.cl")
                .contactos(null)
                .build()

        this.usuarioController = new UsuarioController(this.servicioUsuario)
    }

    def "listar usuarios"(){
        given: "dados los siguientes datos"

        List<UsuarioResponseDTO> usuarios = new ArrayList<>()

        usuarios.add(this.usuarioResponseDTO)

        this.servicioUsuario.listarUsuarios() >> usuarios

        when: "listo los usuario"
        ResponseEntity <List <UsuarioResponseDTO>> respuesta = this.usuarioController.listarUsuario()

        then: "obtengo un status ok"
        respuesta.getStatusCode() == HttpStatus.OK
        respuesta.getBody().get(0).getRut() == 6968503
        respuesta.getBody().get(0).getNombre().equals("Luis")
        respuesta.getBody().get(0).getClave().equals("123456")
        respuesta.getBody().get(0).getEmail().equals("luis@dominio.cl")
    }

    def "Listar Usuario"(){
        given:"dado los siguientes datos de usuario"
        this.servicioUsuario.obtenerUsuario(usuarioResponseDTO.rut) >> this.usuarioResponseDTO

        when:"listo el usuario"
        ResponseEntity<UsuarioResponseDTO> respuesta = this.usuarioController.obtenerUsuario(6968503)

        then:"obtengo el OK"
        respuesta.getStatusCode() == HttpStatus.OK
        respuesta.getBody().getRut() == 6968503
        respuesta.getBody().getNombre().equals("Luis")
        respuesta.getBody().getClave().equals("123456")
        respuesta.getBody().getEmail().equals("luis@dominio.cl")

    }

    def "Insertar usuario"(){
        given:"Con los siguientes datos"
        this.servicioUsuario.insertar(_) >> null

        when:"Insertar usuario"
        ResponseEntity respuesta = this.usuarioController.insertar(this.usuarioRequestDTO)

        then:"se obtiene un ok"
        respuesta.getStatusCode() == HttpStatus.CREATED

    }

    def "actualizar usuario"(){
        given:"Con los siguientes datos"
        this.servicioUsuario.modificar(_) >> null

        when:"Se actualiza el usuario"
        ResponseEntity respuesta = this.usuarioController.modificar(this.usuarioRequestDTO)

        then:"Respuesta OK"
        respuesta.getStatusCode() == HttpStatus.OK
    }

    def "Eliminar usuario"(){
        given:"Con los siguientes datos"
        this.servicioUsuario.modificar(_) >> null

        when:"Actualiza usuario"
        ResponseEntity respuesta = this.usuarioController.eliminar(1)

        then:"Respuesta Ok"
        respuesta.getStatusCode() == HttpStatus.NO_CONTENT
    }

}
