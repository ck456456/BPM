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
@Table(name = "BPMF08")
public class BBPMF08 extends BSYSP01 {
	
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "f02_id")
	@ForeignKey(name = "F08_F02") 
	BBPMF02 f02;
	
	@Temporal(value = TemporalType.DATE)
	private Date	bpm08003; // 有效日期
	private String	bpm08004; // 說明
	
	public BBPMF02 getF02() {
		return f02;
	}

	public void setF02(BBPMF02 f02) {
		this.f02 = f02;
	}

	public Date getBpm08003() {
		return bpm08003;
	}

	public void setBpm08003(Date bpm08003) {
		this.bpm08003 = bpm08003;
	}

	public String getBpm08004() {
		return bpm08004;
	}

	public void setBpm08004(String bpm08004) {
		this.bpm08004 = bpm08004;
	}
}
