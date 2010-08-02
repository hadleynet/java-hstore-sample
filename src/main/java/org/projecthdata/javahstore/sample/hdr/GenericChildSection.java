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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.projecthdata.javahstore.hdr.DocumentMetadata;
import org.projecthdata.javahstore.hdr.Extension;
import org.projecthdata.javahstore.hdr.Section;
import org.projecthdata.javahstore.hdr.SectionDocument;

/**
 * Example implementation of Section interface
 * @author marc
 */
public class GenericChildSection implements Section {

  private String path, name;
  private Extension extension;
  private Map<String, Section> childSections;
  private Map<String, SectionDocument> childDocuments;
  AtomicInteger docCount;

  public GenericChildSection(Extension extension, String path, String name) {
    this.extension = extension;
    this.path = path;
    this.name = name;
    this.docCount = new AtomicInteger(1);
    this.childSections = new HashMap<String,Section>();
    this.childDocuments = new HashMap<String,SectionDocument>();
  }

  @Override
  public String getPath() {
    return path;
  }

  @Override
  public Extension getExtension() {
    return extension;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Collection<Section> getChildSections() {
    return childSections.values();
  }

  @Override
  public Section getChildSection(String path) {
    return childSections.get(path);
  }

  @Override
  public Collection<SectionDocument> getChildDocuments() {
    return childDocuments.values();
  }

  @Override
  public SectionDocument getChildDocument(String path) {
    return childDocuments.get(path);
  }

  @Override
  public Section createChildSection(Extension extension, String path, String name) {
    Section section = new GenericChildSection(extension, path, name);
    childSections.put(path, section);
    return section;
  }

  @Override
  public SectionDocument createDocument(String mediaType, InputStream content) throws IOException {
    String filename = "doc"+Integer.toString(docCount.getAndIncrement());
    SectionDocument doc = new GenericDocument(content, mediaType, filename);
    childDocuments.put(doc.getPath(), doc);
    return doc;
  }

  @Override
  public SectionDocument createDocument(String mediaType, InputStream content, DocumentMetadata metadata) throws IOException {
    String filename = "doc"+Integer.toString(docCount.getAndIncrement());
    SectionDocument doc = new GenericDocument(content, mediaType, filename);
    doc.setMetadata(metadata);
    childDocuments.put(doc.getPath(), doc);
    return doc;
  }

  @Override
  public void deleteDocument(SectionDocument document) {
    childDocuments.remove(document.getPath());
  }

  @Override
  public void deleteChildSection(Section childSection) {
    childSections.remove(childSection.getPath());
  }
}
