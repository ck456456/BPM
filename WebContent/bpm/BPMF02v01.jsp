<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="Stylesheet" type="text/css" href="js/jqModal/css/jqModal.css"/>
	<link rel="Stylesheet" type="text/css" href="js/flexigrid/flexigrid.pack.css"/>
	<link rel="Stylesheet" type="text/css" href="css/TKC_styles.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery.alerts-1.1/jquery.alerts.css" />
<title>人 員 權 限 設 定</title>
</head>
<body>
	<div id="L01"><table id="L0101"></table></div>
	<div id="L02">
		<div id="TKC_edit">
			<form name="ef01" id="ef01" action="json/JBPMF02.action">
			<input type="hidden"  name="f02.id" />
			<input type="hidden"  name="bpm02004"/>
			<table>
				<tr>
					<th colspan=8 align=center>人員權限設定 (ID:<a class='upID'></a>)</th>
				</tr>
				<tr>
					<td colspan=8 >
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
					<td align=right>工號 :</td> 	
					<td><input type="text" name="f02.bpm02001" /></td>
					<td align=right>姓名 :</td> 	
					<td><input type="text" name="f02.bpm02002" /></td>
				</tr>
				<tr>
					<td align=right>職級 :</td> 	
					<td><input type="text" name="f02.bpm02003" /></td>
					<td align=right>部門 :</td> 	
					<td><input type="text" name="f02.bpm02004_n" readonly="readonly" style="width:68%;"/><input type="submit" id="B_002" value=".."/></td>
				</tr>
			</table>
			</form>
		</div>
		<table id="L03"></table>
	</div>
	<div class="jqmWindow" id="f02_v0101"></div>
	<div class="jqmWindow" id="f02_v0102"></div>
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
		S02 = $.extend({'j_f11':Tmp},S02);
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
	
	$('#B_002').click(function(e) {
		e.preventDefault();
		$('#f02_v0102').jqmShow();
	});
	$('#f02_v0102').jqm({
		ajax : 'bpm/jqm/bx_f01.jsp',
		onHide:bx_f01Close	
	});
	function bx_f01Close(hash){
		hash.w.hide();
		hash.o.hide();
		if ("確定" != $('input[id="bx_f01.status"]').val()) return;
		kc_reptxt('ef01','bpm02004'   ,$('input[id="bx_f01.id"]').val());
		kc_reptxt('ef01','f02.bpm02004_n' ,$('input[id="bx_f01.bpm01002"]').val());
	}
	
	$('#f02_v0101').jqm({
		ajax : 'bpm/jqm/f02_v0101.jsp',
		onHide:bx_G01Close	
	});
	function bx_G01Close(hash){
		hash.w.hide();
		hash.o.hide();
	}
	$('#L0101').flexigrid({
			url: 'json/JBPMF02.action?action=f02box',
			dataType: 'json',
			colModel : [   
	 			{display: 'id'		, name : 'id'		, hide:true , width :50, sortable : true, align: 'left'},   
			 	{display: '工號'		, name : 'bpm02001'	, width :100, sortable : true, align: 'left'},   
			 	{display: '姓名'		, name : 'bpm02002'	, width :200, sortable : true, align: 'left'}   
			],
			searchitems : [ {
				display : '工號',
				name : 'bpm02001',
				isdefault : true
			}, {
				display : '姓名',
				name : 'bpm02002'
			} ],            
			sortname : "bpm02001",
			sortorder : "asc",
			title : "人員資料一覽", 
			usepager : true,
			useRp : true,
			rp : 20,
			showTableToggleBtn : false,
			errormsg:	msg_fg_errormsg,
			pagestat:	msg_fg_pagestat, 
			procmsg:	msg_fg_procmsg, 
			nomsg:		msg_fg_nomsg,
			singleSelect:true, 
			nowrap:true,
			showToggleBtn:true,
			resizable:false, 
			width : $('#L01').width(),
			height : $(window).height()-135,
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
 				if (upid == document.forms['ef01'].elements['f02.id'].value) {
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
      
		$('#L03').flexOptions({url: 'json/JBPMF02.action?action=T02&upid='+upid})
				 .flexReload(); 
	}
	$('#L03').flexigrid({
		dataType: 'json',
		colModel : [   
	 				{display: 'ID'		, name : 'id'			, width : 30, sortable : true, align: 'center'},
	 			 	{display: '權限id'	, name : 'bpm11002'		, hide:true , sortable : true, align: 'left'},   
	 			 	{display: '權限'		, name : 'bpm11002_n'	, width :150, sortable : true, align: 'left'},   
	 			 	{display: '有效日期'	, name : 'bpm11003'		, width :150, sortable : true, align: 'left'},   
	 			 	{display: '說明'		, name : 'bpm11004'		, width :150, sortable : true, align: 'left'}   
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
				kc_reptxt('ef01','f02.bpm02001'   ,json['bpm02001']);
				kc_reptxt('ef01','f02.bpm02002'   ,json['bpm02002']);
				kc_reptxt('ef01','f02.bpm02003'   ,json['bpm02003']);
				kc_reptxt('ef01','bpm02004'   ,json['bpm02004']);
				kc_reptxt('ef01','f02.bpm02004_n' ,json['bpm02004_n']);
				kc_reptxt('ef01','f02.id'       ,json['id']);
				$('.upID').text(document.forms['ef01'].elements['f02.id'].value);
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
			$('#f02_v0101').jqmShow();
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
			$('#f02_v0101').jqmShow();
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