function pdf(){
  var doc = new jsPDF();

  doc.autoTable({ html: '#id' });


  doc.save('posteTn.pdf');
}
function showError(msg){
	 Dialog.alert(msg);
	  return false;
}
function refresh(){
    var t = 1000; // rafraîchissement en millisecondes
    setTimeout('showDate()',t)
}

function showDate() {
    var date = new Date()
    var h = date.getHours();
    var m = date.getMinutes();
    var s = date.getSeconds();
    if( h < 10 ){ h = '0' + h; }
    if( m < 10 ){ m = '0' + m; }
    if( s < 10 ){ s = '0' + s; }
    var time = h + ':' + m + ':' + s
    document.getElementById('horloge').innerHTML = time;
    refresh();
 }


function verifcin()
{
   if(f.cin.value.length!=8){
	   swal({
		   title: "Erreur CIN !!",
		   text: "S'il vous plait verifier le champ !!",
		   icon: "error",
		   button: false,
		 });
	   return false;
	   f.cin.focus();
   }
}
function confirmeDelete(lien1) {
    Dialog.confirm('Voulez-vous supprimer ?', 'Question', (dlg) => {
        location.href=lien1;
        dlg.close();
    }, (dlg) => {
        dlg.close();
    });
}
function verif(){
	
	if(f.nom.options.selectedIndex==0){
		   Dialog.alert('il faux séléctionner un type !!');
		   return false;
		   f.nom.focus();
	}
	 if(f.cin.value.length!=8 ){
		 Dialog.alert('CIN incorrect !!');
		   return false;
		   f.cin.focus();
	   }
	 if(f.montant.value==""){
		 Dialog.alert('Invalide Montant!!');
		 f.montant.focus();
		 return false;
	 }
	var v=f.nom.options[f.nom.selectedIndex].value;
	 if(v =="Emmission Mondat Minute" || v=="Emmission Mondat Ordinaire" || v=="Paiement Mondat" || v=="Paiement Mondat Ordinaire"){
		 if(f.uniqueCode.value.length!=8 || isNaN(f.uniqueCode.value)){
			 Dialog.alert('Numéro de Mondat incorrect !!');
			 f.uniqueCode.focus();
			 return false;
		 }
	 
		 if(f.uniqueCode.value.charAt(0)!=8 || f.uniqueCode.value.charAt(1)!=8){
			 Dialog.alert(' Numéro de Mondat incorrect !!');
			 f.uniqueCode.focus();
			 return false;
		 }
	
	 }
	 if(v=="Crée Edinar" || v=="Recharge Edinar"){
		 if(f.uniqueCode.value.length!=16 || isNaN(f.uniqueCode.value)){
			 Dialog.alert('Numéro Edinar incorrect !!');
			 f.uniqueCode.focus();
			 return false;
			 }
		 if(f.uniqueCode.value.charAt(0)!=5 || f.uniqueCode.value.charAt(1)!=3 || f.uniqueCode.value.charAt(2)!=5 || f.uniqueCode.value.charAt(3)!=9){
			 Dialog.alert('Numéro Edinar incorrect !!');
			 f.uniqueCode.focus();
			 return false;
		 }
	 }
	 
	 if(v=="Paiement Cheque"){
		 if(f.uniqueCode.value.length!=9 || isNaN(f.uniqueCode.value)){
			 Dialog.alert('Numéro de Chéque incorrect !!');
			 f.uniqueCode.focus();
			 return false;
		 }
		
	}	
	 if(v=="Facture"){
		 if(f.uniqueCode.value.length!=16 || isNaN(f.uniqueCode.value)){
			 Dialog.alert('Numéro du facture incorrect !!');
			 f.uniqueCode.focus();
			 return false;
		 }
		 if(f.uniqueCode.value.charAt(0)!=0 || f.uniqueCode.value.charAt(1)!=0 || f.uniqueCode.value.charAt(2)!=0 || f.uniqueCode.value.charAt(3)!=0 || f.uniqueCode.value.charAt(4)!=0){
			 Dialog.alert('Numéro du facture incorrect !!');
			 f.uniqueCode.focus();
			 return false;
		 }
	}	
	
	 if(v=="Crée Epargne" || v=="Versement Epargne" || v=="Retrait Epargne"){
		 if(f.uniqueCode.value.length!=6 ){
			 Dialog.alert('Numéro Epargne incorrect !!');
			 f.uniqueCode.focus();
			 return false;
		 }
		 for(i=0;i<=4;i++){
			 if(isNaN(f.uniqueCode.value.charAt(i))){
				 Dialog.alert('Numéro Epargne incorrect !!');
				 f.uniqueCode.focus();
				 return false;
			 }
		 }
		 
		 if(!isNaN(f.uniqueCode.value.charAt(5)) || f.uniqueCode.value.charAt(5).toUpperCase()<"A" || f.uniqueCode.value.charAt(5).toUpperCase()>"Z"){
			 Dialog.alert('Numéro Epargne incorrect  !!');
			 f.uniqueCode.focus();
			 return false;
		 }
	 }
	 if(v=="western union"){
			if(f.uniqueCode.value.length!=10 || isNaN(f.uniqueCode.value)){
				 Dialog.alert('Numéro du western union incorrect !!');
				 f.uniqueCode.focus();
				 return false;
			 }
			
		}
	 if(v=="Colis"){
			if(f.uniqueCode.value.length!=13){
				 Dialog.alert('Numéro de colis incorrect !!');
				 f.uniqueCode.focus();
				 return false;
			 }
			for(i=2;i<=12;i++){
				 if(isNaN(f.uniqueCode.value.charAt(i))){
					 Dialog.alert('Numéro de colis incorrect !!');
					 f.uniqueCode.focus();
					 return false;
				 }
			if(!f.uniqueCode.value.charAt(0).match( /[0-9]/g) && !f.uniqueCode.value.charAt(0).match( /[A-Z]/g) || !f.uniqueCode.value.charAt(1).match( /[A-Z]/g)){
				 Dialog.alert('Numéro de colis incorrect !!');
				 f.uniqueCode.focus();
				 return false;
				 
			}
			}
		}
	
	 
	
	
	

}