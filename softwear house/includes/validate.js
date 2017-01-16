function register(){
    if(document.document.getElementById("passw").value!=document.getElementById("passw2").value){
	alert("Your passwords do not match");	}
    else if(document.getElementById("fname").value.length<1){	
		alert("You must enter your First Name"); 
	document.getElementById("fname").focus();
	}
	
    else if(document.getElementById("lname").value.length<1){	
		alert("You must enter your Last Name"); 
	document.getElementById("lname").focus();
	}
	
    else if(checkpassw()){
			if(agree){
				showmessage("register.jsp"+getSubmitQuery(document.regform),"databar");
			}else{
			    alert("You must agree to our Terms and Policy");
			}
			
		}
}


function checkpassw(){
    var len=document.getElementById("passw").value.length; 
	if(len<5){
		document.getElementById("m_passw").innerHTML="&nbsp;&nbsp;Too Short (Min 5 chars)";
		document.getElementById("m_passw").style.color="red" ;
		document.regform.passw.focus();
		return false;
	}
    else if(len < 8){
		document.getElementById("m_passw").innerHTML="&nbsp;&nbsp;Weak";
		document.getElementById("m_passw").style.color="Yellow";
		return true ;
	}
    else{
		document.getElementById("m_passw").innerHTML="&nbsp;&nbsp;Strong" ;
		document.getElementById("m_passw").style.color="green";
		return true ;
	}
}

function checkRetype(){
 	var pass1 = document.getElementById("passw").value;
	var pass2 = document.getElementById("passw2").value;
	if(pass1 != pass2){
		document.getElementById("m_passw2").innerHTML="&nbsp;&nbsp;Passwords do not Match";
		document.getElementById("m_passw2").style.color="red" ;
		return false;
	}
	else{
		document.getElementById("m_passw2").innerHTML="&nbsp;&nbsp;Match OK";
		document.getElementById("m_passw2").style.color="green" ;
		return true;
	}
 }
