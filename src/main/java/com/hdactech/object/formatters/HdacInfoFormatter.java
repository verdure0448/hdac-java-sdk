/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object.formatters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hdactech.object.HdacInfo;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class HdacInfoFormatter {

	public final static HdacInfo formatHdacInfo(
			String stringHdacInfo) {
		final Gson gson = new GsonBuilder().create();
		final HdacInfo hdacInfo = gson.fromJson(
				stringHdacInfo, HdacInfo.class);

		return hdacInfo;
	}

}
