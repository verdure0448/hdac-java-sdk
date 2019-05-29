/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object.queryobjects;

import com.hdactech.command.HdacException;
import com.hdactech.command.tools.HdacTestParameter;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class TxIdVout {
	String txid;
	int vout;

	public void isFilled() throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("txId", txid);
		HdacTestParameter.valueIsNotNegative("vOut", vout);
	}

	/**
	 * @return the txId
	 */
	public String getTxId() {
		return txid;
	}
	/**
	 * @param txId the txId to set
	 */
	public void setTxId(String txId) {
		this.txid = txId;
	}
	/**
	 * @return the vOut
	 */
	public int getvOut() {
		return vout;
	}
	/**
	 * @param vOut the vOut to set
	 */
	public void setvOut(int vOut) {
		this.vout = vOut;
	}


}
