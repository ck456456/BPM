<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="js/JSCal2-1.9/css/jscal2.css" />
<link rel="stylesheet" type="text/css" href="js/JSCal2-1.9/css/border-radius.css" />
<link rel="stylesheet" type="text/css" href="js/JSCal2-1.9/css/steel/steel.css" />
<title>Insert title here</title>
</head>
<body>
	<a href="#" class="jqmClose"><em>Close</em></a>
	<hr>
	<div id="TKC_edit">
		<form name="ef0101" id="ef0101" action="#" >
		<input type="hidden" id="f08.id"/>
		<input type="hidden" id="f08.bpm08002"/>
		<table>
			<tr>
				<th colspan=4 align=center>流程群組設定(子)</th>
			</tr>
			<tr>
				<td align=right>人員</td> 	
				<td><input type="text" id="f08.bpm08002_n" readonly="readonly"/><input type="submit" id="B_002d" value=".."/></td>
				<td align=right>有效日期 :</td> 	
				<td><input type="text" id="f08.bpm08003"/><button id="B_003d">..</button></td>
			</tr>
			<tr>
				<td align=right>說明</td> 	
				<td colspan=3><input type="text" id="f08.bpm08004" style="width:95%"/></td>
			</tr>
		</table>
		<hr>
		<div id="KC_ret">
			<input type="submit" name="B01" value="確定" width="20"/>
			<input type="submit" name="B02" value="取消" width="20"/>
		</div>
		</form>
	</div>
	<div class="jqmWindow" id="bx_f07_v0101f02"></div>
	<script type="text/javascript" src="js/JSCal2-1.9/js/jscal2.js"></script>
	<script type="text/javascript" src="js/JSCal2-1.9/js/lang/b5.js"></script>
	<script type="text/javascript">
	Calendar.setup({
		inputField : "f08.bpm08003",
		trigger    : "B_003d",
		onSelect   : function() { this.hide(); },
		showTime   : 12,
		dateFormat : "%Y/%m/%d"
	});
	
	$('#B_002d').click(function(e) {
		e.preventDefault();
		$('#bx_f07_v0101f02').jqmShow();
	});
	$('#bx_f07_v0101f02').jqm({
		ajax : 'bpm/jqm/bx_f02.jsp',
		onHide:bx_f02Close	
	});
	function bx_f02Close(hash){
		hash.w.hide();
		hash.o.hide();
		if ("確定" != $('input[id="bx_f02.status"]').val()) return;
		kc_reptxt('ef0101','f08.bpm08002'   ,$('input[id="bx_f02.id"]').val());
		kc_reptxt('ef0101','f08.bpm08002_n' ,$('input[id="bx_f02.bpm02001"]').val());
	}
	
	$().ready(function(){
		kc_reptxt('ef0101','f08.id'          ,'');
		kc_reptxt('ef0101','f08.bpm08002'   ,'');
		kc_reptxt('ef0101','f08.bpm08002_n' ,'');
		kc_reptxt('ef0101','f08.bpm08003'   ,'');
		kc_reptxt('ef0101','f08.bpm08004'   ,'');
		if (L03Dat['jqcmd'] == '修改'){ 
			for (i=0; i<L03Dat["rows"].length; i=i+1)
			if (L03Dat['jqid'] == L03Dat["rows"][i]["id"]){
				var dat = L03Dat["rows"][i]["cell"];
				kc_reptxt('ef0101','f08.id'        ,dat['id']);
				kc_reptxt('ef0101','f08.bpm08002'  ,dat['bpm08002']);
				kc_reptxt('ef0101','f08.bpm08002_n',dat['bpm08002_n']);
				kc_reptxt('ef0101','f08.bpm08003'  ,dat['bpm08003']);
				kc_reptxt('ef0101','f08.bpm08004'  ,dat['bpm08004']);
			}
		}
	});

	$('#KC_ret input').click(function(e) {
		$('#f07_v0101').jqmHide(); 
		e.preventDefault();
		if (this.name == 'B02') return;
		if (L03Dat['jqcmd'] == '修改'){
			for (i=0; i<L03Dat["rows"].length; i=i+1){
				L03Dat["rows"][i]['id'] = i+1;	
				if (L03Dat['jqid'] == L03Dat["rows"][i]["id"]){
					L03Dat["rows"][i]["cell"]['id'] 	    = document.forms['ef0101'].elements['f08.id'].value;
					L03Dat["rows"][i]["cell"]['bpm08002']   = document.forms['ef0101'].elements['f08.bpm08002'].value;
					L03Dat["rows"][i]["cell"]['bpm08002_n'] = document.forms['ef0101'].elements['f08.bpm08002_n'].value;
					L03Dat["rows"][i]["cell"]['bpm08003']   = document.forms['ef0101'].elements['f08.bpm08003'].value;
					L03Dat["rows"][i]["cell"]['bpm08004']   = document.forms['ef0101'].elements['f08.bpm08004'].value;
					L03Dat['jqid'] = L03Dat["rows"][i]['id'];
				}
			}
		}
		if (L03Dat['jqcmd'] == '新增'){
			var dat = {"id":"","cell":{"id":"","bpm08002":"","bpm08003":"","bpm08004":""}};
			dat["cell"]['id']   	   = -1;
			dat["cell"]['bpm08002']   = document.forms['ef0101'].elements['f08.bpm08002'].value;
			dat["cell"]['bpm08002_n'] = document.forms['ef0101'].elements['f08.bpm08002_n'].value;
			dat["cell"]['bpm08003']   = document.forms['ef0101'].elements['f08.bpm08003'].value;
			dat["cell"]['bpm08004']   = document.forms['ef0101'].elements['f08.bpm08004'].value;
			dat['id'] = L03Dat["rows"].length+1;
			L03Dat["rows"].push(dat);
			L03Dat['jqid'] = L03Dat["rows"].length;
			L03Dat['total'] = L03Dat["rows"].length;
		}
		$('#L03').flexAddData(L03Dat);
    });
	
	</script>

</body>
</html>