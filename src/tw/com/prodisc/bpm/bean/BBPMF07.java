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
@Table(name = "BPMF07")
public class BBPMF07 extends BSYSP01 {
	@Column(length=10)
	private String  bpm07001;	// 群組代號
	@Column(length=20)
	private String  bpm07002;	// 群組簡稱
	@Column(length=40)
	private String  bpm07003;	// 群組名稱
	private String  bpm07004;	// 說明
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = BBPMF08.class, cascade = {CascadeType.ALL})
	@JoinColumns(value = { @JoinColumn(name = "f07_id",nullable=false,referencedColumnName = "id") })
	@ForeignKey(name = "F07_08")
	private List<BBPMF08> f08s = new ArrayList<BBPMF08>();

	public List<BBPMF08> getF08s() {
		return f08s;
	}
	public void setF08s(List<BBPMF08> f08s) {
		this.f08s = f08s;
	}
	
	public String getBpm07001() {
		return bpm07001;
	}
	public void setBpm07001(String bpm07001) {
		this.bpm07001 = bpm07001;
	}
	public String getBpm07002() {
		return bpm07002;
	}
	public void setBpm07002(String bpm07002) {
		this.bpm07002 = bpm07002;
	}
	public String getBpm07003() {
		return bpm07003;
	}
	public void setBpm07003(String bpm07003) {
		this.bpm07003 = bpm07003;
	}
	public String getBpm07004() {
		return bpm07004;
	}
	public void setBpm07004(String bpm07004) {
		this.bpm07004 = bpm07004;
	}

}
