/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object.formatters;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.hdactech.object.Address;
import com.hdactech.object.KeyPairs;
import com.hdactech.object.ListAddress;
import com.hdactech.object.ListAddressDetailed;
import com.hdactech.object.MultiSigAddress;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class AddressFormatter {
	public final static Address formatAddress(Object objectAddress) {
		Address address = new Address();

		if (objectAddress != null && LinkedTreeMap.class.isInstance(objectAddress)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectAddress);
			address = gson.fromJson(jsonValue, Address.class);
		}

		return address;
	}
	
	/**
	 * Created by Justin
	 * @param objectAddress
	 * @return
	 */
	public final static ListAddress formatAddressList(Object objectAddress) {
		ListAddress address = new ListAddress();

		if (objectAddress != null && LinkedTreeMap.class.isInstance(objectAddress)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectAddress);
			address = gson.fromJson(jsonValue, ListAddress.class);
		}

		return address;
	}
	
	public final static ListAddressDetailed formatAddressListDetailed(Object objectAddress) {
		ListAddressDetailed address = new ListAddressDetailed();

		if (objectAddress != null && LinkedTreeMap.class.isInstance(objectAddress)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
		
			String jsonValue = gson.toJson(objectAddress);
			address = gson.fromJson(jsonValue, ListAddressDetailed.class);
		}

		return address;
	}
	
	
	public final static MultiSigAddress formatMultiSigAddress(Object objectAddress) {
		MultiSigAddress address = new MultiSigAddress();
		
		if(objectAddress !=null && LinkedTreeMap.class.isInstance(objectAddress)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			String jsonValue = gson.toJson(objectAddress);
			address = gson.fromJson(jsonValue, MultiSigAddress.class);
		}
		
		return address;
	}

	public final static List<Address> formatAddressesList(List<Object> objectAddresses) {
		List<Address> addresses = new ArrayList<Address>();

		if (objectAddresses != null) {
			for (Object objectAddress : objectAddresses) {
				addresses.add(formatAddress(objectAddress));
			}
		}

		return addresses;
	}
	
	/**
	 * Created by Justin
	 * @param objectAddresses
	 * @return
	 */
	public final static List<Object> formatAddressesListIsMine(List<Object> objectAddresses) {
		List<Object> addresses = new ArrayList<Object>();

		if (objectAddresses != null) {
			for (Object objectAddress : objectAddresses) {
				addresses.add(formatAddressList(objectAddress));
			}
		}

		return addresses;
	}
	
	public final static List<Object> formatAddressesListIsMineDetailed(List<Object> objectAddresses) {
		List<Object> addresses = new ArrayList<Object>();

		if (objectAddresses != null) {
			for (Object objectAddress : objectAddresses) {
				addresses.add(formatAddressListDetailed(objectAddress));
			}
		}

		return addresses;
	}

	public final static KeyPairs formatKeyPairs(Object objectKeyPair) {
		KeyPairs keyPairs = new KeyPairs();

		if (objectKeyPair != null && LinkedTreeMap.class.isInstance(objectKeyPair)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectKeyPair);
			keyPairs = gson.fromJson(jsonValue, KeyPairs.class);
		}

		return keyPairs;
	}

	public final static List<KeyPairs> formatKeyPairsList(List<Object> objectKeyPairs) {
		List<KeyPairs> listKeyPairs = new ArrayList<KeyPairs>();

		if (objectKeyPairs != null) {
			for (Object objectKeyPair : objectKeyPairs) {
				listKeyPairs.add(formatKeyPairs(objectKeyPair));
			}
		}

		return listKeyPairs;
	}

}