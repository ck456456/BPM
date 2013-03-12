<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
		<input type="hidden" id="f13.id"/>
		<input type="hidden" id="f13.f02_id2"/>
		<input type="hidden" id="f13.f03_id"/>
		<table>
			<tr>
				<th colspan=4 align=center>表單代理人設定</th>
			</tr>
			<tr>
			
				<td align=right>代理人 :</td> 	
				<td><input type="text" id="f13.f13_02" readonly="readonly"/><input type="submit" id="B_f02d" value=".."/></td>
				<td align=right>表單 :</td> 	
				<td><input type="text" id="f13.f13_03" readonly="readonly"/><input type="submit" id="B_f03d" value=".."/></td>
			</tr>
			<tr>
				<td align=right>有效日期 :</td> 	
				<td><input type="text" id="f13.bpm13001"/><button id="B_001d">..</button></td>
				<td align=right>截止日期 :</td> 	
				<td><input type="text" id="f13.bpm13002"/><button id="B_002d">..</button></td>
			</tr>
			<tr>
				<td align=right>說明</td> 	
				<td colspan=3><input type="text" id="f13.bpm13003" style="width:95%"/></td>
			</tr>
		</table>
		<hr>
		<div id="KC_ret">
			<input type="submit" name="B01" value="確定" width="20"/>
			<input type="submit" name="B02" value="取消" width="20"/>
		</div>
		</form>
	</div>
	<div class="jqmWindow" id="f02_v0201f02"></div>
	<div class="jqmWindow" id="f02_v0201f03"></div>
	<script type="text/javascript" src="js/JSCal2-1.9/js/jscal2.js"></script>
	<script type="text/javascript" src="js/JSCal2-1.9/js/lang/b5.js"></script>
	<script type="text/javascript">
		Calendar.setup({
			inputField : "f13.bpm13001",
			trigger    : "B_001d",
			onSelect   : function() { this.hide(); },
			showTime   : 12,
			dateFormat : "%Y/%m/%d"
		});
		Calendar.setup({
			inputField : "f13.bpm13002",
			trigger    : "B_002d",
			onSelect   : function() { this.hide(); },
			showTime   : 12,
			dateFormat : "%Y/%m/%d"
		});
		$('#B_f02d').click(function(e) {
			e.preventDefault();
			$('#f02_v0201f02').jqmShow();
		});
		$('#f02_v0201f02').jqm({
			ajax : 'bpm/jqm/bx_f02.jsp',
			onHide:bx_f02Close	
		});
		function bx_f02Close(hash){
			hash.w.hide();
			hash.o.hide();
			if ("確定" != $('input[id="bx_f02.status"]').val()) return;
			kc_reptxt('ef0101','f13.f02_id2',$('input[id="bx_f02.id"]').val());
			kc_reptxt('ef0101','f13.f13_02' ,$('input[id="bx_f02.bpm02001"]').val());
		}
		$('#B_f03d').click(function(e) {
			e.preventDefault();
			$('#f02_v0201f03').jqmShow();
		});
		$('#f02_v0201f03').jqm({
			ajax : 'bpm/jqm/bx_f03.jsp',
			onHide:bx_f03Close	
		});
		function bx_f03Close(hash){
			hash.w.hide();
			hash.o.hide();
			if ("確定" != $('input[id="bx_f03.status"]').val()) return;
			kc_reptxt('ef0101','f13.f03_id' ,$('input[id="bx_f03.id"]').val());
			kc_reptxt('ef0101','f13.f13_03' ,$('input[id="bx_f03.bpm03001"]').val());
		}
		$().ready(function(){
			kc_reptxt('ef0101','f13.id'         ,'');
			kc_reptxt('ef0101','f13.f02_id2'	,'');
			kc_reptxt('ef0101','f13.f03_id' 	,'');
			kc_reptxt('ef0101','f13.f13_02' 	,'');
			kc_reptxt('ef0101','f13.f13_03' 	,'');
			kc_reptxt('ef0101','f13.bpm13001'   ,'');
			kc_reptxt('ef0101','f13.bpm13002'   ,'');
			kc_reptxt('ef0101','f13.bpm13003'   ,'');
			if (L03Dat['jqcmd'] == '修改'){
				var i=0; 
				for (i=0; i<L03Dat["rows"].length; i=i+1)
				if (L03Dat['jqid'] == L03Dat["rows"][i]["id"]){
					var dat = L03Dat["rows"][i]["cell"];
					kc_reptxt('ef0101','f13.id'         ,dat['id']);
					kc_reptxt('ef0101','f13.f02_id2'	,dat['f02_id2']);
					kc_reptxt('ef0101','f13.f03_id' 	,dat['f03_id']);
					kc_reptxt('ef0101','f13.f13_02' 	,dat['f13_02']);
					kc_reptxt('ef0101','f13.f13_03' 	,dat['f13_03']);
					kc_reptxt('ef0101','f13.bpm13001'   ,dat['bpm13001']);
					kc_reptxt('ef0101','f13.bpm13002'   ,dat['bpm13002']);
					kc_reptxt('ef0101','f13.bpm13003'   ,dat['bpm13003']);
				}
			}
		});
		
		$('#KC_ret input').click(function(e) {
			$('#f02_v0201').jqmHide(); 
			e.preventDefault();
			if (this.name == 'B02') return;
			if (L03Dat['jqcmd'] == '修改'){
				var i=0;
				for (i=0; i<L03Dat["rows"].length; i=i+1){
					if (L03Dat['jqid'] == L03Dat["rows"][i]["id"]){
						L03Dat["rows"][i]["cell"]['id'] 	    = document.forms['ef0101'].elements['f13.id'].value;
						L03Dat["rows"][i]["cell"]['f02_id2']   	= document.forms['ef0101'].elements['f13.f02_id2'].value;
						L03Dat["rows"][i]["cell"]['f03_id'] 	= document.forms['ef0101'].elements['f13.f03_id'].value;
						L03Dat["rows"][i]["cell"]['f13_02']   	= document.forms['ef0101'].elements['f13.f13_02'].value;
						L03Dat["rows"][i]["cell"]['f13_03']   	= document.forms['ef0101'].elements['f13.f13_03'].value;
						L03Dat["rows"][i]["cell"]['bpm13001']   = document.forms['ef0101'].elements['f13.bpm13001'].value;
						L03Dat["rows"][i]["cell"]['bpm13002']   = document.forms['ef0101'].elements['f13.bpm13002'].value;
						L03Dat["rows"][i]["cell"]['bpm13003']   = document.forms['ef0101'].elements['f13.bpm13003'].value;
						L03Dat['jqid'] = L03Dat["rows"][i]['id'];
					}
				}
			}
			if (L03Dat['jqcmd'] == '新增'){
				var dat = {"id":"","cell":{"id":"","f02_id2":"","f03_id":"","f13_02":"","f13_02":"","bpm13001":"","bpm13002":"","bpm13003":""}};
				dat["cell"]['id']		= -1;
				dat["cell"]['f02_id2']	= document.forms['ef0101'].elements['f13.f02_id2'].value;
				dat["cell"]['f03_id']	= document.forms['ef0101'].elements['f13.f03_id'].value;
				dat["cell"]['f13_02']	= document.forms['ef0101'].elements['f13.f13_02'].value;
				dat["cell"]['f13_03']	= document.forms['ef0101'].elements['f13.f13_03'].value;
				dat["cell"]['bpm13001']	= document.forms['ef0101'].elements['f13.bpm13001'].value;
				dat["cell"]['bpm13002']	= document.forms['ef0101'].elements['f13.bpm13002'].value;
				dat["cell"]['bpm13003']	= document.forms['ef0101'].elements['f13.bpm13003'].value;
				alert(L03Dat['jqcmd']);
				dat['id'] = L03Dat["rows"].length+1;
				alert(L03Dat["rows"].length);
				L03Dat["rows"].push(dat);
				L03Dat['jqid'] = L03Dat["rows"].length;
				L03Dat['total'] = L03Dat["rows"].length;
				alert(L03Dat["rows"].length);
			}
			$('#L03').flexAddData(L03Dat);
	    });
	</script>
</body>
</html>