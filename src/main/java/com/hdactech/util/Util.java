package com.hdactech.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.commons.lang3.ArrayUtils;
import org.bitcoinj.core.Base58;
import org.bouncycastle.crypto.digests.RIPEMD128Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;

public class Util {

	/**
	 * 
	 * @param filePath
	 * @return docHash
	 * @throws Exception
	 */
	public static String getFileHash(String filePath) throws Exception {
		InputStream is = null;
		byte[] docHash = null;
		StringBuffer tempBuf = new StringBuffer();
		StringBuffer resultBuf = new StringBuffer();

		try {
			is = new FileInputStream(filePath);
			while (is.available() > 0) {
				int value = (int) is.read();
				tempBuf.append(String.format("%02X ", value));

				if (tempBuf.length() > 40890) {
					resultBuf.append(tempBuf);
					tempBuf = new StringBuffer();
				}
			}

			if (resultBuf.length() == 0) {
				resultBuf = tempBuf;
			}
			docHash = sha256(tempBuf.toString().getBytes("UTF-8"));
		} catch (Exception e) {
			throw e;
		} finally {
			if (is != null)
				is.close();
		}

		return bytesToHex(docHash);
	}

	public static String hexToString(String strHex) throws UnsupportedEncodingException {
		byte[] b = new byte[strHex.length() / 2];
		for (int i = 0; i < b.length; i++) {
			int index = i * 2;
			int v = Integer.parseInt(strHex.substring(index, index + 2), 16);
			b[i] = (byte) v;
		}

		return new String(b, "UTF-8");
	}

	public static String bytesToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}

		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;

		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}

		return sb.toString();
	}

	/**
	 * pubkey
	 * 
	 * @param buf
	 * @param params
	 * @return
	 */
	public static String convPubkeyToHdacAddress(byte[] buf, String addressHead, String addressChecksum) {

	      byte[] hash = ripemd160(sha256(buf));

	      byte[] bPriHeader = hexToByte(addressHead);
	      byte[] payload = new byte[hash.length + bPriHeader.length];
	      int divHeader = hash.length / bPriHeader.length;
	      int headerIndex = 0;

	      for (int i = 0; i < hash.length; i++) {
	         if (i % divHeader == 0 && bPriHeader.length > headerIndex) {
	            payload[i + headerIndex] = bPriHeader[headerIndex];
	            headerIndex++;
	         }
	         payload[i + headerIndex] = hash[i];
	      }

	      ByteBuffer payloadHash = ByteBuffer.wrap(sha256(sha256(payload)));

	      payloadHash.flip(); // position 0
	      payloadHash.limit(4);

	      byte[] checksum = new byte[4];
	      for (int cs_id = 0; cs_id < checksum.length; cs_id++) {
	         checksum[cs_id] = payloadHash.get(cs_id);
	      }
	      ArrayUtils.reverse(checksum);

	      byte[] hdacChecksum = hexToByte(addressChecksum);
	      ArrayUtils.reverse(hdacChecksum);
	      int length = hdacChecksum.length;// Math.max(checksum.length, hdacChecksum.length);
	      byte[] checksumBuf = new byte[length];
	      for (int i = 0; i < length; ++i) {
	         byte xor = (byte) (0xff & ((int) (i < checksum.length ? checksum[i] : 0)
	               ^ (int) (i < hdacChecksum.length ? hdacChecksum[i] : 0)));
	         checksumBuf[i] = xor;
	      }

	      byte[] hdacAddrByte = new byte[payload.length + length];
	      int hdacAddrByte_index = 0;
	      for (; hdacAddrByte_index < payload.length; hdacAddrByte_index++) {
	         hdacAddrByte[hdacAddrByte_index] = payload[hdacAddrByte_index];
	      }

	      ArrayUtils.reverse(checksumBuf);
	      for (int i = 0; i < checksumBuf.length; i++) {
	         hdacAddrByte[hdacAddrByte_index + i] = checksumBuf[i];
	      }
	      
	      return Base58.encode(hdacAddrByte);
	   }

	public static byte[] hexToByte(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];

		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}

		return data;
	}

	private static byte[] sha256(byte[] buf) {
		SHA256Digest digest = new SHA256Digest();
		byte[] byteData = new byte[digest.getDigestSize()];
		digest.update(buf, 0, buf.length);
		digest.doFinal(byteData, 0);

		return byteData;
	}

	public static byte[] ripemd128(byte[] buf) throws UnsupportedEncodingException {
		RIPEMD128Digest digest = new RIPEMD128Digest();
		digest.update(buf, 0, buf.length);
		byte[] bytebuf = new byte[digest.getDigestSize()];
		digest.doFinal(bytebuf, 0);

		return bytebuf;
	}
	

	public static byte[] ripemd160(byte[] buf) {
		byte byteData[] = null;
		RIPEMD160Digest digest = new RIPEMD160Digest();
		digest.update(buf, 0, buf.length);
		byteData = new byte[digest.getDigestSize()];
		digest.doFinal(byteData, 0);

		return byteData;
	}

	/** 
	    * privatekey(32byte) -> Hdac WIF
	    * 
	    * @param buf
	    * @return
	    */
	   public static String convPrikeyToHdacWIF(byte[] buf, String privateKeyHeader, String addressChecksum) {
	      byte[] bPriHeader = hexToByte(privateKeyHeader);
	      byte[] payload = new byte[buf.length + bPriHeader.length];
	      int divHeader = buf.length / bPriHeader.length;
	      int headerIndex = 0;

	      for (int i = 0; i < buf.length; i++) {
	         if (i % divHeader == 0 && bPriHeader.length > headerIndex) {
	            payload[i + headerIndex] = bPriHeader[headerIndex];
	            headerIndex++;
	         }
	         payload[i + headerIndex] = buf[i];
	      }
	      
	      // add compress option 
	      payload = ArrayUtils.add(payload, (byte)0x01);
	      
	      ByteBuffer payloadHash = ByteBuffer.wrap(sha256(sha256(payload)));

	      payloadHash.flip(); // position 0
	      payloadHash.limit(4);

	      byte[] checksum = new byte[4];
	      for (int cs_id = 0; cs_id < checksum.length; cs_id++) {
	         checksum[cs_id] = payloadHash.get(cs_id);
	      }

	      ArrayUtils.reverse(checksum);

	      byte[] hdacChecksum = hexToByte(addressChecksum);
	      ArrayUtils.reverse(hdacChecksum);
	      int length = hdacChecksum.length;// Math.max(checksum.length, hdacChecksum.length);
	      byte[] checksumBuf = new byte[length];
	      for (int i = 0; i < length; ++i) {
	         byte xor = (byte) (0xff & ((int) (i < checksum.length ? checksum[i] : 0)
	               ^ (int) (i < hdacChecksum.length ? hdacChecksum[i] : 0)));
	         checksumBuf[i] = xor;
	      }

	      byte[] addr_Byte = new byte[payload.length + length];
	      int addrByte_index = 0;
	      for (; addrByte_index < payload.length; addrByte_index++) {
	         addr_Byte[addrByte_index] = payload[addrByte_index];
	      }

	      ArrayUtils.reverse(checksumBuf);
	      for (int i = 0; i < length; i++) {
	         addr_Byte[addrByte_index + i] = checksumBuf[i];
	      }

	      return Base58.encode(addr_Byte);
	   }

	   /**
	    * privatekey(32byte) ->  Bitcoin WIF
	    * @param buf  256bit private key 
	    * @param params HdacNetworkParams
	    * @return Bitcoin WIF
	    */
	   public static String encodeBCBase58WIF(byte[] buf, String privateKeyHeader) {

	      byte[] bPriHeader = hexToByte(privateKeyHeader);
	      byte[] payload = new byte[buf.length + bPriHeader.length];
	      int divHeader = buf.length / bPriHeader.length;
	      int headerIndex = 0;
	      
	      for (int i = 0; i < buf.length; i++) {
	         if (i % divHeader == 0) {
	            payload[i + headerIndex] = bPriHeader[headerIndex];
	            headerIndex++;
	         }
	         payload[i + headerIndex] = buf[i];
	      }

	      ByteBuffer payloadHash = ByteBuffer.wrap(sha256(sha256(payload)));

	      payloadHash.flip(); // position 0
	      payloadHash.limit(4);

	      byte[] checksum = new byte[4];
	      for (int cs_id = 0; cs_id < checksum.length; cs_id++) {
	         checksum[cs_id] = payloadHash.get(cs_id);
	      }

	      int length = checksum.length;

	      byte[] addr_Byte = new byte[payload.length + length];
	      int addrByte_index = 0;
	      for (; addrByte_index < payload.length; addrByte_index++) {
	         addr_Byte[addrByte_index] = payload[addrByte_index];
	      }

	      for (int i = 0; i < length; i++) {
	         addr_Byte[addrByte_index + i] = checksum[i];
	      }
	      
	      return Base58.encode(addr_Byte);
	   }
}
