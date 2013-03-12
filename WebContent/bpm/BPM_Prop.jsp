<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
		#TKC_edit {
		  width: 100%; 
		  margin: 0 auto; 
		  text-align: left; 
		  background-color: #fff;
		}
		#P01 th {
			background: #ccc;
			font-size: 12px;
			border: 1px solid #000000; 
		}
		table tr.a01 {
			background: #ccc;
			font-size: 12px;
			border: 1px solid #000000; 
		}
    </style>
<title>表 單 屬 性</title>
</head>
<body>
	<a id="bpm_prop_f04id" style="display: none">${ f04id }</a>
	<div id="TKC_edit" >
	    <table border=1>
			<tr><th colspan=4 align=center>表 單 屬 性</th></tr>
			<tr><td>代號</td><td>${ f03.bpm03001 }</td>     
			    <td>簡稱</td><td>${ f03.bpm03002 }</td></tr>
			    
			<tr><td>名稱</td><td>${ f03.bpm03003 }</td>    
			    <td>可否抽單</td><td>${ f03.bpm03101 }</td></tr>
			    
			<tr><td>說明</td><td colspan=3>${ f03.bpm03999 }</td></tr>
	    </table>
	    <br/>
	    <table id ="P01" border=1>
			<tr><th style="font-size:12px;">關卡</th>
				<th>簽核人</th>
				<th>簽核結果</th>
				<th>讀取次數</th>
				<th>收件時間</th>     
			    <th>簽核時間</th>
			    <th>意見</th>
			    </tr>
	    </table>
	</div>
	<script type="text/javascript" charset="utf-8">
	var o1 = $("#P01"),
		json = {};
	$.ajax({
		type:'POST',
		dataType: 'json',
		url: "json/JBPM_Prop.action",
		data:{action: 'history', f04id: $('#bpm_prop_f04id').text()}, 
		// data:"?action='history'&f04id="+$('#bpm_prop_f04id').text() , 
		success:function(data,status){sendP01(data);},
		error  :function(XMLHttpRequest, textStatus, errorThrown){ 
					alert("ajax error");
				}
	});	
	function sendP01(data){
		for (i=0; i<data.length; i++)
		{
			S1 = "";
			if (0!=data[i].rowspan)
				S1 = '<td rowspan='+data[i].rowspan+'>'+data[i].bpm12002+'</td>';
			o1.append(
				'<tr><td class="flag" style="font-size:12px;display:none;">'+data[i].bpm12001+'</td>'+
					S1+
					'<td>'+data[i].f02+'</td>'+
					'<td class="flag">'+data[i].bpm06001+'</td>'+
					'<td>'+data[i].bpm06007+'</td>'+
					'<td>'+data[i].bpm06003+'</td>'+   
				    '<td>'+data[i].bpm06004+'</td>'+
				    '<td>'+data[i].bpm06002+'</td></tr>');
		    S1 = data[i].bpm06001;
		}
		$('.flag:contains("無效")'  ).parent().css("background","#999");
		$('.flag:contains("退件")'  ).css("background","red");
		$('.flag:contains("不同意")').css("background","red");
		$('.flag:contains("已抽單")').css("background","red");
		
		$('.flag:contains("未簽核")').css("background","#1E74FF");
		$('.flag:contains("未通知")').css("background","#1E74FF");
		$('.flag').filter(function() {return $(this).text().indexOf('已辦理') === 0;}).css("background","#00FF40");
		$('.flag').filter(function() {return $(this).text().indexOf('同意')   === 0;}).css("background","#00FF40");
	}		
	</script>
</body>
</html>