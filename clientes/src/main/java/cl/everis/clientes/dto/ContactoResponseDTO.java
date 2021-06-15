package cl.everis.clientes.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Clase DTO de contacto del Response
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContactoResponseDTO implements Serializable {

    private String numero ;

    private String codigoCiudad;

    private String codigoPais;

}
