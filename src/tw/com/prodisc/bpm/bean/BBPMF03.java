package tw.com.prodisc.bpm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BPMF03")
public class BBPMF03 extends BSYSP01{
	@Column(length=20,unique=true)
	private String  bpm03001; 	 // 表單代號

	@Column(length=20)
	private String  bpm03002; 	 // 表單簡稱
	
	@Column(length=50)
	private String  bpm03003; 	 // 表單名稱
	
	// @Column(columnDefinition="boolean default 1")
	private boolean  bpm03101; 	 // 填表人可否抽單?
	// @Column(columnDefinition="boolean default 1")
	private boolean  bpm03102; 	 // 是否自動編號?
	private boolean  bpm03103; 	 // 是否有附件
	private String  bpm03998; 	 // 表單程式位置 Driver Class Name
	private String  bpm03999; 	 // 說明
	
	public String getBpm03998() {
		return bpm03998;
	}
	public void setBpm03998(String bpm03998) {
		this.bpm03998 = bpm03998;
	}
	public String getBpm03001() {
		return bpm03001;
	}
	public void setBpm03001(String bpm03001) {
		this.bpm03001 = bpm03001;
	}
	public String getBpm03002() {
		return bpm03002;
	}
	public void setBpm03002(String bpm03002) {
		this.bpm03002 = bpm03002;
	}
	public String getBpm03003() {
		return bpm03003;
	}
	public void setBpm03003(String bpm03003) {
		this.bpm03003 = bpm03003;
	}
	public boolean isBpm03101() {
		return bpm03101;
	}
	public void setBpm03101(boolean bpm03101) {
		this.bpm03101 = bpm03101;
	}
	public String getBpm03999() {
		return bpm03999;
	}
	public void setBpm03999(String bpm03999) {
		this.bpm03999 = bpm03999;
	}
	public boolean isBpm03102() {
		return bpm03102;
	}
	public void setBpm03102(boolean bpm03102) {
		this.bpm03102 = bpm03102;
	}
	public boolean isBpm03103() {
		return bpm03103;
	}
	public void setBpm03103(boolean bpm03103) {
		this.bpm03103 = bpm03103;
	}
}
