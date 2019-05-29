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
public class BalanceAsset {
	String name = null;
	String issuetxid = null;
	String assetref = null;
	//Long multiple = null; -- Not Defined at RPC ver.0.92
	//Double units = null; -- Not Defined at RPC ver.0.92
	Boolean open = null;
	Object details = null;
	Double qty = null;
	Long raw = null;
	//Double issueqty = null; -- Not Defined at RPC ver.0.92
	//Long issueraw = null; -- Not Defined at RPC ver.0.92
	//Boolean subscribed = null; -- Not Defined at RPC ver.0.92
	String type = null;

	/**
	 *
	 */
	public BalanceAsset() {
		super();
	}

	/**
	 * @return the multiple
	 */
	/*public long getMultiple() {
		return multiple;
	}*/

	/**
	 * @param multiple
	 *            the multiple to set
	 */
	/*public void setMultiple(long multiple) {
		this.multiple = multiple;
	}*/

	/**
	 * @return the units
	 */
	/*public double getUnits() {
		return units;
	}*/

	/**
	 * @param units
	 *            the units to set
	 */
	/*public void setUnits(double units) {
		this.units = units;
	}*/

	/**
	 * @return the open
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * @param open
	 *            the open to set
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * @return the details
	 */
	public Object getDetails() {
		return details;
	}

	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(Object details) {
		this.details = details;
	}

	/**
	 * @return the assetref
	 */
	public String getAssetref() {
		return assetref;
	}

	/**
	 * @param assetref
	 *            the assetref to set
	 */
	public void setAssetref(String assetref) {
		this.assetref = assetref;
	}

	/**
	 * @return the issuetxid
	 */
	public String getIssuetxid() {
		return issuetxid;
	}

	/**
	 * @param issuetxid
	 *            the issuetxid to set
	 */
	public void setIssuetxid(String issuetxid) {
		this.issuetxid = issuetxid;
	}

	/**
	 * @return the subscribed
	 */
/*	public boolean isSubscribed() {
		return subscribed;
	}*/

	/**
	 * @param subscribed the subscribed to set
	 */
	/*public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}*/

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
	 * @return the open
	 */
	public Boolean getOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(Boolean open) {
		this.open = open;
	}

	/**
	 * @return the qty
	 */
	public Double getQty() {
		return qty;
	}

	/**
	 * @param qty the qty to set
	 */
	public void setQty(Double qty) {
		this.qty = qty;
	}

	/**
	 * @return the raw
	 */
	public Long getRaw() {
		return raw;
	}

	/**
	 * @param raw the raw to set
	 */
	public void setRaw(Long raw) {
		this.raw = raw;
	}

	/**
	 * @return the subscribed
	 */
	/*public Boolean getSubscribed() {
		return subscribed;
	}*/

	/**
	 * @param subscribed the subscribed to set
	 */
	/*public void setSubscribed(Boolean subscribed) {
		this.subscribed = subscribed;
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
	 * @param multiple the multiple to set
	 */
	/*public void setMultiple(Long multiple) {
		this.multiple = multiple;
	}*/

	/**
	 * @param units the units to set
	 */
	/*public void setUnits(Double units) {
		this.units = units;
	}*/

	/**
	 * @return the issueqty
	 */
	/*public Double getIssueqty() {
		return issueqty;
	}*/

	/**
	 * @param issueqty the issueqty to set
	 */
	/*public void setIssueqty(Double issueqty) {
		this.issueqty = issueqty;
	}*/

	/**
	 * @return the issueraw
	 */
	/*public Long getIssueraw() {
		return issueraw;
	}*/

	/**
	 * @param issueraw the issueraw to set
	 */
	/*public void setIssueraw(Long issueraw) {
		this.issueraw = issueraw;
	}*/

}
