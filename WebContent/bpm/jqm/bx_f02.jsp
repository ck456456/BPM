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
		<form name="bx_f02" id="bx_f02">
		<input type="hidden" id="bx_f02.status"/>
		<input type="hidden" id="bx_f02.id"/>
		<input type="text"   id="bx_f02.bpm02001" size="50" readonly="readonly"/>
		<input type="submit" id="bx_f02.B_001" value="確定" class="jqmClose" />
		<input type="submit" id="bx_f02.B_002" value="取消" class="jqmClose" />
		</form>
	<hr>
	<table id="bx_f02grid"></table>
	<script type="text/javascript">
 	$('#bx_f02grid').flexigrid({
		url: 'json/JBPMF02.action?action=f02box',
		dataType: 'json',
		colModel : [   
	 				{display: 'ID'		, name : 'id'			, width : 30, sortable : true, align: 'center'},   
	 			 	{display: '工號'		, name : 'bpm02001'		, width :100, sortable : true, align: 'left'},   
	 			 	{display: '姓名'		, name : 'bpm02002'		, width :100, sortable : true, align: 'left'}   
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
	$('#bx_f02grid').click(
		function () {
 			$('.trSelected',this).each( function(){
 				$('input[id="bx_f02.id"]')      .val($('td[abbr="id"] >div'      , this).html());
 				$('input[id="bx_f02.bpm02001"]').val($('td[abbr="bpm02001"] >div', this).html()
 		 				+'-'+$('td[abbr="bpm02002"] >div', this).html());
			});
 		});
	$('#bx_f02 input').click(function(e) {
		$('input[id="bx_f02.status"]').val(this.value);
	});
	</script>
</body>
</html>