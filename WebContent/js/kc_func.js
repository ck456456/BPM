/*---------------------------------------------------------------------------------
 * function kc_ajax(S_url, S_dat, func)
 * function kc_ajaxform(FID, StrFunc, func)
 * function kc_submitform(Strform, StrFunc)
 * 
 * function kc_dateCheck(str)  
 * function kc_reptxt(Strform, Strelement ,StrRet)
 * function kc_reprad(name,dat)
 * function kc_repchk(name,dat)
 * function kc_repdte(name,dat)
 * 
 * function kc_formtojson(selector)
 * function KC_JsonDel(array, property, value)
 * 
 * function KC_NPos(SubS,S,I1)
 * function KC_GetSubStr(SubS,S,I1)
 * function KC_GetUserName()  
  ---------------------------------------------------------------------------------*/
function kc_ajax(S_url, S_dat, func) {
	var param = '';
	for(var key in S_dat)
		param = param+key+"="+S_dat[key]+"&";
	$.ajax({
		type:'POST',
		dataType: 'json',
		url: S_url, 
		data:param , 
		success:function(data,status){func(data);},
		error  :function(XMLHttpRequest, textStatus, errorThrown){
            jError( "<br><br><br>"+
            		S_url+"?"+
            		param+"<hr>"+
            		XMLHttpRequest.responseText);
			}
	});			
}
/*---------------------------------------------------------------------------------*/
function kc_ajaxform(FID, StrFunc, func) {
	function kc_tmp(FID, StrFunc, func) {
		var param = '';
    	for(var key in StrFunc)
    		param = param+"&"+key+"="+StrFunc[key];
		$.ajax({
			type:'GET',  // 'POST'
			dataType: 'json',
			url: $(FID).attr('action'), 
			data: $(FID).serialize()+param , 
			success:function(data,status){func(data);},
			error  :function(XMLHttpRequest, textStatus, errorThrown){
                jError( "<br><br><br>"+
                		$(FID).attr('action')+"?"+
                		$(FID).serialize()+ param+"<hr>"+
                		XMLHttpRequest.responseText);
				}
		});			
	};
	var S1 = ""; S2 = "";
	switch (StrFunc['action']) {
	case 'add': {S1 = "是否要新增 ?"; S2 = "新增"; break;}
	case 'mdy': {S1 = "是否要修改 ?"; S2 = "修改"; break;}
	case 'del': {S1 = "是否要刪除 ?"; S2 = "刪除"; break;}
	case 'chg': {S1 = "是否要變更 ?"; S2 = "變更"; break;}
	default: {kc_tmp(FID, StrFunc, func); break;}
	}
	if (S1 != "") jConfirm(S1,S2,function(r1){if (r1) kc_tmp(FID, StrFunc, func);});
}
/*---------------------------------------------------------------------------------*/
function kc_submitform(Strform, StrFunc) {
	function kc_f01(Strform, StrFunc) {
		form = document.forms[Strform];
    	for(var key in StrFunc){
    		form.elements[key].value = StrFunc[key];
        }
		form.submit();
	}
	switch (StrFunc['action']) {
		case 'add': {if (confirm("是否要新增 ?")) kc_f01(Strform, StrFunc); break;}
		case 'mdy': {if (confirm("是否要修改 ?")) kc_f01(Strform, StrFunc); break;}
		case 'del': {if (confirm("是否要刪除 ?")) kc_f01(Strform, StrFunc); break;}
		case 'chg': {if (confirm("是否要變更 ?")) kc_f01(Strform, StrFunc); break;}
		default:	{kc_f01(Strform, StrFunc); break;}
	}
}
/*---------------------------------------------------------------------------------*/
function kc_addDays(myDate,days) {
	return new Date(myDate.getTime() + days*24*60*60*1000);
}

function kc_dateCheck(str){
	var re = new RegExp("^([0-9]{4})[/]{1}([0-9]{1,2})[/]{1}([0-9]{1,2})$");     
	var strDataValue;     
	var infoValidation = true;
	if ((strDataValue = re.exec(str)) != null){         
		var i;         
		i = parseFloat(strDataValue[1]);         
		if (i <= 0 || i > 9999){ // 年             
			infoValidation = false;         
			}         
		i = parseFloat(strDataValue[2]);         
		if (i <= 0 || i > 12){ // 月             
			infoValidation = false;         
			}         
		i = parseFloat(strDataValue[3]);         
		if (i <= 0 || i > 31){ // 日             
			infoValidation = false;         
			}     
		}else
		{         
			infoValidation = false;     
			}       
	if (!infoValidation){         
		alert('請輸入 YYYY/MM/DD 日期格式');     
		}     
	return infoValidation; 
}
/*---------------------------------------------------------------------------------*/
function kc_loadselect(e,arr){
	for (var i = 0; i < arr.length; i++) 
		e.append('<option value="'+arr[i].GUID+'">'+arr[i].Name+'</option>');
	if (0!=arr.length)  
		e.val(arr[0].GUID);
}	

function kc_reptxt(Strform, Strelement ,StrRet) {
	form = document.forms[Strform];
	form.elements[Strelement].value = (typeof(StrRet) !== 'undefined' && StrRet != null)?StrRet:"";
}

function kc_reprad(name,dat){ 
    var temp = document.getElementsByName(name),i=0;
    for(i=0;i<temp.length;i++){
        if(temp[i].value == dat){ 
        	temp[i].checked = true;
        } 
    } 
}
function kc_repchk(name,dat) {
	$('input[name='+name+']').attr('checked', dat);
}
function kc_repdte(name,dat){
	var picker = dojo.widget.byId(name);
	picker.setValue(dat); 
}
/*---------------------------------------------------------------------------------*/
function kc_parseJsonDate(jsonDate) {
    return new Date(parseInt(jsonDate.replace(/(^.*\()|([+-].*$)/g, '')));
};

/*----------------- 將 form 轉為 json ----------------------------------------------------------------*/
/*
function kc_formToJSON( selector )
{
     var form = {};
     $(selector).find(':input[name]:enabled').each( function() {
         var self = $(this);
         var name = self.attr('name');
         if (form[name]) {
            form[name] = form[name] + ',' + self.val();
         }
         else {
            form[name] = self.val();
         }
     });

     return form;
}
*/	
function kc_formtojson(selector){
    var ret = {};
    $(selector).each( function() {
        var n = $(this).attr('name');
        var a = $(this).serializeArray();
        ret[n] = {};
        $.each(a, function() {
            if (ret[n][this.name] !== undefined) {
                if (!ret[n][this.name].push) {
               	  ret[n][this.name] = [ret[n][this.name]];
                }
                ret[n][this.name].push(this.value || '');
            } else {
           	 ret[n][this.name] = this.value || '';
            }
        });
    });
    	/*
		alert(JSON.stringify(ret));    
   		for(var k1 in c1){
     	    alert(k1+' : '+c1[k1]);
       	c2= c1[k1];
       	for(var k2 in c2){
       	  alert(k2+' => '+c2[k2]+' <br>');
       	}
       }
    	*/   
    return ret;			
}

function KC_JsonDel(array, property, value) {
	var cc = [],i=0;
	$.each(array, function(index, result) {
		if(result[property] == value) {           //Remove from array
			cc.push(""+index);            
			}        
		});
	for (i=cc.length;i>0;i--){
		array.splice(cc[i-1], 1);
	}  
}
/*---------------------------------------------------------------------------------*/
function KC_NPos(SubS,S,I1)
{
	var i=0;
	B = 0;
	for (i=1;i<=I1;i++)
		B = S.indexOf(SubS,B+1);
	return B;
}
function KC_GetSubStr(SubS,S,I1)
{
	S=S+SubS;
	I01=KC_NPos(SubS,S,I1-1);
	I02=KC_NPos(SubS,S,I1);
	if (I01!=0) I01++; 
	if (I02<0) return ""; 
	return S.substring(I01,I02);
}
function KC_GetUserName()
{
	var wshell = new ActiveXObject("WScript.Shell");
	return wshell.ExpandEnvironmentStrings("%USERNAME%");
}
/*---------------------------------------------------------------------------------*/