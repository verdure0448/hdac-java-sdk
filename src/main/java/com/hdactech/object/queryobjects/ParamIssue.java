/*
 * Copyright (C) 2017 Worldline, Inc.
 * MultiChainJavaAPI code distributed under the GPLv3 license, see COPYING file.
 * Copyright (c) 2018 Hdac Technology AG
 * HdacJavaAPI code distributed under the GPLv3 license, see COPYING file.
 *
 */
package com.hdactech.object.queryobjects;

/**
 * @author Hdac Technology
 * @version 1.0
 */
public class ParamIssue {
  private Long raw = null;
  /**
   * 
   */
  public ParamIssue() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  public boolean isFilled() {
    boolean filled = true;
    
    if (raw == null || raw < 0) {
      filled = false;
    }
    
    return filled;
  }
  
  /**
   * @param raw
   * @param asset
   */
  public ParamIssue(Long raw) {
    this.raw = raw;
  }
  /**
   * @return the raw
   */
  public Long getRaw() {
    return raw;
  }
  /**
   * @param raw the raw to set
   */
  public void setRaw(Long raw) {
    this.raw = raw;
  }
  
}
