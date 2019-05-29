/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command.builders;

import com.hdactech.command.HdacException;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class QueryBuilderBlock extends QueryBuilderCommon {

	/**
	 * getbestblockhash
	 * 
	 * Returns the hash of the best (tip) block in the longest block chain.
	 * 
	 * Result "hex" (string) the block hash hex encoded
	 * 
	 * @return the hash of the best block
	 * @throws HdacException
	 */
	protected Object executeGetBestBlockHash() throws HdacException {
		return execute(CommandEnum.GETBESTBLOCKHASH);
	}

	/**
	 * getblock "hash/height" ( verbose )
	 * 
	 * If verbose is false, returns a string that is serialized, hex-encoded data
	 * for block 'hash'. If verbose is true, returns an Object with information
	 * about block <hash>.
	 * 
	 * Arguments: 1. "hash/height" (string, required) The block hash or block height
	 * in active chain 2. verbose (boolean, optional, default=true) true for a json
	 * object, false for the hex encoded data
	 * 
	 * Result (for verbose = true): { "hash" : "hash", (string) the block hash (same
	 * as provided) "miner" : "miner", (string) the address of the miner
	 * "confirmations" : n, (numeric) The number of confirmations, or -1 if the
	 * block is not on the main chain "size" : n, (numeric) The block size "height"
	 * : n, (numeric) The block height or index "version" : n, (numeric) The block
	 * version "merkleroot" : "xxxx", (string) The merkle root "tx" : [ (array of
	 * string) The transaction ids "transactionid" (string) The transaction id ,...
	 * ], "time" : ttt, (numeric) The block time in seconds since epoch (Jan 1 1970
	 * GMT) "nonce" : n, (numeric) The nonce "bits" : "1d00ffff", (string) The bits
	 * "difficulty" : x.xxx, (numeric) The difficulty "previousblockhash" : "hash",
	 * (string) The hash of the previous block "nextblockhash" : "hash" (string) The
	 * hash of the next block }
	 * 
	 * Result (for verbose=false): "data" (string) A string that is serialized,
	 * hex-encoded data for block 'hash'.
	 * 
	 * 
	 * @param hash
	 * @param verbose
	 * @return information about the block with hash (retrievable from getblockhash)
	 *         or at the given height in the active chain
	 * @throws HdacException
	 */
	protected Object executeGetBlock(String hash, boolean verbose) throws HdacException {
		return execute(CommandEnum.GETBLOCK, hash, verbose);
	}

	/****
	 * return blockList object for block identified by string with form like 12-13
	 * 
	 * @param blockidentifiers
	 * @param verbose
	 * @return Object
	 * @throws HdacException
	 */
	
	/*
	 * Parameter(verbose) type changed for ver 0.92. 
	 */
	protected Object executeListBlocks(String blockidentifiers, long verbose) throws HdacException {
		return execute(CommandEnum.LISTBLOCKS, blockidentifiers, verbose);
	}

	/**
	 * getblock "hash/height" ( verbose )
	 * 
	 * If verbose is false, returns a string that is serialized, hex-encoded data
	 * for block 'hash'. If verbose is true, returns an Object with information
	 * about block <hash>.
	 * 
	 * Arguments: 1. "hash/height" (string, required) The block hash or block height
	 * in active chain 2. verbose (boolean, optional, default=true) true for a json
	 * object, false for the hex encoded data
	 * 
	 * Result (for verbose = true): { "hash" : "hash", (string) the block hash (same
	 * as provided) "miner" : "miner", (string) the address of the miner
	 * "confirmations" : n, (numeric) The number of confirmations, or -1 if the
	 * block is not on the main chain "size" : n, (numeric) The block size "height"
	 * : n, (numeric) The block height or index "version" : n, (numeric) The block
	 * version "merkleroot" : "xxxx", (string) The merkle root "tx" : [ (array of
	 * string) The transaction ids "transactionid" (string) The transaction id ,...
	 * ], "time" : ttt, (numeric) The block time in seconds since epoch (Jan 1 1970
	 * GMT) "nonce" : n, (numeric) The nonce "bits" : "1d00ffff", (string) The bits
	 * "difficulty" : x.xxx, (numeric) The difficulty "previousblockhash" : "hash",
	 * (string) The hash of the previous block "nextblockhash" : "hash" (string) The
	 * hash of the next block }
	 * 
	 * Result (for verbose=false): "data" (string) A string that is serialized,
	 * hex-encoded data for block 'hash'.
	 * 
	 * 
	 * @param height
	 * @param verbose
	 * @return information about the block with hash (retrievable from getblockhash)
	 *         or at the given height in the active chain
	 * @throws HdacException
	 */
	protected Object executeGetBlock(long height, int verbose) throws HdacException {
		return execute(CommandEnum.GETBLOCK, String.valueOf(height), verbose);
	}

	/**
	 * 
	 * getblockcount
	 * 
	 * Returns the number of blocks in the longest block chain.
	 * 
	 * Result: n (numeric) The current block count
	 * 
	 * @return The Actual count of blocks in the BlockChain.
	 * @throws HdacException
	 */
	protected Object executeGetBlockCount() throws HdacException {
		return execute(CommandEnum.GETBLOCKCOUNT);
	}

	/**
	 * getblockhash index
	 * 
	 * Returns hash of block in best-block-chain at index provided.
	 * 
	 * Arguments: 1. index (numeric, required) The block index
	 * 
	 * Result: "hash" (string) The block hash
	 * 
	 * @return the hash of the best block
	 * @throws HdacException
	 */
	
	/*
	 * Command changed for ver 0.92. 
	 */
	protected Object executeGetBlockHash(long index) throws HdacException {
		return execute(CommandEnum.GETBLOCKHASH, index);
	}

}
