<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
#L01 { 
	width: 35%; 
	float: left; 
	background-color:#eee;
} 
#L02 { 
	width: 65%; 
	float: left; 
	background-color:#eee;
}
</style>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >    
	<meta http-equiv="Content-Type" content="text/html; charset=">
	<link rel="Stylesheet" type="text/css" href="js/jqModal/css/jqModal.css"/>
	<link rel="Stylesheet" type="text/css" href="css/TKC_styles.css"/>
	<link rel="Stylesheet" type="text/css" href="js/flexigrid/flexigrid.pack.css"/>
	<link rel="stylesheet" type="text/css" href="js/jquery.alerts-1.1/jquery.alerts.css" />
	<link rel="stylesheet" type="text/css" href="js/JSCal2-1.9/css/jscal2.css" />
	<link rel="stylesheet" type="text/css" href="js/JSCal2-1.9/css/border-radius.css" />
	<link rel="stylesheet" type="text/css" href="js/JSCal2-1.9/css/steel/steel.css" />
	<title>設 定 表 單 代 理 人</title>
</head>
<body>
	<div id="L01"><table id="L0101"></table></div>
	<div id="L02">
		<div id="TKC_edit">
			<form name="ef01" id="ef01" action="json/JBPMF02.action">
			<input type="hidden"  name="f13.id" />
			<input type="hidden"  name="f13.f02_id2" />
			<input type="hidden"  name="f13.bpm13999" />
			<table>
				<tr>
					<th colspan=8 align=center>設 定 表 單 代 理 人 (ID:<a class='upID'></a>)</th>
				</tr>
				<tr>
					<td colspan=8 >
					<div id="TKC_menu">
					<ul>
						<li><a href="javascript: kc_submitChk('#ef01',{'action':'f13add'})">新增</a></li>
						<li><a href="javascript: kc_submitChk('#ef01',{'action':'f13mdy'})">修改</a></li>
						<li><a href="javascript: kc_submitChk('#ef01',{'action':'f13del'})">刪除</a></li>
					</ul>
					</div>
					</td>
				</tr>
				<tr>
					<td align=right>組群 :</td> 	
					<td><input type="text" name="f13.bpm13001" /></td>
					<td align=right>代理人 :</td> 	
					<td><input type="text" name="f13.f02_0002" readonly="readonly"/><button id="B_001">..</button></td>
				</tr>
				<tr>
					<td align=right>起始日 :</td> 	
					<td><input type="text" name="f13.bpm13002" id="f13.bpm13002"/><button id="B_002">..</button></td>
					<td align=right>截止日 :</td> 	
					<td><input type="text" name="f13.bpm13003" id="f13.bpm13003"/><button id="B_003">..</button></td>
				</tr>
			</table>
			</form>
		</div>
		<table id="L0201"></table>
	</div>
	<div class="jqmWindow" id="f02_v02f02"></div>
	
	<script type="text/javascript" src="js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jqModal/jqModal.js"></script>
	<script type="text/javascript" src="js/flexigrid/flexigrid.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
	<script type="text/javascript" src="js/jquery.alerts-1.1/jquery.alerts.js"></script>
	<script type="text/javascript" src="js/kc_func.js"></script>
	<script type="text/javascript" src="js/JSCal2-1.9/js/jscal2.js"></script>
	<script type="text/javascript" src="js/JSCal2-1.9/js/lang/b5.js"></script>
	<script type="text/javascript">
	var L03Dat = {rows:[],page:0,total:0,jqcmd:''};
	Calendar.setup({
		inputField : "f13.bpm13002",
		trigger    : "B_002",
		onSelect   : function() { this.hide(); },
		showTime   : 12,
		dateFormat : "%Y/%m/%d"
	});
	Calendar.setup({
		inputField : "f13.bpm13003",
		trigger    : "B_003",
		onSelect   : function() { this.hide(); },
		showTime   : 12,
		dateFormat : "%Y/%m/%d"
	});
	$('#B_001').click(function(e) {
		e.preventDefault();
		$('#f02_v02f02').jqmShow();
	});
	$('#f02_v02f02').jqm({
		ajax : 'bpm/jqm/bx_f02.jsp',
		onHide:bx_f02Close	
	});
	function bx_f02Close(hash){
		hash.w.hide();
		hash.o.hide();
		if ("確定" != $('input[id="bx_f02.status"]').val()) return;
		kc_reptxt('ef01','f13.f02_id2'  ,$('input[id="bx_f02.id"]').val());
		kc_reptxt('ef01','f13.f02_0002' ,$('input[id="bx_f02.bpm02001"]').val());
	}
  	function kc_submitChk(S01,S02){
		var bpm13999 = "";
		$('.datacb').each(function(){
			if($(this).is(':checked'))
				bpm13999 += $(this).val()+",";
		});		
		kc_reptxt('ef01','f13.bpm13999'   ,bpm13999);
		
  		Tmp = JSON.stringify(L03Dat['rows'], function(k, v) { return v === "" ? "" : v; });
		S02 = $.extend({'j_rows':Tmp},S02);
  		Tmp = JSON.stringify(kc_formtojson(S01), function(k, v) { return v === "" ? "" : v; });
		S02 = $.extend({'j_form':Tmp},S02);
		S02 = $.extend(S02,{upid:$('.upID').text()});
		switch (S02['action']) {
			case 'f13mdy' : {if (!confirm("是否要修改 ?")) return; break;}
			case 'f13del' : {if (!confirm("是否要刪除 ?")) return; break;}
			case 'f13add' : {if (!confirm("是否要新增 ?")) return; break;}
		}
		kc_ajax($(S01).attr('action'),S02,function(json){
			$('#L0101').flexAddData(json);
			if (json['Msg']!='') jAlert(json['Msg']);
			});
	}
	$('#L0101').flexigrid({
		url: 'json/JBPMF02.action?action=f13box',
		dataType: 'json',
		colModel : [   
	 				{display: 'ID'		, name : 'id'			, hide:true, sortable : true, align: 'center'},
	 				{display: '代理人id'	, name : 'f02_id2'		, hide:true, sortable : true, align: 'center'},
	 				{display: '表單ids'	, name : 'bpm13999'		, hide:true, sortable : true, align: 'center'},
	 			 	{display: '組群'		, name : 'bpm13001'		, width : 50, sortable : true, align: 'left'},   
	 			 	{display: '代理人'	, name : 'f13_02'		, width : 80, sortable : true, align: 'left'},   
	 			 	{display: '起始日'	, name : 'bpm13002'	    , width : 60, sortable : true, align: 'left'},   
	 			 	{display: '截止日'	, name : 'bpm13003'		, width : 60, sortable : true, align: 'left'}   
	 			],
		usepager : false,
		useRp : false,
		showTableToggleBtn : true,
		errormsg:	msg_fg_errormsg,
		pagestat:	msg_fg_pagestat, 
		procmsg:	msg_fg_procmsg, 
		nomsg:		msg_fg_nomsg,
		singleSelect:true, 
		nowrap:true,
		showToggleBtn:true,
		resizable:true, 
		width :  $('#L01').width(),
		height : $(window).height()-$('#L01').height()-100,
		onSuccess : function(){
	 			var cc = $('#L0101');
 				if ($('.trSelected',cc).length == 0){
 					if ($('.upID').text() != '')
 	 					$('#row'+$('.upID').text(),cc).addClass("trSelected");
 	 				else
 	 					$('tr:first',cc).addClass("trSelected");
 				}
 				Updata();
			/*
 			setTimeout(function(){
 	 			var cc = $('#L0101');
 				if ($('.trSelected',cc).length == 0){
 					if ($('.upID').text() != '')
 	 					$('#row'+$('.upID').text(),cc).addClass("trSelected");
 	 				else
 	 					$('tr:first',cc).addClass("trSelected");
 				}
 				Updata();
 			},100);
			*/	
		}
 	});
 	
	$('#L0101').click(Updata);
	function Updata(){
 		var cc = $('#L0101'),
			upid = '';
		if ($('.trSelected',cc).length==0) {  // 處理重覆按row問題
			if ($('tr:not(.trSelected)',cc).length==0) return;
 			$('tr',cc).each( function(){
 				upid = $('td[abbr="id"] >div', this).html();
 				if (upid == document.forms['ef01'].elements['f13.id'].value) {
 	 				$('#'+$(this).attr('id'),cc).addClass("trSelected");
 	 			}
			  });
			return;
		}
		upid = '';
		$('.trSelected',cc).each( function(){
			upid = $('td[abbr="id"] >div', this).html();          
	     });
    	if (upid == '') return;
		$('#L0201').flexOptions({url: 'json/JBPMF02.action?action=f13id&upid='+upid})
				   .flexReload(); 
	}
	$('#L0201').flexigrid({
		dataType: 'json',
		colModel : [   
	 				{display: '表單ID'	, name : 'id'		, width : 50, sortable : true, align: 'left', hide:true},
	 			 	{display: '代理'		, name : 'check'	, width : 50, sortable : true, align: 'left'},   
	 			 	{display: '表單代號'	, name : 'bpm03001'	, width : 80, sortable : true, align: 'left'},   
	 			 	{display: '表單簡稱'	, name : 'bpm03002'	, width : 60, sortable : true, align: 'left'}   
	 			],
		usepager : false,
		useRp : false,
		showTableToggleBtn : true,
		errormsg:	msg_fg_errormsg,
		pagestat:	msg_fg_pagestat, 
		procmsg:	msg_fg_procmsg, 
		nomsg:		msg_fg_nomsg,
		singleSelect:true, 
		nowrap:true,
		showToggleBtn:true,
		resizable:true, 
		width :  $('#L02').width(),
		height : $(window).height()-$('#L02').height()-100,
		preProcess : function(json){
			if (json['jqcmd'] == ''){
				kc_reptxt('ef01','f13.bpm13001'   ,json['bpm13001']);
				kc_reptxt('ef01','f13.bpm13002'   ,json['bpm13002']);
				kc_reptxt('ef01','f13.bpm13003'   ,json['bpm13003']);
				kc_reptxt('ef01','f13.f02_0002'   ,json['f02_0002']);
				kc_reptxt('ef01','f13.id'         ,json['id']);
				kc_reptxt('ef01','f13.f02_id2'    ,json['f02_id2']);
				$('.upID').text(document.forms['ef01'].elements['f13.id'].value);
			}
	        return L03Dat = json;
		},
		onSuccess : function(){
		}
 	}); 	
	</script>
</body>
</html>