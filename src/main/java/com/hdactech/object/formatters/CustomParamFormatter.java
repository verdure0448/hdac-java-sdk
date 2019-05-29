/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object.formatters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hdactech.command.HdacException;
import com.hdactech.object.queryobjects.CustomParamString;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class CustomParamFormatter extends GsonFormatters {
	public final static Map<String, String> formatCustomParamString(List<CustomParamString> customParam)
			throws HdacException {
		Map<String, String> mapFormat = new HashMap<String, String>();

		if (customParam != null) {
			for (CustomParamString param : customParam) {
				if (param.isFilled()) {
					mapFormat.put(param.getName(), param.getValue());
				}
			}
		}

		return mapFormat;
	}
}
