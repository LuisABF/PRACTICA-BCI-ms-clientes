package cl.everis.clientes.controller;

import cl.everis.clientes.dto.UsuarioRequestDTO;
import cl.everis.clientes.dto.UsuarioResponseDTO;
import cl.everis.clientes.dto.error.ErrorDTO;
import cl.everis.clientes.service.ServiceUsuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Clase que gestiona la exposicion del servicio usuario mediante protocolo REST.
 *
 * @author Luis Bascunan.
 *
 */
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private final ServiceUsuario servicioUsuario;

    public UsuarioController (ServiceUsuario servicioUsuario){
        this.servicioUsuario = servicioUsuario;
    }

    /**
     * [GET] Metodo que permite obtener una lista de usuarios
     * @return
     */
    @GetMapping
    public ResponseEntity <List <UsuarioResponseDTO>> listarUsuario(){
        return new ResponseEntity<>(this.servicioUsuario.listarUsuarios(), HttpStatus.OK);
    }

    /**
     * [GET] Metodo que permite obtener datos de un determinado usuario
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity <UsuarioResponseDTO> obtenerUsuario(@Validated @PathVariable("id") long id){
        return new ResponseEntity<>(this.servicioUsuario.obtenerUsuario(id), HttpStatus.OK);
    }

    /**
     * [POST] Metodo que permite insertar usuario
     * @param usuarioRequestDTO
     * @return
     */
    @PostMapping(value="")
    public ResponseEntity<?> insertar(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        this.servicioUsuario.insertar(usuarioRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * [PUT] Metodo que permite actualizar un usuario
     *
     * @param usuarioRequestDTO
     * @return
     */
    @PutMapping(value="")
    public ResponseEntity modificar(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        this.servicioUsuario.modificar(usuarioRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * [Delete] Metodo que permite eliminar
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity eliminar(@PathVariable("id") long id){
        this.servicioUsuario.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
