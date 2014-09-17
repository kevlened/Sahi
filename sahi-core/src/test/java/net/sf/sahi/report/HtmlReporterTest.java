package net.sf.sahi.report;

import net.sf.sahi.config.Configuration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Sahi - Web Automation and Test Tool
 *
 * Copyright  2006  V Narayan Raman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * User: dlewis
 * Date: Dec 7, 2006
 * Time: 4:31:15 PM
 */
public class HtmlReporterTest {

  private SahiReporter reporter;

  @Before
  public void setup() {
    Configuration.init();
  }

  @Test
  public void testGetSuiteDirForDefaultLogDir() {
    reporter = new HtmlReporter();
    assertEquals(Configuration.getPlayBackLogsRoot(), reporter.getLogDir());
  }

  @Test
  public void testGetSuiteDirForCustomLogDir() {
    reporter = new HtmlReporter("testDir");
    assertEquals("testDir", reporter.getLogDir());
  }

  @Test
  public void testGetSuiteDirForCreatedSuiteLogDir() {
    reporter = new HtmlReporter(true);
    reporter.setSuiteName("testSuite");
    assertTrue(reporter.getLogDir().indexOf("testSuite") != -1);
  }
}
