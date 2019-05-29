/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hdactech.command.HdacException;
import com.hdactech.command.tools.HdacTestParameter;
import com.hdactech.object.AddressBalance;
import com.hdactech.object.PrevTx;
import com.hdactech.object.PreviousTx;
import com.hdactech.object.queryobjects.DataParam;
import com.hdactech.object.queryobjects.RawParam;
import com.hdactech.object.queryobjects.TxIdVout;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class QueryBuilderRAWTransaction extends QueryBuilderCommon {

	/**
	 * appendrawchange "hexstring" address ( native-fee )
	 * 
	 * Appends change output to raw transaction, containing any remaining assets / native currency in the inputs that
	 * are not already sent to other outputs.
	 * 
	 * Arguments:
	 * 1. "hexstring" (string, required) The hex string of the raw transaction)
	 * 2. "address" (string, required) The address to send the change to.
	 * 3. "native-fee" (numeric, optional) Native currency value deducted from that amount so it becomes a transaction
	 * fee. Default - calculated automatically
	 * 
	 * Result:
	 * "transaction" (string) hex string of the transaction
	 * 
	 * Adds a change output to the raw transaction in hexstring given by a previous call to createrawtransaction
	 * 
	 * @param hexString
	 * @param address
	 * @return
	 * @throws HdacException
	 */
	protected Object executeAppendRawChange(String hexString, String address, int nativefee) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("hexString", hexString);
		HdacTestParameter.isNotNullOrEmpty("address", address);
		return execute(CommandEnum.APPENDRAWCHANGE, hexString, address, nativefee);
	}
	
	/**
	 * appendrawexchange "hex" "txid" vout ask-assets
	 * 
	 * Adds to the raw atomic exchange transaction in tx-hex given by a previous call to 
	 * createrawexchange or appendrawexchange.
	 * 
	 * Arguments:
	 * 1. "hex"                     (string, required) The transaction hex string
	 * 2. "txid"                    (string, required) Transaction ID of the output prepared by preparelockunspent.
	 * 3. vout                      (numeric, required) Output index
	 * 4. ask-assets                (object, required) A json object of assets to ask
	 *{ "asset-identifier" : asset-quantity,... }
	 * 
	 * Result:
	 * { "hex": "value",            (string) The raw transaction with signature(s) (hex-encoded string)
	 *"complete": true|false        (boolean) if exchange is completed and can be sent
	 * }
	 * 
	 * 
	 * @param hexString
	 * @param txid
	 * @param vout
	 * @param assets
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	protected Object executeAppendRawExchange(String hexString, String txid, int vout, JsonObject assets) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("hexString", hexString);
		HdacTestParameter.isNotNullOrEmpty("txid", txid);
		HdacTestParameter.valueIsNotNegative("vout", vout);
		HdacTestParameter.isNotNull("assets", assets);
		
		return execute(CommandEnum.APPENDRAWEXCHANGE, hexString, txid, vout, assets);
	}

	/**
	 * 
	 * appendrawmetadata tx-hex data-hex
	 * 
	 * Appends new OP_RETURN output to existing raw transaction
	 * Returns hex-encoded raw transaction.
	 * 
	 * Arguments:
	 * 1. "tx-hex" (string, required) The transaction hex string
	 * 2. "data-hex" (string, required) Data hex string
	 * or
	 * 2. "issue-details" (object, required) A json object with issue metadata
	 * {
	 * "name" : asset-name (string,optional) Asset name
	 * "multiple" : n (numeric,optional, default 1) Number of raw units in one displayed unit
	 * "open" : true|false (boolean, optional, default false) True if follow-on issues are allowed
	 * "details" : (object, optional) a json object with custom fields
	 * {
	 * "param-name": "param-value" (strings, required) The key is the parameter name, the value is parameter value
	 * ,...
	 * }
	 * or
	 * 2. "issuemore-details" (object, required) A json object with issuemore metadata
	 * {
	 * "details" : (object, optional) a json object with custom fields
	 * {
	 * "param-name": "param-value" (strings, required) The key is the parameter name, the value is parameter value
	 * ,...
	 * }
	 * }
	 * 
	 * Result:
	 * {
	 * "hex": "value", (string) The raw transaction with appended data output (hex-encoded string)
	 * }
	 * 
	 * Adds a metadata output (using an OP_RETURN) to the raw transaction in tx-hex given by a previous call to
	 * createrawtransaction
	 * 
	 * @param txHex
	 * @param dataHex
	 * @return
	 * @throws HdacException
	 */
	protected Object executeAppendRawMetaData(String txHex, String dataHex) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("txHex", txHex);
		HdacTestParameter.isNotNullOrEmpty("dataHex", dataHex);
		return execute(CommandEnum.APPENDRAWMETADA, txHex, dataHex);
	}
	
	/*
	 *  Method created for ver 0.92.
	 */
	protected Object executeAppendRawData(String txHex, String dataHex) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("txHex", txHex);
		HdacTestParameter.isNotNullOrEmpty("dataHex", dataHex);
		return execute(CommandEnum.APPENDRAWDATA, txHex, dataHex);
	}
	
	/*
	 *  Method created for ver 0.92.
	 */
	protected Object executeAppendRawData(String txHex, JsonObject issueDetails) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("txHex", txHex);
		HdacTestParameter.isNotNull("issueDetails", issueDetails);
		return execute(CommandEnum.APPENDRAWDATA, txHex, issueDetails);
	}
	
	/*
	 *  Method created for ver 0.92.
	 */
	protected Object executeAppendRawTransaction(String txHex, List<PreviousTx> previousTx, List<RawParam> rawParams, String[] data,
			String action) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("txHex", txHex);
		
		if (previousTx == null || previousTx.isEmpty()) {
			throw new HdacException("previousTx", "previousTx is null or empty");
		}
		
		if (rawParams == null || rawParams.isEmpty()) {
			throw new HdacException("rawParams", "rawParams is null or empty");
		}

		Map<String, Object> mapParams = new HashMap<String, Object>();
		for (RawParam param : rawParams) {
			if (param.isFilled()) {
				mapParams.put(param.getAddress(), param.getValue());
			}
		}
		
		String[] dataFormated = null;
		if (data != null) {
			dataFormated = new String[data.length];
			for (int i = 0; i < data.length; i++) {
				dataFormated[i] = data[i];
			}
		} else {
			dataFormated = new String[0];
		}
		
		if (action == null) {
			return execute(CommandEnum.APPENDRAWTX, txHex, previousTx, mapParams, dataFormated);
		} else {
			return execute(CommandEnum.APPENDRAWTX, txHex, previousTx, mapParams, dataFormated, action);
		}
		

	}

	/**
	 * createrawsendfrom from-address {"address":amount,...} ( [data] action )
	 * 
	 * Create a transaction using the given sending address.
	 * 
	 * Arguments:
	 * 1. from-address (string, required) Address to send from.
	 * 2. "addresses" (string, required) a json object with addresses as keys and amounts as values
	 * {
	 * "address":
	 * x.xxx (numeric, required) The key is the address, the value is the native currency amount
	 * or
	 * { (object) A json object of assets to send
	 * "asset-identifier" : asset-quantity
	 * ,...
	 * }
	 * or
	 * { (object) A json object describing new asset issue
	 * "issue" :
	 * {
	 * "raw" : n (numeric, required) The asset total amount in raw units
	 * ,...
	 * }
	 * ,...
	 * }
	 * or
	 * { (object) A json object describing follow-on asset issue
	 * "issuemore" :
	 * {
	 * "asset" : "asset-identifier" (string, required) Asset identifier - one of the following: issue txid. asset
	 * reference, asset name.
	 * "raw" : n (numeric, required) The asset total amount in raw units
	 * ,...
	 * }
	 * ,...
	 * }
	 * or
	 * { (object) A json object describing permission change
	 * "permissions" :
	 * {
	 * "type" : "permission(s)" (string,required) Permission strings, comma delimited. Possible values:
	 * connect,send,receive,issue,mine,admin,activate,create
	 * "startblock" (numeric, optional) Block to apply permissions from (inclusive). Default - 0
	 * "endblock" (numeric, optional) Block to apply permissions to (exclusive). Default - 4294967295
	 * "timestamp" (numeric, optional) This helps resolve conflicts between permissions assigned by the same
	 * administrator. Default - current time
	 * ,...
	 * }
	 * ,...
	 * }
	 * ,...
	 * }
	 * 3. data (array, optional) Array of hexadecimal strings or data objects, see help appendrawdata for details.
	 * 4. action (string, optional, default "") Additional actions: "lock", "sign", "lock,sign", "sign,lock", "send".
	 * 
	 * Result:
	 * "transaction" (string) hex string of the transaction (if action= "" or "lock")
	 * or
	 * { (object) A json object (if action= "sign" or "lock,sign" or "sign,lock")
	 * "hex": "value", (string) The raw transaction with signature(s) (hex-encoded string)
	 * "complete": n (numeric) if transaction has a complete set of signature (0 if not)
	 * }
	 * or
	 * "hex" (string) The transaction hash in hex (if action= "send")
	 */
	protected Object executeCreateRawSendFrom(String address, List<RawParam> rawParams, String[] data, String action)
			throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("address", address);

		if (rawParams == null || rawParams.isEmpty()) {
			throw new HdacException("rawParams", "rawParams is null or empty");
		}

		Map<String, Object> mapParams = new HashMap<String, Object>();
		for (RawParam param : rawParams) {
			if (param.isFilled()) {
				mapParams.put(param.getAddress(), param.getValue());
			}
		}

		String[] dataFormated = null;
		if (data != null) {
			dataFormated = new String[data.length];
			for (int i = 0; i < data.length; i++) {
				dataFormated[i] = data[i];
			}
		} else {
			dataFormated = new String[0];
		}

		if (action == null) {
			return execute(CommandEnum.CREATERAWSENDFROM, address, mapParams, dataFormated);
		} else {
			return execute(CommandEnum.CREATERAWSENDFROM, address, mapParams, dataFormated, action);
		}
	}

	protected Object executeCreateRawSendFrom(String address, List<Map<String, String>> rawParams)
			throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("address", address);

		if (rawParams == null || rawParams.isEmpty()) {
			throw new HdacException("rawParams", "rawParams is null or empty");
		}

		return execute(CommandEnum.CREATERAWSENDFROM, address, new Object(), rawParams, "send");
	}

	/**
	 * Creates a transaction spending the specified inputs, sending to the given addresses
	 * 
	 * createrawtransaction [{"txid":"id","vout":n},...] {"address":amount,...}
	 * 
	 * Create a transaction spending the given inputs and sending to the given addresses.
	 * Returns hex-encoded raw transaction.
	 * Note that the transaction's inputs are not signed, and
	 * it is not stored in the wallet or transmitted to the network.
	 * 
	 * Arguments:
	 * 1. "transactions" (string, required) A json array of json objects
	 * [
	 * {
	 * "txid":"id", (string, required) The transaction id
	 * "vout":n (numeric, required) The output number
	 * }
	 * ,...
	 * ]
	 * 2. "addresses" (string, required) a json object with addresses as keys and amounts as values
	 * {
	 * "address":
	 * x.xxx (numeric, required) The key is the address, the value is the native currency amount
	 * or
	 * { (object) A json object of assets to send
	 * "asset-identifier" : asset-quantity
	 * ,...
	 * }
	 * or
	 * { (object) A json object describing new asset issue
	 * "issue" :
	 * {
	 * "raw" : n (numeric, required) The asset total amount in raw units
	 * ,...
	 * }
	 * ,...
	 * }
	 * or
	 * { (object) A json object describing follow-on asset issue
	 * "issuemore" :
	 * {
	 * "asset" : "asset-identifier" (string, required) Asset identifier - one of the following: issue txid. asset
	 * reference, asset name.
	 * "raw" : n (numeric, required) The asset total amount in raw units
	 * ,...
	 * }
	 * ,...
	 * }
	 * or
	 * { (object) A json object describing permission change
	 * "permissions" :
	 * {
	 * "type" : "permission(s)" (string,required) Permission strings, comma delimited. Possible values:
	 * connect,send,receive,issue,mine,admin,activate
	 * "startblock" (numeric, optional) Block to apply permissions from (inclusive). Default - 0
	 * "endblock" (numeric, optional) Block to apply permissions to (exclusive). Default - 4294967295
	 * "timestamp" (numeric, optional) This helps resolve conflicts between permissions assigned by the same
	 * administrator. Default - current time
	 * ,...
	 * }
	 * ,...
	 * }
	 * ,...
	 * }
	 * 
	 * Result:
	 * "transaction" (string) hex string of the transaction
	 * 
	 * @param txids
	 * @param vouts
	 * @param addresses
	 * @param amounts
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	protected Object executeCreateRawTransaction(List<TxIdVout> inputs, List<AddressBalance> addessBalances, List<DataParam> data)
			throws HdacException {
		if (inputs == null || inputs.isEmpty()) {
			throw new HdacException("inputs", "inputs needed to create a RAW Transaction");
		}
		for (TxIdVout input : inputs) {
			input.isFilled();
		}

//		if (addessBalances == null || addessBalances.isEmpty()) {
//			throw new HdacException("Address Balance", "Address Balance needed to create a RAW Transaction");
//		}
		//addessBalances can be null or empty in case of call for Stream Creation : only data is present
		Map<String, Object> mapOuput = new HashMap<String, Object>();
		if (addessBalances != null && !addessBalances.isEmpty()) {
    		for (AddressBalance addessBalance : addessBalances) {
    			addessBalance.isFilled();
    			mapOuput.put(addessBalance.getAddress(), addessBalance.getValue());
    		}
		}
		
		if (data != null) {
		    List<Object> dataObject = new ArrayList<Object>();
		    for (DataParam param : data) {
		      dataObject.add(param.getFormatedvalue());
		    }
			return execute(CommandEnum.CREATERAWTRANSACTION, inputs, mapOuput, dataObject);
		} else {
			return execute(CommandEnum.CREATERAWTRANSACTION, inputs, mapOuput);
		}
	}
	
	/*
	 * Method created for ver 0.92.
	 */
	protected Object executeCreateRawTransaction(List<TxIdVout> inputs, JsonObject addresses, JsonArray data, String action) throws HdacException{
		if (inputs == null || inputs.isEmpty()) {
			throw new HdacException("inputs", "inputs needed to create a RAW Transaction");
		}
		for (TxIdVout input : inputs) {
			input.isFilled();
		}
		
		return execute(CommandEnum.CREATERAWTRANSACTION, inputs, addresses, data, action);
		
	}
	
	/*
	 * Method created for ver 0.92.
	 */
	protected Object executeCreateRawTransaction(List<TxIdVout> inputs, List<AddressBalance> addessBalances, List<DataParam> data, String action)
			throws HdacException {
		if (inputs == null || inputs.isEmpty()) {
			throw new HdacException("inputs", "inputs needed to create a RAW Transaction");
		}
		for (TxIdVout input : inputs) {
			input.isFilled();
		}

//		if (addessBalances == null || addessBalances.isEmpty()) {
//			throw new HdacException("Address Balance", "Address Balance needed to create a RAW Transaction");
//		}
		//addessBalances can be null or empty in case of call for Stream Creation : only data is present
		Map<String, Object> mapOuput = new HashMap<String, Object>();
		if (addessBalances != null && !addessBalances.isEmpty()) {
    		for (AddressBalance addessBalance : addessBalances) {
    			addessBalance.isFilled();
    			mapOuput.put(addessBalance.getAddress(), addessBalance.getValue());
    		}
		}
		
		if (data != null) {
		    List<Object> dataObject = new ArrayList<Object>();
		    for (DataParam param : data) {
		      dataObject.add(param.getFormatedvalue());
		    }
			return execute(CommandEnum.CREATERAWTRANSACTION, inputs, mapOuput, dataObject, action);
		} else {
			return execute(CommandEnum.CREATERAWTRANSACTION, inputs, mapOuput, "[]", action);
		}
	}
	
	

	/**
	 * 
	 * decoderawtransaction "hexstring"
	 * 
	 * Return a JSON object representing the serialized, hex-encoded transaction.
	 * 
	 * Arguments:
	 * 1. "hex" (string, required) The transaction hex string
	 * 
	 * Result:
	 * {
	 * "txid" : "id", (string) The transaction id
	 * "version" : n, (numeric) The version
	 * "locktime" : ttt, (numeric) The lock time
	 * "vin" : [ (array of json objects)
	 * {
	 * "txid": "id", (string) The transaction id
	 * "vout": n, (numeric) The output number
	 * "scriptSig": { (json object) The script
	 * "asm": "asm", (string) asm
	 * "hex": "hex" (string) hex
	 * },
	 * "sequence": n (numeric) The script sequence number
	 * }
	 * ,...
	 * ],
	 * "vout" : [ (array of json objects)
	 * {
	 * "value" : x.xxx, (numeric) The value in btc
	 * "n" : n, (numeric) index
	 * "scriptPubKey" : { (json object)
	 * "asm" : "asm", (string) the asm
	 * "hex" : "hex", (string) the hex
	 * "reqSigs" : n, (numeric) The required sigs
	 * "type" : "pubkeyhash", (string) The type, eg 'pubkeyhash'
	 * "addresses" : [ (json array of string)
	 * "12tvKAXCxZjSmdNbao16dKXC8tRWfcF5oc" (string) address
	 * ,...
	 * ]
	 * }
	 * }
	 * ,...
	 * ],
	 * }
	 * 
	 * @param hex
	 * @return
	 * @throws HdacException
	 */
	protected Object executeDecodeRawTransaction(String hex) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("hex", hex);
		return execute(CommandEnum.DECODERAWTRANSACTION, hex);
	}
	
	/**
	 * Created by Justin
	 * @param hex
	 * @param verbose
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	protected Object executeDecodeRawExchange(String hex, boolean verbose) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("hex", hex);
		return execute(CommandEnum.DECODERAWEXCHANGE, hex, verbose);
	}

	/**
	 * getrawchangeaddress
	 * 
	 * Returns a new address, for receiving change.
	 * This is for use with raw transactions, NOT normal use.
	 * 
	 * Result:
	 * "address" (string) The address
	 * 
	 * @return String address
	 * @throws HdacException
	 */
	protected Object executeGetRawChangeAddress() throws HdacException {
		return execute(CommandEnum.GETRAWCHANGEADDRESS);
	}

	/**
	 * getrawtransaction "txid" ( verbose )
	 * 
	 * NOTE: By default this function only works sometimes. This is when the tx is in the mempool
	 * or there is an unspent output in the utxo for this transaction. To make it always work,
	 * you need to maintain a transaction index, using the -txindex command line option.
	 * 
	 * Return the raw transaction data.
	 * 
	 * If verbose=0, returns a string that is serialized, hex-encoded data for 'txid'.
	 * If verbose is non-zero, returns an Object with information about 'txid'.
	 * 
	 * Arguments:
	 * 1. "txid" (string, required) The transaction id
	 * 2. verbose (numeric, optional, default=0) If 0, return a string, other return a json object
	 * 
	 * Result (if verbose is not set or set to 0):
	 * "data" (string) The serialized, hex-encoded data for 'txid'
	 * 
	 * Result (if verbose > 0):
	 * {
	 * "hex" : "data", (string) The serialized, hex-encoded data for 'txid'
	 * "txid" : "id", (string) The transaction id (same as provided)
	 * "version" : n, (numeric) The version
	 * "locktime" : ttt, (numeric) The lock time
	 * "vin" : [ (array of json objects)
	 * {
	 * "txid": "id", (string) The transaction id
	 * "vout": n, (numeric)
	 * "scriptSig": { (json object) The script
	 * "asm": "asm", (string) asm
	 * "hex": "hex" (string) hex
	 * },
	 * "sequence": n (numeric) The script sequence number
	 * }
	 * ,...
	 * ],
	 * "vout" : [ (array of json objects)
	 * {
	 * "value" : x.xxx, (numeric) The value in btc
	 * "n" : n, (numeric) index
	 * "scriptPubKey" : { (json object)
	 * "asm" : "asm", (string) the asm
	 * "hex" : "hex", (string) the hex
	 * "reqSigs" : n, (numeric) The required sigs
	 * "type" : "pubkeyhash", (string) The type, eg 'pubkeyhash'
	 * "addresses" : [ (json array of string)
	 * "address" (string) address
	 * ,...
	 * ]
	 * }
	 * }
	 * ,...
	 * ],
	 * "blockhash" : "hash", (string) the block hash
	 * "confirmations" : n, (numeric) The confirmations
	 * "time" : ttt, (numeric) The transaction time in seconds since epoch (Jan 1 1970 GMT)
	 * "blocktime" : ttt (numeric) The block time in seconds since epoch (Jan 1 1970 GMT)
	 * }
	 * 
	 * @param txid
	 * @param verbose
	 *            (0 : false / 1 : true)
	 * @return
	 * @throws HdacException
	 */
	protected Object executeGetRawTransaction(String txid, int verbose) throws HdacException {
		return execute(CommandEnum.GETRAWTRANSACTION, txid, verbose);
	}

	/**
	 * 
	 * sendrawtransaction "hexstring" ( allowhighfees )
	 * 
	 * Submits raw transaction (serialized, hex-encoded) to local node and network.
	 * 
	 * Also see createrawtransaction and signrawtransaction calls.
	 * 
	 * Arguments:
	 * 1. "hexstring" (string, required) The hex string of the raw transaction)
	 * 2. allowhighfees (boolean, optional, default=false) Allow high fees
	 * 
	 * Result:
	 * "hex" (string) The transaction hash in hex
	 * 
	 * Validates the raw transaction in hexstring and transmits it to the network, returning the txid.
	 * 
	 * @param hexString
	 * @return
	 * @throws HdacException
	 */
	protected Object executeSendRawTransaction(String hexString) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("hexString", hexString);
		return execute(CommandEnum.SENDRAWTRANSACTION, hexString);
	}

	/**
	 * 
	 * signrawtransaction "hexstring" ( [{"txid":"id","vout":n,"scriptPubKey":"hex","redeemScript":"hex"},...]
	 * ["privatekey1",...] sighashtype )
	 * 
	 * Sign inputs for raw transaction (serialized, hex-encoded).
	 * The second optional argument (may be null) is an array of previous transaction outputs that
	 * this transaction depends on but may not yet be in the block chain.
	 * The third optional argument (may be null) is an array of base58-encoded private
	 * keys that, if given, will be the only keys used to sign the transaction.
	 * 
	 * 
	 * Arguments:
	 * 1. "hexstring" (string, required) The transaction hex string
	 * 2. "prevtxs" (string, optional) An json array of previous dependent transaction outputs
	 * [ (json array of json objects, or 'null' if none provided)
	 * {
	 * "txid":"id", (string, required) The transaction id
	 * "vout":n, (numeric, required) The output number
	 * "scriptPubKey": "hex", (string, required) script key
	 * "redeemScript": "hex" (string, required for P2SH) redeem script
	 * }
	 * ,...
	 * ]
	 * 3. "privatekeys" (string, optional) A json array of base58-encoded private keys for signing
	 * [ (json array of strings, or 'null' if none provided)
	 * "privatekey" (string) private key in base58-encoding
	 * ,...
	 * ]
	 * 4. "sighashtype" (string, optional, default=ALL) The signature hash type. Must be one of
	 * "ALL"
	 * "NONE"
	 * "SINGLE"
	 * "ALL|ANYONECANPAY"
	 * "NONE|ANYONECANPAY"
	 * "SINGLE|ANYONECANPAY"
	 * 
	 * Result:
	 * {
	 * "hex": "value", (string) The raw transaction with signature(s) (hex-encoded string)
	 * "complete": n (numeric) if transaction has a complete set of signature (0 if not)
	 * }
	 * 
	 * Signs the raw transaction in hexstring, often provided by a previous call to createrawtransaction and
	 * (optionally) appendrawmetadata. * @param hexString
	 * 
	 * @return
	 * @throws HdacException
	 */
	protected Object executeSignRawTransaction(String hexString) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("hexString", hexString);
		return execute(CommandEnum.SIGNRAWTRANSACTION, hexString);
	}
	
	/*
	 * Parameter added for ver 0.92.
	 */
	protected Object executeSignRawTransaction(String hexString, List<PrevTx> prevtxs, List<String> privatekeys, String sighashtype) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("hexString", hexString);
		return execute(CommandEnum.SIGNRAWTRANSACTION, hexString, prevtxs, privatekeys, sighashtype);
	}
	
	/*
	 * Method created for ver 0.92.
	 */
	protected Object executeDisableRawTransaction(String txhex) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("txhex", txhex);
		return execute(CommandEnum.DISABLERAWTRANSACTION, txhex);
	}
	
	
	/**
	 * Created by Justin
	 * @param txid
	 * @param vout
	 * @param assets
	 * @return
	 * @throws HdacException
	 */
	
	protected Object executeCreateRawExchange(String txid, int vout, JsonObject jsonAssets) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("txid", txid);
		
		return execute(CommandEnum.CREATERAWEXCHANGE, txid, vout, jsonAssets);
	}
	
	/**
	 * Created by Justin
	 * @param txid
	 * @param vout
	 * @param assets
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	protected Object executeCompleteRawRxchange(String hexString, String txid, int vout, JsonObject assets) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("hexString", hexString);
		HdacTestParameter.isNotNullOrEmpty("txid", txid);
		HdacTestParameter.valueIsNotNegative("vout", vout);
		HdacTestParameter.isNotNull("assets", assets);
		
		return execute(CommandEnum.COMPLETERAWEXCHANGE, hexString, txid, vout, assets);
	}
	
	/**
	 * Created by Justin
	 * @param txid
	 * @param vout
	 * @param assets
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	protected Object executePrepareLockUnspent(JsonObject assets, boolean lock) throws HdacException {
		
		if (assets == null) {
			throw new HdacException("assets", "assets needed to be sent");
		}
		//assets.isFilled();
		return execute(CommandEnum.PREPARELOCKUNSPENT, assets, lock);
	}
	
	/**
	 * Created by Justin
	 * @param txid
	 * @param vout
	 * @param assets
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	protected Object executePrepareLockUnspentFrom(String fromAddress, JsonObject assets, boolean lock) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("fromAddress", fromAddress);
		
		if (assets == null) {
			throw new HdacException("assets", "assets needed to be sent");
		}
		
		return execute(CommandEnum.PREPARELOCKUNSPENTFROM, fromAddress, assets, lock);
	}
}