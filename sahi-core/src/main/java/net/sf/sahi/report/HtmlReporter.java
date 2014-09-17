/**
 * User: dlewis
 * Date: Dec 6, 2006
 * Time: 3:14:31 PM
 */
package net.sf.sahi.report;

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


public class HtmlReporter extends SahiReporter {

  protected boolean createSuiteLogFolder = false;

  public HtmlReporter() {
    super(new HtmlFormatter());
  }

  public HtmlReporter(boolean createSuiteLogFolder) {
    this();
    this.createSuiteLogFolder = createSuiteLogFolder;
  }

  public HtmlReporter(final String logDir) {
    super(logDir, new HtmlFormatter());
    createSuiteLogFolder = true;
  }

  public boolean createSuiteLogFolder() {
    return createSuiteLogFolder;
  }
}
