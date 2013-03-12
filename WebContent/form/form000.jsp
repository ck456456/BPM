<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="Stylesheet" type="text/css" href="js/jqModal/css/jqModal.css"/>
	<link rel="Stylesheet" type="text/css" href="css/styles.css" />
	<link rel="Stylesheet" type="text/css" href="js/flexigrid/flexigrid.pack.css"/>
	<link rel="Stylesheet" type="text/css" href="css/TKC_styles.css"/>
    <style type="text/css">
		#bpm_header {
			margin: 0 auto; 
			text-align: left;
			background-color: ivory;
    		border: gray 1px solid;
		}
		#bpm_head{
			padding:5px;
			letter-spacing: 3px; 
			border-top: gray 1px solid;
		}
		#bpm_body {
			margin: 0 auto; 
			text-align: left;
			border: gray 1px solid;
		}
		#bpm_footer {
			display: none;
			position:absolute;
			bottom:0;
    		height: 15px;
			background:silver;
			
    		font-size: 9pt;
    		font-family: 'Times New Roman';
    		LINE-HEIGHT: 0px;
    		
			
    		border-top: white 1px solid;
    		border-bottom: gray 1px solid;
    		border-left: white 1px solid;
    		border-right: gray 1px solid;
		}
		#bpm_footer span{
			display: none;
			width:100px; 
    		border-left: white 1px solid;
    		border-right: gray 1px solid;
		}
		#bpm_header input[readonly] {background:#c7d1db;}		
		#bpm_imgcmd{position:relative; padding:3px 0px 0px 0px; background:#d7e1eb; height: 26px; margin:0px 0;}
        #bpm_imgcmd li{float:left; list-style-type:none}
        #bpm_imgcmd img{display:inline-block; padding:0px 10px 0px 10px; cursor:hand;}
		#bpm_imgcmd img:hover{border-top: white 1px solid;border-bottom: gray 1px solid;border-left: white 1px solid;border-right: gray 1px solid;}
    </style>
<title>Insert title here</title>
</head>
<body>
    <div id="bpm_header">
		<ul id="bpm_imgcmd"></ul>
		<div id="bpm_head">
			<form name="bpm_main" id="bpm_main">
			單別:<input type="text" name="bpm_f_formnm" id="bpm_f_formnm" value="${ f_formnm }" style="width:450px;" readonly />
			單號:<input type="text" name="bpm_f_formsn" id="bpm_f_formsn" value="${ f_formsn }" style="width:80px;" readonly />
			關卡:<input type="text" value="${ f_f04.f05.bpm05002 }" style="width:100px;" readonly />
			重要性:
			<input type="radio" value="0" name="bpm_f_Important" class="bpm_f_Important" disabled/>低
			<input type="radio" value="1" name="bpm_f_Important" class="bpm_f_Important" checked disabled/>中
			<input type="radio" value="2" name="bpm_f_Important" class="bpm_f_Important" disabled/>高
			<br>
			主旨:<input type="text" name="bpm_f_subject" id="bpm_f_subject" value="${ f_f04.bpm04002 }" style="width:450px;" readonly/>
			</form>
		</div>
    </div>
    <div id="bpm_flow"></div>
    <div id="bpm_body"></div>
    <div id="bpm_prop"></div>
    <div id="bpm_atta"></div>
    <div id="bpm_footer">
		<span>登入者:<a id="bpm_f_user">${ G_F02.bpm02001 } - ${ G_F02.bpm02002 }</a></span>
		<span>登入部門:<a id="bpm_f_dep">${ G_F02.dep.bpm01002 } - ${ G_F02.dep.bpm01003 }</a></span>
		<span>申請者:<a id="bpm_f_f4user">${ f_f04.f02.bpm02001 } - ${ f_f04.f02.bpm02002 }</a></span>
		<span>申請部門:<a id="bpm_f_f4dep">${ f_f04.f02.dep.bpm01002 } - ${ f_f04.f02.dep.bpm01003 }</a></span>
		<span>申請日期:<a id="bpm_f_f4date"><fmt:formatDate value="${f_f04.bpm04004}" type="date" pattern="yyyy/MM/dd"/></a></span>
		
		<span>自動編號:<a id="bpm_f_autosn">${ f_f03.bpm03102 }</a></span>
		<span>附件:<a id="bpm_f_atta">${ f_f03.bpm03103 }</a></span>
		<span>狀態:<a id="bpm_f_status">${ f_status }</a></span>
		<span>單別:<a id="bpm_f_formid">${ f_f03.bpm03001 }</a></span>
		<span>關卡:<a id="bpm_f_checkpoint">${ f_f04.f05.bpm05001 }</a></span>
		<span>f04id<a id="bpm_f_f04id">${ f_f04.id }</a></span>
		<span>上傳路徑<a id="bpm_f_tempath">${ f_tempath }</a></span>
		<span>json:<a id="bpm_f_json">${ f_json }</a></span>
    </div>
	<div class="jqmWindow" id="bpm_jqm_return"></div>
	
	<script type="text/javascript" src="js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/flexigrid/flexigrid.js"></script>
	<script type="text/javascript" src="js/jqModal/jqModal.js"></script>
	<script type="text/javascript" src="js/upclick/upclick.js"></script>
	<script type="text/javascript" src="js/jstree/jquery.jstree.js"></script>
	<script type="text/javascript" src="js/jstree/lib/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/jstree/lib/jquery.hotkeys.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
	<script type="text/javascript" src="js/kc_func.js"></script>
	<script type="text/javascript" charset="utf-8">
		var imgcmd = {
				'待簽':'<li><img id="bpm_b_Pend" onclick="f_Pend()" title="返回待簽表"			src="images/Phrase3.gif"			/></li>',
				'傳送':'<li><img id="bpm_b_Send" onclick="f_Send(Approe_Before)" title="傳送"	src="images/efSend.gif"				style="height:20px;"/></li>',
				'附件':'<li><img id="bpm_b_Atta" onclick="f_Atta()" title="附件"					src="images/efAttachment.gif"		/></li>',
				'本文':'<li><img id="bpm_b_body" onclick="f_body()" title="本文"					src="images/efForm.gif"  			/></li>',
				'屬性':'<li><img id="bpm_b_Prop" onclick="f_Prop()" title="屬性"					src="images/efProperty.gif"			/></li>',
				'流程':'<li><img id="bpm_b_Flow" onclick="f_Flow()" title="流程"					src="images/efFlow.gif"				/></li>',
				'準'  :'<li><img id="bpm_b_Agre" onclick="f_Agre(Approe_Before)" title="準"		src="images/Agree1.gif"				style="width:20px;height:20px;" /></li>',
				'不準':'<li><img id="bpm_b_Deny" onclick="f_Deny()" title="不準"					src="images/Deny2.gif"				style="width:20px;height:20px;" /></li>',
				'退件':'<li><img id="bpm_b_Retu" onclick="f_Retu()" title="退件"					src="images/Deny1.gif"				style="width:20px;height:20px;" /></li>',
				'抽單':'<li><img id="bpm_b_Retu" onclick="f_Canc()" title="抽單"					src="images/efCancelApprove.gif"	style="width:20px;height:20px;" /></li>',
				'意見':'意見:<input type="text" name="bpm_b_Opinion" id="bpm_b_Opinion"			style="width:430px;"/>'
		};
		
		$(function () {
			bpm_json   = $('#bpm_f_json').text();
			if (bpm_json != '') bpm_json   = eval('('+bpm_json+')');
			bpm_autosn = $('#bpm_f_autosn').text(); 
			bpm_formid = $('#bpm_f_formid').text().toLowerCase();
			bpm_f04id  = $('#bpm_f_f04id').text();
			bpm_formsn = $(":input[id=bpm_formsn]").attr('value');
			bpm_status = $('#bpm_f_status').text();
			bpm_chkpnt = $('#bpm_f_checkpoint').text();
			bpm_atta   = $('#bpm_f_atta').text();
			bpm_tempath   = $('#bpm_f_tempath').text();
			appimgcmd(bpm_status);
			if ('CREATE'!=bpm_status.toUpperCase()){
				$('#bpm_flow').height($(window).height()-$("#bpm_header").height()-20)
	               			  .load('Property.action?action=TreeFlow&f04id='+bpm_f04id).hide();
				$('#bpm_prop').height($(window).height()-$("#bpm_header").height()-20)
	            			  .load('Property.action?action=Prop&formid='+bpm_formid+'&f04id='+bpm_f04id).hide();
            }
			$('#bpm_body').height($(window).height()-$("#bpm_header").height()-20)
			  .load('form/'+bpm_formid+'.jsp').show();
			$('#bpm_atta').height($(window).height()-$("#bpm_header").height()-20)
            			  .load('Property.action?action=Atta&f04id='+bpm_f04id).hide();
		});
				
		function appimgcmd(s1){
			$('#bpm_imgcmd li').remove();
			s1 = s1.toUpperCase();
			switch(s1)
			{
				case 'CREATE' : // 新增
					$('#bpm_imgcmd')
					.append(imgcmd['傳送'])
					.append(imgcmd['附件'])
					.append(imgcmd['本文'])
					.append(imgcmd['待簽']);
					$('#bpm_f_subject').attr('readonly', false);
					$('.bpm_f_Important').attr('disabled', false);
					if (bpm_autosn.toUpperCase()!="TRUE") $("#bpm_f_formsn").attr('readonly', false);
					break;
				case 'DISPLAY' : // 檢視
					$('#bpm_imgcmd')
					.append(imgcmd['附件'])
					.append(imgcmd['本文'])
					.append(imgcmd['屬性'])
					.append(imgcmd['流程'])
					.append(imgcmd['待簽']);
					break;
				case 'APPROVE' : // 簽核
					$('#bpm_imgcmd')
					.append(imgcmd['準'  ])
					.append(imgcmd['不準'])
					.append(imgcmd['退件'])
					.append(imgcmd['附件'])
					.append(imgcmd['本文'])
					.append(imgcmd['屬性'])
					.append(imgcmd['流程'])
					.append(imgcmd['待簽']);
					$('#bpm_main').append(imgcmd['意見']);
					break;
				case 'EXECUTE' : // 執行
					$('#bpm_imgcmd')
					.append(imgcmd['準'  ])
					.append(imgcmd['附件'])
					.append(imgcmd['本文'])
					.append(imgcmd['屬性'])
					.append(imgcmd['流程'])
					.append(imgcmd['待簽']);
					$('#bpm_main').append(imgcmd['意見']);
					break;
				case 'CANCEL' : // 抽單 cancel
					$('#bpm_imgcmd')
					.append(imgcmd['抽單'])
					.append(imgcmd['附件'])
					.append(imgcmd['本文'])
					.append(imgcmd['屬性'])
					.append(imgcmd['流程'])
					.append(imgcmd['待簽']);
					$('#bpm_main').append(imgcmd['意見']);
					break;
			}
			if (bpm_atta.toUpperCase()!="TRUE") $("#bpm_b_Atta").hide();
		}
		function f_Atta(){ 
			$('#bpm_body').hide();
			$('#bpm_prop').hide();
			$('#bpm_flow').hide();
			$('#bpm_atta').show();
		}
		function f_body(){
			$('#bpm_body').show();
			$('#bpm_prop').hide();
			$('#bpm_flow').hide();
			$('#bpm_atta').hide();
		}	
		function f_Prop(){
			$('#bpm_body').hide();
			$('#bpm_prop').show();
			$('#bpm_flow').hide();
			$('#bpm_atta').hide();
			// window.open ('Property.action?action=Prop&formid='+bpm_formid+'&f04id='+bpm_f04id);
		}
		function f_Flow(){
			$('#bpm_body').hide();
			$('#bpm_prop').hide();
			$('#bpm_flow').show();
			$('#bpm_atta').hide();
			// window.open ('Property.action?action=TreeFlow&f04id='+bpm_f04id);
		}
		function f_Canc(){
			if (!confirm('"抽單!!" 此單將作廢 ?')) return;
			c1=kc_formtojson('form');
			kc_ajax('json/JBPM000.action',
					{'json'		:JSON.stringify(c1),
				 	 'status'	:'Cancel',
				 	 'f04id'	:bpm_f04id},
						function(json){
								window.location = 'Manager.action?action=BPMF04';
						});
			}
		function f_Pend(){
			location.replace('Manager.action?action=BPMF04');
			}				
		function f_Send(Approe_Before){
			if ($.isFunction(Approe_Before)){ 
				if (!Approe_Before()) return;
			}	
			if (!confirm("是否開立此表單 ?")) return;
			c1=kc_formtojson('form');
			kc_ajax('json/JBPM000.action',
					{'json'		:JSON.stringify(c1),
				 	 'status'	:bpm_status,
				 	 'tempath'  :bpm_tempath,
				 	 'formid'	:bpm_formid},
				function(json){
					if (confirm("是否繼續新增表單 ?"))
						window.location = 'WorkFlow.action?action=main&formid='+$('#bpm_f_formid').text()+'&status=create';
					else
						window.location = 'Manager.action?action=BPMF04';
				});
		}
		function f_Agre(){ 
			if ($.isFunction(Approe_Before)){ 
				if (!Approe_Before()) return;
			}	
			S01 = '"核准!!" 到下一關 ?';
			if (bpm_status == "EXECUTE") S01 = '"已處理!!" 此表單 ?'; 
			if (!confirm(S01)) return;
			c1=kc_formtojson('form');
			kc_ajax('json/JBPM000.action',
					{'json'		:JSON.stringify(c1),
				 	 'status'	:bpm_status,
				 	 'f04id'	:bpm_f04id},
						function(json){
								window.location = 'Manager.action?action=BPMF04';
						});
		}
		function f_Deny(){ 
			if (!confirm('"不核准!!" 此單將作廢 ?')) return;
			c1=kc_formtojson('form');
			kc_ajax('json/JBPM000.action',
					{'json'		:JSON.stringify(c1),
				 	 'status'	:'Deny',
				 	 'f04id'	:bpm_f04id},
						function(json){
								window.location = 'Manager.action?action=BPMF04';
						});
		}
		function bpm_bxClose(hash){
			hash.w.hide();
			hash.o.hide();
			if ("取消" 		== $('input[id="bpm_form_status"]').val()) return;
			if ("退件重辦" 	== $('input[id="bpm_form_status"]').val()){
				if ("" == $('input[id="bpm_form_id"]').val()){ 
					alert("未選退辦關卡 !!");
					return;
				}	
				c1=kc_formtojson('form');
				kc_ajax('json/JBPM000.action',
						{'json'		:JSON.stringify(c1),
					 	 'status'	:'return',
					 	 'f05id'	:$('input[id="bpm_form_id"]').val(),
					 	 'f04id'	:bpm_f04id},
							function(json){
									window.location = 'Manager.action?action=BPMF04';
							});
			}	
			if ("通知抽單" 	== $('input[id="bpm_form_status"]').val()){
				c1=kc_formtojson('form');
				kc_ajax('json/JBPM000.action',
						{'json'		:JSON.stringify(c1),
					 	 'status'	:'retMail',
					 	 'f04id'	:bpm_f04id},
							function(json){
									window.location = 'Manager.action?action=BPMF04';
							});
			}	
		}
		$('#bpm_jqm_return').jqm({
			ajax : 'form/jqm/bpm_jqm_return.jsp',
			onHide:bpm_bxClose	
		});
		function f_Retu(){
			if (!confirm('"退件!!" 此單將到指定關卡 ?')) return;
			$('#bpm_jqm_return').jqmShow();
		};
		/*
		function f_Retu(){
			if (!confirm('"退件!!" 此單將到指定關卡 ?')) return;
			c1=kc_formtojson('form');
			kc_ajax('json/JBPM000.action',
					{'json'		:JSON.stringify(c1),
				 	 'status'	:'Return',
				 	 'f04id'	:bpm_f04id},
						function(json){
								window.location = 'Manager.action?action=BPMF04';
						});
		}
		*/
	</script>
</body>
</html>