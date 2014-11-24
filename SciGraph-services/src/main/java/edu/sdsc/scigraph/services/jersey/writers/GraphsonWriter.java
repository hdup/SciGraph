/**
 * Copyright (C) 2014 The SciGraph authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.sdsc.scigraph.services.jersey.writers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.util.io.graphson.GraphSONMode;
import com.tinkerpop.blueprints.util.io.graphson.GraphSONWriter;

import edu.sdsc.scigraph.services.jersey.CustomMediaTypes;

@Produces(CustomMediaTypes.APPLICATION_GRAPHSON)
@Provider
public class GraphsonWriter implements MessageBodyWriter<Graph> {

  @Override
  public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return Graph.class.isAssignableFrom(type);
  }

  @Override
  public long getSize(Graph data, Class<?> type, Type genericType, Annotation annotations[], MediaType mediaType) {
    return -1;
  }

  @Override
  public void writeTo(Graph data, Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType, MultivaluedMap<String, Object> headers, OutputStream out) throws IOException {
    GraphSONWriter.outputGraph(data, out, GraphSONMode.COMPACT);
    out.flush();
  }

}
