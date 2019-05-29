/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object.formatters;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Hex;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class HexFormatter {

	private static final String CHARSET = "UTF-8";

	public static String toHex(String asciiValue) {
		try {
			return Hex.encodeHexString(asciiValue.getBytes(CHARSET));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static String fromHex(String hexValue) {
		try {
			final byte[] bytes = Hex.decodeHex(hexValue.toCharArray());
			return new String(bytes, CHARSET);
		} catch (Exception e) {
			return null;
		}
	}

}
