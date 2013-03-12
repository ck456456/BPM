package tw.com.prodisc.bpm.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "BPMF09")
public class BBPMF09 extends BSYSP01 {
	@Column(length=10)
	private String  bpm09001;	// 群組代號
	@Column(length=20)
	private String  bpm09002;	// 群組簡稱
	@Column(length=40)
	private String  bpm09003;	// 群組名稱
	private String  bpm09004;	// 說明
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = BBPMF10.class, cascade = {CascadeType.ALL})
	@JoinColumns(value = { @JoinColumn(name = "f09_id",nullable=false,referencedColumnName = "id") })
	@ForeignKey(name = "F09_F10")
	private List<BBPMF10> f10s = new ArrayList<BBPMF10>();

	public String getBpm09001() {
		return bpm09001;
	}

	public void setBpm09001(String bpm09001) {
		this.bpm09001 = bpm09001;
	}

	public String getBpm09002() {
		return bpm09002;
	}

	public void setBpm09002(String bpm09002) {
		this.bpm09002 = bpm09002;
	}

	public String getBpm09003() {
		return bpm09003;
	}

	public void setBpm09003(String bpm09003) {
		this.bpm09003 = bpm09003;
	}

	public String getBpm09004() {
		return bpm09004;
	}

	public void setBpm09004(String bpm09004) {
		this.bpm09004 = bpm09004;
	}

	public List<BBPMF10> getF10s() {
		return f10s;
	}

	public void setF10s(List<BBPMF10> f10s) {
		this.f10s = f10s;
	}
}
