<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="js/JSCal2-1.9/css/jscal2.css" />
<link rel="stylesheet" type="text/css" href="js/JSCal2-1.9/css/border-radius.css" />
<link rel="stylesheet" type="text/css" href="js/JSCal2-1.9/css/steel/steel.css" />
<link rel="Stylesheet" type="text/css" href="css/form002.css"/>
<style type="text/css">
	.bpm_chi_box{
	    display: none;
	    
	    position: fixed;
	    top:  7%;
	    left: 50%;
	    
	    margin-left: -300px;
	    width: 600px;
	    height: 550px;
	    
	    background-color: #EEE;
	    color: #333;
	    border: 1px solid black;
	    padding: 5px;
	}
</style>

<title>Insert title here</title>
</head>
<body >
	<div class="form_head" style="border-bottom: gray 1px solid;padding-left  : 5px">
		<span style="float:left;" >申請人員 :  <a id="form002_t01"></a></span>
		<span style="float:right;">申請日期 :  <a id="form002_t02"></a></span>
    	<div style="clear:both;"></div>
	</div>
	<form name="ef01" id="ef01" action="json/JForm002.action">
		<div class="form_body" style="padding-left  : 5px; padding-right  : 5px">
			<input type="hidden"  name="form002000" />
			<div class="c1"> 類別 : <select id="form002003" name="form002003" > </select></div>
			<div class="c1"> 日期 : <input type="text" name="form002002" id="form002002"/><button id="B_002">..</button></div>
			<div class="c1"> 單號 : <input type="text" name="form002001" id="form002001"/></div>
			
			<ul class="tabs">
				<li><a href="#tab1" id='LL01'>單據</a></li>
				<li><a href="#tab2" id='LL02'>條件</a></li>
				<li><a href="#tab3" id='LL03'>送貨</a></li>
				<li><a href="#tab4" id='LL04'>加扣項</a></li>
				<li><a href="#tab5" id='LL05'>備註</a></li>
			</ul>
			<div class="tab_container" style="height : 160px;">
				<div id="tab1" class="tab_content">
					<table style="width:100%;">
						<tr>
							<td>客戶編號</td><td><input type="hidden"  name="form002101" readonly />
												 <input type="text"   name="form002101_1" id="form002101_1" readonly/><button id="B_101" disabled>..</button></td>
							<td>客戶簡稱</td><td><input type="text"    name="form002101_2" id="form002101_2" readonly/></td>
							<td>幣別匯率</td><td><select id="form002103" name="form002103" disabled></select><input type="text"    name="form002104" id="form002104" readonly/></td>
						</tr>
						<tr>
							<td>收款客戶</td><td><input type="hidden"  name="form002102" />
												<input type="text"    name="form002102_1" id="form002102_1" readonly/><button id="B_102" disabled>..</button></td>
							<td>收款客戶</td><td><input type="text"    name="form002102_2" id="form002102_2" readonly/></td>
							<td>統一編號</td><td><input type="text"    name="form002105" id="form002105" readonly/></td>
						</tr>
						<tr>
							<td>發票聯式</td>
								<td>
									<select id="form002106" name="form002106" disabled> 
										<option value="0">三聯式，電子二聯、三聯(31)</option>
										<option value="1">二聯式，收銀二聯(32)</option>
										<option value="2">免用</option>
										<option value="3">收銀機三聯式</option>
										<option value="4">空白</option>
									</select>
								</td> 	
							<td>產品單價</td>
								<td>
									<select id="form002107" name="form002107" disabled>
										<option value="0">未稅</option>
										<option value="1">含稅</option>
									</select>
								</td>
							<td>課稅類別</td>
								<td>
									<select id="form002108" name="form002108" disabled>
										<option value="0">應稅</option>
										<option value="1">零稅</option>
										<option value="2">免稅</option>
										<option value="3">免開</option>
										<option value="4">空白</option>
									</select>
								</td>
						</tr>
						<tr>
							<td>聯絡人員</td><td><input type="text"    name="form002109" id="form002109" readonly/></td> 	
							<td>聯絡電話</td><td><input type="text"    name="form002110" id="form002110" readonly/></td>
							<td>傳真電話</td><td><input type="text"    name="form002111" id="form002111" readonly/></td>
						</tr>
						<tr>
							<td>業務人員</td><td><input type="hidden"  name="form002112" />
												<input type="text"    name="form002112_1" id="form002112_1" readonly/><button id="B_112" disabled>..</button></td>
							<td>所屬部門</td><td><input type="hidden"  name="form002113" />
												<input type="text"    name="form002113_1" id="form002113_1" readonly/><button id="B_113" disabled>..</button></td>
						</tr>
					</table>
				</div>
				<div id="tab2" class="tab_content">
					tab2
				</div>
				<div id="tab3" class="tab_content">
					tab3
				</div>
				<div id="tab4" class="tab_content">
					tab4
				</div>
				<div id="tab5" class="tab_content">
					tab5
				</div>
			</div>
		</div>
	</form>
	
	<div class="bpm_chi_box" id="bpm_chi_customer"></div>
	<div class="bpm_chi_box" id="bpm_chi_department"></div>
	<div class="bpm_chi_box" id="bpm_chi_personnew"></div>
	
	<script type="text/javascript" src="js/JSCal2-1.9/js/jscal2.js"></script>
	<script type="text/javascript" src="js/JSCal2-1.9/js/lang/b5.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" src="js/kc_func.js"></script>
	<script type="text/javascript" charset="utf-8">
	var bb="";
	function bx_chi_personnewClose(hash){
		hash.w.hide();
		hash.o.hide();
		alert("-2-");
		// if ("確定" != $('input[id="chi_department.status"]').val()) return;
	}	
	function bx_chi_departmentClose(hash){
		hash.w.hide();
		hash.o.hide();
		if ("確定" != $('input[id="chi_department.status"]').val()) return;
		guid = $('input[id="chi_department.guid"]').val();
		kc_reptxt('ef01','form002113'   ,guid);
		kc_reptxt('ef01','form002113_1' ,$('input[id="chi_department.id"]').val()+" : "+$('input[id="chi_department.name"]').val());
	}	
	function chgcust(guid){
		alert(guid);
		$.ajax({
			type:'POST',
			dataType: 'json',
			url: 'json/JChiBox.action', 
			data:{action: 'customerguid', guid: guid},
			success:function(data,status){
				alert(data["a_ID"]);
			},
			error  :function(XMLHttpRequest, textStatus, errorThrown){alert('chgcust error!!');}
		});			
		
	}
	function bx_chi_customerClose(hash){
		hash.w.hide();
		hash.o.hide();
		if ("確定" != $('input[id="chi_customer.status"]').val()) return;
		guid  = $('input[id="chi_customer.guid"]').val();
		bguid = $('input[id="chi_customer.bguid"]').val();
		if (bb=="B_101"){
			kc_reptxt('ef01','form002101'   ,guid);
			kc_reptxt('ef01','form002101_1' ,$('input[id="chi_customer.id"]').val());
			kc_reptxt('ef01','form002101_2' ,$('input[id="chi_customer.shortname"]').val());
			chgcust(bguid);
			if (""==$('input[name="form002102"]').val()){
				kc_reptxt('ef01','form002102'   ,guid);
				kc_reptxt('ef01','form002102_1' ,$('input[id="chi_customer.id"]').val());
				kc_reptxt('ef01','form002102_2' ,$('input[id="chi_customer.shortname"]').val());
			}
		}
		if (bb=="B_102"){
			kc_reptxt('ef01','form002102'   ,guid);
			kc_reptxt('ef01','form002102_1' ,$('input[id="chi_customer.id"]').val());
			kc_reptxt('ef01','form002102_2' ,$('input[id="chi_customer.shortname"]').val());
		}	
	}
	
	$('#bpm_chi_personnew').jqm({
		ajax : 'form/chi/bpm_chi_personnew.jsp',
		onHide:bx_chi_personnewClose	
	});
	$('#bpm_chi_department').jqm({
		ajax : 'form/chi/bpm_chi_department.jsp',
		onHide:bx_chi_departmentClose	
	});
	$('#bpm_chi_customer').jqm({
		ajax : 'form/chi/bpm_chi_customer.jsp',
		onHide:bx_chi_customerClose	
	});
	$('#B_112').click(function(e) {
		bb=this.id;
		e.preventDefault();
		$('#bpm_chi_personnew').jqmShow();
	});
	$('#B_113').click(function(e) {
		bb=this.id;
		e.preventDefault();
		$('#bpm_chi_department').jqmShow();
	});
	$('#B_101, #B_102').click(function(e) {
		bb=this.id;
		e.preventDefault();
		$('#bpm_chi_customer').jqmShow();
	});
	Calendar.setup({
		inputField : "form002002",
		trigger    : "B_002",
		onSelect   : function() { this.hide(); },
		showTime   : 12,
		dateFormat : "%Y/%m/%d"
	});
	$(function(){
		var _showTab = 0;
		var $defaultLi = $('ul.tabs li').eq(_showTab).addClass('active');
		$($defaultLi.find('a').attr('href')).siblings().hide();
		$('ul.tabs li').click(function() {
			var $this = $(this),
				_clickTab = $this.find('a').attr('href');
			$this.addClass('active').siblings('.active').removeClass('active');
			$(_clickTab).stop(false, true).fadeIn().siblings().hide();
			return false;
		}).find('a').focus(function(){
			this.blur();
		});
	});
	
	function setini(){
		$.ajax({
			type:'POST',
			dataType: 'json',
			url: 'json/JChiBox.action', 
			data:'action=form002ini&' , 
			success:function(data,status){
				kc_loadselect($('#form002003'),data.ClassList);
				kc_loadselect($('#form002103'),data.Currency);
			},
			error  :function(XMLHttpRequest, textStatus, errorThrown){
				alert("form002ini error !!");
			}
		});
					
		$('#form002_t01').text($('#bpm_f_f4user').text()+'('+$('#bpm_f_f4dep').text()+')');
		$('#form002_t02').text($('#bpm_f_f4date').text());
		if ('CREATE'==bpm_status.toUpperCase()){
			$('#form002_t01').text($('#bpm_f_user').text()+'('+$('#bpm_f_dep').text()+')');
			$('#form002_t02').text($.datepicker.formatDate("yy/mm/dd",new Date()));
			$('#form002002').val($.datepicker.formatDate("yy/mm/dd",new Date()));
			$('#tab1 :text').attr('readonly', false);
			$('#tab1 :button').attr('disabled', false);
			$('#tab1 select').attr('disabled', false);
		}
			
		$('#tab1 :text')
			.filter('[name*="_"]')
			.attr('readonly', true);
	}
		
	function updata(){
	}	
	setini();
	updata();
	</script>
</body>
</html>