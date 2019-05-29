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
import com.google.gson.internal.LinkedTreeMap;
import com.hdactech.object.TxOut;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class TxOutFormatter {
	public final static TxOut formatTxOut(Object objectTxOut) {
		TxOut txOut = new TxOut();

		if (objectTxOut != null && LinkedTreeMap.class.isInstance(objectTxOut)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectTxOut);
			txOut = gson.fromJson(jsonValue, TxOut.class);
		}

		return txOut;
	}

}
