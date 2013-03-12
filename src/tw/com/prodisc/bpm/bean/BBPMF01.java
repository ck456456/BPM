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
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;


import tw.com.prodisc.bpm.bean.BSYSP01;

@Entity
@Table(name = "BPMF01")
@org.hibernate.annotations.Table(appliesTo = "BPMF01",
	indexes = { @Index(name = "index_999", columnNames = { "bpm01999" })
	})
public class BBPMF01 extends BSYSP01{
	// @Column(nullable = false, columnDefinition = "bool default 0")
	@Column(name = "bpm01001", nullable = false, columnDefinition = "bool default 0")
	private boolean bpm01001; 	 // 是否有效				DEP_DISABLE
	
	@Column(length=10)
	private String  bpm01002; 	 // 部門代號				DEP_CODE
	
	@Column(length=50)
	private String  bpm01003; 	 // 部門名稱				DEP_NAME
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f01_id")
	@ForeignKey(name = "bpmF01_boss") 
	private BBPMF01  f01; 	 	// 上階部門 
	
	@OneToMany(mappedBy = "f01")
	private List<BBPMF01> f01s = new ArrayList<BBPMF01>(); // 下階部門
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f02_id")
	@ForeignKey(name = "F01F02_boss") // 部門主管
	private BBPMF02  boss; 	 // 主管 UserID->BPMF02	DEP_CHIEF
	
	@Column(unique=true) 
	private int     bpm01999; 	 // 原 ID				DEP_NO
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dep")
	private List<BBPMF02>  users = new ArrayList<BBPMF02>();
	
	public List<BBPMF01> getF01s() {
		return f01s;
	}
	public void setF01s(List<BBPMF01> f01s) {
		this.f01s = f01s;
	}
	
	public BBPMF01 getF01() {
		return f01;
	}
	public void setF01(BBPMF01 f01) {
		this.f01 = f01;
	}
	public BBPMF02 getBoss() {
		return boss;
	}
	public void setBoss(BBPMF02 boss) {
		this.boss = boss;
	}
	public String getBpm01002() {
		return bpm01002;
	}
	public void setBpm01002(String bpm01002) {
		this.bpm01002 = bpm01002;
	}
	public String getBpm01003() {
		return bpm01003;
	}
	public void setBpm01003(String bpm01003) {
		this.bpm01003 = bpm01003;
	}
	public int getBpm01999() {
		return bpm01999;
	}
	public void setBpm01999(int bpm01999) {
		this.bpm01999 = bpm01999;
	}
	public List<BBPMF02> getUsers() {
		return users;
	}
	public void setUsers(List<BBPMF02> users) {
		this.users = users;
	}
	public boolean isBpm01001() {
		return bpm01001;
	}
	public void setBpm01001(boolean bpm01001) {
		this.bpm01001 = bpm01001;
	}
}
