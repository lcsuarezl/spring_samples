package co.com.jvirtualstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.authorizeRequests().anyRequest().authenticated();
        try {
            http.authorizeRequests(a -> a
                    .mvcMatchers(HttpMethod.GET,"/api/product/**").hasAuthority("READ")
                    .mvcMatchers(HttpMethod.GET,"/api/product/search**").hasAuthority("READ")
                    .mvcMatchers(HttpMethod.POST, "/api/product").hasAuthority("WRITE")
                    .mvcMatchers(HttpMethod.PUT, "/api/product").hasAuthority("WRITE")
                    .mvcMatchers(HttpMethod.DELETE, "/api/product/**").hasAuthority("WRITE")
            ).httpBasic();

            http.csrf().disable();

                   /*
                    .antMatchers("/swagger-ui.html").permitAll()
                    .antMatchers("/swagger-resources/**").permitAll()
                    .antMatchers("/actuator/health/**").permitAll()
                    .antMatchers("/actuator/info/**").permitAll()
                    .antMatchers("/api/**").hasRole("USER");
                    */
        } catch (Exception e) {
            throw new RuntimeException("Spring Security had a catastrophic error", e);
        }
    }

    @Bean
    public UserDetailsService users(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
