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
@Table(name = "BPMF10")
public class BBPMF10 extends BSYSP01{
	
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "sf01_id")
	@ForeignKey(name = "F10_sf01") 
	private BSYSF01 sf01;
	
	@Temporal(value = TemporalType.DATE)
	private Date	bpm10003; // 有效日期
	private String	bpm10004; // 說明

	public BSYSF01 getSf01() {
		return sf01;
	}
	public void setSf01(BSYSF01 sf01) {
		this.sf01 = sf01;
	}
	public Date getBpm10003() {
		return bpm10003;
	}
	public void setBpm10003(Date bpm10003) {
		this.bpm10003 = bpm10003;
	}
	public String getBpm10004() {
		return bpm10004;
	}
	public void setBpm10004(String bpm10004) {
		this.bpm10004 = bpm10004;
	}
}
