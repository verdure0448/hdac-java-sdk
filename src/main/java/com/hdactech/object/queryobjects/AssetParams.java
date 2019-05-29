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
public class AssetParams {
	String name;
	boolean open;

	/**
	 * @param name
	 * @param open
	 */
	public AssetParams(String name, boolean open) {
		super();
		this.name = name;
		this.open = open;
	}

	public void isFilled() throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("name", getName());
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

}
