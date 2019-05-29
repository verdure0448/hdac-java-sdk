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

import com.hdactech.command.builders.QueryBuilderAddress;
import com.hdactech.object.Address;
import com.hdactech.object.BalanceAssetGeneral;
import com.hdactech.object.KeyPairs;
import com.hdactech.object.ListAddress;
import com.hdactech.object.ListAddressDetailed;
import com.hdactech.object.MultiBalance;
import com.hdactech.object.MultiSigAddress;
import com.hdactech.object.formatters.AddressFormatter;
import com.hdactech.object.formatters.BalanceFormatter;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class AddressCommand extends QueryBuilderAddress {

	public AddressCommand(String ip, String port, String login, String password, RuntimeParameters runtimeparameters) {
		initialize(ip, port, login, password, runtimeparameters);
	}

	/**
	 * Creates public/private key pairs. These key pairs are not stored in the
	 * wallet.
	 * 
	 * Arguments: 1. count (number, optional, default=1) Number of pairs to
	 * create.
	 * 
	 * Result: [ (json array of ) { "address" : "address", (string)
	 * Pay-to-pubkeyhash address "pubkey" : "pubkey", (string) Public key
	 * (hexadecimal) "privkey" : "privatekey", (string) Private key,
	 * base58-encoded as required for signrawtransaction } ]
	 * 
	 * @param numberOfPairs
	 * @return
	 * @throws HdacException
	 */
	public List<KeyPairs> createKeyPairs() throws HdacException {
		return createKeyPairs(1);
	}

	/**
	 * Creates public/private key pairs. These key pairs are not stored in the
	 * wallet.
	 * 
	 * Arguments: 1. count (number, optional, default=1) Number of pairs to
	 * create.
	 * 
	 * Result: [ (json array of ) { "address" : "address", (string)
	 * Pay-to-pubkeyhash address "pubkey" : "pubkey", (string) Public key
	 * (hexadecimal) "privkey" : "privatekey", (string) Private key,
	 * base58-encoded as required for signrawtransaction } ]
	 * 
	 * @param numberOfPairs
	 * @return
	 * @throws HdacException
	 */
	public List<KeyPairs> createKeyPairs(int numberOfPairs) throws HdacException {
		List<KeyPairs> listKeyPairs = new ArrayList<KeyPairs>();
		Object objectKeyPairs = executeCreateKeyPairs(numberOfPairs);

		if (verifyInstance(objectKeyPairs, ArrayList.class) && verifyInstanceofList((ArrayList<Object>) objectKeyPairs, KeyPairs.class)) {
			listKeyPairs = AddressFormatter.formatKeyPairsList((ArrayList<Object>) objectKeyPairs);
		}

		return listKeyPairs;
	}

	/**
	 * addmultisigaddress nrequired ["key",...] ( "account" )
	 * 
	 * Add a nrequired-to-sign multisignature address to the wallet. Each key is
	 * a address or hex-encoded public key. If 'account' is specified, assign
	 * address to that account.
	 * 
	 * Arguments: 1. nrequired (numeric, required) The number of required
	 * signatures out of the n keys or addresses. 2. "keysobject" (string,
	 * required) A json array of addresses or hex-encoded public keys [
	 * "address" (string) address or hex-encoded public key ..., ] 3. "account"
	 * (string, optional) An account to assign the addresses to.
	 * 
	 * Result: "address" (string) A address associated with the keys.
	 * 
	 * 
	 * @param numberOfSigRequired
	 * @param publicKeys
	 * @return the P2SH address
	 * @throws HdacException
	 */
	
	/*
	 * Object changed for ver 0.92.
	 */
	public final MultiSigAddress addMultiSigAddress(int numberOfSigRequired, String[] publicKeys) throws HdacException {
		MultiSigAddress address = new MultiSigAddress();

		Object objectAddress = executeAddMultiSigAddress(numberOfSigRequired, publicKeys);
		if (verifyInstance(objectAddress, String.class)) {
			String value = (String) objectAddress;
			address = validateMultisigAddress(value);
		}

		return address;
	}
	
	/**
	 * createmultisig nrequired ["key",...]
	 * 
	 * Creates a multi-signature address with n signature of m keys required. It
	 * returns a json object with the address and redeemScript.
	 * 
	 * Arguments: 1. nrequired (numeric, required) The number of required
	 * signatures out of the n keys or addresses. 2. "keys" (string, required) A
	 * json array of keys which are addresses or hex-encoded public keys [ "key"
	 * (string) address or hex-encoded public key ,... ]
	 * 
	 * Result: { "address":"multisigaddress", (string) The value of the new
	 * multisig address. "redeemScript":"script" (string) The string value of
	 * the hex-encoded redemption script. }
	 * 
	 * 
	 * @param numberOfSigRequired
	 * @param publicKeys
	 * @return The P2SH address and corresponding redeem script.
	 * @throws HdacException
	 */
	public Address createMultiSig(int numberOfSigRequired, String[] publicKeys) throws HdacException {
		Address address = new Address();

		Object objectAddress = executeCreateMultiSig(numberOfSigRequired, publicKeys);
		if (verifyInstance(objectAddress, String.class)) {
			address = validateAddress((String) objectAddress);
		}

		return address;
	}

	/**
	 * getaddresses ( verbose ) with verbose false
	 * 
	 * Returns the list of all addresses in the wallet.
	 * 
	 * Arguments: 1. "verbose" (boolean, optional, default=false) The account
	 * name.
	 * 
	 * Result: [ (json array of ) "address" (string) an address or
	 * "address-datails" (object) address details if verbose=true ,... ]
	 * 
	 * @return Addresses of the Wallet
	 * @throws HdacException
	 */
	public final List<String> getAddressesStringList() throws HdacException {
		List<String> addresses = new ArrayList<String>();

		Object objectAddresses = executeGetAddresses(false);
		if (verifyInstance(objectAddresses, ArrayList.class) && verifyInstanceofList((ArrayList<Object>) objectAddresses, String.class)) {
			addresses = (ArrayList<String>) objectAddresses;
		}

		return addresses;
	}

	/**
	 * getaddresses
	 * 
	 * Returns the list of all addresses in the wallet.
	 * 
	 * Result: [ (json array of ) "address" (string) an address ,... ]
	 * 
	 * @return Addresses of the Wallet
	 * @throws HdacException
	 */
	public final List<String> getAddresses() throws HdacException {
		return getAddressesStringList();
	}

	/**
	 * getaddresses ( verbose ) with verbose true
	 * 
	 * Returns the list of all addresses in the wallet.
	 * 
	 * Arguments: 1. "verbose" (boolean, optional, default=false) The account
	 * name.
	 * 
	 * Result: [ (json array of ) "address" (string) an address or
	 * "address-datails" (object) address details if verbose=true ,... ]
	 * 
	 * @return Addresses of the Wallet
	 * @throws HdacException
	 */
	public final List<Address> getAddressesList() throws HdacException {
		List<Address> addresses = new ArrayList<Address>();

		Object objectAddresses = executeGetAddresses(true);
		if (verifyInstance(objectAddresses, ArrayList.class) && verifyInstanceofList((ArrayList<Object>) objectAddresses, Address.class)) {
			addresses = AddressFormatter.formatAddressesList((ArrayList<Object>) objectAddresses);
		}
		return addresses;
	}
	
	public final List<Address> getAddressesList(boolean verbose) throws HdacException {
		List<Address> addresses = new ArrayList<Address>();

		Object objectAddresses = executeGetAddresses(verbose);
		if (verifyInstance(objectAddresses, ArrayList.class) && verifyInstanceofList((ArrayList<Object>) objectAddresses, Address.class)) {
			addresses = AddressFormatter.formatAddressesList((ArrayList<Object>) objectAddresses);
		}

		return addresses;
	}	

	/**
	 * Returns a list of balances of all addresses in this node's wallet
	 * 
	 * getmultibalances ("address(es)" assets minconf includeLocked
	 * includeWatchonly)
	 * 
	 * Returns asset balances for specified address
	 * 
	 * Arguments: 1. "address(es)" (string, optional) Address(es) to return
	 * balance for, comma delimited. Default - all or 1. "address(es)" (array,
	 * optional) A json array of addresses to return balance for 2. "assets"
	 * (array, optional) A json array of asset identifiers to return balance
	 * for, default - all [] 3. minconf (numeric, optional, default=1) Only
	 * include transactions confirmed at least this many times. 4.
	 * includeWatchonly (bool, optional, default=false) Include transactions to
	 * watchonly addresses (see 'importaddress') 5. includeLocked (bool,
	 * optional, default=false) Also take locked outputs into account Results
	 * are an Object of balance arrays with totals and details for each asset.
	 * 
	 * @return Balances
	 */
	public MultiBalance getMultiBalances(String[] addresses, String[] assets) throws HdacException {
		Object objectMultiBalance = executeGetMultiBalances(addresses, assets);

		return BalanceFormatter.formatMultiBalance(objectMultiBalance);
	}

	/**
	 * {@link #getMultiBalances(String[], String[]) with only 1 asset}
	 * 
	 * @param address
	 * @return
	 * @throws HdacException
	 */
	public MultiBalance getMultiBalances(String[] addresses, String asset) throws HdacException {
		String[] assets = { asset };

		return getMultiBalances(addresses, assets);
	}

	/**
	 * {@link #getMultiBalances(String[], String[]) with only 1 address, 1
	 * asset}
	 * 
	 * @param address
	 * @return
	 * @throws HdacException
	 */
	public MultiBalance getMultiBalances(String address, String asset) throws HdacException {
		String[] assets = { asset };
		String[] addresses = { address };

		return getMultiBalances(addresses, assets);
	}

	/**
	 * Returns a list of balances of all addresses in this node's wallet
	 * 
	 * getmultibalances ("address(es)" assets minconf includeLocked
	 * includeWatchonly)
	 * 
	 * Returns asset balances for specified address
	 * 
	 * Arguments: 1. "address(es)" (string, optional) Address(es) to return
	 * balance for, comma delimited. Default - all or 1. "address(es)" (array,
	 * optional) A json array of addresses to return balance for 2. "assets"
	 * (array, optional) A json array of asset identifiers to return balance
	 * for, default - all [] 3. minconf (numeric, optional, default=1) Only
	 * include transactions confirmed at least this many times. 4.
	 * includeWatchonly (bool, optional, default=false) Include transactions to
	 * watchonly addresses (see 'importaddress') 5. includeLocked (bool,
	 * optional, default=false) Also take locked outputs into account Results
	 * are an Object of balance arrays with totals and details for each asset.
	 * 
	 * @return Balances
	 */
	public MultiBalance getMultiBalances(String[] addresses) throws HdacException {
		Object objectMultiBalance = executeGetMultiBalances(addresses);

		return BalanceFormatter.formatMultiBalance(objectMultiBalance);
	}

	/**
	 * {@link #getMultiBalances(String[]) with only 1 address}
	 * 
	 * @param address
	 * @return
	 * @throws HdacException
	 */
	public MultiBalance getMultiBalances(String address) throws HdacException {
		Object objectMultiBalance = executeGetMultiBalances(address);

		return BalanceFormatter.formatMultiBalance(objectMultiBalance);
	}

	/**
	 * {@link #getMultiBalances(String) without address}
	 * 
	 * @return
	 * @throws HdacException
	 */
	public MultiBalance getMultiBalances() throws HdacException {
		Object objectMultiBalance = executeGetMultiBalances();

		return BalanceFormatter.formatMultiBalance(objectMultiBalance);
	}

	/**
	 * 
	 * getaddressbalances "address" ( minconf includeLocked )
	 * 
	 * Returns asset balances for specified address
	 * 
	 * Arguments: 1. "address" (string, required) Address to return balance for.
	 * 2. minconf (numeric, optional, default=1) Only include transactions
	 * confirmed at least this many times. 3. includeLocked (bool, optional,
	 * default=false) Also take locked outputs into account Results are an array
	 * of Objects with totals and details for each asset.
	 * 
	 * @param address
	 * @return Balance of the address
	 * @throws HdacException
	 */
	
	/*
	 * Parameter added for ver 0.92.
	 */
	public List<BalanceAssetGeneral> getAddressBalances(String address,int minconf,boolean includelocked) throws HdacException {
		List<BalanceAssetGeneral> balance = new ArrayList<BalanceAssetGeneral>();

		Object objectBalances = executeGetAddressBalances(address,minconf,includelocked);
		balance = BalanceFormatter.formatBalanceAssetsGeneral((ArrayList<Object>) objectBalances);

		return balance;
	}

	/**
	 * 
	 * getnewaddress ( "account" )
	 * 
	 * Returns a new address for receiving payments. If 'account' is specified
	 * (recommended), it is added to the address book so payments received with
	 * the address will be credited to 'account'.
	 * 
	 * Arguments: 1. "account" (string, optional) The account name for the
	 * address to be linked to. if not provided, the default account "" is used.
	 * It can also be set to the empty string "" to represent the default
	 * account. The account does not need to exist, it will be created if there
	 * is no account by the given name.
	 * 
	 * Result: "address" (string) The new address
	 * 
	 * @return Address created
	 * @throws HdacException
	 */
	public final String getNewAddress() throws HdacException {
		return getNewAddress(null);
	}
	public final String getNewAddress(String label) throws HdacException {
		String stringAddress = "";
		
		Object objectAddress = null;
		if (label == null || label.isEmpty()) {
			objectAddress = executeGetNewAddress();
		} else {
			objectAddress = executeGetNewAddress(label);
		}
		if (verifyInstance(objectAddress, String.class)) {
			stringAddress = (String) objectAddress;
		}

		return stringAddress;
	}	

	/**
	 * 
	 * getnewaddress ( "account" )
	 * 
	 * Returns a new address for receiving payments. If 'account' is specified
	 * (recommended), it is added to the address book so payments received with
	 * the address will be credited to 'account'.
	 * 
	 * Arguments: 1. "account" (string, optional) The account name for the
	 * address to be linked to. if not provided, the default account "" is used.
	 * It can also be set to the empty string "" to represent the default
	 * account. The account does not need to exist, it will be created if there
	 * is no account by the given name.
	 * 
	 * Result: "address" (string) The new address
	 * 
	 * @return Address created
	 * @throws HdacException
	 */
	public final Address getNewAddressFilled() throws HdacException {
		return getNewAddressFilled(null);
	}
	public final Address getNewAddressFilled(String label) throws HdacException {
		Address address = new Address();

		Object objectAddress = null;
		if (label == null || label.isEmpty()) {
			objectAddress = executeGetNewAddress();
		} else {
			objectAddress = executeGetNewAddress(label);
		}
		if (verifyInstance(objectAddress, String.class)) {
			String stringAddress = (String) objectAddress;

			address = validateAddress(stringAddress);
		}

		return address;
	}	

	/**
	 * Adds address to the wallet, without an associated private key, to create
	 * a watch-only address. This is an address whose activity and balance can
	 * be retrieved , but whose funds cannot be spent by this node
	 * 
	 * importaddress "address" ( "label" rescan )
	 * 
	 * Adds an address or script (in hex) that can be watched as if it were in
	 * your wallet but cannot be used to spend.
	 * 
	 * Arguments: 1. "address" (string, required) The address 2. "label"
	 * (string, optional, default="") An optional label 3. rescan (boolean,
	 * optional, default=true) Rescan the wallet for transactions
	 * 
	 * Note: This call can take minutes to complete if rescan is true.
	 * 
	 * @param address
	 * @param label
	 * @param rescan
	 *            If rescan is true, the entire blockchain is checked for
	 *            transactions relating to all addresses in the wallet,
	 *            including the added one
	 * @throws HdacException
	 */
	public void importAddress(String address, String label, boolean rescan) throws HdacException {
		/* String systemMessage = */executeImportAddress(address, label, rescan);
	}
	
	/**
	 * setaccount "address" "account"
	 * 
	 * Sets the account associated with the given address.
	 * 
	 * Arguments:
	 * 1. "address"                        (string, required) The address to be associated with an account.
	 * 2. "account"                        (string, required) The account to assign the address to.
	 * 
	 * @param address
	 * @param label
	 * @return
	 * @throws HdacException
	 * 
	 * !!!!! Accounts are not supported with scalable wallet - if you need accounts, run hdacd -walletdbversion=1 -rescan, but the wallet will perform worse
	 */
	public void setAccount(String address, String label) throws HdacException {
		executeSetAccount(address, label);
	}	

	/**
	 * Get information about an address
	 * 
	 * validateaddress "address"
	 * 
	 * Return information about the given address.
	 * 
	 * Arguments: 1. "address" (string, required) The address to validate
	 * 
	 * Result: { "isvalid" : true|false, (boolean) If the address is valid or
	 * not. If not, this is the only property returned. "address" : "address",
	 * (string) The address validated "ismine" : true|false, (boolean) If the
	 * address is yours or not "isscript" : true|false, (boolean) If the key is
	 * a script "pubkey" : "publickeyhex", (string) The hex value of the raw
	 * public key "iscompressed" : true|false, (boolean) If the address is
	 * compressed "account" : "account" (string) The account associated with the
	 * address, "" is the default account }
	 * 
	 * @param stringAddress
	 *            Address String in hdac
	 * @return Address with Information
	 * @throws HdacException
	 */
	public final Address validateAddress(String stringAddress) throws HdacException {

		Object objectAddressInfo = executeValidateAddress(stringAddress);
		return AddressFormatter.formatAddress(objectAddressInfo);
	}
	
	/*
	 * Method created for ver 0.92.
	 */
	public final MultiSigAddress validateMultisigAddress(String stringAddress) throws HdacException{
		Object objectAddressInfo = executeValidateAddress(stringAddress);
		return AddressFormatter.formatMultiSigAddress(objectAddressInfo);
		
	}
	
	/**
	 * 
	 * listaddresses ( "addresses" verbose count start )
	 * 
	 * Returns asset balances for specified address
	 * 
	 * Arguments: 1. "address(es)" (string, optional, default *) Address(es) to 
	 * return information for, comma delimited. Default - all  or 1. address(es) 
	 * (array, optional) A json array of addresses to return information for 
	 * 2. verbose (boolean, optional, default=false) If true return more information 
	 * about address. 3. count (number, optional, default=INT_MAX - all) The number of 
	 * addresses to display 4. start (number, optional, default=-count - last) Start from 
	 * specific address, 0 based, if negative - from the end
	 * 
	 * Result: An array of address Objects.
	 * 
	 * @param addresses
	 * @param verbose
	 * @param count
	 * @param start
	 * @return Addresses of the Wallet
	 * @throws HdacException
	 */
	
	/*
	 * Method created for ver 0.92.
	 */
	public final List<Object> listAddresses(String[] addresses, boolean verbose, long count, long start)
			throws HdacException {
		List<Object> listAddresses = new ArrayList<Object>();

		Object objectAddresses = executeListAddresses(addresses, verbose, count, start);

		if (verbose == true) {
			if (verifyInstance(objectAddresses, ArrayList.class)
					&& verifyInstanceofList((ArrayList<Object>) objectAddresses, ListAddressDetailed.class)) {
				listAddresses = AddressFormatter.formatAddressesListIsMineDetailed((ArrayList<Object>) objectAddresses);

			} 
		} else {
			if (verifyInstance(objectAddresses, ArrayList.class)
					&& verifyInstanceofList((ArrayList<Object>) objectAddresses, ListAddress.class)) {
				listAddresses = AddressFormatter.formatAddressesListIsMine((ArrayList<Object>) objectAddresses);
			}
		}

		return listAddresses;
	}
}
