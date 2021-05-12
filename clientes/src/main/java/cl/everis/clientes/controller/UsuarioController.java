package cl.everis.clientes.controller;

import cl.everis.clientes.dto.UsuarioRequestDTO;
import cl.everis.clientes.dto.UsuarioResponseDTO;
import cl.everis.clientes.service.ServiceUsuario;
import cl.everis.clientes.model.Usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private ServiceUsuario servicioUsuario;

    public UsuarioController (ServiceUsuario servicioUsuario){
        this.servicioUsuario = servicioUsuario;
    }

    @GetMapping(value = "")
    public ResponseEntity <List <UsuarioResponseDTO>> listarUsuario(){
        return new ResponseEntity<>(this.servicioUsuario.listarUsuarios(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuario(@Validated @PathVariable("id") int id){
        return new ResponseEntity<>(this.servicioUsuario.obtenerUsuario(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity insertar(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        this.servicioUsuario.insertar(usuarioRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity modificar(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        this.servicioUsuario.modificar(usuarioRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity eliminar(@PathVariable("id") int id){
        this.servicioUsuario.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
