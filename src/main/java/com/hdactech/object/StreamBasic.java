package com.hdactech.object;

public class StreamBasic {
	String type = null;
	String name = null;
	String streamref = null;
	Boolean open = null;
	Object details = null;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the streamref
	 */
	public String getStreamref() {
		return streamref;
	}
	/**
	 * @return the open
	 */
	public Boolean getOpen() {
		return open;
	}
	/**
	 * @return the details
	 */
	public Object getDetails() {
		return details;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param streamref the streamref to set
	 */
	public void setStreamref(String streamref) {
		this.streamref = streamref;
	}
	/**
	 * @param open the open to set
	 */
	public void setOpen(Boolean open) {
		this.open = open;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(Object details) {
		this.details = details;
	}
}
