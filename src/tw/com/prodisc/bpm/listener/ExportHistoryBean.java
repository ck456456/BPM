package tw.com.prodisc.bpm.listener;

import java.util.Calendar;  
import java.util.TimerTask;
import javax.servlet.ServletContext;

public class ExportHistoryBean extends TimerTask{
	private static final int C_SCHEDULE_HOUR = 17;  
	  private static boolean isRunning = false;  
	  private ServletContext context = null;   
	  public ExportHistoryBean(ServletContext context)  
	  {  
	      this.context = context;   
	  }  
	  
	  public void run()  
	  {  
	    Calendar c = Calendar.getInstance();  
	    if(!isRunning){  
	    	context.log(C_SCHEDULE_HOUR+":"+c.get(Calendar.HOUR_OF_DAY));
	      if(C_SCHEDULE_HOUR == c.get(Calendar.HOUR_OF_DAY))  
	      {  
	        isRunning = true;  
	        context.log("開始執行指定任務");  
	        /*-------------------開始保存當日曆史記錄 ------------------- 
	        	在這裡編寫自己的功能，例：
	        	File file = new File("temp");
	        	file.mkdir();
	        	啟動tomcat，可以發現在tomcat根目錄下，會自動創建temp文件夾
	          -------------------結束-------------------*/	        
	        isRunning = false;  
	        context.log("指定任務執行結束");  
	      }
	    }
      else
      {  
        context.log("上一次任務執行還未結束 : "+isRunning);
      }
	 }  
}
