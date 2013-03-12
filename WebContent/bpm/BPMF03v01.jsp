<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="Stylesheet" type="text/css" href="css/TKC_styles.css"/>
	<link rel="Stylesheet" type="text/css" href="js/flexigrid/flexigrid.pack.css"/>
	<link rel="stylesheet" type="text/css" href="js/jquery.alerts-1.1/jquery.alerts.css" />
	<title>表 單 初 始 定 義</title>
</head>
<body>
	<div id="TKC_edit">
		<form name="ef01" id="ef01" action="json/JBPMF03.action">
			<input type="hidden"  name="f03.id" />
		    <table>
			<tr>
			    <th colspan=4 align=center>表 單 初 始 定 義(ID:<a class='upID'></a>)</th>
			</tr>
			<tr>
				<td colspan=4 >
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
				<td align=right>表單代號 :</td> 	
				<td><input type="text" name="f03.bpm03001" style=" width : 100px;"/></td>
				<td align=right>表單簡稱 :</td> 	
				<td><input type="text" name="f03.bpm03002" style=" width : 200px;"/></td>
			</tr>
			<tr>
				<td align=right>表單名稱 :</td> 	
				<td colspan=3><input type="text" name="f03.bpm03003" style=" width : 900px;"/></td>
			</tr>
			<tr>
				<td align=right>表單屬性 :</td>
				<td> <input type="checkbox" value="true" name="bpm03101"/>
				  	可否抽單</td>
				<td> <input type="checkbox" value="true" name="bpm03102"/>
				  	自動編號</td>
				<td> <input type="checkbox" value="true" name="bpm03103"/>
				  	有附件</td>
				  
			</tr>
			<tr>
				<td align=right> 程式位置:</td>
				<td colspan=3><input type="text" name="f03.bpm03998" style=" width : 900px;"/></td>
			</tr>
			<tr>
				<td align=right> 表單說明:</td>
				<td colspan=3><input type="text" name="f03.bpm03999" style=" width : 900px;"/></td>
			</tr>
		    </table>
			<input type="submit" style="display:none;">
		</form>
	</div>
	<table class="T01"></table> 
	
	<script type="text/javascript" src="js/kc_func.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jquery.alerts-1.1/jquery.alerts.js"></script>
	<script type="text/javascript" src="js/flexigrid/flexigrid.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
	<script type="text/javascript">
	  	function kc_submitChk(S01,S02){
			switch (S02['action']) {
			case 'mdy' :
			case 'del' :
			case 'add' : {
							S02 = $.extend(S02,{upid:$('.upID').text()});
							kc_ajaxform(S01,S02,function(json){
												$('.T01').flexAddData(json);
												if (json['Msg']!='') jAlert(json['Msg']);
												});
						  	break;
						 }
			}
	  	}
 	 	$('.T01').flexigrid({
 			url: 'json/JBPMF03.action?action=T01',
 			dataType: 'json',
 			colModel : [   
 				{display: 'ID'			, name : 'id'			, width : 30, sortable : true, align: 'center'},   
 			 	{display: '代號'			, name : 'bpm03001'		, width : 50, sortable : true, align: 'left'},   
 			 	{display: '簡稱'			, name : 'bpm03002'		, width : 50, sortable : true, align: 'left'},   
 			 	{display: '名稱'			, name : 'bpm03003'		, width :100, sortable : true, align: 'left'},   
 			 	{display: '可否抽單'		, name : 'bpm03101'		, width : 50, sortable : true, align: 'left'},   
 			 	{display: '自動編號'		, name : 'bpm03102'		, width : 50, sortable : true, align: 'left'},   
 			 	{display: '附件'			, name : 'bpm03103'		, width : 50, sortable : true, align: 'left'},   
 			 	{display: '說明'			, name : 'bpm03999'		, width :100, sortable : true, align: 'left'}   
 			],
 			buttons : [
 				{name: 'Reload',  onpress : test},
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
 			width : $("#TKC_edit").width(), // $(window).width()-16,
 			height : $(window).height()-$("#TKC_edit").height()-110,
 			preProcess : function(json){
				$('.upID').text('-');
 		        return json;
 			},
 			onSuccess : function(){
 				if ($('.trSelected').length == 0){
 					$('.bDiv tbody tr:first').addClass("trSelected");
 					Updata();
 				}
 			}
 	 	});

 	 	$('.T01').click(Updata);
 	 	
 		function Updata() {
 	 		var cc = $('.T01'),
				upid = '';
			
 			if ($('.trSelected',cc).length==0) {  // 處理重覆按row問題
 				if ($('tr:not(.trSelected)',cc).length==0) return;
 	 			$('tr',cc).each( function(){
 	 				upid = $('td[abbr="id"] >div', this).html();
 	 				if (upid == document.forms['ef01'].elements['f03.id'].value) {
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
		      
 			$.getJSON(url = 'json/JBPMF03.action?action=T02&upid='+upid,
 				function(json){
 					
 					kc_reptxt('ef01','f03.bpm03001' ,json['bpm03001']);
 					kc_reptxt('ef01','f03.bpm03002' ,json['bpm03002']);
 					kc_reptxt('ef01','f03.bpm03003' ,json['bpm03003']);
 					kc_repchk(           'bpm03101' ,json['bpm03101']);
 					kc_repchk(           'bpm03102' ,json['bpm03102']);
 					kc_repchk(           'bpm03103' ,json['bpm03103']);
 					kc_reptxt('ef01','f03.bpm03998' ,json['bpm03998']);
 					kc_reptxt('ef01','f03.bpm03999' ,json['bpm03999']);
 					kc_reptxt('ef01','f03.id'       ,json['id']);
 					$('.upID').text(json['id']);
 				});
 		}
 		
 		function test(com, grid) {
 			if (com == 'Reload') {
 				$('.T01')
 				.flexOptions({url: 'json/JBPMF03.action?action=T01'})
 				.flexReload(); 
 			}
 		} 	 	
	</script>
</body>
</html>