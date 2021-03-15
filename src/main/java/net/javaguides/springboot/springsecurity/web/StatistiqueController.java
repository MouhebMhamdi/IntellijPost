package net.javaguides.springboot.springsecurity.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.springsecurity.model.Archive;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.model.pointage;
import net.javaguides.springboot.springsecurity.model.stockage;
import net.javaguides.springboot.springsecurity.repository.ArchiveRepository;
import net.javaguides.springboot.springsecurity.repository.CategorieRepository;
import net.javaguides.springboot.springsecurity.repository.PointageService;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.repository.stockageRepository;
import net.javaguides.springboot.springsecurity.web.dto.userDto;

@Controller
@RequestMapping("/statistique")
public class StatistiqueController {
	@Autowired
	private final stockageRepository stockRep;
	@Autowired
	private final UserRepository userr;
	@Autowired
	private final PointageService pointageRep;
	@Autowired
	private final CategorieRepository CatRep;
	@Autowired
	private final ArchiveRepository archRep;
	
	@Autowired
	public StatistiqueController(UserRepository user,PointageService pointageRep,CategorieRepository CatRep,ArchiveRepository archRep,stockageRepository stockRep) {
		this.userr=user;
		this.pointageRep=pointageRep;
		this.archRep=archRep;
		this.stockRep=stockRep;
		this.CatRep=CatRep;
	}
	@GetMapping("stat")
	public String stat(Model model) {
Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
		stockage stock=stockRep.findByUser(userr.findByUsername(auth.getName()));
		long totWork=(userr.getAllWork(date)*100)/(stock.getNombre());
		
		model.addAttribute("work", totWork);
		
		int totAbs=(pointageRep.getAllAbsence(date)*100)/(pointageRep.allPointage());
		int totPres=(pointageRep.getAllPresence(date)*100)/(pointageRep.allPointage());
		List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");

		User prime=null;
		String nomPrenom="";
		Double somPrime=0.0;
		for(Archive arch: archRep.getArchive(date, us)) {
			if(archRep.somme(l, arch.getUser(),date)==null){
				model.addAttribute("prime", null);
				break;
			}
			if(archRep.somme(l, arch.getUser(),date)>somPrime) {
				somPrime=archRep.somme(l, arch.getUser(),date);
				prime=arch.getUser();
			}
		}
		if(CatRep.getCategorie(prime,date)!=null && somPrime<=CatRep.getCategorie(prime,date).getMontant()) {
			model.addAttribute("prime", null);
		}else{
		model.addAttribute("prime", prime);
		}
    	List<pointage> p=pointageRep.findBydate(date);
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
		model.addAttribute("absence",totAbs);
		model.addAttribute("presence",totPres);
		if(archRep.travailsTot()!=0) {
			model.addAttribute("travailsTot", archRep.travailsTot());
			}else {
				model.addAttribute("travailsTot", "0");
			}
		if(archRep.travails(date)!=0) {
			model.addAttribute("travails", archRep.travails(date));
			}else {
				model.addAttribute("travails", "0");
			}
		Double argentTot=archRep.MontantAllsum(l);
		String  argentTot1=String.valueOf(argentTot);
		
		if(argentTot1.equals("null")) {
			model.addAttribute("argentsTot", "0");
		}else {
			model.addAttribute("argentsTot", argentTot);
		}
		Double argent=archRep.MontantAllsumDate(l,date);
		String  argent1=String.valueOf(argent);
		
		if(argent1.equals("null")) {
			model.addAttribute("argents", "0");
		}else {
			model.addAttribute("argents", argent);
		}
			int nbr=pointageRep.nbrJoursTravail(1);
			String  nbr1=String.valueOf(nbr);

			if(nbr1.equals("0")) {
				model.addAttribute("nbrs", nbr1);
			}else {
				model.addAttribute("nbrs", nbr);
			}
			
			int abs=pointageRep.nbrJoursTravail(0);
			String  abs1=String.valueOf(abs);
			
			if(nbr1.equals("0")) {
				model.addAttribute("abss", abs1);
			}else {
				model.addAttribute("abss", abs);
			}

		
		model.addAttribute("employes", userr.getAllUsers(auth.getName()));
		
		return"/admin/stat";
	}
	
	@GetMapping("nbrJour/{id}")
	public String nbrJour(@PathVariable("id") long id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
    	User us=userr.findByUsername(auth.getName());
		model.addAttribute("userConnect", us);
		LocalDate myObj = LocalDate.now();
		String date=myObj.toString();
		stockage stock=stockRep.findByUser(userr.findByUsername(auth.getName()));
		long totWork=(userr.getAllWork(date)*100)/(stock.getNombre());
		
		model.addAttribute("work", totWork);
		
		int totAbs=(pointageRep.getAllAbsence(date)*100)/(pointageRep.allPointage());
		int totPres=(pointageRep.getAllPresence(date)*100)/(pointageRep.allPointage());
		List<String>l=new ArrayList<String>();
		l.add("Paiement Mondat");
		l.add("Paiement Mondat Ordinaire");
		l.add("Paiement Cheque");
		l.add("Crée Edinar");
		l.add("Crée Epargne");
		l.add("Retrait Epargne");
		l.add("western union");
		l.add("Virement");

    
    	List<pointage> p=pointageRep.findBydate(date);
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
		model.addAttribute("absence",totAbs);
		model.addAttribute("presence",totPres);
		model.addAttribute("us", userr.findById(id));
		if(archRep.travail(userr.findById(id))!=0) {
		model.addAttribute("travail", archRep.travail(userr.findById(id)));
		}else {
			model.addAttribute("travail", "0");
		}
		Double argent=archRep.MontantAllsumUser(l, userr.findById(id));
		String  argent1=String.valueOf(argent);
		if(argent1.equals("null")) {
			model.addAttribute("argent", "0");
		}else {
			model.addAttribute("argent", argent);
		}
			int nbr=pointageRep.nbrJours(1, userr.findById(id));
			String  nbr1=String.valueOf(nbr);

			if(nbr1.equals("0")) {
				model.addAttribute("nbr", nbr1);
			}else {
				model.addAttribute("nbr", nbr);
			}
			
			int abs=pointageRep.getAbsence(userr.findById(id));
			String  abs1=String.valueOf(abs);
			
			if(nbr1.equals("0")) {
				model.addAttribute("abs", abs1);
			}else {
				model.addAttribute("abs", "0");
			}

		
		model.addAttribute("employes", userr.getAllUsers(auth.getName()));
		
		return"/admin/stat";
	}
	
	

	
}
