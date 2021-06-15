package cl.everis.clientes.dto;

import cl.everis.clientes.model.Usuario;
import lombok.*;

import java.io.Serializable;

/**
 * Clase DTO de contacto del Request
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContactoRequestDTO implements Serializable {
    private int idContacto;

    private String numero ;

    private String codigoCiudad;

    private String codigoPais;

    private Usuario usuario;
}
