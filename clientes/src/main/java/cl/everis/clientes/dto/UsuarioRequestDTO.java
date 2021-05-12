package cl.everis.clientes.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioRequestDTO {

    private int idUsuario;
    private String nombre;
    private String clave;
    private String email;
    private String contactos;
}
