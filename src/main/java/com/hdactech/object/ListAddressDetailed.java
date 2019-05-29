package com.hdactech.object;


/**
 * @author Justin
 *
 */
public class ListAddressDetailed {
	String address = null;
	Boolean ismine = null;
    Boolean iswatchonly = null;
    Boolean isscript = null;
    String pubkey = null;
    Boolean iscompressed = null;
    String account = null;
    //boolean SYNCHRONIZED;
    
	/**
	 * @return the address
	 */
    
    public void getSize() {
    	
    }
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the ismine
	 */
	public Boolean getIsmine() {
		return ismine;
	}
	/**
	 * @return the iswatchonly
	 */
	public Boolean getIswatchonly() {
		return iswatchonly;
	}
	/**
	 * @return the isscript
	 */
	public Boolean getIsscript() {
		return isscript;
	}
	/**
	 * @return the pubkey
	 */
	public String getPubkey() {
		return pubkey;
	}
	/**
	 * @return the iscompressed
	 */
	public Boolean getIscompressed() {
		return iscompressed;
	}
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param ismine the ismine to set
	 */
	public void setIsmine(Boolean ismine) {
		this.ismine = ismine;
	}
	/**
	 * @param iswatchonly the iswatchonly to set
	 */
	public void setIswatchonly(Boolean iswatchonly) {
		this.iswatchonly = iswatchonly;
	}
	/**
	 * @param isscript the isscript to set
	 */
	public void setIsscript(Boolean isscript) {
		this.isscript = isscript;
	}
	/**
	 * @param pubkey the pubkey to set
	 */
	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}
	/**
	 * @param iscompressed the iscompressed to set
	 */
	public void setIscompressed(Boolean iscompressed) {
		this.iscompressed = iscompressed;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
}
