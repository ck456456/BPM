package tw.com.prodisc.bpm.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "BPMF12")
public class BBPMF12 extends BSYSP01{
	private Integer bpm12001;	//是否為有效簽核 0=有效 1=無效
	
	@Column(length=5)
	private String  bpm12002;	// 關號
	
	private String  bpm12003;	// 關卡說明
	
	private Integer bpm12004;	// 關號屬性 1=簽核, 2=通知
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f04_id")
	@ForeignKey(name = "bpmF12_F04") 
	private BBPMF04 f04;		
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f05_id")
	@ForeignKey(name = "bpmF12_F05") 
	private BBPMF05 f05;		// 啟簽位置 	BPMF05.id (ManyToOne)
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f07_id")
	@ForeignKey(name = "bpmF12_F07") 
	private BBPMF07 f07;		// 簽核組群 	BPMF07.id (ManyToOne)
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f12l_id")
	@ForeignKey(name = "bpm_f12l") 
	private BBPMF12 f12l;		// 上一結點
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f12n_id")
	@ForeignKey(name = "bpm_f12n") 
	private BBPMF12 f12n;		// 下一結點
	
	@OneToMany(mappedBy = "f12", cascade = {CascadeType.ALL})
	private List<BBPMF06> f06s = new ArrayList<BBPMF06>();
	
	public BBPMF07 getF07() {
		return f07;
	}

	public void setF07(BBPMF07 f07) {
		this.f07 = f07;
	}

	public Integer getBpm12001() {
		return bpm12001;
	}

	public void setBpm12001(Integer bpm12001) {
		this.bpm12001 = bpm12001;
	}

	public String getBpm12002() {
		return bpm12002;
	}

	public void setBpm12002(String bpm12002) {
		this.bpm12002 = bpm12002;
	}

	public String getBpm12003() {
		return bpm12003;
	}

	public void setBpm12003(String bpm12003) {
		this.bpm12003 = bpm12003;
	}

	public BBPMF12 getF12l() {
		return f12l;
	}

	public void setF12l(BBPMF12 f12l) {
		this.f12l = f12l;
	}

	public BBPMF12 getF12n() {
		return f12n;
	}

	public void setF12n(BBPMF12 f12n) {
		this.f12n = f12n;
	}

	public List<BBPMF06> getF06s() {
		return f06s;
	}

	public void setF06s(List<BBPMF06> f06s) {
		this.f06s = f06s;
	}

	public BBPMF05 getF05() {
		return f05;
	}

	public void setF05(BBPMF05 f05) {
		this.f05 = f05;
	}

	public Integer getBpm12004() {
		return bpm12004;
	}

	public void setBpm12004(Integer bpm12004) {
		this.bpm12004 = bpm12004;
	}

	public BBPMF04 getF04() {
		return f04;
	}

	public void setF04(BBPMF04 f04) {
		this.f04 = f04;
	}
}
