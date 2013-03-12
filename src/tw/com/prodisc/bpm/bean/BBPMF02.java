package tw.com.prodisc.bpm.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

import tw.com.prodisc.bpm.bean.BSYSP01;

@Entity
@Table(name = "BPMF02")
@org.hibernate.annotations.Table(appliesTo = "BPMF02",
indexes = { @Index(name = "index_001", columnNames = { "bpm02001" })
})
public class BBPMF02 extends BSYSP01{
	@Column(length=20,unique=true)
	private String  bpm02001; 	 // 員工代號				EMPID
	@Column(length=50)
	private String  bpm02002; 	 // 員工姓名				HECNAME
	@Column(length=3)
	private String  bpm02003; 	 // 職級  				AGENT
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date bpm02004;   	 // 離職日
	@Column(length=20)
	private String  bpm02101; 	 // 轉為此員工進入系統        EMPID
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f01_id")
	@ForeignKey(name = "bpmF01_F02") 
	private BBPMF01  dep; 	 	// 所屬部門 ->BPMF01		DEP_NO
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f02_AGENT")
	@ForeignKey(name = "bpmF02_AGENT") 
	private BBPMF02 f02_AGENT; 	 // 離職代理人
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f02_Boss")
	@ForeignKey(name = "bpmF02_Boss") 
	private BBPMF02 f02_Boss; 	 // 上級

	/**
	 * 權限族群關係
	 */
	@OneToMany(fetch = FetchType.LAZY, targetEntity = BBPMF11.class, cascade = {CascadeType.ALL})
	@JoinColumns(value = { @JoinColumn(name = "f02_id",nullable=false,referencedColumnName = "id") })
	@ForeignKey(name = "F02_11")
	private List<BBPMF11> f11s = new ArrayList<BBPMF11>();
	
	/**
	 * 被需求為離職代理人
	 */
	@OneToMany(mappedBy = "f02_AGENT")
	private List<BBPMF02> f02_AGENTs = new ArrayList<BBPMF02>();
	
	/**
	 * 代理人
	 */
	@OneToMany(mappedBy = "src")
	private List<BBPMF13> f13s = new ArrayList<BBPMF13>();
	
	/**
	 * 被需求為代理人
	 */
	@OneToMany(mappedBy = "f02")
	private List<BBPMF13> f1301s = new ArrayList<BBPMF13>();
	
	/**
	 * 待簽核表單
	 */
	@ManyToMany(mappedBy = "f02s")
	private List<BBPMF04> f04s = new ArrayList<BBPMF04>();
	
	public List<BBPMF02> getF02_AGENTs() {
		return f02_AGENTs;
	}
	public void setF02_AGENTs(List<BBPMF02> f02_AGENTs) {
		this.f02_AGENTs = f02_AGENTs;
	}
	public BBPMF02 getF02_AGENT() {
		return f02_AGENT;
	}
	public void setF02_AGENT(BBPMF02 f02_AGENT) {
		this.f02_AGENT = f02_AGENT;
	}
	public BBPMF02 getF02_Boss() {
		return f02_Boss;
	}
	public void setF02_Boss(BBPMF02 f02_Boss) {
		this.f02_Boss = f02_Boss;
	}
	public Date getBpm02004() {
		return bpm02004;
	}
	public void setBpm02004(Date bpm02004) {
		this.bpm02004 = bpm02004;
	}
	public List<BBPMF13> getF1301s() {
		return f1301s;
	}
	public void setF1301s(List<BBPMF13> f1301s) {
		this.f1301s = f1301s;
	}
	public List<BBPMF13> getF13s() {
		return f13s;
	}
	public void setF13s(List<BBPMF13> f13s) {
		this.f13s = f13s;
	}
	public List<BBPMF11> getF11s() {
		return f11s;
	}
	public void setF11s(List<BBPMF11> f11s) {
		this.f11s = f11s;
	}
	public String getBpm02001() {
		return bpm02001;
	}
	public void setBpm02001(String bpm02001) {
		this.bpm02001 = bpm02001;
	}
	public String getBpm02002() {
		return bpm02002;
	}
	public void setBpm02002(String bpm02002) {
		this.bpm02002 = bpm02002;
	}
	public String getBpm02003() {
		return bpm02003;
	}
	public void setBpm02003(String bpm02003) {
		this.bpm02003 = bpm02003;
	}
	public String getBpm02101() {
		return bpm02101;
	}
	public void setBpm02101(String bpm02101) {
		this.bpm02101 = bpm02101;
	}
	public BBPMF01 getDep() {
		return dep;
	}
	public void setDep(BBPMF01 dep) {
		this.dep = dep;
	}
	public List<BBPMF04> getF04s() {
		return f04s;
	}
	public void setF04s(List<BBPMF04> f04s) {
		this.f04s = f04s;
	}
}
