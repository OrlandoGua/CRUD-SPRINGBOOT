
package mx.com.oga.dao;

import mx.com.oga.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface IPersonaDao extends JpaRepository <Persona, Long>{
    
}
