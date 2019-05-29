package com.hdactech.object;

public class PrevTx {

	private String txid;
	private long vout;
	private String scriptPubKey;
	private String redeemScript;
	
	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public long getVout() {
		return vout;
	}
	public void setVout(long vout) {
		this.vout = vout;
	}
	public String getScriptPubKey() {
		return scriptPubKey;
	}
	public void setScriptPubKey(String scriptPubKey) {
		this.scriptPubKey = scriptPubKey;
	}
	public String getRedeemScript() {
		return redeemScript;
	}
	public void setRedeemScript(String redeemScript) {
		this.redeemScript = redeemScript;
	}

	
	
}
