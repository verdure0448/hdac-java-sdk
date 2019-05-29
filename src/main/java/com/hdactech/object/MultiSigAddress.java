package com.hdactech.object;

import java.util.List;

public class MultiSigAddress {
	Boolean isvalid = null;
	String address = null;
	Boolean ismine = null;
	Boolean iswatchonly = null;
	Boolean isscript = null;
	String script = null;
	String hex = null;
	List<String> addresses = null;
	int sigsrequired;
	String account = null;
	
	public MultiSigAddress() {}
	
	public MultiSigAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public Boolean getIsmine() {
		return ismine;
	}

	public void setIsmine(Boolean ismine) {
		this.ismine = ismine;
	}

	public Boolean getIswatchonly() {
		return iswatchonly;
	}

	public void setIswatchonly(Boolean iswatchonly) {
		this.iswatchonly = iswatchonly;
	}

	public Boolean getIsscript() {
		return isscript;
	}

	public void setIsscript(Boolean isscript) {
		this.isscript = isscript;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public int getSigsrequired() {
		return sigsrequired;
	}

	public void setSigsrequired(int sigsrequired) {
		this.sigsrequired = sigsrequired;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	
	
}
