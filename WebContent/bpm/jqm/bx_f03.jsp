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
		<form name="bx_f03" id="bx_f03">
		<input type="hidden" id="bx_f03.status"/>
		<input type="hidden" id="bx_f03.id"/>
		<input type="text"   id="bx_f03.bpm03001" size="50" readonly="readonly"/>
		<input type="submit" id="bx_f03.B_001" value="確定" class="jqmClose" />
		<input type="submit" id="bx_f03.B_002" value="取消" class="jqmClose" />
		</form>
	<hr>
	<table id="bx_f03grid"></table>
	<script type="text/javascript">
 	$('#bx_f03grid').flexigrid({
		url: 'json/JBPMF03.action?action=f03box',
		dataType: 'json',
		colModel : [   
	 				{display: 'ID'		, name : 'id'			, width : 30, sortable : true, align: 'center'},   
	 			 	{display: '表單代號'		, name : 'bpm03001'		, width :100, sortable : true, align: 'left'},   
	 			 	{display: '表單簡稱'		, name : 'bpm03002'		, width :100, sortable : true, align: 'left'},   
	 			 	{display: '表單名稱'		, name : 'bpm03003'		, width :100, sortable : true, align: 'left'}   
		],
		searchitems : [ {
			display : '代號',
			name : 'bpm03001',
			isdefault : true
		}, {
			display : '簡稱',
			name : 'bpm03002'
		} ],            
		sortname : "bpm03001",
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
	$('#bx_f03grid').click(
		function () {
 			$('.trSelected',this).each( function(){
 				$('input[id="bx_f03.id"]')      .val($('td[abbr="id"] >div'      , this).html());
 				$('input[id="bx_f03.bpm03001"]').val($('td[abbr="bpm03001"] >div', this).html()
 		 				+'-'+$('td[abbr="bpm03002"] >div', this).html());
			});
 		});
	$('#bx_f03 input').click(function(e) {
		$('input[id="bx_f03.status"]').val(this.value);
	});
	</script>
</body>
</html>