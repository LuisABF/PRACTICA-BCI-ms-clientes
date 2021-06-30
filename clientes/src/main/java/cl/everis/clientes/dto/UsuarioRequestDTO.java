package cl.everis.clientes.dto;

import cl.everis.clientes.model.Contacto;
import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * Clase DTO de usuario del Request
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioRequestDTO implements Serializable {
    @Min(value=1000000, message = "Debe ingresar un rut valido")
    @NotNull(message = "no puede ser nulo")
    private long rut;

    @NotNull(message = "no puede ser nulo")
    @Size(min=2, message = "Debe ingresar al menos 2 valores")
    private String nombre;

    @Pattern(regexp = "[A-Z]{1}[a-z]*[0-9]{2}", message = "Debe ingresar una mayuscula, minusculas y 2 números")
    private String clave;

    @NotBlank
    @Email (message = "Ingrese una dirección de correo electrónico válida ")
    private String email;

    private List<Contacto> contactos;

    private String created;

    private String modified;

    private String lastLogin;

    private String token;

    private String isactive;

}
