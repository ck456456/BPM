
function kc_GetXmlHttpObject() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	}
	if (window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
	return null;
}

function kc_Page(S01, S02) {

	kc_xmlhttp = kc_GetXmlHttpObject();
	if (kc_xmlhttp == null) {
		alert("Your browser does not support Ajax HTTP");
		return;
	}

	kc_xmlhttp.onreadystatechange = function(){
		if (kc_xmlhttp.readyState == 4) {
			document.getElementById(S01).innerHTML = kc_xmlhttp.responseText;
		}
	};
	kc_xmlhttp.open("GET", S02, true);
	kc_xmlhttp.send(null);
}

function kc_Update(S01, S02, arr) {
	kc_xmlhttp = kc_GetXmlHttpObject();
	url = S02;
	for(var key in arr){
		url = url+key+"="+arr[key]+"&";
    }
	
	if (kc_xmlhttp == null) {
		alert("Your browser does not support Ajax HTTP");
		return;
	}
	kc_xmlhttp.onreadystatechange = function(){
		if (kc_xmlhttp.readyState == 4) {
			document.getElementById(S01).innerHTML = kc_xmlhttp.responseText;
		}
	};
	kc_xmlhttp.open("GET", url, true);
	kc_xmlhttp.send(null);
}
