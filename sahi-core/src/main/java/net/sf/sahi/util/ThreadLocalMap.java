package net.sf.sahi.util;

import java.util.HashMap;
import java.util.Map;

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


public class ThreadLocalMap {
  private static ThreadLocal<Map<String, Object>> tLocal = new ThreadLocal<Map<String, Object>>();

  public static void put(String key, Object value) {
    Map<String, Object> map = (Map<String, Object>) tLocal.get();
    if (map == null) {
      map = new HashMap<String, Object>();
    }
    map.put(key, value);
    tLocal.set(map);
  }

  public static void clearAll() {
    Map<String, Object> map = (Map<String, Object>) tLocal.get();
    if (map != null) {
      map.clear();
    }
    tLocal.set(new HashMap<String, Object>());
  }

  public static Object get(String key) {
    Map<String, Object> map = (Map<String, Object>) tLocal.get();
    if (map == null)
      return null;
    return map.get(key);
  }
}
