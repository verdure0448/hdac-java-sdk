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

import com.hdactech.command.builders.QueryBuilderWalletTransaction;
import com.hdactech.object.BalanceAssetGeneral;
import com.hdactech.object.ListAddressTxsDetailed;
import com.hdactech.object.ListWalletTxs;
import com.hdactech.object.TransactionWallet;
import com.hdactech.object.TransactionWalletDetailed;
import com.hdactech.object.TxOut;
import com.hdactech.object.formatters.TxOutFormatter;
import com.hdactech.object.formatters.WalletTransactionFormatter;
import com.hdactech.object.queryobjects.AssetQuantity;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class WalletTransactionCommand extends QueryBuilderWalletTransaction {

	public WalletTransactionCommand(String ip, String port, String login, String password, RuntimeParameters runtimeparameters) {
		initialize(ip, port, login, password, runtimeparameters);
	}

	/**
	 * 
	 * getaddresstransaction "address" "txid" ( verbose)
	 * 
	 * Get detailed information about in-wallet transaction <txid>
	 * 
	 * Arguments: 1. "address" (string, required) Address used for balance
	 * calculation. 2. "txid" (string, required) The transaction id 3. verbose
	 * (bool, optional, default=false) If true, returns detailed array of inputs
	 * and outputs and raw hex of transactions
	 * 
	 * Result: [ { "balance": {...}, (object) Changes in address balance. {
	 * "amount": x.xxx, (numeric) The amount in native currency. Negative value
	 * means amount was send by the wallet, positive - received "assets": {...},
	 * (object) Changes in asset amounts. } "myaddresses": [...], (array)
	 * Address passed as parameter "addresses": [...], (array) Array of
	 * counterparty addresses involved in transaction "permissions": [...],
	 * (array) Changes in permissions "issue": {...}, (object) Issue details
	 * "data" : "metadata", (array) Hexadecimal representation of metadata
	 * appended to the transaction "confirmations": n, (numeric) The number of
	 * confirmations for the transaction. Available for 'send' and 'receive'
	 * category of transactions. "blockhash": "hashvalue",(string) The block
	 * hash containing the transaction. Available for 'send' and 'receive'
	 * category of transactions. "blockindex": n, (numeric) The block index
	 * containing the transaction. Available for 'send' and 'receive' category
	 * of transactions. "txid": "transactionid", (string) The transaction id.
	 * Available for 'send' and 'receive' category of transactions. "time": xxx,
	 * (numeric) The transaction time in seconds since epoch (midnight Jan 1
	 * 1970 GMT). "timereceived": xxx, (numeric) The time received in seconds
	 * since epoch (midnight Jan 1 1970 GMT). Available for 'send' and 'receive'
	 * category of transactions. "comment": "...", (string) If a comment is
	 * associated with the transaction. "vin": [...], (array) If verbose=true.
	 * Array of input details "vout": [...], (array) If verbose=true. Array of
	 * output details "hex" : "data" (string) If verbose=true. Raw data for
	 * transaction } ]
	 * 
	 * Lists information about the 10 most recent transactions related to
	 * address in this node's wallet, including how they affected that address's
	 * balance.
	 * 
	 * @param address
	 * @return
	 * @throws HdacException
	 */
	public TransactionWallet getAddressTransaction(String address, String txid, boolean verbose) throws HdacException {
		TransactionWallet transactionWallet = new TransactionWallet();

		Object objectTransactionWallet = executeGetAddressTransaction(address, txid, verbose);
		transactionWallet = WalletTransactionFormatter.formatTransactionWallet(objectTransactionWallet);

		return transactionWallet;
	}

	/**
	 * {@link #getAddressTransaction(String, String, boolean) without verbose}
	 * 
	 * @param address
	 * @param txid
	 * @return
	 * @throws HdacException
	 */
	public TransactionWallet getAddressTransaction(String address, String txid) throws HdacException {
		return getAddressTransaction(address, txid, false);

	}
	
	
	/**
	 * getassettx "asset-identifier" "txid" ( verbose )
	 * 
	 * Retrieves a specific transaction txid involving asset.
	 * 
	 * Arguments: 1. "asset-identifier" (string, required) Asset identifier - one of 
	 * the following: asset txid, asset reference, asset name. 2. "txid" (string, 
	 * required) The transaction id 3. verbose (boolean, optional, default=false)
	 * If true, returns information about item transaction
	 * 
	 * Result: "transaction" (object) Information about an individual transaction from the perspective of a particular asset
	 * 
	 * @param asset-identifier
	 * @param txid
	 * @param verbose
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public TransactionWallet getAssetTransaction(String assetName, String txid, boolean verbose) throws HdacException {
		TransactionWallet transactionWallet = new TransactionWallet();
		
		Object objectTransactionWallet = excuteGetAssetTransaction(assetName, txid, verbose);
		transactionWallet = WalletTransactionFormatter.formatTransactionWallet(objectTransactionWallet);
		
		return transactionWallet;
	}

	/**
	 * gettransaction "txid" ( includeWatchonly )
	 * 
	 * Get detailed information about in-wallet transaction <txid>
	 * 
	 * Arguments: 1. "txid" (string, required) The transaction id 2.
	 * "includeWatchonly" (bool, optional, default=false) Whether to include
	 * watchonly addresses in balance calculation and details[]
	 * 
	 * Result: { "amount" : x.xxx, (numeric) The transaction amount in native
	 * currency "confirmations" : n, (numeric) The number of confirmations
	 * "blockhash" : "hash", (string) The block hash "blockindex" : xx,
	 * (numeric) The block index "blocktime" : ttt, (numeric) The time in
	 * seconds since epoch (1 Jan 1970 GMT) "txid" : "transactionid", (string)
	 * The transaction id. "time" : ttt, (numeric) The transaction time in
	 * seconds since epoch (1 Jan 1970 GMT) "timereceived" : ttt, (numeric) The
	 * time received in seconds since epoch (1 Jan 1970 GMT) "details" : [ {
	 * "account" : "accountname", (string) The account name involved in the
	 * transaction, can be "" for the default account. "address" : "address",
	 * (string) The address involved in the transaction "category" :
	 * "send|receive", (string) The category, either 'send' or 'receive'
	 * "amount" : x.xxx (numeric) The amount in native currency "vout" : n,
	 * (numeric) the vout value } ,... ], "hex" : "data" (string) Raw data for
	 * transaction }
	 * 
	 * @param txid
	 * @param includeWatchonly
	 * @return
	 * @throws HdacException
	 */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*public Transaction getTransaction(String txid, boolean includeWatchonly) throws HdacException {
		Transaction transaction = new Transaction();

		Object objectTransaction = executeGetTransaction(txid, includeWatchonly);
		transaction = TransactionFormatter.formatTransaction(objectTransaction);

		return transaction;
	}*/
	
	/*public Transaction getTransaction(String txid) throws HdacException {
	return getTransaction(txid, false);
}*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Object getTransaction(String txid, boolean includeWatchonly) throws HdacException {
		return executeGetTransaction(txid, includeWatchonly);
	}

	/**
	 * {@link #getTransaction(String, boolean) without includeWathconly}
	 * 
	 * @param txid
	 * @return
	 * @throws HdacException
	 */
	

	/**
	 * 
	 * gettxout "txid" n ( includemempool )
	 * 
	 * Returns details about an unspent transaction output.
	 * 
	 * Arguments: 1. "txid" (string, required) The transaction id 2. n (numeric,
	 * required) vout value 3. includemempool (boolean, optional) Whether to
	 * included the mem pool
	 * 
	 * Result: { "bestblock" : "hash", (string) the block hash "confirmations" :
	 * n, (numeric) The number of confirmations "value" : x.xxx, (numeric) The
	 * transaction value in btc "scriptPubKey" : { (json object) "asm" : "code",
	 * (string) "hex" : "hex", (string) "reqSigs" : n, (numeric) Number of
	 * required signatures "type" : "pubkeyhash", (string) The type, eg
	 * pubkeyhash "addresses" : [ (array of string) array of addresses "address"
	 * (string) address ,... ] }, "version" : n, (numeric) The version
	 * "coinbase" : true|false (boolean) Coinbase or not }
	 * 
	 * Returns details about an unspent transaction output vout of txid.
	 * 
	 * @param txid
	 * @param vout
	 * @return
	 * @throws HdacException
	 */
	public TxOut getTxOut(String txid, int vout, boolean includemempool) throws HdacException {
		TxOut txOut = new TxOut();

		Object objectTxOut = executeGetTxOut(txid, vout, includemempool);
		txOut = TxOutFormatter.formatTxOut(objectTxOut);

		return txOut;
	}
	
	/*
	 * Method changed for ver 0.92.
	 */
	public Object ObjectGetTxOut(String txid, int vout, boolean includemempool) throws HdacException {
		return executeGetTxOut(txid, vout, includemempool);
	}

	/**
	 * {@link #getTxOut(String, int, boolean) without includemempool}
	 * 
	 * @param txid
	 * @param vout
	 * @return
	 * @throws HdacException
	 */
	public TxOut getTxOut(String txid, int vout) throws HdacException {
		return getTxOut(txid, vout, false);
	}
	
	
	/* should be continue
	public String getTxOutData(String txid, int vout) throws HdacException {
		String stringAppendRawMetaData = "";

		Object objectAppendRawMetaData = executeAppendRawMetaData(txHex, dataHex);
		if (verifyInstance(objectAppendRawMetaData, String.class)) {
			stringAppendRawMetaData = (String) objectAppendRawMetaData;
		}

		return stringAppendRawMetaData;
	}*/

	/**
	 * 
	 * getwallettransaction "txid" ( includeWatchonly verbose)
	 * 
	 * Get detailed information about in-wallet transaction <txid>
	 * 
	 * Arguments: 1. "txid" (string, required) The transaction id 2.
	 * "includeWatchonly" (bool, optional, default=false) Whether to include
	 * watchonly addresses in balance calculation and details[] 3. verbose
	 * (bool, optional, default=false) If true, returns detailed array of inputs
	 * and outputs and raw hex of transactions
	 * 
	 * Result: [ { "balance": {...}, (object) Changes in wallet balance. {
	 * "amount": x.xxx, (numeric) The amount in native currency. Negative value
	 * means amount was send by the wallet, positive - received "assets": {...},
	 * (object) Changes in asset amounts. } "myaddresses": [...], (array) Array
	 * of wallet addresses involved in transaction "addresses": [...], (array)
	 * Array of counterparty addresses involved in transaction "permissions":
	 * [...], (array) Changes in permissions "issue": {...}, (object) Issue
	 * details "data" : "metadata", (array) Hexadecimal representation of
	 * metadata appended to the transaction "confirmations": n, (numeric) The
	 * number of confirmations for the transaction. Available for 'send' and
	 * 'receive' category of transactions. "blockhash": "hashvalue",(string) The
	 * block hash containing the transaction. Available for 'send' and 'receive'
	 * category of transactions. "blockindex": n, (numeric) The block index
	 * containing the transaction. Available for 'send' and 'receive' category
	 * of transactions. "txid": "transactionid", (string) The transaction id.
	 * Available for 'send' and 'receive' category of transactions. "time": xxx,
	 * (numeric) The transaction time in seconds since epoch (midnight Jan 1
	 * 1970 GMT). "timereceived": xxx, (numeric) The time received in seconds
	 * since epoch (midnight Jan 1 1970 GMT). Available for 'send' and 'receive'
	 * category of transactions. "comment": "...", (string) If a comment is
	 * associated with the transaction. "vin": [...], (array) If verbose=true.
	 * Array of input details "vout": [...], (array) If verbose=true. Array of
	 * output details "hex" : "data" (string) If verbose=true. Raw data for
	 * transaction } ]
	 * 
	 * Provides information about transaction txid in this node's wallet,
	 * including how it affected the node's total balance.
	 * 
	 * @param txid
	 * @param includeWatchOnly
	 * @param verbose
	 * @return
	 * @throws HdacException
	 */
	public TransactionWalletDetailed getWalletTransaction(String txid, boolean includeWatchOnly, boolean verbose) throws HdacException {
		TransactionWalletDetailed transactionWalletDetailed = new TransactionWalletDetailed();

		Object objectTransactionWalletDetailed = executeGetWalletTransaction(txid, includeWatchOnly, verbose);
		transactionWalletDetailed = WalletTransactionFormatter.formatTransactionWalletDetailed(objectTransactionWalletDetailed);

		return transactionWalletDetailed;
	}

	/**
	 * {@link #getWalletTransaction(String, boolean, boolean) with watchOnly
	 * false and verbose true}
	 * 
	 * @param txid
	 * @return
	 * @throws HdacException
	 */
	public TransactionWalletDetailed getWalletTransactionWithDetail(String txid) throws HdacException {
		return getWalletTransaction(txid, false, true);
	}

	/**
	 * {@link #getWalletTransaction(String, boolean, boolean) with watchOnly
	 * false and verbose false}
	 * 
	 * @param txid
	 * @return
	 * @throws HdacException
	 */
	public TransactionWallet getWalletTransactionWithoutDetail(String txid) throws HdacException {
		return getWalletTransaction(txid, false, false).getTransactionWallet();
	}

	/**
	 * 
	 * listaddresstransactions ( "address" count skip verbose)
	 * 
	 * Returns up to 'count' most recent transactions skipping the first 'from'
	 * transactions for account 'account'.
	 * 
	 * Arguments: 1. "address" (string, required) Address to list transactions
	 * for. 2. count (numeric, optional, default=10) The number of transactions
	 * to return 3. skip (numeric, optional, default=0) The number of
	 * transactions to skip 4. verbose (bool, optional, default=false) If true,
	 * returns detailed array of inputs and outputs and raw hex of transactions
	 * 
	 * Result: [ { "balance": {...}, (object) Changes in address balance. {
	 * "amount": x.xxx, (numeric) The amount in native currency. Negative value
	 * means amount was send by the wallet, positive - received "assets": {...},
	 * (object) Changes in asset amounts. } "myaddresses": [...], (array)
	 * Address passed as parameter. "addresses": [...], (array) Array of
	 * counterparty addresses involved in transaction "permissions": [...],
	 * (array) Changes in permissions "issue": {...}, (object) Issue details
	 * "data" : "metadata", (array) Hexadecimal representation of metadata
	 * appended to the transaction "confirmations": n, (numeric) The number of
	 * confirmations for the transaction. Available for 'send' and 'receive'
	 * category of transactions. "blockhash": "hashvalue",(string) The block
	 * hash containing the transaction. Available for 'send' and 'receive'
	 * category of transactions. "blockindex": n, (numeric) The block index
	 * containing the transaction. Available for 'send' and 'receive' category
	 * of transactions. "txid": "transactionid", (string) The transaction id.
	 * Available for 'send' and 'receive' category of transactions. "time": xxx,
	 * (numeric) The transaction time in seconds since epoch (midnight Jan 1
	 * 1970 GMT). "timereceived": xxx, (numeric) The time received in seconds
	 * since epoch (midnight Jan 1 1970 GMT). Available for 'send' and 'receive'
	 * category of transactions. "comment": "...", (string) If a comment is
	 * associated with the transaction. "vin": [...], (array) If verbose=true.
	 * Array of input details "vout": [...], (array) If verbose=true. Array of
	 * output details "hex" : "data" (string) If verbose=true. Raw data for
	 * transaction } ]
	 * 
	 * Lists information about the 10 most recent transactions related to
	 * address in this node's wallet, including how they affected that address's
	 * balance.
	 * 
	 * @param address
	 * @param count
	 * @param skip
	 * @param verbose
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Object changed for ver 0.92.
	 */
	public List<ListAddressTxsDetailed> listAddressTransactions(String address, long count, long skip, boolean verbose) throws HdacException {
		List<ListAddressTxsDetailed> listTransactionWallet = new ArrayList<ListAddressTxsDetailed>();

		Object objectTransactionWallet = executeListAddressTransactions(address, count, skip, verbose);
		listTransactionWallet = WalletTransactionFormatter.formatListAddressTxs((List<Object>) objectTransactionWallet);

		return listTransactionWallet;
	}

	public List<TransactionWallet> listAddressTransactionsWithoutDetail(String address, long count, long skip, boolean verbose) throws HdacException {
		List<TransactionWallet> listTransactionWallet = new ArrayList<TransactionWallet>();

		Object objectTransactionWallet = executeListAddressTransactions(address, count, skip, verbose);
		listTransactionWallet = WalletTransactionFormatter.formatListTransactionWallet((List<Object>) objectTransactionWallet);

		return listTransactionWallet;
	}

	/**
	 * {@link #listAddressTransactions(String, long, long, boolean) with verbose
	 * at false}
	 * 
	 * @param address
	 * @param count
	 * @param skip
	 * @return
	 * @throws HdacException
	 */
	public List<TransactionWallet> listAddressTransactions(String address, long count, long skip) throws HdacException {
		return listAddressTransactionsWithoutDetail(address, count, skip, false);
	}

	/**
	 * {@link #listAddressTransactions(String, long, long) with skip at 0}
	 * 
	 * @param address
	 * @param count
	 * @return
	 * @throws HdacException
	 */
	public List<TransactionWallet> listAddressTransactions(String address, long count) throws HdacException {
		return listAddressTransactionsWithoutDetail(address, count, 0, false);
	}

	/**
	 * {@link #listAddressTransactions(String, long) with count at 10}
	 * 
	 * @param address
	 * @param count
	 * @return
	 * @throws HdacException
	 */
	public List<TransactionWallet> listAddressTransactions(String address) throws HdacException {
		return listAddressTransactionsWithoutDetail(address, 10, 0, false);
	}

	/**
	 * 
	 * listwallettransactions ( count skip includeWatchonly verbose)
	 * 
	 * Returns up to 'count' most recent transactions skipping the first 'from'
	 * transactions for account 'account'.
	 * 
	 * Arguments: 1. count (numeric, optional, default=10) The number of
	 * transactions to return 2. skip (numeric, optional, default=0) The number
	 * of transactions to skip 3. includeWatchonly (bool, optional,
	 * default=false) Include transactions to watchonly addresses (see
	 * 'importaddress') 4. verbose (bool, optional, default=false) If true,
	 * returns detailed array of inputs and outputs and raw hex of transactions
	 * 
	 * Result: [ { "balance": {...}, (object) Changes in wallet balance. {
	 * "amount": x.xxx, (numeric) The amount in native currency. Negative value
	 * means amount was send by the wallet, positive - received "assets": {...},
	 * (object) Changes in asset amounts. } "myaddresses": [...], (array) Array
	 * of wallet addresses involved in transaction "addresses": [...], (array)
	 * Array of counterparty addresses involved in transaction "permissions":
	 * [...], (array) Changes in permissions "issue": {...}, (object) Issue
	 * details "data" : "metadata", (array) Hexadecimal representation of
	 * metadata appended to the transaction "confirmations": n, (numeric) The
	 * number of confirmations for the transaction. Available for 'send' and
	 * 'receive' category of transactions. "blockhash": "hashvalue",(string) The
	 * block hash containing the transaction. Available for 'send' and 'receive'
	 * category of transactions. "blockindex": n, (numeric) The block index
	 * containing the transaction. Available for 'send' and 'receive' category
	 * of transactions. "txid": "transactionid", (string) The transaction id.
	 * Available for 'send' and 'receive' category of transactions. "time": xxx,
	 * (numeric) The transaction time in seconds since epoch (midnight Jan 1
	 * 1970 GMT). "timereceived": xxx, (numeric) The time received in seconds
	 * since epoch (midnight Jan 1 1970 GMT). Available for 'send' and 'receive'
	 * category of transactions. "comment": "...", (string) If a comment is
	 * associated with the transaction. "vin": [...], (array) If verbose=true.
	 * Array of input details "vout": [...], (array) If verbose=true. Array of
	 * output details "hex" : "data" (string) If verbose=true. Raw data for
	 * transaction } ]
	 * 
	 * Lists information about the 10 most recent transactions in this node's
	 * wallet, including how they affected the node's total balance.
	 * 
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Object changed for ver 0.92.
	 */
	public List<ListWalletTxs> listWalletTransaction(long count, long skip, boolean includeWatchonly, boolean verbose) throws HdacException {

		List<ListWalletTxs> listTransactionWallet = new ArrayList<ListWalletTxs>();

		Object objectTransactionWallet = executeListWalletTransaction(count, skip, includeWatchonly, verbose);
		listTransactionWallet = WalletTransactionFormatter.formatListTransactionWalletDetailed((List<Object>) objectTransactionWallet);

		return listTransactionWallet;
	}

	/**
	 * 
	 * listwallettransactions ( count skip includeWatchonly verbose)
	 * 
	 * Returns up to 'count' most recent transactions skipping the first 'from'
	 * transactions for account 'account'.
	 * 
	 * Arguments: 1. count (numeric, optional, default=10) The number of
	 * transactions to return 2. skip (numeric, optional, default=0) The number
	 * of transactions to skip 3. includeWatchonly (bool, optional,
	 * default=false) Include transactions to watchonly addresses (see
	 * 'importaddress') 4. verbose (bool, optional, default=false) If true,
	 * returns detailed array of inputs and outputs and raw hex of transactions
	 * 
	 * Result: [ { "balance": {...}, (object) Changes in wallet balance. {
	 * "amount": x.xxx, (numeric) The amount in native currency. Negative value
	 * means amount was send by the wallet, positive - received "assets": {...},
	 * (object) Changes in asset amounts. } "myaddresses": [...], (array) Array
	 * of wallet addresses involved in transaction "addresses": [...], (array)
	 * Array of counterparty addresses involved in transaction "permissions":
	 * [...], (array) Changes in permissions "issue": {...}, (object) Issue
	 * details "data" : "metadata", (array) Hexadecimal representation of
	 * metadata appended to the transaction "confirmations": n, (numeric) The
	 * number of confirmations for the transaction. Available for 'send' and
	 * 'receive' category of transactions. "blockhash": "hashvalue",(string) The
	 * block hash containing the transaction. Available for 'send' and 'receive'
	 * category of transactions. "blockindex": n, (numeric) The block index
	 * containing the transaction. Available for 'send' and 'receive' category
	 * of transactions. "txid": "transactionid", (string) The transaction id.
	 * Available for 'send' and 'receive' category of transactions. "time": xxx,
	 * (numeric) The transaction time in seconds since epoch (midnight Jan 1
	 * 1970 GMT). "timereceived": xxx, (numeric) The time received in seconds
	 * since epoch (midnight Jan 1 1970 GMT). Available for 'send' and 'receive'
	 * category of transactions. "comment": "...", (string) If a comment is
	 * associated with the transaction. "vin": [...], (array) If verbose=true.
	 * Array of input details "vout": [...], (array) If verbose=true. Array of
	 * output details "hex" : "data" (string) If verbose=true. Raw data for
	 * transaction } ]
	 * 
	 * Lists information about the 10 most recent transactions in this node's
	 * wallet, including how they affected the node's total balance.
	 * 
	 * @return
	 * @throws HdacException
	 */
	public List<TransactionWallet> listWalletTransactionWithoutDetail(long count, long skip, boolean includeWatchonly, boolean verbose) throws HdacException {
		List<TransactionWallet> listTransactionWallet = new ArrayList<TransactionWallet>();

		Object objectTransactionWallet = executeListWalletTransaction(count, skip, includeWatchonly, verbose);
		listTransactionWallet = WalletTransactionFormatter.formatListTransactionWallet((List<Object>) objectTransactionWallet);
		
		return listTransactionWallet;
	}

	/**
	 * {@link #listWalletTransaction(long, long, boolean, boolean) with verbose
	 * at false}
	 * 
	 * @param count
	 * @param skip
	 * @param includeWatchonly
	 * @return
	 * @throws HdacException
	 */
	public List<TransactionWallet> listWalletTransaction(long count, long skip, boolean includeWatchonly) throws HdacException {
		return listWalletTransactionWithoutDetail(count, skip, includeWatchonly, false);
	}

	/**
	 * {@link #listWalletTransaction(long, long, boolean) with includeWatchonly
	 * at false}
	 * 
	 * @param count
	 * @param skip
	 * @return
	 * @throws HdacException
	 */
	public List<TransactionWallet> listWalletTransaction(long count, long skip) throws HdacException {
		return listWalletTransactionWithoutDetail(count, skip, false, false);
	}

	/**
	 * {@link #listWalletTransaction(long, long) with skip at 0}
	 * 
	 * @param count
	 * @return
	 * @throws HdacException
	 */
	public List<TransactionWallet> listWalletTransaction(long count) throws HdacException {
		return listWalletTransactionWithoutDetail(count, 0, false, false);
	}

	/**
	 * {@link #listWalletTransaction(long) with count at 10}
	 * 
	 * @return
	 * @throws HdacException
	 */
	public List<TransactionWallet> listWalletTransaction() throws HdacException {
		return listWalletTransactionWithoutDetail(10, 0, false, false);
	}

	/**
	 * 
	 * send "address" amount|asset-quantities ( "comment" 
	 * "comment-to" subtractfeefromamount )
	 * 
	 * Send an amount (or several asset amounts) to a given address. 
	 * The amount is a real and is rounded to the nearest 0.00000001
	 * 
	 * Arguments: 1. address (string, required) The address to send to.
	 * 2. amount (numeric, required) The amount in native currency to send. 
	 * eg 0.1 or 2. asset-quantities (object, required) A json object of assets 
	 * to send 
	 * { 
	 * "asset-identifier" : asset-quantity
	 * ,... 
	 * }
	 * 3. "comment" (string, optional) A comment used to store what the transaction 
	 * is for. This is not part of the transaction, just kept in your wallet. 
	 * 4. "comment-to" (string, optional) A comment to store the name of the person 
	 * or organization to which you're sending the transaction. This is not part of the 
	 * transaction, just kept in your wallet. 5. subtractfeefromamount (boolean, optional
	 * , default=false) The fee will be deducted from the amount being sent.
	 * The recipient will receive less bitcoins than you enter in the amount field.
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param address
	 * @param assets
	 * @return transactionId
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public String send(String address, List<AssetQuantity> assets) throws HdacException {
		String stringSend = "";

		Object objectSend = executeSend(address, assets);
		if (verifyInstance(objectSend, String.class)) {
			stringSend = (String) objectSend;
		}

		return stringSend;
	}
	
	/**
	 * 
	 * sendfromaddress "from-address" "to-address" amount|asset-quantities (
	 * "comment" "comment-to" )
	 * 
	 * Send an amount (or several asset amounts) using specific address.
	 * 
	 * Arguments: 1. "from-address" (string, required) Address to send from. 2.
	 * "to-address" (string, required) The address to send to. 3. "amount"
	 * (numeric, required) The amount in native currency to send. eg 0.1 or 3.
	 * "asset-quantities" (object, required) A json object of assets to send {
	 * "asset-identifier" : asset-quantity ,... } 4. "comment" (string,
	 * optional) A comment used to store what the transaction is for. This is
	 * not part of the transaction, just kept in your wallet. 5. "comment-to"
	 * (string, optional) A comment to store the name of the person or
	 * organization to which you're sending the transaction. This is not part of
	 * the transaction, just kept in your wallet.
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * Sends assets to address, returning the txid.
	 * 
	 * @param fromAddress
	 * @param toAddress
	 * @param assets
	 * @return transactionId
	 * @throws HdacException
	 */
	
	/*
	 * Object changed for ver 0.92.
	 */
	public String sendFromAddress(String fromAddress, String toAddress, List<AssetQuantity> assets) throws HdacException {
		String stringSendFromAddress = "";

		Object objectSendFromAddress = executeSendFromAddress(fromAddress, toAddress, assets);
		if (verifyInstance(objectSendFromAddress, String.class)) {
			stringSendFromAddress = (String) objectSendFromAddress;
		}

		return stringSendFromAddress;
	}

	/**
	 * 
	 * sendtoaddress "address" amount|asset-quantities ( "comment" "comment-to"
	 * )
	 * 
	 * Send an amount (or several asset amounts) to a given address. The amount
	 * is a real and is rounded to the nearest 0.00000001
	 * 
	 * Arguments: 1. "address" (string, required) The address to send to. 2.
	 * "amount" (numeric, required) The amount in native currency to send. eg
	 * 0.1 or 2. "asset-quantities" (object, required) A json object of assets
	 * to send { "asset-identifier" : asset-quantity ,... } 3. "comment"
	 * (string, optional) A comment used to store what the transaction is for.
	 * This is not part of the transaction, just kept in your wallet. 4.
	 * "comment-to" (string, optional) A comment to store the name of the person
	 * or organization to which you're sending the transaction. This is not part
	 * of the transaction, just kept in your wallet.
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * Sends one or more assets to address, returning the txid.
	 * 
	 * @param address
	 * @param assetsNames
	 * @param quantities
	 * @return
	 * @throws HdacException
	 */
	public String sendToAddress(String address, List<BalanceAssetGeneral> assets) throws HdacException {
		String stringSendToAddress = "";

		Object objectSendToAddress = executeSendToAddress(address, assets);
		if (verifyInstance(objectSendToAddress, String.class)) {
			stringSendToAddress = (String) objectSendToAddress;
		}

		return stringSendToAddress;
	}

	/**
	 * 
	 * sendtoaddress "address" amount|asset-quantities ( "comment" "comment-to"
	 * )
	 * 
	 * Send an amount (or several asset amounts) to a given address. The amount
	 * is a real and is rounded to the nearest 0.00000001
	 * 
	 * Arguments: 1. "address" (string, required) The address to send to. 2.
	 * "amount" (numeric, required) The amount in native currency to send. eg
	 * 0.1 or 2. "asset-quantities" (object, required) A json object of assets
	 * to send { "asset-identifier" : asset-quantity ,... } 3. "comment"
	 * (string, optional) A comment used to store what the transaction is for.
	 * This is not part of the transaction, just kept in your wallet. 4.
	 * "comment-to" (string, optional) A comment to store the name of the person
	 * or organization to which you're sending the transaction. This is not part
	 * of the transaction, just kept in your wallet.
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * Sends one or more assets to address, returning the txid.
	 * 
	 * @param address
	 * @param amount
	 * @return
	 * @throws HdacException
	 */
	public String sendToAddress(String address, double amount) throws HdacException {
		String stringSendToAddress = "";

		Object objectSendToAddress = executeSendToAddress(address, amount);
		if (verifyInstance(objectSendToAddress, String.class)) {
			stringSendToAddress = (String) objectSendToAddress;
		}

		return stringSendToAddress;
	}

	/**
	 * This works like sendtoaddress, but includes the data-hex hexadecimal
	 * metadata in an additional OP_RETURN transaction output.
	 * 
	 * sendwithmetadata "address" amount|asset-quantities data-hex
	 * 
	 * Send an amount (or several asset amounts) to a given address with
	 * appended metadata.
	 * 
	 * Arguments: 1. "address" (string, required) The address to send to. 2.
	 * "amount" (numeric, required) The amount in native currency to send. eg
	 * 0.1 or 2. "asset-quantities" (object, required) A json object of assets
	 * to send [ "asset-identifier" : asset-quantity ,... ] 3. "data-hex"
	 * (string, required) Data hex string
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param address
	 * @param assets
	 * @param hexMetaData
	 * @return
	 * @throws HdacException
	 */
	public String sendWithMetaData(String address, List<AssetQuantity> assets, String hexMetaData) throws HdacException {
		String stringSendWithMetaData = "";

		Object objectSendWithMetaData = executeSendWithMetaData(address, assets, hexMetaData);
		if (verifyInstance(objectSendWithMetaData, String.class)) {
			stringSendWithMetaData = (String) objectSendWithMetaData;
		}

		return stringSendWithMetaData;
	}

	/**
	 * This works like sendtoaddress, but includes the data-hex hexadecimal
	 * metadata in an additional OP_RETURN transaction output.
	 * 
	 * sendwithmetadata "address" amount|asset-quantities data-hex
	 * 
	 * Send an amount (or several asset amounts) to a given address with
	 * appended metadata.
	 * 
	 * Arguments: 1. "address" (string, required) The address to send to. 2.
	 * "amount" (numeric, required) The amount in native currency to send. eg
	 * 0.1 or 2. "asset-quantities" (object, required) A json object of assets
	 * to send [ "asset-identifier" : asset-quantity ,... ] 3. "data-hex"
	 * (string, required) Data hex string
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param address
	 * @param amount
	 * @param hexMetaData
	 * @return
	 * @throws HdacException
	 */
	public String sendWithMetaData(String address, double amount, String hexMetaData) throws HdacException {
		String stringSendWithMetaData = "";

		Object objectSendWithMetaData = executeSendWithMetaData(address, amount, hexMetaData);
		if (verifyInstance(objectSendWithMetaData, String.class)) {
			stringSendWithMetaData = (String) objectSendWithMetaData;
		}

		return stringSendWithMetaData;
	}

	/**
	 * This works like sendtoaddress, but with control over the from-address
	 * whose funds are used, and with the data-hex hexadecimal metadata added in
	 * an additional OP_RETURN transaction output. Any change from the
	 * transaction is sent back to from-address.
	 * 
	 * sendwithmetadatafrom "from-address" "to-address" amount|asset-quantities
	 * data-hex
	 * 
	 * Send an amount (or several asset amounts) using specific address.
	 * 
	 * Arguments: 1. "from-address" (string, required) Address to send from. 2.
	 * "to-address" (string, required) The address to send to. 3. "amount"
	 * (numeric, required) The amount in native currency to send. eg 0.1 or 3.
	 * "asset-quantities" (object, required) A json object of assets to send [
	 * "asset-identifier" : asset-quantity ,... ] 4. "data-hex" (string,
	 * required) Data hex string
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param address
	 * @param assets
	 * @param hexMetaData
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Object changed for ver 0.92.
	 */
	public String sendWithMetaDataFrom(String fromAddress, String toAddress, List<BalanceAssetGeneral> assets, String hexMetaData) throws HdacException {
		String stringSendWithMetaDataFrom = "";

		Object objectSendWithMetaDataFrom = executeSendWithMetaDataFrom(fromAddress, toAddress, assets, hexMetaData);
		if (verifyInstance(objectSendWithMetaDataFrom, String.class)) {
			stringSendWithMetaDataFrom = (String) objectSendWithMetaDataFrom;
		}

		return stringSendWithMetaDataFrom;
	}

	/**
	 * This works like sendtoaddress, but with control over the from-address
	 * whose funds are used, and with the data-hex hexadecimal metadata added in
	 * an additional OP_RETURN transaction output. Any change from the
	 * transaction is sent back to from-address.
	 * 
	 * sendwithmetadatafrom "from-address" "to-address" amount|asset-quantities
	 * data-hex
	 * 
	 * Send an amount (or several asset amounts) using specific address.
	 * 
	 * Arguments: 1. "from-address" (string, required) Address to send from. 2.
	 * "to-address" (string, required) The address to send to. 3. "amount"
	 * (numeric, required) The amount in native currency to send. eg 0.1 or 3.
	 * "asset-quantities" (object, required) A json object of assets to send [
	 * "asset-identifier" : asset-quantity ,... ] 4. "data-hex" (string,
	 * required) Data hex string
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param address
	 * @param amount
	 * @param hexMetaData
	 * @return
	 * @throws HdacException
	 */
	public String sendWithMetaDataFrom(String fromAddress, String toAddress, double amount, String hexMetaData) throws HdacException {
		String stringSendWithMetaDataFrom = "";

		Object objectSendWithMetaDataFrom = executeSendWithMetaDataFrom(fromAddress, toAddress, amount, hexMetaData);
		if (verifyInstance(objectSendWithMetaDataFrom, String.class)) {
			stringSendWithMetaDataFrom = (String) objectSendWithMetaDataFrom;
		}

		return stringSendWithMetaDataFrom;
	}
	
	/**
	 * 
	 * sendwithdatafrom "from-address" "to-address" amount|asset-quantities "data-hex"|object
	 * 
	 * Send an amount (or several asset amounts) using specific address.
	 * 
	 * Arguments: 1. "from-address" (string, required) Address to send from.
	 * 2. "to-address" (string, required) The address to send to.
	 * 3. amount (numeric, required) The amount in native currency to send. eg 0.1 or
	 * 3. asset-quantities (object, required) A json object of assets to send
	 * {"asset-identifier" : asset-quantity ,... }
	 * 4. "data-hex" (string, required) Data hex string or 4. publish-new-stream-item
	 * (object, required) A json object with stream item
	 * {"for" : stream-identifier (string,required) Stream identifier - one of the following: 
	 * stream txid, stream reference, stream name. "key" : key (string,optional, default: "") Item key
	 * "data" : data-hex (string,optional, default: "") Data hex string}
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 *  
	 * @param from-address
	 * @param to-address
	 * @param amount|asset-quantities
	 * @param "data-hex"|object
	 * @return
	 * @throws HdacException
	 */
	public String sendWithDataFrom(String fromAddress, String toAddress, String assetName, Integer assetValue, String metadata) throws HdacException {
		String stringSendWithDataFrom = "";

		Object objectSendWithDataFrom = executeSendWithDataFrom(fromAddress, toAddress, assetName, assetValue, metadata);
		if (verifyInstance(objectSendWithDataFrom, String.class)) {
			stringSendWithDataFrom = (String) objectSendWithDataFrom;
		}

		return stringSendWithDataFrom;
	}
}
