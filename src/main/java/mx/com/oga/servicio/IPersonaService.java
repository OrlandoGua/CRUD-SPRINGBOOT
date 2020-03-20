
package mx.com.oga.servicio;

import java.util.List;
import mx.com.oga.domain.Persona;


public interface IPersonaService {
    
    public List<Persona>listarPersonas();
    
    public void guardar(Persona persona);
    
    public void eliminar (Persona persona);
    
    public Persona encontrarPersona(Persona persona);
    
}