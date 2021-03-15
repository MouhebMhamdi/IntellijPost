package net.javaguides.springboot.springsecurity.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.io.File;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import net.javaguides.springboot.springsecurity.CustomLoginSuccessHandler;
import net.javaguides.springboot.springsecurity.Dao.ArchiveDAO;
import net.javaguides.springboot.springsecurity.model.Archive;
import net.javaguides.springboot.springsecurity.model.Categorie;
import net.javaguides.springboot.springsecurity.model.PasswordResetToken;
import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.model.pointage;
import net.javaguides.springboot.springsecurity.model.stockage;
import net.javaguides.springboot.springsecurity.repository.ArchiveRepository;
import net.javaguides.springboot.springsecurity.repository.CategorieRepository;
import net.javaguides.springboot.springsecurity.repository.PasswordTokenRepository;
import net.javaguides.springboot.springsecurity.repository.PointageService;
import net.javaguides.springboot.springsecurity.repository.RoleRepository;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.repository.stockageRepository;
import net.javaguides.springboot.springsecurity.service.ArchiveService;
import net.javaguides.springboot.springsecurity.service.ArchiveServiceImpl;
import net.javaguides.springboot.springsecurity.service.CategorieService;
import net.javaguides.springboot.springsecurity.service.UserService;
import net.javaguides.springboot.springsecurity.service.pointageService;
import net.javaguides.springboot.springsecurity.web.dto.EmailMessage;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;
import net.javaguides.springboot.springsecurity.web.dto.emailDto;
import net.javaguides.springboot.springsecurity.web.dto.pointageDto;
import net.javaguides.springboot.springsecurity.web.dto.searchDto;
import net.javaguides.springboot.springsecurity.web.dto.userDto;

@Controller
public class MainController {
	@Autowired
	private ArchiveDAO archivdao ;
	 @Autowired
	    public JavaMailSender emailSender;
	@Autowired
	private final ArchiveRepository arch;
	@Autowired
	private RoleRepository roleRep;
	@Autowired ArchiveServiceImpl asi;
	private UserService userService;
	@Autowired
	pointageService point;
	@Autowired
	private final UserRepository userr;
	@Autowired
	private final CategorieRepository CatRep;
	@Autowired
	private final stockageRepository stockRep;
	@Autowired

	private final PointageService pointageService;
	@Autowired
	CategorieService catServ;
	@Autowired
	private  ArchiveService archiveService;
	 @Autowired
	    private BCryptPasswordEncoder passwordEncoder;
	 @Autowired
	 PasswordTokenRepository passwordTokenRepository;
	 
	@Autowired
	public MainController(ArchiveRepository arch,UserRepository user,PointageService pointage,CategorieRepository CatRep,stockageRepository stockRep) {
		this.arch = arch;
		this.userr=user;
		this.pointageService=pointage;
		this.CatRep=CatRep;
		this.stockRep=stockRep;
	}
	CustomLoginSuccessHandler confighan=new CustomLoginSuccessHandler();
	private char charAt;
	

@GetMapping("emp")
public String getEmploye() {
return "/admin/employe";
}
    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }
    @GetMapping("resetPassword")
    public String reser(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    boolean hasUserRole = authentication.getAuthorities().stream()
  	          .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
  	boolean hasAdminRole = authentication.getAuthorities().stream()
	          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
  	boolean hasSimpleRole = authentication.getAuthorities().stream()
  	          .anyMatch(r -> r.getAuthority().equals("ROLE_SIMPLE"));
  	if(hasUserRole) {
      return "redirect:/user";
  	}else if(hasAdminRole) {
  		return "redirect:/admin";
  	}else if(hasSimpleRole){
  		return "redirect:/simpleUser";
  	}else {
    	return "password";
  	}
    }
    @GetMapping("/admin")
    public String admin() {
    	return "redirect:listArchive";
    }
   
	@PostMapping("listEmpActif")
	public String showUpdateFormeee(@RequestParam(value="verifid",required = false) long iddd,Model model) {
   		try {
		User us=userr.findById(iddd);
		if(us.isVerif()) {
			us.setVerif(false);
		}else {
			us.setVerif(true);
		}
		userr.save(us);
   		}catch(Exception e) {
   			System.out.println(e.getMessage());
   			return "redirect:/listEmp?errorVerif";
   		}
   		
		return "redirect:/listEmp";
	}
	
    @GetMapping("/login")
    public String login(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    
    	boolean hasAdminRole = authentication.getAuthorities().stream()
    	          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
    	boolean hasUserRole = authentication.getAuthorities().stream()
    	          .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
    	
    	boolean hasSimpleRole = authentication.getAuthorities().stream()
    	          .anyMatch(r -> r.getAuthority().equals("ROLE_SIMPLE"));
    	if(hasUserRole) {
        return "redirect:/user";
    	}else if(hasAdminRole) {
    		return "redirect:/admin";
    	}else if(hasSimpleRole){
    		return "redirect:/simpleUser";
    	}else {
			System.out.println(authentication.getName());
    		return "login";
    	}
    }
    @GetMapping("/error")
    public String handleError() {
        return "error";
    }
 
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping("/chat")
    public String index(HttpServletRequest request, Model model) {
        String username = (String) request.getSession().getAttribute("username");
 
        if (username == null || username.isEmpty()) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
 
        return "chat";
    }
    @GetMapping("tempsSimEmp")
    public String tempsSimple(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	for(Role rr:us.getRoles()) {
    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
    			return "redirect:/logout";
    		}
    	}
    	model.addAttribute("userConnect", us);
    	model.addAttribute("user", us);
    	Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
     	model.addAttribute("date", dates);
    	return "/simpleUser/tempsSimEmp";
    }
    @GetMapping("/simpleUser")
    public String simpleUser(Model model ) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	for(Role rr:us.getRoles()) {
    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
    			return "redirect:/logout";
    		}
    	}
    	for(Role rr:us.getRoles()) {
    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
    			return "redirect:/logout";
    		}
    	}
    	if(us.isVerif()==false) {
    		
    		return "redirect:/logout?logout";
    	}
    	LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
		pointage point =pointageService.findBydateAndUser(date, us);
		if(point!=null && point.getCas()==1) {
			model.addAttribute("present", "présent");
		}
    	model.addAttribute("userConnect", us);
    	model.addAttribute("user", us);
    	
    	return "/simpleUser/simpleUser";
    }
    @PostMapping("pointageSimpleUser")
    public String pointageSimpleUser(@ModelAttribute("user") @Valid pointageDto poin, Model model,BindingResult result) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
    	try {
    		
    	if(result.hasErrors()) {
    		return "/simpleUser/simpleUser";
    	}else if(poin.getCodeSecret().equals(us.getCodeSecret()) && poin.getCin()==us.getCin()) {
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");  
  		   LocalDateTime now = LocalDateTime.now();
  		   int hour=Integer.valueOf(dtf.format(now));
  		  
  		  
  		   if(hour!=Integer.valueOf(us.getTime())) {
  			   return "redirect:/simpleUser?erreurTime";
  		   }
    		
    				
        			pointage p=new pointage();
        			p.setCas(1);
            		p.setDate(date);
            		p.setUser(us);
            	List<pointage> pc= pointageService.findBydateAndUserAndCas(date, us, 1);
            	List<pointage> pc1=pointageService.findBydateAndUserAndCas(date, us, 0);
            	if(!pc1.isEmpty()) {
            		pointageService.deleteAll(pc1);
            	}
        		if(pc.isEmpty()) {
        			pointageService.save(p);
        			return "redirect:/simpleUser?success";
        		}
        		return "redirect:/simpleUser?erreurDate";
    		}else {
    		return "redirect:/simpleUser?erreur";
    		}
    		
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());
    		return "redirect:/simpleUser?erreurDate";
    	}
    }
   //==========================================ARCHIVE==================================================
    
    /*===================>   Le class Pointage USER (se pointer)   <=======================================**/
  
    @RequestMapping(value = "/user")
    public String user(Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	for(Role rr:us.getRoles()) {
    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
    			return "redirect:/logout";
    		}
    	}
    	if(us.isVerif()==false) {
    		model.addAttribute("et", "");
    		return "redirect:/logout";
    	}
    	LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
		pointage point =pointageService.findBydateAndUser(date, us);
		if(point!=null && point.getCas()==1) {
			return "redirect:/list?present";
		}
    	model.addAttribute("userConnect", us);
    	model.addAttribute("user", us);
    	return "user/pointer";
    	
    }
    @GetMapping("send/{id}")
    public String email(@PathVariable("id") long id,Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	model.addAttribute("user", userr.findById(id));
    	model.addAttribute("userConnect", us);
    	return "/admin/email";
    }
    @GetMapping("sendSMS/{email}")
    public String sms(@PathVariable("email") String email,Model model) {
    	try {
    	  final String ACCOUNT_SID = "AC9cfb139c495d5d42d723cc988f60d265";
    	  final String AUTH_TOKEN = "f74ff5606bde72c755896f3464d0c8ad";
    	  List<User> us=userr.findByEmail(email);
    	  User uss=null;
    	  if(us.isEmpty()) {
    		  return"redirect:/admin?userNotFound";
    	  }
    	  for(User us1:us) {
    		  uss=us1;
    	  }
    	  Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    	  
    	  Message message = Message.creator(new PhoneNumber("+21650566033"),
    		        new PhoneNumber("+12058720877"), 
    		        "Employé  : Nom: "+uss.getNom()+" Prénom : "+uss.getPrenom()+" Email: "+uss.getEmail()).create();

    		    System.out.println(message.getSid());
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());
    		return"redirect:/admin";
    	}
    	return"redirect:/admin";
    }
    @PostMapping("send")
    public String sendEmail(@Valid emailDto email,Model model){
    	try {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	
    	if(email==null) {
    		return"redirect:/listEmp?errorEmail";
    	}
    	List<User> uss= userr.findByEmail(email.getEmail());
    	for(User us1:uss) {
    		
    		
    		MimeMessage message = emailSender.createMimeMessage();
    		boolean multipart = true;
    		MimeMessageHelper helper;
			try {
				ClassPathResource pdf = new ClassPathResource("static/attachment.pdf");
				ClassPathResource image = new ClassPathResource("static/bg.jpg");

				
				helper = new MimeMessageHelper(message, multipart, "utf-8");
				EmailMessage em=new EmailMessage();
			
				String ch=us1.getNom().substring(0, 1).toUpperCase();
				ch=ch+us1.getNom().substring(1, us1.getNom().length());
				
				String ch1=us1.getPrenom().substring(0, 1).toUpperCase();
				ch1=ch1+us1.getPrenom().substring(1, us1.getPrenom().length());
				
				String htmlMsg =em.UserEmail(ch, ch1, email.getMessage());
	    		message.setContent(htmlMsg, "text/html");
	    		helper.addInline("asbnotebook", image);
				helper.addAttachment("attachment.pdf", pdf);
	    		helper.setText("stub", false);
	    		helper.setTo(email.getEmail());
	    		helper.setSubject(email.getSujet());
	 
	    		emailSender.send(message);
	    		return"redirect:/send/"+us1.getId()+"?sendSucess";
			} catch (MessagingException e) {
				
				e.printStackTrace();
			}
    		
    		
    	}
    	model.addAttribute("user", userr.findByEmail(email.getEmail()));
    	model.addAttribute("userConnect", us);
    	}catch(Exception e) {
    		List<User> uss= userr.findByEmail(email.getEmail());
    		for(User us1:uss) {
    		return"redirect:/send/"+us1.getId()+"?connexion";
    		}
    	}
    	return"/admin/email";
    }
    @PostMapping("pointage")
    public String pointage(@ModelAttribute("user") @Valid pointageDto poin, Model model,BindingResult result) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
    	try {
    		
    	if(result.hasErrors()) {
    		return "user";
    	}else if(poin.getCodeSecret().equals(us.getCodeSecret()) && poin.getCin()==us.getCin()) {
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");  
  		   LocalDateTime now = LocalDateTime.now();
  		   int hour=Integer.valueOf(dtf.format(now));
  		  
  		  
  		   if(hour!=Integer.valueOf(us.getTime())) {
  			   return "redirect:/user?erreurTime";
  		   }
    		
    				
        			pointage p=new pointage();
        			p.setCas(1);
            		p.setDate(date);
            		p.setUser(us);
            	List<pointage> pc= pointageService.findBydateAndUserAndCas(date, us, 1);
            	List<pointage> pc1=pointageService.findBydateAndUserAndCas(date, us, 0);
            	if(!pc1.isEmpty()) {
            		pointageService.deleteAll(pc1);
            	}
        		if(pc.isEmpty()) {
        			pointageService.save(p);
        			return "redirect:/user?success";
        		}
        		return "redirect:/user?erreurDate";
    		}else {
    		return "redirect:/user?erreur";
    		}
    		
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());
    		return "redirect:/user?erreurDate";
    	}
    		
    	
    
    	
    }
    /*=========================>        Admin Présence           <========================**/
	public static String getOsName()
	{
		String OS = null;
	   if(OS == null) { OS = System.getProperty("os.name"); }
	   return OS;
	}
	public static boolean isWindows()
	{
	   return getOsName().startsWith("Windows");
	}
 
	@GetMapping("adminPresencePage/{pageNo}")
	public String showAdminPresence(@PathVariable("pageNo") int pageNo,Model model){
		
	
		LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
		int pageSize=5;
		Page<pointage>page=point.findPaginatedByDate(pageNo, pageSize, date);
		List<pointage>pointa=page.getContent();
		if(pageNo>page.getTotalPages()){
			return "redirect:/adminPresencePage/1";
		}
		Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
    	model.addAttribute("date", dates);
    	List<pointage> p=pointageService.findBydate(date);
    	boolean bool=false;
    	int i=0;
    	for(pointage pp:p) {
    		i+=1;
    		if(pp.getUser()==us) {
    			bool=true;
    		}
    	}
    	
    	if(bool==false || i!=userr.getNbrUsers()) {
    		return "redirect:/absence";
    	}
    	if(p.isEmpty()) {
    		return "redirect:/absence";
    	}
		model.addAttribute("presence", pointa);
		model.addAttribute("currentPage", pageNo);
 		 model.addAttribute("totalPages", page.getTotalPages());
    	model.addAttribute("totalItems", page.getTotalElements());
		int begin = page.getNumber()+1 ;
		int end = Math.min(page.getTotalPages(),begin + 4);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
    	return "/admin/adminPresence";
	}
  
    @GetMapping("/adminPresence")
    private String AdminPresence(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
		List<pointage> p=pointageService.findBydate(date);
    	boolean bool=false;
    	int i=0;
    	for(pointage pp:p) {
    		i+=1;
    		if(pp.getUser()==us) {
    			bool=true;
    		}
    	}
    	
    	if(bool==false || i!=userr.getNbrUsers()) {
    		return "redirect:/absence";
    	}
    	if(p.isEmpty()) {
    		return "redirect:/absence";
    	}
    	return showAdminPresence(1, model);
    }
    @PostMapping("enregistrer")
    public String pointagee(@ModelAttribute("user") @Valid pointageDto poin, Model model,BindingResult result) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");  
		   LocalDateTime now = LocalDateTime.now();
		   int hour=Integer.valueOf(dtf.format(now));
		  
    	try { 
    		
    		if(poin.getCodeSecret().equals(us.getCodeSecret()) ) {
    		
    		
    		List<User> pusers =  pointageService.getUser();
    		
    		for (User u : pusers) {
    		    //System.out.println(u.getId());
    			//if (!pusers.contains(u)) {
    			
    				LocalDate myObj = LocalDate.now();
    				String date=myObj.toString();
        			List<pointage> pc1=pointageService.findByUserAndDate(u, date);
        			
        			if(pc1.isEmpty()) {
						System.out.println("hi");
        			if(!u.getUsername().equals(auth.getName())){
        			pointage p=new pointage();
        			p.setCas(0);
            		p.setDate(date);
            		p.setUser(u);
            		pointageService.save(p);
        			}else if(hour==16) {
						
        					pointage p=new pointage();
                			p.setCas(1);
                    		p.setDate(date);
                    		p.setUser(u);
                    		pointageService.save(p);
        		 		   }else {
							
        		 			pointage p=new pointage();
                  			p.setCas(0);
                      		p.setDate(date);
                      		p.setUser(u);
                      		pointageService.save(p);
        		 		   }
        			
        			}
    			//} 
        				

    		}
    		LocalDate myObj = LocalDate.now();
			String date=myObj.toString();
    		List<pointage> p=pointageService.getp(date);
    		
    		for(pointage point : p) {
    			List<pointage> pc1=pointageService.findBydateAndUserAndCas(date, point.getUser(), 0);
    			List<pointage> pc2=pointageService.findBydateAndUserAndCas(date, point.getUser(), 1);
    			if(pc1.isEmpty() && pc2.isEmpty()) {
    			pointage po=new pointage();
    			po.setCas(0);
        		po.setDate(date);
        		po.setUser(point.getUser());
        		pointageService.save(po);
        		
    			}
    		}
			List<pointage>p3=pointageService.findBydateAndUserAndCas(date,us,0);
			if(!p3.isEmpty()){
				for(pointage p4:p3){
					if(hour==Integer.valueOf(us.getTime())){
						p4.setCas(1);
						pointageService.save(p4);
					}
				}
			}
        	return "redirect:/adminPresence?success";
    		}else {
    		return "redirect:/absence?erreur";
    		}
		
    	}catch(Exception ex) {
    		
    		return "redirect:/absence?erreurDate";
    	}
    }
    @GetMapping("deletePresence/{id}")
	public String deletePresence(@PathVariable("id") int id, Model model) {
		pointage point = pointageService.findById(id);
		pointageService.delete(point);
		LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
   
   
    	List<pointage> p=pointageService.findBydate(date);
    	boolean bool=false;
    	int i=0;
    	for(pointage pp:p) {
    		i+=1;
    		if(pp.getUser()==us) {
    			bool=true;
    		}
    	}
    	if(bool==false || i!=userr.getNbrUsers()) {
    		return "redirect:/absence";
    	}
    	Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
    	model.addAttribute("date", dates);
    	model.addAttribute("presence", pointageService.findBydate(date));
		return "redirect:/adminPresence";
	}
    @PostMapping("newPassWord")
    public String newpass(Model model,@RequestParam("token") String token,@RequestParam("password") String password) {
    	try {

    	List<PasswordResetToken> pass=passwordTokenRepository.findByTok(token);
    	if(!pass.isEmpty()) {
    		for(PasswordResetToken p:pass) {
    			User us=p.getUser();
    			us.setPassword(passwordEncoder.encode(password));
    			userr.save(us);
				passwordTokenRepository.delete(p);
				MimeMessage message = emailSender.createMimeMessage();
	    		boolean multipart = true;
	    		MimeMessageHelper helper;
				final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
				helper = new MimeMessageHelper(message, multipart, "utf-8");
				String AdminEmail="";
				for(User so:userr.findAll()) {
					for(Role r2:so.getRoles()) {
						if(r2.getName().equals("ROLE_ADMIN")) {
							AdminEmail=so.getEmail();
						}
					}
				}
				String ch="";
				String ch1="";
			
				 ch=us.getNom().substring(0, 1).toUpperCase();
				ch=ch+us.getNom().substring(1, us.getNom().length());
				
				 ch1=us.getPrenom().substring(0, 1).toUpperCase();
				ch1=ch1+us.getPrenom().substring(1, us.getPrenom().length());
				Date time = new Date();
				DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
     			String date=mediumDateFormat.format(time);
				EmailMessage m=new EmailMessage();
				InetAddress IP=InetAddress.getLocalHost();
				String osName=getOsName();
				String pcName=IP.getHostName();
				String Ip=IP.getHostAddress();
				
				String htmlMsg =m.passwordChanged(ch, ch1, AdminEmail, date, osName, Ip, pcName);
	    		message.setContent(htmlMsg, "text/html");
	    		helper.setTo(us.getEmail());
	    		helper.setSubject("POSTE TN : mot de passe changé avec succès !");
	    		emailSender.send(message);
    			return "redirect:/login?passChange";
    		}
    	}else {
    		System.out.println("empty liste");
    	}

    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    		model.addAttribute("error", "error");
    		return"password";
    	}
    	return"password";
    }
    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        Date date=passToken.getExpiryDate();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        cal1.add(Calendar.MINUTE, 5);
        return cal.getTime().before(cal1.getTime());
    }
    @RequestMapping(value = "/newPassword", method = RequestMethod.GET)
    public String reset(Model model,@RequestParam("token") String token) {
    	Optional<PasswordResetToken> tokenReset=passwordTokenRepository.findByToken(token);
    	if(tokenReset.isPresent() && isTokenExpired(passwordTokenRepository.findByTokens(token))) {
    		model.addAttribute("resetToken", token);
    		model.addAttribute("tok", passwordTokenRepository.findByTok(token));
    	}else {
    		return "redirect:/resetPassword?errorToken";
    		
    	}
    	
    	return "password";
    }

   
	@PostMapping("resetPasswordpost")
    public String reset(Model model, @RequestParam("email") String userEmail,HttpServletRequest request) {
		try {
		List<User> user=userr.findByEmail(userEmail);
	
    	if(user.isEmpty()) {
    		model.addAttribute("ErrorEmail", "ErrorEmail");
    		return "password";
    	}else {
    		String token = UUID.randomUUID().toString();
    		for(User us:user) {
    			Date now = Date.from(Instant.now());
    	
    		List<PasswordResetToken> listToken=passwordTokenRepository.findByUs(us);
    		if(listToken.isEmpty()) {
    		PasswordResetToken myToken = new PasswordResetToken();
    		myToken.setToken(token);
    		myToken.setUser(us);
    		myToken.setExpiryDate(now);
    	    passwordTokenRepository.save(myToken);
    		}else {
    			for(PasswordResetToken l:listToken) {
    				l.setToken(token);
    				l.setExpiryDate(now);
    				passwordTokenRepository.save(l);
    			}
    			
    		}

    		
			
				MimeMessage message = emailSender.createMimeMessage();
	    		boolean multipart = true;
	    		MimeMessageHelper helper;
				final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
				helper = new MimeMessageHelper(message, multipart, "utf-8");
				String AdminEmail="";
				for(User so:userr.findAll()) {
					for(Role r2:so.getRoles()) {
						if(r2.getName().equals("ROLE_ADMIN")) {
							AdminEmail=so.getEmail();
						}
					}
				}
				String ch="";
				String ch1="";
				for(User u:user) {
				 ch=u.getNom().substring(0, 1).toUpperCase();
				ch=ch+u.getNom().substring(1, u.getNom().length());
				
				 ch1=u.getPrenom().substring(0, 1).toUpperCase();
				ch1=ch1+u.getPrenom().substring(1, u.getPrenom().length());
				}
				EmailMessage m=new EmailMessage(baseUrl+"/newPassword?token="+token,AdminEmail,ch1,ch);
				String htmlMsg =m.getEmail();
	    		message.setContent(htmlMsg, "text/html");
	    		helper.setTo(us.getEmail());
	    		helper.setSubject("POSTE TN : mot de passe oublié !");
	    		emailSender.send(message);
	    		return"redirect:/login?emailEnvoye";
			} 
    		
    		/*
    	    SimpleMailMessage message = new SimpleMailMessage(); 
    	  
    	    message.setTo(userEmail); 
    	    
    	    message.setSubject("Mot de passe oublier !"); 
    	  
    	    message.setText(baseUrl+"/newPassword?token="+token);
    	    emailSender.send(message);
    	    
    	   */ 
    		}
    		return "password";
    	}catch (MessagingException e) {
					
			e.printStackTrace();
			return"redirect:/login?connexion";
		}
    	
    		
    	}
    
    	
	@GetMapping("deletePresenceAll/{id}")
   	public String deletePresenceAll(@PathVariable("id") int id, Model model) {
   		pointage point = pointageService.findById(id);
   		pointageService.delete(point);
   		LocalDate myObj = LocalDate.now();
   		String date=myObj.toString();
   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    
    	List<pointage> p=pointageService.findBydate(date);
    	boolean bool=false;
    	int i=0;
    	for(pointage pp:p) {
    		i+=1;
    		if(pp.getUser()==us) {
    			bool=true;
    		}
    	}
    	if(bool==false || i!=userr.getNbrUsers()) {
    		return "redirect:/absence";
    	}
    	Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
       	model.addAttribute("date", dates);
       	model.addAttribute("presence", pointageService.findAll());
   		return "redirect:/allAdminPresence";
   	}
@GetMapping("presencePage/{pageNo}")  
public String pres(@PathVariable("pageNo") int pageNo,Model model){
	LocalDate myObj = LocalDate.now();
	String date=myObj.toString();
	int pageSize=5;
	Page<pointage>page=point.findPaginated(pageNo, pageSize);
	List<pointage>pointa=page.getContent();
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	User us=userr.findByUsername(auth.getName());


	List<pointage> p=pointageService.findBydate(date);
	boolean bool=false;
	int i=0;
	for(pointage pp:p) {
		i+=1;
		if(pp.getUser()==us) {
			bool=true;
		}
	}
	if(bool==false || i!=userr.getNbrUsers()) {
		return "redirect:/absence";
	}
	Date time = new Date();
   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
 	String dates=formater.format(time);
	model.addAttribute("userConnect", us);
	model.addAttribute("date", dates);
	model.addAttribute("presence", pointa);
	model.addAttribute("currentPage", pageNo);
    model.addAttribute("totalPages", page.getTotalPages());
    model.addAttribute("totalItems", page.getTotalElements());
    	
    int begin = page.getNumber()+1 ;
    int end = Math.min(page.getTotalPages(),begin + 4);
   	model.addAttribute("begin", begin);
    model.addAttribute("end", end);
	return "/admin/alladminPresence";
	
}
@GetMapping("/allAdminPresence")
private String AllAdminPresence(Model model) {
	return pres(1, model);
}
    @GetMapping("/absence")
    private String engAbsence(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
    	model.addAttribute("user", us);
    	return "/admin/absence";
    }
    @GetMapping("/absenceList")
    private ModelAndView engAbsenceList(Model model) {
    	ModelAndView mav = new ModelAndView("admin/absenceList");
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
    	mav.addObject("presence", pointageService.getUser());
    	return mav;
    }
  
    /*===================>   Le class Archive partie User (modifier,supprimer,ajouter,afficher,calcule argent)   <======================    **/
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable ("pageNo") int pageNo,Model model) {
		
    	int pageSize=5;
    	LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
	   	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User us=userr.findByUsername(auth.getName());
    	Page<Archive>page=archiveService.findPaginated(pageNo, pageSize, date, us);
    	List<Archive>ar=page.getContent();
		if(pageNo>page.getTotalPages()){
			return "redirect:/page/1";
		}
		for(Role rr:us.getRoles()) {
    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
    			return "redirect:/logout";
    		}
    	}
		Double montantUser=0.0;
		List<Categorie>listCat=CatRep.findByDateAndUser(date, us);
		
		if(!listCat.isEmpty()){
		Categorie cat=CatRep.findByUserAndDate(us, date);
		model.addAttribute("cat", cat);
		Categorie c=CatRep.findByUserAndDate(us, date);
		 montantUser=c.getMontant();
		}
		model.addAttribute("userConnect", us);
		
	   	List<pointage> p=pointageService.findBydateAndUserAndCas(date, us, 1);
	   	if(p.isEmpty()) {
	   		return "redirect:/user?pointage";
	   	}
	   	List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");
		
		Double ret=arch.MontantSumRetraitUser(l, date, us);
	   	Double som=arch.MontantSumUser(l, date, us);
	 	
	 	Double rslt=null;
	 	if(ret==null) {ret=0.0;	}
	 	if(som==null) {som=0.0;	}
	 	if(montantUser==null) {montantUser=0.0;	}
	 	
	 	rslt=(som+montantUser)-ret;
	 	List<Archive>a=arch.findByUserAndDate(us, date);
	 	if(a.isEmpty()) {
	 		return "redirect:/addArchive";
	 	}
	 	Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
		model.addAttribute("stockCourant", rslt);
		model.addAttribute("retrait",arch.MontantSumRetraitUser(l, date, us) );
		model.addAttribute("somme",arch.MontantSumUser(l, date, us));
		model.addAttribute("archives", ar);
		model.addAttribute("date", dates);
		model.addAttribute("currentPage", pageNo);
    	model.addAttribute("totalPages", page.getTotalPages());
    	model.addAttribute("totalItems", page.getTotalElements());
    	
    	int begin = page.getNumber()+1 ;
    	int end = Math.min(page.getTotalPages(),begin + 4);
   	 	model.addAttribute("begin", begin);
    	model.addAttribute("end", end);
    	
		return "/user/archiveList";
    }
    @RequestMapping("/addArchive")
	public String openEmployeeAddView(Model model) {
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User us=userr.findByUsername(auth.getName());
		if(us.getGuichet()==-3){
			model.addAttribute("guichet", "guichet");
		}
    	for(Role rr:us.getRoles()) {
    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
    			return "redirect:/logout";
    		}
    	}
		model.addAttribute("userConnect", us);
		model.addAttribute("archive", new Archive());
		
		return "/user/addArchive";
	}
    @GetMapping("deleteArch/{id}")
	public String deleteArch(@PathVariable("id") long id, Model model) {
		Archive archive = arch.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid posteUser Id:" + id));
		arch.delete(archive);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("archives", arch.findAll());
		return "redirect:/listArchive";
	}
    @PostMapping("add")
	public String addposteUser(@Valid Archive posteUser, BindingResult result, Model model) {
  
   		if (result.hasErrors() ) {
   			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   	    	User us=userr.findByUsername(auth.getName());
   			model.addAttribute("userConnect", us);
   			return "/user/addArchive";
   		}
   		if(String.valueOf(posteUser.getCin()).length()!=8) {
       	 result.rejectValue("cin", null, "Invalide CIN !!");
   		}
   		
   		
   
   	 LocalDate myObj = LocalDate.now();
   	String date=myObj.toString();
   	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	User us=userr.findByUsername(auth.getName());
	List<String>l=new ArrayList<String>();
	l.add("Paiement Mondat");
	l.add("Paiement Mondat Ordinaire");
	l.add("Paiement Cheque");
	l.add("Crée Edinar");
	l.add("Crée Epargne");
	l.add("Retrait Epargne");
	l.add("western union");
	l.add("Virement");
	Integer temp = 0;
	String bankAccNo = posteUser.getNom();
	for(String no : l) {
	    if(no.equals(bankAccNo)) {
	        temp = 1;
	     }
	 }
	try {
	Categorie c=CatRep.findByUserAndDate(us, date);
	
	Double ret=arch.MontantSumRetraitUser(l, date, us);
   	Double som=arch.MontantSumUser(l, date, us);
 	Double montantUser=c.getMontant();
 	
   	if(som==null) {som=0.0;}
   	if(ret==null) {ret=0.0;}
   	if(montantUser==null) {montantUser=0.0;}
   	
   	if(temp==1) {
   		ret=ret+posteUser.getMontant();
   	}else {
   		som=som+posteUser.getMontant();
   	}
   	
   	Double rslt=som+montantUser;
   	

	if(rslt<ret ) {
		return "redirect:/list?demandeArgent";
	}
	
	posteUser.setDate(date);

	List<Archive> ll=arch.findByUniqueCode(posteUser.getUniqueCode());
	if(ll.isEmpty()) {
	asi.save(posteUser);
	}else {
		return "redirect:/addArchive?codeUnique";
	}
	}catch(Exception e) {

		System.out.println(e.getMessage());
	
	}
   		
   		return "redirect:/liste";
	}
    @GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Archive posteUser = arch.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid posteUser Id:" + id));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	for(Role rr:us.getRoles()) {
    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
    			return "redirect:/logout";
    		}
    	}
		model.addAttribute("userConnect", us);
		model.addAttribute("archives", posteUser);
		return "/user/update-archive";
	}
	@PostMapping("update/{id}")
	public String updateposteUser(@PathVariable("id") long id, @Valid Archive posteUser, BindingResult result,
			Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
	
		Archive a=arch.findByIdd(id);
		List<Archive> ll=arch.findByUniqueCode(posteUser.getUniqueCode());
		if(!ll.isEmpty()) {
			for(Archive ar:ll) {
				if(!ar.getUniqueCode().equals(a.getUniqueCode())) {
					return"redirect:/edit/"+id+"?uniqueCode";
				}
			}
			
		}
		
		Archive archi=arch.findByIdd(id);
		archi.setCin(posteUser.getCin());
		archi.setMontant(posteUser.getMontant());
		archi.setNom(posteUser.getNom());
		archi.setUniqueCode(posteUser.getUniqueCode());
		
		arch.save(archi);
		model.addAttribute("archive", arch.findAll());
		return "redirect:/list";
	}
	 @GetMapping("editAll/{id}")
		public String showUpdateFormall(@PathVariable("id") long id, Model model) {
			Archive posteUser = arch.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("Invalid posteUser Id:" + id));
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	User us=userr.findByUsername(auth.getName());
	    	for(Role rr:us.getRoles()) {
	    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
	    			return "redirect:/logout";
	    		}
	    	}
			model.addAttribute("userConnect", us);
			model.addAttribute("archives", posteUser);
			model.addAttribute("all", "all");
			return "/user/update-archive";
		}
		@PostMapping("updateAll/{id}")
		public String updateposteUserall(@PathVariable("id") long id, @Valid Archive posteUser, BindingResult result,
				Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	User us=userr.findByUsername(auth.getName());
			model.addAttribute("userConnect", us);
		
		
			
			Archive a=arch.findByIdd(id);
			List<Archive> ll=arch.findByUniqueCode(posteUser.getUniqueCode());
			if(!ll.isEmpty()) {
				for(Archive arr:ll) {
					if(!arr.getUniqueCode().equals(a.getUniqueCode())) {
						return"redirect:/editAll/"+id+"?uniqueCode";
					}
				}
				
			}
			
			
			a.setUniqueCode(posteUser.getUniqueCode());
			a.setCin(posteUser.getCin());
			a.setMontant(posteUser.getMontant());
			a.setNom(posteUser.getNom());
			
			
			arch.save(a);
			model.addAttribute("archive", arch.findAll());
			return "redirect:/listAll";
		}
	@GetMapping("list")
	public String showUpdateForm(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User us=userr.findByUsername(auth.getName());
		LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
		List<pointage> p=pointageService.findBydateAndUserAndCas(date, us, 1);
		List<Categorie>cc=CatRep.findByDateAndUser(date, us);
		if(cc.isEmpty()){
			model.addAttribute("solde", "solde");
		}
		if(p.isEmpty()) {
			return "redirect:/user?pointage";
		}
		List<Archive>a=arch.findByUserAndDate(us, date);

	 	if(a.isEmpty()) {
	 		return "redirect:/emptyArch";
		 }
		 if(us.getGuichet()==-3){
			 model.addAttribute("guichet", "guichet");
			 
		 }
		return findPaginated(1, model);
	}
	@GetMapping("emptyArch")
	public String emptyArchi(Model model){
		int pageSize=5;
    	LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
	   	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User us=userr.findByUsername(auth.getName());
    	List<Archive>ar=arch.findByUserAndDate(us, date);
		
		for(Role rr:us.getRoles()) {
    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
    			return "redirect:/logout";
    		}
    	}
		
		Categorie cat=CatRep.findByUserAndDate(us, date);
		model.addAttribute("cat", cat);
		model.addAttribute("userConnect", us);
		
	   	List<pointage> p=pointageService.findBydateAndUserAndCas(date, us, 1);
	   	if(p.isEmpty()) {
	   		return "redirect:/user?pointage";
	   	}
	   	List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");
		Categorie c=CatRep.findByUserAndDate(us, date);
		Double ret=arch.MontantSumRetraitUser(l, date, us);
	   	Double som=arch.MontantSumUser(l, date, us);
	 	Double montantUser=c.getMontant();
	 	Double rslt=null;
	 	if(ret==null) {ret=0.0;	}
	 	if(som==null) {som=0.0;	}
	 	if(montantUser==null) {montantUser=0.0;	}
	 	
	 	rslt=(som+montantUser)-ret;
	 	List<Archive>a=arch.findByUserAndDate(us, date);
	 	
	 	Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
		model.addAttribute("stockCourant", rslt);
		model.addAttribute("retrait",arch.MontantSumRetraitUser(l, date, us) );
		model.addAttribute("somme",arch.MontantSumUser(l, date, us));
		model.addAttribute("archives", ar);
		model.addAttribute("date", dates);
		model.addAttribute("empty", "attributeValue");
    	
		return "/user/archiveList";
	}
	@GetMapping("liste")
	public String showUpdateForms(Model model) {
		int pageSize=5;
    	LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
	   	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User us=userr.findByUsername(auth.getName());
		if(us.getGuichet()==-3){
			model.addAttribute("guichet", "attributeValue");
		}
    	Page<Archive>page=archiveService.findPaginated(1, pageSize, date, us);
		return findPaginated(page.getTotalPages(),model);
	}
	
	@GetMapping("/pages/{pageNo}")
	public String getPage(@PathVariable("pageNo") int pageNo,Model model) {
		int pageSize=5;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User us=userr.findByUsername(auth.getName());
		Page<Archive>page=archiveService.findPaginatedAll(pageNo, pageSize, us);
		if(pageNo>page.getTotalPages()){
			return"redirect:/pages/1";
		}
		List<Archive>ari=arch.findByUser(us);
		if(ari.isEmpty()){
			return "redirect:/addarchive?empty";
		}
		List<Archive>ar=page.getContent();
		for(Role rr:us.getRoles()) {
    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
    			return "redirect:/logout";
    		}
    	}
		List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");
		if(us.getGuichet()==-3){
			model.addAttribute("guichet", "attributeValue");
		}
		model.addAttribute("userConnect", us);
		model.addAttribute("retrait", arch.MontantAllSumRetraitUser(l, us));
		model.addAttribute("somme", arch.MontantAllsumUser(l, us));
		model.addAttribute("archives", ar);
		model.addAttribute("currentPage", pageNo);
    	model.addAttribute("totalPages", page.getTotalPages());
    	model.addAttribute("totalItems", page.getTotalElements());
    	
    	int begin = page.getNumber()+1 ;
    	int end = Math.min(page.getTotalPages(),begin + 4);
   	 	model.addAttribute("begin", begin);
    	model.addAttribute("end", end);
    	
		
		return "/user/allArchive";
	}
	@GetMapping("allArchEmp")
	public String emptyAllArch(Model model){
		int pageSize=5;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User us=userr.findByUsername(auth.getName());
		
		List<Archive>ari=arch.findByUser(us);
		if(ari.isEmpty()){
			model.addAttribute("empty", "attributeValue");
		}
		List<Archive>ar=arch.findByUser(us);
		for(Role rr:us.getRoles()) {
    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
    			return "redirect:/logout";
    		}
    	}
		List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");
		if(us.getGuichet()==-3){
			model.addAttribute("guichet", "attributeValue");
		}
		model.addAttribute("userConnect", us);
		model.addAttribute("retrait", arch.MontantAllSumRetraitUser(l, us));
		model.addAttribute("somme", arch.MontantAllsumUser(l, us));
		model.addAttribute("archives", ar);

		return "/user/allArchive";
	}
	@GetMapping("/listAll")
	public String showUpdateFormAll(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User us=userr.findByUsername(auth.getName());
		List<Archive>ari=arch.findByUser(us);
		if(ari.isEmpty()){
			return "redirect:/allArchEmp";
		}
		if(us.getGuichet()==-3){
			model.addAttribute("guichet", "attributeValue");
		}
		return getPage(1, model);
	}
	@GetMapping("delete/{id}")
	public String deleteArchive(@PathVariable("id") long id, Model model) {
		Archive archive = arch.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid posteUser Id:" + id));
		arch.delete(archive);
		model.addAttribute("archives", arch.findAll());
		return "redirect:/list";
	}
	@GetMapping("deleteAll/{id}")
	public String deleteArchiveall(@PathVariable("id") long id, Model model) {
		Archive archive = arch.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid posteUser Id:" + id));
		arch.delete(archive);
		model.addAttribute("archives", arch.findAll());
		return "redirect:/listAll";
	}
/*===================>   Le class Archive partie Admin (modifier,supprimer,ajouter,afficher,calcule argent)   <======================    **/
	@GetMapping("pageAdmin/{pageNo}")
	public String shows(@PathVariable("pageNo") int pageNo,Model model) {
		int pageSize=5;
		Page<Archive>page=archiveService.findPaginatedAdminAll(pageNo, pageSize);
		List<Archive>ar=page.getContent();
		if(pageNo>page.getTotalPages() ){
			return"redirect:/pageAdmin/1";
		}
		List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");
		List<Archive> li=arch.findAll();
		if(li.isEmpty()) {
			return "redirect:/archEmp";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("retrait", arch.MontantAllSumRetrait(l));
		model.addAttribute("somme", arch.MontantAllsum(l));
		model.addAttribute("alluser",ar);
		model.addAttribute("currentPage", pageNo);
    	model.addAttribute("totalPages", page.getTotalPages());
    	model.addAttribute("totalItems", page.getTotalElements());
    	
    	 int begin = page.getNumber()+1 ;
    	 int end = Math.min(page.getTotalPages(),begin + 4);
    	 model.addAttribute("begin", begin);
     	model.addAttribute("end", end);
		return "/admin/allArchive";
	}
	@GetMapping("all")
	public String all(Model model) {
		List<Archive> li=arch.findAll();
		if(li.isEmpty()) {
			return "redirect:/archEmp";
		}
		return shows(1, model);
	}
	@GetMapping("archEmp")
	public String emptyArch(Model model){
		
		List<Archive>ar=arch.findAll();
	
		List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("retrait", arch.MontantAllSumRetrait(l));
		model.addAttribute("somme", arch.MontantAllsum(l));
		model.addAttribute("alluser",ar);
		model.addAttribute("empty", "attributeValue");
		return "/admin/allArchive";
	}
	@GetMapping("pagesAdmin/{pageNo}")
	public String show(@PathVariable("pageNo") int pageNo,Model model) {
		int pageSize=5;
		LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
		Page<Archive>page=archiveService.findPaginatedAdmin(pageNo, pageSize, date);
		if(pageNo>page.getTotalPages()){
			return"redirect:/pagesAdmin/1";
		}
    	List<Archive>ar=page.getContent();
     	Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
		List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("retrait", arch.MontantSumRetrait(l,date));
		model.addAttribute("somme", arch.MontantSum(l,date));
		model.addAttribute("date", dates);
		List<Archive> a=arch.findByDate(date);
    	List<pointage> p=pointageService.findBydate(date);
    	boolean bool=false;
    	int i=0;
    	for(pointage pp:p) {
    		i+=1;
    		if(pp.getUser()==us) {
    			bool=true;
    		}
    	}
    	if(bool==false || i!=userr.getNbrUsers()) {
    		return "redirect:/absence";
    	}
		if(a.isEmpty()) {
			model.addAttribute("empty", "attributeValue");
		}
		model.addAttribute("archives", ar);
		model.addAttribute("currentPage", pageNo);
    	model.addAttribute("totalPages", page.getTotalPages());
    	model.addAttribute("totalItems", page.getTotalElements());
    	
    	int begin = page.getNumber()+1 ;
    	int end = Math.min(page.getTotalPages(),begin + 4);
   	 	model.addAttribute("begin", begin);
    	model.addAttribute("end", end);
		return "/admin/archiveList";
	}
	@GetMapping("listArchive")
	public String showUpdateForme(Model model) {
		LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
		List<Archive> a=arch.findByDate(date);
		if(a.isEmpty()) {
			return"redirect:/listeArchive";
		}
		return show(1, model);
	}
	@GetMapping("listeArchive")
	public String listeArchive(Model model){
		LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
     	Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
		List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("retrait", arch.MontantSumRetrait(l,date));
		model.addAttribute("somme", arch.MontantSum(l,date));
		model.addAttribute("date", dates);
    	List<pointage> p=pointageService.findBydate(date);
    	boolean bool=false;
    	int i=0;
    	for(pointage pp:p) {
    		i+=1;
    		if(pp.getUser()==us) {
    			bool=true;
    		}
    	}
    	if(bool==false || i!=userr.getNbrUsers()) {
    		return "redirect:/absence";
    	}
	
		model.addAttribute("empty", "attributeValue");
		model.addAttribute("archives", arch.findByDate(date));
		
		return "/admin/archiveList";
	}
	@GetMapping("lists")
	public String showUpdateFormes(Model model) {
		LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
		Page<Archive>page=archiveService.findPaginatedAdmin(1, 5, date);
		return show(page.getTotalPages(), model);
	}
	
	@RequestMapping("/addArchives")
   	public String openEmployeeAddViews(Model model) {

   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
    	LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
   
    	List<pointage> p=pointageService.findBydate(date);
    	boolean bool=false;
    	int i=0;
    	for(pointage pp:p) {
    		i+=1;
    		if(pp.getUser()==us) {
    			bool=true;
    		}
    	}
    	if(bool==false || i!=userr.getNbrUsers()) {
    		return "redirect:/absence";
    	}
		model.addAttribute("userConnect", us);
   		model.addAttribute("archive", new Archive());
   		
   		return "/admin/addArchive";
   	}
       @PostMapping("addArchives")
   	public String addposteUsers(@Valid Archive posteUser, BindingResult result, Model model) {
    	   
   		if (result.hasErrors() ) {
   			return "/admin/addArchive";
   		}
   		
   		if(String.valueOf(posteUser.getCin()).length()!=8) {
   			return "redirect:/addArchives?error";
   		}
   		
   	 LocalDate myObj = LocalDate.now();
   	String date=myObj.toString();
   		posteUser.setDate(date);																			
   		List<Archive> ll=arch.findByUniqueCode(posteUser.getUniqueCode());
   		if(ll.isEmpty()) {
   		asi.save(posteUser);
   		}else {
   			return "redirect:/addArchives?codeUnique";
   		}																																														   		asi.save(posteUser);
   		return "redirect:/lists";
   		
   	}
       @GetMapping("editArchive/{id}")
   	public String showUpdateForme(@PathVariable("id") long id, Model model) {
   		Archive posteUser = arch.findById(id)
   				.orElseThrow(() -> new IllegalArgumentException("Invalid posteUser Id:" + id));
   		model.addAttribute("archive", posteUser);
   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
   		return "/admin/update-archive";
   	}
       @GetMapping("editArchives/{id}")
      	public String showUpdateFormes(@PathVariable("id") long id, Model model) {
      		Archive posteUser = arch.findById(id)
      				.orElseThrow(() -> new IllegalArgumentException("Invalid posteUser Id:" + id));
      		model.addAttribute("archive", posteUser);
      		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      		User us=userr.findByUsername(auth.getName());
   			model.addAttribute("userConnect", us);
   			model.addAttribute("all", "all");
      		return "/admin/update-archive";
      	}
   	@PostMapping("updatee/{id}")
   	public String updateposteUsers(@PathVariable("id") long id, @Valid Archive posteUser, BindingResult result,
   			Model model) {
   		if (result.hasErrors()) {
   			posteUser.setId(id);
   			return "listArchive";
   		}
   	
			Archive a=arch.findByIdd(id);
			List<Archive> ll=arch.findByUniqueCode(posteUser.getUniqueCode());
			if(!ll.isEmpty()) {
				for(Archive ar:ll) {
					if(!ar.getUniqueCode().equals(a.getUniqueCode())) {
						return"redirect:/editArchive/"+id+"?codeUnique";
					}
				}
				
			}
			a.setCin(posteUser.getCin());
			a.setUniqueCode(posteUser.getUniqueCode());
			a.setMontant(posteUser.getMontant());
			a.setNom(posteUser.getNom());
			
   		arch.save(a);
   		model.addAttribute("archive", arch.findAll());
   		return "redirect:/listArchive";
   	}
   	@PostMapping("updatees/{id}")
   	public String updateposteUserss(@PathVariable("id") long id, @Valid Archive posteUser, BindingResult result,
   			Model model) {
   		if (result.hasErrors()) {
   			posteUser.setId(id);
   			return "redirect:/all";
   		}
   	
			Archive a=arch.findByIdd(id);
			List<Archive> ll=arch.findByUniqueCode(posteUser.getUniqueCode());
			if(!ll.isEmpty()) {
				for(Archive ar:ll) {
					if(!ar.getUniqueCode().equals(a.getUniqueCode())) {
						return"redirect:/editArchives/"+id+"?codeUnique";
					}
				}
				
			}
			a.setCin(posteUser.getCin());
			a.setUniqueCode(posteUser.getUniqueCode());
			a.setMontant(posteUser.getMontant());
			a.setNom(posteUser.getNom());
			
   		arch.save(a);
   		model.addAttribute("archive", arch.findAll());
   		return "redirect:/all";
   	}
	
   	@GetMapping("deleteArchive/{id}")
	public String deleteposteUserS(@PathVariable("id") long id, Model model) {
		Archive archive = arch.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid posteUser Id:" + id));
		arch.delete(archive);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("archives", arch.findAll());
		return "redirect:/all";
	}
   	@GetMapping("deleteArchives")
	public String deleteposteUsers( Model model) {
		arch.deleteAll(arch.findAll());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		model.addAttribute("archives", arch.findAll());
		return "redirect:/all";
	}
   	@GetMapping("infos/{id}")
   	public String infos(@PathVariable("id") long id,Model model) {
   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
   		model.addAttribute("user", userr.findById(id));
   		return "/admin/infos";
   	}
     //=======================================================================================================
       
   //===========================================USER========================================================
 
 /*===================>   Le class User Admin (modifier,supprimer,ajouter,afficher)   <======================    **/
   	@GetMapping("soldeServis")
   	public String solde(Model model) {
   		LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
	   	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
	   	List<Categorie> catList=CatRep.findByDate(date);
	   	
	   	if(catList.isEmpty()) {
	   		return "redirect:/parametre/employe?soldeEmpty";
	   	}
	   	model.addAttribute("sommeCourant", CatRep.getSommeCourant(date));
		   model.addAttribute("cat", CatRep.findByDate(date));
		List<pointage> p=pointageService.findBydate(date);
		boolean bool=false;
		int i=0;
		for(pointage pp:p) {
			i+=1;
			if(pp.getUser()==us) {
				bool=true;
			}
		}
		
		if(bool==false || userr.getNbrUsers()!=pointageService.getNbrUserByDate(date)) {
			return "redirect:/absence";
		}
   		return "/admin/soldeEmploye";
	   }

   	@GetMapping("AllDaysSolde")
   	public String allsolde(Model model) {
		   List<Categorie>a=CatRep.findAll();
		   if(a.isEmpty()){
			return "redirect:/parametre/employe?soldeEmpty";
		   }
   		return solde(1, model);
	   }
	   @GetMapping("soldePage/{pageNo}")
	   public String solde(@PathVariable("pageNo") int pageNo,Model model){
		int pageSize=5;
		Page<Categorie>page=catServ.findPaginatedCategorie(pageNo, pageSize);
		List<Categorie>catList=page.getContent();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User us=userr.findByUsername(auth.getName());
		 model.addAttribute("userConnect", us);
		if(pageNo>page.getTotalPages()){
			return "soldePage/1";
		}
		if(catList.isEmpty()) {
			return "redirect:/parametre/employe?soldeEmpty";
		}
		model.addAttribute("somme", CatRep.getSomme());
		model.addAttribute("allcat", catList);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		int begin = page.getNumber()+1 ;
		int end = Math.min(page.getTotalPages(),begin + 4);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		return "/admin/soldeEmploye";
	   }
   	@GetMapping("Emplois")
   	public String emplois(Model model) {
   		LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
	   
   		
   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   		User us=userr.findByUsername(auth.getName());
   		model.addAttribute("cat", userr.getAllUsers(us.getUsername()));
   		Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
   		model.addAttribute("date", dates);
		   model.addAttribute("userConnect", us);
		List<pointage> p=pointageService.findBydate(date);
		boolean bool=false;
		int i=0;
		for(pointage pp:p) {
			i+=1;
			if(pp.getUser()==us) {
				bool=true;
			}
		}
		
		if(bool==false || userr.getNbrUsers()!=pointageService.getNbrUserByDate(date)) {
			return "redirect:/absence";
		}
   		return "/admin/emplois";
   	}
   	
   	@GetMapping("EmploisUser")
   	String emp(Model model) {
  
   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   		User us=userr.findByUsername(auth.getName());
   		for(Role rr:us.getRoles()) {
    		if(!auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(rr.getName()))) {
    			return "redirect:/logout";
    		}
    	}
   		Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
     	model.addAttribute("date", dates);
   		model.addAttribute("userConnect", us);
   		return "/user/emplois";
	   }
	@GetMapping("EmpPage/{pageNo}")
	public String userPage(@PathVariable("pageNo") int pageNo ,Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
		int pageSize=5;
		final Pageable pageable=PageRequest.of(pageNo-1 , pageSize);
		final Page<User>page=userr.findAll(pageable);
		if(pageNo>page.getTotalPages()){
			return "redirect:/EmpPage/1";
		}
		List<User>pointa=page.getContent();
		User us=userr.findByUsername(auth.getName());
		 List<pointage> p=pointageService.findBydate(date);

	 boolean bool=false;
	 int i=0;
	 for(pointage pp:p) {
		 i+=1;
		 if(pp.getUser()==us) {
			 bool=true;
		 }
	 }
	 if(bool==false ) {
		 return "redirect:/absence";
	 }
	 model.addAttribute("userConnect", us);
	 
		 model.addAttribute("employes",pointa);
		 model.addAttribute("currentPage", pageNo);
 		 model.addAttribute("totalPages", page.getTotalPages());
    	model.addAttribute("totalItems", page.getTotalElements());
		int begin = page.getNumber()+1 ;
		int end = Math.min(page.getTotalPages(),begin + 4);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
	 return "/admin/employe";
	}
   	@GetMapping("listEmp")
	public String showUpdateFormee(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
		
	 	User us=userr.findByUsername(auth.getName());
		List<pointage> p=pointageService.findBydate(date);
		boolean bool=false;
		int i=0;
		for(pointage pp:p) {
			i+=1;
			if(pp.getUser()==us) {
				bool=true;
			}
		}
		
		if(bool==false || userr.getNbrUsers()!=pointageService.getNbrUserByDate(date)) {
			return "redirect:/absence";
		}
   		return userPage(1, model);
	}

   
       @GetMapping("editEmploye/{id}")
   	public String showUpdateFormee(@PathVariable("id") long id, Model model) {
   		User posteUser = userr.findById(id);
   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
   		model.addAttribute("employe", posteUser);
   		return "/admin/update-employe";
   	}
       @GetMapping("editEmployeNom/{id}")
      	public String showUpdateFormeNom(@PathVariable("id") long id, Model model) {
      		User posteUser = userr.findById(id);
      		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      		User us=userr.findByUsername(auth.getName());
      		model.addAttribute("nom", "nom");
   			model.addAttribute("userConnect", us);
      		model.addAttribute("employe", posteUser);
      		return "/admin/update-employe";
      	}
       @GetMapping("editEmployeCIN/{id}")
     	public String showUpdateFormeCIN(@PathVariable("id") long id, Model model) {
     		User posteUser = userr.findById(id);
     		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     		User us=userr.findByUsername(auth.getName());
     		model.addAttribute("cin", "cin");
  			model.addAttribute("userConnect", us);
     		model.addAttribute("employe", posteUser);
     		return "/admin/update-employe";
     	}
       @GetMapping("editEmployeEmail/{id}")
    	public String showUpdateFormeemail(@PathVariable("id") long id, Model model) {
    		User posteUser = userr.findById(id);
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		User us=userr.findByUsername(auth.getName());
    		model.addAttribute("email", "email");
 			model.addAttribute("userConnect", us);
    		model.addAttribute("employe", posteUser);
    		return "/admin/update-employe";
    	}
       @GetMapping("editEmployeTel/{id}")
   	public String showUpdateFormeTel(@PathVariable("id") long id, Model model) {
   		User posteUser = userr.findById(id);
   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   		User us=userr.findByUsername(auth.getName());
   		model.addAttribute("tel", "tel");
			model.addAttribute("userConnect", us);
   		model.addAttribute("employe", posteUser);
   		return "/admin/update-employe";
   	}
       @GetMapping("editEmployeAdresse/{id}")
      	public String showUpdateFormeadr(@PathVariable("id") long id, Model model) {
      		User posteUser = userr.findById(id);
      		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      		User us=userr.findByUsername(auth.getName());
      		model.addAttribute("adresse", "adresse");
   			model.addAttribute("userConnect", us);
      		model.addAttribute("employe", posteUser);
      		return "/admin/update-employe";
      	}
       @GetMapping("editEmployeCode/{id}")
     	public String showUpdateFormecode(@PathVariable("id") long id, Model model) {
     		User posteUser = userr.findById(id);
     		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     		User us=userr.findByUsername(auth.getName());
     		model.addAttribute("codeSecret", "code");
  			model.addAttribute("userConnect", us);
     		model.addAttribute("employe", posteUser);
     		return "/admin/update-employe";
     	}
       @GetMapping("editEmployeGuichet/{id}")
    	public String showUpdateFormeGuichet(@PathVariable("id") long id, Model model) {
    		User posteUser = userr.findById(id);
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		User us=userr.findByUsername(auth.getName());
    		stockage stock=stockRep.findByUser(us);
    		model.addAttribute("guichet", stock.getNbrGuichet());
 			model.addAttribute("userConnect", us);
    		model.addAttribute("employe", posteUser);
    		return "/admin/update-employe";
    	}
       @GetMapping("editEmployeUsername/{id}")
   	public String showUpdateFormeUsername(@PathVariable("id") long id, Model model) {
   		User posteUser = userr.findById(id);
   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   		User us=userr.findByUsername(auth.getName());
   		stockage stock=stockRep.findByUser(us);
   		model.addAttribute("username", stock.getNbrGuichet());
			model.addAttribute("userConnect", us);
   		model.addAttribute("employe", posteUser);
   		return "/admin/update-employe";
   	}
       @GetMapping("editEmployePassword/{id}")
      	public String showUpdateFormepassword(@PathVariable("id") long id, Model model) {
      		User posteUser = userr.findById(id);
      		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      		User us=userr.findByUsername(auth.getName());
      		
      		model.addAttribute("password","d");
   			model.addAttribute("userConnect", us);
      		model.addAttribute("employe", posteUser);
      		return "/admin/update-employe";
      	}
       @GetMapping("editEmployeRole/{id}")
     	public String showUpdateFormeRole(@PathVariable("id") long id, Model model) {
     		User posteUser = userr.findById(id);
     		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     		User us=userr.findByUsername(auth.getName());
     		
     		model.addAttribute("role","d");
  			model.addAttribute("userConnect", us);
     		model.addAttribute("employe", posteUser);
     		return "/admin/update-employe";
     	}
    	@PostMapping("updateUserNom/{id}")
    	public String updateNom(@PathVariable("id") long id,@Valid userDto user,Model model) {
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       		User us=userr.findByUsername(auth.getName());
    		User uss=userr.findById(id);
    		if(user.getRoles()!=null) {
    		for(Role ro:uss.getRoles()) {
    			ro.setName(user.getRoles());
    			uss.setGuichet(-3);
    			roleRep.save(ro);
    		}
    		}
    		
    		if(user.getNewPassword()!=null) {
    			uss.setPassword(passwordEncoder.encode(user.getNewPassword()));
    		}
    		if(user.getUsername()!=null) {
    			
    			List<User> usr=userr.findByUser(user.getUsername());
    			if(!usr.isEmpty() && !uss.getUsername().equals(user.getUsername())) {
    				return "redirect:/editEmploye/{id}?username";
    			}
    			uss.setUsername(user.getUsername());
    			if(uss.equals(us)) {
    				userr.save(uss);
    	    		return "redirect:/logout?logout";
    			}
    		}
    		if(user.getCodeSecret()!=null) {
    			
    				uss.setCodeSecret(user.getCodeSecret());
    			
    		}
    		
    		if(user.getGuichet()!=0) {
    			try {
    			
    		
    			User cat =userr.findByUsername(uss.getUsername());
    			List<User>cati=userr.findByNotUser(uss.getUsername());
    			boolean bool=true;
    			String catNameCat="";
    			if(Integer.valueOf(cat.getTime())<13) {
    				catNameCat="jour";
				}else if(Integer.valueOf(cat.getTime())>=13) {
					catNameCat="nuit";
				}
    			if(!cati.isEmpty()) {
    			for(User cat1:cati) {
    				String catNamCat1="";
    				if(Integer.valueOf(cat1.getTime())<13) {
    					catNamCat1="jour";
    				}else if(Integer.valueOf(cat1.getTime())>=13) {
    					catNamCat1="nuit";
    				}
    				
    				if(catNamCat1.equals(catNameCat) && cat1.getGuichet()==user.getGuichet()) {
    					bool=false;
    				}
    			}
    			}else {
    				return "redirect:/parametre/employe?soldeEmpty";
    			}
    			if(bool==false && user.getGuichet()!=-3) {
    				return "redirect:/editEmploye/{id}?guichet";
    			}
    		
    			}catch(Exception ex) {
    				System.out.println(ex.getMessage()+" "+ex.getCause());
    			}
    			
    			uss.setGuichet(user.getGuichet());
    		}
    		
    		if(user.getEmail()!=null) {
    			List<User>users2=userr.findByEmail(user.getEmail());
    			if(!users2.isEmpty() && !uss.getEmail().equals(user.getEmail())) {
   				 return "redirect:/editEmploye/{id}?email";
   			 }
    			uss.setEmail(user.getEmail());
    		}
    		if(user.getTel()!=0) {
    			List<User>users2=userr.findByTel(user.getTel());
    			if(!users2.isEmpty() && uss.getTel()!=user.getTel()) {
    				 return "redirect:/editEmploye/{id}?tel";
    			}
    			uss.setTel(user.getTel());
    		}
    		if(user.getNom()!=null && user.getPrenom()!=null) {
    		uss.setNom(user.getNom());
    		uss.setPrenom(user.getPrenom());
    		}
    		if(user.getCin()!=0) {
    			List<User>users=userr.findByCIN(user.getCin());
    			 if(!users.isEmpty() && uss.getCin()!=user.getCin()) {
    				 return "redirect:/editEmploye/{id}?cin";
    			 }
    			uss.setCin(user.getCin());
    		}
    		if(user.getAdresse()!=null) {
    			uss.setAdresse(user.getAdresse());
    		}
    		userr.save(uss);
    		return "redirect:/editEmploye/{id}";
    	}
   	@PostMapping("updateUser/{id}")
   	public String updateposteUserse(@ModelAttribute("User") @Valid User posteUser, @PathVariable("id") long id,@RequestParam("AncienPassword") String Ancien,@RequestParam("AncienCodeSecret") String code, BindingResult result,
   			Model model) {
   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User uss=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", uss);

		List<User>users=userr.findByCIN(posteUser.getCin());
		
		List<User>users1=userr.findByguichet(posteUser.getGuichet());
		
		List<User>users2=userr.findByUser(posteUser.getUsername());
		
		User userId=userr.findById(id);
		
		List<User> usByEmail=userr.findByEmail(posteUser.getEmail());
		
		List<User> usByTel=userr.findByTel(posteUser.getTel());
		if(!usByTel.isEmpty()&& userId.getTel()!=posteUser.getTel()) {
        	return "redirect:/editEmploye/{id}?tel";
        }
		if(!usByEmail.isEmpty() && !userId.getEmail().equals(posteUser.getEmail())) {
        	return "redirect:/editEmploye/{id}?email";
        }
		if(!users2.isEmpty() && !userId.getUsername().equals(posteUser.getUsername())) {
        	return "redirect:/editEmploye/{id}?username";
        }
        if(!users.isEmpty() && userId.getCin()!=posteUser.getCin()) {
        	return "redirect:/editEmploye/{id}?cin";
        }
        if(!users1.isEmpty() && userId.getGuichet()!=posteUser.getGuichet()) {
        	return "redirect:/editEmploye/{id}?guichet";
        }
        if(!passwordEncoder.matches(Ancien, userId.getPassword())) {
        	return "redirect:/editEmploye/{id}?password";
        }
        if(!userId.getCodeSecret().equals(code)) {
        	return "redirect:/editEmploye/{id}?code";
        }
   		if (result.hasErrors()) {
   			User posteUsert = userr.findById(id);
   	   		model.addAttribute("employe", posteUsert);
   	   		return "/admin/update-employe";
   		}
   		
   		User us=userr.findByUsername(posteUser.getUsername());
   		posteUser.setPassword(passwordEncoder.encode(posteUser.getPassword()));
   		posteUser.setRoles(us.getRoles());
   		userr.save(posteUser);
   		return "redirect:/listEmp";
   	}
	
   	@GetMapping("deleteEmploye/{id}")
	public String deleteposteUsereS(@PathVariable("id") long id, Model model) {
   		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User us=userr.findByUsername(auth.getName());
		
		model.addAttribute("userConnect", us);
		User user = userr.findById(id);
		userr.delete(user);
		model.addAttribute("employes", userr.findAll());
		if(us.getId()==user.getId()) {
		return "redirect:/logout";
		}else {
			return "redirect:/listEmp";
		}
		
		
	}
      
   	public boolean verifCIN(long cin) {
		String ciin=String.valueOf(cin);
		if(ciin.length()!=8) {
			return false;
		}else {
			return true;
		}
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
       
       
	
	public boolean verfString(String chaine) {
		boolean numeric = true;

        try {
            Double num = Double.parseDouble(chaine);
        } catch (NumberFormatException e) {
            numeric = false;
        }

     return numeric;
	}
	public int verfStringInt(String chaine) {
		int numeric = 1;

        try {
            long num = Long.parseLong(chaine);
        } catch (NumberFormatException e) {
            numeric = 0;
        }

     return numeric;
	}
/*===================>   Le class Recherche Admin    <======================    **/
	@PostMapping("/searchEmployee")
	public String SearchEmp(@RequestParam("searchEmp") String employeeName,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		List<User>use=userr.getUsersByNom(employeeName);
		if(use.isEmpty()) {
			return "redirect:/listEmp?notFound";
		}
		model.addAttribute("chercher", "im a search page !!");
		model.addAttribute("userConnect", us);
		model.addAttribute("employes", userr.getUsersByNom(employeeName));
		return "/admin/employe";
		
	}
	@PostMapping("searchsoldeServis")
   	public String searchsolde(@RequestParam("search") String categorie,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
   		LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
	   	List<Categorie> catList=CatRep.getCategorieByNomAndDate(categorie,date);
   		if(catList.isEmpty() ) {
   			return "redirect:/soldeServis?notFound";
   		}
   		model.addAttribute("chercher", "im a search page !!");
   		model.addAttribute("sommeCourant", CatRep.getCategorieCourantSommeByNom(categorie, date));
   		model.addAttribute("cat", CatRep.getCategorieByNomAndDate(categorie,date));
   		return "/admin/soldeEmploye";
   	}
	@PostMapping("searchAllDaysSolde")
   	public String searchallsolde(@RequestParam("search") String categorie ,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
   		List<Categorie> catList=CatRep.getCategorieByNom(categorie);
   		if(catList.isEmpty()) {
   			return "redirect:/AllDaysSolde?notFound";
   		}
   		model.addAttribute("chercher", "im a search page !!");
   		model.addAttribute("somme", CatRep.getCategorieSommeByNom(categorie));
   		model.addAttribute("allcat",  CatRep.getCategorieByNom(categorie));
   		return "/admin/soldeEmploye";
   	}
	
	@PostMapping("/searchAllArchive")
	public String SearchAllArch(@RequestParam("search") String search ,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");
		List<Archive>archi=arch.getArchiveByNom(search);
		if(archi.isEmpty()) {
			return "redirect:/all?notFound";
		}
		model.addAttribute("chercher", "im a search page !!");
		model.addAttribute("userConnect", us);
		model.addAttribute("retrait", arch.MontantAllSumRetrait(l));
		model.addAttribute("somme", arch.MontantAllsum(l));
		model.addAttribute("alluser", arch.getArchiveByNom(search));
		return "/admin/allArchive";
	}
	@PostMapping("/searchArchive")
	public String SearchArch(@RequestParam("search") String search ,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
		LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
		List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");
		List<Archive>archi=arch.getArchiveByNomAndDate(search,date);
		if(archi.isEmpty()) {
			return "redirect:/listArchive?notFound";
		}
		model.addAttribute("chercher", "im a search page !!");
		model.addAttribute("userConnect", us);
		model.addAttribute("retrait", arch.MontantSumRetrait(l,date));
		model.addAttribute("somme", arch.MontantSum(l,date));
		model.addAttribute("date", dates);
		model.addAttribute("archives", arch.getArchiveByNomAndDate(search,date));
		return "/admin/archiveList";

	}
	@PostMapping("/searchPresence")
	public String searchPresence(@RequestParam("search") String search,Model model) {
		Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
		LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
    	List<pointage> plist=pointageService.getPointagByNomAndDate(search,date);
    	if(plist.isEmpty()) {
    		return"redirect:/adminPresence?notFound";
    	}
    	model.addAttribute("chercher", "im a search page !!");
		model.addAttribute("userConnect", us);
    	model.addAttribute("date", dates);
    	model.addAttribute("presence", pointageService.getPointagByNomAndDate(search,date));
    	return "/admin/adminPresence";
	}
	@PostMapping("/searchPr")
	public String searchPresences(@RequestParam("search") String search,Model model) {
		Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
		LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
    	List<pointage> plist= pointageService.getPointagByNom(search);
    	if(plist.isEmpty()) {
    		return"redirect:/allAdminPresence?notFound";
    	}
    	model.addAttribute("chercher", "im a search page !!");
		model.addAttribute("userConnect", us);
    	model.addAttribute("date", dates);
    	model.addAttribute("presence", pointageService.getPointagByNom(search));
    	return "/admin/alladminPresence";
	}
	/*===================>   Le class Recherche USER    <======================    **/
	@PostMapping("/searchArch")
	public String searcharch(@RequestParam("search") String search ,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");
		List<Archive>a=arch.getArchiveByNomAndUser(search, us);
		if(a.isEmpty()) {
			return "redirect:/listAll?notFound";
		}
		model.addAttribute("chercher", "im a search page !!");
		model.addAttribute("userConnect", us);
		model.addAttribute("retrait", arch.MontantAllSumRetraitUser(l, us));
		model.addAttribute("somme", arch.MontantAllsumUser(l, us));
		model.addAttribute("archives", arch.getArchiveByNomAndUser(search, us));
		
		return "/user/allArchive";
	}
	@PostMapping("/searchEmplois")
	public String SearchEmplois(@RequestParam("search") String search ,Model model) {
		LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
	   	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   		User us=userr.findByUsername(auth.getName());
	   	
	   	if(userr.getnotUsersByNom(search,us.getUsername()).isEmpty()) {
	   		return "redirect:/Emplois?notFound";
	   	}
   		model.addAttribute("cat", userr.getnotUsersByNom(search,us.getUsername()));
   		Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
     	model.addAttribute("chercher", "im a search page !!");
   		model.addAttribute("date", dates);
   		model.addAttribute("userConnect", us);
   		return "/admin/emplois";
	}

	@PostMapping("/searchArchiveEmp")
	public String SearchArchemp(@RequestParam("search") String search ,Model model) {
		Date time = new Date();
	   	DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL);
     	String dates=formater.format(time);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		LocalDate myObj = LocalDate.now();
	   	String date=myObj.toString();
		Categorie cat=CatRep.findByUserAndDate(us, date);
		model.addAttribute("chercher", "im a search page !!");
		model.addAttribute("cat", cat);
		model.addAttribute("userConnect", us);
		
	   	List<pointage> p=pointageService.findBydateAndUserAndCas(date, us, 1);
	   	if(p.isEmpty()) {
	   		return "redirect:/user?pointage";
	   	}
	   	List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");
		Categorie c=CatRep.findByUserAndDate(us, date);
		Double ret=arch.MontantSumRetraitUser(l, date, us);
	   	Double som=arch.MontantSumUser(l, date, us);
	 	Double montantUser=c.getMontant();
	 	Double rslt=null;
	 	if(ret==null) {ret=0.0;	}
	 	if(som==null) {som=0.0;	}
	 	if(montantUser==null) {montantUser=0.0;	}
	 	
	 	rslt=(som+montantUser)-ret;
	 	List<Archive>a=arch.getArchiveByNomAndDateAndUser(search,date,us);
	 	if(a.isEmpty()) {
	 		return "redirect:/list?notFound";
	 	}
	 	model.addAttribute("chercher", "im a search page !!");
		model.addAttribute("stockCourant", rslt);
		model.addAttribute("retrait",arch.MontantSumRetraitUser(l, date, us) );
		model.addAttribute("somme",arch.MontantSumUser(l, date, us));
		model.addAttribute("archives", arch.getArchiveByNomAndDateAndUser(search,date,us));
		model.addAttribute("date", dates);
		return "/user/archiveList";

	}
    

	

}