package cl.everis.clientes.dto;

import lombok.*;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioResponseDTO {

    private long rut;

    private String nombre;

    private String clave;

    private String email;

    private List <ContactoResponseDTO> contactos;


}
