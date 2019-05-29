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
 * permission is not used in RPC 0.92
 */
public class Address extends AddressInfo {
	String address = null;

	// HashMap<String, Permission> permissions;
	/**
	 *
	 */
	public Address() {
		super();

		// permissions = new HashMap<String, Permission>();
	}

	/**
	 * @param address
	 */
	public Address(String address) {
		super();
		this.address = address;
		// permissions = new HashMap<String, Permission>();
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	// /**
	// * @return the permissions
	// */
	// public HashMap<String, Permission> getPermissions() {
	// return permissions;
	// }
	//
	//
	// /**
	// * @param permissions the permissions to set
	// */
	// public void setPermissions(HashMap<String, Permission> permissions) {
	// this.permissions = permissions;
	// }

	// /**
	// * @param permissions the permissions to set
	// */
	// public void addPermission(Permission permission) {
	// if (permission != null && permission.getType() != null && !"".equals(permission.getType())) {
	// this.permissions.put(permission.getType(), permission);
	// }
	// }

}
