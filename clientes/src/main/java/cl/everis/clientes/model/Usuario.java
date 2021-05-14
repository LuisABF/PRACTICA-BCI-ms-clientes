package cl.everis.clientes.model;
import lombok.*;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="USUARIO")
public class Usuario implements Serializable {

    @Id
    private long rut;

    @Column(name="nombre", length = 50)
    private String nombre;

    private String clave;

    private String email;

    @OneToMany(mappedBy = "usuario")
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    private List <Contacto> contactos;

    private String created;

    private String modified;

    private String lastLogin;

    private String token;

    private boolean isactive;


}
