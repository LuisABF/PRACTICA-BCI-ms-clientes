package cl.everis.clientes.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    private int idUsuario;

    @Column(name="nombre", length = 50)
    private String nombre;

    private String clave;

    @Email(message = "Email should be valid")
    private String email;

    private String contactos;

    private String created;

    private String modified;

    private String lastLogin;

    private String token;

    private String isactive;


}
