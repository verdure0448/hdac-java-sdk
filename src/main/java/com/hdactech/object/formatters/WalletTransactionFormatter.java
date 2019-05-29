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
import com.hdactech.object.ListAddressTxsDetailed;
import com.hdactech.object.ListWalletTxs;
import com.hdactech.object.TransactionWallet;
import com.hdactech.object.TransactionWalletDetailed;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class WalletTransactionFormatter {
	public final static List<TransactionWallet> formatListTransactionWallet(List<Object> objectWalletTransactions) {
		List<TransactionWallet> transactionWalletList = new ArrayList<TransactionWallet>();

		if (objectWalletTransactions != null) {
			for (Object objectWalletTransaction : objectWalletTransactions) {
				transactionWalletList.add(formatTransactionWallet(objectWalletTransaction));
			}
		}

		return transactionWalletList;
	}

	public final static TransactionWallet formatTransactionWallet(Object objectWalletTransaction) {
		TransactionWallet transactionWallet = new TransactionWallet();

		if (objectWalletTransaction != null && LinkedTreeMap.class.isInstance(objectWalletTransaction)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectWalletTransaction);
			transactionWallet = gson.fromJson(jsonValue, TransactionWallet.class);
		}

		return transactionWallet;
	}

	public static final List<ListWalletTxs> formatListTransactionWalletDetailed(List<Object> objectWalletTransactionsDetailed) {
		List<ListWalletTxs> transactionWalletList = new ArrayList<ListWalletTxs>();

		if (objectWalletTransactionsDetailed != null) {
			for (Object objectWalletTransaction : objectWalletTransactionsDetailed) {
				transactionWalletList.add(formatListWalletTxs(objectWalletTransaction));
			}
		}

		return transactionWalletList;
	}

	public final static TransactionWalletDetailed formatTransactionWalletDetailed(Object objectWalletTransactionDetailed) {
		TransactionWalletDetailed transactionWalletDetailed = new TransactionWalletDetailed();

		if (objectWalletTransactionDetailed != null && LinkedTreeMap.class.isInstance(objectWalletTransactionDetailed)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectWalletTransactionDetailed);
			transactionWalletDetailed = gson.fromJson(jsonValue, TransactionWalletDetailed.class);
		}

		return transactionWalletDetailed;
	}
	
	public final static ListWalletTxs formatListWalletTxs(Object objectWalletTransactionDetailed) {
		ListWalletTxs transactionWalletDetailed = new ListWalletTxs();

		if (objectWalletTransactionDetailed != null && LinkedTreeMap.class.isInstance(objectWalletTransactionDetailed)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectWalletTransactionDetailed);
			transactionWalletDetailed = gson.fromJson(jsonValue, ListWalletTxs.class);
		}

		return transactionWalletDetailed;
	}
	
	public static final List<ListAddressTxsDetailed> formatListAddressTxs(List<Object> objectWalletTransactionsDetailed) {
		List<ListAddressTxsDetailed> transactionWalletList = new ArrayList<ListAddressTxsDetailed>();

		if (objectWalletTransactionsDetailed != null) {
			for (Object objectWalletTransaction : objectWalletTransactionsDetailed) {
				transactionWalletList.add(formatListAddressTxs(objectWalletTransaction));
			}
		}

		return transactionWalletList;
	}

	public final static ListAddressTxsDetailed formatListAddressTxs(Object objectWalletTransactionDetailed) {
		ListAddressTxsDetailed transactionWalletDetailed = new ListAddressTxsDetailed();

		if (objectWalletTransactionDetailed != null && LinkedTreeMap.class.isInstance(objectWalletTransactionDetailed)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectWalletTransactionDetailed);
			transactionWalletDetailed = gson.fromJson(jsonValue, ListAddressTxsDetailed.class);
		}

		return transactionWalletDetailed;
	}

}
