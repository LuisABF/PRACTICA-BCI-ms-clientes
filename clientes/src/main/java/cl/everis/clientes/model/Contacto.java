package cl.everis.clientes.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase Entity para la persistencia de los telefonos del usuario
 */

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="contactos")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContacto;

    private String numero ;

    private String codigoCiudad;

    private String codigoPais;

    @ManyToOne
    @JoinColumn(name="rut")
    private Usuario usuario;

}
