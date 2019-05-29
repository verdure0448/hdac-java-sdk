/*
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.publish;

import java.util.ArrayList;
import java.util.List;

import org.zeromq.ZMQ;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hdactech.object.StreamSubscribe;
import com.hdactech.subscribe.Subscriber;

/**
 * @author Hdac Technology
 * @version 1.1
 */
public class StreamPublisher implements Publisher {

	private List<Subscriber> subscribers;
	private StreamSubscribe streamObj;
	private String id;
	private Thread mqThread;
	
	public StreamPublisher(String ip, String port) {
		subscribers = new ArrayList<Subscriber>();
		this.id = ip + ":" + port;
		
		Runnable r = new ZMQSubscriber(this, ip, port);
		mqThread = new Thread(r);
		mqThread.start();
	} 

	@Override
	public void add(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	@Override
	public void delete(Subscriber subscriber) {
		int index = subscribers.indexOf(subscriber);
		subscribers.remove(index);
	}

	@Override
	public void notifyObserver() {
		for (Subscriber subscriber : subscribers) {
			subscriber.update(streamObj);
		}
	}

	public void setData(StreamSubscribe streamObj) {
		this.streamObj = streamObj;
		notifyObserver();
	}

	public StreamSubscribe getStreamObj() {
		return streamObj;
	}

	public String getId() {
		return this.id;
	}
	
	public void dispose() {
		mqThread.interrupt();
		try {
			mqThread.join(300);
		} catch (InterruptedException e) {
			
		}
	}
	
	
	/**
	 * @author Hdac Technology
	 * @version 1.1
	 */
	class ZMQSubscriber implements Runnable {
		//protected static boolean bRun = true;

		private StreamPublisher publisher;
		private String ip;
		private String port;

		public ZMQSubscriber(StreamPublisher publisher, String ip, String port) {
			this.publisher = publisher;
			this.ip = ip;
			this.port = port;
		}

		@Override
		public void run() {
			ZMQ.Context context = ZMQ.context(1);
			ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
			String topic = "stream ";
			// System.out.println("Start thread.");
			try {
				subscriber.connect("tcp://" + this.ip + ":" + this.port);
				// subscriber.subscribe(topic.getBytes());
				subscriber.subscribe(topic);
				// ZMQ.Poller poller = context.poller(1);
				// poller.register(subscriber);
				// System.out.println("Subscribed ZMQ");
				while (true) {
					String recvData = subscriber.recvStr(0).replaceFirst(topic, "");

					// System.out.println("ZMQ subscribe Recevied.");
					final Gson gson = new GsonBuilder().create();
					this.publisher.setData(gson.fromJson(recvData, StreamSubscribe.class));
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				subscriber.close();
				context.term();
			}

		}
	}
}