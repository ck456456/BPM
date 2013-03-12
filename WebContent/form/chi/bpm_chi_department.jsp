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
	<form name="bx_chi_department" id="bx_chi_department">
		<input type="hidden" id="chi_department.status"/>
		<input type="hidden" id="chi_department.guid"/>
		<table style="width:100%;">
			<tr>
				<td align=right>編號 :</td>
				<td> <input type="text"	 id="chi_department.id" readonly="readonly"/></td>
				<td align=right>名稱 :</td>
				<td> <input type="text"   id="chi_department.name" size="50" readonly="readonly"/></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" id="chi_department.B_001" value="確定" class="jqmClose" />
					<input type="submit" id="chi_department.B_002" value="取消" class="jqmClose" />
				</td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</form>
	<hr>
	<table id="bx_chi_department_grid"></table>
	<script type="text/javascript">
 	$('#bx_chi_department_grid').flexigrid({
		dataType: 'json',
		colModel : [   
	 				{display: 'guid'	, name : 'guid'			, width : 30, sortable : true, align: 'center', hide:true},   
	 				{display: '編號'		, name : 'id'			, width : 80, sortable : true, align: 'left'},   
	 			 	{display: '名稱'		, name : 'name'			, width :400, sortable : true, align: 'left'}   
		],
		searchitems : [ {
			display : '編號',
			name : 'id',
			isdefault : true
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
	$('#bx_chi_department_grid').click(
		function () {
 			$('.trSelected',this).each( function(){
 				$('input[id="chi_department.guid"]')     .val($('td[abbr="guid"] >div'     , this).html());
 				$('input[id="chi_department.id"]')       .val($('td[abbr="id"] >div'       , this).html());
 				$('input[id="chi_department.name"]')     .val($('td[abbr="name"] >div'     , this).html());
			});
	 	});
	$('#bx_chi_department input').click(function(e) {
			$('input[id="chi_department.status"]').val(this.value);
		});
	$('#bx_chi_department_grid').flexOptions({url: 'json/JChiBox.action?action=departmentbox'})
		   .flexReload(); 
	</script>
</body>
</html>