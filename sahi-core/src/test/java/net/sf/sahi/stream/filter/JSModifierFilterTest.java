package net.sf.sahi.stream.filter;

import net.sf.sahi.config.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class JSModifierFilterTest extends AbstractFilterTestCase {
  private static final long serialVersionUID = -723608175329141025L;

  @Before
  public void setup() {
    Configuration.init();
  }

  @Test
  public void testActiveXIsSubstituted() throws IOException {
    assertTrue(Configuration.modifyActiveX());
    String s1 = "new ActiveXObject";
    String output = getFiltered(new String[]{s1});
    assertEquals("new_ActiveXObject", output);

    s1 = "obj = new ActiveXObject('Microsoft.XMLHTTP');";
    output = getFiltered(new String[]{s1});
    assertEquals("obj = new_ActiveXObject('Microsoft.XMLHTTP');", output);
  }

  @Test
  public void testWithTabsAndSpaces() throws IOException {
    String s1;
    String output;
    s1 = "new\t    \t\t\tActiveXObject";
    output = getFiltered(new String[]{s1});
    assertEquals("new_ActiveXObject", output);
  }

  @Test
  public void testWithTab() throws IOException {
    String s1;
    String output;
    s1 = "new\tActiveXObject";
    output = getFiltered(new String[]{s1});
    assertEquals("new_ActiveXObject", output);
  }

  @Test
  public void testWithSpaces() throws IOException {
    String s1;
    String output;
    s1 = "new          ActiveXObject";
    output = getFiltered(new String[]{s1});
    assertEquals("new_ActiveXObject", output);
  }

  @Test
  public void testBrokenStreamParsing1() throws IOException {
    byte[] s1 = "xx a = new ActiveXObject(); yy".getBytes();
    JSModifierFilter modifierFilter = new JSModifierFilter("iso-8859-1");
    byte[] result = modifierFilter.modify(s1);
    byte[] remaining = modifierFilter.getRemaining();
    assertEquals("xx a = new_ActiveXObject(); yy", new String(result) + new String(remaining));
  }

  @Test
  public void testBrokenStreamParsing11() throws IOException {
    String s1 = "xx a = new    ActiveXObject(); yy";
    String output = getFiltered(new String[]{s1});
    assertEquals("xx a = new_ActiveXObject(); yy", output);
  }

  @Test
  public void testBrokenStreamParsing2() throws IOException {
    String s1 = "xx a = new ActiveX";
    String s2 = "Object(); yy";
    String output = getFiltered(new String[]{s1, s2});
    assertEquals("xx a = new_ActiveXObject(); yy", output);
  }

  @Test
  public void testBrokenStreamParsing3() throws IOException {
    String s1 = "xx a = ne";
    String s2 = "w ActiveX";
    String s3 = "Object(); yy";
    String output = getFiltered(new String[]{s1, s2, s3});
    assertEquals("xx a = new_ActiveXObject(); yy", output);
  }

  @Test
  public void testBrokenStreamParsing4() throws IOException {
    String s1 = "xx a = ne";
    String s2 = "w                      ActiveX";
    String s3 = "Object(); yy";
    String output = getFiltered(new String[]{s1, s2, s3});
    assertEquals("xx a = new_ActiveXObject(); yy", output);
  }

  @Test
  public void testBrokenStreamParsing5() throws IOException {
    String s1 = "xx a = ne";
    String s2 = "w                  ";
    String s3 = "    ActiveX";
    String s4 = "Object(); yy";
    String output = getFiltered(new String[]{s1, s2, s3, s4});
    assertEquals("xx a = new_ActiveXObject(); yy", output);
  }

  @Test
  public void testWithTabsAndSpacesBroken() throws IOException {
    String output;
    String s1 = "new\t   ";
    String s2 = " \t\t\tActiveXObject";
    output = getFiltered(new String[]{s1, s2});
    assertEquals("new_ActiveXObject", output);
  }

  @Test
  public void testWithTabsBroken() throws IOException {
    String output;
    String s1 = "new\t";
    String s2 = "\t\t\tActiveX";
    String s3 = "Object";
    output = getFiltered(new String[]{s1, s2, s3});
    assertEquals("new_ActiveXObject", output);
  }

  @Test
  public void testWithTabsAndSpacesBroken2() throws IOException {
    String output;
    String s1 = "new\t   ";
    String s2 = " \t ";
    String s3 = " \t\tActiveXObject";
    output = getFiltered(new String[]{s1, s2, s3});
    assertEquals("new_ActiveXObject", output);
  }

  private String getFiltered(String[] strings) throws IOException {
    JSModifierFilter modifierFilter = new JSModifierFilter("iso-8859-1");
    return getFiltered(strings, modifierFilter);
  }
}
