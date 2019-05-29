/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command.tools;

import java.util.List;

import com.hdactech.command.HdacException;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class HdacTestParameter {

	public static void isNotNullOrEmpty(String name, String string) throws HdacException {
		if (string != null) {
			if (string.isEmpty()) {
				throw new HdacException(name, "is null or empty.");
			}
		} else {
			throw new HdacException(name, "is null or empty.");
		}
	}

	public static void isNotNull(String name, Object object) throws HdacException {
		if (object == null) {
			throw new HdacException(name, "is null or empty.");
		}
	}

	public static void isNotNullOrEmpty(String name, Object[] array) throws HdacException {
		if (array != null) {
			if (array.length <= 0) {
				throw new HdacException(name, "size is 0.");
			}
		} else {
			throw new HdacException(name, "is null.");
		}
	}

	public static void isNotNullOrEmpty(String name, List<Object> list) throws HdacException {
		if (list != null) {
			if (list.size() <= 0) {
				throw new HdacException(name, "size is 0.");
			}
		} else {
			throw new HdacException(name, "is null.");
		}
	}

	public static boolean isArrayNotNullOrEmpty(Object[] array) {
		if (array == null || array.length <= 0) {
			return false;
		}
		return true;
	}

	public static void arrayNotContainNullOrEmptyValues(String name, Object[] array) throws HdacException {
		for (Object object : array) {
			if (object == null) {
				throw new HdacException(name, "array contain null value.");
			} else if (object.getClass() == String.class) {
				if (((String) object).isEmpty()) {
					throw new HdacException(name, "array contain empty string.");
				}
			}
		}
	}

	public static void floatArrayContainNullOrNegativeValue(String name, float[] array) throws HdacException {
		for (float f : array) {
			if (f < 0) {
				throw new HdacException(name, "array contain negative value.");
			} else if (f == 0) {
				throw new HdacException(name, "array contain null values.");
			}
		}
	}

	public static void intArrayContainNullOrNegativeValue(String name, int[] array) throws HdacException {
		for (int i : array) {
			if (i < 0) {
				throw new HdacException(name, "array contain negative value.");
			} else if (i == 0) {
				throw new HdacException(name, "array contain null values.");
			}
		}
	}

	public static void valueIsNotNegative(String name, int value) throws HdacException {
		if (value < 0) {
			throw new HdacException(name, "is negative.");
		}
	}

	public static void valueIsNotNegative(String name, float value) throws HdacException {
		if (value < 0) {
			throw new HdacException(name, "is negative.");
		}
	}

	public static void valueIsNotNegative(String name, double value) throws HdacException {
		if (value < 0) {
			throw new HdacException(name, "is negative.");
		}
	}

	public static void valueIsPositive(String name, int value) throws HdacException {
		if (value < 0) {
			throw new HdacException(name, "is negative.");
		} else if (value == 0) {
			throw new HdacException(name, "is null.");
		}
	}

	public static void valueIsPositive(String name, float value) throws HdacException {
		if (value < 0) {
			throw new HdacException(name, "is negative.");
		} else if (value == 0) {
			throw new HdacException(name, "is null.");
		}
	}

	public static void valueIsPositive(String name, double value) throws HdacException {
		if (value < 0) {
			throw new HdacException(name, "is negative.");
		} else if (value == 0) {
			throw new HdacException(name, "is null.");
		}
	}

}
