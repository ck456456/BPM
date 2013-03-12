<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" type="text/css" href="css/TKC_styles.css"/>
<link rel="Stylesheet" type="text/css" href="js/flexigrid/flexigrid.pack.css"/>
<link rel="stylesheet" type="text/css" href="js/jquery.alerts-1.1/jquery.alerts.css" />

<title>選單資料維護</title>
</head>
<body>
	<div id="TKC_edit">
		<s:form name="ef01" id="ef01" action="json/JSYSF01.action" theme="simple">
			<s:hidden    name="level" value="upt"></s:hidden>
			<s:hidden    name="M01.id"  ></s:hidden>
			<s:hidden    name="M01.sys01001"  ></s:hidden>
		    <table>
			<tr>
			    <th colspan=6 align=center>選單資料維護(ID:<a class='upID'></a><a class='upLevel'>,Level)</a></th>
			</tr>
			<tr>
				<td colspan=6 >
				<div id="TKC_menu">
				<ul>
					<li><a href="javascript: kc_submitChk('#ef01',{'action':'add'})">新增</a></li>
					<li><a href="javascript: kc_submitChk('#ef01',{'action':'mdy'})">修改</a></li>
					<li><a href="javascript: kc_submitChk('#ef01',{'action':'del'})">刪除</a></li>
					<li><a href="javascript: kc_submitChk('#ef01',{'action':'up' })">上一階</a></li>
					<li><a href="javascript: kc_submitChk('#ef01',{'action':'dwn'})">下一階</a></li>
				</ul>
				</div>
				</td>
			</tr>
			<tr>
				<td align=right>顯示名稱 :</td> 	
				<td><s:textfield name="M01.sys01004" style=" width : 400px;"/></td>
				<td align=right>排列順序 :</td> 	
				<td><s:textfield name="M01.sys01006" style=" width : 50px;"/></td>
				<td align=right>主選單記號 :</td> 	
				<td><s:textfield name="M01.sys01002" style=" width : 200px;"/></td>
			</tr>
			<tr>
				<td align=right>href :</td> 	
				<td colspan=5><s:textfield name="M01.sys01003" style=" width : 95%;"/></td>
			<tr>
				<td align=right>style :</td> 	
				<td colspan=5><s:textfield name="M01.sys01005" style=" width : 95%;"/></td>
			</tr>
		    </table>
			<input type="submit" style="display:none;">
		</s:form>
	</div>
	<table class="T01"></table> 
	<script type="text/javascript" src="js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jquery.alerts-1.1/jquery.alerts.js"></script>
	<script type="text/javascript" src="js/flexigrid/flexigrid.js"></script>
	<script type="text/javascript" src="js/kc_ajax.js"></script>
	<script type="text/javascript" src="js/kc_func.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
	<script type="text/javascript">
		
	  	function kc_submitChk(S01,S02){
			// S02 = $.extend($(".T01").KC_GetFlexItems(),S02);
			// S02 = $.extend(S02,{level:document.forms['ef01'].elements['level'].value});
			/*
 			var items = $('.trSelected');
 			if (items.length==0) return;
			S02 = $.extend(S02,{upid:items[0].id.substr(3)});
			kc_ajaxform(S01,S02,function(json) {
								$('.T01').flexAddData(json);
								jAlert("OK....");
								});
			*/
			switch (S02['action']) {
			case 'mdy' :
			case 'del' :
			case 'add' : 
			case 'up'  :
			case 'dwn' : {
							
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
 			url: 'json/JSYSF01.action?action=T01',
 			dataType: 'json',
 			colModel : [   
 				{display: 'ID'			, name : 'id'			, width : 30, sortable : true, align: 'center'},   
 			 	{display: '上階 MenuID'	, name : 'sys01001'		, width : 50, sortable : true, align: 'center'},   
 			 	{display: '排列順序'		, name : 'sys01006'		, width :100, sortable : true, align: 'left'},   
 			 	{display: '顯示名稱'		, name : 'sys01004'		, width :100, sortable : true, align: 'left'},   
 			 	{display: 'href'		, name : 'sys01003'		, width :100, sortable : true, align: 'left'},   
 			 	{display: 'Class'		, name : 'sys01005'		, width :100, sortable : true, align: 'right'},   
 			 	{display: '主選單記號'	, name : 'sys01002'		, width : 50, sortable : true, align: 'left'}   
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
 			width : $(window).width()-16,
 			height : $(window).height()-$("#TKC_edit").height()-125,
 			preProcess : function(json){
				kc_reptxt('ef01','level' ,json['level']);
				$('.upID').text('-');
 				$('.upLevel').text(',level:'+json['level']+')');
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
 	 				if (upid == document.forms['ef01'].elements['M01.id'].value) {
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
 			$.getJSON(url = 'json/JSYSF01.action?action=T02&upid='+upid,
 				function(json){
 					kc_reptxt('ef01','M01.sys01001' ,json['sys01001']);
 					kc_reptxt('ef01','M01.sys01002' ,json['sys01002']);
 					kc_reptxt('ef01','M01.sys01003' ,json['sys01003']);
 					kc_reptxt('ef01','M01.sys01004' ,json['sys01004']);
 					kc_reptxt('ef01','M01.sys01005' ,json['sys01005']);
 					kc_reptxt('ef01','M01.sys01006' ,json['sys01006']);
 					kc_reptxt('ef01','M01.id'       ,json['id']);
 					$('.upID').text(json['id']);
 				});
 				//.error(function(jqXHR, textStatus, errorThrown) {
 	 			//	alert(errorThrown);
 	 			//}); 
 		}
 		
 		function test(com, grid) {
 			if (com == 'Reload') {
 				$('.T01')
 				.flexOptions({url: 'json/JSYSF01.action?action=T01&level='+document.forms['ef01'].elements['level'].value})
 				.flexReload(); 
 			}
 		} 	 	
	</script>
</body>
</html>