/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command.builders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hdactech.command.HdacException;
import com.hdactech.command.tools.HdacTestParameter;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class QueryBuilderStream extends QueryBuilderCommon {
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
	 * Treating required parameter.
	 */
	protected Object executeCreate(String entityType, String streamName, boolean open) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("entityType", entityType);
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);

		return execute(CommandEnum.CREATE, "stream", streamName, open);
	}

	/**
	 * {@link #executeCreate(String, boolean)} with control over the from-address
	 * used to create the stream
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
	
	/*
	 * Treating required parameter.
	 */
	protected Object executeCreateFrom(String addressFrom, String entityType, String streamName, boolean open) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);
		HdacTestParameter.isNotNullOrEmpty("addressFrom", addressFrom);
		HdacTestParameter.isNotNullOrEmpty("entityType", entityType);

		return execute(CommandEnum.CREATEFROM, addressFrom, entityType, streamName, open);
	}

	/**
	 * 
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
	 * @param streams
	 * @param key
	 * @param verbose
	 * @param count
	 * @param start
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Treating required parameter.
	 */
	protected Object executeListStreams(String[] streams, boolean verbose, long count, long start)
			throws HdacException {
		
		List<String> streamList;
		HdacTestParameter.valueIsNotNegative("count", count);
		
		if(streams != null) {
			streamList = new ArrayList<String>(Arrays.asList(streams));
		} else {
			String emptyStreamsList = "*";
			
			return execute(CommandEnum.LISTSTREAMS, emptyStreamsList, verbose, count, start);
		}

		return execute(CommandEnum.LISTSTREAMS, streams, verbose, count, start);
	}

	/**
	 * {@link #executeListStreams(String, boolean, int, int)} without start
	 * 
	 * @param streamName
	 * @param verbose
	 * @param count
	 * @param start
	 * @return
	 * @throws HdacException
	 */
	protected Object executeListStreams(String streamName, boolean verbose, int count) throws HdacException {
		if (streamName == null || streamName.isEmpty()) {
			streamName = "*";
		}
		HdacTestParameter.valueIsPositive("count", count);

		return execute(CommandEnum.LISTSTREAMS, streamName, verbose, count);

	}

	/**
	 * 
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
	protected Object executeListStreamKeys(String streamName, String key, boolean verbose, int count, int start)
			throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);
		HdacTestParameter.isNotNullOrEmpty("key", key);
		HdacTestParameter.valueIsPositive("count", count);

		return execute(CommandEnum.LISTSTREAMKEYS, streamName, key, verbose, count, start);
	}

	/**
	 * 
	 * {@link executeListStreamKeys(String streamName, String key, boolean verbose,
	 * int count, int start)} without start
	 * 
	 * @param streamName
	 * @param key
	 * @param verbose
	 * @param count
	 * @return
	 * @throws HdacException
	 */
	protected Object executeListStreamKeys(String streamName, String key, boolean verbose, int count)
			throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);
		HdacTestParameter.isNotNullOrEmpty("key", key);
		HdacTestParameter.valueIsPositive("count", count);

		return execute(CommandEnum.LISTSTREAMKEYS, streamName, key, verbose, count);
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
	protected Object executeListStreamKeyItems(String streamName, String key, boolean verbose, int count, int start)
			throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);
		HdacTestParameter.isNotNullOrEmpty("key", key);
		HdacTestParameter.valueIsPositive("count", count);

		return execute(CommandEnum.LISTSTREAMKEYITEMS, streamName, key, verbose, count, start);
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
	protected Object executeListStreamKeyItems(String streamName, String key, boolean verbose, int count)
			throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);
		HdacTestParameter.isNotNullOrEmpty("key", key);
		HdacTestParameter.valueIsPositive("count", count);

		return execute(CommandEnum.LISTSTREAMKEYITEMS, streamName, key, verbose, count);
	}

	/**
	 * liststreamitems "stream-identifier" ( verbose count start
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
	 * Result: "stream-items" (array) List of stream items for specific key.
	 *
	 * @param streamName
	 * @param verbose
	 * @param count
	 * @param start
	 * @return
	 * @throws HdacException
	 */
	protected Object executeListStreamItems(String streamName, boolean verbose, int count, int start)
			throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);
		HdacTestParameter.valueIsPositive("count", count);

		return execute(CommandEnum.LISTSTREAMITEMS, streamName, verbose, count, start);
	}
	
	/*
	 * Method created for ver 0.92.
	 */
	protected Object executeListStreamPublisherItems(String streamName, String address, boolean verbose, int count, int start)
			throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);
		HdacTestParameter.isNotNullOrEmpty("address", address);

		return execute(CommandEnum.LISTSTREAMPUBLISHERITEMS, streamName,address, verbose, count, start);
	}

	/**
	 * liststreamitems "stream-identifier" ( verbose count start
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
	 * Result: "stream-items" (array) List of stream items for specific key.
	 *
	 * @param streamName
	 * @param verbose
	 * @param count
	 * @return
	 * @throws HdacException
	 */
	protected Object executeListStreamItems(String streamName, boolean verbose, int count)
			throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);
		HdacTestParameter.valueIsPositive("count", count);

		return execute(CommandEnum.LISTSTREAMITEMS, streamName, verbose, count);
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
	protected Object executePublish(String streamName, String key, String dataHex) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);
		HdacTestParameter.isNotNullOrEmpty("key", key);
		HdacTestParameter.isNotNullOrEmpty("dataHex", dataHex);

		return execute(CommandEnum.PUBLISH, streamName, key, dataHex);
	}

	/**
	 * {@link #executePublish(String, String, String)} with control over the
	 * from-address used to publish
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
	protected Object executePublishFrom(String addressFrom ,String streamName, String key, String dataHex) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("addressFrom", addressFrom);
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);
		HdacTestParameter.isNotNullOrEmpty("key", key);
		HdacTestParameter.isNotNullOrEmpty("dataHex", dataHex);

		return execute(CommandEnum.PUBLISHFROM, addressFrom, streamName, key, dataHex);
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
	protected void executeSubscribe(String streamName) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);

		execute(CommandEnum.SUBSCRIBE, streamName);
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
	protected void executeUnsubscribe(String streamName) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("streamName", streamName);

		execute(CommandEnum.UNSUBSCRIBE, streamName);
	}
        
        /****
         * subscribe address to stream with rescan param
         * @param streamname
         * @param rescan 
         */
        protected void executeSubscribe(String streamname,boolean rescan) throws HdacException, HdacException{
           HdacTestParameter.isNotNullOrEmpty("streamname", streamname);
           execute(CommandEnum.SUBSCRIBE,streamname,rescan);
        }
        /****
         *  
         * @param steamname
         * @param verbose
         * @param count
         * @param start
         * @param localordering 
         * @return Object
         */
        protected Object executeListStreamItems(String streamname,boolean verbose,int count,int start,boolean localordering) throws HdacException{
            HdacTestParameter.isNotNullOrEmpty("streamname", streamname);
            return execute(CommandEnum.LISTSTREAMITEMS,streamname,verbose,count,start,localordering);
        }
        /****
         * 
         * @param streamname
         * @param txid
         * @param verbose
         * @return 
         */
        protected Object executeGetstreamitem(String streamname,String txid,boolean verbose) throws HdacException{
            HdacTestParameter.isNotNullOrEmpty("streamname", streamname);
            HdacTestParameter.isNotNullOrEmpty("txid", txid);
            return execute(CommandEnum.GETSTREAMITEM,streamname,txid,verbose);
        }
}