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

import com.hdactech.command.builders.QueryBuilderGrant;
import com.hdactech.object.Address;
import com.hdactech.object.Permission;
import com.hdactech.object.formatters.GrantFormatter;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class GrantCommand extends QueryBuilderGrant {

	public static int CONNECT = QueryBuilderGrant.CONNECT;
	public static int SEND = QueryBuilderGrant.SEND;
	public static int RECEIVE = QueryBuilderGrant.RECEIVE;
	public static int ISSUE = QueryBuilderGrant.ISSUE;
	public static int MINE = QueryBuilderGrant.MINE;
	public static int ACTIVATE = QueryBuilderGrant.ACTIVATE;
	public static int ADMIN = QueryBuilderGrant.ADMIN;
	public static int CREATE = QueryBuilderGrant.CREATE;

	public static int WALLET = QueryBuilderGrant.WALLET;
	public static int WALLET_ISSUE = QueryBuilderGrant.WALLET_ISSUE;

	public GrantCommand(String ip, String port, String login, String password, RuntimeParameters runtimeparameters) {
		initialize(ip, port, login, password, runtimeparameters);
	}

	/**
	 * Give grant permissions to an address
	 * 
	 * grant "address(es)" "permission(s)" ( native-amount "comment"
	 * "comment-to" startblock endblock )
	 * 
	 * Grant permission(s) to a given address.
	 * 
	 * Arguments: 1. "address(es)" (string, required) The hdac addresses
	 * to send to (comma delimited) 2. "permission(s)" (string, required)
	 * Permission strings, comma delimited. Possible values:
	 * connect,send,receive,issue,mine,admin,activate 3. "native-amount"
	 * (numeric, optional) native currency amount to send. eg 0.1. Default - 0.0
	 * 4. "startblock" (numeric, optional) Block to apply permissions from
	 * (inclusive). Default - 0 5. "endblock" (numeric, optional) Block to apply
	 * permissions to (exclusive). Default - 4294967295 If -1 is specified
	 * default value is used. 6. "comment" (string, optional) A comment used to
	 * store what the transaction is for. This is not part of the transaction,
	 * just kept in your wallet. 7. "comment-to" (string, optional) A comment to
	 * store the name of the person or organization to which you're sending the
	 * transaction. This is not part of the transaction, just kept in your
	 * wallet.
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param address
	 *            to give permissions
	 * @param permissions
	 *            byte to give (possible values in this class, concatenation
	 *            with | )
	 * @return transactionId of grant action
	 * @throws HdacException
	 * @Deprecated
	 */
	public String grant(Address address, byte permissions) throws HdacException {
		Byte b = new Byte(permissions);
		return grant(address.getAddress(), b.intValue());
	}

	/**
	 * {@link #grant(Address, byte)} with permissions in format int
	 * 
	 * @param address
	 *            to give permissions
	 * @param permissions
	 *            int to give (possible values in this class, concatenation with
	 *            | )
	 * @return transactionId of grant action
	 * @throws HdacException
	 */
	public String grant(Address address, int permissions) throws HdacException {
		return grant(address.getAddress(), permissions);
	}

	/**
	 * {@link #grant(Address, byte)} with address in format string
	 * 
	 * @param address
	 *            String to give permissions
	 * @param permissions
	 *            byte to give (possible values in this class, concatenation
	 *            with | )
	 * @return transactionId of grant action
	 * @throws HdacException
	 * @Deprecated
	 */
	public String grant(String address, byte permissions) throws HdacException {
		Byte b = new Byte(permissions);
		return grant(address, b.intValue());
	}

	/**
	 * {@link #grant(Address, byte)} with address in format string and
	 * permissions in format int
	 * 
	 * @param address
	 *            String to give permissions
	 * @param permissions
	 *            int to give (possible values in this class, concatenation with
	 *            | )
	 * @return transactionId of grant action
	 * @throws HdacException
	 */
	public String grant(String address, int permissions) throws HdacException {
		String grant = "";

		Object objectGrant = executeGrant(address, permissions);
		if (verifyInstance(objectGrant, String.class)) {
			grant = (String) objectGrant;
		}

		return grant;
	}
	
	/*
	 * Command changed for ver 0.92.
	 */
	public String grant(String address, String permissions) throws HdacException {
		Byte b = new Byte(permissions);
		return grant(address, b.intValue());
	}

	/**
	 * Grants permissions to addresses From an address
	 * 
	 * grantfrom "from-address" "to-address(es)" "permission(s)" ( native-amount
	 * "comment" "comment-to" startblock endblock )
	 * 
	 * Grant permission using specific address.
	 * 
	 * Arguments: 1. "from-address" (string, required) Address used for grant.
	 * 2. "to-address(es)" (string, required) The hdac addresses to grant
	 * permissions to 3. "permission(s)" (string, required) Permission strings,
	 * comma delimited. Possible values:
	 * connect,send,receive,issue,mine,admin,activate 4. "native-amount"
	 * (numeric, optional) native currency amount to send. eg 0.1. Default - 0.0
	 * 5. "startblock" (numeric, optional) Block to apply permissions from
	 * (inclusive). Default - 0 6. "endblock" (numeric, optional) Block to apply
	 * permissions to (exclusive). Default - 4294967295 If -1 is specified
	 * default value is used. 7. "comment" (string, optional) A comment used to
	 * store what the transaction is for. This is not part of the transaction,
	 * just kept in your wallet. 8. "comment-to" (string, optional) A comment to
	 * store the name of the person or organization to which you're sending the
	 * transaction. This is not part of the transaction, just kept in your
	 * wallet.
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param addressFrom
	 *            String giving permissions
	 * @param address
	 *            to give permissions
	 * @param permissions
	 *            byte to give (possible values in this class, concatenation
	 *            with | )
	 * @return transactionId of grantFrom action
	 * @throws HdacException
	 * @Deprecated
	 */
	public String grantFrom(Address addressFrom, Address address, byte permissions) throws HdacException {
		Byte b = new Byte(permissions);
		return grantFrom(addressFrom.getAddress(), address.getAddress(), b.intValue());
	}

	/**
	 * {@link #grantFrom(Address, byte)} with permissions in format int
	 * 
	 * @param addressFrom
	 *            String giving permissions
	 * @param address
	 *            to give permissions
	 * @param permissions
	 *            int to give (possible values in this class, concatenation with
	 *            | )
	 * @return transactionId of grantFrom action
	 * @throws HdacException
	 */
	public String grantFrom(Address addressFrom, Address address, int permissions) throws HdacException {
		return grantFrom(addressFrom.getAddress(), address.getAddress(), permissions);
	}

	/**
	 * {@link #grantFrom(Address, byte)} with address in format string
	 * 
	 * @param addressFrom
	 *            String giving permissions
	 * @param address
	 *            String to give permissions
	 * @param permissions
	 *            byte to give (possible values in this class, concatenation
	 *            with | )
	 * @return transactionId of grantFrom action
	 * @throws HdacException
	 * @Deprecated
	 */
	public String grantFrom(String addressFrom, String address, byte permissions) throws HdacException {
		Byte b = new Byte(permissions);
		return grantFrom(addressFrom, address, b.intValue());
	}

	/**
	 * {@link #grantFrom(Address, byte)} with address in format string and
	 * permissions in format int
	 * 
	 * @param addressFrom
	 *            String giving permissions
	 * @param address
	 *            String to give permissions
	 * @param permissions
	 *            int to give (possible values in this class, concatenation with
	 *            | )
	 * @return transactionId of grantFrom action
	 * @throws HdacException
	 */
	public String grantFrom(String addressFrom, String address, int permissions) throws HdacException {
		String grant = "";

		Object objectGrant = executeGrantFrom(addressFrom, address, permissions);
		if (verifyInstance(objectGrant, String.class)) {
			grant = (String) objectGrant;
		}

		return grant;
	}
	
	/**
	 * 
	 * grantwithdata "address(es)" "permission(s)" "data-hex"|object ( native-amount startblock endblock )
	 * 
	 * Grant permission(s) with metadata to a given address.
	 * 
	 * Arguments: 1. "address(es)" (string, required) The hdac addresses to send 
	 * to (comma delimited) 2. "permission(s)" (string, required) Permission strings, 
	 * comma delimited. Global: connect,send,receive,issue,mine,admin,activate,create 
	 * or per-asset: asset-identifier.issue,admin or per-stream: stream-identifier.write,
	 * activate,admin 3. "data-hex" (string, required) Data hex string  or 
	 * 3. publish-new-stream-item (object, required) A json object with stream item 
	 * {"for" : stream-identifier (string,required) Stream identifier - one of the following: stream txid, 
	 * stream reference, stream name.
	 *"key" : key (string,optional, default: "") Item key
	 *"data" : data-hex (string,optional, default: "") Data hex string}
	 *4. native-amount (numeric, optional)  Native currency amount to send. eg 0.1. Default - 0.0
	 *5. startblock (numeric, optional)  Block to apply permissions from (inclusive). Default - 0
	 *6. endblock (numeric, optional)  Block to apply permissions to (exclusive). Default - 4294967295
	 *If -1 is specified default value is used.
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param address
	 * @param permissions
	 * @param data-hex
	 * @return transactionId of grantwithdata action
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public String grantWithData(String address, int permissions, String dataHex) throws HdacException {
		String txId = "";
		
		Object objectTxId = executeGrantWithData(address, permissions, dataHex);
		if(verifyInstance(objectTxId, String.class)) {
			txId = (String) objectTxId;
		}
		return txId;
	}
	
	/**
	 * 
	 * grantwithdatafrom "from-address" "to-address(es)" "permission(s)" "data-hex"|object ( native-amount startblock endblock )
	 * 
	 * Grant permission with metadata using specific address.
	 * 
	 * Arguments: 1. "from-address" (string, required) Address used for grant.
	 * 2. "address(es)" (string, required) The hdac addresses to send to 
	 * (comma delimited) 3. "permission(s)" (string, required) Permission strings, 
	 * comma delimited. Global: connect,send,receive,issue,mine,admin,activate,create 
	 * or per-asset: asset-identifier.issue,admin or per-stream: stream-identifier.write,
	 * activate,admin 4. "data-hex" (string, required) Data hex string or
	 * 4. publish-new-stream-item (object, required) A json object with stream item
	 * {"for" : stream-identifier (string,required) Stream identifier - one of the following: stream txid, stream reference, stream name.
	 * "key" : key (string,optional, default: "") Item key
	 * "data" : data-hex (string,optional, default: "") Data hex string }
	 * 5. native-amount (numeric, optional)  Native currency amount to send. eg 0.1. Default - 0.0
	 * 6. startblock (numeric, optional)  Block to apply permissions from (inclusive). Default - 0
	 * 7. endblock (numeric, optional)  Block to apply permissions to (exclusive). Default - 4294967295
	 * If -1 is specified default value is used.
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param fromAddress
	 * @param address
	 * @param permissions
	 * @param data-hex
	 * @return transactionId of grantwithdatafrom action
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public String grantWithDataFrom(String fromAddress, String address, int permissions, String dataHex) throws HdacException {
		String txId = "";
		
		Object objectTxId = executeGrantWithDataFrom(fromAddress, address, permissions, dataHex);
		if(verifyInstance(objectTxId, String.class)) {
			txId = (String) objectTxId;
		}
		return txId;
	}
	
	/**
	 * Grant permission to write in a stream to addresses
	 * 
	 * @param address
	 *            (Address) The addresses to grant permission to
	 * @param streamName
	 *            (String) The name of the stream
	 * @return transactionId of grant action
	 * @throws HdacException
	 */
	public String grantWrite(Address address, String streamName) throws HdacException {
		return grantWrite(address.getAddress(),streamName);
	}
	
	/**
	 * {@link #grantWrite(Address, byte)} with address in format String
	 * 
	 * @param address
	 *            (String) The addresses to grant permission to
	 * @param streamName
	 *            (String) The name of the stream
	 * @return transactionId of grant action
	 * @throws HdacException
	 */
	public String grantWrite(String address, String streamName) throws HdacException {
		String grantWrite = "";

		Object objectGrant = executeGrantWrite(address, streamName);
		if (verifyInstance(objectGrant, String.class)) {
			grantWrite = (String) objectGrant;
		}

		return grantWrite;
	}
	/**
	 * {@link #grantWrite(Address, byte)} with control over the from-address used to
	 * used to grant
	 * 
	 * @param addressFrom
	 *            (Address) The from-address used to publish
	 * @param address
	 *            (Address) The addresses to grant permission to
	 * @param streamName
	 *            (String) The name of the stream
	 * @return transactionId of grant action
	 * @throws HdacException
	 */
	public String grantWriteFrom(Address addressFrom, Address address, String streamName) throws HdacException {
		return grantWriteFrom(addressFrom.getAddress(), address.getAddress(), streamName);
	}
	
	/**
	 * {@link #grantWrite(Address, Address, byte)} with addresses in format String
	 * 
	 * @param addressFrom
	 *            (Address) The from-address used to publish
	 * @param address
	 *            (String) The addresses to grant permission to
	 * @param streamName
	 *            (String) The name of the stream
	 * @return transactionId of grant action
	 * @throws HdacException
	 */
	public String grantWriteFrom(String addressFrom, String address, String streamName) throws HdacException {
		String grantWrite = "";

		Object objectGrant = executeGrantWriteFrom(addressFrom, address, streamName);
		if (verifyInstance(objectGrant, String.class)) {
			grantWrite = (String) objectGrant;
		}

		return grantWrite;
	}
	
	/**
	 * listpermissions ("permission(s)" "address" verbose)
	 * 
	 * Returns list of addresses having one of the specified permissions
	 * 
	 * Arguments: 1. "permission(s)" (string, optional) Permission strings,
	 * comma delimited. Possible values:
	 * connect,send,receive,issue,mine,admin,activate. Default: all. 2.
	 * "address" (string, optional) The addresses to retrieve permissions for.
	 * "" or "*" for all addresses 3. verbose (boolean, optional, default=false)
	 * If true, returns list of pending grants
	 * 
	 * Gives permissions of an address
	 * 
	 * @param permissions
	 * @param address
	 *            to get permissions
	 * @param verbose
	 * @return a list of all permissions currently granted to addresses.
	 * @throws HdacException
	 */
	public List<Permission> listPermissions(byte permissions, Address address, boolean verbose)
			throws HdacException {
		return listPermissions(permissions, address.getAddress(), verbose);
	}

	/**
	 * {@link #listPermissions(byte, Address, boolean)} with permissions in int
	 * 
	 * @param permissions
	 *            int format
	 * @param address
	 *            Address
	 * @param verbose
	 *            boolean
	 * @return a list of all permissions currently granted to addresses.
	 * @throws HdacException
	 */
	public List<Permission> listPermissions(int permissions, Address address, boolean verbose)
			throws HdacException {
		return listPermissions(permissions, address.getAddress(), verbose);
	}

	/**
	 * {@link #listPermissions(int, Address, boolean)} with address in format
	 * String
	 * 
	 * @param permissions
	 *            int format
	 * @param address
	 *            String
	 * @param verbose
	 *            boolean
	 * @return a list of all permissions currently granted to addresses.
	 * @throws HdacException
	 */
	public List<Permission> listPermissions(int permissions, String address, boolean verbose)
			throws HdacException {
		List<Permission> permission = new ArrayList<Permission>();

		Object objectPermission = executeListPermissions(permissions, address, verbose);

		if (verifyInstance(objectPermission, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectPermission, Permission.class)) {
			permission = GrantFormatter.formatPermissions((ArrayList<Object>) objectPermission);
		}

		return permission;
	}

	/**
	 * {@link #listPermissions(byte, Address, boolean)} with address in format
	 * String
	 * 
	 * @param permissions
	 *            byte format
	 * @param address
	 *            String
	 * @param verbose
	 *            boolean
	 * @return a list of all permissions currently granted to addresses.
	 * @throws HdacException
	 */
	public List<Permission> listPermissions(byte permissions, String address, boolean verbose)
			throws HdacException {
		List<Permission> permission = new ArrayList<Permission>();

		Object objectPermission = executeListPermissions(permissions, address, verbose);

		if (verifyInstance(objectPermission, ArrayList.class)
				&& verifyInstanceofList((ArrayList<Object>) objectPermission, Permission.class)) {
			permission = GrantFormatter.formatPermissions((ArrayList<Object>) objectPermission);
		}

		return permission;
	}

	/**
	 * {@link #listPermissions(byte, Address, boolean)} without verbose
	 * 
	 * @param permissions
	 *            byte
	 * @param address
	 *            to get permissions
	 * @return a list of all permissions currently granted to addresses.
	 * @throws HdacException
	 */
	public List<Permission> listPermissions(byte permissions, Address address) throws HdacException {
		return listPermissions(permissions, address, false);
	}

	/**
	 * {@link #listPermissions(byte, Address)} with permissions in int
	 * 
	 * @param permissions
	 *            int
	 * @param address
	 *            Address
	 * @return a list of all permissions currently granted to addresses.
	 * @throws HdacException
	 */
	public List<Permission> listPermissions(int permissions, Address address) throws HdacException {
		return listPermissions(permissions, address, false);
	}

	/**
	 * {@link #listPermissions(int, Address)} with address in format String
	 * 
	 * @param permissions
	 *            int
	 * @param address
	 *            String
	 * @return a list of all permissions currently granted to addresses.
	 * @throws HdacException
	 */
	public List<Permission> listPermissions(int permissions, String address) throws HdacException {
		Address returnedAddress = new Address(address);

		return listPermissions(permissions, returnedAddress, false);
	}

	/**
	 * {@link #listPermissions(byte, Address)} with address in format String
	 * 
	 * @param permissions
	 *            byte
	 * @param address
	 *            String
	 * @return a list of all permissions currently granted to addresses.
	 * @throws HdacException
	 */
	public List<Permission> listPermissions(byte permissions, String address) throws HdacException {
		Address returnedAddress = new Address(address);

		return listPermissions(permissions, returnedAddress, false);
	}

	/**
	 * {@link #listPermissions(byte, Address)} without address
	 * 
	 * @param permissions
	 *            byte
	 * @return a list of all permissions currently granted to addresses.
	 * @throws HdacException
	 */
	public List<Permission> listPermissions(byte permissions) throws HdacException {
		return listPermissions(permissions, (String) null, false);
	}

	/**
	 * {@link #listPermissions(byte)} with permissions in int
	 * 
	 * @param permissions
	 *            int
	 * @return a list of all permissions currently granted to addresses.
	 * @throws HdacException
	 */
	public List<Permission> listPermissions(int permissions) throws HdacException {
		return listPermissions(permissions, (String) null, false);
	}

	/**
	 * {@link #listPermissions(byte)} without permissions
	 * 
	 * @return a list of all permissions currently granted to addresses.
	 * @throws HdacException
	 */
	public List<Permission> listPermissions() throws HdacException {
		return listPermissions(0, (String) null, false);
	}

	/**
	 * Revoke permissions to addresses
	 * 
	 * revoke "address(es)" "permission(s)" ( native-amount "comment"
	 * "comment-to" )
	 * 
	 * Revoke permission from a given address. The amount is a real
	 * 
	 * Arguments: 1. "address(es)" (string, required) The addresses(es) to
	 * revoke permissions from 2. "permission(s)" (string, required) Permission
	 * strings, comma delimited. Possible values:
	 * connect,send,receive,issue,mine,admin 3. "native-amount" (numeric,
	 * optional) native currency amount to send. eg 0.1. Default - 0 4.
	 * "comment" (string, optional) A comment used to store what the transaction
	 * is for. This is not part of the transaction, just kept in your wallet. 5.
	 * "comment-to" (string, optional) A comment to store the name of the person
	 * or organization to which you're sending the transaction. This is not part
	 * of the transaction, just kept in your wallet.
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * @param address
	 * @param permissions
	 *            This permissions will be grant to all addresses who are send
	 *            in parameter
	 * @return the txid of the transaction revoking the permissions
	 * @throws HdacException
	 */
	public String revoke(Address address, byte permissions) throws HdacException {
		Byte b = new Byte(permissions);
		return revoke(address.getAddress(), b.intValue());
	}

	/**
	 * {@link #revoke(Address, byte)} with permissions in format int
	 * 
	 * @param address
	 *            to revoke permissions
	 * @param permissions
	 *            int to revoke (possible values in this class, concatenation
	 *            with | )
	 * @return transactionId of revoke action
	 * @throws HdacException
	 */
	public String revoke(Address address, int permissions) throws HdacException {
		return revoke(address.getAddress(), permissions);
	}

	/**
	 * {@link #revoke(Address, byte)} with address in format string
	 * 
	 * @param address
	 *            String to revoke permissions
	 * @param permissions
	 *            byte to revoke (possible values in this class, concatenation
	 *            with | )
	 * @return transactionId of revoke action
	 * @throws HdacException
	 */
	public String revoke(String address, byte permissions) throws HdacException {
		Byte b = new Byte(permissions);
		return revoke(address, b.intValue());
	}

	/**
	 * {@link #revoke(Address, byte)} with address in format string and
	 * permissions in format int
	 * 
	 * @param address
	 *            String to revoke permissions
	 * @param permissions
	 *            int to revoke (possible values in this class, concatenation
	 *            with | )
	 * @return transactionId of revoke action
	 * @throws HdacException
	 */
	public String revoke(String address, int permissions) throws HdacException {
		String revoke = "";

		Object objectRevoke = executeRevoke(address, permissions);
		if (verifyInstance(objectRevoke, String.class)) {
			revoke = (String) objectRevoke;
		}

		return revoke;
	}

	/**
	 * Revoke permissions to addresses From an address
	 * 
	 * revokefrom "from-address" "to-address(es)" "permission(s)" (
	 * native-amount "comment" "comment-to" )
	 * 
	 * Revoke permissions using specific address.
	 * 
	 * Arguments: 1. "from-address" (string, required) Addresses used for
	 * revoke. 2. "to-address(es)" (string, required) The addresses(es) to
	 * revoke permissions from. Comma delimited 3. "permission(s)" (string,
	 * required) Permission strings, comma delimited. Possible values:
	 * connect,send,receive,issue,mine,admin 4. "native-amount" (numeric,
	 * optional) native currency amount to send. eg 0.1. Default - 0 5.
	 * "comment" (string, optional) A comment used to store what the transaction
	 * is for. This is not part of the transaction, just kept in your wallet. 6.
	 * "comment-to" (string, optional) A comment to store the name of the person
	 * or organization to which you're sending the transaction. This is not part
	 * of the transaction, just kept in your wallet.
	 * 
	 * Result: "transactionid" (string) The transaction id.
	 * 
	 * 
	 * @param addressFrom
	 *            address origin
	 * @param address
	 *            address destination
	 * @param permissions
	 *            This permissions will be grant to all addresses who are send
	 *            in parameter
	 * @return the txid of the transaction revoking the permissions
	 * @throws HdacException
	 */
	public String revokeFrom(Address addressFrom, Address address, byte permissions) throws HdacException {
		Byte b = new Byte(permissions);
		return revokeFrom(addressFrom.getAddress(), address.getAddress(), b.intValue());
	}

	/**
	 * {@link #revokeFrom(Address, byte)} with permissions in format int
	 * 
	 * @param addressFrom
	 *            address origin
	 * @param address
	 *            address destination
	 * @param permissions
	 *            int to revoke (possible values in this class, concatenation
	 *            with | )
	 * @return transactionId of revoke action
	 * @throws HdacException
	 */
	public String revokeFrom(Address addressFrom, Address address, int permissions) throws HdacException {
		return revokeFrom(addressFrom.getAddress(), address.getAddress(), permissions);
	}

	/**
	 * {@link #revokeFrom(Address, byte)} with address in format string
	 * 
	 * @param addressFrom
	 *            address origin
	 * @param address
	 *            address destination
	 * @param permissions
	 *            byte to revoke (possible values in this class, concatenation
	 *            with | )
	 * @return transactionId of revoke action
	 * @throws HdacException
	 */
	public String revokeFrom(String addressFrom, String address, byte permissions) throws HdacException {
		Byte b = new Byte(permissions);
		return revokeFrom(addressFrom, address, b.intValue());
	}

	/**
	 * {@link #revokeFrom(Address, byte)} with address in format string and
	 * permissions in format int
	 * 
	 * @param addressFrom
	 *            address origin
	 * @param address
	 *            address destination
	 * @param permissions
	 *            int to revoke (possible values in this class, concatenation
	 *            with | )
	 * @return transactionId of revoke action
	 * @throws HdacException
	 */
	public String revokeFrom(String addressFrom, String address, int permissions) throws HdacException {
		String revoke = "";

		Object objectRevoke = executeRevokeFrom(addressFrom, address, permissions);
		if (verifyInstance(objectRevoke, String.class)) {
			revoke = (String) objectRevoke;
		}

		return revoke;

	}

}
