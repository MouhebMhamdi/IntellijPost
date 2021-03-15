package net.javaguides.springboot.springsecurity.web.dto;

public class EmailMessage {
private String Email;
public EmailMessage(String lien,String email,String nom ,String prenom) {
	this.Email="<html lang=\"fr\" xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
			" \r\n" + 
			"<head>\r\n" + 
			"  <meta charset=\"utf-8\" />\r\n" + 
			"\r\n" + 
			"  <style type=\"text/css\">\r\n" + 
			"  body {margin: 0; padding: 0; min-width: 100%!important;}\r\n" + 
			"  img {height: auto;}\r\n" + 
			"  .content {width: 100%; max-width: 600px;}\r\n" + 
			"  .header {padding: 40px 30px 20px 30px;}\r\n" + 
			"  .innerpadding {padding: 30px 30px 30px 30px;}\r\n" + 
			"  .borderbottom {border-bottom: 1px solid #f2eeed;}\r\n" + 
			"  .subhead {font-size: 15px; color: #ffffff; font-family: sans-serif; letter-spacing: 10px;}\r\n" + 
			"  .h1, .h2, .bodycopy {color: #153643; font-family: sans-serif;}\r\n" + 
			"  .h1 {font-size: 33px; line-height: 38px; font-weight: bold;}\r\n" + 
			"  .h2 {padding: 0 0 15px 0; font-size: 24px; line-height: 28px; font-weight: bold;}\r\n" + 
			"  .bodycopy {font-size: 16px; line-height: 22px;}\r\n" + 
			"  .button {text-align: center; font-size: 18px; font-family: sans-serif; font-weight: bold; padding: 0 30px 0 30px;background-color:rgba(99,10,74,0.5);}\r\n" + 
			"  .button a {color: #ffffff; text-decoration: none;}\r\n" + 
			"  .buttonwrapper:hover{\r\n" + 
			"    color: black;\r\n" + 
			"  }\r\n" + 
			"  .footer {padding: 20px 30px 15px 30px;}\r\n" + 
			"  .footercopy {font-family: sans-serif; font-size: 14px; color: #ffffff;}\r\n" + 
			"  .footercopy a {color: #ffffff; text-decoration: underline;}\r\n" + 
			"\r\n" + 
			"  @media only screen and (max-width: 550px), screen and (max-device-width: 550px) {\r\n" + 
			"  body[yahoo] .hide {display: none!important;}\r\n" + 
			"  body[yahoo] .buttonwrapper {background-color: transparent!important;}\r\n" + 
			"  body[yahoo] .button {padding: 0px!important;}\r\n" + 
			"  body[yahoo] .button a {background-color:rgba(99,10,74,0.5); padding: 15px 15px 13px!important;}\r\n" + 
			"  body[yahoo] .unsubscribe {display: block; margin-top: 20px; padding: 10px 50px; background: #2f3942; border-radius: 5px; text-decoration: none!important; font-weight: bold;}\r\n" + 
			"  }\r\n" + 
			"\r\n" + 
			"  /*@media only screen and (min-device-width: 601px) {\r\n" + 
			"    .content {width: 600px !important;}\r\n" + 
			"    .col425 {width: 425px!important;}\r\n" + 
			"    .col380 {width: 380px!important;}\r\n" + 
			"    }*/\r\n" + 
			"\r\n" + 
			"  </style>\r\n" + 
			"</head>\r\n" + 
			"\r\n" + 
			"<body yahoo bgcolor=\"#f6f8f1\">\r\n" + 
			"<table width=\"100%\" bgcolor=\"#630a4a\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
			"<tr>\r\n" + 
			"  <td>\r\n" + 
			"    <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"      <table width=\"600\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
			"        <tr>\r\n" + 
			"          <td>\r\n" + 
			"    <![endif]-->     \r\n" + 
			"    <table bgcolor=\"#ffffff\" class=\"content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
			"      <tr>\r\n" + 
			"        <td style=\"background-color:rgba(99,10,74,0.5); \"  class=\"header\">\r\n" + 
			"          <table width=\"70\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">  \r\n" + 
			"            <tr>\r\n" + 
			"              <td height=\"70\" style=\"padding: 0 20px 20px 0;\">\r\n" + 
			"                <img class=\"fix\" src=\"https://iphone-image.apkpure.com/v2/app/a/9/c/a9c8996691aae9442aadd01416a28cec.jpg\" width=\"70\" height=\"70\" border=\"0\" alt=\"\" />\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"          </table>\r\n" + 
			"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"            <table width=\"425\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
			"              <tr>\r\n" + 
			"                <td>\r\n" + 
			"          <![endif]-->\r\n" + 
			"          <table class=\"col425\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%; max-width: 425px;\">  \r\n" + 
			"            <tr>\r\n" + 
			"              <td height=\"70\">\r\n" + 
			"                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			"                  <tr>\r\n" + 
			"                    <td class=\"subhead\" style=\"padding: 0 0 0 3px;\">\r\n" + 
			"                      Poste Tn\r\n" + 
			"                    </td>\r\n" + 
			"                  </tr>\r\n" + 
			"                  <tr>\r\n" + 
			"                    <td class=\"h1\" style=\"padding: 5px 0 0 0;\">\r\n" + 
			"                      Mot de passe oublié !!\r\n" + 
			"                    </td>\r\n" + 
			"                  </tr>\r\n" + 
			"                </table>\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"          </table>\r\n" + 
			"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"                </td>\r\n" + 
			"              </tr>\r\n" + 
			"          </table>\r\n" + 
			"          <![endif]-->\r\n" + 
			"        </td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr>\r\n" + 
			"        <td class=\"innerpadding borderbottom\">\r\n" + 
			"          <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			"            <tr>\r\n" + 
			"              <td class=\"h2\">\r\n" + 
			"                Bonjour "+prenom+" "+nom+",\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"            <tr>\r\n" + 
			"              <td class=\"bodycopy\">\r\n" + 
			"                Nous avons reçu votre demande de récupération de mot de passe de votre compte de travail ,si ce n'est pas vous qu'il l'avez demandé, veuillez contactez le directeur via :\r\n" +email+ 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"          </table>\r\n" + 
			"        </td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr>\r\n" + 
			"        <td class=\"innerpadding borderbottom\">\r\n" + 
			"          <table width=\"115\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">  \r\n" + 
			"            <tr>\r\n" + 
			"              <td height=\"115\" style=\"padding: 0 20px 20px 0;\">\r\n" + 
			"                <img class=\"fix\" src=\"https://www.icone-png.com/png/30/29845.png\" width=\"115\" height=\"115\" border=\"0\" alt=\"\" />\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"          </table>\r\n" + 
			"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"            <table width=\"380\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
			"              <tr>\r\n" + 
			"                <td>\r\n" + 
			"          <![endif]-->\r\n" + 
			"          <table class=\"col380\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%; max-width: 380px;\">  \r\n" + 
			"            <tr>\r\n" + 
			"              <td>\r\n" + 
			"                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			"                  <tr>\r\n" + 
			"                    <td class=\"bodycopy\">\r\n" + 
			"                     Pour amorcer le processus de réinitialisation du mot de passe, veuillez cliquer sur le bouton réinitialisation  ci-dessous.\r\n" + 
			"                    </td>\r\n" + 
			"                  </tr>\r\n" + 
			"                  <tr>\r\n" + 
			"                    <td style=\"padding: 20px 0 0 0;\">\r\n" + 
			"                      <table class=\"buttonwrapper\" style=\"background-color:rgba(99,10,74,0.5); \" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			"                        <tr>\r\n" + 
			"                          <td class=\"button\" height=\"45\">\r\n" + 
			"                            <a href=\""+lien+"\">Réinitialisation</a>\r\n" + 
			"                          </td>\r\n" + 
			"                        </tr>\r\n" + 
			"                        \r\n" + 
			"\r\n" + 
			"                      </table>\r\n" + 
			"                    </td>\r\n" + 
			"                  </tr>\r\n" + 
			"                  <tr>\r\n" + 
			"                          <td style=\"padding: 50px 0 0 0;\"></td>\r\n" + 
			"                        </tr>\r\n" + 
			"                </table>\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"          </table>\r\n" + 
			"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"                </td>\r\n" + 
			"              </tr>\r\n" + 
			"          </table>\r\n" + 
			"          <![endif]-->\r\n" + 
			"        </td>\r\n" + 
			"      </tr>\r\n" + 
			"      \r\n" + 
			"      <tr>\r\n" + 
			"        <td class=\"footer\" style=\"background-color:rgba(99,10,74,0.5); \">\r\n" + 
			"          <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			"            <tr>\r\n" + 
			"              <td align=\"center\" class=\"footercopy\">\r\n" + 
			"                &reg; POSTE TN 2020<br/>\r\n" + 
			"                <a href=\"#\" class=\"unsubscribe\">\r\n" + 
			"                <span class=\"hide\"></span>\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"            <tr>\r\n" + 
			"              <td align=\"center\" style=\"padding: 10px 0 0 0;\">\r\n" + 
			"                <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			"                  <tr>\r\n" + 
			"                    <td width=\"37\" style=\"text-align: center; padding: 0 10px 0 5px;\">\r\n" + 
			"                      <a href=\"http://www.facebook.com/mouhebmhh\">\r\n" + 
			"                        <img src=\"https://www.usbourgbarrefoot.fr/images/icons/fbgris.png\" width=\"37\" height=\"37\" alt=\"Facebook\" border=\"0\" />\r\n" + 
			"                      </a><br><br>\r\n" + 
			"                    </td>\r\n" + 
			"                   \r\n" + 
			"                  </tr>\r\n" + 
			"               \r\n" + 
			"                </table>\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"          </table>\r\n" + 
			"        </td>\r\n" + 
			"      </tr>\r\n" + 
			"    </table>\r\n" + 
			"    <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"          </td>\r\n" + 
			"        </tr>\r\n" + 
			"    </table>\r\n" + 
			"    <![endif]-->\r\n" + 
			"    </td>\r\n" + 
			"  </tr>\r\n" + 
			"</table>\r\n" + 
			"</body>\r\n" + 
			"</html>";
}
public EmailMessage() {
	// TODO Auto-generated constructor stub
}
public String getEmail() {
	return Email;
}
public String passwordChanged(String nom,String prenom,String email,String date,String osName,String ip,String pcName){
	this.Email="<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
	" \r\n" + 
	"<head>\r\n" + 
	"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
	"\r\n" + 
	"  <style type=\"text/css\">\r\n" + 
	"  body {margin: 0; padding: 0; min-width: 100%!important;}\r\n" + 
	"  img {height: auto;}\r\n" + 
	"  .content {width: 100%; max-width: 600px;}\r\n" + 
	"  .header {padding: 40px 30px 20px 30px;}\r\n" + 
	"  .innerpadding {padding: 30px 30px 30px 30px;}\r\n" + 
	"  .borderbottom {border-bottom: 1px solid #f2eeed;}\r\n" + 
	"  .subhead {font-size: 15px; color: #ffffff; font-family: sans-serif; letter-spacing: 10px;}\r\n" + 
	"  .h1, .h2, .bodycopy {color: #153643; font-family: sans-serif;}\r\n" + 
	"  .h1 {font-size: 33px; line-height: 38px; font-weight: bold;}\r\n" + 
	"  .h2 {padding: 0 0 15px 0; font-size: 24px; line-height: 28px; font-weight: bold;}\r\n" + 
	"  .bodycopy {font-size: 16px; line-height: 22px;}\r\n" + 
	"  .button {text-align: center; font-size: 18px; font-family: sans-serif; font-weight: bold; padding: 0 30px 0 30px;background-color:rgba(99,10,74,0.5);}\r\n" + 
	"  .button a {color: #ffffff; text-decoration: none;}\r\n" + 
	"  .buttonwrapper:hover{\r\n" + 
	"    color: black;\r\n" + 
	"  }\r\n" + 
	"  .footer {padding: 20px 30px 15px 30px;}\r\n" + 
	"  .footercopy {font-family: sans-serif; font-size: 14px; color: #ffffff;}\r\n" + 
	"  .footercopy a {color: #ffffff; text-decoration: underline;}\r\n" + 
	"\r\n" + 
	"  @media only screen and (max-width: 550px), screen and (max-device-width: 550px) {\r\n" + 
	"  body[yahoo] .hide {display: none!important;}\r\n" + 
	"  body[yahoo] .buttonwrapper {background-color: transparent!important;}\r\n" + 
	"  body[yahoo] .button {padding: 0px!important;}\r\n" + 
	"  body[yahoo] .button a {background-color:rgba(99,10,74,0.5); padding: 15px 15px 13px!important;}\r\n" + 
	"  body[yahoo] .unsubscribe {display: block; margin-top: 20px; padding: 10px 50px; background: #2f3942; border-radius: 5px; text-decoration: none!important; font-weight: bold;}\r\n" + 
	"  }\r\n" + 
	"\r\n" + 
	"  /*@media only screen and (min-device-width: 601px) {\r\n" + 
	"    .content {width: 600px !important;}\r\n" + 
	"    .col425 {width: 425px!important;}\r\n" + 
	"    .col380 {width: 380px!important;}\r\n" + 
	"    }*/\r\n" + 
	"\r\n" + 
	"  </style>\r\n" + 
	"</head>\r\n" + 
	"\r\n" + 
	"<body yahoo bgcolor=\"#f6f8f1\">\r\n" + 
	"<table width=\"100%\" bgcolor=\"#630a4a\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
	"<tr>\r\n" + 
	"  <td>\r\n" + 
	"    <!--[if (gte mso 9)|(IE)]>\r\n" + 
	"      <table width=\"600\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
	"        <tr>\r\n" + 
	"          <td>\r\n" + 
	"    <![endif]-->     \r\n" + 
	"    <table bgcolor=\"#ffffff\" class=\"content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"height: 700px;\">\r\n" + 
	"      <tr>\r\n" + 
	"        <td style=\"background-color:rgba(99,10,74,0.5); \"  class=\"header\">\r\n" + 
	"          <table width=\"70\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">  \r\n" + 
	"            <tr>\r\n" + 
	"              <td height=\"70\" style=\"padding: 0 20px 20px 0;\">\r\n" + 
	"                <img class=\"fix\" src=\"https://lh3.googleusercontent.com/proxy/-F6LmrdsKFdQSdZ0c_ul5XvrsXnJUtYYRElO6MlalFbKL_Z9AbD_PiSvpzJ1eJpZJR-MV20vA7nR7LjYgN1UpsFZSwUjT_IYwkqzVKaRn2uPVEf2PsIHT5g\" width=\"70\" height=\"70\" border=\"0\" alt=\"\" />\r\n" + 
	"              </td>\r\n" + 
	"            </tr>\r\n" + 
	"          </table>\r\n" + 
	"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
	"            <table width=\"425\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
	"              <tr>\r\n" + 
	"                <td>\r\n" + 
	"          <![endif]-->\r\n" + 
	"          <table class=\"col425\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;max-width: 425px;\">  \r\n" + 
	"            <tr>\r\n" + 
	"              <td height=\"70\">\r\n" + 
	"                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
	"                  <tr>\r\n" + 
	"                    <td class=\"subhead\" style=\"padding: 0 0 0 3px;\">\r\n" + 
	"                      Poste Tn\r\n" + 
	"                    </td>\r\n" + 
	"                  </tr>\r\n" + 
	"                  <tr>\r\n" + 
	"                    <td class=\"h1\" style=\"padding: 5px 0 0 0;\">\r\n" + 
	"                      Mot de passe changé !\r\n" + 
	"                    </td>\r\n" + 
	"                  </tr>\r\n" + 
	"                </table>\r\n" + 
	"              </td>\r\n" + 
	"            </tr>\r\n" + 
	"          </table>\r\n" + 
	"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
	"                </td>\r\n" + 
	"              </tr>\r\n" + 
	"          </table>\r\n" + 
	"          <![endif]-->\r\n" + 
	"        </td>\r\n" + 
	"      </tr>\r\n" + 
	"      <tr>\r\n" + 
	"        <td class=\"innerpadding borderbottom\">\r\n" + 
	"          <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
	"            <tr>\r\n" + 
	"              <td class=\"h2\">\r\n" + 
	"                Bonjour "+nom+" "+prenom+",\r\n" + 
	"              </td>\r\n" + 
	"            </tr>\r\n" + 
	"            <tr>\r\n" + 
	"              <td class=\"bodycopy\">\r\n" + 
	"     <center>\r\n" + 
	"     	<table>\r\n" + 
	"     		<tr>\r\n" + 
	"     			<td><img class=\"fix\" src=\"https://cdn3.iconfinder.com/data/icons/flat-actions-icons-9/792/Tick_Mark_Dark-512.png\" width=\"25\" height=\"25\" border=\"0\" alt=\"\" /></td>\r\n" + 
	"     			<td align=\"center\"> <font color=\"green\" size=\"3px\"> Votre mot de passe a été réinitialisé , le "+date+"</font></td>\r\n" + 
	"     		</tr>\r\n" + 
	"     	</table><br>\r\n" + 
	"     	 <table align=\"center\">\r\n" + 
	"     			<tr>\r\n" + 
	"     				<td  align=\"center\">\r\n" + 
	"     					<b>Système d'exploitation :	</b> "+osName+"\r\n" + 
	"     				</td>\r\n" + 
	"     			</tr>\r\n" + 
	"     			<tr>\r\n" + 
	"     				<td  align=\"center\"> \r\n" + 
	"     					  <b>Adresse IP :</b> "+ip+"\r\n" + 
	"     				</td>\r\n" + 
	"\r\n" + 
	"     			</tr>\r\n" + 
	"     			<tr>\r\n" + 
	"     				<td align=\"center\">\r\n" + 
	"     					<b>Nom du machine :</b> "+pcName+"\r\n" + 
	"     				</td>\r\n" + 
	"     			</tr>\r\n" + 
	"     		</table><br>\r\n" + 
	"     		<table align=\"center\">\r\n" + 
	"     			<tr>\r\n" + 
	"     				<td  align=\"center\">\r\n" + 
	"     					<b>Si vous l'avez fait , </b> ne tenez pas compte de cet e-mail.\r\n" + 
	"     				</td>\r\n" + 
	"     			</tr>\r\n" + 
	"     			<tr>\r\n" + 
	"     				<td  align=\"center\"> \r\n" + 
	"     					  <b>Si vous ne l'avez pas fait , </b> veuillez contactez le directeur via : "+email+"\r\n" + 
	"     				</td>\r\n" + 
	"     			</tr>\r\n" + 
	"     		</table>\r\n" + 
	"             \r\n" + 
	"             \r\n" + 
	"              </td>\r\n" + 
	"            </tr>\r\n" + 
	"              </center> \r\n" + 
	"          </table>\r\n" + 
	"        </td>\r\n" + 
	"      </tr>\r\n" + 
	"     \r\n" + 
	"      \r\n" + 
	"      <tr>\r\n" + 
	"        <td class=\"footer\" style=\"background-color:rgba(99,10,74,0.5); \">\r\n" + 
	"          <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
	"            <tr>\r\n" + 
	"              <td align=\"center\" class=\"footercopy\">\r\n" + 
	"                &reg; POSTE TN 2020<br/>\r\n" + 
	"                <a href=\"#\" class=\"unsubscribe\">\r\n" + 
	"                <span class=\"hide\"></span>\r\n" + 
	"              </td>\r\n" + 
	"            </tr>\r\n" + 
	"            <tr>\r\n" + 
	"              <td align=\"center\" style=\"padding: 10px 0 0 0;\">\r\n" + 
	"                <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
	"                  <tr>\r\n" + 
	"                    <td width=\"37\" style=\"text-align: center; padding: 0 10px 0 5px;\">\r\n" + 
	"                      <a href=\"http://www.facebook.com/mouhebmhh\">\r\n" + 
	"                        <img src=\"https://www.usbourgbarrefoot.fr/images/icons/fbgris.png\" width=\"37\" height=\"37\" alt=\"Facebook\" border=\"0\" />\r\n" + 
	"                      </a><br><br>\r\n" + 
	"                    </td>\r\n" + 
	"                   \r\n" + 
	"                  </tr>\r\n" + 
	"               \r\n" + 
	"                </table>\r\n" + 
	"              </td>\r\n" + 
	"            </tr>\r\n" + 
	"          </table>\r\n" + 
	"        </td>\r\n" + 
	"      </tr>\r\n" + 
	"    </table>\r\n" + 
	"    <!--[if (gte mso 9)|(IE)]>\r\n" + 
	"          </td>\r\n" + 
	"        </tr>\r\n" + 
	"    </table>\r\n" + 
	"    <![endif]-->\r\n" + 
	"    </td>\r\n" + 
	"  </tr>\r\n" + 
	"</table>\r\n" + 
	"</body>\r\n" + 
	"</html>";
	return this.Email;
}
public String UserEmail(String nom,String prenom,String message) {
	this.Email="<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
			" \r\n" + 
			"<head>\r\n" + 
			"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
			"\r\n" + 
			"  <style type=\"text/css\">\r\n" + 
			"  body {margin: 0; padding: 0; min-width: 100%!important;}\r\n" + 
			"  img {height: auto;}\r\n" + 
			"  .content {width: 100%; max-width: 600px;}\r\n" + 
			"  .header {padding: 40px 30px 20px 30px;}\r\n" + 
			"  .innerpadding {padding: 30px 30px 30px 30px;}\r\n" + 
			"  .borderbottom {border-bottom: 1px solid #f2eeed;}\r\n" + 
			"  .subhead {font-size: 15px; color: #ffffff; font-family: sans-serif; letter-spacing: 10px;}\r\n" + 
			"  .h1, .h2, .bodycopy {color: #153643; font-family: sans-serif;}\r\n" + 
			"  .h1 {font-size: 33px; line-height: 38px; font-weight: bold;}\r\n" + 
			"  .h2 {padding: 0 0 15px 0; font-size: 24px; line-height: 28px; font-weight: bold;}\r\n" + 
			"  .bodycopy {font-size: 16px; line-height: 22px;}\r\n" + 
			"  .button {text-align: center; font-size: 18px; font-family: sans-serif; font-weight: bold; padding: 0 30px 0 30px;background-color:rgba(99,10,74,0.5);}\r\n" + 
			"  .button a {color: #ffffff; text-decoration: none;}\r\n" + 
			"  .buttonwrapper:hover{\r\n" + 
			"    color: black;\r\n" + 
			"  }\r\n" + 
			"  .footer {padding: 20px 30px 15px 30px;}\r\n" + 
			"  .footercopy {font-family: sans-serif; font-size: 14px; color: #ffffff;}\r\n" + 
			"  .footercopy a {color: #ffffff; text-decoration: underline;}\r\n" + 
			"\r\n" + 
			"  @media only screen and (max-width: 550px), screen and (max-device-width: 550px) {\r\n" + 
			"  body[yahoo] .hide {display: none!important;}\r\n" + 
			"  body[yahoo] .buttonwrapper {background-color: transparent!important;}\r\n" + 
			"  body[yahoo] .button {padding: 0px!important;}\r\n" + 
			"  body[yahoo] .button a {background-color:rgba(99,10,74,0.5); padding: 15px 15px 13px!important;}\r\n" + 
			"  body[yahoo] .unsubscribe {display: block; margin-top: 20px; padding: 10px 50px; background: #2f3942; border-radius: 5px; text-decoration: none!important; font-weight: bold;}\r\n" + 
			"  }\r\n" + 
			"\r\n" + 
			"  /*@media only screen and (min-device-width: 601px) {\r\n" + 
			"    .content {width: 600px !important;}\r\n" + 
			"    .col425 {width: 425px!important;}\r\n" + 
			"    .col380 {width: 380px!important;}\r\n" + 
			"    }*/\r\n" + 
			"\r\n" + 
			"  </style>\r\n" + 
			"</head>\r\n" + 
			"\r\n" + 
			"<body yahoo bgcolor=\"#f6f8f1\">\r\n" + 
			"<table width=\"100%\" bgcolor=\"#630a4a\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
			"<tr>\r\n" + 
			"  <td>\r\n" + 
			"    <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"      <table width=\"600\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
			"        <tr>\r\n" + 
			"          <td>\r\n" + 
			"    <![endif]-->     \r\n" + 
			"    <table bgcolor=\"#ffffff\" class=\"content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"height: 700px;\">\r\n" + 
			"      <tr>\r\n" + 
			"        <td style=\"background-color:rgba(99,10,74,0.5); \"  class=\"header\">\r\n" + 
			"          <table width=\"70\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">  \r\n" + 
			"            <tr>\r\n" + 
			"              <td height=\"70\" style=\"padding: 0 20px 20px 0;\">\r\n" + 
			"                <img class=\"fix\" src=\"https://iphone-image.apkpure.com/v2/app/a/9/c/a9c8996691aae9442aadd01416a28cec.jpg\" width=\"70\" height=\"70\" border=\"0\" alt=\"\" />\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"          </table>\r\n" + 
			"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"            <table width=\"425\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
			"              <tr>\r\n" + 
			"                <td>\r\n" + 
			"          <![endif]-->\r\n" + 
			"          <table class=\"col425\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;max-width: 425px;\">  \r\n" + 
			"            <tr>\r\n" + 
			"              <td height=\"70\">\r\n" + 
			"                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			"                  <tr>\r\n" + 
			"                    <td class=\"subhead\" style=\"padding: 0 0 0 3px;\">\r\n" + 
			"                      Poste Tn\r\n" + 
			"                    </td>\r\n" + 
			"                  </tr>\r\n" + 
			"                  <tr>\r\n" + 
			"                    <td class=\"h1\" style=\"padding: 5px 0 0 0;\">\r\n" + 
			"                      Nouveau message de la part d'administration !!\r\n" + 
			"                    </td>\r\n" + 
			"                  </tr>\r\n" + 
			"                </table>\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"          </table>\r\n" + 
			"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"                </td>\r\n" + 
			"              </tr>\r\n" + 
			"          </table>\r\n" + 
			"          <![endif]-->\r\n" + 
			"        </td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr>\r\n" + 
			"        <td class=\"innerpadding borderbottom\">\r\n" + 
			"          <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			"            <tr>\r\n" + 
			"              <td class=\"h2\">\r\n" + 
			"                Bonjour  "+prenom+" "+nom+",\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"            <tr>\r\n" + 
			"              <td class=\"bodycopy\">\r\n" + 
			"                "+message+"\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"          </table>\r\n" + 
			"        </td>\r\n" + 
			"      </tr>\r\n" + 
			"     \r\n" + 
			"      \r\n" + 
			"      <tr>\r\n" + 
			"        <td class=\"footer\" style=\"background-color:rgba(99,10,74,0.5); \">\r\n" + 
			"          <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			"            <tr>\r\n" + 
			"              <td align=\"center\" class=\"footercopy\">\r\n" + 
			"                &reg; POSTE TN 2020<br/>\r\n" + 
			"                <a href=\"#\" class=\"unsubscribe\">\r\n" + 
			"                <span class=\"hide\"></span>\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"            <tr>\r\n" + 
			"              <td align=\"center\" style=\"padding: 10px 0 0 0;\">\r\n" + 
			"                <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			"                  <tr>\r\n" + 
			"                    <td width=\"37\" style=\"text-align: center; padding: 0 10px 0 5px;\">\r\n" + 
			"                      <a href=\"http://www.facebook.com/mouhebmhh\">\r\n" + 
			"                        <img src=\"https://www.usbourgbarrefoot.fr/images/icons/fbgris.png\" width=\"37\" height=\"37\" alt=\"Facebook\" border=\"0\" />\r\n" + 
			"                      </a><br><br>\r\n" + 
			"                    </td>\r\n" + 
			"                   \r\n" + 
			"                  </tr>\r\n" + 
			"               \r\n" + 
			"                </table>\r\n" + 
			"              </td>\r\n" + 
			"            </tr>\r\n" + 
			"          </table>\r\n" + 
			"        </td>\r\n" + 
			"      </tr>\r\n" + 
			"    </table>\r\n" + 
			"    <!--[if (gte mso 9)|(IE)]>\r\n" + 
			"          </td>\r\n" + 
			"        </tr>\r\n" + 
			"    </table>\r\n" + 
			"    <![endif]-->\r\n" + 
			"    </td>\r\n" + 
			"  </tr>\r\n" + 
			"</table>\r\n" + 
			"</body>\r\n" + 
			"</html>";
	return this.Email;
}

}
