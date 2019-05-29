/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hdactech.command.builders.QueryBuilderRAWTransaction;
import com.hdactech.object.Address;
import com.hdactech.object.AddressBalance;
import com.hdactech.object.GetTransactionRAW;
import com.hdactech.object.PrevTx;
import com.hdactech.object.PreviousTx;
import com.hdactech.object.SignedTransactionRAW;
import com.hdactech.object.TransactionRAW;
import com.hdactech.object.formatters.RawTransactionFormatter;
import com.hdactech.object.queryobjects.DataParam;
import com.hdactech.object.queryobjects.RawParam;
import com.hdactech.object.queryobjects.TxIdVout;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class RAWTransactionCommand extends QueryBuilderRAWTransaction {

	public RAWTransactionCommand(String ip, String port, String login, String password,
			RuntimeParameters runtimeparameters) {
		initialize(ip, port, login, password, runtimeparameters);
	}

	/**
	 * appendrawchange "hexstring" address ( native-fee )
	 * 
	 * Appends change output to raw transaction, containing any remaining assets /
	 * native currency in the inputs that are not already sent to other outputs.
	 * 
	 * Arguments: 1. "hexstring" (string, required) The hex string of the raw
	 * transaction) 2. "address" (string, required) The address to send the change
	 * to. 3. "native-fee" (numeric, optional) Native currency value deducted from
	 * that amount so it becomes a transaction fee. Default - calculated
	 * automatically
	 * 
	 * Result: "transaction" (string) hex string of the transaction
	 * 
	 * Adds a change output to the raw transaction in hexstring given by a previous
	 * call to createrawtransaction
	 * 
	 * @param hexString
	 * @param address
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Parameter added for ver 0.92.
	 */
	public String appendRawChange(String hexString, String address,int nativefee) throws HdacException {
		String stringAppendRawChange = "";

		Object objectAppendRawChange = executeAppendRawChange(hexString, address, nativefee);
		if (verifyInstance(objectAppendRawChange, String.class)) {
			stringAppendRawChange = (String) objectAppendRawChange;
		}

		return stringAppendRawChange;
	}

	public String appendRawChange(String hexString, Address address,int nativefee) throws HdacException {
		if (address == null) {
			throw new HdacException("address", "is null");
		}

		return appendRawChange(hexString, address.getAddress(),nativefee);
	}
	
	/**
	 * appendrawexchange "hex" "txid" vout ask-assets
	 * 
	 * Adds to the raw atomic exchange transaction in tx-hex given by a previous call 
	 * to createrawexchange or appendrawexchange.
	 * 
	 * Arguments: 1. "hex" (string, required) The transaction hex string
	 * 2. "txid" (string, required) Transaction ID of the output prepared by preparelockunspent.
	 * 3. vout (numeric, required) Output index 4. ask-assets (object, required) A json 
	 * object of assets to ask
	 * {"asset-identifier" : asset-quantity ,... }
	 * 
	 * Result: 
	 * {"hex": "value", (string) The raw transaction with signature(s) (hex-encoded string)
	 *  "complete": true|false (boolean) if exchange is completed and can be sent}
	 *  
	 * @param hex
	 * @param txid
	 * @param vout
	 * @param ask-assets
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public SignedTransactionRAW appendRawExchange(String hexString, String txid, int vout, JsonObject assets) throws HdacException {
		SignedTransactionRAW appendRawExchange = new SignedTransactionRAW();
		Object objectAppendRawExchange = executeAppendRawExchange(hexString, txid, vout, assets);
		appendRawExchange = RawTransactionFormatter.formatSignedTransactionRAW(objectAppendRawExchange);
		return appendRawExchange;
	}
	

	/**
	 * 
	 * appendrawmetadata tx-hex data-hex
	 * 
	 * Appends new OP_RETURN output to existing raw transaction Returns hex-encoded
	 * raw transaction.
	 * 
	 * Arguments: 1. "tx-hex" (string, required) The transaction hex string 2.
	 * "data-hex" (string, required) Data hex string or 2. "issue-details" (object,
	 * required) A json object with issue metadata { "name" : asset-name
	 * (string,optional) Asset name "multiple" : n (numeric,optional, default 1)
	 * Number of raw units in one displayed unit "open" : true|false (boolean,
	 * optional, default false) True if follow-on issues are allowed "details" :
	 * (object, optional) a json object with custom fields { "param-name":
	 * "param-value" (strings, required) The key is the parameter name, the value is
	 * parameter value ,... } or 2. "issuemore-details" (object, required) A json
	 * object with issuemore metadata { "details" : (object, optional) a json object
	 * with custom fields { "param-name": "param-value" (strings, required) The key
	 * is the parameter name, the value is parameter value ,... } }
	 * 
	 * Result: { "hex": "value", (string) The raw transaction with appended data
	 * output (hex-encoded string) }
	 * 
	 * Adds a metadata output (using an OP_RETURN) to the raw transaction in tx-hex
	 * given by a previous call to createrawtransaction
	 * 
	 * @param txHex
	 * @param dataHex
	 * @return
	 * @throws HdacException
	 */
	public String appendRawMetaData(String txHex, String dataHex) throws HdacException {
		String stringAppendRawMetaData = "";

		Object objectAppendRawMetaData = executeAppendRawMetaData(txHex, dataHex);
		if (verifyInstance(objectAppendRawMetaData, String.class)) {
			stringAppendRawMetaData = (String) objectAppendRawMetaData;
		}

		return stringAppendRawMetaData;
	}
	
	/**
	 * 
	 * appendrawdata tx-hex data-hex|object 
	 * 
	 * Appends new OP_RETURN output to existing raw transaction Returns hex-encoded raw transaction.
	 * 
	 * Arguments: 1. "tx-hex" (string, required) The transaction hex string
	 * 2. "data-hex" (string, required) Data hex string or 2. issue-details 
	 * (object, required) A json object with issue metadata 
	 * {"create" : asset (string,required) asset 
	 * "name" : asset-name (string,optional) Asset name
	 * "multiple" : n  (numeric,optional, default 1) Number of raw units in one displayed unit
	 * "open" : true|false (boolean, optional, default false) True if follow-on issues are allowed
	 * "details" : (object, optional)  a json object with custom fields 
	 * {"param-name": "param-value" (strings, required) The key is the parameter name, the value 
	 * is parameter value ,... }  or 2. issuemore-details (object, required) A json object with issuemore metadata 
	 * {"update" : asset-identifier (string,required) Asset identifier - * one of the following: 
	 * asset txid, asset reference, asset name. "details" : (object, optional)  a json object with custom fields
	 * {"param-name": "param-value" (strings, required) The key is the parameter name, the value is 
	 * parameter value ,... }} or 2. create-new-stream (object, required) A json object with new stream details
	 *{"create" : stream (string,required) stream 
	 *"name" : stream-name (string,optional) Stream name
	 *"open" : true|false (string,optional, default: false) If true, anyone can publish
	 *"details" : (object,optional) a json object with custom fields
	 *{"param-name": "param-value" (strings, required) The key is the parameter name, the value is parameter value
	 *,...}} or 2. publish-new-stream-item (object, required) A json object with stream item
	 *{"for" : stream-identifier (string,required) Stream identifier - one of the following: stream txid, stream reference, stream name.
	 *"key" : key (string,optional, default: "") Item key 
	 *"data" : data-hex (string,optional, default: "") Data hex string} or
	 *2. create-new-upgrade (object, required) A json object with new upgrade details
	 *{"create" : upgrade (string,required) upgrade
	 *"name" : upgrade-name (string,optional) Upgrade name
	 *"startblock" : n (numeric,optional, default: 0) Block to apply upgrade from (inclusive).
	 *"details" : (object,optional) a json object with custom fields
	 *{"protocol-version": version (numeric, required) Protocol version to upgrade to}} or
	 *2. approve-upgrade (object, required) A json object with approval details 
	 *{"approve" : approve (boolean,required) Approve or disapprove
	 *"for" : upgrade-identifier (string,required)  Upgrade identifier - one of the following: upgrade txid, upgrade name.}
	 * 
	 * Result:
	 * {"hex": "value", (string) The raw transaction with appended data output (hex-encoded string)}
	 * 
	 * @param txHex
	 * @param dataHex
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public String appendRawData(String txHex, String dataHex) throws HdacException {
		String stringAppendRawData = "";

		Object objectAppendRawData = executeAppendRawData(txHex, dataHex);
		if (verifyInstance(objectAppendRawData, String.class)) {
			stringAppendRawData = (String) objectAppendRawData;
		}

		return stringAppendRawData;
	}
	
	/**
	 * @param txHex
	 * @param issueDetails
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Parameter added for ver 0.92.
	 */
	public String appendRawData(String txHex, JsonObject issueDetails) throws HdacException {
		String stringAppendRawData = "";
		
		Object objectAppendRawData = executeAppendRawData(txHex, issueDetails);
		if(verifyInstance(objectAppendRawData, String.class)) {
			stringAppendRawData = (String) objectAppendRawData;
		}
		
		return stringAppendRawData;
	}
	
	/**
	 * 
	 * appendrawtx "tx-hex" [{"txid":"id","vout":n},...] ( {"address":amount,...} [data] "action" )
	 * 
	 * Append inputs and outputs to raw transaction
	 *
	 * Arguments: 1. "tx-hex" (string, required) Source transaction hex string
	 * 2. transactions (array, required) A json array of json objects [ {
	 * "txid":"id", (string, required) The transaction id "vout":n (numeric,
	 * required) The output number "scriptPubKey": "hex", (string, optional) script
	 * key, used if cache=true or action=sign "redeemScript": "hex" (string,
	 * optional) redeem script, used if action=sign "cache":true|false (boolean,
	 * optional) If true - add cached script to tx, if omitted - add automatically
	 * if needed } ,... ] 3. addresses (object, required) a json object with
	 * addresses as keys and amounts as values { "address": x.xxx (numeric,
	 * required) The key is the address, the value is the native currency amount or
	 * { (object) A json object of assets to send "asset-identifier" :
	 * asset-quantity ,... } or { (object) A json object describing new asset issue
	 * "issue" : { "raw" : n (numeric, required) The asset total amount in raw units
	 * ,... } ,... } or { (object) A json object describing follow-on asset issue
	 * "issuemore" : { "asset" : "asset-identifier"(string, required) Asset
	 * identifier - one of the following: issue txid. asset reference, asset name.
	 * "raw" : n (numeric, required) The asset total amount in raw units ,... } ,...
	 * } or { (object) A json object describing permission change "permissions" : {
	 * "type" : "permission(s)" (string,required) Permission strings, comma
	 * delimited. Possible values:
	 * connect,send,receive,issue,mine,admin,activate,create "startblock" : n
	 * (numeric, optional) Block to apply permissions from (inclusive). Default - 0
	 * "endblock" : n (numeric, optional) Block to apply permissions to (exclusive).
	 * Default - 4294967295 "timestamp" : n (numeric, optional) This helps resolve
	 * conflicts between permissions assigned by the same administrator. Default -
	 * current time ,... } ,... } ,... } 4. data (array, optional) Array of
	 * hexadecimal strings or data objects, see help appendrawdata for details.
	 * 5."action" (string, optional, default "") Additional actions: "lock", "sign",
	 * "lock,sign", "sign,lock", "send".
	 * 
	 * Result: "transaction" (string) hex string of the transaction (if action= ""
	 * or "lock") or { (object) A json object (if action= "sign" or "lock,sign" or
	 * "sign,lock") "hex": "value", (string) The raw transaction with signature(s)
	 * (hex-encoded string) "complete": true|false (boolean) if transaction has a
	 * complete set of signature (0 if not) } or "hex" (string) The transaction hash
	 * in hex (if action= "send")
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
	public SignedTransactionRAW appendRawTransaction(String txHex, List<PreviousTx> previousTx, List<RawParam> rawParams, String[] data,
			String action) throws HdacException {
		
		SignedTransactionRAW signedTransactionRAW = new SignedTransactionRAW();
		
		Object objectTransactionRAW = executeAppendRawTransaction(txHex, previousTx, rawParams, data, action);
		signedTransactionRAW = RawTransactionFormatter.formatSignedTransactionRAW(objectTransactionRAW);
		
		return signedTransactionRAW;
	}
	
	/*
	 * Method created for ver 0.92.
	 */
	public String appendRawTransactionWithSend(String txHex, List<PreviousTx> previousTx, List<RawParam> rawParams, String[] data,
			String action) throws HdacException {

		String signedTransactionRAW = "";
		Object objectTransactionRAW = executeAppendRawTransaction(txHex, previousTx, rawParams, data, action);
		if (verifyInstance(objectTransactionRAW, String.class)) {
			signedTransactionRAW = (String) objectTransactionRAW;
		}
		return signedTransactionRAW;
		
	}

	/**
	 * createrawsendfrom from-address {"address":amount,...} ( [data] action )
	 * 
	 * Create a transaction using the given sending address except action "send".
	 * 
	 * Arguments: 1. from-address (string, required) Address to send from. 2.
	 * "addresses" (string, required) a json object with addresses as keys and
	 * amounts as values { "address": x.xxx (numeric, required) The key is the
	 * address, the value is the native currency amount or { (object) A json object
	 * of assets to send "asset-identifier" : asset-quantity ,... } or { (object) A
	 * json object describing new asset issue "issue" : { "raw" : n (numeric,
	 * required) The asset total amount in raw units ,... } ,... } or { (object) A
	 * json object describing follow-on asset issue "issuemore" : { "asset" :
	 * "asset-identifier" (string, required) Asset identifier - one of the
	 * following: issue txid. asset reference, asset name. "raw" : n (numeric,
	 * required) The asset total amount in raw units ,... } ,... } or { (object) A
	 * json object describing permission change "permissions" : { "type" :
	 * "permission(s)" (string,required) Permission strings, comma delimited.
	 * Possible values: connect,send,receive,issue,mine,admin,activate,create
	 * "startblock" (numeric, optional) Block to apply permissions from (inclusive).
	 * Default - 0 "endblock" (numeric, optional) Block to apply permissions to
	 * (exclusive). Default - 4294967295 "timestamp" (numeric, optional) This helps
	 * resolve conflicts between permissions assigned by the same administrator.
	 * Default - current time ,... } ,... } ,... } 3. data (array, optional) Array
	 * of hexadecimal strings or data objects, see help appendrawdata for details.
	 * 4. action (string, optional, default "") Additional actions: "lock", "sign",
	 * "lock,sign", "sign,lock", "send".
	 * 
	 * Result: "transaction" (string) hex string of the transaction (if action= ""
	 * or "lock") or { (object) A json object (if action= "sign" or "lock,sign" or
	 * "sign,lock") "hex": "value", (string) The raw transaction with signature(s)
	 * (hex-encoded string) "complete": n (numeric) if transaction has a complete
	 * set of signature (0 if not) }
	 */
	public SignedTransactionRAW createRawSendFrom(String address, List<RawParam> rawParams, String[] data,
			String action) throws HdacException {
		SignedTransactionRAW signedTransactionRAW = new SignedTransactionRAW();

		Object objectTransactionRAW = executeCreateRawSendFrom(address, rawParams, data, action);
		signedTransactionRAW = RawTransactionFormatter.formatSignedTransactionRAW(objectTransactionRAW);

		return signedTransactionRAW;
	}

	
	/**
	 * createrawsendfrom from-address {"address":amount,...} ( [data] action )
	 * 
	 * Create a transaction using the given sending address with action "send".
	 * 
	 * Arguments: 1. from-address (string, required) Address to send from. 2.
	 * "addresses" (string, required) a json object with addresses as keys and
	 * amounts as values { "address": x.xxx (numeric, required) The key is the
	 * address, the value is the native currency amount or { (object) A json object
	 * of assets to send "asset-identifier" : asset-quantity ,... } or { (object) A
	 * json object describing new asset issue "issue" : { "raw" : n (numeric,
	 * required) The asset total amount in raw units ,... } ,... } or { (object) A
	 * json object describing follow-on asset issue "issuemore" : { "asset" :
	 * "asset-identifier" (string, required) Asset identifier - one of the
	 * following: issue txid. asset reference, asset name. "raw" : n (numeric,
	 * required) The asset total amount in raw units ,... } ,... } or { (object) A
	 * json object describing permission change "permissions" : { "type" :
	 * "permission(s)" (string,required) Permission strings, comma delimited.
	 * Possible values: connect,send,receive,issue,mine,admin,activate,create
	 * "startblock" (numeric, optional) Block to apply permissions from (inclusive).
	 * Default - 0 "endblock" (numeric, optional) Block to apply permissions to
	 * (exclusive). Default - 4294967295 "timestamp" (numeric, optional) This helps
	 * resolve conflicts between permissions assigned by the same administrator.
	 * Default - current time ,... } ,... } ,... } 3. data (array, optional) Array
	 * of hexadecimal strings or data objects, see help appendrawdata for details.
	 * 4. action (string, optional, default "") Additional actions: "lock", "sign",
	 * "lock,sign", "sign,lock", "send".
	 * 
	 * Result: "hex" (string) The transaction hash in hex (only action= "send") 
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public String createRawSendFromWithSend(String address, List<RawParam> rawParams, String[] data, String action)
			throws HdacException {

		Object objectTransactionRAW = executeCreateRawSendFrom(address, rawParams, data, action);

		return objectTransactionRAW.toString();
	}

	/**
	 * for example have a look in test : RAWTransactionCommandTest
	 * 
	 * @param address   from-address
	 * @param rawParams list of parameters
	 * @return txId
	 * @throws HdacException
	 */
	public String createRawSendFromByMap(String address, List<Map<String, String>> rawParams) throws HdacException {
		Object objectTransactionRAW = executeCreateRawSendFrom(address, rawParams);

		return objectTransactionRAW.toString();
	}

	/**
	 * createrawsendfrom from-address {"address":amount,...} ( [data] action )
	 * 
	 * Create a transaction using the given sending address.
	 * 
	 * Arguments: 1. from-address (string, required) Address to send from. 2.
	 * "addresses" (string, required) a json object with addresses as keys and
	 * amounts as values { "address": x.xxx (numeric, required) The key is the
	 * address, the value is the native currency amount or { (object) A json object
	 * of assets to send "asset-identifier" : asset-quantity ,... } or { (object) A
	 * json object describing new asset issue "issue" : { "raw" : n (numeric,
	 * required) The asset total amount in raw units ,... } ,... } or { (object) A
	 * json object describing follow-on asset issue "issuemore" : { "asset" :
	 * "asset-identifier" (string, required) Asset identifier - one of the
	 * following: issue txid. asset reference, asset name. "raw" : n (numeric,
	 * required) The asset total amount in raw units ,... } ,... } or { (object) A
	 * json object describing permission change "permissions" : { "type" :
	 * "permission(s)" (string,required) Permission strings, comma delimited.
	 * Possible values: connect,send,receive,issue,mine,admin,activate,create
	 * "startblock" (numeric, optional) Block to apply permissions from (inclusive).
	 * Default - 0 "endblock" (numeric, optional) Block to apply permissions to
	 * (exclusive). Default - 4294967295 "timestamp" (numeric, optional) This helps
	 * resolve conflicts between permissions assigned by the same administrator.
	 * Default - current time ,... } ,... } ,... } 3. data (array, optional) Array
	 * of hexadecimal strings or data objects, see help appendrawdata for details.
	 * 4. action (string, optional, default "") Additional actions: "lock", "sign",
	 * "lock,sign", "sign,lock", "send".
	 * 
	 * Result: "transaction" (string) hex string of the transaction (if action= ""
	 * or "lock") or { (object) A json object (if action= "sign" or "lock,sign" or
	 * "sign,lock") "hex": "value", (string) The raw transaction with signature(s)
	 * (hex-encoded string) "complete": n (numeric) if transaction has a complete
	 * set of signature (0 if not) } or "hex" (string) The transaction hash in hex
	 * (if action= "send")
	 */
	public String createRawSendFrom(String address, List<RawParam> rawParams, String[] data) throws HdacException {
		String transactionRAW = new String();

		Object objectTransactionRAW = executeCreateRawSendFrom(address, rawParams, data, null);
		if (verifyInstance(objectTransactionRAW, String.class)) {
			transactionRAW = (String) objectTransactionRAW;
		}

		return transactionRAW;
	}

	/**
	 * createrawsendfrom from-address {"address":amount,...} ( [data] action )
	 * 
	 * Create a transaction using the given sending address.
	 * 
	 * Arguments: 1. from-address (string, required) Address to send from. 2.
	 * "addresses" (string, required) a json object with addresses as keys and
	 * amounts as values { "address": x.xxx (numeric, required) The key is the
	 * address, the value is the native currency amount or { (object) A json object
	 * of assets to send "asset-identifier" : asset-quantity ,... } or { (object) A
	 * json object describing new asset issue "issue" : { "raw" : n (numeric,
	 * required) The asset total amount in raw units ,... } ,... } or { (object) A
	 * json object describing follow-on asset issue "issuemore" : { "asset" :
	 * "asset-identifier" (string, required) Asset identifier - one of the
	 * following: issue txid. asset reference, asset name. "raw" : n (numeric,
	 * required) The asset total amount in raw units ,... } ,... } or { (object) A
	 * json object describing permission change "permissions" : { "type" :
	 * "permission(s)" (string,required) Permission strings, comma delimited.
	 * Possible values: connect,send,receive,issue,mine,admin,activate,create
	 * "startblock" (numeric, optional) Block to apply permissions from (inclusive).
	 * Default - 0 "endblock" (numeric, optional) Block to apply permissions to
	 * (exclusive). Default - 4294967295 "timestamp" (numeric, optional) This helps
	 * resolve conflicts between permissions assigned by the same administrator.
	 * Default - current time ,... } ,... } ,... } 3. data (array, optional) Array
	 * of hexadecimal strings or data objects, see help appendrawdata for details.
	 * 4. action (string, optional, default "") Additional actions: "lock", "sign",
	 * "lock,sign", "sign,lock", "send".
	 * 
	 * Result: "transaction" (string) hex string of the transaction (if action= ""
	 * or "lock") or { (object) A json object (if action= "sign" or "lock,sign" or
	 * "sign,lock") "hex": "value", (string) The raw transaction with signature(s)
	 * (hex-encoded string) "complete": n (numeric) if transaction has a complete
	 * set of signature (0 if not) } or "hex" (string) The transaction hash in hex
	 * (if action= "send")
	 */
	public String createRawSendFrom(String address, List<RawParam> rawParams) throws HdacException {
		return createRawSendFrom(address, rawParams, null);
	}

	/**
	 * Creates a transaction spending the specified inputs, sending to the given
	 * addresses
	 * 
	 * createrawtransaction [{"txid":"id","vout":n},...] {"address":amount,...}
	 * 
	 * Create a transaction spending the given inputs and sending to the given
	 * addresses. Returns hex-encoded raw transaction. Note that the transaction's
	 * inputs are not signed, and it is not stored in the wallet or transmitted to
	 * the network.
	 * 
	 * Create a transaction spending the given inputs.
	 *
	 * Arguments: 1. transactions (array, required) A json array of json objects [ {
	 * "txid":"id", (string, required) The transaction id "vout":n (numeric,
	 * required) The output number "scriptPubKey": "hex", (string, optional) script
	 * key, used if cache=true or action=sign "redeemScript": "hex" (string,
	 * optional) redeem script, used if action=sign "cache":true|false (boolean,
	 * optional) If true - add cached script to tx, if omitted - add automatically
	 * if needed } ,... ] 2. addresses (object, required) a json object with
	 * addresses as keys and amounts as values { "address": x.xxx (numeric,
	 * required) The key is the address, the value is the native currency amount or
	 * { (object) A json object of assets to send "asset-identifier" :
	 * asset-quantity ,... } or { (object) A json object describing new asset issue
	 * "issue" : { "raw" : n (numeric, required) The asset total amount in raw units
	 * ,... } ,... } or { (object) A json object describing follow-on asset issue
	 * "issuemore" : { "asset" : "asset-identifier"(string, required) Asset
	 * identifier - one of the following: issue txid. asset reference, asset name.
	 * "raw" : n (numeric, required) The asset total amount in raw units ,... } ,...
	 * } or { (object) A json object describing permission change "permissions" : {
	 * "type" : "permission(s)" (string,required) Permission strings, comma
	 * delimited. Possible values:
	 * connect,send,receive,issue,mine,admin,activate,create "startblock" : n
	 * (numeric, optional) Block to apply permissions from (inclusive). Default - 0
	 * "endblock" : n (numeric, optional) Block to apply permissions to (exclusive).
	 * Default - 4294967295 "timestamp" : n (numeric, optional) This helps resolve
	 * conflicts between permissions assigned by the same administrator. Default -
	 * current time ,... } ,... } ,... } 3. data (array, optional) Array of
	 * hexadecimal strings or data objects, see help appendrawdata for details.
	 * 4."action" (string, optional, default "") Additional actions: "lock", "sign",
	 * "lock,sign", "sign,lock", "send".
	 * 
	 * Result: "transaction" (string) hex string of the transaction (if action= ""
	 * or "lock") or { (object) A json object (if action= "sign" or "lock,sign" or
	 * "sign,lock") "hex": "value", (string) The raw transaction with signature(s)
	 * (hex-encoded string) "complete": true|false (boolean) if transaction has a
	 * complete set of signature (0 if not) } or "hex" (string) The transaction hash
	 * in hex (if action= "send")
	 * 
	 * @param txids
	 * @param vouts
	 * @param addresses
	 * @param amounts
	 * @return
	 * @throws HdacException
	 */
	public String createRawTransaction(List<TxIdVout> inputs, List<AddressBalance> addessBalances, List<DataParam> data)
			throws HdacException {
		String createTransactionRAW = new String();

		Object objectTransactionRAW = executeCreateRawTransaction(inputs, addessBalances, data);
		if (verifyInstance(objectTransactionRAW, String.class)) {
			createTransactionRAW = (String) objectTransactionRAW;
		}

		return createTransactionRAW;
	}

	/**
	 * @param inputs
	 * @param addessBalances
	 * @return
	 * @throws HdacException
	 */
	public String createRawTransaction(List<TxIdVout> inputs, List<AddressBalance> addessBalances)
			throws HdacException {
		return createRawTransaction(inputs, addessBalances, null);
	}
	
	/**
	 * @param inputs
	 * @param addresses
	 * @param data
	 * @param action
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Parameter changed for ver 0.92.
	 */
	public String createRawTransaction(List<TxIdVout> inputs, JsonObject addresses, JsonArray data, String action) throws HdacException{
		String createTransactionRAW = "";
		Object objectTransactionRAW = executeCreateRawTransaction(inputs, addresses, data, action);
		if (verifyInstance(objectTransactionRAW, String.class)) {
			createTransactionRAW = (String) objectTransactionRAW;
		}
		return createTransactionRAW;
	}
	
	/**
	 * 
	 * decoderawtransaction "hexstring"
	 * 
	 * Return a JSON object representing the serialized, hex-encoded transaction.
	 * 
	 * Arguments: 1. "hex" (string, required) The transaction hex string
	 * 
	 * Result: { "txid" : "id", (string) The transaction id "version" : n, (numeric)
	 * The version "locktime" : ttt, (numeric) The lock time "vin" : [ (array of
	 * json objects) { "txid": "id", (string) The transaction id "vout": n,
	 * (numeric) The output number "scriptSig": { (json object) The script "asm":
	 * "asm", (string) asm "hex": "hex" (string) hex }, "sequence": n (numeric) The
	 * script sequence number } ,... ], "vout" : [ (array of json objects) { "value"
	 * : x.xxx, (numeric) The value in btc "n" : n, (numeric) index "scriptPubKey" :
	 * { (json object) "asm" : "asm", (string) the asm "hex" : "hex", (string) the
	 * hex "reqSigs" : n, (numeric) The required sigs "type" : "pubkeyhash",
	 * (string) The type, eg 'pubkeyhash' "addresses" : [ (json array of string)
	 * "12tvKAXCxZjSmdNbao16dKXC8tRWfcF5oc" (string) address ,... ] } } ,... ], }
	 * 
	 * @param hex
	 * @return
	 * @throws HdacException
	 */
	public TransactionRAW decodeRawTransaction(String hex) throws HdacException {
		TransactionRAW transactionRAW = new TransactionRAW();

		Object objectTransactionRAW = executeDecodeRawTransaction(hex);
		transactionRAW = RawTransactionFormatter.formatTransactionRAW(objectTransactionRAW);

		return transactionRAW;
	}
	
	/**
	 * decoderawexchange "tx-hex" ( verbose )
	 * 
	 * Return a JSON object representing the serialized, hex-encoded exchange transaction.
	 * 
	 * Result: 1. "tx-hex" (string, required) The exchange transaction hex string
	 * 2. verbose (boolean, optional, default=false) If true, returns array of all exchanges
	 * created by createrawexchange or appendrawexchange
	 * 
	 * @param tx-hex
	 * @param verbose
	 * @return String address
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public Object decodeRawExchange(String txHex, boolean verbose) throws HdacException {
		Object decodeRawExchange = executeDecodeRawExchange(txHex, verbose);
		if(verbose == false) {
			decodeRawExchange = RawTransactionFormatter.formatDecodeExchageRAW(decodeRawExchange);
			return decodeRawExchange;
		}
		decodeRawExchange = RawTransactionFormatter.formatDecodeExchageRAWDetailed(decodeRawExchange);
		return decodeRawExchange;
	}
	
	/**
	 * getrawchangeaddress
	 * 
	 * Returns a new address, for receiving change. This is for use with raw
	 * transactions, NOT normal use.
	 * 
	 * Result: "address" (string) The address
	 * 
	 * @return String address
	 * @throws HdacException
	 */
	public String getRawChangeAddress() throws HdacException {
		String rawChangeAddress = new String();

		Object objectRawChangeAddress = executeGetRawChangeAddress();
		if (verifyInstance(objectRawChangeAddress, String.class)) {
			rawChangeAddress = (String) objectRawChangeAddress;
		}

		return rawChangeAddress;
	}

	/**
	 * getrawtransaction "txid" ( verbose )
	 * 
	 * NOTE: By default this function only works sometimes. This is when the tx is
	 * in the mempool or there is an unspent output in the utxo for this
	 * transaction. To make it always work, you need to maintain a transaction
	 * index, using the -txindex command line option.
	 * 
	 * Return the raw transaction data.
	 * 
	 * If verbose=0, returns a string that is serialized, hex-encoded data for
	 * 'txid'. If verbose is non-zero, returns an Object with information about
	 * 'txid'.
	 * 
	 * Arguments: 1. "txid" (string, required) The transaction id 2. verbose
	 * (numeric, optional, default=0) If 0, return a string, other return a json
	 * object
	 * 
	 * Result (if verbose is not set or set to 0): "data" (string) The serialized,
	 * hex-encoded data for 'txid'
	 * 
	 * Result (if verbose > 0): { "hex" : "data", (string) The serialized,
	 * hex-encoded data for 'txid' "txid" : "id", (string) The transaction id (same
	 * as provided) "version" : n, (numeric) The version "locktime" : ttt, (numeric)
	 * The lock time "vin" : [ (array of json objects) { "txid": "id", (string) The
	 * transaction id "vout": n, (numeric) "scriptSig": { (json object) The script
	 * "asm": "asm", (string) asm "hex": "hex" (string) hex }, "sequence": n
	 * (numeric) The script sequence number } ,... ], "vout" : [ (array of json
	 * objects) { "value" : x.xxx, (numeric) The value in btc "n" : n, (numeric)
	 * index "scriptPubKey" : { (json object) "asm" : "asm", (string) the asm "hex"
	 * : "hex", (string) the hex "reqSigs" : n, (numeric) The required sigs "type" :
	 * "pubkeyhash", (string) The type, eg 'pubkeyhash' "addresses" : [ (json array
	 * of string) "address" (string) address ,... ] } } ,... ], "blockhash" :
	 * "hash", (string) the block hash "confirmations" : n, (numeric) The
	 * confirmations "time" : ttt, (numeric) The transaction time in seconds since
	 * epoch (Jan 1 1970 GMT) "blocktime" : ttt (numeric) The block time in seconds
	 * since epoch (Jan 1 1970 GMT) }
	 * 
	 * @param txid
	 * @param verbose (0 : false / 1 : true)
	 * @return
	 * @throws HdacException
	 */
	public Object getRawTransaction(String txid, int verbose) throws HdacException {
		Object objectTransactionRAW = executeGetRawTransaction(txid, verbose);
		if (verbose == 0) {
			return objectTransactionRAW;
		}
		GetTransactionRAW transactionRAW = RawTransactionFormatter.formatGetTransactionRAW(objectTransactionRAW);
		return transactionRAW;
	}

	/**
	 * {@link #getRawTransaction(String, int) in verbose mode}
	 * 
	 * @param txid
	 * @return
	 * @throws HdacException
	 */
	public TransactionRAW getRAWTransactionWithDetail(String txid) throws HdacException {
		return (TransactionRAW) getRawTransaction(txid, 1);
	}

	/**
	 * {@link #getRawTransaction(String, int) in non-verbose mode}
	 * 
	 * @param txid
	 * @return
	 * @throws HdacException
	 */
	public String getRAWTransactionWithoutDetail(String txid) throws HdacException {
		return (String) getRawTransaction(txid, 0);
	}

	/**
	 * 
	 * sendrawtransaction "hexstring" ( allowhighfees )
	 * 
	 * Submits raw transaction (serialized, hex-encoded) to local node and network.
	 * 
	 * Also see createrawtransaction and signrawtransaction calls.
	 * 
	 * Arguments: 1. "hexstring" (string, required) The hex string of the raw
	 * transaction) 2. allowhighfees (boolean, optional, default=false) Allow high
	 * fees
	 * 
	 * Result: "hex" (string) The transaction hash in hex
	 * 
	 * Validates the raw transaction in hexstring and transmits it to the network,
	 * returning the txid.
	 * 
	 * @param hexString
	 * @return
	 * @throws HdacException
	 */
	public Object sendRawTransaction(String hexString) throws HdacException {
		return executeSendRawTransaction(hexString);
	}

	/**
	 * 
	 * signrawtransaction "hexstring" (
	 * [{"txid":"id","vout":n,"scriptPubKey":"hex","redeemScript":"hex"},...]
	 * ["privatekey1",...] sighashtype )
	 * 
	 * Sign inputs for raw transaction (serialized, hex-encoded). The second
	 * optional argument (may be null) is an array of previous transaction outputs
	 * that this transaction depends on but may not yet be in the block chain. The
	 * third optional argument (may be null) is an array of base58-encoded private
	 * keys that, if given, will be the only keys used to sign the transaction.
	 * 
	 * 
	 * Arguments: 1. "hexstring" (string, required) The transaction hex string 2.
	 * "prevtxs" (string, optional) An json array of previous dependent transaction
	 * outputs [ (json array of json objects, or 'null' if none provided) {
	 * "txid":"id", (string, required) The transaction id "vout":n, (numeric,
	 * required) The output number "scriptPubKey": "hex", (string, required) script
	 * key "redeemScript": "hex" (string, required for P2SH) redeem script } ,... ]
	 * 3. "privatekeys" (string, optional) A json array of base58-encoded private
	 * keys for signing [ (json array of strings, or 'null' if none provided)
	 * "privatekey" (string) private key in base58-encoding ,... ] 4. "sighashtype"
	 * (string, optional, default=ALL) The signature hash type. Must be one of "ALL"
	 * "NONE" "SINGLE" "ALL|ANYONECANPAY" "NONE|ANYONECANPAY" "SINGLE|ANYONECANPAY"
	 * 
	 * Result: { "hex": "value", (string) The raw transaction with signature(s)
	 * (hex-encoded string) "complete": n (numeric) if transaction has a complete
	 * set of signature (0 if not) }
	 * 
	 * Signs the raw transaction in hexstring, often provided by a previous call to
	 * createrawtransaction and (optionally) appendrawmetadata. * @param hexString
	 * 
	 * @return
	 * @throws HdacException
	 */
	public SignedTransactionRAW signRawTransaction(String hexString) throws HdacException {
		SignedTransactionRAW signedTransactionRAW = new SignedTransactionRAW();

		Object objectTransactionRAW = executeSignRawTransaction(hexString);
		signedTransactionRAW = RawTransactionFormatter.formatSignedTransactionRAW(objectTransactionRAW);

		return signedTransactionRAW;
	}
	
	/**
	 * @param hexString
	 * @param prevtxs
	 * @param privatekeys
	 * @param sighashtype
	 * @return
	 * @throws HdacException
	 */
	public SignedTransactionRAW signRawTransaction(String hexString, List<PrevTx> prevtxs, List<String> privatekeys, String sighashtype) throws HdacException {
		SignedTransactionRAW signedTransactionRAW = new SignedTransactionRAW();
		Object objectTransactionRAW = executeSignRawTransaction(hexString, prevtxs, privatekeys, sighashtype);
		signedTransactionRAW = RawTransactionFormatter.formatSignedTransactionRAW(objectTransactionRAW);
		return signedTransactionRAW;
	}
	
	
	/**
	 * disablerawtx "tx-hex"
	 * 
	 * Disable raw transaction by spending one of its inputs and sending it back to the wallet. 
	 *  
	 * Arguments: 1. "tx-hex" (string, required) The transaction hex string
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 *  
	 * @param tx-hex
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public Object disableRawTransaction(String txhex) throws HdacException {
		return executeDisableRawTransaction(txhex);
	}
	
	/**
	 * createrawexchange "txid" vout ask-assets
	 * 
	 * Creates new exchange transaction Note that the transaction should be completed by appendrawexchange 
	 *  
	 * Arguments: 1. "txid" (string, required) Transaction ID of the output prepared by preparelockunspent.
	 * 2. vout (numeric, required) Output index 
	 * 3. ask-assets (object, required) A json object of assets to ask
	 *{"asset-identifier" : asset-quantity ,... }
	 * 
	 * Result: "transaction" (string) hex string of the transaction
	 *  
	 * @param txid
	 * @param vout
	 * @param ask-assets
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public String createRawExchange(String txid, int vout, JsonObject jsonAssets) throws HdacException {
		String createRawExchange = "";
		Object objectCreateRawExchange = executeCreateRawExchange(txid, vout, jsonAssets);
		if (verifyInstance(objectCreateRawExchange, String.class)) {
			createRawExchange = (String) objectCreateRawExchange;
		}
		return createRawExchange;
	}
	
	/**
	 * completerawexchange hex txid vout ask-assets ( "data-hex"|object )
	 * 
	 * Completes existing exchange transaction, adds fee if needed Returns hex-encoded raw transaction. 
	 *  
	 * Arguments: 1. "hex" (string, required) The transaction hex string 
	 * 2. "txid" (string, required) Transaction ID of the output prepared by preparelockunspent.
	 * 3. "vout" (numeric, required) Output index
	 * 4. "ask-assets" (object, required) A json object of assets to ask
	 *{"asset-identifier" : asset-quantity ,... }
	 *5. "data-hex"  (string, optional) Data hex string  or
	 *5. publish-new-stream-item  (object, optional) A json object with stream item
	 *{"for" : stream-identifier (string,required) Stream identifier - one of the following: 
	 *stream txid, stream reference, stream name. "key" : key (string,optional, default: "") Item key
	 *"data" : data-hex (string,optional, default: "") Data hex string}
	 * 
	 * Result: "transaction" (string) hex string of the transaction
	 *  
	 * @param hex
	 * @param txid
	 * @param vout
	 * @param ask-assets
	 * @param "data-hex"|object
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public String completeRawExchange(String hexString, String txid, int vout, JsonObject assets) throws HdacException {
		String completeRawExchange = "";

		Object objectCompleteRawExchange = executeCompleteRawRxchange(hexString, txid, vout, assets);
		if (verifyInstance(objectCompleteRawExchange, String.class)) {
			completeRawExchange = (String) objectCompleteRawExchange;
		}
		
		return completeRawExchange;
	}
	
	
	/**
	 * preparelockunspent asset-quantities ( lock )
	 * 
	 * Prepares exchange transaction output for createrawexchange, appendrawexchange 
	 *  
	 * Arguments: 1. asset-quantities (object, required) A json object of assets to send
	 *{"asset-identifier" : asset-quantity ,... } 
	 *2. lock (boolean, optiona, default=true) Lock prepared unspent output
	 * 
	 * Result: 
	 *{"txid": "transactionid", (string) Transaction ID of the output which can be spent 
	 *in createrawexchange or createrawexchange "vout": n (numeric) Output index}
	 *  
	 * @param asset-quantities
	 * @param lock
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public TxIdVout prepareLockUnspent(JsonObject assets, boolean lock) throws HdacException {
		TxIdVout prepareLockUnspent = new TxIdVout();
		Object objectPrepareLockUnpent = executePrepareLockUnspent(assets, lock);
		prepareLockUnspent = RawTransactionFormatter.formatPrepareLockUnspent(objectPrepareLockUnpent);		
		return prepareLockUnspent;
	}
	
	/**
	 * preparelockunspentfrom "from-address" asset-quantities ( lock )
	 * 
	 * Prepares exchange transaction output for createrawexchange, appendrawexchange 
	 * using specific address 
	 *  
	 * Arguments: 1. "from-address" (string, required) Address to send from .
	 * 2. asset-quantities (object, required) A json object of assets to send 
	 * {"asset-identifier" : asset-quantity ,... }
	 * 3. lock (boolean, optiona, default=true) Lock prepared unspent output
	 * 
	 * Result: 
	 *{"txid": "transactionid", (string) Transaction ID of the output which can be spent 
	 *in createrawexchange or createrawexchange "vout": n (numeric) Output index}
	 *  
	 * @param from-address
	 * @param asset-quantities
	 * @param lock
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public TxIdVout prepareLockUnspentFrom(String fromAddress, JsonObject assets, boolean lock) throws HdacException {
		TxIdVout prepareLockUnspentFrom = new TxIdVout();
		Object objectPrepareLockUnpentFrom = executePrepareLockUnspentFrom(fromAddress, assets, lock);
		prepareLockUnspentFrom = RawTransactionFormatter.formatPrepareLockUnspentFrom(objectPrepareLockUnpentFrom);		
		return prepareLockUnspentFrom;
	}
	
}
