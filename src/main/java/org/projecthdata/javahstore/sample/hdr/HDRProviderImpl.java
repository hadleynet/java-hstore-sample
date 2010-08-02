/*
 *    Copyright 2009 The MITRE Corporation
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.projecthdata.javahstore.sample.hdr;

import java.util.HashMap;
import java.util.Map;
import org.projecthdata.javahstore.hdr.HDR;
import org.projecthdata.javahstore.hdr.HDRProvider;

/**
 * Implementation of the HDRProvider interface to provide access to a set of
 * dummy records. These are managed as an in-memory set.
 * 
 * @author marc
 */
public class HDRProviderImpl implements HDRProvider {

  private static Map<String, HDR> records;

  /**
   * Manages an in-memory cache of records. 
   * @param recordId
   * @return
   */
  private static synchronized HDR findHDR(String recordId) {
    if (records==null)
      records = new HashMap<String, HDR>();
    HDR hdr = records.get(recordId);
    if (hdr==null) {
      hdr = new HDRImpl(recordId);
      records.put(recordId, hdr);
    }
    return hdr;
  }

  @Override
  /**
   * Responds to any ID and creates a new record for that ID if required, a real
   * implementation would look up a record in some kind of store.
   *
   */
  public HDR getHDR(String recordId) {
    return findHDR(recordId);
  }

}
