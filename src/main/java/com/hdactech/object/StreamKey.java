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
public class StreamKey {
	String key = null;
	Long items = null;
	Long confirmed = null;
	StreamKeyInfo first = null;
	StreamKeyInfo last = null;
	
	//should be checked
	//StreamKeyinfo first & last

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String value = "StreamKey [key=" + key + ", items=" + items + ", confirmed=" + confirmed;
		if (first != null) {
			value += ", first=" + first.toString();
		}
		if (last != null) {
			value += ", last=" + last.toString();
		}
		value += "]";

		return value;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the items
	 */
	public long getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(long items) {
		this.items = items;
	}

	/**
	 * @return the confirmed
	 */
	public long getConfirmed() {
		return confirmed;
	}

	/**
	 * @param confirmed
	 *            the confirmed to set
	 */
	public void setConfirmed(long confirmed) {
		this.confirmed = confirmed;
	}

	/**
	 * @return the first
	 */
	public StreamKeyInfo getFirst() {
		return first;
	}

	/**
	 * @param first
	 *            the first to set
	 */
	public void setFirst(StreamKeyInfo first) {
		this.first = first;
	}

	/**
	 * @return the last
	 */
	public StreamKeyInfo getLast() {
		return last;
	}

	/**
	 * @param last
	 *            the last to set
	 */
	public void setLast(StreamKeyInfo last) {
		this.last = last;
	}

}
