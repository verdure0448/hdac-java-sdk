package com.hdactech.object.formatters;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.hdactech.object.UnspentList;

public class IssueFormatter {

	public final static UnspentList formatListUnspentDetailed(Object objectListUnspentDetailed) {
		UnspentList unspentList = new UnspentList();

		if (objectListUnspentDetailed != null && LinkedTreeMap.class.isInstance(objectListUnspentDetailed)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectListUnspentDetailed);
			unspentList = gson.fromJson(jsonValue, UnspentList.class);
		}

		return unspentList;
	}

	public final static List<UnspentList> formatListUnspentDetaileds(List<Object> objectListUnspentDetaileds) {
		List<UnspentList> unspentList = new ArrayList<UnspentList>();

		if (objectListUnspentDetaileds != null) {

			for (Object objectListUnspentDetailed : objectListUnspentDetaileds) {
				unspentList.add(formatListUnspentDetailed(objectListUnspentDetailed));
			}
		}
		
		return unspentList;
	}
}

/*
 * 
 * if (objectBalanceAssets != null) { for (Object objectBalanceAsset :
 * objectBalanceAssets) {
 * balanceAsset.add(formatBalanceAsset(objectBalanceAsset)); } }
 */
