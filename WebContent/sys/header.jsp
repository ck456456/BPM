<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="tw.com.prodisc.bpm.bean.BSYSF01" %>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
    <title>電子表單</title>
    <!-- Context menu default skin -->
    <link href="js/jeegoocontext/skins/cm_blue/style.css" rel="Stylesheet" type="text/css" />
    <style type="text/css">
		*{margin:0; padding:0}
		body, html{height:100%}
		body{
			font-family:Arial, Sans-Serif; 
			font-size:.8em; 
			line-height:1.4em;
			text-align: center;  
			color:#000; 
			background:#aaa;
			overflow: hidden;
		}
		
		#outer {
		  width: 1024px; 
		  margin: 0 auto; 
		  text-align: left; 
		  background-color: #fff;
		}
		#menu_header{position:relative; padding:2px 0px 0px 0px; background:#d7e1eb}
		#menu_header h2{position:absolute; right:10px; font-size:12px; font-weight:normal; color:#00294d}
		
		#menu_content{padding:0px 0px 0px 0px; border-bottom:solid 2px #0c3961; border-top:solid 2px #0c3961; border-left : solid 2px #0c3961;}
		
        #horizontal{margin:0px 0}
        #horizontal li{float:left; list-style-type:none}
        #horizontal a{display:inline-block; padding:2px 20px 3px 20px; background:#4d88c4; border-left:solid 1px #76a5d5; border-right:solid 1px #004284; border-bottom:solid 1px #004284;text-decoration:none; color:#fff}
        #horizontal a:hover{background:#6db8f4;}
    </style>
    
</head>
<body>
    <div id="outer">
        <div id="menu_header">
			<h2>
				<!-- 
				使用者: ${ G_F02.bpm02001 }(${ G_F02.bpm02002 }) ; IP: <%= request.getRemoteAddr() %> ; RemoteUser: <%= request.getRemoteUser() %>
				 -->
				 使用者: ${ G_F02.bpm02001 }(${ G_F02.bpm02002 })
			</h2>
		</div>          
        <div id="menu_content">
            <!-- Horizontal main menu -->
            <ul id="horizontal">
            	<%-- 
				使用者: ${ G_account } ; IP: <%= request.getRemoteAddr() %> ; RemoteUser: <%= request.getRemoteUser() %>
                <li><a id="mainmenu_1" href="#">系 統 設 定</a></li>
                <li><a id="mainmenu_2" href="#">基 本 設 定</a></li>
                <li><a id="mainmenu_3" href="#">日 常 作 業</a></li>
                <li><a id="mainmenu_4" href="#">日 常 報 表</a></li>
                <li><a id="mainmenu_5" href="#">統 計 報 表</a></li>
                <li><a id="mainmenu_6" href="#">輔 助 作 業</a></li>
                --%>
				<%
					@SuppressWarnings("unchecked")
					List<BSYSF01> MainMenu= (List<BSYSF01>) request.getAttribute("MainMenu");
					for (BSYSF01 c : MainMenu) {
						out.write("<li><a id= \"" + c.getSys01002()+
								    "\"  href=\"" + c.getSys01003()+
								   "\"  style=\"" + c.getSys01005()+
								            "\">" + c.getSys01004()+
						              "</a></li>\n");
					}
				%>
            </ul>
            <div style="clear:both"></div>
        </div>
        <iframe frameborder=0 width='100%' id='work' src=${ formID } ></iframe>
    </div>
    <ul id="submenu01" class="jeegoocontext cm_blue"></ul>
    <ul id="submenu02" class="jeegoocontext cm_blue"></ul>
    <ul id="submenu03" class="jeegoocontext cm_blue"></ul>
    <ul id="submenu04" class="jeegoocontext cm_blue"></ul>
    <ul id="submenu05" class="jeegoocontext cm_blue"></ul>
    <ul id="submenu06" class="jeegoocontext cm_blue"></ul>
    <script type="text/javascript" src="js/jquery/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.livequery.js"></script>
    <script type="text/javascript" src="js/jeegoocontext/jquery.jeegoocontext.js"></script>
    <script type="text/javascript">
		var arr = <%= session.getAttribute("G_SubMenu")  %>
		function chgFrame( url )
		{
			var o=document.getElementById('work');
			o.src=url;
			o.contentWindow.location.href=url;
		}
		
		function MenuINI()
		{
            var optionsHorizontal = {
				openBelowContext: true,
				event: 'mouseover',
				ignoreHeightOverflow: true,
				submenuLeftOffset: -1,
				startTopOffset: -1,
				fadeIn: 0,
				autoHide: true
			};
			$('#mainmenu01').jeegoocontext('submenu01', optionsHorizontal);
			$('#mainmenu02').jeegoocontext('submenu02', optionsHorizontal);
			$('#mainmenu03').jeegoocontext('submenu03', optionsHorizontal);
			$('#mainmenu04').jeegoocontext('submenu04', optionsHorizontal);
			$('#mainmenu05').jeegoocontext('submenu05', optionsHorizontal);
			$('#mainmenu06').jeegoocontext('submenu06', optionsHorizontal);
		}
        
        function adjustFrame()
		{
			theFrame = $("#work", parent.document.body);
			testheight = $(this).height() - 21 +'px';
			theFrame.height(testheight);
		}
		
        $(window).resize(function() {
            adjustFrame();
        });
		
        function SortBySUBID(x,y) {
        	return ((x.Sort == y.Sort) ? 0 : ((x.Sort > y.Sort) ? 1 : -1 ));    
        }
        function chkSub(S1){
	        for(var n=0;n<arr.length;n++){
	        	S2 = arr[n].SUBID.substring(0,arr[n].SUBID.lastIndexOf("_"));
		        if (S1 == S2) return true;
	        }
        	return false;
        }	    
        function MenuSet(){
            var ul = '';
	        arr.sort(SortBySUBID);  // 排一下顯示順序 "上階ID Sys01006"
	        // alert(arr);
	        for(var n=0;n<arr.length;n++){
	        	// 原 SUBID = 上階ID..._原ID
	        	// 執行後 SUBID = 上階ID 字串
	        	SUBID = arr[n].SUBID.substring(0,arr[n].SUBID.lastIndexOf("_"));
	        	// alert(arr[n].Sort+"|"+SUBID+"|"+arr[n].SUBID+"|"+arr[n].SYS01004);
        		ul = '';
	        	href = 'javascript:chgFrame(\"'+arr[n].SYS01003+'\")';
		        if (-1!=arr[n].SYS01003.lastIndexOf("login.jsp")) 
			        href = arr[n].SYS01003;
		        // 檢查是否有 下階 Menu    
        		if (chkSub(arr[n].SUBID)) 
            		ul = '<ul id='+arr[n].SUBID+'></ul>';
            	// 將結果加進去上階Menu	
            	$("#"+SUBID).append("<li><a href='"+href+"' style='"+arr[n].SYS01005+"'>"+arr[n].SYS01004+"</a>"+ul+"</li>"); 	
	        }
        }
        $(function(){
	        MenuSet();
	        MenuINI();
	        adjustFrame();
	    });
    </script>
</body>
</html>