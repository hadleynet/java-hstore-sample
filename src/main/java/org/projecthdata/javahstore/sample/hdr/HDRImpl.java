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

import org.projecthdata.javahstore.hdr.HDR;
import org.projecthdata.javahstore.hdr.RootDocument;

/**
 * A simple in-memory implementation of the HDR interface.
 * @author mhadley
 */
public class HDRImpl implements HDR {

  private String recordId;
  private RootDocument rootDocument;

  public HDRImpl(String recordId) {
    this.recordId = recordId;
    this.rootDocument = new RootDocumentImpl(recordId);
  }

  @Override
  public String getId() {
    return recordId;
  }

  @Override
  public RootDocument getRootDocument() {
    return rootDocument;
  }
}
