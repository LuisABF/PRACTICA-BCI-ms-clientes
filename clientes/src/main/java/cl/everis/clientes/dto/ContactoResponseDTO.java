package cl.everis.clientes.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContactoResponseDTO {

    private String numero ;

    private String codigoCiudad;

    private String codigoPais;

}
