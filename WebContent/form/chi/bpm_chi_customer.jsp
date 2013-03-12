<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="#" class="jqmClose"><em>Close</em></a>
	<hr>
		<form name="bx_chi_customer" id="bx_chi_customer">
		<input type="hidden" id="chi_customer.status"/>
		<input type="hidden" id="chi_customer.guid"/>
		<input type="hidden" id="chi_customer.bguid"/>
		<table style="width:100%;">
			<tr>
				<td align=right>編號 :</td>
				<td> <input type="text"	 id="chi_customer.id" readonly="readonly"/></td>
				<td align=right>簡稱 :</td>
				<td> <input type="text"   id="chi_customer.shortname" size="50" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align=right>名稱 :</td>
				<td colspan=3> <input type="text" id="chi_customer.name" size="84" readonly="readonly"/></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" id="chi_customer.B_001" value="確定" class="jqmClose" />
					<input type="submit" id="chi_customer.B_002" value="取消" class="jqmClose" />
				</td>
				<td></td>
				<td></td>
			</tr>
		</table>
		</form>
	<hr>
	<table id="bx_chi_customer_grid"></table>
	<script type="text/javascript">
 	$('#bx_chi_customer_grid').flexigrid({
 		url: 'json/JChiBox.action?action=customerbox',
		dataType: 'json',
		colModel : [
					{display: '收款客戶'	, name : 'bguid'		, width : 30, sortable : true, align: 'center', hide:true},   
	 				{display: 'guid'	, name : 'guid'			, width : 30, sortable : true, align: 'center', hide:true},   
	 				{display: '編號'		, name : 'id'			, width : 30, sortable : true, align: 'center'},   
	 			 	{display: '簡稱'		, name : 'shortname'	, width :100, sortable : true, align: 'left'},   
	 			 	{display: '名稱'		, name : 'name'			, width :400, sortable : true, align: 'left'}   
		],
		searchitems : [ {
			display : '編號',
			name : 'id',
			isdefault : true
		}, {
			display : '簡稱',
			name : 'shortname'
		}, {
			display : '名稱',
			name : 'name',
			isdefault : true
		} ],            
		sortname : "id",
		sortorder : "asc",
		title : false, 
		usepager : true,
		useRp : true,
		rp : 10,
		showTableToggleBtn : false,
		errormsg:	msg_fg_errormsg,
		pagestat:	msg_fg_pagestat, 
		procmsg:	msg_fg_procmsg, 
		nomsg:		msg_fg_nomsg,
		singleSelect:true, 
		nowrap:true,
		showToggleBtn:true, 
		width :  600,
		height : 300
 	});
	$('#bx_chi_customer_grid').click(
		function () {
 			$('.trSelected',this).each( function(){
 				$('input[id="chi_customer.bguid"]')    .val($('td[abbr="bguid"] >div'    , this).html());
 				$('input[id="chi_customer.guid"]')     .val($('td[abbr="guid"] >div'     , this).html());
 				$('input[id="chi_customer.id"]')       .val($('td[abbr="id"] >div'       , this).html());
 				$('input[id="chi_customer.shortname"]').val($('td[abbr="shortname"] >div', this).html());
 				$('input[id="chi_customer.name"]')     .val($('td[abbr="name"] >div'     , this).html());
			});
	 	});
	$('#bx_chi_customer input').click(function(e) {
			$('input[id="chi_customer.status"]').val(this.value);
		});
	</script>

</body>
</html>