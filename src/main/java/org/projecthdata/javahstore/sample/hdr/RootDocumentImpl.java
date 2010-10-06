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

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.projecthdata.javahstore.hdr.DocumentMetadata;
import org.projecthdata.javahstore.hdr.Extension;
import org.projecthdata.javahstore.hdr.RootDocument;
import org.projecthdata.javahstore.hdr.Section;
import org.projecthdata.javahstore.hdr.SectionDocument;

/**
 * Sample implementation of RootDocument with a single section for allergies
 * @author marc
 */
public class RootDocumentImpl implements RootDocument {

  private String id;
  Map<String, Extension> extensions;
  Section rootSection;
  Date created;
  Date updated;

  private static final String TEST_AUTHOR = "Fred Bloggs";
  private static final String TEST_METADATA = "<DocumentMetaData xmlns='http://projecthdata.org/hdata/schemas/2009/11/metadata'>"
          + "<PedigreeInfo>"
          + "<Author>"
          + TEST_AUTHOR
          + "</Author>"
          + "</PedigreeInfo>"
          + "<DocumentId>xyzzy</DocumentId>"
          + "<Title>The Title</Title>"
          + "<RecordDate>"
          + "<CreatedDateTime>2006-05-04T18:13:51.0Z</CreatedDateTime>"
          + "</RecordDate>"
          + "</DocumentMetaData>";

  /**
   * Build a fixed record with a single allergies extension, corresponding
   * section and with one document in the allergies section
   * @param recordId
   */
  RootDocumentImpl(String recordId) {
    this.id = recordId;
    this.created = this.updated = new Date();
    this.extensions = new HashMap<String, Extension>();
    Extension allergiesExtension = new AllergiesExtension();
    this.extensions.put(allergiesExtension.getId(), allergiesExtension);
    this.rootSection = new RootSection();
    Section allergiesSection = rootSection.createChildSection(allergiesExtension, "allergies", "Allergy Section");
    try {
      SectionDocument secDoc = allergiesSection.createDocument("text/plain", new ByteArrayInputStream("Hello World !".getBytes()));
      DocumentMetadata docMeta = new DocumentMetadata(TEST_METADATA);
      secDoc.setMetadata(docMeta);
    } catch (Exception ex) {
      Logger.getLogger(RootDocumentImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public Date getCreated() {
    return created;
  }

  @Override
  public Date getLastModified() {
    return updated;
  }

  @Override
  public Collection<Extension> getExtensions() {
    return extensions.values();
  }

  @Override
  public Section getRootSection() {
    return rootSection;
  }

  @Override
  public Extension getExtension(String id) {
    Extension e = extensions.get(id);
    if (e==null)
      throw new IllegalArgumentException("Unknown extension ID: "+id);
    return e;
  }

}
