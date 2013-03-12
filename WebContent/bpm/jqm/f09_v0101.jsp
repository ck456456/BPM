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
		<input type="hidden" id="f10.id"/>
		<input type="hidden" id="f10.bpm10002"/>
		<table>
			<tr>
				<th colspan=4 align=center>權限群組設定(子)</th>
			</tr>
			<tr>
				<td align=right>選單</td> 	
				<td><input type="text" id="f10.bpm10002_n" readonly="readonly"/><input type="submit" id="B_002d" value=".."/></td>
				<td align=right>有效日期 :</td> 	
				<td><input type="text" id="f10.bpm10003"/><button id="B_003d">..</button></td>
			</tr>
			<tr>
				<td align=right>說明</td> 	
				<td colspan=3><input type="text" id="f10.bpm10004" style="width:95%"/></td>
			</tr>
		</table>
		<hr>
		<div id="KC_ret">
			<input type="submit" name="B01" value="確定" width="20"/>
			<input type="submit" name="B02" value="取消" width="20"/>
		</div>
		</form>
	</div>
	<div class="jqmWindow" id="bx_f09_v0101sf01"></div>
	<script type="text/javascript" src="js/JSCal2-1.9/js/jscal2.js"></script>
	<script type="text/javascript" src="js/JSCal2-1.9/js/lang/b5.js"></script>
	<script type="text/javascript">
	Calendar.setup({
		inputField : "f10.bpm10003",
		trigger    : "B_003d",
		onSelect   : function() { this.hide(); },
		showTime   : 12,
		dateFormat : "%Y/%m/%d"
	});
	
	$('#B_002d').click(function(e) {
		e.preventDefault();
		$('#bx_f09_v0101sf01').jqmShow();
	});
	$('#bx_f09_v0101sf01').jqm({
		ajax : 'bpm/jqm/bx_sf01.jsp',
		onHide:bx_sf01Close	
	});
	function bx_sf01Close(hash){
		hash.w.hide();
		hash.o.hide();
		if ("確定" != $('input[id="bx_sf01.status"]').val()) return;
		kc_reptxt('ef0101','f10.bpm10002'   ,$('input[id="bx_sf01.id"]').val());
		kc_reptxt('ef0101','f10.bpm10002_n' ,$('input[id="bx_sf01.sys01004"]').val());
	}
	
	$().ready(function(){
		kc_reptxt('ef0101','f10.id'          ,'');
		kc_reptxt('ef0101','f10.bpm10002'   ,'');
		kc_reptxt('ef0101','f10.bpm10002_n' ,'');
		kc_reptxt('ef0101','f10.bpm10003'   ,'');
		kc_reptxt('ef0101','f10.bpm10004'   ,'');
		if (L03Dat['jqcmd'] == '修改'){ 
			for (i=0; i<L03Dat["rows"].length; i=i+1)
			if (L03Dat['jqid'] == L03Dat["rows"][i]["id"]){
				var dat = L03Dat["rows"][i]["cell"];
				kc_reptxt('ef0101','f10.id'        ,dat['id']);
				kc_reptxt('ef0101','f10.bpm10002'  ,dat['bpm10002']);
				kc_reptxt('ef0101','f10.bpm10002_n',dat['bpm10002_n']);
				kc_reptxt('ef0101','f10.bpm10003'  ,dat['bpm10003']);
				kc_reptxt('ef0101','f10.bpm10004'  ,dat['bpm10004']);
			}
		}
	});

	$('#KC_ret input').click(function(e) {
		$('#f09_v0101').jqmHide(); 
		e.preventDefault();
		if (this.name == 'B02') return;
		if (L03Dat['jqcmd'] == '修改'){
			for (i=0; i<L03Dat["rows"].length; i=i+1){
				L03Dat["rows"][i]['id'] = i+1;	
				if (L03Dat['jqid'] == L03Dat["rows"][i]["id"]){
					L03Dat["rows"][i]["cell"]['id'] 	    = document.forms['ef0101'].elements['f10.id'].value;
					L03Dat["rows"][i]["cell"]['bpm10002']   = document.forms['ef0101'].elements['f10.bpm10002'].value;
					L03Dat["rows"][i]["cell"]['bpm10002_n'] = document.forms['ef0101'].elements['f10.bpm10002_n'].value;
					L03Dat["rows"][i]["cell"]['bpm10003']   = document.forms['ef0101'].elements['f10.bpm10003'].value;
					L03Dat["rows"][i]["cell"]['bpm10004']   = document.forms['ef0101'].elements['f10.bpm10004'].value;
					L03Dat['jqid'] = L03Dat["rows"][i]['id'];
				}
			}
		}
		if (L03Dat['jqcmd'] == '新增'){
			var dat = {"id":"","cell":{"id":"","bpm10002":"","bpm10003":"","bpm10004":""}};
			dat["cell"]['id']   	   = -1;
			dat["cell"]['bpm10002']   = document.forms['ef0101'].elements['f10.bpm10002'].value;
			dat["cell"]['bpm10002_n'] = document.forms['ef0101'].elements['f10.bpm10002_n'].value;
			dat["cell"]['bpm10003']   = document.forms['ef0101'].elements['f10.bpm10003'].value;
			dat["cell"]['bpm10004']   = document.forms['ef0101'].elements['f10.bpm10004'].value;
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