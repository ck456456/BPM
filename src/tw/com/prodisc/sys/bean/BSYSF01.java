package tw.com.prodisc.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import tw.com.prodisc.sys.bean.BSYSP01;

@Entity
@Table(name = "SYSF01")
public class BSYSF01 extends BSYSP01{
	private int     sys01001; 	 // Menu Name
	@Column(length=32)
	private String  sys01002; 	 // Menu Name
	private String  sys01003; 	 // Menu href
	@Column(length=32)
	private String  sys01004; 	 // Menu 顯示名稱
	@Column(length=32)
	private String  sys01005; 	 // Menu Class
	
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
