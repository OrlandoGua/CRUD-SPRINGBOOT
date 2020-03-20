
package mx.com.oga.dao;

import mx.com.oga.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioDao extends JpaRepository <Usuario, Long>{
    
    Usuario findByUsername(String username);
    
}
