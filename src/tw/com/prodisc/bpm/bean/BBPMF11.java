package tw.com.prodisc.bpm.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "BPMF11")
public class BBPMF11 extends BSYSP01{

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "f09_id")
	@ForeignKey(name = "F11_09") 
	BBPMF09 f09;
	
	@Temporal(value = TemporalType.DATE)
	private Date	bpm11003; // 有效日期
	private String	bpm11004; // 說明
	
	public BBPMF09 getF09() {
		return f09;
	}
	public void setF09(BBPMF09 f09) {
		this.f09 = f09;
	}
	public Date getBpm11003() {
		return bpm11003;
	}
	public void setBpm11003(Date bpm11003) {
		this.bpm11003 = bpm11003;
	}
	public String getBpm11004() {
		return bpm11004;
	}
	public void setBpm11004(String bpm11004) {
		this.bpm11004 = bpm11004;
	}
}
