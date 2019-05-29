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
public class TransactionWalletVout extends TransactionWalletVInfo{
	Long n = null;
	List<Item> items;

	/**
	 *
	 */
	public TransactionWalletVout() {
		super();
		items = new ArrayList<Item>();
	}
	/**
	 * @param info
	 */
	public TransactionWalletVout(TransactionWalletVInfo info) {
		super(info);
		items = new ArrayList<Item>();
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
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}


}
