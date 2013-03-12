package tw.com.prodisc.bpm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import tw.com.prodisc.bpm.bean.BSYSP01;

@Entity
@Table(name = "SYSF01")
public class BSYSF01 extends BSYSP01{
	private int     sys01001; 	 // 上階 Menu id
	@Column(length=32)
	private String  sys01002; 	 // 主選單記號
	private String  sys01003; 	 // Menu href
	@Column(length=32)
	private String  sys01004; 	 // Menu 顯示名稱
	private String  sys01005; 	 // Menu style
	@Column(length=4)
	private String  sys01006;    // 順序
	
	public String getSys01006() {
		return sys01006;
	}
	public void setSys01006(String sys01006) {
		this.sys01006 = sys01006;
	}
	public int getSys01001() {
		return sys01001;
	}
	public void setSys01001(int sys01001) {
		this.sys01001 = sys01001;
	}
	public String getSys01002() {
		return sys01002;
	}
	public void setSys01002(String sys01002) {
		this.sys01002 = sys01002;
	}
	public String getSys01003() {
		return sys01003;
	}
	public void setSys01003(String sys01003) {
		this.sys01003 = sys01003;
	}
	public String getSys01004() {
		return sys01004;
	}
	public void setSys01004(String sys01004) {
		this.sys01004 = sys01004;
	}
	public String getSys01005() {
		return sys01005;
	}
	public void setSys01005(String sys01005) {
		this.sys01005 = sys01005;
	}
}
