package cl.everis.clientes.dto;

import cl.everis.clientes.model.Contacto;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioRequestDTO {
    @Min(value=1000000, message = "Debe ingresar un rut valido")
    private long rut;

    @NotNull(message = "no puede ser nulo")
    @Size(min=2, message = "Debe ingresar al menos 2 valores")
    private String nombre;

    @Pattern(regexp = "([A-Z])([a-z]*)", message = "La contraseña debe tener una Mayuscula, letras minúsculas y dos numeros")

    private String clave;

    @Email(message = "Ingrese Mail valido")
    private String email;

    private List<Contacto> contactos;

    private String created;

    private String modified;

    private String lastLogin;

    private String token;

    private String isactive;

}