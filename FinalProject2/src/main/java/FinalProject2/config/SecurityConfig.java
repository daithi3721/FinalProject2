package FinalProject2.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity web) throws Exception {
		web.formLogin().loginPage("/login").defaultSuccessUrl("/inpg01").failureUrl("/login-error").permitAll();
		web.authorizeRequests().antMatchers("/css/**", "/images/**", "/js/**").permitAll().anyRequest().authenticated();
	}
}