
package com.hdactech.object;

/**
 * @author Hdac Technology
 * @version 1.0
 */
/**
 * @author Justin
 *
 */
public class ListAddress {
	String address = null;
	boolean ismine;
	
	public ListAddress() {
		super();
	}

	/**
	 * @param address
	 */
	public ListAddress(String address, boolean ismine) {
		super();
		this.address = address;
		this.ismine = ismine;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
}
