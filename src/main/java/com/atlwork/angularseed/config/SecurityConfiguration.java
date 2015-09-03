package com.atlwork.angularseed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.csrf.CsrfFilter;

import com.atlwork.angularseed.config.filter.CsrfCookieGeneratorFilter;
import com.atlwork.angularseed.security.Http401UnauthorizedEntryPoint;
import com.atlwork.angularseed.security.handler.AjaxAuthenticationFailureHandler;
import com.atlwork.angularseed.security.handler.AjaxAuthenticationSuccessHandler;
import com.atlwork.angularseed.security.handler.AjaxLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Autowired
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Autowired
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {

	return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

	web.ignoring().antMatchers("/yo/**/*.{js,html}").antMatchers("/bower_components/**").antMatchers("/properties/**").antMatchers("/assets/**")
		.antMatchers("/console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	http.csrf().and()

	.addFilterAfter(new CsrfCookieGeneratorFilter(), CsrfFilter.class).exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()

	.formLogin().loginProcessingUrl("/api/authentication").successHandler(ajaxAuthenticationSuccessHandler)
		.failureHandler(ajaxAuthenticationFailureHandler).usernameParameter("j_username").passwordParameter("j_password").permitAll().and()

		.logout().logoutUrl("/api/logout").logoutSuccessHandler(ajaxLogoutSuccessHandler).deleteCookies("JSESSIONID").permitAll().and()

		.headers().frameOptions().disable()

		.authorizeRequests().antMatchers("/api/register").permitAll().antMatchers("/api/activate").permitAll().antMatchers("/api/authenticate")
		.permitAll().antMatchers("/api/account/reset_password/init").permitAll().antMatchers("/api/account/reset_password/finish").permitAll();

    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
	return new SecurityEvaluationContextExtension();
    }
}
