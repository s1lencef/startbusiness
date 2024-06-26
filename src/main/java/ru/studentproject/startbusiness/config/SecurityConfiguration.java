package ru.studentproject.startbusiness.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import java.util.Properties;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/registration**").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers( "/style/**").permitAll()
                        .requestMatchers( "/admin/**").hasRole("ADMIN")
                        .requestMatchers("/forgot-password").permitAll()
                        .requestMatchers("/reset-password").permitAll()
                        .requestMatchers("/upload").hasRole("USER")
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/profile/**").hasRole("USER")
                        .requestMatchers("/form/**").hasRole("USER")
                        .requestMatchers("/download").permitAll()
                        .requestMatchers("/proxy/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .invalidateHttpSession(true)
                ).sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                ).httpBasic(Customizer.withDefaults()).csrf(Customizer.withDefaults());

        return http.build();
    }
    private JavaMailSenderImpl createMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("startbusineshelp@gmail.com");
        mailSender.setPassword("jpys rlye qgra shqk");
        return mailSender;
    }
    private void setEmailProperties(JavaMailSenderImpl mailSender){
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = createMailSender();

        setEmailProperties(mailSender);

        return mailSender;
    }
}