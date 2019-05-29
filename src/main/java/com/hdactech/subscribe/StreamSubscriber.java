/*
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.subscribe;

import com.hdactech.object.StreamSubscribe;
import com.hdactech.publish.Publisher;
import com.hdactech.publish.StreamPublisher;


/**
 * Don't use!!
 * 
 * @author Hdac Technology
 * @version 1.1
 */
class StreamSubscriber implements Subscriber {

	private Publisher publisher;
	private Subscriber subscriber;
	
	
	/**
	 * TODO
	 * 
	 * @param publisher
	 * @param observer
	 */
	public StreamSubscriber(StreamPublisher publisher, Subscriber subscriber) {
		this.publisher = publisher;
		this.subscriber = subscriber;
		publisher.add(this); 
	}

	/**
	 * TODO
	 */
	@Override
	public void update(StreamSubscribe stream) {
		this.subscriber.update(stream);
	}
	
	/**
	 * TODO
	 */
	public void close() {
		this.publisher.delete(this);
	}
}