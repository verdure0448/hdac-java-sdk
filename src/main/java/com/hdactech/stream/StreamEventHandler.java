/*
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.stream;

/**
 * @author Hdac Technology
 * @version 1.1
 */
public interface StreamEventHandler {
	public void update(StreamObject stream);
}