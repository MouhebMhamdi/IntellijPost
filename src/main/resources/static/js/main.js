(function ($) {
    "use strict";

    var input1 = $('.form-control');
	$('a[href^="#"]').click(function(){  
		var id = $(this).attr("href");
		var offset = $(id).offset().top 
		$('html, body').animate({scrollTop: offset}, 'slow'); 
		return false;  
	}); 
    $('.validate-forms').on('submit',function(){
        var check = true;

        for(var i=0; i<input1.length; i++) {
            if(validate(input1[i]) == false){
                showValidate(input1[i]);
                check=false;
            }
        }

        return check;
    });
    $(document).on('click', '.number-spinner button', function () {    
    	var btn = $(this),
    		oldValue = btn.closest('.number-spinner').find('input').val().trim(),
    		newVal = 0;
    	
    	if (btn.attr('data-dir') == 'up') {
    		newVal = parseInt(oldValue) + 1;
    	} else {
    		if (oldValue > 1) {
    			newVal = parseInt(oldValue) - 1;
    		} else {
    			newVal = 1;
    		}
    	}
    	btn.closest('.number-spinner').find('input').val(newVal);
    });

    $('.validate-forms .form-control').each(function(){
        $(this).focus(function(){
        	
           hideValidate(this);
           
        });
        
    });

 
    /*==================================================================
    [ Validate ]*/
    var input = $('.input100');
 
    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });
    function validateEmail($email) {
    	  var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    	  return emailReg.test( $email );
    	}

    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });
 

    function validate (input) {
    	if($(input).attr('type')=='number'){
    		if(isNaN($(input).val())){
    			return false;
    		}
    	}
    	if($(input).attr('name')=='guichet'){
    		if($(input).val()=="-1"){
    			return false;
    		}
       	}
    	
    	if($(input).attr('name')=='confirmePassword'){
       		if($(input).val()!=$('.pass').val()){
       			return false;
       		}
       	}
    	if($(input).attr('name')=='confirmPassword'){
       		if($(input).val()!=$('.pass').val()){
       			return false;
       		}
       	}
    	if($(input).attr('name')=='confirmePassword'){
       		if($(input).val()!=$('.pass').val()){
       			return false;
       		}
       	}
    	 if($(input).attr('name')=="tel"){
         	if($(input).val().length!=8){
         		return false;
         	}
         }
    	 
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if(!validateEmail($(input).val())) {
                return false;
            }
            var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            return regex.test($(input).val());
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
        if($(input).attr('name') =='cin'){
        	if($(input).val().length!=8){
        		
        		return false;
        	}
        }
        
        if($(input).attr('name') =='uniqueCode'){
        	if($('.nom').val()=="Facture"){
    		if($(input).val().length!=16 || isNaN($(input).val())|| $(input).val().charAt(0)!=0 || $(input).val().charAt(1)!=0 || $(input).val().charAt(2)!=0 || $(input).val().charAt(3)!=0 || $(input).val().charAt(4)!=0 ){
    			return false;
    		}
    		
        	}
        	var v=$('.nom').val();
       	 if(v =="Emmission Mondat Minute" || v=="Emmission Mondat Ordinaire" || v=="Paiement Mondat" || v=="Paiement Mondat Ordinaire"){
        		if($(input).val().length!=12 || isNaN($(input).val())|| $(input).val().charAt(0)!=8 || $(input).val().charAt(1)!=8  ){
        			return false;
        		}
        		
            }
       	 if(v=="Crée Edinar" || v=="Recharge Edinar"){
       		if($(input).val().length!=16 || isNaN($(input).val())|| $(input).val().charAt(0)!=5 || $(input).val().charAt(1)!=3 || $(input).val().charAt(2)!=5  || $(input).val().charAt(3)!=9  ){
    			return false;
    		}
       	 }
       	 
       	if(v=="Paiement Cheque"){
       		if($(input).val().length!=9 || isNaN($(input).val()) ){
    			return false;
    		}
       	}
     
   	 if(v=="Crée Epargne" || v=="Versement Epargne" || v=="Retrait Epargne"){
   		if($(input).val().length!=6 || isNaN($(input).val().substring(0,5))|| $(input).val().toUpperCase().charAt(5)<'A'  || $(input).val().toUpperCase().charAt(5)>'Z'){
			return false;
		}
   		
		}
   	 if(v=="western union"){
			if($(input).val().length!=10 || isNaN($(input).val())){
				return false;
			}
   	 }
			if(v=="Colis"){
				if($(input).val().length!=13 || isNaN($(input).val().substring(2, 16)) || ($(input).val().charAt(0)<'0' ||$(input).val().charAt(0)>'9' ) && ($(input).val().toUpperCase().charAt(0)<'A' || $(input).val().toUpperCase().charAt(0)>'A')){
					return false;
				}
				
			}
        
   
   	 
       	 
 }
        	
        	
    	
        
    }

    function showValidate(input) {
    	
        var thisAlert = $(input).parent();
        var v=$('.nom').val();
        
        
        $(thisAlert).addClass('border');
        $(thisAlert).addClass('alert-validate');
        if($(input).attr('name')=='confirmePassword'){
       		if($(input).val()!=$('.pass').val()){
       			$(thisAlert).addClass('alert-validate');
            	$('#cpassword').parent().addClass('alert-validate');
            	$('#cpassword').parent().addClass('border');
            	$(thisAlert).addClass('border');
       		}
       	}
        if($(input).attr('name')=="montant"){
        	$(thisAlert).addClass('alert-validate');
        	$('#montant').addClass('alertbg');
        	 $(thisAlert).addClass('border');
        }
        if($(input).attr('name')=="nom"){
        	if($(input).val()==''){
        		$(thisAlert).addClass('alert-validate');
        	$('#nom').addClass('alertbg');
        	 $(thisAlert).addClass('border');
        	}
        }
        if($(input).attr('name')=="prenom"){
        	if($(input).val()==''){
        		$(thisAlert).addClass('alert-validate');
        	$('#prenom').addClass('alertbg');
        	 $(thisAlert).addClass('border');
        	}
        }
        if($(input).attr('name')=="username"){
        	if($(input).val()==''){
        		$(thisAlert).addClass('alert-validate');
        	
        	 $(thisAlert).addClass('border');
        	}
        }
        if($(input).attr('name')=="password"){
        	if($(input).val()==''){
        		$(thisAlert).addClass('alert-validate');
        	
        	 $(thisAlert).addClass('border');
        	}
        }
        if($(input).attr('name')=="email"){
        
        }
		if($(input).attr('name')=="cin"){
			 $(thisAlert).addClass('border');
			 $(thisAlert).addClass('alert-validate');
				$('#cin').addClass('alertbg');
				if($(input).val()!='' && $(input).val().length!=8){
					
		        $('.feedback').addClass('show');
				}
		}
		
		if($(input).attr('name')=="uniqueCode"){
			 $(thisAlert).addClass('border');
			 $(thisAlert).addClass('alert-validate');
			$('#uniqueCode').addClass('alertbg');
		 	 if(v =="Emmission Mondat Minute" || v=="Emmission Mondat Ordinaire" || v=="Paiement Mondat" || v=="Paiement Mondat Ordinaire"){
		 		 $('.feedback1').text("le numéro d'"+jQuery($('.nom')).find('option:selected').text()+" est incorrect !").addClass('show');
		 	 }
		 	 
		 	 if(v=="Crée Epargne" || v=="Versement Epargne" || v=="Retrait Epargne"){
		 		 $('.feedback1').text("le numéro de"+jQuery($('.nom')).find('option:selected').text()+" est incorrect !").addClass('show');
		 	 }
		 	if(v=="Colis"){
		 		 $('.feedback1').text("le numéro de "+jQuery($('.nom')).find('option:selected').text()+" est incorrect !").addClass('show');

		 	}
		 	 if(v=="western union"){
		 		 $('.feedback1').text("le numéro de "+jQuery($('.nom')).find('option:selected').text()+" est incorrect !").addClass('show');

		 	 }
		 	if(v=="Paiement Cheque"){
		 		 $('.feedback1').text("le numéro de "+jQuery($('.nom')).find('option:selected').text()+" est incorrect !").addClass('show');
		 	}
	       	 if(v=="Crée Edinar" || v=="Recharge Edinar"){
	       		 $('.feedback1').text("le numéro du carte E-dinar est incorrect !").addClass('show');
	       	 }
	     	if(v=="Facture"){
	     		$('.feedback1').text("le numéro du "+jQuery($('.nom')).find('option:selected').text()+" est incorrect !").addClass('show');
	     	}
	     	
	     	

	}
        
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();
        $(thisAlert).removeClass('alertbg');
		$(thisAlert).removeClass('show');
		 $(thisAlert).removeClass('border');
		 $(thisAlert).removeClass('alert-validate');
      
       
    	if($(input).attr('name')=="cin"){
    		$('#cin').removeClass('alert-validate');
    		$('#cin').removeClass('alertbg');
    		$('.feedback').removeClass('show');
    		 $(thisAlert).removeClass('border');
    	}
    	
    	if($(input).attr('name')=="nom"){
    		$('#nom').removeClass('alertbg');
    		$('#nom').removeClass('alert-validate');
    		
    		$(thisAlert).removeClass('border');
   
    	}
    	if($(input).attr('name')=="montant"){
    		$('#montant').removeClass('alertbg');
    		$('#montant').removeClass('alert-validate');
    		  $(thisAlert).removeClass('border');
    	}
    	
    	if($(input).attr('name')=="uniqueCode"){
    		$('#uniqueCode').removeClass('alertbg');
    		$('#uniqueCode').removeClass('alert-validate');
    		 $(thisAlert).removeClass('border');
    		$('.feedback1').removeClass('show');
    	}
        
    }
    
    /*==================================================================
    [ Show pass ]*/
    var showPass = 0;
    $('.btn-show-pass').on('click', function(){
        if(showPass == 0) {
            $(this).next('input').attr('type','text');
            $(this).find('i').removeClass('fa-eye');
            $(this).find('i').addClass('fa-eye-slash');
            showPass = 1;
        }
        else {
            $(this).next('input').attr('type','password');
            $(this).find('i').removeClass('fa-eye-slash');
            $(this).find('i').addClass('fa-eye');
            showPass = 0;
        }
        
    });
    

})(jQuery);

