package cl.everis.clientes.dto;

import cl.everis.clientes.model.Usuario;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContactoRequestDTO {
    private int idContacto;

    private String numero ;

    private String codigoCiudad;

    private String codigoPais;

    private Usuario usuario;
}
