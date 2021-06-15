package cl.everis.clientes.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Clase DTO de contacto del Response
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioResponseDTO implements Serializable {

    private long rut;

    private String nombre;

    private String clave;

    private String email;

    private List <ContactoResponseDTO> contactos;


}
