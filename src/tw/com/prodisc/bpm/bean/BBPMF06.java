package tw.com.prodisc.bpm.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "BPMF06")
public class BBPMF06 extends BSYSP01{
	private Integer bpm06001;	/* 簽核結果	1=未簽核,   2=同意,   3=不同意,
											4=會辦完成, 5=已撤簽, 6=執行成功,
											7=執行失敗, 8=已通知, 9=已抽單
											10=他人已簽核 11=他人已撤簽 */
	private String  bpm06002;	// 簽核意見
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date bpm06003;   	// 收件日期/時間
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date bpm06004;   	// 簽核日期/時間
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date bpm06005;   	// 初次讀取日期/時間
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date bpm06006;   	// 最近讀取日期/時間
	@Column(nullable = false, columnDefinition = "int default 0")
	private int bpm06007 = 0;   	// 讀取次數
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f12_id")
	@ForeignKey(name = "bpmF06_F12") 
	private BBPMF12 f12;		// 簽核節點		BPMF12.id (ManyToOne)
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f02_id")
	@ForeignKey(name = "bpmF06_F02") 
	private BBPMF02 f02;		// 應簽核人 	BPMF02.id (ManyToOne)
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f0201_id")
	@ForeignKey(name = "bpmF06_F0201") 
	private BBPMF02 f0201;		// 實際簽核人 	BPMF02.id (ManyToOne)
	
	public BBPMF02 getF0201() {
		return f0201;
	}

	public void setF0201(BBPMF02 f0201) {
		this.f0201 = f0201;
	}

	public Integer getBpm06001() {
		return bpm06001;
	}

	public void setBpm06001(Integer bpm06001) {
		this.bpm06001 = bpm06001;
	}

	public String getBpm06002() {
		return bpm06002;
	}

	public void setBpm06002(String bpm06002) {
		this.bpm06002 = bpm06002;
	}

	public Date getBpm06003() {
		return bpm06003;
	}

	public void setBpm06003(Date bpm06003) {
		this.bpm06003 = bpm06003;
	}

	public Date getBpm06004() {
		return bpm06004;
	}

	public void setBpm06004(Date bpm06004) {
		this.bpm06004 = bpm06004;
	}

	public Date getBpm06005() {
		return bpm06005;
	}

	public void setBpm06005(Date bpm06005) {
		this.bpm06005 = bpm06005;
	}

	public Date getBpm06006() {
		return bpm06006;
	}

	public void setBpm06006(Date bpm06006) {
		this.bpm06006 = bpm06006;
	}

	public BBPMF02 getF02() {
		return f02;
	}

	public void setF02(BBPMF02 f02) {
		this.f02 = f02;
	}

	public BBPMF12 getF12() {
		return f12;
	}

	public void setF12(BBPMF12 f12) {
		this.f12 = f12;
	}

	public int getBpm06007() {
		return bpm06007;
	}

	public void setBpm06007(int bpm06007) {
		this.bpm06007 = bpm06007;
	}

}
