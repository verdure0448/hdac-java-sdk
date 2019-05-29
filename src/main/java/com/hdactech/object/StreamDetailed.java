package com.hdactech.object;

import java.util.List;

/**
 * @author Justin
 * @Should be refactoring
 * 
 */
public class StreamDetailed {
	String name = null;
	String createtxid = null;
	String streamref = null;
	Boolean open = null;
	Object details = null;
	List<String> creators;
	Boolean subscribed = null;

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
	 * @return the createtxid
	 */
	public String getCreatetxid() {
		return createtxid;
	}

	/**
	 * @param createtxid
	 *            the createtxid to set
	 */
	public void setCreatetxid(String createtxid) {
		this.createtxid = createtxid;
	}

	/**
	 * @return the streamref
	 */
	public String getStreamref() {
		return streamref;
	}

	/**
	 * @param streamref
	 *            the streamref to set
	 */
	public void setStreamref(String streamref) {
		this.streamref = streamref;
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
	 * @return the creators
	 */
	public List<String> getCreators() {
		return creators;
	}

	/**
	 * @param creators
	 *            the creators to set
	 */
	public void setCreators(List<String> creators) {
		this.creators = creators;
	}

	/**
	 * @return the subscribed
	 */
	public boolean isSubscribed() {
		return subscribed;
	}

	/**
	 * @param subscribed
	 *            the subscribed to set
	 */
	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}
}
