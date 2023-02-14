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
                .requestMatchers("/notary").hasRole("NOTARY")
                .requestMatchers("/lawyer").hasRole("LAWYER")
                .requestMatchers("/spouse").hasRole("SPOUSE")
                .requestMatchers("/cdp").permitAll()
                .requestMatchers("/member_names").permitAll()
                .requestMatchers("/document_details").permitAll()
                .requestMatchers("/divorce_confirmation").permitAll()
                .requestMatchers("/divorce_cancellation").permitAll()
                .requestMatchers("/account_details").permitAll()
                //μόλις φτιαχτεί να το αλλάξω
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/", true).permitAll()
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
