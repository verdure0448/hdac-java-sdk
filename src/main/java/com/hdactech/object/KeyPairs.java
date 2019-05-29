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
public class KeyPairs {
	String address = null;
	String pubkey = null;
	String privkey = null;

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
	 * @return the pubkey
	 */
	public String getPubkey() {
		return pubkey;
	}

	/**
	 * @param pubkey
	 *            the pubkey to set
	 */
	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}

	/**
	 * @return the privkey
	 */
	public String getPrivkey() {
		return privkey;
	}

	/**
	 * @param privkey
	 *            the privkey to set
	 */
	public void setPrivkey(String privkey) {
		this.privkey = privkey;
	}

}
