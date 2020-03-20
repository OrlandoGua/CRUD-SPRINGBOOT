package mx.com.oga.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.oga.domain.Persona;
import mx.com.oga.servicio.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j

public class ControladorInicio {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/")
    public String inicio( Model model, @AuthenticationPrincipal User user) {

        List<Persona> personas = (List<Persona>) personaService.listarPersonas();

        log.info("estamos ejecuntado el controlador de tipo MVC");
        log.info("Usuario que hizo login: " + user);
        model.addAttribute("personas", personas);
        
        var saldoTotal=0D;
        for(Persona p:personas){
        saldoTotal +=p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);     
             model.addAttribute( "totalClientes", personas.size());
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona , Errors erros) {
        if(erros.hasErrors()){
        return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model) {
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
    personaService.eliminar(persona);
    return "redirect:/";
    }

}
