/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class AddressInfo {

    Boolean isvalid = null;
    Boolean ismine = null;
    Boolean iswatchonly = null;
    Boolean isscript = null;
    String pubkey = null;
    Boolean iscompressed = null;
    String account = null;
    
	/**
	 * @return the isvalid
	 */
	public boolean isIsvalid() {
		return isvalid;
	}
	/**
	 * @param isvalid the isvalid to set
	 */
	public void setIsvalid(boolean isvalid) {
		this.isvalid = isvalid;
	}
	/**
	 * @return the ismine
	 */
	public boolean isIsmine() {
		return ismine;
	}
	/**
	 * @param ismine the ismine to set
	 */
	public void setIsmine(boolean ismine) {
		this.ismine = ismine;
	}
	/**
	 * @return the iswatchonly
	 */
	public boolean isIswatchonly() {
		return iswatchonly;
	}
	/**
	 * @param iswatchonly the iswatchonly to set
	 */
	public void setIswatchonly(boolean iswatchonly) {
		this.iswatchonly = iswatchonly;
	}
	/**
	 * @return the isscript
	 */
	public boolean isIsscript() {
		return isscript;
	}
	/**
	 * @param isscript the isscript to set
	 */
	public void setIsscript(boolean isscript) {
		this.isscript = isscript;
	}
	/**
	 * @return the pubkey
	 */
	public String getPubkey() {
		return pubkey;
	}
	/**
	 * @param pubkey the pubkey to set
	 */
	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}
	/**
	 * @return the iscompressed
	 */
	public boolean isIscompressed() {
		return iscompressed;
	}
	/**
	 * @param iscompressed the iscompressed to set
	 */
	public void setIscompressed(boolean iscompressed) {
		this.iscompressed = iscompressed;
	}
	
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

}
