/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command;

import com.hdactech.command.builders.QueryBuilderChain;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class ChainCommand extends QueryBuilderChain {

	public ChainCommand(String ip, String port, String login, String password, RuntimeParameters runtimeparameters) {
		initialize(ip, port, login, password, runtimeparameters);
	}

	/**
	 * getinfo
	 * Returns an object containing various state info.
	 * 
	 * Result:
	 * {
	 * "version": xxxxx, (numeric) the server version
	 * "protocolversion": xxxxx, (numeric) the protocol version
	 * "chainname": "xxxx", (string) hdac network name
	 * "description": "xxxx", (string) network desctription
	 * "protocol": "xxxx", (string) protocol - hdac or bitcoin
	 * "port": xxxx, (numeric) network port
	 * "setupblocks": "xxxx", (string) number of network setup blocks
	 * "walletversion": xxxxx, (numeric) the wallet version
	 * "balance": xxxxxxx, (numeric) the total native currency balance of the wallet
	 * "walletdbversion": xxxxx, (numeric) the wallet database version
	 * "blocks": xxxxxx, (numeric) the current number of blocks processed in the server
	 * "timeoffset": xxxxx, (numeric) the time offset
	 * "connections": xxxxx, (numeric) the number of connections
	 * "proxy": "host:port", (string, optional) the proxy used by the server
	 * "difficulty": xxxxxx, (numeric) the current difficulty
	 * "testnet": true|false, (boolean) if the server is using testnet or not
	 * "keypoololdest": xxxxxx, (numeric) the timestamp (seconds since GMT epoch) of the oldest pre-generated key in the
	 * key pool
	 * "keypoolsize": xxxx, (numeric) how many new keys are pre-generated
	 * "unlocked_until": ttt, (numeric) the timestamp in seconds since epoch (midnight Jan 1 1970 GMT) that the wallet
	 * is unlocked for transfers, or 0
	 * if the wallet is locked
	 * "paytxfee": x.xxxx, (numeric) the transaction fee set in btc/kb
	 * "relayfee": x.xxxx, (numeric) minimum relay fee for non-free transactions in btc/kb
	 * "errors": "..." (string) any error messages
	 * }
	 * 
	 * @return
	 * @throws HdacException
	 */
	
	//리턴되는 결과는 JsonObject으로 파싱해서 사용 가능함. 일정 부족으로 급하게 바이패스 함. hjs0317
	public Object ObjectGetInfo() throws HdacException {
		return executeObjectGetInfo();
	}
	
	public String getInfo() throws HdacException {
		return executeGetInfo();
	}
	
	/**
	 * help 
	 * 
	 * List all commands, or get help for a specified command.
	 * 
	 * Result:
	 * "text" (string) The help text
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public String help() throws HdacException {
		return executeHelp(null);
	}
	
	/**
	 * help ( command )
	 * 
	 * List all commands, or get help for a specified command.
	 * 
	 * Arguments:
	 * 1. "command" (string, optional) The command to get help on
	 * 
	 * Result:
	 * "text"  (string) The help text
	 * 
	 * @param command
	 * @return
	 * @throws HdacException
	 */
	
	/*
	 * Parameter added for ver 0.92.
	 */
	public String help(String command) throws HdacException {
		return executeHelp(command);
	}
	
	/**
	 * getblockchainparams ( displaynames with-upgrades )
	 * 
	 * Returns a list of values of this blockchain's parameters
	 * 
	 * Arguments: 1. displaynames (boolean, optional, default=true) use display names 
	 * instead of internal 2. with-upgrades (boolean, optional, default=true) Take 
	 * upgrades into account 
	 * 
	 * Result: An object containing various blockchain parameters.
	 * 
	 * @param displaynames
	 * @param with-upgrades
	 * @return Information of Blockchain parameters
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public Object getblockchainParams() throws HdacException {
		return executeGetBlockchainParams();
	}
	
	/**
	 * getpeerinfo
	 * 
	 * Returns general information about this node and blockchain.
	 * 
	 * Result: 
	 *[
	 *{
	 *"id": n, (numeric) Peer index
	 *"addr":"host:port", (string) The ip address and port of the peer
	 *"addrlocal":"ip:port", (string) local address
	 *"services":"xxxxxxxxxxxxxxxx", (string) The services offered
	 *"lastsend": ttt, (numeric) The time in seconds since epoch (Jan 1 1970 GMT) of the last send
	 *"lastrecv": ttt, (numeric) The time in seconds since epoch (Jan 1 1970 GMT) of the last receive
	 *"bytessent": n, (numeric) The total bytes sent
	 *"bytesrecv": n, (numeric) The total bytes received
	 *"conntime": ttt, (umeric) The connection time in seconds since epoch (Jan 1 1970 GMT)
	 *"pingtime": n, (numeric) ping time
	 *"pingwait": n, (numeric) ping wait
	 *"version": v, (numeric) The peer version, such as 7001
	 *"subver": "/Satoshi:0.8.5/", (string) The string version
	 *"handshakelocal": n, (string) If protocol is Hdac. Address used by local node for handshake.
	 *"handshake": n, (string) If protocol is Hdac. Address used by remote node for handshake.
	 *"inbound": true|false, (boolean) Inbound (true) or Outbound (false)
	 *"startingheight": n, (numeric) The starting height (block) of the peer
	 *"banscore": n, (numeric) The ban score
	 *"synced_headers": n, (numeric) The last header we have in common with this peer
	 *"synced_blocks": n, (numeric) The last block we have in common with this peer
	 *"inflight": [n,(numeric) The heights of blocks we're currently asking from this peer...]} ,... ]
	 * 
	 * @return 
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public Object getPeerInfo() throws HdacException {
		return executeGetPeerInfo();
	}
}
