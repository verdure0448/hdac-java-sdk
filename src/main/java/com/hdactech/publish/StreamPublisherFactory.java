/*
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.publish;

import org.zeromq.ZMQ;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hdactech.command.HdacException;
import com.hdactech.object.StreamSubscribe;

/**
 * @author Hdac Technology
 * @version 1.1
 */
public class StreamPublisherFactory {

	private static StreamPublisherFactory instance;
	private StreamPublisher publisher;
	// private static StreamPublisherFactory instance = null;

	private static Thread thread = null;

	private static String ip = null;
	private static String port = null;

	private StreamPublisherFactory(String ip, String port) {

		// ZMQ Subscribe
		Runnable r = new ZMQSubscriber2(this, ip, port);
		thread = new Thread(r);
		thread.start();
	}

	/**
	 *
	 * 
	 * @param ip
	 * @param port
	 */
	public static void init(String ip, String port) {
		StreamPublisherFactory.ip = ip;
		StreamPublisherFactory.port = port;
	}

	/**
	 * StreamPublisher
	 * 
	 * @return
	 * @throws HdacException
	 */
	public static StreamPublisher getInstance() throws HdacException {
		if (instance == null) {
			if (StreamPublisherFactory.ip == null || StreamPublisherFactory.port == null) {
				throw new HdacException(null, "Not set NodeMQServer parameter.");
			}
			
			instance = new StreamPublisherFactory(StreamPublisherFactory.ip, StreamPublisherFactory.port);
			//instance.publisher = new StreamPublisher();
		}

		return instance.publisher;
	}

	/**
	 * StreamPublisher
	 * 
	 * @return
	 */
	public Publisher getStreamPublisher() {
		return instance.publisher;
	}

	// ZMQ
	synchronized protected void update(StreamSubscribe streamObj) {
		instance.publisher.setData(streamObj);
	}

	/**
	 * Dispose all resoure
	 */
	public static void dispose() {
		//ZMQSubscriber.bRun = false;
		if (thread != null) {
			thread.interrupt();
		}

		ip = null;
		port = null;
		thread = null;
		instance.publisher = null;
		instance = null;
	}
}

/**
 * @author Hdac Technology
 * @version 1.1
 */
class ZMQSubscriber2 implements Runnable {
	protected static boolean bRun = true;

	private StreamPublisherFactory ins;
	private String ip;
	private String port;

	public ZMQSubscriber2(StreamPublisherFactory ins, String ip, String port) {
		this.ins = ins;
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
			while (bRun) {
				String recvData = subscriber.recvStr(0).replaceFirst(topic, "");

				// System.out.println("ZMQ subscribe Recevied.");
				final Gson gson = new GsonBuilder().create();
				this.ins.update(gson.fromJson(recvData, StreamSubscribe.class));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			subscriber.close();
			context.term();
		}

	}
}