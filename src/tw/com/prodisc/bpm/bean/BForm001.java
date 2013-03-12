package tw.com.prodisc.bpm.bean;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Form001")
public class BForm001 extends BBPMP01{
	private String  	form001001; // 正航客戶ID
	private String  	form001002;	// 申請類別
	private BigDecimal 	form001101;	// 預計下單金額 BigDecimal
	private BigDecimal 	form001102;	// 申請授信額度 BigDecimal
	private BigDecimal 	form001103;	// 最近三個月每月交易額度 BigDecimal
	private BigDecimal 	form001104;	// 未來三個月每月交易額度 BigDecimal
	private BigDecimal 	form001105;	// 建議授信額度 BigDecimal
	private boolean 	form001201;	// 營利事業登記證影本	
	private boolean 	form001202;	// 公司執照影本	
	private boolean 	form001203;	// 工廠登記證影本
	private boolean 	form001204; // 客戶資料表	
	private boolean 	form001205;	// 客戶往來銀行資料	
	private boolean 	form001206;	// 客戶甲存帳號徵信記錄
	private boolean 	form001207;	// 最近三次營業稅單401表
	private boolean 	form001208;	// 近三年財務報表	
	private boolean 	form001209;	// 委外徵信資料
	private String  	form001901;	// 申請人員說明
	private String  	form001902;	// 財會人員說明
	private String  	form001903;	// 申請時的客戶說明

	public String getForm001901() {
		return form001901;
	}
	public void setForm001901(String form001901) {
		this.form001901 = form001901;
	}
	public String getForm001902() {
		return form001902;
	}
	public void setForm001902(String form001902) {
		this.form001902 = form001902;
	}
	public String getForm001903() {
		return form001903;
	}
	public void setForm001903(String form001903) {
		this.form001903 = form001903;
	}
	public String getForm001002() {
		return form001002;
	}
	public void setForm001002(String form001002) {
		this.form001002 = form001002;
	}
	public BigDecimal getForm001101() {
		return form001101;
	}
	public void setForm001101(BigDecimal form001101) {
		this.form001101 = form001101;
	}
	public BigDecimal getForm001102() {
		return form001102;
	}
	public void setForm001102(BigDecimal form001102) {
		this.form001102 = form001102;
	}
	public BigDecimal getForm001103() {
		return form001103;
	}
	public void setForm001103(BigDecimal form001103) {
		this.form001103 = form001103;
	}
	public BigDecimal getForm001104() {
		return form001104;
	}
	public void setForm001104(BigDecimal form001104) {
		this.form001104 = form001104;
	}
	public boolean isForm001201() {
		return form001201;
	}
	public void setForm001201(boolean form001201) {
		this.form001201 = form001201;
	}
	public boolean isForm001202() {
		return form001202;
	}
	public void setForm001202(boolean form001202) {
		this.form001202 = form001202;
	}
	public boolean isForm001203() {
		return form001203;
	}
	public void setForm001203(boolean form001203) {
		this.form001203 = form001203;
	}
	public boolean isForm001204() {
		return form001204;
	}
	public void setForm001204(boolean form001204) {
		this.form001204 = form001204;
	}
	public boolean isForm001205() {
		return form001205;
	}
	public void setForm001205(boolean form001205) {
		this.form001205 = form001205;
	}
	public boolean isForm001206() {
		return form001206;
	}
	public void setForm001206(boolean form001206) {
		this.form001206 = form001206;
	}
	public boolean isForm001207() {
		return form001207;
	}
	public void setForm001207(boolean form001207) {
		this.form001207 = form001207;
	}
	public boolean isForm001208() {
		return form001208;
	}
	public void setForm001208(boolean form001208) {
		this.form001208 = form001208;
	}
	public boolean isForm001209() {
		return form001209;
	}
	public void setForm001209(boolean form001209) {
		this.form001209 = form001209;
	}
	public String getForm001001() {
		return form001001;
	}
	public void setForm001001(String form001001) {
		this.form001001 = form001001;
	}
	public BigDecimal getForm001105() {
		return form001105;
	}
	public void setForm001105(BigDecimal form001105) {
		this.form001105 = form001105;
	}	 

}
