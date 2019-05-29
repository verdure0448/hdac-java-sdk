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
public class BalanceAssetWalletTransaction {
	String name = null;
	//String assetref = null; -- Not Defined at RPC ver.0.92
	//Double qty = null; -- Not Defined at RPC ver.0.92
	String type = null;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the assetref
	 */
	/*public String getAssetref() {
		return assetref;
	}*/

	/**
	 * @param assetref the assetref to set
	 */
	/*public void setAssetref(String assetref) {
		this.assetref = assetref;
	}*/

	/**
	 * @return the qty
	 */
	/*public double getQty() {
		return qty;
	}*/

	/**
	 * @param qty the qty to set
	 */
	/*public void setQty(double qty) {
		this.qty = qty;
	}*/

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
	 * @param qty the qty to set
	 */
	/*public void setQty(Double qty) {
		this.qty = qty;
	}*/


}
