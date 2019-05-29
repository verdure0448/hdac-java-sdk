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
public class TransactionWalletVin extends TransactionWalletVInfo{
	Long n = null;
	String txid = null;
	Long vout = null;


	/**
	 * @param info
	 */
	public TransactionWalletVin(TransactionWalletVInfo info) {
		super(info);
	}

	/**
	 *
	 */
	public TransactionWalletVin() {
		super();
	}

	/**
	 * @return the txid
	 */
	public String getTxid() {
		return txid;
	}

	/**
	 * @param txid the txid to set
	 */
	public void setTxid(String txid) {
		this.txid = txid;
	}

	/**
	 * @return the n
	 */
	public long getN() {
		return n;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(long n) {
		this.n = n;
	}

	/**
	 * @return the vout
	 */
	public Long getVout() {
		return vout;
	}

	/**
	 * @param vout the vout to set
	 */
	public void setVout(Long vout) {
		this.vout = vout;
	}



}
