/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object;

import java.util.HashMap;
import java.util.Map;

import com.hdactech.command.HdacException;
import com.hdactech.command.tools.HdacTestParameter;
import com.hdactech.object.queryobjects.ParamIssue;
import com.hdactech.object.queryobjects.ParamIssueMore;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class AddressBalanceIssue implements AddressBalance {
	String address = null;
	ParamIssue issue = null;
	ParamIssueMore issuemore = null;

	public void isFilled() throws HdacException {
		HdacTestParameter.isNotNullOrEmpty("address", address);
		if (issue == null && issuemore == null) {  
		  throw new HdacException("issue/issuemore", "issue / issuremore badly filled (none).");
		} else if (issue != null && issuemore != null
            && issue.isFilled() && issuemore.isFilled()) {
          throw new HdacException("issue/issuemore", "issue / issuremore badly filled (both).");
		}
		  
	}
	
	public Map<String, Object> getValue() throws HdacException {
		Map<String, Object> mapAssets = new HashMap<String, Object>();
		
		if (issue != null && issue.isFilled()) {
		  mapAssets.put("issue", issue);
		} else if (issuemore != null && issuemore.isFilled()) {
          mapAssets.put("issuemore", issuemore);
        }
		
		return mapAssets;
	}

  /**
   * 
   */
  public AddressBalanceIssue() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @param address
   * @param issue
   * @param issuemore
   */
  public AddressBalanceIssue(String address, ParamIssue issue, ParamIssueMore issuemore) {
    this.address = address;
    this.issue = issue;
    this.issuemore = issuemore;
  }

  /**
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * @return the issue
   */
  public ParamIssue getIssue() {
    return issue;
  }

  /**
   * @param issue the issue to set
   */
  public void setIssue(ParamIssue issue) {
    this.issue = issue;
  }

  /**
   * @return the issuemore
   */
  public ParamIssueMore getIssuemore() {
    return issuemore;
  }

  /**
   * @param issuemore the issuemore to set
   */
  public void setIssuemore(ParamIssueMore issuemore) {
    this.issuemore = issuemore;
  }

  
}
