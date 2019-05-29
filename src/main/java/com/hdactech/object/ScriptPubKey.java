/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class ScriptPubKey {
    String asm = null;
    String hex = null;
    Long reqSigs = null;
    String type = null;
    List<String> addresses;

    /**
	 *
	 */
	public ScriptPubKey() {
		super();
		addresses = new ArrayList<String>();
	}

	/**
	 * @return the asm
	 */
	public String getAsm() {
		return asm;
	}

	/**
	 * @param asm the asm to set
	 */
	public void setAsm(String asm) {
		this.asm = asm;
	}

	/**
	 * @return the hex
	 */
	public String getHex() {
		return hex;
	}

	/**
	 * @param hex the hex to set
	 */
	public void setHex(String hex) {
		this.hex = hex;
	}

	/**
	 * @return the reqSigs
	 */
	public long getReqSigs() {
		if(reqSigs == null) {
			reqSigs = Long.valueOf(0);
		}
		return reqSigs;
	}

	/**
	 * @param reqSigs the reqSigs to set
	 */
	public void setReqSigs(long reqSigs) {
		this.reqSigs = reqSigs;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the addresses
	 */
	public List<String> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

}
