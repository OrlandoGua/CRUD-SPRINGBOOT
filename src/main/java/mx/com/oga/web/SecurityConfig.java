package mx.com.oga.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//clase para login
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
//    auth.inMemoryAuthentication()
//            .withUser("admin")
//            .password("{noop}123")
//            .roles("ADMIN","USER")
//            .and()
//            .withUser("user")
//            .password("{noop}123")
//            .roles("USER")
//            ;
//    }
    //cargamos usuarios de la base de datos con jpa
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public  BCryptPasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
    build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //metodo restringimos permisos de alguna accion
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/editar/**", "/agregar/**", "/eliminar/**")
                .hasRole("ADMIN")
                .antMatchers("/")
                .hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login") //indicamo cual es la pagina de login
                .and()
                .exceptionHandling().accessDeniedPage("/errores/403");
    }

}
