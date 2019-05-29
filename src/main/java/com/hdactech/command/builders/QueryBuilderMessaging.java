/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command.builders;

import com.hdactech.command.HdacException;
import com.hdactech.command.tools.HdacTestParameter;
/**
 * @author Hdac Technology
 * @version 1.0
 */
public class QueryBuilderMessaging extends QueryBuilderCommon {

	/**
	 * Verifies that message was approved by the owner of address by checking
	 * the base64-encoded digital signature provided.
	 * 
	 * @param address
	 *            (String, required) The owner address
	 * @param signature
	 *            (String, required) The base64-encoded digital signature to
	 *            check
	 * @param message
	 *            (String, required) The message
	 * @return (Boolean) True if the message is approved, else false
	 * @throws HdacException
	 */
	protected Object executeVerifyMessage(String address, String signature, String message) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("address", address);
		HdacTestParameter.isNotNullOrEmpty("signature", signature);
		HdacTestParameter.isNotNullOrEmpty("message", message);
		return execute(CommandEnum.VERIFYMESSAGE, address, signature, message);
	}

	/**
	 * Returns a base64-encoded digital signature which proves that message was
	 * approved by the owner of the address or any other private key given in
	 * addressORPrivateKey.
	 * 
	 * @param addressORPrivateKey
	 *            (String, required) The address or the private key (which must
	 *            belong to this wallet)
	 * @param message
	 *            (String, required) The message to approved
	 * @return (String) The base64-encoded digital signature
	 * @throws HdacException
	 */
	protected Object executeSignMessage(String addressORPrivKey, String message) throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("addressORPrivKey", addressORPrivKey);
		HdacTestParameter.isNotNullOrEmpty("message", message);
		return execute(CommandEnum.SIGNMESSAGE, addressORPrivKey, message);
	}
}