<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="#" class="jqmClose">Close</a>
	<hr>
		<form name="bx_sf01" id="bx_sf01">
		<input type="hidden" id="bx_sf01.status"/>
		<input type="hidden" id="bx_sf01.id"/>
		<input type="text"   id="bx_sf01.sys01004" size="50" readonly="readonly"/>
		<input type="submit" id="bx_sf01.B_001" value="確定" class="jqmClose" />
		<input type="submit" id="bx_sf01.B_002" value="取消" class="jqmClose" />
		</form>
	<hr>
	<div id="bx_sf01grid"></div>
	<script type="text/javascript">
 	$('#bx_sf01grid').flexigrid({
		url: 'json/JSYSF01.action?action=box',
		dataType: 'json',
		colModel : [   
	 				{display: 'ID'		, name : 'id'			, width : 30, sortable : true, align: 'center'},   
	 			 	{display: 'href'	, name : 'sys01003'		, width :100, sortable : true, align: 'left'},   
	 			 	{display: '顯示名稱'	, name : 'sys01004'		, width :100, sortable : true, align: 'left'}   
		],
		searchitems : [ {
			display : 'href',
			name : 'sys01003'
		}, {
			display : '顯示名稱',
			name : 'sys01004',
			isdefault : true
		} ],            
		sortname : "sys01004",
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
		height : 275
 	});
	$('#bx_sf01grid').click(
		function () {
 			$('.trSelected',this).each( function(){
 				$('input[id="bx_sf01.id"]')      .val($('td[abbr="id"] >div'      , this).html());
 				$('input[id="bx_sf01.sys01004"]').val($('td[abbr="sys01004"] >div', this).html());
			});
 		});
	$('#bx_sf01 input').click(function(e) {
		$('input[id="bx_sf01.status"]').val(this.value);
	});
	</script>
</body>
</html>