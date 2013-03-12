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
		<input type="hidden" id="f11.id"/>
		<input type="hidden" id="f11.bpm11002"/>
		<table>
			<tr>
				<th colspan=4 align=center>人員權限設定(子)</th>
			</tr>
			<tr>
				<td align=right>權限</td> 	
				<td><input type="text" id="f11.bpm11002_n" readonly="readonly"/><input type="submit" id="B_002d" value=".."/></td>
				<td align=right>有效日期 :</td> 	
				<td><input type="text" id="f11.bpm11003"/><button id="B_003d">..</button></td>
			</tr>
			<tr>
				<td align=right>說明</td> 	
				<td colspan=3><input type="text" id="f11.bpm11004" style="width:95%"/></td>
			</tr>
		</table>
		<hr>
		<div id="KC_ret">
			<input type="submit" name="B01" value="確定" width="20"/>
			<input type="submit" name="B02" value="取消" width="20"/>
		</div>
		</form>
	</div>
	<div class="jqmWindow" id="bx_f02_v0101f09"></div>
	<script type="text/javascript" src="js/JSCal2-1.9/js/jscal2.js"></script>
	<script type="text/javascript" src="js/JSCal2-1.9/js/lang/b5.js"></script>
	<script type="text/javascript">
	Calendar.setup({
		inputField : "f11.bpm11003",
		trigger    : "B_003d",
		onSelect   : function() { this.hide(); },
		showTime   : 12,
		dateFormat : "%Y/%m/%d"
	});
	
	$('#B_002d').click(function(e) {
		e.preventDefault();
		$('#bx_f02_v0101f09').jqmShow();
	});
	$('#bx_f02_v0101f09').jqm({
		ajax : 'bpm/jqm/bx_f09.jsp',
		onHide:bx_f09Close	
	});
	function bx_f09Close(hash){
		hash.w.hide();
		hash.o.hide();
		if ("確定" != $('input[id="bx_f09.status"]').val()) return;
		kc_reptxt('ef0101','f11.bpm11002'   ,$('input[id="bx_f09.id"]').val());
		kc_reptxt('ef0101','f11.bpm11002_n' ,$('input[id="bx_f09.bpm09001"]').val());
	}
	
	$().ready(function(){
		kc_reptxt('ef0101','f11.id'         ,'');
		kc_reptxt('ef0101','f11.bpm11002'   ,'');
		kc_reptxt('ef0101','f11.bpm11002_n' ,'');
		kc_reptxt('ef0101','f11.bpm11003'   ,'');
		kc_reptxt('ef0101','f11.bpm11004'   ,'');
		if (L03Dat['jqcmd'] == '修改'){
			var i=0; 
			for (i=0; i<L03Dat["rows"].length; i=i+1)
			if (L03Dat['jqid'] == L03Dat["rows"][i]["id"]){
				var dat = L03Dat["rows"][i]["cell"];
				kc_reptxt('ef0101','f11.id'        ,dat['id']);
				kc_reptxt('ef0101','f11.bpm11002'  ,dat['bpm11002']);
				kc_reptxt('ef0101','f11.bpm11002_n',dat['bpm11002_n']);
				kc_reptxt('ef0101','f11.bpm11003'  ,dat['bpm11003']);
				kc_reptxt('ef0101','f11.bpm11004'  ,dat['bpm11004']);
			}
		}
	});

	$('#KC_ret input').click(function(e) {
		$('#f02_v0101').jqmHide(); 
		e.preventDefault();
		if (this.name == 'B02') return;
		if (L03Dat['jqcmd'] == '修改'){
			var i=0;
			for (i=0; i<L03Dat["rows"].length; i=i+1){
				L03Dat["rows"][i]['id'] = i+1;	
				if (L03Dat['jqid'] == L03Dat["rows"][i]["id"]){
					L03Dat["rows"][i]["cell"]['id'] 	    = document.forms['ef0101'].elements['f11.id'].value;
					L03Dat["rows"][i]["cell"]['bpm11002']   = document.forms['ef0101'].elements['f11.bpm11002'].value;
					L03Dat["rows"][i]["cell"]['bpm11002_n'] = document.forms['ef0101'].elements['f11.bpm11002_n'].value;
					L03Dat["rows"][i]["cell"]['bpm11003']   = document.forms['ef0101'].elements['f11.bpm11003'].value;
					L03Dat["rows"][i]["cell"]['bpm11004']   = document.forms['ef0101'].elements['f11.bpm11004'].value;
					L03Dat['jqid'] = L03Dat["rows"][i]['id'];
				}
			}
		}
		if (L03Dat['jqcmd'] == '新增'){
			var dat = {"id":"","cell":{"id":"","bpm11002":"","bpm11003":"","bpm11004":""}};
			dat["cell"]['id']   	   = -1;
			dat["cell"]['bpm11002']   = document.forms['ef0101'].elements['f11.bpm11002'].value;
			dat["cell"]['bpm11002_n'] = document.forms['ef0101'].elements['f11.bpm11002_n'].value;
			dat["cell"]['bpm11003']   = document.forms['ef0101'].elements['f11.bpm11003'].value;
			dat["cell"]['bpm11004']   = document.forms['ef0101'].elements['f11.bpm11004'].value;
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