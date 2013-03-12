<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="display: inline"> 
		<input type="button" id="bmp_atta_pg_f1" value="上傳">
	</div>
	<div style="display: inline">
		<input type="button" id="bmp_atta_pg_f2" value="下載">
		<input type="button" id="bmp_atta_pg_f3" value="刪除">
		<input type="button" id="bmp_atta_pg_f0" value="重新整理">
	</div>
	    
	<table id="bpm_atta_pg"></table>
		
	<script type="text/javascript">
	if ('CREATE'!=bpm_status.toUpperCase()){
		$('#bmp_atta_pg_f1').hide();
		$('#bmp_atta_pg_f3').hide();
	}
	var uploader = document.getElementById('bmp_atta_pg_f1');
	upclick({
		      element: uploader,
		      action: 'json/JBPM_Prop.action?action=AttaUpload',
              action_params: {'f04id':bpm_f04id, status : bpm_status , tempath : bpm_tempath},
			  dataname: 'fileData',
			  autoSubmit: true,
		      onstart:
		        function(filename)
		        {
		          // alert('Start upload: '+filename);
		        },
		      oncomplete:
		        function(response_data) 
		        {
			      	if (""!=response_data){
						alert("上傳失敗,可能檔案超過 20M !!");
					} 
			      	else{
						alert("上傳成功");
					}    
		        }
		     });
		$('#bpm_atta_pg').flexigrid({
			url: 'json/JBPM_Prop.action?action=AttaDisplay&status='+bpm_status+'&tempath='+bpm_tempath+'&f04id='+bpm_f04id,
			dataType: 'json',
			colModel : [   
	 			{display: '檔名'		, name : 'name'		, width :600, sortable : true, align: 'left'},
	 			{display: '類型'		, name : 'ext'		, width :60 , sortable : true, align: 'left'},
	 			{display: '大小'		, name : 'size'		, width :60 , sortable : true, align: 'right'},
	 			{display: '修改日期'	, name : 'date'		, width :60 , sortable : true, align: 'left'},
	 			{display: '路徑'		, name : 'rpath'	, width :60 , sortable : true, align: 'left', hide:true}
			],
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
			width : $(window).width()-16,
			height : $(window).height()-200,
			onSuccess : function(){
			}
		});
		$('#bmp_atta_pg_f0').click(function () {
			$('#bpm_atta_pg')
				.flexOptions({url: 'json/JBPM_Prop.action?action=AttaDisplay&status='+bpm_status+'&tempath='+bpm_tempath+'&f04id='+bpm_f04id})
				.flexReload();
		});
		$('#bmp_atta_pg_f2').click(function () {
			var grid = $('#bpm_atta_pg');
			if ($('.trSelected', grid).length == 0){alert('請選擇記錄!');return;}
				$('.trSelected', grid).each(function() {
					rlph = $('td[abbr="rpath"] >div', this).html();
				});
			window.location='json/JBPM_Prop.action?action=AttaDownload&rpath='+rlph;
		});
		$('#bmp_atta_pg_f3').click(function () {
			var grid = $('#bpm_atta_pg');
			if ($('.trSelected', grid).length == 0){alert('請選擇記錄!');return;}
				$('.trSelected', grid).each(function() {
					rlph = $('td[abbr="rpath"] >div', this).html();
					name = $('td[abbr="name"] >div', this).html();
				});
			if (!confirm("確定刪除 " + name + " ?")) return;
	 			$.ajax({
	 				type:'POST',
	 				dataType: 'json',
	 				url: "json/JBPM_Prop.action",
	 				data:{action: 'AttaDelete', rpath: rlph},
	 				success:function(data,status){
	 					alert(data["Msg"]);
	 					$('#bmp_atta_pg_f0').click();
	 				},
	 				error  :function(XMLHttpRequest, textStatus, errorThrown){ 
					alert("ajax error");
					}
	 			});
		});
	</script>
</body>
</html>