package tw.com.prodisc.bpm.listener;
//下面就Servlet偵聽器結合Java定時器來講述整個實現過程。要運用Servlet偵聽器需要實現javax.servlet.ServletContextListener接口，同時實現它的contextInitialized(ServletContextEvent event)和contextDestroyed(ServletContextEvent event)兩個接口函數。考慮定時器有個建立和銷毀的過程，看了前面兩個接口函數，就不容置疑的把建立的過程置入contextInitialized，把銷毀的過程置入contextDestroyed了。

import java.util.Timer;//定時器類  
// import tw.com.prodisc.bpm.listener.ExportHistoryBean;
import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener; 
public class Schedule implements ServletContextListener{
	private Timer timer = null;  
	public void contextInitialized(ServletContextEvent event)  
	{  
		event.getServletContext().log("定時器已啟動");//添加日誌，可在tomcat日誌中查看到
		// 在這裡初始化監聽器，在tomcat啟動的時候監聽器啟動，可以在這裡實現定時器功能
		timer = new Timer(true);  
		// timer.schedule(new ExportHistoryBean(event.getServletContext()),0,5*1000);//調用exportHistoryBean，0表示任務無延遲，5*1000表示每隔5秒執行任務，60*60*1000表示一個小時。
		/* 將 eHR 資料倒入 BPM */
		// timer.schedule(new Listen_001(event.getServletContext()),0,5*1000);
		event.getServletContext().log("已經添加任務");  
	}  
	public void contextDestroyed(ServletContextEvent event)  
	{ 
		//在這里關閉監聽器，所以在這裡銷毀定時器。  
		timer.cancel();  
		event.getServletContext().log("定時器銷毀");  
	}  
}
