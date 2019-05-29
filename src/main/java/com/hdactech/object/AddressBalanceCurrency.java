/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object;

import com.hdactech.command.HdacException;
import com.hdactech.command.tools.HdacTestParameter;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class AddressBalanceCurrency implements AddressBalance {
	String address = null;
	Double currencyValue = null;

	public AddressBalanceCurrency() {
	}

	public void isFilled() throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("address", address);
		HdacTestParameter.valueIsNotNegative("currency value", currencyValue);
	}
	
	public Double getValue() {
		return new Double(currencyValue);
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the currencyValue
	 */
	public double getCurrencyValue() {
		return currencyValue;
	}

	/**
	 * @param currencyValue the currencyValue to set
	 */
	public void setCurrencyValue(double currencyValue) {
		this.currencyValue = currencyValue;
	}



}
