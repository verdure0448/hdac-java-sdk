/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hdactech.command.builders.QueryBuilderStream;
import com.hdactech.object.Address;
import com.hdactech.object.Stream;
import com.hdactech.object.StreamKey;
import com.hdactech.object.StreamKeyItem;
import com.hdactech.object.formatters.StreamFormatter;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class StreamCommand extends QueryBuilderStream {

	public StreamCommand(String ip, String port, String login, String password, RuntimeParameters runtimeparameters) {
		initialize(ip, port, login, password, runtimeparameters);
	}

	/**
	 * create stream "stream-name" open ( custom-fields )
	 * 
	 * Creates stream
	 * 
	 * 
	 * Arguments: 1. entity-type (string, required) The only possible value: stream
	 * 2. "stream-name" (string, required) Stream name, if not "" should be unique.
	 * 3. open (boolean, required) Allow anyone to publish in this stream 4
	 * custom-fields (object, optional) a json object with custom fields {
	 * "param-name": "param-value" (strings, required) The key is the parameter
	 * name, the value is parameter value ,... }
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param streamName
	 * @param open
	 * @return TxId
	 * @throws HdacException
	 */
	
	/*
	 * Parameter added for ver 0.92.
	 */
	public String create(String entityType, String streamName, boolean open) throws HdacException {
		String stringCreate = "";

		Object objectCreate = executeCreate(entityType, streamName, open);
		if (verifyInstance(objectCreate, String.class)) {
			stringCreate = (String) objectCreate;
		}

		return stringCreate;
	}

	/**
	 * create stream "stream-name" close
	 * 
	 * Creates stream
	 * 
	 * 
	 * Arguments: 1. entity-type (string, required) The only possible value: stream
	 * 2. "stream-name" (string, required) Stream name, if not "" should be unique.
	 * 3. open (boolean, required) Allow anyone to publish in this stream 4
	 * custom-fields (object, optional) a json object with custom fields {
	 * "param-name": "param-value" (strings, required) The key is the parameter
	 * name, the value is parameter value ,... }
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param streamName
	 * @param open
	 *            = false
	 * @return TxId
	 * @throws HdacException
	 */
	
	/*
	 * Parameter added for ver 0.92.
	 */
	public String create(String entityType, String streamName) throws HdacException {
		return create(entityType, streamName, false);
	}
        /*****
         * subscribe to a stream with rescan param
         * @param streamidentifier
         * @param rescan 
         */
        public void subscribe(String streamidentifier,boolean rescan) throws HdacException{
            executeSubscribe(streamidentifier,rescan);
        }
        /****
         * list stream items for a stream 
         * @param streanmane
         * @param verbose
         * @param count
         * @param start
         * @param localordering
         * @return 
         */
        public List<StreamKeyItem> listStreamItems(String streanmane,boolean verbose,int count,int start,boolean localordering){
            Object obj=null;
            try {
                obj = executeListStreamItems(streanmane,verbose,count,start,localordering);
            } catch (HdacException ex) {
                Logger.getLogger(StreamCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            int size=((List)obj).size();
            StreamKeyItem item=null;
            List<StreamKeyItem> items=new ArrayList<StreamKeyItem>();
            for(int a=0;a<size;a++){
                item=StreamFormatter.formatStreamKeyItem(((List)obj).get(a));
                items.add(item);
            }
            return items;
        }
        /****
         * added by leo
         * return a specific item for a stream 
         * @param streamname
         * @param txid
         * @param verbose
         * @return 
         */
        public StreamKeyItem getStreamItem(String streamname,String txid,boolean verbose){
            Object obj=null;
            StreamKeyItem item=null;
            try {
                obj=executeGetstreamitem(streamname,txid,verbose);
            } catch (HdacException ex) {
                Logger.getLogger(StreamCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(verifyInstance(obj,com.google.gson.internal.LinkedTreeMap.class)){
                item=StreamFormatter.formatStreamKeyItem(obj);
            }
            if(item!=null){
                return item;
            }
            return null;
        }
        /**
     * **
     * get the latest key item for a specific stream
     *
     * @param streamname
     * @param key
     * @param verbose
     *
     * @return
     */
    public StreamKeyItem getLatestStreamKeyItem(String streamname, String key, boolean verbose) throws HdacException {
        StreamKeyItem result = new StreamKeyItem();
        Object obj = executeListStreamKeyItems(streamname, key, verbose, 1, -1);
        if (verifyInstance(obj, ArrayList.class) && verifyInstanceofList((ArrayList<Object>) obj, StreamKeyItem.class)) {
            if (StreamFormatter.formatStreamKeyItems((ArrayList<Object>) obj).size() > 0) {
                result = StreamFormatter.formatStreamKeyItems((ArrayList<Object>) obj).get(0);
            }
        }
        return result;
    }
	/**
	 * {@link #create(String, boolean)} with control over the from-address used to
	 * used to create the stream
	 * 
	 * @param addressFrom
	 *            (Address) The from-address used to create the stream
	 * @param streamName
	 *            (String) The name of the stream to create
	 * @param open
	 *            (Boolean) True if the stream is open, else false
	 * @return (String) The transaction id
	 * @throws HdacException
	 */
	/*public String createFrom(Address addressFrom, String streamName, boolean open) throws HdacException {
		String stringCreate = "";
		Object objectCreate = executeCreateFrom(addressFrom.getAddress(), streamName, open);
		if (verifyInstance(objectCreate, String.class)) {
			stringCreate = (String) objectCreate;
		}

		return stringCreate;
	}*/

	/**
	 * {@link #createFrom(Address, String, boolean)} with address in format string
	 * 
	 * @param addressFrom
	 *            (String) The from-address used to create the stream
	 * @param streamName
	 *            (String) The name of the stream to create
	 * @param open
	 *            (Boolean) True if the stream is open, else false
	 * @return (String) The transaction id
	 * @throws HdacException
	 */
	public String createFrom(String addressFrom, String entityType, String streamName, boolean open) throws HdacException {
		String stringCreate = "";
		Object objectCreate = executeCreateFrom(addressFrom, entityType, streamName, open);
		if (verifyInstance(objectCreate, String.class)) {
			stringCreate = (String) objectCreate;
		}

		return stringCreate;
	}

	/**
	 * {@link #createFrom(Address, String, boolean)} for a close stream
	 * 
	 * @param addressFrom
	 *            (Address) The from-address used to create the stream
	 * @param streamName
	 *            (String) The name of the stream to create
	 * @return (String) The transaction id
	 * @throws HdacException
	 */
	/*public String createFrom(Address addressFrom, String streamName) throws HdacException {
		return createFrom(addressFrom.getAddress(), streamName, false);
	}*/

	/**
	 * {@link #createFrom(Address, String)} with address in format string
	 * 
	 * @param addressFrom
	 *            (String) The from-address used to create the stream
	 * @param streamName
	 *            (String) The name of the stream to create
	 * @return (String) The transaction id
	 * @throws HdacException
	 */
	/*public String createFrom(String addressFrom, String streamName) throws HdacException {
		return createFrom(addressFrom, streamName, false);
	}*/

	/**
	 * liststreams (stream-identifier(s) verbose count start ) 1.
	 * "stream-identifier(s)" (string, optional, default=*, all streams) Stream
	 * identifier - one of the following: issue txid, stream reference, stream name.
	 * or 1. stream-identifier(s) (array, optional) A json array of stream
	 * identifiers 2. verbose (boolean, optional, default=false) If true, returns
	 * stream list of creators 3. count (number, optional, default=INT_MAX - all)
	 * The number of streams to display 4. start (number, optional, default=-count -
	 * last) Start from specific stream, 0 based, if negative - from the end
	 * 
	 * Returns list of defined streams
	 * 
	 * @param streamName
	 * @param verbose
	 * @param count
	 * @param start
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Parameter(count,start) type changed for ver 0.92.
	 */
	public List<Stream> listStreams(String[] streamName, boolean verbose, long count, long start)
			throws HdacException {
		List<Stream> streams = new ArrayList<Stream>();

		Object objectStreams = executeListStreams(streamName, verbose, count, start);
		if (verifyInstance(objectStreams, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectStreams, Stream.class)) {
			streams = StreamFormatter.formatStreams((ArrayList<Object>) objectStreams);
		}

		return streams;
	}

	/**
	 * {@link #listStreams(String, boolean, int, int)} without start
	 * 
	 * @param streamName
	 * @param verbose
	 * @param count
	 * @return
	 * @throws HdacException
	 */
	public List<Stream> listStreams(String streamName, boolean verbose, int count) throws HdacException {
		List<Stream> streams = new ArrayList<Stream>();

		Object objectStreams = executeListStreams(streamName, verbose, count);
		if (verifyInstance(objectStreams, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectStreams, Stream.class)) {
			streams = StreamFormatter.formatStreams((ArrayList<Object>) objectStreams);
		}

		return streams;

	}

	/**
	 * {@link #listStreams(String, boolean, int, int)} without start and default
	 * count = 10
	 * 
	 * @param streamName
	 * @param verbose
	 * @return
	 * @throws HdacException
	 */
	public List<Stream> listStreams(String streamName, boolean verbose) throws HdacException {
		List<Stream> streams = new ArrayList<Stream>();

		Object objectStreams = executeListStreams(streamName, verbose, 10);
		if (verifyInstance(objectStreams, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectStreams, Stream.class)) {
			streams = StreamFormatter.formatStreams((ArrayList<Object>) objectStreams);
		}

		return streams;

	}

	/**
	 * {@link #listStreams(String, boolean, int, int)} without start and default
	 * count = 10 and defautl verbose = false
	 * 
	 * @param streamName
	 * @return
	 * @throws HdacException
	 */
	public List<Stream> listStreams(String streamName) throws HdacException {
		List<Stream> streams = new ArrayList<Stream>();

		Object objectStreams = executeListStreams(streamName, false, 10);
		if (verifyInstance(objectStreams, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectStreams, Stream.class)) {
			streams = StreamFormatter.formatStreams((ArrayList<Object>) objectStreams);
		}

		return streams;

	}

	/**
	 * liststreamkeys "stream-identifier" ( key(s) verbose count start
	 * local-ordering )
	 * 
	 * Returns stream keys.
	 * 
	 * Arguments: 1. "stream-identifier"(string, required) Stream identifier - one
	 * of the following: stream txid, stream reference, stream name. 2. "key"
	 * (string, optional, default=*) Stream key or 2. key(s) (array, optional) A
	 * json array of stream keys 3. verbose (boolean, optional, default=false) If
	 * true, returns extended information about key 4. count (number, optional,
	 * default=INT_MAX - all) The number of items to display 5. start (number,
	 * optional, default=-count - last) Start from specific item, 0 based, if
	 * negative - from the end 6. local-ordering (boolean, optional, default=false)
	 * If true, items appear in the order they were processed by the wallet, if
	 * false - in the order they apppear in blockchain
	 * 
	 * Result: "stream-keys" (array) List of stream keys.
	 * 
	 * @param streamName
	 * @param key
	 * @param verbose
	 * @param count
	 * @param start
	 * @return
	 * @throws HdacException
	 */
	public List<StreamKey> listStreamKeys(String streamName, String key, boolean verbose, int count, int start)
			throws HdacException {
		List<StreamKey> streamKeys = new ArrayList<StreamKey>();

		Object objectStreamKeys = executeListStreamKeys(streamName, key, verbose, count, start);
		if (verifyInstance(objectStreamKeys, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectStreamKeys, StreamKey.class)) {
			streamKeys = StreamFormatter.formatStreamKeys((ArrayList<Object>) objectStreamKeys);
		}

		return streamKeys;
	}

	/**
	 * {@link listStreamKeys(String streamName, String key, boolean verbose, int
	 * count, int start)} without start
	 * 
	 * @param streamName
	 * @param key
	 * @param verbose
	 * @param count
	 * @return
	 * @throws HdacException
	 */
	public List<StreamKey> listStreamKeys(String streamName, String key, boolean verbose, int count)
			throws HdacException {
		List<StreamKey> streamKeys = new ArrayList<StreamKey>();

		Object objectStreamKeys = executeListStreamKeys(streamName, key, verbose, count);
		if (verifyInstance(objectStreamKeys, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectStreamKeys, StreamKey.class)) {
			streamKeys = StreamFormatter.formatStreamKeys((ArrayList<Object>) objectStreamKeys);
		}

		return streamKeys;
	}

	/**
	 * {@link listStreamKeys(String streamName, String key, boolean verbose, int
	 * count, int start)} without start, with count = 10
	 * 
	 * @param streamName
	 * @param key
	 * @param verbose
	 * @param count
	 * @return
	 * @throws HdacException
	 */
	public List<StreamKey> listStreamKeys(String streamName, String key, boolean verbose) throws HdacException {
		return listStreamKeys(streamName, key, verbose, 10);
	}

	/**
	 * {@link listStreamKeys(String streamName, String key, boolean verbose, int
	 * count, int start)} without start, with count = 10, verbse = false
	 * 
	 * @param streamName
	 * @param key
	 * @param verbose
	 * @param count
	 * @return
	 * @throws HdacException
	 */
	public List<StreamKey> listStreamKeys(String streamName, String key) throws HdacException {
		return listStreamKeys(streamName, key, false, 10);
	}

	/**
	 * {@link listStreamKeys(String streamName, String key, boolean verbose, int
	 * count, int start)} without start, with count = 10, verbse = false, keys = "*"
	 * 
	 * @param streamName
	 * @param key
	 * @param verbose
	 * @param count
	 * @return
	 * @throws HdacException
	 */
	public List<StreamKey> listStreamKeys(String streamName) throws HdacException {
		return listStreamKeys(streamName, "*", false, 10);
	}

	/**
	 * liststreamkeyitems "stream-identifier" "key" ( verbose count start
	 * local-ordering )
	 * 
	 * Returns stream items for specific key.
	 * 
	 * Arguments: 1. "stream-identifier"(string, required) Stream identifier - one
	 * of the following: stream txid, stream reference, stream name. 2. "key"
	 * (string, required) Stream key 3. verbose (boolean, optional, default=false)
	 * If true, returns information about item transaction 4. count (number,
	 * optional, default=10) The number of items to display 5. start (number,
	 * optional, default=-count - last) Start from specific item, 0 based, if
	 * negative - from the end 6. local-ordering (boolean, optional, default=false)
	 * If true, items appear in the order they were processed by the wallet, if
	 * false - in the order they appear in blockchain
	 * 
	 * Result: "stream-items" (array) List of stream items for specific key.
	 * 
	 * @param streamName
	 * @param key
	 * @param verbose
	 * @param count
	 * @param start
	 * @return
	 * @throws HdacException
	 */
	public List<StreamKeyItem> listStreamKeyItems(String streamName, String key, boolean verbose, int count, int start)
			throws HdacException {
		List<StreamKeyItem> streamKeyItems = new ArrayList<StreamKeyItem>();

		Object objectStreamKeyItems = executeListStreamKeyItems(streamName, key, verbose, count, start);
		if (verifyInstance(objectStreamKeyItems, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectStreamKeyItems, StreamKeyItem.class)) {
			streamKeyItems = StreamFormatter.formatStreamKeyItems((ArrayList<Object>) objectStreamKeyItems);
		}

		return streamKeyItems;
	}

	/**
	 * liststreamkeyitems "stream-identifier" "key" ( verbose count start
	 * local-ordering )
	 * 
	 * Returns stream items for specific key.
	 * 
	 * Arguments: 1. "stream-identifier"(string, required) Stream identifier - one
	 * of the following: stream txid, stream reference, stream name. 2. "key"
	 * (string, required) Stream key 3. verbose (boolean, optional, default=false)
	 * If true, returns information about item transaction 4. count (number,
	 * optional, default=10) The number of items to display 5. start (number,
	 * optional, default=-count - last) Start from specific item, 0 based, if
	 * negative - from the end 6. local-ordering (boolean, optional, default=false)
	 * If true, items appear in the order they were processed by the wallet, if
	 * false - in the order they appear in blockchain
	 * 
	 * Result: "stream-items" (array) List of stream items for specific key.
	 * 
	 * @param streamName
	 * @param key
	 * @param verbose
	 * @param count
	 *            * @return
	 * @throws HdacException
	 */
	public List<StreamKeyItem> listStreamKeyItems(String streamName, String key, boolean verbose, int count)
			throws HdacException {
		List<StreamKeyItem> streamKeyItems = new ArrayList<StreamKeyItem>();

		Object objectStreamKeyItems = executeListStreamKeyItems(streamName, key, verbose, count);
		if (verifyInstance(objectStreamKeyItems, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectStreamKeyItems, StreamKeyItem.class)) {
			streamKeyItems = StreamFormatter.formatStreamKeyItems((ArrayList<Object>) objectStreamKeyItems);
		}

		return streamKeyItems;
	}

	/**
	 * liststreamkeyitems "stream-identifier" "key" ( verbose count start
	 * local-ordering )
	 * 
	 * Returns stream items for specific key.
	 * 
	 * Arguments: 1. "stream-identifier"(string, required) Stream identifier - one
	 * of the following: stream txid, stream reference, stream name. 2. "key"
	 * (string, required) Stream key 3. verbose (boolean, optional, default=false)
	 * If true, returns information about item transaction 4. count (number,
	 * optional, default=10) The number of items to display 5. start (number,
	 * optional, default=-count - last) Start from specific item, 0 based, if
	 * negative - from the end 6. local-ordering (boolean, optional, default=false)
	 * If true, items appear in the order they were processed by the wallet, if
	 * false - in the order they appear in blockchain
	 * 
	 * Result: "stream-items" (array) List of stream items for specific key.
	 * 
	 * @param streamName
	 * @param key
	 * @param verbose
	 * @param count
	 *            = 10 * @return
	 * @throws HdacException
	 */
	public List<StreamKeyItem> listStreamKeyItems(String streamName, String key, boolean verbose)
			throws HdacException {
		return listStreamKeyItems(streamName, key, verbose, 10);
	}

	/**
	 * liststreamkeyitems "stream-identifier" "key" ( verbose count start
	 * local-ordering )
	 * 
	 * Returns stream items for specific key.
	 * 
	 * Arguments: 1. "stream-identifier"(string, required) Stream identifier - one
	 * of the following: stream txid, stream reference, stream name. 2. "key"
	 * (string, required) Stream key 3. verbose (boolean, optional, default=false)
	 * If true, returns information about item transaction 4. count (number,
	 * optional, default=10) The number of items to display 5. start (number,
	 * optional, default=-count - last) Start from specific item, 0 based, if
	 * negative - from the end 6. local-ordering (boolean, optional, default=false)
	 * If true, items appear in the order they were processed by the wallet, if
	 * false - in the order they appear in blockchain
	 * 
	 * Result: "stream-items" (array) List of stream items for specific key.
	 * 
	 * @param streamName
	 * @param key
	 * @param verbose
	 *            = false
	 * @param count
	 *            = 10 * @return
	 * @throws HdacException
	 */
	public List<StreamKeyItem> listStreamKeyItems(String streamName, String key) throws HdacException {
		return listStreamKeyItems(streamName, key, false, 10);
	}

	/**
	 * liststreamkeyitems "stream-identifier" ( verbose count start
	 * local-ordering )
	 *
	 * Returns stream items.
	 *
	 * Arguments: 1. "stream-identifier"(string, required) Stream identifier - one
	 * of the following: stream txid, stream reference, stream name. 2. verbose (boolean, optional, default=false)
	 * If true, returns information about item transaction 3. count (number,
	 * optional, default=10) The number of items to display 4. start (number,
	 * optional, default=-count - last) Start from specific item, 0 based, if
	 * negative - from the end 5. local-ordering (boolean, optional, default=false)
	 * If true, items appear in the order they were processed by the wallet, if
	 * false - in the order they appear in blockchain
	 *
	 * Result: "stream-items" (array) List of stream items.
	 *
	 * @param streamName
	 * @param verbose
	 * @param count
	 * @param start
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method changed for ver 0.92.
	 */
	public List<StreamKeyItem> listStreamItems(String streamName, boolean verbose, int count, int start)
			throws HdacException {
		List<StreamKeyItem> streamKeyItems = new ArrayList<StreamKeyItem>();

		Object objectStreamKeyItems = executeListStreamItems(streamName, verbose, count, start);
		if (verbose == true) {
			streamKeyItems = StreamFormatter.formatStreamKeyItems((ArrayList<Object>) objectStreamKeyItems);
			return streamKeyItems;
		}
		streamKeyItems = StreamFormatter.formatStreamKeyItemsFalse((ArrayList<Object>) objectStreamKeyItems);
		return streamKeyItems;
	}
	
	/**
	 * liststreamkeyitems "stream-identifier" ( verbose count start
	 * local-ordering )
	 *
	 * Returns stream items.
	 *
	 * Arguments: 1. "stream-identifier"(string, required) Stream identifier - one
	 * of the following: stream txid, stream reference, stream name. 2. verbose (boolean, optional, default=false)
	 * If true, returns information about item transaction 3. count (number,
	 * optional, default=10) The number of items to display 4. start (number,
	 * optional, default=-count - last) Start from specific item, 0 based, if
	 * negative - from the end 5. local-ordering (boolean, optional, default=false)
	 * If true, items appear in the order they were processed by the wallet, if
	 * false - in the order they appear in blockchain
	 *
	 * Result: "stream-items" (array) List of stream items.
	 *
	 * @param streamName
	 * @param verbose
	 * @param count
	 * @return
	 * @throws HdacException
	 */
	public List<StreamKeyItem> listStreamItems(String streamName, boolean verbose, int count)
			throws HdacException {
		List<StreamKeyItem> streamKeyItems = new ArrayList<StreamKeyItem>();

		Object objectStreamKeyItems = executeListStreamItems(streamName, verbose, count);
		if (verifyInstance(objectStreamKeyItems, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectStreamKeyItems, StreamKeyItem.class)) {
			streamKeyItems = StreamFormatter.formatStreamKeyItems((ArrayList<Object>) objectStreamKeyItems);
		}

		return streamKeyItems;
	}

	/**
	 * liststreamkeyitems "stream-identifier" ( verbose count start
	 * local-ordering )
	 *
	 * Returns stream items.
	 *
	 * Arguments: 1. "stream-identifier"(string, required) Stream identifier - one
	 * of the following: stream txid, stream reference, stream name. 2. verbose (boolean, optional, default=false)
	 * If true, returns information about item transaction 3. count (number,
	 * optional, default=10) The number of items to display 4. start (number,
	 * optional, default=-count - last) Start from specific item, 0 based, if
	 * negative - from the end 5. local-ordering (boolean, optional, default=false)
	 * If true, items appear in the order they were processed by the wallet, if
	 * false - in the order they appear in blockchain
	 *
	 * Result: "stream-items" (array) List of stream items.
	 *
	 * @param streamName
	 * @param verbose
	 * @param count
	 *            = 10 * @return
	 * @throws HdacException
	 */
	public List<StreamKeyItem> listStreamItems(String streamName, boolean verbose)
			throws HdacException {
		return listStreamItems(streamName, verbose, 10);
	}

	/**
	 * liststreamkeyitems "stream-identifier" ( verbose count start
	 * local-ordering )
	 *
	 * Returns stream items.
	 *
	 * Arguments: 1. "stream-identifier"(string, required) Stream identifier - one
	 * of the following: stream txid, stream reference, stream name. 2. verbose (boolean, optional, default=false)
	 * If true, returns information about item transaction 3. count (number,
	 * optional, default=10) The number of items to display 4. start (number,
	 * optional, default=-count - last) Start from specific item, 0 based, if
	 * negative - from the end 5. local-ordering (boolean, optional, default=false)
	 * If true, items appear in the order they were processed by the wallet, if
	 * false - in the order they appear in blockchain
	 *
	 * Result: "stream-items" (array) List of stream items.
	 *
	 * @param streamName
	 * @param verbose
	 *            = false
	 * @param count
	 *            = 10 * @return
	 * @throws HdacException
	 */
	public List<StreamKeyItem> listStreamItems(String streamName) throws HdacException {
		return listStreamItems(streamName, false, 10);
	}

	/**
	 * publish "stream-identifier" "key" data-hex
	 * 
	 * Publishes stream item
	 * 
	 * 
	 * Arguments: 1. "stream-identifier" (string, required) Stream identifier - one
	 * of the following: stream txid, stream reference, stream name. 2. "key"
	 * (string, required) Item key 3. data-hex (string, required) Item data hex
	 * string
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param streamName
	 * @param key
	 * @param dataHex
	 *            : data in hexadecimal in string format
	 * @return
	 * @throws HdacException
	 */
	public String publish(String streamName, String key, String dataHex) throws HdacException {
		String stringPublish = "";

		Object objectPublish = executePublish(streamName, key, dataHex);
		if (verifyInstance(objectPublish, String.class)) {
			stringPublish = (String) objectPublish;
		}

		return stringPublish;
	}
	
	/**
	 * {@link #publish(String, String, String)} with control over the from-address used to
	 * used to publish
	 * 
	 * @param addressFrom
	 *            (Address) The from-address used to publish
	 * @param streamName
	 *            (String) The name of the stream
	 * @param key
	 *            (String) The key of the item
	 * @param dataHex
	 *            (String) Data in hexadecimal
	 * @return (String) The transaction id
	 * @throws HdacException
	 */
	
	public String publishFrom(Address addressFrom, String streamName, String key, String dataHex) throws HdacException {
		return publishFrom(addressFrom.getAddress(), streamName, key, dataHex);
	}
	
	/**
	 * {@link #PublishFrom(Address, String, String, String)} with address in format string
	 * 
	 * @param addressFrom
	 *            (String) The from-address used to publish
	 * @param streamName
	 *            (String) The name of the stream
	 * @param key
	 *            (String) The key of the item
	 * @param dataHex
	 *            (String) Data in hexadecimal
	 * @return (String) The transaction id
	 * @throws HdacException
	 */
	
	public String publishFrom(String addressFrom, String streamName, String key, String dataHex) throws HdacException {
		String stringPublish = "";

		Object objectPublish = executePublishFrom(addressFrom, streamName, key, dataHex);
		if (verifyInstance(objectPublish, String.class)) {
			stringPublish = (String) objectPublish;
		}

		return stringPublish;
	}
	
	/**
	 * subscribe entity-identifier(s) ( rescan )
	 * 
	 * Subscribes to the stream.
	 * 
	 * Arguments: 1. "stream-identifier" (string, required) Stream identifier - one
	 * of the following: stream txid, stream reference, stream name. or 1.
	 * "asset-identifier" (string, required) Asset identifier - one of the
	 * following: asset txid, asset reference, asset name. or 1.
	 * entity-identifier(s) (array, optional) A json array of stream or asset
	 * identifiers 2. rescan (boolean, optional, default=true) Rescan the wallet for
	 * transactions
	 * 
	 * Note: This call can take minutes to complete if rescan is true.
	 * 
	 * @param streamName
	 * @throws HdacException
	 */
	public void subscribe(String streamName) throws HdacException {
		executeSubscribe(streamName);
	}

	/**
	 * unsubscribe entity-identifier(s)
	 * 
	 * Unsubscribes from the stream.
	 * 
	 * Arguments: 1. "stream-identifier" (string, required) Stream identifier - one
	 * of the following: stream txid, stream reference, stream name. or 1.
	 * "asset-identifier" (string, required) Asset identifier - one of the
	 * following: asset txid, asset reference, asset name. or 1.
	 * entity-identifier(s) (array, optional) A json array of stream or asset
	 * identifiers
	 * 
	 * @param streamName
	 * @throws HdacException
	 */
	public void unsubscribe(String streamName) throws HdacException {
		executeUnsubscribe(streamName);
	}

	/**
	 * liststreampublisheritems "stream-identifier" "address" ( verbose count start local-ordering )
	 * 
	 * Returns stream items for specific publisher.
	 * 
	 * 
	 * Arguments: 1. "stream-identifier"  (string, required) Stream identifier - one of 
	 * the following: stream txid, stream reference, stream name. 2. "address" 
	 * (string, required) Publisher address 3. verbose (boolean, optional, default=false) 
	 * If true, returns information about item transaction 4. count (number, optional, 
	 * default=10) The number of items to display 5. start (number, optional, default=-count - last) 
	 * Start from specific item, 0 based, if negative - from the end 6. local-ordering 
	 * (boolean, optional, default=false) If true, items appear in the order they were processed 
	 * by the wallet, if false - in the order they appear in blockchain
	 * 
	 * Result: "stream-items"  (array) List of stream items for specific publisher.
	 * 
	 * @param stream-identifier
	 * @param address
	 * @param verbose
	 * @param count
	 * @param start
	 * @param local-ordering
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public List<StreamKeyItem> listStreamPublisherItems(String streamName, String address, boolean verbose, int count,
			int start) throws HdacException{
		List<StreamKeyItem> streamKeyItems = new ArrayList<StreamKeyItem>();

		Object objectStreamKeyItems = executeListStreamPublisherItems(streamName, address,verbose, count, start);
		if (verifyInstance(objectStreamKeyItems, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectStreamKeyItems, StreamKeyItem.class)) {
			streamKeyItems = StreamFormatter.formatStreamKeyItems((ArrayList<Object>) objectStreamKeyItems);
		}
		
		return streamKeyItems;
	}

	
	
}