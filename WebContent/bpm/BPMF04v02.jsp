<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="Stylesheet" type="text/css" href="js/flexigrid/flexigrid.pack.css"/>
	<title>可 抽 表 單 一 覽</title>
</head>
<body>
	<table id="L0101"></table>
	<script type="text/javascript" src="js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
	<script type="text/javascript" src="js/flexigrid/flexigrid.js"></script>
	<script type="text/javascript">
	$('#L0101').flexigrid({
		url: 'json/JBPMF04.action?action=BPMCancel',
		dataType: 'json',
		colModel : [   
 			{display: 'id'		, name : 'id'		, hide:true , width :50, sortable : true, align: 'left'},
		 	{display: '重要'		, name : 'bpm04003'	, width :25, sortable : true, align: 'left'},   
		 	{display: '上一關'	, name : 'prev'		, width :50, sortable : true, align: 'left'},
		 	{display: '表單簡稱'	, name : 'bpm03002'	, width :100, sortable : true, align: 'left'},   
		 	{display: '表單單號'	, name : 'bpm04001'	, width :75, sortable : true, align: 'left'},   
		 	{display: '主旨'		, name : 'bpm04002'	, width :380, sortable : true, align: 'left'},
		 	{display: '關卡屬性'	, name : 'bpm05002'	, width :100, sortable : true, align: 'left'},   
		 	{display: '填表人'	, name : 'bpm02002'	, width :50, sortable : true, align: 'left'},
		 	{display: '收件時間'	, name : 'bpm04006'	, width :110, sortable : true, align: 'left'},
		 	{display: '填表時間'	, name : 'bpm04004'	, width :110, sortable : true, align: 'left'}
		],
		buttons : [
	 				{name: '重新整理',  onpress : test},
	 				{separator: true}
	 			],
		searchitems : [ {
			display : '表單簡稱',
			name : 'bpm03002',
			isdefault : true
		}, {
			display : '表單單號',
			name : 'bpm04001',
		}, {
			display : '主旨',
			name : 'bpm04002'
		} ],            
		sortname : "bpm04006",
		sortorder : "asc",
		title : "可 抽 表 單 一 覽", 
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
		function test(com, grid) {
 			if (com == '重新整理') {
 				$('#L0101')
 				.flexOptions({url: 'json/JBPMF04.action?action=BPMCancel'})
 				.flexReload(); 
 			}
 		} 	 	
		$('#L0101').click(Updata);
		function Updata(){
			var cc = $('#L0101');
			upid = '';
			$('.trSelected',cc).each( function(){
				upid = $('td[abbr="id"] >div', this).html();          
		     });
		     if (upid == '') return;
		     location.href='WorkFlow.action?action=main&f04_id='+upid+'&status=cancel';
		}
	</script>
</body>
</html>