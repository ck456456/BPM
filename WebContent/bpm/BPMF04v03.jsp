<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="Stylesheet" type="text/css" href="css/TKC_styles.css" />
	<link rel="Stylesheet" type="text/css" href="js/flexigrid/flexigrid.pack.css"/>
	<link rel="stylesheet" type="text/css" href="js/JSCal2-1.9/css/jscal2.css" />
	<link rel="stylesheet" type="text/css" href="js/JSCal2-1.9/css/border-radius.css" />
	<link rel="stylesheet" type="text/css" href="js/JSCal2-1.9/css/steel/steel.css" />
	<title>表 單 查 詢</title>
</head>
<body>
	<div id="TKC_edit">
		<form id="fm01">      
			<table>
				<tr>
					<th colspan=8 align=center>表 單 查 詢 </th>
				</tr>
				<tr>
					<td align=right>單別 :</td> 	
					<td><input type="text" name="b_03001" id="b_03001"/>~
						<input type="text" name="e_03001" id="e_03001"/></td>
					<td align=right>單號 :</td> 	
					<td><input type="text" name="b_04001" id="b_04001"/>~
						<input type="text" name="e_04001" id="e_04001"/></td>
				</tr>
				<tr>
					<td align=right>開單日 :</td> 	
					<td><input type="text" name="b_04004" id="b_04004" style="width:125px;"/><button id="B_001">..</button>~
						<input type="text" name="e_04004" id="e_04004" style="width:125px;"/><button id="B_002">..</button></td>
					<td><input type="submit" id="B_ret" value="搜尋"/></td>
				</tr>
			</table>
		</form>  		
	</div>
	
	<table id="L0101"></table>
	
	<script type="text/javascript" src="js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
	<script type="text/javascript" src="js/flexigrid/flexigrid.js"></script>
	<script type="text/javascript" src="js/JSCal2-1.9/js/jscal2.js"></script>
	<script type="text/javascript" src="js/JSCal2-1.9/js/lang/b5.js"></script>
	<script type="text/javascript" src="js/kc_func.js"></script>
	<script type="text/javascript">
	var today = new Date();
	$("#fm01 [name=b_04004]").val($.datepicker.formatDate("yy/mm/dd",kc_addDays(today,-7)));
	$("#fm01 [name=e_04004]").val($.datepicker.formatDate("yy/mm/dd",today));
	Calendar.setup({
		inputField : "b_04004",
		trigger    : "B_001",
		onSelect   : function() { this.hide(); },
		showTime   : 12,
		dateFormat : "%Y/%m/%d"
	});
	Calendar.setup({
		inputField : "e_04004",
		trigger    : "B_002",
		onSelect   : function() { this.hide(); },
		showTime   : 12,
		dateFormat : "%Y/%m/%d"
	});
	//  $(#L0101).attr('id')
	$('#L0101').flexigrid({
		url: 'json/JBPMF04.action?action=BPMDisplay&'+$('#fm01').serialize(),
		dataType: 'json',
		colModel : [   
 			{display: 'id'		, name : 'id'		, hide:true , width :50, sortable : true, align: 'left'},
		 	{display: '重要'		, name : 'bpm04003'	, width :25, sortable : true, align: 'left'},   
		 	{display: '上一關'	, name : 'prev'		, width :50, sortable : true, align: 'left'},
		 	{display: '表單簡稱'	, name : 'bpm03002'	, width :100, sortable : true, align: 'left'},   
		 	{display: '表單單號'	, name : 'bpm04001'	, width :75, sortable : true, align: 'left'},   
		 	{display: '主旨'		, name : 'bpm04002'	, width :380, sortable : true, align: 'left'},
		 	{display: '關卡屬性'	, name : 'bpm05002'	, width :55, sortable : true, align: 'left'},   
		 	{display: '填表人'	, name : 'bpm02002'	, width :50, sortable : true, align: 'left'},
		 	{display: '填表時間'	, name : 'bpm04004'	, width :110, sortable : true, align: 'left'},
		 	{display: '收件時間'	, name : 'bpm04006'	, width :110, sortable : true, align: 'left'}
		],
		sortname : "bpm04004",
		sortorder : "asc",
		usepager : false,
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
		resizable:true, 
		width : $(window).width()-20,
		height : $(window).height()-135,
		onSuccess : function(){
		}
		});
		$('#B_ret').click(function(e) {
			e.preventDefault();
			$('#L0101')
			.flexOptions({url: 'json/JBPMF04.action?action=BPMDisplay&'+$('#fm01').serialize(),
				dataType: 'json'
				})
			.flexReload(); 
		});
		$('#L0101').click(Updata);
		function Updata(){
			var cc = $('#L0101');
			upid = '';
			$('.trSelected',cc).each( function(){
				upid = $('td[abbr="id"] >div', this).html();          
		     });
		     if (upid == '') return;
		     location.href='WorkFlow.action?action=main&f04_id='+upid+'&status=display';
		}
		
	</script>
</body>
</html>