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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import org.projecthdata.javahstore.hdr.DocumentMetadata;
import org.projecthdata.javahstore.hdr.SectionDocument;

/**
 * Simple in-memory implementation of SectionDocument
 * @author marc
 */
public class GenericDocument implements SectionDocument {

  byte[] content;
  String mediaType;
  Date lastUpdated;
  String path;
  DocumentMetadata metadata;

  public GenericDocument(InputStream in, String mediaType, String path) throws IOException {
    this.mediaType = mediaType;
    this.path = path;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    byte buffer[] = new byte[128];
    int len = -1;
    while((len = in.read(buffer)) > -1) {
      out.write(buffer, 0, len);
    }
    content = out.toByteArray();
  }

  @Override
  public String getMediaType() {
    return mediaType;
  }

  @Override
  public void update(String mediaType, InputStream in) throws IOException {
    this.mediaType = mediaType.toString();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    byte buffer[] = new byte[128];
    int len = -1;
    while((len = in.read(buffer)) > -1) {
      out.write(buffer, 0, len);
    }
    content = out.toByteArray();
  }

  @Override
  public InputStream getContent() {
    return new ByteArrayInputStream(content);
  }

  @Override
  public String getPath() {
    return path;
  }

  @Override
  public Date getLastUpdated() {
    return lastUpdated;
  }

  @Override
  public DocumentMetadata getMetadata() {
    return metadata;
  }

  @Override
  public void setMetadata(DocumentMetadata metadata) {
    this.metadata = metadata;
  }

}
