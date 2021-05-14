package cl.everis.clientes.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="CONTACTO")
public class Contacto implements Serializable {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private int idContacto;

    private String numero ;

    private String codigoCiudad;

    private String codigoPais;

    @ManyToOne
    @JoinColumn(name="rut")
    private Usuario usuario;

}
