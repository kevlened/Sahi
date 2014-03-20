package net.sf.sahi.util;

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


public class CaseInsensitiveString {
  private final String key;
  private String lower;

  public CaseInsensitiveString(String key) {
    this.key = key;
    this.lower = key.toLowerCase();
  }

  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((key == null) ? 0 : lower.hashCode());
    return result;
  }

  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CaseInsensitiveString other = (CaseInsensitiveString) obj;
    if (key == null) {
      if (other.key != null)
        return false;
    } else if (!lower.equals(other.lower))
      return false;
    return true;
  }

  public String toString() {
    return this.key;
  }

  public boolean isNull() {
    return this.key == null;
  }
}