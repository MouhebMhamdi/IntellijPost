package net.javaguides.springboot.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.javaguides.springboot.springsecurity.CustomLoginSuccessHandler;

import net.javaguides.springboot.springsecurity.service.UserService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomLoginSuccessHandler successHandler;
    @Autowired
    private DataSource dataSource;
    @Autowired
    UserDetailsService userDetailsService ;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                
                .authorizeRequests()
                
                .antMatchers("/admin/**","/editArchive/**",
                		"/registration**","/registration/**","/adminPresence/**",
                		"/allAdminPresence/**","/absence/**","/listArchive/**",
                		"/addArchives/**","/infos/**","/deleteArchive/**",
                		"/all/**","/searchArchive/**","/Emplois/**","/listEmp/**","/soldeServis/**","/statistique/**",
                		"/parametre/application/**","/parametre/employe/**","/parametre/paramo/**",
                		"/parametre/travail/**","/parametre/**","/statistique/stat/**","/statistique/nbrJour/**",
                		"/searchEmployee/**","/searchPresence/**","/editEmploye/**","/absence/**","/adminPresence/**",
                		"/allAdminPresence/**","/send/**","/pagesAdmin/**","/pageAdmin/**").hasRole("ADMIN")
                
                .antMatchers("/user/**","/archiveUser/**","/edit/**",
                		"/listAll/**","/delete/**","/list/**",
                		"/save/**","/addArchive/**","/EmploisUser/**","/page/**","/pages/**"
                		).hasRole("USER")
                
                .antMatchers("/parametreCompte/param/**").hasAnyRole("SIMPLE","USER")
                
                .antMatchers("/simpleUser/**","/tempsSimEmp/**").hasRole("SIMPLE")
                
                .antMatchers("/","/public/**", "/resources/**","/resources/public/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/vendor/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/static/**","/fontawesome/**").permitAll()
                .antMatchers("/resetPassword/**","/resetPasswordpost/**","/newPassword/**","/newPassWord/**","/login/**").permitAll()
                 
               .anyRequest().authenticated()
                    
                .and()
                    .formLogin()
                        .loginPage("/login")
                            .successHandler(successHandler)
                            .permitAll()
                            
                .and()
                .rememberMe().rememberMeCookieName("remember-me")
                .tokenValiditySeconds(24 * 60 * 60)
                .tokenRepository(tokenRepository())
                
                .and()
                    .logout()
                    	.deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) .logoutSuccessUrl("/login")
                        .deleteCookies("remember-me")
                       
                .permitAll()
                
                
                
                
                ;
        
    }
 
    @Bean
    public PersistentTokenRepository tokenRepository() {
      JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
      jdbcTokenRepositoryImpl.setDataSource(dataSource);
      return jdbcTokenRepositoryImpl;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
    	
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        auth.setUserDetailsService(userDetailsService);
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        
    }
    
}
