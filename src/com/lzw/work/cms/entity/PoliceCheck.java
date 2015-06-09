package com.lzw.work.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*@Scope("prototype")
@Component("policeCheck")
@Entity
@Table(name = "T_PoliceCheckYC")*/
public class PoliceCheck {
	
	@Column(name="InpOrderNo",length=50)
	private String inpOrderNo;
	
	@Column(name="License",length=30)
	private String license;
	
	@Column(name="LicType",length=30)
	private String licType;
	
	@Column(name="LicTypeYC",length=30)
	private String licTypeYC;
	
	@Column(name="BussinessType",length=100)
	private String bussinessType;
	
	@Column(name="BussinessTypeCode",length=20)
	private String bussinessTypeCode;//YC周警官
	
	@Column(name="Times")
	private Integer times;
	
	@Column(name="Time")
	private Date time;
	
	@Column(name="Checker",length=30)
	private String checker;
	
	@Column(name="Items",length=500)
	private String items;
	
	@Column(name="Remarks",length=500)
	private String remarks;
	
	@Column(name="CheckVehType",length=100)
	private String checkVehType;
	
	@Column(name="CheckVehTypeCode",length=20)
	private int checkVehTypeCode;
	
	@Column(name="Station",length=50)
	private String station;

	@Column(name="Rst1",length=30)
	private String rst1;
	@Column(name="Rst2",length=30)
	private String rst2;
	@Column(name="Rst3",length=30)
	private String rst3;
	@Column(name="Rst4",length=30)
	private String rst4;
	@Column(name="Rst5",length=30)
	private String rst5;
	@Column(name="Rst6",length=30)
	private String rst6;
	@Column(name="Rst7",length=30)
	private String rst7;
	@Column(name="Rst8",length=30)
	private String rst8;
	@Column(name="Rst9",length=30)
	private String rst9;
	@Column(name="Rst10",length=30)
	private String rst10;
	@Column(name="Rst11",length=30)
	private String rst11;
	@Column(name="Rst12",length=30)
	private String rst12;
	@Column(name="Rst13",length=30)
	private String rst13;
	@Column(name="Rst14",length=30)
	private String rst14;
	@Column(name="Rst15",length=30)
	private String rst15;
	@Column(name="Rst16",length=30)
	private String rst16;
	@Column(name="Rst17",length=30)
	private String rst17;
	@Column(name="Rst18",length=30)
	private String rst18;
	@Column(name="Rst19",length=30)
	private String rst19;
	@Column(name="Rst20",length=30)
	private String rst20;
	
	@Column(name="rst")
	private int rstTotal;
	
	@Column(name="UseType",length=20)
	private String useType;
	
	@Column(name="VIN",length=50)
	private String vin;
	
	
	public String getLicTypeYC() {
		return licTypeYC;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getRst20() {
		return rst20;
	}
	public void setRst20(String rst20) {
		this.rst20 = rst20;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public void setLicTypeYC(String licTypeYC) {
		this.licTypeYC = licTypeYC;
	}
	public String getRst1() {
		return rst1;
	}
	public void setRst1(String rst1) {
		this.rst1 = rst1;
	}
	public String getRst2() {
		return rst2;
	}
	public void setRst2(String rst2) {
		this.rst2 = rst2;
	}
	public String getRst3() {
		return rst3;
	}
	public void setRst3(String rst3) {
		this.rst3 = rst3;
	}
	public String getRst4() {
		return rst4;
	}
	public void setRst4(String rst4) {
		this.rst4 = rst4;
	}
	public String getRst5() {
		return rst5;
	}
	public void setRst5(String rst5) {
		this.rst5 = rst5;
	}
	public String getRst6() {
		return rst6;
	}
	public void setRst6(String rst6) {
		this.rst6 = rst6;
	}
	public String getRst7() {
		return rst7;
	}
	public void setRst7(String rst7) {
		this.rst7 = rst7;
	}
	public String getRst8() {
		return rst8;
	}
	public void setRst8(String rst8) {
		this.rst8 = rst8;
	}
	public String getRst9() {
		return rst9;
	}
	public void setRst9(String rst9) {
		this.rst9 = rst9;
	}
	public String getRst10() {
		return rst10;
	}
	public void setRst10(String rst10) {
		this.rst10 = rst10;
	}
	public String getRst11() {
		return rst11;
	}
	public void setRst11(String rst11) {
		this.rst11 = rst11;
	}
	public String getRst12() {
		return rst12;
	}
	public void setRst12(String rst12) {
		this.rst12 = rst12;
	}
	public String getRst13() {
		return rst13;
	}
	public void setRst13(String rst13) {
		this.rst13 = rst13;
	}
	public String getRst14() {
		return rst14;
	}
	public void setRst14(String rst14) {
		this.rst14 = rst14;
	}
	public String getRst15() {
		return rst15;
	}
	public void setRst15(String rst15) {
		this.rst15 = rst15;
	}
	public String getRst16() {
		return rst16;
	}
	public void setRst16(String rst16) {
		this.rst16 = rst16;
	}
	public String getRst17() {
		return rst17;
	}
	public void setRst17(String rst17) {
		this.rst17 = rst17;
	}
	public String getRst18() {
		return rst18;
	}
	public void setRst18(String rst18) {
		this.rst18 = rst18;
	}
	public String getRst19() {
		return rst19;
	}
	public void setRst19(String rst19) {
		this.rst19 = rst19;
	}
	public String getBussinessType() {
		return bussinessType;
	}
	public void setBussinessType(String bussinessType) {
		this.bussinessType = bussinessType;
	}
	
	public String getBussinessTypeCode() {
		return bussinessTypeCode;
	}
	public void setBussinessTypeCode(String bussinessTypeCode) {
		this.bussinessTypeCode = bussinessTypeCode;
	}
	public String getCheckVehType() {
		return checkVehType;
	}
	public void setCheckVehType(String checkVehType) {
		this.checkVehType = checkVehType;
	}
	public int getCheckVehTypeCode() {
		return checkVehTypeCode;
	}
	public void setCheckVehTypeCode(int checkVehTypeCode) {
		this.checkVehTypeCode = checkVehTypeCode;
	}
	public String getInpOrderNo() {
		return inpOrderNo;
	}
	public void setInpOrderNo(String inpOrderNo) {
		this.inpOrderNo = inpOrderNo;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getLicType() {
		return licType;
	}
	public void setLicType(String licType) {
		this.licType = licType;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getRstTotal() {
		return rstTotal;
	}
	public void setRstTotal(int rstTotal) {
		this.rstTotal = rstTotal;
	}
	
	
}
