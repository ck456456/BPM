<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 退 件 </title>
</head>
<body>
	<a href="#" class="jqmClose"><em>Close</em></a>
	<hr>
	<form name="bpm_jqm_return_form" id="bpm_jqm_return_form">
		<input type="hidden" id="bpm_form_status"/>
		<input type="hidden" id="bpm_form_id"/>
		<input type="text"   id="bpm_form_bpm05001" size="50" readonly="readonly"/>
		<input type="submit" id="bpm_form_B001" value="退件重辦"	class="jqmClose" />
		<input type="submit" id="bpm_form_B002" value="通知抽單"	class="jqmClose" />
		<input type="submit" id="bpm_form_B003" value="取消"		class="jqmClose" />
	</form>
	<table id="bpm_jqm_return_grid"></table>
	<hr>
	<script type="text/javascript">
	 	$('#bpm_jqm_return_grid').flexigrid({
			url: 'json/JBPM000.action?status=retbox&f04id='+bpm_f04id,
			dataType: 'json',
			colModel : [   
		 				{display: 'ID'		, name : 'id'			, width : 30, sortable : true, align: 'center'},   
		 				{display: '關號'		, name : 'bpm05001'		, width : 30, sortable : true, align: 'center'},   
		 			 	{display: '屬性'		, name : 'bpm05003'		, width : 30, sortable : true, align: 'left'},   
		 			 	{display: '簽核人員'	, name : 'bpm02001'		, width :100, sortable : true, align: 'left'},
		 			 	{display: '簽核時間'	, name : 'bpm06004'		, width :110, sortable : true, align: 'left'},
		 			 	{display: '簽核意見'	, name : 'bpm06002'		, width :220, sortable : true, align: 'left'}
			],
			title : false, 
			usepager : true,
			useRp : true,
			showTableToggleBtn : false,
			singleSelect:true, 
			nowrap:true,
			showToggleBtn:true, 
			errormsg:	msg_fg_errormsg,
			pagestat:	msg_fg_pagestat, 
			procmsg:	msg_fg_procmsg, 
			nomsg:		msg_fg_nomsg,
			width :  600,
			height : 275
	 	});
		$('#bpm_jqm_return_grid').click(
			function () {
	 			$('.trSelected',this).each( function(){
	 				$('#bpm_jqm_return_form input[id="bpm_form_id"]')      .val($('td[abbr="id"] >div'      , this).html());
	 				$('#bpm_jqm_return_form input[id="bpm_form_bpm05001"]').val($('td[abbr="bpm05001"] >div', this).html()+'-'+$('td[abbr="bpm02001"] >div', this).html());
				});
	 		});
		$('#bpm_jqm_return_form input').click(function(e) {
			e.preventDefault();
			$('#bpm_jqm_return_form input[id="bpm_form_status"]').val(this.value);
		});
	</script>
</body>
</html>