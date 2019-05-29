/*
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.publish;

import com.hdactech.subscribe.Subscriber;

/**
 * @author Hdac Technology
 * @version 1.1
 */
public interface Publisher {
	public void add(Subscriber observer);
	public void delete(Subscriber observer);
	public void notifyObserver();
}