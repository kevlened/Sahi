package net.sf.sahi.config;

import com.google.common.io.Files;
import net.sf.sahi.util.Utils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import static org.junit.Assert.*;

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
public class ConfigurationTest {
  private String basePath;
  private String userDataDirectory;

  @Before
  public void setUp() throws Exception {
    basePath = ".";
    userDataDirectory = Utils.concatPaths(basePath, "userdata");
  }

  @Test
  public void testSplit() {
    assertEquals("a", "a\nb\nc".split("\n")[0]);
    assertEquals("b", "a\nb\nc".split("\n")[1]);
    assertEquals("c", "a\nb\nc".split("\n")[2]);
  }

  @Test
  public void testGetRenderableContentTypes() {
    assertEquals("a\nb", "a\r\nb".replaceAll("\\\r", ""));
  }

  @Test
  public void testGetNonBlankLines() {
    assertEquals("a", Configuration.getNonBlankLines(" \r\n a \r\n")[0]);
  }

  @Test
  public void testInit() {
    Configuration.init(basePath);
    assertEquals(userDataDirectory + "/logs/playback", Configuration.getPlayBackLogsRoot().replace('\\', '/'));
    assertEquals(userDataDirectory + "/certs", Configuration.getCertsPath().replace('\\', '/'));
    assertEquals(userDataDirectory + "/temp/download", Configuration.tempDownloadDir().replace('\\', '/'));
    // FIXME somehow this is failing in the maven test phase.
    // assertEquals("sahi", Configuration.getControllerMode());
  }

  @Test
  public void testInitJava() {
    Configuration.initJava(basePath + "", userDataDirectory);
    assertEquals(userDataDirectory + "/logs/playback", Configuration.getPlayBackLogsRoot().replace('\\', '/'));
    assertEquals(userDataDirectory + "/certs", Configuration.getCertsPath().replace('\\', '/'));
    assertEquals(userDataDirectory + "/temp/download", Configuration.tempDownloadDir().replace('\\', '/'));
    assertEquals("java", Configuration.getControllerMode());
  }

  @Test
  public void readDefaultProperties() {
    Configuration config = new Configuration();
    config.initWithOptionalProperties(null);
    assertNotNull(config.getUserProperties());
    assertTrue(config.getUserProperties().entrySet().size() > 30);
  }

  @Test
  public void allPropertiesHaveNotNullDefaultValues() {
    Configuration config = new Configuration();
    config.initWithOptionalProperties(null);
    Properties properties = config.getUserProperties();
    for (Iterator iterator = properties.keySet().iterator(); iterator.hasNext(); ) {
      String key = (String) iterator.next();
      assertNotNull(properties.getProperty(key));
    }
  }

  @Test
  public void initReadsUserProperties() throws IOException {
   Configuration.init(".", Files.createTempDir().getPath());
    String scripts = Configuration.userProperties.getProperty("scripts.dir");
    String keytool = Configuration.userProperties.getProperty("keytool.path");
    assertEquals("forConfigTesting", scripts);
    assertEquals("keytool", keytool);
  }
}
