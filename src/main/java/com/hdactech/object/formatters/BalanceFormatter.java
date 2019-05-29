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
import com.hdactech.object.BalanceAsset;
import com.hdactech.object.BalanceAssetGeneral;
import com.hdactech.object.MultiBalance;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class BalanceFormatter {
	public final static BalanceAssetGeneral formatBalanceAssetGeneral(Object objectBalanceAsset) {
		BalanceAssetGeneral balanceAsset = new BalanceAssetGeneral();

		if (objectBalanceAsset != null && LinkedTreeMap.class.isInstance(objectBalanceAsset)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectBalanceAsset);
			balanceAsset = gson.fromJson(jsonValue, BalanceAssetGeneral.class);
		}

		return balanceAsset;
	}

	public final static List<BalanceAssetGeneral> formatBalanceAssetsGeneral(List<Object> objectBalanceAssets) {
		List<BalanceAssetGeneral> balanceAsset = new ArrayList<BalanceAssetGeneral>();

		if (objectBalanceAssets != null) {
			for (Object objectBalanceAsset : objectBalanceAssets) {
				balanceAsset.add(formatBalanceAssetGeneral(objectBalanceAsset));
			}
		}

		return balanceAsset;
	}	
	
	public final static BalanceAsset formatBalanceAsset(Object objectBalanceAsset) {
		BalanceAsset balanceAsset = new BalanceAsset();

		if (objectBalanceAsset != null && LinkedTreeMap.class.isInstance(objectBalanceAsset)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectBalanceAsset);
			balanceAsset = gson.fromJson(jsonValue, BalanceAsset.class);
		}

		return balanceAsset;
	}

	public final static List<BalanceAsset> formatBalanceAssets(List<Object> objectBalanceAssets) {
		List<BalanceAsset> balanceAsset = new ArrayList<BalanceAsset>();

		if (objectBalanceAssets != null) {
			for (Object objectBalanceAsset : objectBalanceAssets) {
				balanceAsset.add(formatBalanceAsset(objectBalanceAsset));
			}
		}

		return balanceAsset;
	}

	public final static MultiBalance formatMultiBalance(Object objectMultiBalance) {
		MultiBalance multiBalance = new MultiBalance();

		if (objectMultiBalance != null && LinkedTreeMap.class.isInstance(objectMultiBalance)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectMultiBalance);
			multiBalance = gson.fromJson(jsonValue, MultiBalance.class);
		}

		return multiBalance;
	}


}
