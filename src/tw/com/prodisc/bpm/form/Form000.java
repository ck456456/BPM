package tw.com.prodisc.bpm.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import tw.com.prodisc.bpm.bean.BBPMF01;
import tw.com.prodisc.bpm.bean.BBPMF04;
import tw.com.prodisc.bpm.bean.BBPMF06;
import tw.com.prodisc.bpm.bean.BBPMF08;
import tw.com.prodisc.bpm.bean.BBPMF12;
import tw.com.prodisc.bpm.bean.BBPMP01;
import tw.com.prodisc.bpm.bean.BBPMF02;
import tw.com.prodisc.bpm.bean.BBPMF03;
import tw.com.prodisc.bpm.bean.BBPMF05;
import tw.com.prodisc.bpm.bean.BBPMF07;
import tw.com.prodisc.bpm.dao.IDao;

import tw.com.prodisc.bpm.service.IsBPMF02;
import tw.com.prodisc.bpm.service.IsBPMF07;
import tw.com.prodisc.bpm.util.BPM_PUB;
import tw.com.prodisc.util.KC_Pub;

import net.sf.json.JSONObject;
/*
 * 
 * public void flowBoss(String S01,String S02,Integer I01,String S03,String S04,BBPMP01 bpm)
 * public void flowRole(String S01,String S02,Integer I01,String S03,String S04,BBPMP01 bpm)
 * public void flowGrup(String S01,String S02,Integer I01,String S03,String S04,BBPMP01 bpm)
 * public void flowSort(BBPMP01 bpm) 
 * public void flowSend(BBPMP01 bpm) 
 * public String getLastSN(int id)
 * 
 * public void createComeIn(BBPMF03 f03)
 * public void createEnter(BBPMP01 bpm,JSONObject obj,BBPMF03 f03)
 * public void approveComeIn(,BBPMF04 f04)
 * public void approveEnter(JSONObject obj,BBPMF04 f04)    
 */
public abstract class Form000 implements IsForm{
	private static String[][] grade = 
		{{"作業層","45","46"},
		 {"課級層","47","50"},
		 {"部級層","51","55"},
		 {"處級層","56","61"},
		 {"決策層","62","99"}
		};
	@SuppressWarnings("rawtypes")
	private IsBPMF02 S_BPMF02;
	@SuppressWarnings("rawtypes")
	private IsBPMF07 S_BPMF07;
	public HttpServletRequest request;
	public HttpSession session;
	
	@SuppressWarnings("rawtypes")
	public Form000(){
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
	                                    ServletActionContext.getServletContext()
	                        );
		S_BPMF02 	= (IsBPMF02)		context.getBean("BPMF02");		
		S_BPMF07 	= (IsBPMF07)		context.getBean("BPMF07");
	}
	/** 刪掉已無歸屬的 f05 */
	public void delf05null(){
		Session ss01 = S_BPMF02.getDao().openSession();
		SQLQuery qry = ss01.createSQLQuery("delete BPMF05 Where f04_id is null");
		qry.executeUpdate();
		S_BPMF02.getDao().closeSession(ss01);
	}
	/** 檢查關卡是否存在 */
	private int chkf05(BBPMP01 bpm,BBPMF05 f05){
		int ret = -1;
		for (int i=0; i<bpm.getF04().getF05s().size(); i++){
			if (f05.getBpm05001().equals(bpm.getF04().getF05s().get(i).getBpm05001()))
				ret = i;
		}
		return ret;
	}
	/** 退件程序   
	 * @param bpm : 表單物件 
	 * @param obj : form 欄位資料(如 :退件意見) , 來自前端 
	 * @param f05id : 要退到哪一關  , 來自前端
	 */
	public void flowReturn(BBPMP01 bpm,JSONObject obj,String f05id){  
		/* 判定是否無關卡 */
		if (bpm.getF04().getF05s().size() == 0) return;
		/* 判定是否未完成 */
		if (bpm.getF04().getBpm04101() != 1) return;
		
        JSONObject o1 = (JSONObject) obj.get("bpm_main");
		String opinion = o1.getString("bpm_b_Opinion");
		
		BBPMF02 f02 = (BBPMF02) session.getAttribute("G_F02");
		/* 尋求未處理關卡 */
		BBPMF05 f05 = BPM_PUB.FirstF05(bpm.getF04().getF05s());
		while ((null != f05) && (2 == f05.getBpm05004())){
			f05 = f05.getF05n();
		}
		/* 紀錄退件資料 */
		if ((null != bpm.getF04().getF05()) &&
			    (bpm.getF04().getF05().getBpm05001().equals(f05.getBpm05001()))){
			List<BBPMF12> f12s = f05.getF12s();
			f05.setBpm05004(1);
			for (int i=0;i<f12s.size();i++){
				if (f12s.get(i).getBpm12001() != 0) continue;
				f12s.get(i).setBpm12001(1); // 設為無效結點
				f12s.get(i).setF05(null);
				List<BBPMF06> f06s = f12s.get(i).getF06s();
				for (int j=0;j<f06s.size();j++){
					/* 判斷簽核人員*/
					f06s.get(j).setBpm06001(5); // 他人已簽核
					if (f06s.get(j).getF02().getBpm02001().equals(f02.getBpm02001())){
						f06s.get(j).setBpm06001(6); // 退件 
						f06s.get(j).setBpm06002(opinion);
						f06s.get(j).setBpm06004(new Date());
					}
				}
			}
			f05 = f05.getF05l();
		}
		boolean bb = true;
		while ((null != f05) && bb){
			if (f05.getId().toString().equals(f05id)) bb = false;
			f05.setBpm05004(1);
			List<BBPMF12> f12s = f05.getF12s();
			for (int i=0;i<f12s.size();i++){
				if (f12s.get(i).getBpm12001() != 0) continue;
				f12s.get(i).setBpm12001(1); // 設為無效結點
				if (bb) f12s.get(i).setF05(null);
			}
			if (bb) f05 = f05.getF05l();
		}
		/* 刪除退回產生的關卡*/
		String S1="9999";
		List<BBPMF05> f05r= new ArrayList<BBPMF05>();
		for (BBPMF05 f05t: bpm.getF04().getF05s())
			if (f05t.getId().toString().equals(f05id)) S1 = f05t.getBpm05001();
		for (BBPMF05 f05t: bpm.getF04().getF05s())
			if (0 <= f05t.getBpm05005().compareTo(S1))
				f05r.add(f05t);
		bpm.getF04().getF05s().removeAll(f05r);
		flowSort(bpm);
			 
		/* 加入歷史流程 */
		// f12 加入
		BBPMF12 f12 = new BBPMF12();
		BBPMF12 f12l = null; 
		if (f05.getF05l()!=null) {
			List<BBPMF12> f12s = f05.getF05l().getF12s();
			for (int i=0;i < f12s.size(); i++)
				if (f12s.get(i).getBpm12001()==0) f12l = f12s.get(i);  
		}
		f12.setBpm12001(0);
		f12.setBpm12002(f05.getBpm05001());
		f12.setBpm12003(f05.getBpm05002());
		f12.setBpm12004(f05.getBpm05003());
		f12.setF04(bpm.getF04());
		f12.setF05(f05);
		f12.setF07(f05.getF07());
		f12.setF12l(f12l);
		if (f12l != null) f12l.setF12n(f12);
		f05.getF12s().add(f12);
		
		// f06 加入
		if (null != f05.getF02()){
			BBPMF06 f06 = new BBPMF06();
			f06.setBpm06001(1);
			f06.setBpm06003(new Date());
			f06.setF02(f05.getF02());
			f06.setF12(f12);
			f12.getF06s().add(f06);
		}
		if (null != f05.getF07()){
			List<BBPMF08> f08s = f05.getF07().getF08s();
			for (int i=0;i<f08s.size();i++){
				BBPMF06 f06 = new BBPMF06();
				f06.setBpm06001(1);
				f06.setBpm06003(new Date());
				f06.setF02(f08s.get(i).getF02());
				f06.setF12(f12);
				f12.getF06s().add(f06);
			}
		}
		
		/*設定待簽人員*/
		bpm.getF04().getF02s().clear();
		if (null != f05.getF02()){
			bpm.getF04().getF02s().add(f05.getF02());
		}
		if (null != f05.getF07()){
			List<BBPMF08> f08s = f05.getF07().getF08s(); 
			for (int i=0;i<f08s.size();i++){
				bpm.getF04().getF02s().add(f08s.get(i).getF02());
			}
		}
		/* 設定目前關卡 */
		bpm.getF04().setF05(f05);
		// bpm05004
	}
	
	/** 抽單程序   
	 * @param bpm : 表單物件 
	 * @param obj : 前端傳來的 form 欄位資料(如 :退件意見) 
	 */
	public void flowCancel(BBPMP01 bpm,JSONObject obj){
		/* 判定是否無關卡 */
		if (bpm.getF04().getF05s().size() == 0) return;
		/* 判定是否未完成 */
		if (bpm.getF04().getBpm04101() != 1) return;
		
		/* 尋求未處理關卡 */
		BBPMF05 f05 = BPM_PUB.FirstF05(bpm.getF04().getF05s());
		while ((null != f05) && (2 == f05.getBpm05004())){
			f05 = f05.getF05n();
		}
		if ((null != bpm.getF04().getF05()) &&
		    (bpm.getF04().getF05().getBpm05001().equals(f05.getBpm05001()))){
			f05.setBpm05004(4);
			List<BBPMF12> f12s = f05.getF12s();
			for (int i=0;i<f12s.size();i++){
				if (f12s.get(i).getBpm12001() != 0) continue;
				List<BBPMF06> f06s = f12s.get(i).getF06s();
				for (int j=0;j<f06s.size();j++){
					f06s.get(j).setBpm06001(4); // 已抽單
				}
			}
			f05 = f05.getF05n();
		}
		
		bpm.getF04().setF05(null);
		bpm.getF04().setBpm04101(4);
		bpm.getF04().setBpm04006(new Date());
		/* 清除待簽人員 */
		bpm.getF04().getF02s().clear();
	}
	/** "不同意"程序   
	 * @param bpm : 表單物件 
	 * @param obj : 前端傳來的 form 欄位資料(如 :退件意見) 
	 */
	public void flowDeny(BBPMP01 bpm,JSONObject obj){
		/* 判定是否無關卡 */
		if (bpm.getF04().getF05s().size() == 0) return;
		/* 判定是否未完成 */
		if (bpm.getF04().getBpm04101() != 1) return;
		
        JSONObject o1 = (JSONObject) obj.get("bpm_main");
		String opinion = o1.getString("bpm_b_Opinion");
		BBPMF02 f02 = (BBPMF02) session.getAttribute("G_F02");
		
		/* 尋求未處理關卡 */
		BBPMF05 f05 = BPM_PUB.FirstF05(bpm.getF04().getF05s());
		while ((null != f05) && (2 == f05.getBpm05004())){
			f05 = f05.getF05n();
		}
		if ((null != bpm.getF04().getF05()) &&
		    (bpm.getF04().getF05().getBpm05001().equals(f05.getBpm05001()))){
			f05.setBpm05004(3);
			List<BBPMF12> f12s = f05.getF12s();
			for (int i=0;i<f12s.size();i++){
				if (f12s.get(i).getBpm12001() != 0) continue;
				List<BBPMF06> f06s = f12s.get(i).getF06s();
				for (int j=0;j<f06s.size();j++){
					/* 判斷簽核人員*/
					f06s.get(j).setBpm06001(5); // 他人已簽核
					if (f06s.get(j).getF02().getBpm02001().equals(f02.getBpm02001())){
						f06s.get(j).setBpm06001(3);
						f06s.get(j).setBpm06002(opinion);
						f06s.get(j).setBpm06004(new Date());
					}
				}
			}
			f05 = f05.getF05n();
		}
		bpm.getF04().setF05(null);
		bpm.getF04().setBpm04101(3);
		bpm.getF04().setBpm04006(new Date());
		/* 清除待簽人員 */
		bpm.getF04().getF02s().clear();
	}
	
	/** "建構關卡及同意"程序   
	 * @param bpm : 表單物件
	 * @param obj : 前端傳來的 form 欄位資料(如 :退件意見) 
	 */
	public void flowSend(BBPMP01 bpm,JSONObject obj){
		/* 判定是否無關卡 */
		if (bpm.getF04().getF05s().size() == 0) return;
		/* 判定是否未完成 */
		if (bpm.getF04().getBpm04101() != 1) return;
		
		int 	i02 = 1;
		BBPMF02 f02 = (BBPMF02) session.getAttribute("G_F02");
		
		/* 尋求未處理關卡 */
		BBPMF05 f05 = BPM_PUB.FirstF05(bpm.getF04().getF05s());
		while ((null != f05) && (2 == f05.getBpm05004())){
			f05 = f05.getF05n();
		}
		/* 如果未處理關卡 與待簽關卡一樣 "簽準"*/
		if ((null != bpm.getF04().getF05()) &&
		    (bpm.getF04().getF05().getBpm05001().equals(f05.getBpm05001()))){
			f05.setBpm05004(2);
			List<BBPMF12> f12s = f05.getF12s();
			for (int i=0;i<f12s.size();i++){
				if (f12s.get(i).getBpm12001() != 0) continue;
				List<BBPMF06> f06s = f12s.get(i).getF06s();
				for (int j=0;j<f06s.size();j++){
					/* 判斷簽核人員*/
					f06s.get(j).setBpm06001(5); // 他人已簽核
					f06s.get(j).setF0201(f02);
					if (f06s.get(j).getF02().getBpm02001().equals(f02.getBpm02001())){
				        JSONObject o1 = (JSONObject) obj.get("bpm_main");
						String opinion = o1.getString("bpm_b_Opinion");
						f06s.get(j).setBpm06001(2);
						f06s.get(j).setBpm06002(opinion);
						f06s.get(j).setBpm06004(new Date());
					}
				}
			}
			f05 = f05.getF05n();
		}
		if (null == f05) {
			bpm.getF04().setF05(null);
			bpm.getF04().setBpm04101(2);
			bpm.getF04().setBpm04006(new Date());
			bpm.getF04().getF02s().clear();
			return;
		}
		
		do {
			// 加入歷史流程
			BBPMF12 f12 = new BBPMF12();
			BBPMF12 f12l = null; 
			if (f05.getF05l()!=null) {
				List<BBPMF12> f12s = f05.getF05l().getF12s();
				for (int i=0;i < f12s.size(); i++)
					if (f12s.get(i).getBpm12001()==0) f12l = f12s.get(i);  
			}
			f12.setBpm12001(0);
			f12.setBpm12002(f05.getBpm05001());
			f12.setBpm12003(f05.getBpm05002());
			f12.setBpm12004(f05.getBpm05003());
			f12.setF04(bpm.getF04());
			f12.setF05(f05);
			f12.setF07(f05.getF07());
			f12.setF12l(f12l);
			if (f12l != null) f12l.setF12n(f12);
			
			f05.getF12s().add(f12);
			
			/* 通知關卡處理 */
			i02 = 1;
			if (3==f05.getBpm05003()){
				f05.setBpm05004(2);
				i02 = 2;
			}
			
			if (null != f05.getF02()){
				BBPMF06 f06 = new BBPMF06();
				f06.setBpm06001(i02);
				f06.setBpm06003(new Date());
				f06.setF02(f05.getF02());
				f06.setF12(f12);
				f12.getF06s().add(f06);
			}
			if (null != f05.getF07()){
				List<BBPMF08> f08s = f05.getF07().getF08s();
				for (int i=0;i<f08s.size();i++){
					BBPMF06 f06 = new BBPMF06();
					f06.setBpm06001(i02);
					f06.setBpm06003(new Date());
					f06.setF02(f08s.get(i).getF02());
					f06.setF12(f12);
					f12.getF06s().add(f06);
				}
			}
			
			/*設定待簽人員*/
			bpm.getF04().getF02s().clear();
			if (3 != f05.getBpm05003()){
				if (null != f05.getF02()){
					bpm.getF04().getF02s().add(f05.getF02());
				}
				if (null != f05.getF07()){
					List<BBPMF08> f08s = f05.getF07().getF08s(); 
					for (int i=0;i<f08s.size();i++){
						bpm.getF04().getF02s().add(f08s.get(i).getF02());
					}
				}
			}
			f05 = f05.getF05n();
		} while ((null != f05) && (3 == f05.getF05l().getBpm05003()));
		
		
		/* 尋求未處理關卡 */
		f05 = BPM_PUB.FirstF05(bpm.getF04().getF05s());
		while ((null != f05) && (2 == f05.getBpm05004())){
			f05 = f05.getF05n();
		}
		if (null == f05) {
			bpm.getF04().setF05(null);
			bpm.getF04().setBpm04101(2);
			bpm.getF04().setBpm04006(new Date());
			bpm.getF04().getF02s().clear();
			return;
		}
		// 未完成 -  設定待簽關卡
		bpm.getF04().setF05(f05);
		
		/*
		for (int i=0; i<bpm.getF04().getF05s().size()-1; i++){
			System.out.println(bpm.getF04().getF05s().get(i).getF05().getBpm05001());
		}
		*/
	}
	/** 關卡排序 bpm.getF04().getF05s()
	 * @param bpm : 表單物件
	 */
	public void flowSort(BBPMP01 bpm){
		
		Collections.sort(bpm.getF04().getF05s(),
		        new Comparator<BBPMF05>() {
		            public int compare(BBPMF05 o1, BBPMF05 o2) {
		                return o1.getBpm05001().compareTo(o2.getBpm05001());
		            }
		        });
		
		for (int i=0; i<bpm.getF04().getF05s().size(); i++){
			/* 設定上一關卡關係*/
			if (i==0) 
				bpm.getF04().getF05s().get(i).setF05l(null); // 第一關
			else
				bpm.getF04().getF05s().get(i).setF05l(bpm.getF04().getF05s().get(i-1));
			
			/* 設定下一關卡關係*/
			if (i==(bpm.getF04().getF05s().size()-1)) 
				bpm.getF04().getF05s().get(i).setF05n(null); // 最後關卡
			else
				bpm.getF04().getF05s().get(i).setF05n(bpm.getF04().getF05s().get(i+1));
		}
	}
	private Integer chkgrade(BBPMF02 f02,Integer i01){
		Integer ret=i01,
				ii=Integer.parseInt(f02.getBpm02003());
		
		for (int i=i01;i<grade.length;i++){
			if (ii>=Integer.parseInt(grade[i][1])) ret=i;
		}
		return ret;
	}
	
	/**
	 * 加入一個 "上級階層" 關卡	 * 
	 * @param s01 : 關號 (系統用於 辨識與排序)
	 * @param i01 : 關卡屬性  : 1=簽核(同不同意, 退單)2=辦理(退單), 3=通知
	 * @param i02 : 起始 grade 
	 * @param i03 : 截止 grade
	 * @param s03 : 由那一個關號產生 (用於退件時 , 刪除關卡用:只要退件關卡小於等於時會刪除此關)
	 * @param s04 : 關卡說明
	 * @param bpm : 表單物件
	 */
	public void flowBoss(String s01,Integer i01,Integer i02,Integer i03,String s03,String s04,BBPMP01 bpm){
		BBPMF02 f02 = bpm.getF04().getF02(); // 填表人
		BBPMF02 t02 = f02; // 簽核人
		BBPMF01 f01 = f02.getDep();
		int cnt=0;
		for (int i=i02;i<=i03;i++){
			if (i!=chkgrade(f01.getBoss(),i)) continue;
			do {
				if (t02!=f01.getBoss())
					flowRole(s01+(cnt++),f01.getBoss().getBpm02001(),i01,s03,s04+" : "+f01.getBpm01003(),bpm);
				t02 = f01.getBoss();
				f01 = f01.getF01();
				if (null==f01) break;
				// System.out.println(i+":"+chkgrade(f01.getBoss(),i));
			} while(i>=chkgrade(f01.getBoss(),i));	
			if (null==f01) break;
		}
	}
	/**
	 * 加入一個 "人員" 關卡
	 * @param S01 : 關號 (系統用於 辨識與排序)
	 * @param S02 : 員工編號 (與 BPMF02 相關) 
	 * @param i01 : 關卡屬性  : 1=簽核(同不同意, 退單)2=辦理(退單), 3=通知
	 * @param S03 : 由那一個關號產生 (用於退件時 , 刪除關卡用:只要退件關卡小於等於時會刪除此關)
	 * @param S04 : 關卡說明
	 * @param bpm : 表單物件
	 */
	public void flowRole(String S01,String S02,Integer i01,String S03,String S04,BBPMP01 bpm){
		BBPMF05 f05 = new BBPMF05();
		BBPMF02 f02 = S_BPMF02.get001(S02);
		f05.setBpm05001(S01);
		f05.setBpm05002(S04);
		f05.setF02(f02);
		f05.setF07(null);
		f05.setBpm05003(i01);
		f05.setBpm05004(1);
		f05.setBpm05005(S03);
		// System.out.println(f05.getF02().getBpm02001());
		int i = chkf05(bpm,f05);
		if (i == -1) 
			bpm.getF04().getF05s().add(f05);
		else
			bpm.getF04().getF05s().set(i,f05);
	}
	/**
	 * 加入一個 "群組" 關卡
	 * @param S01 : 關號 (系統用於 辨識與排序)
	 * @param S02 : 流程群組 (與 BPMF07 相關) 
	 * @param I01 : 關卡屬性  : 1=簽核(同不同意, 退單)2=辦理(退單), 3=通知
	 * @param S03 : 由那一個關號產生 (用於退件時 , 刪除關卡用:只要退件關卡小於等於時會刪除此關)
	 * @param S04 : 關卡說明
	 * @param bpm : 表單物件
	 */
	public void flowGrup(String S01,String S02,Integer I01,String S03,String S04,BBPMP01 bpm){
		BBPMF05 f05 = new BBPMF05();
		BBPMF07 f07 = S_BPMF07.get001(S02);
		f05.setBpm05001(S01);
		f05.setBpm05002(S04);
		f05.setF02(null);
		f05.setF07(f07);
		f05.setBpm05003(I01);
		f05.setBpm05004(1);
		f05.setBpm05005(S03);
		// System.out.println(f05.getF07().getBpm07001());
		int i = chkf05(bpm,f05);
		if (i == -1) 
			bpm.getF04().getF05s().add(f05);
		else
			bpm.getF04().getF05s().set(i,f05);
	}
	/**
	 * 取得表單最後編號 
	 * @param id : 表單定義 ID (BPMF03)  
	 * @return 表單最後編號
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getLastSN(int id){
		int ret = 0;
		IDao D_Dao = S_BPMF02.getDao();
		Session ss01 = D_Dao.openSession();
		List f04s = ss01.createSQLQuery("select TOP (1) bpm04001 from BPMF04 Where f03_id = "+id+" Order by bpm04001 DESC")
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) 
				.list();
		D_Dao.closeSession(ss01);
		for (Map row : (List<Map>) f04s) ret = Integer.parseInt((String) row.get("bpm04001"));
		return String.format("%10d", ++ret).replace(' ', '0');
	}
	/**
	 * 建立表單時的進入動作(公用) , 於 Form - WorkFlow 啟動此行為
	 * @param f03 : 表單定義物件 (BPMF03) , 在 WorkFlow 階段傳入 
	 */
	public void createComeIn(BBPMF03 f03){
		BBPMF02 f02 = (BBPMF02) session.getAttribute("G_F02");
		request.setAttribute("f_formnm",f03.getBpm03001()+":"+f03.getBpm03003());
		request.setAttribute("f_formsn",f03.isBpm03101() ? "系統自動編號":"");
		request.setAttribute("f_f03",f03);
		request.setAttribute("f_tempath","bmp_"+f03.getBpm03001()+"_"+f02.getBpm02001()+"_"+KC_Pub.kc_Date2Str("yyyyMMddHHmmssSSS",KC_Pub.kc_now()));
	}
	
	/**
	 * 表單表單時的確認動作(公用) , 於 Form - JBPM000 啟動此行為  
	 * @param bpm : 表單物件
	 * @param obj : 前端傳來的 form 欄位資料(如 :表單意見)
	 * @param f03 : 表單定義物件 (BPMF03) , 在 JBPM000 階段傳入
	 */
	public BBPMF04 createEnter(BBPMP01 bpm,JSONObject obj,BBPMF03 f03){
        JSONObject o1 = (JSONObject) obj.get("bpm_main");
		String sn = o1.getString("bpm_f_formsn");
        
        bpm.getF04().setF03(f03);
        if (f03.isBpm03102()){
        	sn = getLastSN(bpm.getF04().getF03().getId());
        }
        	
        bpm.getF04().setBpm04001(sn);
        bpm.getF04().setBpm04002(o1.getString("bpm_f_subject"));
        bpm.getF04().setBpm04003(o1.getString("bpm_f_Important"));
        bpm.getF04().setBpm04004(new Date());
        bpm.getF04().setBpm04006(new Date());
        bpm.getF04().setBpm04101(1);
        bpm.getF04().setF02((BBPMF02) session.getAttribute("G_F02"));
        
        return null;
        
        // bpm.getF04().getF05s().
        // System.out.println(o1.getString("bpm_f_formid")+" -qqqqqqqq");
        // System.out.println(bpm.getF04().getF02().getBpm02001()+" -------");
        // System.out.println(bpm.getF03().getBpm03001()+" -------");
		// System.out.println(obj["bpm_main"]["bpm_f_formid"]);
		// KC_Pub.kc_json2obj(bpm.getF04(),"bpm_main",obj);
		// System.out.println("Hi Form000 ....");
	}
	
	/**
	 * 取消表單時的進入動作(公用) , 於 Form - WorkFlow 啟動此行為
	 * @param bpm : 表單物件 
	 */
	public void cancelComeIn(BBPMP01 bpm){
		BBPMF04 f04 = bpm.getF04();
		request.setAttribute("f_formnm",f04.getF03().getBpm03001()+":"+f04.getF03().getBpm03003());
		request.setAttribute("f_formsn",f04.getBpm04001());
		request.setAttribute("f_f04",f04);
		request.setAttribute("f_f03",f04.getF03());
	}
	public void approveComeIn(BBPMP01 bpm){
		String S01=null;
		BBPMF04 f04 = bpm.getF04();
		BBPMF02 f02 = (BBPMF02) session.getAttribute("G_F02");
		request.setAttribute("f_formnm",f04.getF03().getBpm03001()+":"+f04.getF03().getBpm03003());
		request.setAttribute("f_formsn",f04.getBpm04001());
		request.setAttribute("f_f04",f04);
		request.setAttribute("f_f03",f04.getF03());
		for (int i=0; i<f04.getF05().getF12s().size(); i++){
			if (f04.getF05().getF12s().get(i).getBpm12001()!=0) continue;
			for (int j=0;j<f04.getF05().getF12s().get(i).getF06s().size();j++){
				BBPMF06 f06 = f04.getF05().getF12s().get(i).getF06s().get(j);
				S01 = f06.getF02().getBpm02001();  
				if (S01.equals(f02.getBpm02001())){
					if (null==f06.getBpm06005()) f06.setBpm06005(new Date()); 
					f06.setBpm06006(new Date());
					f06.setBpm06007(f06.getBpm06007()+1);
				}
			}
		}
	}

	public void displayComeIn(BBPMP01 bpm){
		BBPMF04 f04 = bpm.getF04();
		request.setAttribute("f_formnm",f04.getF03().getBpm03001()+":"+f04.getF03().getBpm03003());
		request.setAttribute("f_formsn",f04.getBpm04001());
		request.setAttribute("f_f04",f04);
		request.setAttribute("f_f03",f04.getF03());
		for (int i=0;i< grade.length;i++){
			String ss = i+" : ";
			for (int j=0;j< grade[i].length;j++)
				ss = ss+","+grade[i][j];
			// System.out.println(ss);
		}	
		/*
		System.out.println("-1-"+request.getRequestURL());
		System.out.println("-2-"+request.getServerName());
		System.out.println("-3-"+request.getServerPort());
		System.out.println("-4-"+request.getContextPath());
		System.out.println("-5-"+request.getServletPath());
		*/
	}
	
	public void approveEnter(BBPMP01 bpm,JSONObject obj){
		bpm.getF04().setBpm04006(new Date());
	}
}
