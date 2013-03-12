<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="Stylesheet" type="text/css" href="js/flexigrid/flexigrid.pack.css"/>
	<link rel="Stylesheet" type="text/css" href="css/BPMF04v01.css"/>
	<title>待 處 理 表 單</title>
</head>
<body>
	<div class="abgne_tab">
		<ul class="tabs">
			<li><a href="#tab1" id='LL01'>待 簽 核</a></li>
			<li><a href="#tab2" id='LL02'>代 理 人 簽 核</a></li>
			<li><a href="#tab3" id='LL03'>離 職 代 簽 核</a></li>
		</ul>

		<div class="tab_container">
			<div id="tab1" class="tab_content">
				<table id="L0101"></table>
			</div>
			<div id="tab2" class="tab_content">
				<table id="L0102"></table>
			</div>
			<div id="tab3" class="tab_content">
				<table id="L0103"></table>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
	<script type="text/javascript" src="js/flexigrid/flexigrid.js"></script>
	<script type="text/javascript">
	$(function(){
		// 預設顯示第一個 Tab
		var _showTab = 0;
		var $defaultLi = $('ul.tabs li').eq(_showTab).addClass('active');
		$($defaultLi.find('a').attr('href')).siblings().hide();
		
		// 當 li 頁籤被點擊時...
		// 若要改成滑鼠移到 li 頁籤就切換時, 把 click 改成 mouseover
		$('ul.tabs li').click(function() {
			// 找出 li 中的超連結 href(#id)
			var $this = $(this),
				_clickTab = $this.find('a').attr('href');
			// 把目前點擊到的 li 頁籤加上 .active
			// 並把兄弟元素中有 .active 的都移除 class
			$this.addClass('active').siblings('.active').removeClass('active');
			// 淡入相對應的內容並隱藏兄弟元素
			$(_clickTab).stop(false, true).fadeIn().siblings().hide();

			return false;
		}).find('a').focus(function(){
			this.blur();
		});
	});	
	$('#L0101').flexigrid({
		url: 'json/JBPMF04.action?action=BPMPending',
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
		title : false, 
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
		height : $(window).height()-155,
		preProcess : function(json){
			$("#LL01").text("待 簽 核("+json['total']+")");
			return json;
		},	
		onSuccess : function(){
		}
		});
	$('#L0102').flexigrid({
		url: 'json/JBPMF04.action?action=BPMProxy',
		dataType: 'json',
		colModel : [   
 			{display: 'id'		, name : 'id'		, hide:true , width :50, sortable : true, align: 'left'},
		 	{display: '原簽核人'	, name : 'src02002'	, width :50, sortable : true, align: 'left'},
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
		title : false, 
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
		height : $(window).height()-155,
		preProcess : function(json){
			$("#LL02").text("代 理 人 簽 核("+json['total']+")");
			return json;
		},	
		onSuccess : function(){
		}
		});	
	$('#L0103').flexigrid({
		url: 'json/JBPMF04.action?action=BPMLeave',
		dataType: 'json',
		colModel : [   
 			{display: 'id'		, name : 'id'		, hide:true , width :50, sortable : true, align: 'left'},
		 	{display: '原簽核人'	, name : 'src02002'	, width :50, sortable : true, align: 'left'},
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
		title : false, 
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
		height : $(window).height()-155,
		preProcess : function(json){
			$("#LL03").text("離 職 代 簽 核("+json['total']+")");
			return json;
		},	
		onSuccess : function(){
		}
		});	
		function test(com, grid) {
 			if (com == '重新整理') {
 				$('#L0101')
 				.flexOptions({url: 'json/JBPMF04.action?action=BPMPending'})
 				.flexReload(); 
 				$('#L0102')
 				.flexOptions({url: 'json/JBPMF04.action?action=BPMProxy'})
 				.flexReload();
 				$('#L0103')
 				.flexOptions({url: 'json/JBPMF04.action?action=BPMLeave'})
 				.flexReload();
 			}
 		} 	 	
	function Updata(){
		var cc = $(this);// $(s1);
		upid = '';
		$('.trSelected',cc).each( function(){
			upid = $('td[abbr="id"] >div', this).html();          
	     });
	     if (upid == '') return;
	     location.href='WorkFlow.action?action=main&f04_id='+upid+'&status=approve';
	}
	$('#L0101').click(Updata);
	$('#L0102').click(Updata);
	$('#L0103').click(Updata);
	</script>
</body>
</html>