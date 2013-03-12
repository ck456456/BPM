package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tw.com.prodisc.bpm.bean.BSYSF01;
import tw.com.prodisc.bpm.dao.IDao;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class DaoRun {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		String[] MenuName = {
				"表 單 追 蹤 處 理",
				"電 子 表 單 清 單",
				"電 子 表 單 報 表",
				"管 理 程 式 清 單",
				"開 發 測 試 區"
		};
		
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource(
				"Spring.xml"));
		IDao D_Dao = (IDao) factory.getBean("dao");
		
		List<BSYSF01> O_MENU_Lst = new ArrayList<BSYSF01>();
		for (int i=0; i < MenuName.length; i++)
        {
			BSYSF01 O_MENU = new BSYSF01();
			O_MENU.setSys01002("mainmenu0"+(i+1));
			O_MENU.setSys01003("#");
			O_MENU.setSys01004(MenuName[i]);
			O_MENU.setSys01005("");
			O_MENU.setDateCreated(new Date());
			O_MENU_Lst.add(O_MENU);
			D_Dao.create(O_MENU);
        }
		
		for (int i=0; i < O_MENU_Lst.size(); i++){
			// System.out.println("O_MENU_Lst: " + O_MENU_Lst.get(i).getSys01004());	
		}
		

		/*
		List<BSYSF01> MENU_Lst = D_Dao.list("Select c from BSYSF01 c");
		for (BSYSF01 c : MENU_Lst) {
			System.out.println("Name: " + c.getSys01004());
		}
		
		B_USER O_USER = new B_USER();
		O_USER.setTKCUR004("武裝_实验室");
		O_USER.setDateCreated(new Date());
		D_Dao.create(O_USER);
		
		List<B_USER> USER_Lst = D_Dao.list("Select c from T_USER c");

		for (B_USER c : USER_Lst) {
			System.out.println("Name: " + c.getTKCUR004());
		}
		*/
      factory.destroySingletons();
	}
}
