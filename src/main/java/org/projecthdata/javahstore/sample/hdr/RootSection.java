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

import java.io.InputStream;
import javax.ws.rs.core.MediaType;
import org.projecthdata.javahstore.hdr.DocumentMetadata;
import org.projecthdata.javahstore.hdr.SectionDocument;

/**
 * Implementation of Section interface for root of record
 * @author marc
 */
public class RootSection extends GenericChildSection {

  public RootSection() {
    super(null, "", "Root");
  }

  // disallow creation of documents in root section

  @Override
  public SectionDocument createDocument(String mediaType, InputStream content) {
    throw new UnsupportedOperationException("Documents not allowed in root section.");
  }

  @Override
  public SectionDocument createDocument(String mediaType, InputStream content, DocumentMetadata metadata) {
    throw new UnsupportedOperationException("Documents not allowed in root section.");
  }
}
