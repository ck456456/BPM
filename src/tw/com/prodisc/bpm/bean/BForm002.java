package tw.com.prodisc.bpm.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Form002")
public class BForm002 extends BBPMP01{
	@Column(length=36)
	private String  form002000;	// 正航 GUID
	@Column(length=14)
	private String  form002001;	// 編號 ID 
	@Temporal(value = TemporalType.DATE)
	private Date form002002;   	// 日期Date
	@Column(length=36)
	private String  form002003;	// 類別編號 ClassGUID->CHI_OrderClass
	@Column(length=36)
	private String  form002101;	// 客戶編號 CustomerGUID->CHI_Customer
	@Column(length=36)
	private String  form002102;	// 收款客戶 BCustomerGUID->CHI_Customer
	@Column(length=36)
	private String  form002103;	// 幣別編號 CurrencyGUID->CHI_Currency
	private BigDecimal form002104; // 當時匯率 CurrencyRate
	@Column(length=15)
	private String  form002105;	// 統一編號 UniteNo
	private byte	form002106;	// 發票聯式 InvoiceType(0.二聯式,1.三聯式,2.其它(進)/免用(銷),3空白)
	private boolean	form002107;	// 產品單價 PriceTax(True.含稅,False.未稅)
	private byte	form002108;	// 課稅類別 TaxWay(0.應稅,1.零稅,2.免稅,3.免開,4.空白)
	@Column(length=30)
	private String  form002109;	// 聯絡人員 Contact
	@Column(length=20)
	private String  form002110;	// 聯絡電話 ContTelNo
	@Column(length=20)
	private String  form002111;	// 傳真電話 ContTelNo
	@Column(length=36)
	private String  form002112;	// 業務人員 UnderTakerGUID->CHI_PersonNew
	@Column(length=36)
	private String  form002113;	// 所屬部門 DepartmentGUID->CHI_Department
	
	public String getForm002000() {
		return form002000;
	}
	public void setForm002000(String form002000) {
		this.form002000 = form002000;
	}
	public String getForm002001() {
		return form002001;
	}
	public void setForm002001(String form002001) {
		this.form002001 = form002001;
	}
	public Date getForm002002() {
		return form002002;
	}
	public void setForm002002(Date form002002) {
		this.form002002 = form002002;
	}
	public String getForm002003() {
		return form002003;
	}
	public void setForm002003(String form002003) {
		this.form002003 = form002003;
	}
	public String getForm002101() {
		return form002101;
	}
	public void setForm002101(String form002101) {
		this.form002101 = form002101;
	}
	public String getForm002102() {
		return form002102;
	}
	public void setForm002102(String form002102) {
		this.form002102 = form002102;
	}
	public String getForm002103() {
		return form002103;
	}
	public void setForm002103(String form002103) {
		this.form002103 = form002103;
	}
	public BigDecimal getForm002104() {
		return form002104;
	}
	public void setForm002104(BigDecimal form002104) {
		this.form002104 = form002104;
	}
	public String getForm002105() {
		return form002105;
	}
	public void setForm002105(String form002105) {
		this.form002105 = form002105;
	}
	public byte getForm002106() {
		return form002106;
	}
	public void setForm002106(byte form002106) {
		this.form002106 = form002106;
	}
	public boolean isForm002107() {
		return form002107;
	}
	public void setForm002107(boolean form002107) {
		this.form002107 = form002107;
	}
	public byte getForm002108() {
		return form002108;
	}
	public void setForm002108(byte form002108) {
		this.form002108 = form002108;
	}
	public String getForm002109() {
		return form002109;
	}
	public void setForm002109(String form002109) {
		this.form002109 = form002109;
	}
	public String getForm002110() {
		return form002110;
	}
	public void setForm002110(String form002110) {
		this.form002110 = form002110;
	}
	public String getForm002111() {
		return form002111;
	}
	public void setForm002111(String form002111) {
		this.form002111 = form002111;
	}
	public String getForm002112() {
		return form002112;
	}
	public void setForm002112(String form002112) {
		this.form002112 = form002112;
	}
	public String getForm002113() {
		return form002113;
	}
	public void setForm002113(String form002113) {
		this.form002113 = form002113;
	}
	
}
