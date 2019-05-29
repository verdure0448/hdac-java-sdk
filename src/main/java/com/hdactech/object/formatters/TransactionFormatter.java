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
import com.hdactech.object.Transaction;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class TransactionFormatter {

	public final static Transaction formatTransaction(Object objectTransaction) {
		Transaction transaction = new Transaction();

		if (objectTransaction != null && LinkedTreeMap.class.isInstance(objectTransaction)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectTransaction);
			transaction = gson.fromJson(jsonValue, Transaction.class);
		}

		return transaction;
	}

}
