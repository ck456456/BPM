package tw.com.prodisc.bpm.bean;


import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public class BBPMP01 extends BSYSP01{

	@OneToOne (fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "f04_id", unique=true)
	private BBPMF04 f04 = new BBPMF04();
	
	public BBPMF04 getF04() {
		return f04;
	}

	public void setF04(BBPMF04 f04) {
		this.f04 = f04;
	}

}
