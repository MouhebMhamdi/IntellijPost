package net.javaguides.springboot.springsecurity.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.springsecurity.model.Categorie;
import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.model.pointage;
import net.javaguides.springboot.springsecurity.model.stockage;
import net.javaguides.springboot.springsecurity.repository.CategorieRepository;
import net.javaguides.springboot.springsecurity.repository.PointageService;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.repository.stockageRepository;
import net.javaguides.springboot.springsecurity.web.dto.ParametreDto;
import net.javaguides.springboot.springsecurity.web.dto.stockageDto;
import net.javaguides.springboot.springsecurity.web.dto.updateParamDto;

@Controller
@RequestMapping("/parametre")
public class parametreController {
@Autowired
private final UserRepository userRep;
@Autowired
private final UserRepository userr;
@Autowired
private final CategorieRepository categ;
@Autowired
PointageService pointageService;
@Autowired
private final PointageService pointageRep;
@Autowired
private final stockageRepository stockRep;
public  parametreController(UserRepository userRep,CategorieRepository categ,UserRepository userr,stockageRepository stockRep,PointageService pointageRep) {
	this.userRep=userRep;
	this.categ=categ;
	this.stockRep=stockRep;
	this.pointageRep=pointageRep;
	this.userr=userr;
}
	@GetMapping("application")
public String application(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
    	
    	LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
   
    	List<pointage> p=pointageRep.findBydate(date);
    	boolean bool=false;
    	for(pointage pp:p) {
    		
    		if(pp.getUser()==us) {
    			bool=true;
    		}
    	}
    	if(bool==false) {
    		return "redirect:/absence";
    	}
	model.addAttribute("user", userRep.getAllUsers(auth.getName()));
	return "/admin/parametre";
}
	@GetMapping("employe")
	public String applications(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
    	
    	LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
	   	
	 
	   		
	  
    	List<pointage> p=pointageRep.findBydate(date);
    	boolean bool=false;
    	for(pointage pp:p) {
    		
    		if(pp.getUser()==us) {
    			bool=true;
    		}
    	}
    	if(bool==false || userr.getNbrUsers()!=pointageService.getNbrUserByDate(date)) {
    		return "redirect:/absence";
    	}
		model.addAttribute("user", userRep.getAllUsers(auth.getName()));
		
		model.addAttribute("employe", "true");
		return "/admin/parametre";
	}
	@GetMapping("travail")
	public String applicationtravail(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	stockage stock=stockRep.findByUser(us);
    	model.addAttribute("stockage", stock);
		model.addAttribute("userConnect", us);
    	
    	LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
   
    	List<pointage> p=pointageRep.findBydate(date);
    	boolean bool=false;
    	for(pointage pp:p) {
    		
    		if(pp.getUser()==us) {
    			bool=true;
    		}
    	}
    	if(bool==false || userr.getNbrUsers()!=pointageService.getNbrUserByDate(date)) {
    		return "redirect:/absence";
    	}
		model.addAttribute("user", userRep.getAllUsers(auth.getName()));
		model.addAttribute("nbrstock", stockRep.findByUser(userRep.findByUsername(auth.getName())));
		model.addAttribute("travail", "true");
		return "/admin/parametre";
	}
	@PostMapping("stockage")
	public String stock(@Valid stockageDto stock ,Model model,BindingResult result) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	User us=userr.findByUsername(auth.getName());
	    	
	    	LocalDate myObj = LocalDate.now();
		   	String date=myObj.toString();
	   
	    	List<pointage> p=pointageRep.findBydate(date);
	    	boolean bool=false;
	    	for(pointage pp:p) {
	    		
	    		if(pp.getUser()==us) {
	    			bool=true;
	    		}
	    	}
	    	if(bool==false) {
	    		return "redirect:/absence";
	    	}
		User uss=userRep.findByUsername(auth.getName());
		stockage st=stockRep.findByUser(uss);
		if(st==null) {
		stockage stocks=new stockage();
		stocks.setUser(uss);
		stocks.setNombre(stock.getMontant());
		stocks.setNbrGuichet(stock.getNbrGuichet());
		stockRep.save(stocks);
		}else {
			st.setUser(uss);
			st.setNombre(stock.getMontant());
			st.setNbrGuichet(stock.getNbrGuichet());
			stockRep.save(st);
		}
		
		return "redirect:/parametre/travail?success";
		}catch(Exception e) {
			return "redirect:/parametre/travail?error";
		}
	}
	@PostMapping("paramo")
	public String par(@Valid ParametreDto param ,Model model,BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User uss=userr.findByUsername(auth.getName());
    	User us=userr.findById(param.getId());
		model.addAttribute("userConnect", uss);
		LocalDate myObj = LocalDate.now();
		 String date=myObj.toString();
		
		Categorie cat=categ.findByUserAndDate(us, date);
		boolean b=false;
	 	for(Role role:us.getRoles()) {
	   		if(role.getName().equals("ROLE_SIMPLE")) {
	   			b=true;
	   		}
	   	}
	 	if(cat==null && b) {
	 		Categorie cat1=new Categorie();
	 		cat1.setDate(date);
	 		cat1.setUser(us);
	 		cat1.setMontant(0.0);
	 		cat1.setMontantAjouter(0.0);
	 		cat1.setTime(us.getTime());
	 		if( Integer.valueOf(us.getTime())<13 && Integer.valueOf(us.getTime())>=0 ) {
				cat1.setCatname("jour");
			}else
			if(Integer.valueOf(us.getTime())>=13) {
				cat1.setCatname("nuit");
			}
	 		categ.save(cat1);
	 		return "redirect:/parametre/employe?Simple";
		}
		if(cat==null ) {
			
			model.addAttribute("user", us);
			model.addAttribute("addparam", "true");
			return "/admin/parametre";
		}
		model.addAttribute("user", userRep.findAll());
		model.addAttribute("cat", cat);
		return "/admin/parametre";
	}
	@PostMapping("Addparamo")
	public String Addpara(@Valid updateParamDto param ,Model model) {
		try {
			LocalDate myObj = LocalDate.now();
			 String date=myObj.toString();
		User us=userRep.findById(param.getId());
		Categorie cat=new Categorie();
		if( Integer.valueOf(us.getTime())<13 && Integer.valueOf(us.getTime())>=0 ) {
			cat.setCatname("jour");
		}else
		if(Integer.valueOf(us.getTime())>=13) {
			cat.setCatname("nuit");
		}
		cat.setMontant(param.getMontant());
		cat.setTime(us.getTime());
		cat.setUser(us);
		cat.setDate(date);
		cat.setMontantAjouter(param.getMontant());
		categ.save(cat);
		userr.save(us);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return "redirect:/parametre/employe?error";
		}
		
		return "redirect:/parametre/employe?success";
	}
	@PostMapping("Updateparamo")
	public String para(@Valid updateParamDto param ,Model model,BindingResult result) {
		try {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User uss=userr.findByUsername(auth.getName());
	    	User us=userr.findById(param.getId());
			model.addAttribute("userConnect", uss);
			LocalDate myObj = LocalDate.now();
			String date=myObj.toString();
			 boolean b=false;
			 	for(Role role:us.getRoles()) {
			   		if(role.getName().equals("ROLE_SIMPLE")) {
			   			b=true;
			   		}
			   	}
		Categorie cat=categ.findByUserAndDate(us, date);
		Double mount=cat.getMontantAjouter();
		if(param.getMontant()!=null) {
		if(cat.getMontantAjouter()!=null) {
			mount+=param.getMontant();
		}else {
			mount=param.getMontant();
		}
		}else {
			mount=0.0;
		}
		if( param.getTime()<13 && param.getTime()>=0 ) {
			cat.setCatname("jour");
		}else
		if(param.getTime()>=13) {
			cat.setCatname("nuit");
		}
		if(b) {
			cat.setMontant(0.0);
			cat.setMontantAjouter(0.0);
		}else {
		cat.setMontant(param.getMontant());
		cat.setMontantAjouter(mount);
		}
		cat.setTime(String.valueOf(param.getTime()));
		us.setTime(String.valueOf(param.getTime()));
		cat.setDate(date);
		
		List<Categorie>cati=categ.findByDateNotUserAndCatName(date,cat.getUser(),cat.getCatname());
        
	       boolean bool=true;
	       if(!cati.isEmpty()) {
	       for(Categorie c:cati) {
	    	   if(c.getUser().getGuichet()==cat.getUser().getGuichet()) {
	    		   bool=false;
	    	   }
	       }
	       }
		
		if(bool==false && us.getGuichet()!=-3) {
			return "redirect:/parametre/employe?guichet";
		}
		
		categ.save(cat);
		userr.save(us);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return "redirect:/parametre/employe?error";
		}
		
		return "redirect:/parametre/employe?success";
	}
	
}
