package gr.dit.hua.divorce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/more_information").permitAll()
                .requestMatchers("/help").permitAll()
                .requestMatchers("/member/**").hasRole("ADMIN")
                .requestMatchers("/my_divorces" ).hasAnyRole("LAWYER", "SPOUSE", "NOTARY")
                .requestMatchers("/divorce/deleteDivorce").hasRole("LAWYER")
                .requestMatchers("/divorce/getDivorces").hasRole("ADMIN")
                .requestMatchers("/divorce/saveDivorce").hasRole("LAWYER")
                .requestMatchers("/divorce/approveDivorce").hasRole("NOTARY")
                .requestMatchers("/cdp").hasRole("LAWYER")
                .requestMatchers("/member_names").hasRole("LAWYER")
                .requestMatchers("/document_details").hasRole("LAWYER")
                .requestMatchers("/confirmation_of_divorce").hasAnyRole("SPOUSE", "LAWYER")
                .requestMatchers("/divorce_cancellation").hasRole("LAWYER")
                .requestMatchers("/account_details").hasRole("NOTARY")
                .requestMatchers("/notarialActionId/**").hasRole("NOTARY")
                .requestMatchers("/cu").hasRole("ADMIN")
                //μόλις φτιαχτεί να το αλλάξω
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/my_divorces", true).permitAll()
                .and().logout();



        http.headers().frameOptions().sameOrigin();

        return http.build();

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->
                web.ignoring().requestMatchers(
                        "/css/**", "/js/**", "/images/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
