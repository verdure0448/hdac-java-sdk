/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object;

import com.hdactech.command.HdacException;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public interface AddressBalance {
	public String getAddress();
	public void isFilled() throws HdacException;
	public Object getValue() throws HdacException;
}
