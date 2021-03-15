package net.javaguides.springboot.springsecurity.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.web.dto.compteParametreDto;

@Controller
@RequestMapping("/parametreCompte")
public class parametreCompteController {
	@Autowired
	private final UserRepository userr;
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	public parametreCompteController(UserRepository userr) {
		this.userr=userr;
	}
	@GetMapping("param")
		public String parameter(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	for(Role rr:us.getRoles()) {
    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
    			return "redirect:/logout";
    		}
    	}
		model.addAttribute("userConnect", us);
		boolean hasSimpleRole = auth.getAuthorities().stream()
	  	          .anyMatch(r -> r.getAuthority().equals("ROLE_SIMPLE"));
			if(hasSimpleRole) {
				return "/simpleUser/parametreCompte";
			}
		return "/user/parametreCompte";
	}
	@GetMapping("nom&prenom")
	public String parameternom(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("nom","true");
		boolean hasSimpleRole = auth.getAuthorities().stream()
  	          .anyMatch(r -> r.getAuthority().equals("ROLE_SIMPLE"));
		if(hasSimpleRole) {
			return "/simpleUser/parametreCompte";
		}
		return "/user/parametreCompte";
	}

	@GetMapping("email")
	public String parameteremail(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("email","true");
		boolean hasSimpleRole = auth.getAuthorities().stream()
	  	          .anyMatch(r -> r.getAuthority().equals("ROLE_SIMPLE"));
			if(hasSimpleRole) {
				return "/simpleUser/parametreCompte";
			}
		return "/user/parametreCompte";
	}
	@GetMapping("username")
	public String username(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("username","true");
		boolean hasSimpleRole = auth.getAuthorities().stream()
	  	          .anyMatch(r -> r.getAuthority().equals("ROLE_SIMPLE"));
			if(hasSimpleRole) {
				return "/simpleUser/parametreCompte";
			}
		return "/user/parametreCompte";
	}
	
	@GetMapping("password")
	public String password(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("password","true");
		boolean hasSimpleRole = auth.getAuthorities().stream()
	  	          .anyMatch(r -> r.getAuthority().equals("ROLE_SIMPLE"));
			if(hasSimpleRole) {
				return "/simpleUser/parametreCompte";
			}
		return "/user/parametreCompte";
	}
	
	
	@GetMapping("code")
	public String code(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("code","true");
		boolean hasSimpleRole = auth.getAuthorities().stream()
	  	          .anyMatch(r -> r.getAuthority().equals("ROLE_SIMPLE"));
			if(hasSimpleRole) {
				return "/simpleUser/parametreCompte";
			}
		return "/user/parametreCompte";
	}
	
	@GetMapping("tel")
	public String tel(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("tel","true");
		boolean hasSimpleRole = auth.getAuthorities().stream()
	  	          .anyMatch(r -> r.getAuthority().equals("ROLE_SIMPLE"));
			if(hasSimpleRole) {
				return "/simpleUser/parametreCompte";
			}
		return "/user/parametreCompte";
	}
	@PostMapping("UpdateParam")
	public String upd(@Valid compteParametreDto param,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	List<User>UserEmail=userr.findByEmail(param.getEmail());
    	List<User>UserUsername=userr.findByUser(param.getUsername());
    	List<User>UserTel=userr.findByTel(param.getTel());
    	model.addAttribute("userConnect", us);
    	if(param.getNom()!=null && param.getPrenom()!=null) {
    		System.out.println(param.getNom());
    		us.setNom(param.getNom());
    		us.setPrenom(param.getPrenom());
    		userr.save(us);
    		return "redirect:/parametreCompte/param?nom";
    	}
    	if(param.getEmail()!=null) {
    		if(!UserEmail.isEmpty()) {
    			for(User use:UserEmail) {
    				if(use.getCin()!=us.getCin()) {
    					return "redirect:/parametreCompte/param?UniqueEmail";
    				}
    			}
    		}
    		us.setEmail(param.getEmail());
    		userr.save(us);
    		return "redirect:/parametreCompte/param?email";
    	}
    	
    	if(param.getUsername()!=null) {
    		if(!UserUsername.isEmpty()) {
    			for(User use:UserUsername) {
    				if(use.getCin()!=us.getCin()) {
    					return "redirect:/parametreCompte/param?UniqueUsername";
    				}
    			}
    		}
    		us.setUsername(param.getUsername());
    		userr.save(us);
    		return "redirect:/logout?logout";
    	}
    
    	if(param.getPassword()!=null && param.getNewpassword()!=null) {
			
			boolean password=passwordEncoder.matches(param.getPassword(),us.getPassword());
			System.out.println(password);
    		String newPassword=param.getNewpassword();
    		
    		System.out.println(password);
    		if(password) {
    			
    			String nPassword=passwordEncoder.encode(newPassword);
    			us.setPassword(nPassword);
    			userr.save(us);
    			return "redirect:/parametreCompte/param?password";
    			
    		}else {
    			return "redirect:/parametreCompte/password?errorPass";
    		}
    		
    		
	}
    	
    	if(param.getCode()!=null && param.getCodeActuel()!=null) {
        	if(us.getCodeSecret().equals(param.getCodeActuel())) {
        		us.setCodeSecret(param.getCode());
        		userr.save(us);
        		return "redirect:/parametreCompte/param?code";
        	}else {
        		return "redirect:/parametreCompte/code?errorcode";
        	}
    	}
    	
    	if(param.getTel()!=0) {
    		if(!UserTel.isEmpty()) {
    			for(User use:UserTel) {
    				if(use.getCin()!=us.getCin()) {
    					return "redirect:/parametreCompte/param?UniqueTel";
    				}
    			}
    		}
    		if(String.valueOf(param.getTel()).length()!=8) {
    			return "redirect:/parametreCompte/tel?errortel";
    		}else {
    			us.setTel(param.getTel());
        		userr.save(us);
        		return "redirect:/parametreCompte/param?tel";
    		}
    	}
    	return "/user/parametreCompte";
	}
	


}
