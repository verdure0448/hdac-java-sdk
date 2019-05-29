/*
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.subscribe;

import com.hdactech.object.StreamSubscribe;

/**
 * @author Hdac Technology
 * @version 1.1
 */
public interface Subscriber {
	public void update(StreamSubscribe stream);
}