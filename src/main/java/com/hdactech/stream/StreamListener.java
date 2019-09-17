package com.hdactech.stream;

import org.zeromq.ZMQ;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StreamListener {

	private static Thread thread = null;
	
	public StreamListener(String ip, String port, String streamId, StreamEventHandler eventHandler){

		Runnable r = new ZMQSubscriber("tcp://" + ip + ":" + port, streamId, eventHandler);
		thread = new Thread(r);
		thread.start();
	}
	
	public static void dispose() {
		//ZMQSubscriber.bRun = false;
		if (thread != null) {
			thread.interrupt();
		}
	}
}

/**
 * @author bsnc
 * @version 1.1
 */
class ZMQSubscriber implements Runnable {
	protected static boolean bRun = true;

	private StreamEventHandler eventHandler;
	private String url;
	private String streamId;

	public ZMQSubscriber(String url,  String streamId, StreamEventHandler eventHandler) {
		this.eventHandler = eventHandler;
		this.url = url;
		this.streamId = streamId + " ";
	}

	@Override
	public void run() {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
		// String topic = "stream ";
		// System.out.println("Start thread.");
		try {
			subscriber.connect(this.url);
			// subscriber.subscribe(topic.getBytes());
			subscriber.subscribe(streamId);
			// ZMQ.Poller poller = context.poller(1);
			// poller.register(subscriber);
			System.out.println("Subscribed ZMQ");
			
			final Gson gson = new GsonBuilder().create();
			while (bRun) {
				String recvData = subscriber.recvStr(0).replaceFirst(streamId, "");
				// System.out.println("ZMQ subscribe Recevied.");
				this.eventHandler.update(gson.fromJson(recvData, StreamObject.class));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			subscriber.close();
			context.term();
		}

	}
}