/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.command.builders;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.hdactech.command.HdacException;
import com.hdactech.command.RuntimeParameters;
import com.hdactech.object.HdacRPCAnswer;
import com.hdactech.object.formatters.GsonFormatters;

/**
 * @author Hdac Technology
 * @version 1.0
 */
abstract class QueryBuilderCommon extends GsonFormatters {

	private CloseableHttpClient httpclient = null;
	private HttpPost httppost = null;
	private RuntimeParameters queryParameters = null;

	protected enum CommandEnum {
								ADDMULTISIGADDRESS,
								ADDNODE,
								APPENDRAWCHANGE,
								APPENDRAWEXCHANGE,
								APPENDRAWDATA,
								APPENDRAWMETADA,
								APPENDRAWTX,
								CLEARMEMPOOL,
								COMBINEUNPSENT,
								COMPLETERAWEXCHANGE,
								CREATE,
								CREATEFROM,
								CREATEKEYPAIRS,
								CREATEMULTISIG,
								CREATERAWEXCHANGE,
								CREATERAWSENDFROM,
								CREATERAWTRANSACTION,
								DECODERAWEXCHANGE,
								DECODERAWTRANSACTION,
								DISABLERAWTRANSACTION,
								DUMPPRIVKEY,
								GETADDRESSBALANCES,
								GETADDRESSES,
								GETADDRESSTRANSACTION,
								GETASSETBALANCES,
								GETASSETTRANSACTION,
								GETBESTBLOCKHASH,
								GETBLOCK,
								GETBLOCKCHAINPARAMS,
								GETBLOCKCOUNT,
								GETBLOCKHASH,
								GETINFO,
								GETMULTIBALANCES,
								GETNEWADDRESS,
								GETRAWCHANGEADDRESS,
								GETPEERINFO,
								GETRAWTRANSACTION,
								GETSTREAMITEM,
								GETTOTALBALANCES,
								GETTRANSACTION,
								GETTXOUT,
								GETTXOUTDATA,
								GETUNCONFIRMEDBALANCE,
								GETWALLETTRANSACTION,
								GRANT,
								GRANTFROM,
								GRANTWITHDATA,
								GRANTWITHDATAFROM,
								GRANTWITHMETADATA,
								GRANTWITHMETADATAFROM,
								HELP,
								IMPORTADDRESS,
								IMPORTPRIVKEY,
								ISSUE,
								ISSUEFROM,
								ISSUEMORE,
								ISSUEMOREFROM,
								LISTADDRESSES,
								LISTADDRESSTRANSACTIONS,
								LISTASSETS,
								LISTLOCKUNPSENT,
								LISTPERMISSIONS,
								LISTSTREAMITEMS,
								LISTSTREAMKEYITEMS,
								LISTSTREAMKEYS,
								LISTSTREAMPUBLISHERS,
								LISTSTREAMPUBLISHERITEMS,
								LISTSTREAMS,
								LISTUNSPENT,
								LISTWALLETTRANSACTIONS,
								LOCKUNSPENT,
                                LISTBLOCKS,
								PAUSE,
								PING,
								PREPARELOCKUNSPENT,
								PREPARELOCKUNSPENTFROM,
								PUBLISH,
								PUBLISHFROM,
								RESUME,
								REVOKE,
								REVOKEFROM,
								SEND,
								SENDASSETFROM,
								SENDASSETTOADDRESS,
								SENDFROM,
								SENDFROMADDRESS,
								SENDRAWTRANSACTION,
								SENDTOADDRESS,
								SENDWITHMETADATA,
								SENDWITHMETADATAFROM,
								SETACCOUNT,
								SETLASTBLOCK,
								SIGNMESSAGE,
								SIGNRAWTRANSACTION,
								STOP,
								SUBSCRIBE,
								UNSUBSCRIBE,
								VALIDATEADDRESS,
								VERIFYMESSAGE,
								SENDWITHDATA,
								SENDWITHDATAFROM
	}

	protected void initialize(String ip, String port, String login, String password, RuntimeParameters queryParameter) {
		httppost = new HttpPost("http://" + ip + ":" + port);

		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(login, password);
		provider.setCredentials(AuthScope.ANY, credentials);
		queryParameters = queryParameter;

		httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();

	}
	
	/**
	 * 
	 * @param command
	 * @param parameters
	 * 
	 * @return
	 * 
	 * 		example :
	 *         HdacQueryBuidlder.executeProcess(HdacCommand
	 *         .SENDTOADDRESS,"1EyXuq2JVrj4E3CpM9iNGNSqBpZ2iTPdwGKgvf
	 *         {\"rdcoin\":0.01}"
	 * @throws HdacException
	 */
	protected Object execute(CommandEnum command, Object... parameters) throws HdacException {

		if (httpclient != null && httppost != null) {
			try {
				// Generate Mapping of calling arguments
				Map<String, Object> entityValues = prepareMap(this.queryParameters, command, parameters);
				//System.out.println("ENTITY: " + entityValues);
				// Generate the entity and initialize request
				StringEntity rpcEntity = prepareRpcEntity(entityValues);
				httppost.setEntity(rpcEntity);
				// Execute the request and get the answer
				return executeRequest();

			} catch (IOException e) {
				e.printStackTrace();
				throw new HdacException(null, e.toString());
			}
		} else {
			throw new HdacException("Initialization Problem",
					"HdacCommand not initialized, please specify ip, port, user and pwd !");

		}
	}
	

	protected StringEntity prepareRpcEntity(Map<String, Object> entityValues) throws UnsupportedEncodingException {
		return new StringEntity(formatJson(entityValues));
	}

	private Object executeRequest() throws IOException, ClientProtocolException, HdacException {
		CloseableHttpResponse response = httpclient.execute(httppost);
//		int statusCode = response.getStatusLine().getStatusCode();
//		if (statusCode >= 400) {
//			EntityUtils.consume(response.getEntity());
//			throw new HdacException("code :" + statusCode, "message : " + response.getStatusLine().getReasonPhrase());
//		}
		HttpEntity entity = response.getEntity();

		String rpcAnswer = EntityUtils.toString(entity);
		response.close();

		final Gson gson = new GsonBuilder().create(); 
		//System.out.println("RPC RESPONSE: " + rpcAnswer);
		final HdacRPCAnswer hdacRPCAnswer = gson.fromJson(rpcAnswer, HdacRPCAnswer.class);

		if (hdacRPCAnswer != null && hdacRPCAnswer.getError() == null) {
			return hdacRPCAnswer.getResult();
		} else if (hdacRPCAnswer != null && hdacRPCAnswer.getError() != null) {
			throw new HdacException("code :" + hdacRPCAnswer.getError().get("code").toString(),
					"message : " + hdacRPCAnswer.getError().get("message").toString());
		} else {
			throw new HdacException(null, "General RPC Exceution Technical Error");
		}
	}

	private Map<String, Object> prepareMap(RuntimeParameters queryparameter, CommandEnum command, Object... parameters) {
		Map<String, Object> entityValues = new HashMap<String, Object>();
		entityValues.put("id", UUID.randomUUID().toString());
		if (queryparameter != null) {
			if (queryparameter.getDatadir() != null && !queryparameter.getDatadir().isEmpty()) {
				entityValues.put("datadir", queryparameter.getDatadir());
			}
			if (queryparameter.getRpcport() != null && !queryparameter.getRpcport().isEmpty()) {
				entityValues.put("rpcport", queryparameter.getRpcport());
			}
		}
		entityValues.put("method", command.toString().toLowerCase());
		List<Object> paramList = new ArrayList<Object>(Arrays.asList(parameters));
		entityValues.put("params", paramList);
		return entityValues;
	}
	
	@SuppressWarnings("rawtypes")
	protected boolean verifyInstance(Object obj, Class TheClass) {
		return TheClass.isInstance(obj);
	}

	@SuppressWarnings("rawtypes")
	protected boolean verifyInstanceofList(List<Object> obj, Class TheClass) {
		boolean verify = true;

		// Verify only the first Element of the list
		if (obj.size() > 0) {
			Object objElt = obj.get(0);
			if (!LinkedTreeMap.class.isInstance(objElt)) {
				verify = TheClass.isInstance(objElt);
			}
		}

		return verify;
	}

}
