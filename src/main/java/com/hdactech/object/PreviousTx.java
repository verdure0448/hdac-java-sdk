package com.hdactech.object;

/**
 * @author Justin
 *
 */
public class PreviousTx {

	private String txid;
	private long vout;
	private String scriptPubKey;
	private String redeemScript;
	private boolean cache;
	
	public PreviousTx(String txid, long vout, String scriptPubKey, String redeemScript, boolean cache) {
		super();
		this.txid = txid;
		this.vout = vout;
		this.scriptPubKey = scriptPubKey;
		this.redeemScript = redeemScript;
		this.cache = cache;
	}
	
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
	/**
	 * @return the cache
	 */
	public boolean isCache() {
		return cache;
	}
	/**
	 * @param cache the cache to set
	 */
	public void setCache(boolean cache) {
		this.cache = cache;
	}
}
