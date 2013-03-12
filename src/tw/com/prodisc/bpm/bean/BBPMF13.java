package tw.com.prodisc.bpm.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "BPMF13")
public class BBPMF13 extends BSYSP01{
	
	private String  bpm13001;	// 組群
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date bpm13002;   	// 起始代理日期
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date bpm13003;   	// 截止代理日期
	private String  bpm13999;	// 代理表單id(1,5,9,.....)
	
	/**
	 *  原簽核人
	 */
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "f02_id1")
	@ForeignKey(name = "F02_13")
	private BBPMF02  src; 	 
	
	/**
	 * 代理人  
	 */
	@ManyToOne (cascade = {CascadeType.ALL})
	@JoinColumn(name = "f02_id2")
	@ForeignKey(name = "F13_02")
	private BBPMF02 f02 = null;
	
	public String getBpm13001() {
		return bpm13001;
	}
	public void setBpm13001(String bpm13001) {
		this.bpm13001 = bpm13001;
	}
	public Date getBpm13003() {
		return bpm13003;
	}
	public void setBpm13003(Date bpm13003) {
		this.bpm13003 = bpm13003;
	}
	public String getBpm13999() {
		return bpm13999;
	}
	public void setBpm13999(String bpm13999) {
		this.bpm13999 = bpm13999;
	}
	public BBPMF02 getSrc() {
		return src;
	}
	public void setSrc(BBPMF02 src) {
		this.src = src;
	}
	public BBPMF02 getF02() {
		return f02;
	}
	public void setF02(BBPMF02 f02) {
		this.f02 = f02;
	}
	public Date getBpm13002() {
		return bpm13002;
	}
	public void setBpm13002(Date bpm13002) {
		this.bpm13002 = bpm13002;
	}
}
