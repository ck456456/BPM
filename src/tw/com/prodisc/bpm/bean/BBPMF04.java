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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;


import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

@Entity
@Table(name = "BPMF04" , uniqueConstraints= 
	{@UniqueConstraint(columnNames ={"f03_id","bpm04001"})}
)
@org.hibernate.annotations.Table(appliesTo = "BPMF04",
		   indexes = {@Index(name="bpm04001", columnNames = {"bpm04001"})}
		)
public class BBPMF04 extends BSYSP01{
	@Column(length=20)
	private String  bpm04001;	// 表單單號
	private String  bpm04002;	// 主旨
	@Column(length=1)
	private String  bpm04003;	// 重要性      0=低, 1=普通, 2=高
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date bpm04004;   	// 填表日期/時間
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date bpm04005;   	// 結案日期/時間
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date bpm04006;   	// 待簽收件日期/時間
	private Integer bpm04101;	// 簽核結果 1=未完成, 2=同意,   3=不同意, 4=已抽單
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "f02_id")
	@ForeignKey(name = "bpmF04_F02") 
	private BBPMF02 f02;		// 填表人
	
	/* 有效簽核流程 */
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, targetEntity = BBPMF05.class)
	@JoinColumns(value = { @JoinColumn(name = "f04_id",referencedColumnName = "id")})
	@ForeignKey(name = "bpmF05_F04")
	private List<BBPMF05> f05s = new ArrayList<BBPMF05>();
	
	/* 歷史簽核流程 */
	@OneToMany(mappedBy = "f04" , cascade = {CascadeType.ALL})
	private List<BBPMF12> f12s = new ArrayList<BBPMF12>();
	
	/* 目前待簽關卡 */
	@OneToOne (cascade = {CascadeType.ALL})
	@JoinColumn(name = "f05_id")//, unique=true)
	@ForeignKey(name = "bpmF04_F05")
	private BBPMF05 f05 = null;
	
	/* 目前待簽人員 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable( 
		name="F04_F02",
		joinColumns 		= @JoinColumn(name = "f04_id",referencedColumnName = "id"),
		inverseJoinColumns 	= @JoinColumn(name = "f02_id",referencedColumnName = "id"))
	private List<BBPMF02> f02s = new ArrayList<BBPMF02>();
	
	/* 表單定義 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "f03_id")
	// @Index(name="f03_001", columnNames = {"f03_id","bpm04001"})
	@ForeignKey(name = "bpmF04_F03")
	private BBPMF03 f03;
	
	public List<BBPMF05> getF05s() {
		return f05s;
	}
	public void setF05s(List<BBPMF05> f05s) {
		this.f05s = f05s;
	}
	public Integer getBpm04101() {
		return bpm04101;
	}
	public void setBpm04101(Integer bpm04101) {
		this.bpm04101 = bpm04101;
	}
	public BBPMF02 getF02() {
		return f02;
	}
	public void setF02(BBPMF02 f02) {
		this.f02 = f02;
	}
	public String getBpm04001() {
		return bpm04001;
	}
	public void setBpm04001(String bpm04001) {
		this.bpm04001 = bpm04001;
	}
	public String getBpm04002() {
		return bpm04002;
	}
	public void setBpm04002(String bpm04002) {
		this.bpm04002 = bpm04002;
	}
	public String getBpm04003() {
		return bpm04003;
	}
	public void setBpm04003(String bpm04003) {
		this.bpm04003 = bpm04003;
	}
	public Date getBpm04004() {
		return bpm04004;
	}
	public void setBpm04004(Date bpm04004) {
		this.bpm04004 = bpm04004;
	}
	public Date getBpm04005() {
		return bpm04005;
	}
	public void setBpm04005(Date bpm04005) {
		this.bpm04005 = bpm04005;
	}
	public BBPMF05 getF05() {
		return f05;
	}
	public void setF05(BBPMF05 f05) {
		this.f05 = f05;
	}
	public List<BBPMF02> getF02s() {
		return f02s;
	}
	public void setF02s(List<BBPMF02> f02s) {
		this.f02s = f02s;
	}
	public BBPMF03 getF03() {
		return f03;
	}
	public void setF03(BBPMF03 f03) {
		this.f03 = f03;
	}
	public Date getBpm04006() {
		return bpm04006;
	}
	public void setBpm04006(Date bpm04006) {
		this.bpm04006 = bpm04006;
	}
	public List<BBPMF12> getF12s() {
		return f12s;
	}
	public void setF12s(List<BBPMF12> f12s) {
		this.f12s = f12s;
	}
}
