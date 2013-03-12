package tw.com.prodisc.bpm.util;

import java.util.List;

import tw.com.prodisc.bpm.bean.BBPMF05;
import tw.com.prodisc.bpm.bean.BBPMF06;

public class BPM_PUB {
	// 重要性
	public static BBPMF05 FirstF05(List<BBPMF05> f05s){
		for (BBPMF05 f05 : f05s) 
			if (null == f05.getF05l()) return f05; 
		return null;
	}
	
	public static String get04003(String s01){
		String ret = "普通";
		if (s01.equals("0")) ret = "低";
		if (s01.equals("1")) ret = "普通";
		if (s01.equals("2")) ret = "高";
		return ret;
	}	
	public static String jpg05003(int i01){
		String ret = "images/16x16/People_027.gif";
		switch (i01){
			case 1 : ret = "images/16x16/People_027.gif"; break;
			case 2 : ret = "images/16x16/People_030.gif"; break;
			case 3 : ret = "images/16x16/Email_icons_036.gif"; break;
			default: ret = "images/16x16/People_027.gif";
		}
		return ret;
	}
	// 關號屬性
	public static String get05003(int i01){
		String ret = "簽核";
		switch (i01){
			case 1 : ret = "簽核"; break;
			case 2 : ret = "辦理"; break;
			case 3 : ret = "通知"; break;
			default: ret = "簽核";
		}
		return ret;
	}
	// 簽核結果
	public static String get04101(int i01){
		String ret = "未完成";
		switch (i01){
			case 1 : ret = "未完成";	break;
			case 2 : ret = "同意";	break;
			case 3 : ret = "不同意";	break;
			case 4 : ret = "已抽單";	break;
			default: ret = "未完成";
		}
		return ret;
	}
	
	public static String get05004(int i01,int i02){
		String ret = "未完成";
		switch (i01){
		case 1 : switch (i02){
					case 1 : ret = "未完成";	break;
					case 2 : ret = "同意";	break;
					case 3 : ret = "不同意";	break;
					case 4 : ret = "已抽單";	break;
					default: ret = "未完成";
				} break;
		case 2 : switch (i02){
					case 1 : ret = "未完成";	break;
					case 2 : ret = "已辦理";	break;
					case 3 : ret = "不同意";	break;
					case 4 : ret = "已抽單";	break;
					default: ret = "未完成";
				} break;
		case 3 : switch (i02){
					case 1 : ret = "未完成";	break;
					case 2 : ret = "已通知";	break;
					case 3 : ret = "不同意";	break;
					case 4 : ret = "已抽單";	break;
					default: ret = "未完成";
				} break;
		}
		return ret;
	}
	// 簽核結果  
	public static String get06001(int i01,BBPMF06 f06){
		String ret = "未簽核";
		switch (i01){
		case 1 : switch (f06.getBpm06001()){
					case 1 : ret = "簽核中";		break;
					case 2 : ret = "同意"; 		break;
					case 3 : ret = "不同意";		break;
					case 4 : ret = "已抽單";		break;
					case 5 : ret = f06.getF0201().getBpm02002()+" - 已簽核";	break;
					case 6 : ret = "退件";		break;
					default: ret = "未簽核";
				 } break;
		case 2 : switch (f06.getBpm06001()){
					case 1 : ret = "辦理中";		break;
					case 2 : ret = "已辦理";		break;
					case 3 : ret = "不同意";		break;
					case 4 : ret = "已抽單";		break;
					case 5 : ret = f06.getF0201().getBpm02002()+" - 已辦理";	break;
					case 6 : ret = "退件";		break;
					default: ret = "未辦理";
				 } break;
		case 3 : switch (f06.getBpm06001()){
					case 1 : ret = "待通知";		break;
					case 2 : ret = "已通知";		break;
					case 3 : ret = "不同意";		break;
					case 4 : ret = "已抽單";		break;
					case 5 : ret = f06.getF0201().getBpm02002()+" - 已簽核";	break;
					case 6 : ret = "退件";		break;
					default: ret = "未辦理";
				 } break;
		}
		return ret;
	}
}