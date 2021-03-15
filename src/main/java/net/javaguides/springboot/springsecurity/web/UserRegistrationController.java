package net.javaguides.springboot.springsecurity.web;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.springsecurity.model.Categorie;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.model.stockage;
import net.javaguides.springboot.springsecurity.repository.CategorieRepository;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.repository.stockageRepository;
import net.javaguides.springboot.springsecurity.service.UserService;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;
import net.javaguides.springboot.springsecurity.web.dto.userDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	@Autowired
	private final UserRepository userr;
    @Autowired
    private UserService userService;
    @Autowired
    private CategorieRepository catRep;
    @Autowired
	private  stockageRepository stockRep;
    public UserRegistrationController(UserRepository userr) {
    	this.userr=userr;
    }
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	stockage stock=stockRep.findByUser(us);
		model.addAttribute("guichet", stock.getNbrGuichet());
		model.addAttribute("userConnect", us);
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,@RequestParam("cin") String cin,@RequestParam("periode") String periode, Model model,BindingResult result){
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	stockage stock=stockRep.findByUser(us);
		model.addAttribute("guichet", stock.getNbrGuichet());
		model.addAttribute("userConnect", us);
    	LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
        User existing = userService.findByUsername(userDto.getUsername());
        List<User>emailExisting=userr.findByEmail(userDto.getEmail());
        List<User>telExisting=userr.findByTel(userDto.getTel());
        User cinExisting=userService.findByCin(userDto.getCin());
        Categorie catig=new Categorie();
      
        List<Categorie>cat=catRep.findByDateAndCatname(date,periode);
        
       boolean bool=true;
       if(!cat.isEmpty()) {
       for(Categorie c:cat) {
    	   if(c.getUser().getGuichet()==userDto.getGuichet() && userDto.getGuichet()!=-3) {
    		   bool=false;
    	   }
       }
       }
        if(bool==false) {
        	result.rejectValue("guichet", null, "Ce guichet contient un employé pour cette période  !!");
        }
        if(userDto.getRole().equals("ROLE_SIMPLE") && userDto.getGuichet()!=-3){
            result.rejectValue("guichet", null, "S'il vous plait choisissez aucun guichet !!");
        }
        if(!emailExisting.isEmpty()) {
        	result.rejectValue("email", null, "Adresse email existe déja!!");
        }
        if(!telExisting.isEmpty()) {
        	result.rejectValue("tel", null, "Le numéro du téléphone existe déja!!");
        }
        try {
      
        if(userDto.getGuichet()==0) {
        	result.rejectValue("guichet", null, "Ce champ ne doit pas être null!!");
        }
       
        if (existing != null ){
            result.rejectValue("username", null, "Existe déja !!");
        }
        if(userDto.getAdresse().equals("")) {
        	 result.rejectValue("adresse", null, "Ce champ ne doit pas être vide  !!");
        }
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        if(validationEmail(userDto.getEmail())==false) {result.rejectValue("email", null, "Email non valide !!");}
        if(userDto.getUsername().equals("")) {result.rejectValue("username", null, "Ce champ ne doit pas être vide !!");}
        if(cinExisting !=null) { result.rejectValue("cin", null, "CIN Existe déja !!");}
        if(cin.length()!=8) {result.rejectValue("cin", null, "Invalide CIN !!"); } 
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())){result.rejectValue("confirmPassword", null, "Mot de passe n'est pas édantique !!"); }
        if(userDto.getPassword().equals("")) {result.rejectValue("password", null, "Ce champ ne doit pas être vide !!");}
        if(userDto.getConfirmPassword().equals("")) {result.rejectValue("confirmPassword", null, "Ce champ ne doit pas être vide  !!");}
        if(userDto.getNom().equals("")) {result.rejectValue("nom", null, "Ce champ ne doit pas être vide  !!");}
        if(userDto.getPrenom().equals("")) {result.rejectValue("prenom", null, "Ce champ ne doit pas être vide  !!");}
      if(userDto.getCode_secret().equals("")) {result.rejectValue("code_secret", null, "Ce champ ne doit pas être vide  !!");}
      if(userDto.getTel()==0) {
    	  result.rejectValue("tel", null, "Ce champ ne doit pas être null  !!");
      }
     
      
        if (result.hasErrors()){
            return "registration";
        }
      
       
        catig.setCatname(periode);
        if(periode.equals("jour")) {
        	catig.setTime("8");
        	userDto.setTime("8");
        }else {
        	catig.setTime("13");
        	userDto.setTime("13");
        }
        
        userService.save(userDto);

        catig.setUser(userr.findByUsername(userDto.getUsername()));
        catig.setDate(date);
        catig.setMontantAjouter(0.0);
        catig.setMontant(0.0);
        catRep.save(catig);
  
        return "redirect:/listEmp?success";
    }
    public boolean validationEmail( String email ) {
    	if ( email != null && email.trim().length() != 0 ) {
    	if ( !email.matches(
    	"([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
    		return false;
    	}
    	} else {
    		return false;
    	}
    	return true;
    	}
}

