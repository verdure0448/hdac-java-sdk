/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object.queryobjects;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class AssetQuantity {
	String name;
	float quantity;

	/**
	 * @param name
	 * @param quantity
	 */
	public AssetQuantity(String name, float quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

	public boolean isFilled() {
		if (name == null || "".equals(name)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the quantity
	 */
	public float getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

}
