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

import com.hdactech.command.builders.QueryBuilderBlock;
import com.hdactech.object.Block;
import com.hdactech.object.formatters.BlockFormatter;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class BlockCommand extends QueryBuilderBlock {

	public BlockCommand(String ip, String port, String login, String password, RuntimeParameters runtimeparameters) {
		initialize(ip, port, login, password, runtimeparameters);
	}

	/**
	 * getbestblockhash
	 * 
	 * Returns the hash of the best (tip) block in the longest block chain.
	 * 
	 * Result
	 * "hex" (string) the block hash hex encoded
	 * 
	 * @return the hash of the best block
	 * @throws HdacException
	 */
	public String getBestBlockHash() throws HdacException {
		String stringBestBlockHash = "";

		Object objectBestBlockHash = executeGetBestBlockHash();
		if (verifyInstance(objectBestBlockHash, String.class)) {
			stringBestBlockHash = (String) objectBestBlockHash;
		}

		return stringBestBlockHash;
	}

	/**
	 * getblock "hash/height" ( verbose )
	 * 
	 * If verbose is false, returns a string that is serialized, hex-encoded data for block 'hash'.
	 * If verbose is true, returns an Object with information about block <hash>.
	 * 
	 * Arguments:
	 * 1. "hash/height" (string, required) The block hash or block height in active chain
	 * 2. verbose (boolean, optional, default=true) true for a json object, false for the hex encoded data
	 * 
	 * Result (for verbose = true):
	 * {
	 * "hash" : "hash", (string) the block hash (same as provided)
	 * "miner" : "miner", (string) the address of the miner
	 * "confirmations" : n, (numeric) The number of confirmations, or -1 if the block is not on the main chain
	 * "size" : n, (numeric) The block size
	 * "height" : n, (numeric) The block height or index
	 * "version" : n, (numeric) The block version
	 * "merkleroot" : "xxxx", (string) The merkle root
	 * "tx" : [ (array of string) The transaction ids
	 * "transactionid" (string) The transaction id
	 * ,...
	 * ],
	 * "time" : ttt, (numeric) The block time in seconds since epoch (Jan 1 1970 GMT)
	 * "nonce" : n, (numeric) The nonce
	 * "bits" : "1d00ffff", (string) The bits
	 * "difficulty" : x.xxx, (numeric) The difficulty
	 * "previousblockhash" : "hash", (string) The hash of the previous block
	 * "nextblockhash" : "hash" (string) The hash of the next block
	 * }
	 * 
	 * Result (for verbose=false):
	 * "data" (string) A string that is serialized, hex-encoded data for block 'hash'.
	 * 
	 * 
	 * @param hash
	 * @param verbose
	 * @return Block : information about the block with hash (retrievable from getblockhash) or at the given height in
	 *         the active chain
	 * @throws HdacException
	 */
	public Block getBlock(String blockHash, boolean verbose) throws HdacException {
		Object objectBlock = executeGetBlock(blockHash, verbose);
		Block block = BlockFormatter.formatBlock(objectBlock);

		return block;
	}

        /****
         * added by leo
         * @param blockidentifiers
         * @param verbose
         * @return List<Block>
         */
	
		/*
		 * Parameter(verbose) type changed for ver 0.92.
		 */
        public List<Block> listBlocksList(String blockidentifiers, long verbose){
            try {
                List<Block> blocks=new ArrayList<Block>();
                Object objectBlock=executeListBlocks(blockidentifiers,verbose);
                int size=((ArrayList)objectBlock).size();
                for(int a=0;a<size;a++){
                    blocks.add(BlockFormatter.formatBlock(((ArrayList)objectBlock).get(a)));
                }
                return blocks;
            } catch (HdacException ex) {
                Logger.getLogger(BlockCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        
	/**
	 * {@link #getBlock(String, boolean)} without verbose
	 * 
	 * @param blockHash
	 * @return Block : information about the block with hash (retrievable from getblockhash) or at the given height in
	 *         the active chain
	 * @throws HdacException
	 */
	public Block getBlock(String blockHash) throws HdacException {
		return getBlock(blockHash, true);
	}

	/**
	 * getblock "hash/height" ( verbose )
	 * 
	 * If verbose is false, returns a string that is serialized, hex-encoded data for block 'hash'.
	 * If verbose is true, returns an Object with information about block <hash>.
	 * 
	 * Arguments:
	 * 1. "hash/height" (string, required) The block hash or block height in active chain
	 * 2. verbose (boolean, optional, default=true) true for a json object, false for the hex encoded data
	 * 
	 * Result (for verbose = true):
	 * {
	 * "hash" : "hash", (string) the block hash (same as provided)
	 * "miner" : "miner", (string) the address of the miner
	 * "confirmations" : n, (numeric) The number of confirmations, or -1 if the block is not on the main chain
	 * "size" : n, (numeric) The block size
	 * "height" : n, (numeric) The block height or index
	 * "version" : n, (numeric) The block version
	 * "merkleroot" : "xxxx", (string) The merkle root
	 * "tx" : [ (array of string) The transaction ids
	 * "transactionid" (string) The transaction id
	 * ,...
	 * ],
	 * "time" : ttt, (numeric) The block time in seconds since epoch (Jan 1 1970 GMT)
	 * "nonce" : n, (numeric) The nonce
	 * "bits" : "1d00ffff", (string) The bits
	 * "difficulty" : x.xxx, (numeric) The difficulty
	 * "previousblockhash" : "hash", (string) The hash of the previous block
	 * "nextblockhash" : "hash" (string) The hash of the next block
	 * }
	 * 
	 * Result (for verbose=false):
	 * "data" (string) A string that is serialized, hex-encoded data for block 'hash'.
	 * 
	 * 
	 * @param height
	 * @param verbose
	 * @return Block : information about the block with hash (retrievable from getblockhash) or at the given height in
	 *         the active chain
	 * @throws HdacException
	 */
	public Block getBlock(long blockHeight, boolean verbose) throws HdacException {
	  int verboseValue = 0;
	  if (verbose) {
	    verboseValue = 1;
	  }
		Object objectBlock = executeGetBlock(blockHeight, verboseValue);
		Block block = BlockFormatter.formatBlock(objectBlock);

		return block;
	}

	/**
	 * {@link #getBlock(long, boolean)} without verbose
	 * 
	 * @param blockHash
	 * @return Block : information about the block with hash (retrievable from getblockhash) or at the given height in
	 *         the active chain
	 * @throws HdacException
	 */
	public Block getBlock(long blockHeight) throws HdacException {
		return getBlock(blockHeight, true);
	}

	/**
	 * getblockcount
	 * 
	 * Returns the number of blocks in the longest block chain.
	 * 
	 * Result:
	 * n (numeric) The current block count
	 * 
	 * @return The Actual count of blocks in the BlockChain.
	 * @throws HdacException
	 */
	public long getBlockCount() throws HdacException {
		long stringBlockCount = 0;

		Object objectBlockCount = executeGetBlockCount();
		if (verifyInstance(objectBlockCount, long.class)) {
			stringBlockCount = (Long) objectBlockCount;
		} else if (verifyInstance(objectBlockCount, Double.class)) {
			stringBlockCount = ((Double)objectBlockCount).longValue();
		}

		return stringBlockCount;
	}

	/**
	 * getblockhash index
	 * 
	 * Returns hash of block in best-block-chain at index provided.
	 * 
	 * Arguments:
	 * 1. index (numeric, required) The block index
	 * 
	 * Result:
	 * "hash" (string) The block hash
	 * 
	 * @return the hash of the best block
	 * @throws HdacException
	 */
	public String getBlockHash(long index) throws HdacException {
		String stringBlockHash = "";

		Object objectBlockHash = executeGetBlockHash(index);
		if (verifyInstance(objectBlockHash, String.class)) {
			stringBlockHash = (String) objectBlockHash;
		}

		return stringBlockHash;
	}

}
