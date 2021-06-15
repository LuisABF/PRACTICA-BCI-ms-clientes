package cl.everis.clientes.repository;

import cl.everis.clientes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaz Repository que permite el acceso a la base de datos de usuarios
 */
 @Repository
public interface UsuarioRepo extends JpaRepository <Usuario, Long>{
  // public List<Usuario> finByRut(Long rut);
}
