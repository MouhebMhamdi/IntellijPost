package net.javaguides.springboot.springsecurity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	protected void handle(HttpServletRequest request ,HttpServletResponse response,Authentication authentication ) throws IOException{
		String targetUrl=detemineTargerUrl(authentication);
		if(response.isCommitted()) {
			return;
		}
		RedirectStrategy redirectStrategy =new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
protected String detemineTargerUrl(Authentication authentication) {
	String url="/login?error=true";
	Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
	List<String> roles=new ArrayList<String>();
	for(GrantedAuthority a: authorities) {
		roles.add(a.getAuthority());
	}
	if(roles.contains("ROLE_ADMIN")) {
		url="/admin";
	}else if(roles.contains("ROLE_USER")) {
		url="/user";
	}else {
		url="/simpleUser";
	}
	return url;
}
protected String detemineTargerUrl(Authentication authentication,String urll) {
	 String url="/login?error=true";
	Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
	List<String> roles=new ArrayList<String>();
	for(GrantedAuthority a: authorities) {
		roles.add(a.getAuthority());
	}
	if(roles.contains("ROLE_ADMIN") && urll.equals("/")) {
		url="/admin";
	}else if(roles.contains("ROLE_USER") && urll.equals("/")) {
		url="/user";
	}else if(roles.contains("ROLE_ADMIN") && urll.equals("/")){
		url="/simpleUser";
	}
	return url;
}
}
