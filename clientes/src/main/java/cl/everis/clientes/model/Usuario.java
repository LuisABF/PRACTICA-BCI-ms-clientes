package cl.everis.clientes.model;
import lombok.*;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.List;

/**
 * Clase Entity para la persistencia de los datos de usuario
 */

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="usuarios")
public class Usuario {

    @Id
    private long rut;

    @Column(name="nombre", length = 50)
    private String nombre;

    private String clave;

    private String email;

    @OneToMany(mappedBy = "usuario")
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    private List <Contacto> contactos;

    @Column(name="created_at")
    private String created;

    @Column(name="update_at")
    private String modified;

    private String lastLogin;

    private String token;

    private boolean isactive;


}
