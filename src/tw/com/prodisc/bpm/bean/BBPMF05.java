package tw.com.prodisc.bpm.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "BPMF05")
public class BBPMF05 extends BSYSP01{
	
	@Column(length=5)
	private String  bpm05001;	// 關號
	
	private String  bpm05002;	// 關卡說明

	private Integer bpm05003;	// 關號屬性 1=簽核, 2=辦理,3=通知
	
	private Integer bpm05004;	// 簽核狀態 1=未完成, 2=同意, 3=不同意, 4=已抽單
	
	@Column(length=4)
	private String  bpm05005;	// 產生關卡之關號
	/*
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, targetEntity = BBPMF12.class)
	@JoinColumns(value = { @JoinColumn(name = "f05_id",referencedColumnName = "id")})
	@ForeignKey(name = "bpmF12_F05")
	*/
	// 流程歷史
	@OneToMany(mappedBy = "f05" , cascade = {CascadeType.ALL})
	private List<BBPMF12> f12s = new ArrayList<BBPMF12>();
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f05l_id")
	@ForeignKey(name = "bpm_f05l") 
	private BBPMF05 f05l;		// 上一關卡
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f05n_id")
	@ForeignKey(name = "bpm_f05n") 
	private BBPMF05 f05n;		// 下一關卡
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f07_id")
	@ForeignKey(name = "bpmF05_F07") 
	private BBPMF07 f07;		// 流程群組	BPMF07.id (ManyToOne)
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f02_id")
	@ForeignKey(name = "bpmF05_F02") 
	private BBPMF02 f02;		// 流程角色	BPMF02.id (ManyToOne)
	
	public String getBpm05001() {
		return bpm05001;
	}
	public void setBpm05001(String bpm05001) {
		this.bpm05001 = bpm05001;
	}
	public Integer getBpm05003() {
		return bpm05003;
	}
	public void setBpm05003(Integer bpm05003) {
		this.bpm05003 = bpm05003;
	}
	public Integer getBpm05004() {
		return bpm05004;
	}
	public void setBpm05004(Integer bpm05004) {
		this.bpm05004 = bpm05004;
	}
	public BBPMF07 getF07() {
		return f07;
	}
	public void setF07(BBPMF07 f07) {
		this.f07 = f07;
	}
	public BBPMF02 getF02() {
		return f02;
	}
	public void setF02(BBPMF02 f02) {
		this.f02 = f02;
	}
	public BBPMF05 getF05l() {
		return f05l;
	}
	public void setF05l(BBPMF05 f05l) {
		this.f05l = f05l;
	}
	public BBPMF05 getF05n() {
		return f05n;
	}
	public void setF05n(BBPMF05 f05n) {
		this.f05n = f05n;
	}
	public List<BBPMF12> getF12s() {
		return f12s;
	}
	public void setF12s(List<BBPMF12> f12s) {
		this.f12s = f12s;
	}
	public String getBpm05002() {
		return bpm05002;
	}
	public void setBpm05002(String bpm05002) {
		this.bpm05002 = bpm05002;
	}
	public String getBpm05005() {
		return bpm05005;
	}
	public void setBpm05005(String bpm05005) {
		this.bpm05005 = bpm05005;
	}
}
