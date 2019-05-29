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
import com.hdactech.object.Permission;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class GrantFormatter {
	public final static Permission formatPermission(Object objectPermission) {
		Permission permission = new Permission();

		if (objectPermission != null && LinkedTreeMap.class.isInstance(objectPermission)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			String jsonValue = gson.toJson(objectPermission);
			permission = gson.fromJson(jsonValue, Permission.class);
		}

		return permission;
	}

	public final static List<Permission> formatPermissions(List<Object> objectPermissions) {
		List<Permission> permissions = new ArrayList<Permission>();

		if (objectPermissions != null) {
			for (Object objectPermission : objectPermissions) {
				permissions.add(formatPermission(objectPermission));
			}
		}

		return permissions;
	}

}
