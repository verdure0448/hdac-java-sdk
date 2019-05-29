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
import com.hdactech.object.ExchangeTransactionRAW;
import com.hdactech.object.ExchangeTransactionRAWDetailed;
import com.hdactech.object.GetTransactionRAW;
import com.hdactech.object.SignedTransactionRAW;
import com.hdactech.object.TransactionRAW;
import com.hdactech.object.queryobjects.TxIdVout;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class RawTransactionFormatter {
	public final static TransactionRAW formatTransactionRAW(Object objectTransactionRAW) {
		TransactionRAW transactionRAW = new TransactionRAW();

		if (objectTransactionRAW != null && LinkedTreeMap.class.isInstance(objectTransactionRAW)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectTransactionRAW);
			transactionRAW = gson.fromJson(jsonValue, TransactionRAW.class);
		}

		return transactionRAW;
	}
	
	public final static GetTransactionRAW formatGetTransactionRAW(Object objectTransactionRAW) {
		GetTransactionRAW transactionRAW = new GetTransactionRAW();

		if (objectTransactionRAW != null && LinkedTreeMap.class.isInstance(objectTransactionRAW)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectTransactionRAW);
			transactionRAW = gson.fromJson(jsonValue, GetTransactionRAW.class);
		}

		return transactionRAW;
	}

	public final static SignedTransactionRAW formatSignedTransactionRAW(Object objectSignedTransactionRAW) {
		SignedTransactionRAW signedTransactionRAW = new SignedTransactionRAW();

		if (objectSignedTransactionRAW != null && LinkedTreeMap.class.isInstance(objectSignedTransactionRAW)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectSignedTransactionRAW);
			signedTransactionRAW = gson.fromJson(jsonValue, SignedTransactionRAW.class);
		}

		return signedTransactionRAW;
	}

	/**
	 * !!Should be checked why do i make this method???? I figure out that it
	 * doesn't need cuz of above method Created by Justin
	 * 
	 * @param objectAppendExchangeRaw
	 * @return
	 */

	public final static SignedTransactionRAW formatAppendExchangeRaw(Object objectAppendExchangeRaw) {
		SignedTransactionRAW appendExchangeRaw = new SignedTransactionRAW();

		if (objectAppendExchangeRaw != null && LinkedTreeMap.class.isInstance(objectAppendExchangeRaw)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectAppendExchangeRaw);
			appendExchangeRaw = gson.fromJson(jsonValue, SignedTransactionRAW.class);
		}

		return appendExchangeRaw;
	}

	
	/**
	 * Created by Justin
	 * @param objectDecodeExchangeRAW
	 * @return
	 */
	public final static ExchangeTransactionRAW formatDecodeExchageRAW(Object objectDecodeExchangeRAW) {
		
		ExchangeTransactionRAW decodeExchangeRAW = new ExchangeTransactionRAW();

		if (objectDecodeExchangeRAW != null && LinkedTreeMap.class.isInstance(objectDecodeExchangeRAW)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectDecodeExchangeRAW);
			decodeExchangeRAW = gson.fromJson(jsonValue, ExchangeTransactionRAW.class);
		}

		return decodeExchangeRAW;
	}
	
	public final static ExchangeTransactionRAWDetailed formatDecodeExchageRAWDetailed(Object objectDecodeExchangeRAW) {
		ExchangeTransactionRAWDetailed decodeExchangeRAW = new ExchangeTransactionRAWDetailed();

		if (objectDecodeExchangeRAW != null && LinkedTreeMap.class.isInstance(objectDecodeExchangeRAW)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectDecodeExchangeRAW);
			decodeExchangeRAW = gson.fromJson(jsonValue, ExchangeTransactionRAWDetailed.class);
		}

		return decodeExchangeRAW;
	}
	
	public static TxIdVout formatPrepareLockUnspent(Object objectPrepareLockUnpent) {
		TxIdVout prepareLockUnspent = new TxIdVout();
		
		if (objectPrepareLockUnpent != null && LinkedTreeMap.class.isInstance(objectPrepareLockUnpent)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			String jsonValue = gson.toJson(objectPrepareLockUnpent);
			prepareLockUnspent = gson.fromJson(jsonValue, TxIdVout.class);
		} 
		
		return prepareLockUnspent;
	}
	
	public static TxIdVout formatPrepareLockUnspentFrom(Object objectPrepareLockUnpentFrom) {
		TxIdVout prepareLockUnspentFrom = new TxIdVout();
		
		if (objectPrepareLockUnpentFrom != null && LinkedTreeMap.class.isInstance(objectPrepareLockUnpentFrom)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			String jsonValue = gson.toJson(objectPrepareLockUnpentFrom);
			prepareLockUnspentFrom = gson.fromJson(jsonValue, TxIdVout.class);
		} 
		
		return prepareLockUnspentFrom;
	}
	
	

}
