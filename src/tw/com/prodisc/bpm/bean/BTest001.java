package tw.com.prodisc.bpm.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Test001")
public class BTest001 extends BBPMP01{
	private String  	test001001;	 
	private int     	test001002; 
	private Integer 	test001003; 
	private boolean 	test001004; 
	private Date 		test001005; 
	private float 		test001006; 
	private double		test001007;
	private long		test001008;
	private byte		test001009;
	private short		test001010;
	private char		test001011;
	private BigDecimal 	test001012;
	private BigInteger 	test001013;
	
	public String getTest001001() {
		return test001001;
	}
	public void setTest001001(String test001001) {
		this.test001001 = test001001;
	}
	public int getTest001002() {
		return test001002;
	}
	public void setTest001002(int test001002) {
		this.test001002 = test001002;
	}
	public Integer getTest001003() {
		return test001003;
	}
	public void setTest001003(Integer test001003) {
		this.test001003 = test001003;
	}
	public boolean isTest001004() {
		return test001004;
	}
	public void setTest001004(boolean test001004) {
		this.test001004 = test001004;
	}
	public Date getTest001005() {
		return test001005;
	}
	public void setTest001005(Date test001005) {
		this.test001005 = test001005;
	}
	public float getTest001006() {
		return test001006;
	}
	public void setTest001006(float test001006) {
		this.test001006 = test001006;
	}
	public double getTest001007() {
		return test001007;
	}
	public void setTest001007(double test001007) {
		this.test001007 = test001007;
	}
	public long getTest001008() {
		return test001008;
	}
	public void setTest001008(long test001008) {
		this.test001008 = test001008;
	}
	public byte getTest001009() {
		return test001009;
	}
	public void setTest001009(byte test001009) {
		this.test001009 = test001009;
	}
	public short getTest001010() {
		return test001010;
	}
	public void setTest001010(short test001010) {
		this.test001010 = test001010;
	}
	public char getTest001011() {
		return test001011;
	}
	public void setTest001011(char test001011) {
		this.test001011 = test001011;
	}
	public BigDecimal getTest001012() {
		return test001012;
	}
	public void setTest001012(BigDecimal test001012) {
		this.test001012 = test001012;
	}
	public BigInteger getTest001013() {
		return test001013;
	}
	public void setTest001013(BigInteger test001013) {
		this.test001013 = test001013;
	}

}
