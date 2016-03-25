function setCookie() {
	cookie.set('account', document.getElementById("account").value, {expires : 30}); 
	cookie.set('password', document.getElementById("password").value, {expires : 30});
	cookie.set('checkbox',true,{expires : 30});
}

function showCookie() {
	document.getElementById("account").value=cookie.get("account");
	document.getElementById("password").value=cookie.get("password");
	document.getElementById("checkbox").checked=cookie.get("checkbox");
}

function checkCookie(){
	if (document.getElementById("checkbox").checked==false){
		cookie.empty();
		} else {
		setCookie();
		}
}