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
import com.hdactech.object.Block;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class BlockFormatter {
	public final static long formatBlockCount(String stringBlockCount) {
		final Gson gson = new GsonBuilder().create();

		final Long blockCount = gson.fromJson(stringBlockCount, Long.class);

		return blockCount.longValue();
	}

	public final static Block formatBlock(Object objectBlock) {
		Block block = new Block();

		if (objectBlock != null && LinkedTreeMap.class.isInstance(objectBlock)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectBlock);
			block = gson.fromJson(jsonValue, Block.class);
		}

		return block;
	}

}
