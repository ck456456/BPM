<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
		input[readonly] {background:#c7d1db;}
		textarea[readonly] {background:#c7d1db;}
		.form001box {
    		border-top: white 1px solid;
    		border-bottom: gray 1px solid;
    		border-left: white 1px solid;
    		border-right: gray 1px solid;
		}
		#bpm_chi_customer {
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
<body>
	<div class="form_head" style="border-bottom: gray 1px solid;">
		<span style="float:left;" >申請人員 :  <a id="form001_t01"></a></span>
		<span style="float:right;">申請日期 :  <a id="form001_t02"></a></span>
    	<div style="clear:both;"></div>
	</div>
	<div id="TKC_edit">
		<form name="ef01" id="ef01" action="json/JForm001.action">
		<input type="hidden"  name="id" />
			<table style="width:100%; border-bottom: gray 1px solid;">
				<tr class="create">
					<td align=right>申請類別 :</td>
						<td> 	
						<select id="form001002" name="form001002" disabled>
							<option selected value="新增">新增</option>
							<option value="變更">變更</option>
						</select>
						</td>
					<td align=right>客戶 :</td>
						<td colspan=3>
							<input type="hidden"  name="form001001" id="form001001"/>
							<input type="text"    name="form001001_n" id="form001001_n" style="width: 90%;" readonly />
							<button id="B_001" disabled>..</button>
						</td>
					<td rowspan=8>
						<fieldset style="height: 340px"><legend>客戶資料 (申請時)</legend>
					    <textarea name="form001903" id="form001903" style="width: 95%;height: 310px" readonly> </textarea>
					   </fieldset>
					</td>
				</tr>
				<tr class="create">
					<td></td>
						<td></td>
					<td align=right>預計下單金額 :</td>
						<td>
							<input type="text"    name="form001101" id="form001101" readonly/>
						</td>
					<td align=right>最近三個月每月交易額度 :</td>
						<td>
							<input type="text"    name="form001103" id="form001103" readonly/>
						</td>
				</tr>
				<tr class="create">
					<td align=right></td>
						<td>
						</td>
					<td align=right>申請授信額度 :</td>
						<td>
							<input type="text"    name="form001102" id="form001102" readonly/>
						</td>
					<td align=right>未來三個月每月交易額度 :</td>
						<td>
							<input type="text"    name="form001104" id="form001104" readonly/>
						</td>
				</tr>
				<tr class="create">
					<td align=right>檢附文件:</td>
					<td colspan=5>
						<table style="width:100%;">
							<tr>
								<td><input type='checkbox' name="form001201" id="form001201" disabled/>營利事業登記證影本</td>
								<td><input type='checkbox' name="form001202" id="form001202" disabled/>公司執照影本</td>
								<td><input type='checkbox' name="form001203" id="form001203" disabled/>工廠登記證影本</td>
							</tr>
							<tr>
								<td><input type='checkbox' name="form001204" id="form001204" disabled/>客戶資料表</td>
								<td><input type='checkbox' name="form001205" id="form001205" disabled/>客戶往來銀行資料</td>
								<td><input type='checkbox' name="form001206" id="form001206" disabled/>客戶甲存帳號徵信記錄</td>
							</tr>
							<tr>
								<td><input type='checkbox' name="form001207" id="form001207" disabled/>最近三次營業稅單401表</td>
								<td><input type='checkbox' name="form001208" id="form001208" disabled/>近三年財務報表</td>
								<td><input type='checkbox' name="form001209" id="form001209" disabled/>委外徵信資料</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr class="create">
					<td align=right>申請人員說明 :</td>
						<td colspan=5>
							<textarea name="form001901" id="form001901" style="width: 97%;height: 60px" readonly></textarea>
						</td>
				</tr>
				<tr>
					<td colspan=6> <hr></td>
				</tr>
				<tr class="accpnt">
					<td align=right>財會人員說明 :</td>
						<td colspan=5>
							<textarea name="form001902" id="form001902" style="width: 97%;height: 60px" readonly></textarea>
						</td>
				</tr>
				<tr class="accpnt">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td align=right>建議授信額度 :</td>
						<td><input name="form001105" id="form001105" type="text" readonly/></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="bpm_chi_customer"></div>
	
	<script type="text/javascript" src="js/jquery/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" charset="utf-8">
	function bx_chi_customerClose(hash){
		hash.w.hide();
		hash.o.hide();
		if ("確定" != $('input[id="chi_customer.status"]').val()) return;
		guid = $('input[id="chi_customer.guid"]').val();
		kc_reptxt('ef01','form001001'   ,guid);
		kc_reptxt('ef01','form001001_n' ,$('input[id="chi_customer.id"]').val()+':'+$('input[id="chi_customer.shortname"]').val());
		kc_ajax('json/JChiBox.action?action=customerguid',
				{'guid'	:guid},
				function(json){
					$('#form001903').text(
					  "客戶編號:"+json["a_ID"]+	
					"\n業務人員:"+json["b_Name"]+"("+json["b_ID"]+")"+	
					"\n收款方式:"+json["c_Name"]+"("+json["c_ID"]+")"+
					"\n交易條件:"+json["a_TradeCondition"]+
					"\n帳款額度:"+json["a_AccountLine"]+
					"\n票據額度:"+json["a_CheckLimit"]
					);
					$('#form001102').val(json["a_AccountLine"]);
					$('#form001105').val(json["a_AccountLine"]);
				});
	}
	$('#bpm_chi_customer').jqm({
		ajax : 'form/chi/bpm_chi_customer.jsp',
		onHide:bx_chi_customerClose	
	});
	$('#B_001').click(function(e) {
		e.preventDefault();
		$('#bpm_chi_customer').jqmShow();
		
	});
	
	$('#form001102').bind('blur', function() {
		if ('CREATE'!=bpm_status.toUpperCase()) return;
		$('#form001105').val($('#form001102').val());
	});
	function Approe_Before(){
		var ret="";
		if ('CREATE'==bpm_status.toUpperCase()){
			if ($.trim($('#form001001').val()) == '') ret += "未選客戶\n"; 
			if (!$.isNumeric($('#form001101').val())) ret += "預計下單金額錯誤\n";
			if (!$.isNumeric($('#form001102').val())) ret += "申請授信額度錯誤\n"; 
			if (!$.isNumeric($('#form001103').val())) ret += "最近三個月每月交易額度錯誤\n"; 
			if (!$.isNumeric($('#form001104').val())) ret += "未來三個月每月交易額度錯誤\n"; 
			if ($.trim($('#form001901').val()) == '') ret += "未輸入申請人員說明\n";
		}
		if (('APPROVE'==bpm_status.toUpperCase())||('EXECUTE'==bpm_status.toUpperCase()))
		if ("0100" == bpm_chkpnt) {
			if ($.trim($('#form001902').val()) == '') ret += "未輸入財會人員說明\n"; 
			if (!$.isNumeric($('#form001105').val())) ret += "建議授信額度錯誤\n";
		}
		if (ret=="") return true;
		alert(ret); 
		return false;
	}	
	function setini(){
		$('#form001_t01').text($('#bpm_f_f4user').text()+'('+$('#bpm_f_f4dep').text()+')');
		$('#form001_t02').text($('#bpm_f_f4date').text());
		
		if ('CREATE'==bpm_status.toUpperCase()){
			$('#form001_t01').text($('#bpm_f_user').text()+'('+$('#bpm_f_dep').text()+')');
			$('#form001_t02').text($.datepicker.formatDate("yy/mm/dd",new Date()));
			$('.create :text').attr('readonly', false);
			$('.create :checkbox').attr('disabled', false);
			$('.create :button').attr('disabled', false);
			$('.create textarea').attr('readonly', false);
			$('.create select').attr('disabled', false);
			$('#form001001_n').attr('readonly', true);
			$('#form001903').attr('readonly', true);
		}
		$('.create select').attr('readonly', false);
		if (('APPROVE'==bpm_status.toUpperCase())||('EXECUTE'==bpm_status.toUpperCase()))
		if (bpm_chkpnt=="0100") {
			$('.accpnt :text').attr('readonly', false);
			$('.accpnt textarea').attr('readonly', false);
		}
	}
	function updata(){
		if (bpm_json.length == 0) return;
		$("#ef01 [name=form001001]").val(bpm_json['ef01']['form001001']);
		$("#ef01 [name=form001001_n]").val(bpm_json['ef01']['form001001_n']);
		$("#ef01 [name=form001002]").val(bpm_json['ef01']['form001002']);
		$("#ef01 [name=form001101]").val(bpm_json['ef01']['form001101']);
		$("#ef01 [name=form001102]").val(bpm_json['ef01']['form001102']);
		$("#ef01 [name=form001103]").val(bpm_json['ef01']['form001103']);
		$("#ef01 [name=form001104]").val(bpm_json['ef01']['form001104']);
		$("#ef01 [name=form001105]").val(bpm_json['ef01']['form001105']);
		$("#ef01 [name=form001201]").prop('checked', !!bpm_json['ef01']['form001201']);
		$("#ef01 [name=form001202]").prop('checked', !!bpm_json['ef01']['form001202']);
		$("#ef01 [name=form001203]").prop('checked', !!bpm_json['ef01']['form001203']);
		$("#ef01 [name=form001204]").prop('checked', !!bpm_json['ef01']['form001204']);
		$("#ef01 [name=form001205]").prop('checked', !!bpm_json['ef01']['form001205']);
		$("#ef01 [name=form001206]").prop('checked', !!bpm_json['ef01']['form001206']);
		$("#ef01 [name=form001207]").prop('checked', !!bpm_json['ef01']['form001207']);
		$("#ef01 [name=form001208]").prop('checked', !!bpm_json['ef01']['form001208']);
		$("#ef01 [name=form001209]").prop('checked', !!bpm_json['ef01']['form001209']);
		$("#ef01 [name=form001901]").val(bpm_json['ef01']['form001901']);
		$("#ef01 [name=form001902]").val(bpm_json['ef01']['form001902']);
		$("#ef01 [name=form001903]").val(bpm_json['ef01']['form001903']);
	}	
	setini();
	updata();
	</script>
</body>
</html>