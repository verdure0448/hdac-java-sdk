/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class HdacException extends Exception {

	private String object;
	private String reason;

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 2412952380749244614L;

	public HdacException(String excep_object, String excep_reason) {
		object = excep_object;
		reason = excep_reason;
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HdacException [\r\n" + "                      object=" + object + ",\r\n"
				+ "                      reason=" + reason + ",\r\n" + "                      message=" + super.getMessage()
				+ ",\r\n" + "                      cause=" + getCause() + "\r\n]";
	}

	public String toShortString() {
		return "HdacException [\r\n" + "                      object=" + object + ",\r\n"
				+ "                      reason=" + reason + "\r\n]";
	}

	/**
	 * @return the object
	 */
	public String getObject() {
		return object;
	}

	/**
	 * @param object
	 *            the object to set
	 */
	public void setObject(String object) {
		this.object = object;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}



	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return toString();
	}

}
