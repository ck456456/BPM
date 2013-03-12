<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#L01 { 
	width: 20%; 
	float: left; 
	background-color:#eee;
} 
#L02 { 
	width: 80%; 
	float: left; 
	background-color:#eee;
}
</style>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="Stylesheet" type="text/css" href="js/jqModal/css/jqModal.css"/>
	<link rel="Stylesheet" type="text/css" href="js/flexigrid/flexigrid.pack.css"/>
	<link rel="Stylesheet" type="text/css" href="css/TKC_styles.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery.alerts-1.1/jquery.alerts.css" />
<title>權 限 群 組 設 定</title>
</head>
<body>
	<div id="L01"><table id="L0101"></table></div>
	<div id="L02">
		<div id="TKC_edit">
			<form name="ef01" id="ef01" action="json/JBPMF09.action">
			<input type="hidden"  name="f09.id" />
			<table>
				<tr>
					<th colspan=6 align=center>權限群組設定 (ID:<a class='upID'></a>)</th>
				</tr>
				<tr>
					<td colspan=6 >
					<div id="TKC_menu">
					<ul>
						<li><a href="javascript: kc_submitChk('#ef01',{'action':'add'})">新增</a></li>
						<li><a href="javascript: kc_submitChk('#ef01',{'action':'mdy'})">修改</a></li>
						<li><a href="javascript: kc_submitChk('#ef01',{'action':'del'})">刪除</a></li>
					</ul>
					</div>
					</td>
				</tr>
				<tr>
					<td align=right>代號 :</td> 	
					<td><input type="text" name="f09.bpm09001" /></td>
					<td align=right>簡稱 :</td> 	
					<td><input type="text" name="f09.bpm09002" /></td>
					<td align=right>名稱 :</td> 	
					<td><input type="text" name="f09.bpm09003" /></td>
				</tr>
				<tr>
					<td align=right>說明 :</td> 	
					<td colspan=5><input type="text" name="f09.bpm09004" style="width:95%"/></td>
				</tr>
			</table>
			</form>
		</div>
		<table id="L03"></table>
	</div>
	<div class="jqmWindow" id="f09_v0101"></div>
	<script type="text/javascript" src="js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jqModal/jqModal.js"></script>
	<script type="text/javascript" src="js/flexigrid/flexigrid.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
	<script type="text/javascript" src="js/jquery.alerts-1.1/jquery.alerts.js"></script>
	<script type="text/javascript" src="js/kc_func.js"></script>
	<script type="text/javascript">
	var L03Dat = {rows:[],page:0,total:0,jqcmd:''};
  	function kc_submitChk(S01,S02){
  		Tmp = JSON.stringify(L03Dat['rows'], function(k, v) { return v === "" ? "" : v; });
		S02 = $.extend({'j_f10':Tmp},S02);
		S02 = $.extend(S02,{upid:$('.upID').text()});
		switch (S02['action']) {
		case 'mdy' :
		case 'del' :
		case 'add' : {
						kc_ajaxform(S01,S02,function(json){
											$('#L0101').flexAddData(json);
											if (json['Msg']!='') jAlert(json['Msg']);
											});
					  	break;
					 }
		}
	}
	$('#f09_v0101').jqm({
		ajax : 'bpm/jqm/f09_v0101.jsp',
		onHide:bx_G01Close	
	});
	function bx_G01Close(hash){
		hash.w.hide();
		hash.o.hide();
	}
	$('#L0101').flexigrid({
			url: 'json/JBPMF09.action?action=T01',
			dataType: 'json',
			colModel : [   
	 			{display: 'id'		, name : 'id'		, hide:true , width :50, sortable : true, align: 'left'},   
			 	{display: '代號'		, name : 'bpm09001'	, width :50, sortable : true, align: 'left'},   
			 	{display: '簡稱'		, name : 'bpm09002'	, width :100, sortable : true, align: 'left'}   
			],
			usepager : false,
			title : false, 
			useRp : false,
			showTableToggleBtn : true,
			errormsg:	msg_fg_errormsg,
			pagestat:	msg_fg_pagestat, 
			procmsg:	msg_fg_procmsg, 
			nomsg:		msg_fg_nomsg,
			singleSelect:true, 
			nowrap:true,
			showToggleBtn:true,
			resizable:false, 
			width : $('#L01').width(),
			height : $(window).height()-45,
			onSuccess : function(){
	 			var cc = $('#L0101');
				if ($('.trSelected',cc).length == 0){
					if ($('.upID').text() != '') 
	 					$('#row'+$('.upID').text(),cc).addClass("trSelected");
	 				else	
	 					$('tr:first',cc).addClass("trSelected");
				}
				Updata();
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
 				if (upid == document.forms['ef01'].elements['f09.id'].value) {
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
      
		$('#L03').flexOptions({url: 'json/JBPMF09.action?action=T02&upid='+upid})
				.flexReload(); 
	}
	$('#L03').flexigrid({
		dataType: 'json',
		colModel : [   
	 				{display: 'ID'		, name : 'id'			, width : 30, sortable : true, align: 'center'},
	 			 	{display: '選單id'	, name : 'bpm10002'		, hide:true , sortable : true, align: 'left'},   
	 			 	{display: '選單'		, name : 'bpm10002_n'	, width :150, sortable : true, align: 'left'},   
	 			 	{display: '有效日期'	, name : 'bpm10003'		, width :150, sortable : true, align: 'left'},   
	 			 	{display: '說明'		, name : 'bpm10004'		, width :150, sortable : true, align: 'left'}   
	 			],
		buttons : [
					{name: msg_add,  onpress : L03CMD},
					{name: msg_mdy,  onpress : L03CMD},
					{name: msg_del,  onpress : L03CMD},
					{name: msg_ref,  onpress : L03CMD},
					{separator: true}
				],
		usepager : false,
		title : false, 
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
		height : $(window).height()-$('#L02').height()-75,
			preProcess : function(json){
				if (json['jqcmd'] == ''){
				kc_reptxt('ef01','f09.bpm09001' ,json['bpm09001']);
				kc_reptxt('ef01','f09.bpm09002' ,json['bpm09002']);
				kc_reptxt('ef01','f09.bpm09003' ,json['bpm09003']);
				kc_reptxt('ef01','f09.bpm09004' ,json['bpm09004']);
				kc_reptxt('ef01','f09.id'       ,json['id']);
				$('.upID').text(document.forms['ef01'].elements['f09.id'].value);
				}
		        return L03Dat = json;
			},
			onSuccess : function(){
	 			var cc = $('#L03');
				if ($('.trSelected',cc).length == 0){
					if (L03Dat['jqid'] == '') $('tr:first',cc).addClass("trSelected"); 
					if (L03Dat['jqid'] != '') $('#row'+L03Dat['jqid'],cc).addClass("trSelected");
				}
			}
 	});
	$('#L03').click(UpL03);
	function UpL03(){
 		var cc = $('#L03'),id = '';
		$('.trSelected',cc).each(function() {
			id = $(this).attr('id').substr(3);
			$.extend(L03Dat,{'jqid':id});
		});
		if ($('.trSelected',cc).length==0) {
			if ($('tr:not(.trSelected)',cc).length==0) return;
			if (L03Dat['jqid'] == '') $('tr:first',cc).addClass("trSelected"); 
			if (L03Dat['jqid'] != '') $('#row'+L03Dat['jqid'],cc).addClass("trSelected");
		}
		return;
 	}
	function L03CMD(com, grid){
		$.extend(L03Dat,{'jqcmd':com});
		if (com == '新增') {	
			$('#f09_v0101').jqmShow();
 		}
		if (com == '修改') {
 			var id; 
 			if ($('.trSelected', grid).length == 0){
			alert('請選擇記錄!');  
				return;  
			}
			$('.trSelected', grid).each(function() {
				id = $(this).attr('id').substr(3);
 		 		$.extend(L03Dat,{'jqid':id});
			});
			$('#f09_v0101').jqmShow();
 		}
		if (com == '刪除') {
			if ($('.trSelected', grid).length == 0){
				alert('請選擇記錄!');  
				return;  
			}
			$('.trSelected', grid).each(function() {
				var id = $(this).attr('id');
				id = id.substring(id.lastIndexOf('row')+3);
				if (confirm("確定刪除第 " + id + "筆 ?")){
					KC_JsonDel(L03Dat['rows'],'id', id);
					for (i=0; i<L03Dat["rows"].length; i++){
						L03Dat["rows"][i]['id'] = i+1;	
					}
					$('#L03').flexAddData(L03Dat);
 				}   
			});
 		}
		if (com == '整理') {
 			Updata();
 			}
	} 	 	
	</script>
</body>
</html>