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
public class Permission {

	String address = null;
	String type = null;
	Long startblock = null;
	Long endblock = null;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the startblock
	 */
	public long getStartblock() {
		return startblock;
	}

	/**
	 * @param startblock
	 *            the startblock to set
	 */
	public void setStartblock(long startblock) {
		this.startblock = startblock;
	}

	/**
	 * @return the endblock
	 */
	public long getEndblock() {
		return endblock;
	}

	/**
	 * @param endblock
	 *            the endblock to set
	 */
	public void setEndblock(long endblock) {
		this.endblock = endblock;
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
}
