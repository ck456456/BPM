<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style type="text/css">
		#header {
			margin: 0 auto; 
			text-align: center;
			font-weight:bold;
			font-family:標楷體;
			background-color: #fff;
			border-bottom: gray 1px solid;
		}
		#header
        .h_col{margin:0px 0;padding:5px 20px 0px 20px;}
        .h_col li{float:left; list-style-type:none}
        .h_row li{float:none; list-style-type:none}
        .clearfloat {clear:both;height:0;font-size: 1px;line-height: 0px;}
        
		#ef01 input[readonly] {background:#c7d1db;}		
    </style>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div id="header">
        <ul class="h_col">
            <li><img src="images/prodisc-english_v1.jpg"/></li>
        	<li>
        		<ul class="h_row" >
            		<li style="font-size:25px;width: 600px;">精碟科技股份有限公司</li>
            		<li style="font-size:16px;width: 600px;">表單測試作業</li>
        		</ul>
        	</li>
        	<li style="clear:both;"></li>
            <li style="font-size:12px;float:left;">表單代號:TEST001</li>
            <li style="font-size:12px;float:right;">Version:1.01</li>
        </ul>
        <div style="clear:both;"></div>
    </div>
    
	<div id="TKC_edit">
		<form name="ef01" id="ef01" action="json/JTest001.action">
			<input type="hidden"  name="id" />
			<table>
				<tr>
					<td align=right>String :</td> 	
					<td><input type="text" name="test001001" value="${ fm.id }"/></td>
					<td align=right>int :</td> 	
					<td><input type="text" name="test001002" /></td>
					<td align=right>Integer :</td> 	
					<td><input type="text" name="test001003" /></td>
					<td align=right>boolean :</td> 	
					<td><input type="text" name="test001004" /></td>
				</tr>
				<tr>
					<td align=right>Date :</td> 	
					<td><input type="text" name="test001005" /></td>
					<td align=right>float :</td> 	
					<td><input type="text" name="test001006" /></td>
					<td align=right>double :</td> 	
					<td><input type="text" name="test001007" /></td>
					<td align=right>long :</td> 	
					<td><input type="text" name="test001008" /></td>
				</tr>
				<tr>
					<td align=right>byte :</td> 	
					<td><input type="text" name="test001009" /></td>
					<td align=right>short :</td> 	
					<td><input type="text" name="test001010" /></td>
					<td align=right>char :</td> 	
					<td><input type="text" name="test001011" /></td>
				</tr>
				<tr>
					<td align=right>BigDecimal :</td> 	
					<td><input type="text" name="test001012" /></td>
					<td align=right>BigInteger :</td> 	
					<td><input type="text" name="test001013" /></td>
				</tr>
			</table>
		</form>
    </div>
	<script type="text/javascript" src="js/jquery/jquery.ui.datepicker.js"></script>
	<script type="text/javascript" charset="utf-8">
	function setini(){
		if ('CREATE'!=bpm_status.toUpperCase()){
			$('input[type=text]','#ef01').attr('readonly', true);
		}
		if ('APPROVE'==bpm_status.toUpperCase()){
			if (bpm_chkpnt=="0022") 
				$('input[name=test001005]','#ef01').attr('readonly', false);
		}	
	}	
	
	function updata(){
		if (bpm_json.length == 0) return;
		test001005="";
		if (bpm_json['ef01']['test001005']!=''){
		    test001005=$.datepicker.formatDate("yy/mm/dd",kc_parseJsonDate(bpm_json['ef01']['test001005']));
		}   
		$("#ef01 [name=test001001]").val(bpm_json['ef01']['test001001']);
		$("#ef01 [name=test001002]").val(bpm_json['ef01']['test001002']);
		$("#ef01 [name=test001003]").val(bpm_json['ef01']['test001003']);
		$("#ef01 [name=test001004]").val(bpm_json['ef01']['test001004']);
		$("#ef01 [name=test001005]").val(test001005);		
		$("#ef01 [name=test001006]").val(bpm_json['ef01']['test001006']);
		$("#ef01 [name=test001007]").val(bpm_json['ef01']['test001007']);
		$("#ef01 [name=test001008]").val(bpm_json['ef01']['test001008']);
		$("#ef01 [name=test001009]").val(bpm_json['ef01']['test001009']);
		$("#ef01 [name=test001010]").val(bpm_json['ef01']['test001010']);
		$("#ef01 [name=test001011]").val(bpm_json['ef01']['test001011']);
		$("#ef01 [name=test001012]").val(bpm_json['ef01']['test001012']);
	}
	
	updata();
	setini();
	</script>
</body>
</html>