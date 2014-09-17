package net.sf.sahi.response;

import java.io.IOException;

import net.sf.sahi.stream.filter.StreamFilter;

/**
 * Sahi - Web Automation and Test Tool
 * <p/>
 * Copyright  2006  V Narayan Raman
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


public class NoCacheFilter extends StreamFilter {

  public byte[] modify(byte[] data) throws IOException {
    return data;
  }

  public void modifyHeaders(HttpResponse response) {
    response.removeHeader("ETag");
    response.removeHeader("Last-Modified");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.removeHeader("Expires");
  }

}
