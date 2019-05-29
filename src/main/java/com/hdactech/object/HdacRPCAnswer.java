/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object;

import com.google.gson.internal.LinkedTreeMap;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class HdacRPCAnswer {

	Object result = null;
	LinkedTreeMap<String, Object> error;
	String id = null;

	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * @return the error
	 */
	public LinkedTreeMap<String, Object> getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(LinkedTreeMap<String, Object> error) {
		this.error = error;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
