package net.sf.sahi.client;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

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
 * This is a sample junit test used to demonstrate various
 * apis of Sahi.
 * You need sahi/lib/sahi.jar in your classpath
 */
public class DriverClientTest extends SahiTestCase {
  //	protected String baseURL = "http://gramam";
  protected String baseURL = "http://sahi.co.in";

  @Test
  @Ignore("Move to integration test phase")
  public void xtestZK() {
    browser.setSpeed(210);
    browser.navigateTo("http://www.zkoss.org/zkdemo/userguide/");
    browser.div("Hello World").click();
    browser.span("Pure Java").click();
    browser.div("Various Form").click();
//		BrowserCondition condition = new BrowserCondition(browser){@Override
//			public boolean test() {
//				return browser.textbox("z-intbox[1]").isVisible();
//			}};
//		browser.waitFor(condition, 5000);
    assertTrue(browser.textbox("z-intbox[1]").isVisible());
    browser.div("Comboboxes").click();
    browser.textbox("z-combobox-inp").setValue("aa");
    browser.italic("z-combobox-btn").click();
    browser.cell("Simple and Rich").click();
    browser.italic("z-combobox-btn[1]").click();
    browser.span("The coolest technology").click();
    browser.italic("z-combobox-btn[2]").click();
    browser.image("CogwheelEye-32x32.gif").click();
    assertTrue(browser.textbox("z-combobox-inp[2]").exists());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testOpen() {
    browser.navigateTo(baseURL + "/demo/formTest.htm");
    browser.textbox("t1").setValue("aaa");
    browser.link("Back").click();
    browser.link("Table Test").click();
    assertEquals("Cell with id", browser.cell("CellWithId").getText());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testFetch() throws Exception {
    browser.navigateTo(baseURL + "/demo/formTest.htm");
    assertEquals(baseURL + "/demo/formTest.htm", browser.fetch("window.location.href"));

  }

  @Test
  @Ignore("Move to integration test phase")
  public void testDragDrop() {
    browser.navigateTo("http://www.snook.ca/technical/mootoolsdragdrop/");
    browser.div("Drag me").dragAndDropOn(browser.div("Item 2"));
//		browser.waitFor(3000);
    browser.div("dropped").exists();
//		browser.waitFor(3000);		
    browser.div("Item 1").exists();
    browser.div("Item 3").exists();
    browser.div("Item 4").exists();
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testSahiDemoAccessors() {
    browser.navigateTo(baseURL + "/demo/formTest.htm");
    assertEquals("", browser.textbox("t1").value());
    assertNotNull(browser.textbox(1));
    assertNotNull(browser.textbox("$a_dollar"));
    browser.textbox("$a_dollar").setValue("adas");
    assertEquals("", browser.textbox(1).value());
    assertNotNull(browser.textarea("ta1"));
    assertEquals("", browser.textarea("ta1").value());
    assertNotNull(browser.textarea(1));
    assertEquals("", browser.textarea(1).value());
    assertNotNull(browser.checkbox("c1"));
    assertEquals("cv1", browser.checkbox("c1").value());
    assertNotNull(browser.checkbox(1));
    assertEquals("cv2", browser.checkbox(1).value());
    assertNotNull(browser.checkbox("c1[1]"));
    assertEquals("cv3", browser.checkbox("c1[1]").value());
    assertNotNull(browser.checkbox(3));
    assertEquals("", browser.checkbox(3).value());
    assertNotNull(browser.radio("r1"));
    assertEquals("rv1", browser.radio("r1").value());
    assertNotNull(browser.password("p1"));
    assertEquals("", browser.password("p1").value());
    assertNotNull(browser.password(1));
    assertEquals("", browser.password(1).value());
    assertNotNull(browser.select("s1"));
    assertEquals("o1", browser.select("s1").selectedText());
    assertNotNull(browser.select("s1Id[1]"));
    assertEquals("o1", browser.select("s1Id[1]").selectedText());
    assertNotNull(browser.select(2));
    assertEquals("o1", browser.select(2).selectedText());
    assertNotNull(browser.button("button value"));
    assertNotNull(browser.button("btnName[1]"));
    assertNotNull(browser.button("btnId[2]"));
    assertNotNull(browser.button(3));
    assertNotNull(browser.submit("Add"));
    assertNotNull(browser.submit("submitBtnName[1]"));
    assertNotNull(browser.submit("submitBtnId[2]").fetch());
    assertNotNull(browser.submit(3).fetch());
    assertNotNull(browser.image("imageAlt1").fetch());
    assertNotNull(browser.image("imageId1[1]").fetch());
    assertNotNull(browser.image(2).fetch());
    assertNull(browser.link("Back22").fetch());
    assertNotNull(browser.link("Back").fetch());
    assertNotNull(browser.accessor("document.getElementById('ta1')"));
    assertNotNull(browser.byId("ta1"));
    assertNotNull(browser.byClassName("button[1]", "INPUT"));
    browser.navigateTo("tableTest.htm");
    assertNotNull(browser.byXPath("//table[1]/tbody/tr[1]/td[@id='CellWithId']"));
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testSelect() {
    browser.navigateTo(baseURL + "/demo/formTest.htm");
    assertEquals("o1", browser.select("s1Id[1]").selectedText());
    browser.select("s1Id[1]").choose("o2");
    assertEquals("o2", browser.select("s1Id[1]").selectedText());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testMultiSelect() {
    browser.navigateTo(baseURL + "/demo/selectTest.htm");
    browser.select("s4Id").choose("o1");
    assertEquals("o1", browser.select("s4Id").selectedText());
    browser.select("s4Id").choose("o2", true);
    assertEquals("o1,o2", browser.select("s4Id").selectedText());
    browser.select("s4Id").choose(new String[]{"o2", "o3"});
    assertEquals("o2,o3", browser.select("s4Id").selectedText());
    browser.select("s4Id").choose(new String[]{"o1", "o2"}, true);
    assertEquals("o1,o2,o3", browser.select("s4Id").selectedText());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testSetFile() {
    browser.navigateTo(baseURL + "/demo/php/fileUpload.htm");
    browser.file("file").setFile("scripts/demo/uploadme.txt");
    browser.submit("Submit Single").click();
    assertTrue(browser.span("size").exists());
    assertTrue(browser.span("size").text().indexOf("0.3046875 Kb") != -1);
    assertTrue(browser.span("type").text().indexOf("Single") != -1);
    browser.link("Back to form").click();
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testMultiFileUpload() {
    browser.navigateTo(baseURL + "/demo/php/fileUpload.htm");
    browser.file("file[]").setFile("scripts/demo/uploadme.txt");
    browser.file("file[]").setFile("scripts/demo/uploadme2.txt");
    browser.submit("Submit Array").click();
    assertTrue(browser.span("type").text().indexOf("Array") != -1);
    assertTrue(browser.span("file").text().indexOf("uploadme.txt") != -1);
    assertTrue(browser.span("size").text().indexOf("0.3046875 Kb") != -1);

    assertTrue(browser.span("file[1]").text().indexOf("uploadme2.txt") != -1);
    assertTrue(browser.span("size[1]").text().indexOf("0.32421875 Kb") != -1);
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testSahiDemoClicks() {
    browser.navigateTo(baseURL + "/demo/formTest.htm");
    assertNotNull(browser.checkbox("c1"));
    browser.checkbox("c1").click();
    assertEquals("true", browser.checkbox("c1").fetch("checked"));
    browser.checkbox("c1").click();
    assertEquals("false", browser.checkbox("c1").fetch("checked"));

    assertNotNull(browser.radio("r1"));
    browser.radio("r1").click();
    assertEquals("true", browser.radio("r1").fetch("checked"));
    assertTrue(browser.radio("r1").checked());
    assertFalse(browser.radio("r1[1]").checked());
    browser.radio("r1[1]").click();
    assertEquals("false", browser.radio("r1").fetch("checked"));
    assertTrue(browser.radio("r1[1]").checked());
    assertFalse(browser.radio("r1").checked());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testLinks() {
    browser.navigateTo(baseURL + "/demo/index.htm");
    browser.link("Link Test").click();
    browser.link("linkByContent").click();
    browser.link("Back").click();
    browser.link("link with return true").click();
    assertTrue(browser.textarea("ta1").exists());
    assertEquals("", browser.textarea("ta1").value());
    browser.link("Back").click();
    browser.link("Link Test").click();
    browser.link("link with return false").click();
    assertTrue(browser.textbox("t1").exists());
    assertEquals("formTest link with return false", browser.textbox("t1").value());
    assertTrue(browser.link("linkByContent").exists());

    browser.link("link with returnValue=false").click();
    assertTrue(browser.textbox("t1").exists());
    assertEquals("formTest link with returnValue=false", browser.textbox("t1").value());
    browser.link("added handler using js").click();
    assertTrue(browser.textbox("t1").exists());
    assertEquals("myFn called", browser.textbox("t1").value());
    browser.textbox("t1").setValue("");
    browser.image("imgWithLink").click();
    browser.link("Link Test").click();
    browser.image("imgWithLinkNoClick").click();
    assertTrue(browser.textbox("t1").exists());
    assertEquals("myFn called", browser.textbox("t1").value());
    browser.link("Back").click();
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testPopupTitleNameMix() {
    browser.navigateTo(baseURL + "/demo/index.htm");
    browser.link("Window Open Test").click();
    browser.link("Window Open Test With Title").click();
    browser.link("Table Test").click();

    Browser popupPopWin = browser.popup("popWin");

    popupPopWin.link("Link Test").click();
    browser.link("Back").click();

    Browser popupWithTitle = browser.popup("With Title");

    popupWithTitle.link("Form Test").click();
    browser.link("Table Test").click();
    popupWithTitle.textbox("t1").setValue("d");
    browser.link("Back").click();
    popupWithTitle.textbox(1).setValue("e");
    browser.link("Table Test").click();
    popupWithTitle.textbox("name").setValue("f");
    assertNotNull(popupPopWin.link("linkByHtml").exists());

    assertNotNull(browser.cell("CellWithId"));
    assertEquals("Cell with id", browser.cell("CellWithId").text());
    popupWithTitle.link("Break Frames").click();

    Browser popupSahiTests = browser.popup("Sahi Tests");
    popupSahiTests.close();

    popupPopWin.link("Break Frames").click();
//		popupPopWin.link("Close Self").click();
    popupPopWin.close();
    browser.link("Back").click();
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testIn() {
    browser.navigateTo(baseURL + "/demo/tableTest.htm");
    assertEquals("111", browser.textarea("ta").near(browser.cell("a1")).getValue());
    assertEquals("222", browser.textarea("ta").near(browser.cell("a2")).getValue());
    browser.link("Go back").in(browser.cell("a1").parentNode()).click();
    assertTrue(browser.link("Link Test").exists());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testExists() {
    browser.navigateTo(baseURL + "/demo/index.htm");
    assertTrue(browser.link("Link Test").exists());
    assertFalse(browser.link("Link Test NonExistent").exists());
  }

  public void testWaitFor() {
    browser.navigateTo(baseURL + "/demo/waitCondition1.htm");
    BrowserCondition condition = new BrowserCondition(browser) {
      @Override
      public boolean test() {
        return "populated".equals(browser.textbox("t1").value());
      }
    };
    browser.waitFor(condition, 5000);
    assertEquals("populated", browser.textbox("t1").value());
  }

  public void alert1(String message) {
    browser.navigateTo(baseURL + "/demo/alertTest.htm");
    browser.textbox("t1").setValue("Message " + message);
    browser.button("Click For Alert").click();
    browser.navigateTo("/demo/alertTest.htm");
    browser.waitFor(1000);
    assertEquals("Message " + message, browser.lastAlert());
    browser.clearLastAlert();
    assertNull(browser.lastAlert());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testAlert() {
    alert1("One");
    alert1("Two");
    alert1("Three");
    browser.button("Click For Multiline Alert").click();
    assertEquals("You must correct the following Errors:\nYou must select a messaging price plan.\nYou must select an international messaging price plan.\nYou must enter a value for the Network Lookup Charge", browser.lastAlert());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void xtestGoogle() {
    browser.open();
    browser.navigateTo("http://www.google.com");
    browser.setValue(browser.textbox("q"), "sahi forums");
    browser.submit("Google Search").click();
    browser.waitFor(1000);
    browser.link("Forums - Sahi - Web Automation and Test Tool").click();
    browser.link("Login").click();
    assertTrue(browser.textbox("req_username").exists());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void xtestGoogleDD() throws Exception {
    browser.open();
    browser.navigateTo("http://www.google.com");
    browser.setValue(browser.textbox("q"), "sahi dow");
    browser.waitFor(3000);
    assertTrue(browser.cell("sahi download").isVisible());
    browser.textbox("q").keyDown(40, 0);
    browser.textbox("q").keyUp(40, 0);
    assertEquals("sahi download", browser.textbox("q").getValue());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void xtestForumsExists() throws Exception {
    browser.navigateTo("http://sahi.co.in/forums/");
    browser.link("Login").click();
    assertTrue(browser.textbox("req_username").exists());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testConfirm() {
    browser.navigateTo(baseURL + "/demo/confirmTest.htm");
    browser.expectConfirm("Some question?", true);
    browser.button("Click For Confirm").click();
    assertEquals("oked", browser.textbox("t1").value());
    browser.navigateTo("/demo/confirmTest.htm");
    browser.waitFor(1000);
    assertEquals("Some question?", browser.lastConfirm());
    browser.clearLastConfirm();
    assertNull(browser.lastConfirm());

    browser.expectConfirm("Some question?", false);
    browser.button("Click For Confirm").click();
    assertEquals("canceled", browser.textbox("t1").value());
    assertEquals("Some question?", browser.lastConfirm());
    browser.clearLastConfirm();
    assertNull(browser.lastConfirm());

    browser.expectConfirm("Some question?", true);
    browser.button("Click For Confirm").click();
    assertEquals("oked", browser.textbox("t1").value());
    assertEquals("Some question?", browser.lastConfirm());
    browser.clearLastConfirm();
    assertNull(browser.lastConfirm());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testPrompt() {
    browser.navigateTo(baseURL + "/demo/promptTest.htm");
    browser.expectPrompt("Some prompt?", "abc");
    browser.button("Click For Prompt").click();
    assertNotNull(browser.textbox("t1"));
    assertEquals("abc", browser.textbox("t1").value());
    browser.navigateTo("/demo/promptTest.htm");
    browser.waitFor(2000);
    assertEquals("Some prompt?", browser.lastPrompt());
    browser.clearLastPrompt();
    assertNull(browser.lastPrompt());
  }

  @SuppressWarnings("deprecation")
  @Test
  @Ignore("Move to integration test phase")
  public void testIsVisible() {
    browser.navigateTo(baseURL + "/demo/index.htm");
    browser.link("Visible Test").click();
    assertTrue(browser.spandiv("using display").isVisible());

    browser.button("Display none").click();
    assertFalse(browser.isVisible(browser.spandiv("using display")));
    browser.button("Display block").click();
    assertTrue(browser.isVisible(browser.spandiv("using display")));

    browser.button("Display none").click();
    assertFalse(browser.isVisible(browser.spandiv("using display")));
    browser.button("Display inline").click();
    assertTrue(browser.isVisible(browser.spandiv("using display")));

    assertTrue(browser.isVisible(browser.spandiv("using visibility")));
    browser.button("Visibility hidden").click();
    assertFalse(browser.isVisible(browser.spandiv("using visibility")));
    browser.button("Visibility visible").click();
    assertTrue(browser.isVisible(browser.spandiv("using visibility")));

    assertFalse(browser.isVisible(browser.byId("nestedBlockInNone")));
    assertFalse(browser.isVisible(browser.byId("absoluteNestedBlockInNone")));


  }

  @Test
  @Ignore("Move to integration test phase")
  public void testCheck() throws Exception {
    browser.navigateTo(baseURL + "/demo/");
    browser.link("Form Test").click();
    assertEquals("false", browser.checkbox("c1").fetch("checked"));
    assertFalse(browser.checkbox("c1").checked());
    browser.checkbox("c1").check();
    assertEquals("true", browser.checkbox("c1").fetch("checked"));
    assertTrue(browser.checkbox("c1").checked());
    browser.checkbox("c1").check();
    assertEquals("true", browser.checkbox("c1").fetch("checked"));
    browser.checkbox("c1").uncheck();
    assertEquals("false", browser.checkbox("c1").fetch("checked"));
    browser.checkbox("c1").uncheck();
    assertEquals("false", browser.checkbox("c1").fetch("checked"));
    browser.checkbox("c1").click();
    assertEquals("true", browser.checkbox("c1").fetch("checked"));
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testFocus() throws Exception {
    browser.navigateTo(baseURL + "/demo/focusTest.htm");
    browser.textbox("t2").focus();
    assertEquals("focused", browser.textbox("t1").value());
    browser.textbox("t2").removeFocus();
    assertEquals("not focused", browser.textbox("t1").value());
    browser.textbox("t2").focus();
    assertEquals("focused", browser.textbox("t1").value());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testTitle() throws Exception {
    browser.navigateTo(baseURL + "/demo/index.htm");
    assertEquals("Sahi Tests", browser.title());
    browser.link("Form Test").click();
    assertEquals("Form Test", browser.title());
    browser.link("Back").click();
    browser.link("Window Open Test With Title").click();
    assertEquals("With Title", browser.popup("With Title").title());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testArea() throws Exception {
    browser.navigateTo(baseURL + "/demo/map.htm");
    browser.navigateTo("map.htm");
    assertTrue(browser.area("Record").exists());
    assertTrue(browser.area("Playback").exists());
    assertTrue(browser.area("Info").exists());
    assertTrue(browser.area("Circular").exists());
    browser.area("Record").mouseOver();
    assertEquals("Record", browser.div("output").getText());
    browser.mouseOver(browser.button("Clear"));
    assertEquals("", browser.div("output").getText());
    browser.click(browser.area("Record"));
    assertTrue(browser.link("linkByContent").exists());
    //browser.navigateTo("map.htm");
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testRegExp() throws Exception {
    browser.navigateTo(baseURL + "/demo/regexp.htm");
    assertEquals("Inner", browser.div("Inner").getText());
    assertEquals("Inner", browser.div("/Inner/[1]").getText());
    assertTrue(!browser.div("/Inner/[3]").exists());

    assertTrue(browser.link("/Vi/[0]").fetch("href").indexOf("0.htm") != -1);
    assertTrue(browser.link("View[1]").fetch("href").indexOf("1.htm") != -1);
    assertTrue(browser.link("/Vi/[2]").fetch("href").indexOf("2.htm") != -1);
    assertTrue(browser.link("View[3]").fetch("href").indexOf("3.htm") != -1);
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testContainsText() throws Exception {
    browser.navigateTo(baseURL + "/demo/containTest.htm");
    assertTrue(browser.div("a").containsText("find me here"));
    assertTrue(browser.div("a").containsText("me"));
    assertTrue(browser.div("a").containsText("/find/"));
    assertTrue(browser.div("a").containsText("/f.*nd/"));
    assertTrue(browser.accessor("document.body").containsHTML("<DIV") || browser.accessor("document.body").containsHTML("<div"));
    assertTrue(browser.accessor("document.body").containsHTML("/find .* here/"));
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testStyle() throws Exception {
    browser.navigateTo(baseURL + "/demo/mouseover.htm");
    if (browser.isChrome() || browser.isFirefox()) {
      assertEquals("16px", browser.span("Hi Kamlesh").style("font-size"));
      assertEquals("rgb(0, 0, 238)", browser.span("Hi Kamlesh").style("color"));
    } else {
      assertEquals("12pt", browser.span("Hi Kamlesh").style("font-size"));
      assertEquals("#0066cc", browser.span("Hi Kamlesh").style("color"));
    }

  }

  @Test
  @Ignore("Move to integration test phase")
  public void testDoubleClick() throws Exception {
    browser.navigateTo(baseURL + "/demo/clicks.htm");
    browser.div("dbl click me").highlight();
    browser.div("dbl click me").doubleClick();
    assertEquals("[DOUBLE_CLICK]", browser.textarea("t2").value());
    browser.button("Clear").click();
  }


  @Test
  @Ignore("Move to integration test phase")
  public void testRightClick() throws Exception {
    browser.navigateTo(baseURL + "/demo/clicks.htm");
    browser.div("right click me").rightClick();
    assertEquals("[RIGHT_CLICK]", browser.textarea("t2").value());
    browser.button("Clear").click();
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testUnder() throws Exception {
    browser.navigateTo(baseURL + "/demo/tableTest.htm");
    assertEquals("x1-2", browser.cell(0).near(browser.cell("x1-0")).under(browser.tableHeader("header 3")).getText());
    assertEquals("x1-3", browser.cell(0).near(browser.cell("x1-0")).under(browser.tableHeader("header 4")).getText());
  }


  @Test
  @Ignore("Move to integration test phase")
  public void xtestSaveAs() throws Exception {
    browser.navigateTo(baseURL + "/demo/");
    String absolutePath = "testsaveas_x.zip";
    File file = new File(absolutePath);
    if (file.exists()) file.delete();
    assertTrue(!file.exists());

    browser.link("Save As Test").click();
    browser.link("testsaveas.zip").click();
    assertEquals("testsaveas.zip", browser.lastDownloadedFileName());
    String filePath = "testsaveas_x.zip";
    browser.saveDownloadedAs(filePath);
    assertTrue(file.exists());
    if (file.exists()) file.delete();
    browser.clearLastDownloadedFileName();
    assertNull(browser.lastDownloadedFileName());

  }

  @Test
  @Ignore("Move to integration test phase")
  public void testBrowserJS() throws Exception {
    browser.setBrowserJS("function giveMyNumber(){return '23';}");
    browser.navigateTo(baseURL + "/demo/");
    assertEquals("23", browser.fetch("giveMyNumber()"));
    browser.link("Link Test").click();
    assertEquals("23", browser.fetch("giveMyNumber()"));
    browser.link("Back").click();
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testFillMe() throws Exception {
    browser.navigateTo(baseURL + "/demo/");
    browser.link("Link Test").click();
    browser.link("Back").click();
    browser.link("Window Open Test").click();
    browser.popup("popWin").link("Alert Test").click();
    assertTrue(browser.popup("popWin").link("Alert Test").exists());
    browser.popup("popWin").link("Break Frames").click();
  }

  @Test
  @Ignore("Move to integration test phase")
  public void xtestDomain() {
    // works only on FF right now.
    browser.navigateTo(baseURL + "/demo/");
    browser.link("Different Domains External").click();
    browser.domain("www.tytosoftware.com").link("Link Test").click();
    browser.domain("www.bing.com").textbox("q").setValue("fdsfsd");
    browser.domain("www.tytosoftware.com").link("Back").click();
    browser.domain("www.bing.com").div("bgDiv").click();
    browser.navigateTo(baseURL + "/demo/");
  }

  @Test
  @Ignore("Move to integration test phase")
  public void xtestLateRooms() throws Exception {
    browser.navigateTo("http://www.laterooms.com/");
    browser.textbox("k").setValue("Manchester");
    browser.submit("do").click();
    browser.link("Premier Apartments Manchester").click();
    assertTrue(browser.heading1("/Premier Apartments Manchester/").exists());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testFileUploadWithChangedType() throws Exception {
    browser.navigateTo(baseURL + "/demo/php/fileUpload.htm");
    browser.file("file").setFile("scripts/demo/uploadme.txt");
    if ("true".equals(browser.fetch("_sahi._isIE()"))) {
      browser.execute("_sahi._file('file').outerHTML = _sahi._file('file').outerHTML.replace(/type=file/, 'type=text')");
    } else {
      browser.execute("_sahi._file('file').type = 'text'");
    }
    browser.textbox("file").setValue("scripts/demo/uploadme.txt");
    browser.submit("Submit Single").click();
    assertTrue(browser.span("size").exists());
    assertTrue(browser.span("size").text().indexOf("0.3046875 Kb") != -1);
    assertTrue(browser.span("type").text().indexOf("Single") != -1);
    browser.link("Back to form").click();
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testTextareaHandlesNewlines() throws Exception {
    browser.navigateTo(baseURL + "/demo/");
    browser.link("Form Test").click();
    browser.textarea("ta1").setValue("a\nb");
    assertEquals("a\nb", browser.textarea("ta1").getValue().replace("\r\n", "\n"));
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testCount() throws Exception {
    browser.navigateTo(baseURL + "/demo/count.htm");
    assertEquals(4, browser.link("group 0 link").countSimilar());
    assertEquals(0, browser.link("group non existent link").countSimilar());
    assertEquals(5, browser.link("/group 1/").countSimilar());
    assertEquals(2, browser.link("/group 1/").in(browser.div("div1")).countSimilar());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testCollect() throws Exception {
    browser.navigateTo(baseURL + "/demo/count.htm");
    List<ElementStub> els = browser.link("/group 1/").collectSimilar();
    assertEquals(5, els.size());
    assertEquals("group 1 link1", els.get(0).getText());
    assertEquals("group 1 link2", els.get(1).getText());

    browser.navigateTo(baseURL + "/demo/count.htm");
    List<ElementStub> els2 = browser.link("/group 1/").in(browser.div("div1")).collectSimilar();
    assertEquals(2, els2.size());
    assertEquals("group 1 link3", els2.get(0).getText());
    assertEquals("group 1 link4", els2.get(1).getText());

    browser.navigateTo(baseURL + "/demo/count.htm");
    List<ElementStub> els3 = browser.link("/.*/").in(browser.div("div1")).collectSimilar();
    assertEquals(2, els3.size());
    assertEquals("group 1 link3", els3.get(0).getText());
    assertEquals("group 1 link4", els3.get(1).getText());

  }

  @Test
  @Ignore("Move to integration test phase")
  public void testHTML5FormFields() throws Exception {
    browser.navigateTo(baseURL + "/demo/html5_form_fields.htm");
    browser.datebox("dob").setValue("2010-10-10");
    assertEquals("2010-10-10", browser.datebox("dob").getValue());
    browser.execute("_sahi._rangebox(\"points\").value = 3");
    assertEquals("3", browser.rangebox("points").getValue());
    browser.browser.rangebox("points").setValue("5");
    assertEquals("5", browser.rangebox("points").getValue());
    browser.weekbox("week2").setValue("2009-W10");
    assertEquals("2009-W10", browser.weekbox("week2").getValue());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testWikipedia() throws Exception {
    browser.navigateTo("http://www.wikipedia.org");
    browser.searchbox("search").setValue("sahi software");
    browser.submit("go").click();
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testStrictVisibility() throws Exception {
    browser.navigateTo(baseURL + "/demo/strict_visible.htm");
    assertTrue(browser.textbox("q").exists());
    assertTrue(browser.textbox("q[1]").exists());
    assertFalse(browser.textbox("q[1]").isVisible());
    assertTrue(browser.textbox("q[2]").exists());

    browser.setStrictVisibilityCheck(true);
    assertTrue(browser.textbox("q").exists());
    assertTrue(browser.textbox("q[1]").exists());
    assertFalse(browser.textbox("q[2]").exists());

    browser.setStrictVisibilityCheck(false);
    assertTrue(browser.textbox("q").exists());
    assertTrue(browser.textbox("q[1]").exists());
    assertFalse(browser.textbox("q[1]").isVisible());
    assertTrue(browser.textbox("q[2]").exists());
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testCuteEditor() throws Exception {
    browser.navigateTo("http://cutesoft.net/asp/EnableAll.asp");
    browser.rte("CE_Editor1_ID_Frame").rteWrite("<html><b>Hhahaia</b></html>");
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testActiveElement() throws Exception {
    browser.navigateTo(baseURL + "/demo/training/login.htm");
    browser.textbox("user").focus();
    assertEquals("user", browser.activeElement().fetch("name"));
    browser.password("password").focus();
    assertEquals("password", browser.activeElement().fetch("name"));
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testMouseDownMouseUp() throws Exception {
    browser.navigateTo(baseURL + "/demo/mouseEvents.htm");
    browser.button("Capture mouse down").mouseDown();
    assertEquals("[MOUSE_DOWN]", browser.getText(browser.textarea("t2")));
    browser.button("Capture mouse up").mouseUp();
    assertEquals("[MOUSE_DOWN][MOUSE_UP]", browser.getText(browser.textarea("t2")));
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testKeyPressAndGetAttribute() {
    browser.navigateTo(baseURL + "/demo/keypress.htm");
    browser.click(browser.radio("r3"));
    browser.keyPress(browser.textbox("t2"), "a", "CTRL");
    assertEquals("key pressed charCode=[97] keyCode=[0] CTRL", browser.getText(browser.textbox("t1")));
    browser.keyPress(browser.textbox("t2"), "c", "ALT");
    assertEquals("key pressed charCode=[99] keyCode=[0] ALT", browser.getText(browser.textbox("t1")));
    assertEquals((browser.getAttribute(browser.textbox("t1"), "size")), "70");
    browser.keyPress(browser.textbox("t2"), "c");
    assertEquals("key pressed charCode=[99] keyCode=[0] NONE", browser.getText(browser.textbox("t1")));
  }

  @Test
  @Ignore("Move to integration test phase")
  public void testBlur() {
    browser.navigateTo(baseURL + "/demo/focusTest.htm");
    browser.focus(browser.textbox("t2"));
    assertEquals("focused", browser.textbox("t1").getValue());
    browser.removeFocus(browser.textbox("t2"));
    assertEquals("not focused", browser.textbox("t1").getValue());
    browser.focus(browser.textbox("t2"));
    assertEquals("focused", browser.textbox("t1").getValue());
    browser.blur(browser.textbox("t2"));
    assertEquals("not focused", browser.textbox("t1").getValue());

  }

  @Test
  @Ignore("Move to integration test phase")
  public void testPrintCalledAndClearPrintCalled() {
    browser.navigateTo(baseURL + "/demo/print.htm");
    assertFalse(browser.printCalled());
    browser.click(browser.button("Print"));
    assertTrue(browser.printCalled());
    browser.clearPrintCalled();
    assertFalse(browser.printCalled());
    browser.click(browser.button("Print"));
    assertTrue(browser.printCalled());

  }

  @Test
  @Ignore("Move to integration test phase")
  public void testSetXSRStates() {
    browser.navigateTo(baseURL + "/demo/");
    assertEquals("true", browser.fetch("_sahi.waitWhenXHRReadyState1"));
    assertEquals("true", browser.fetch("_sahi.waitWhenXHRReadyState2"));
    assertEquals("true", browser.fetch("_sahi.waitWhenXHRReadyState3"));

    browser.setXHRReadyStatesToWaitFor("2,3");
    assertEquals("false", browser.fetch("_sahi.waitWhenXHRReadyState1"));
    assertEquals("true", browser.fetch("_sahi.waitWhenXHRReadyState2"));
    assertEquals("true", browser.fetch("_sahi.waitWhenXHRReadyState3"));

    browser.click(browser.link("Link Test"));
    assertEquals("false", browser.fetch("_sahi.waitWhenXHRReadyState1"));
    assertEquals("true", browser.fetch("_sahi.waitWhenXHRReadyState2"));
    assertEquals("true", browser.fetch("_sahi.waitWhenXHRReadyState3"));


    browser.setXHRReadyStatesToWaitFor("2");
    browser.click(browser.link("Back"));
    assertEquals("false", browser.fetch("_sahi.waitWhenXHRReadyState1"));
    assertEquals("true", browser.fetch("_sahi.waitWhenXHRReadyState2"));
    assertEquals("false", browser.fetch("_sahi.waitWhenXHRReadyState3"));

  }

  @Test
  @Ignore("Move to integration test phase")
  public void testSetFileWithThirdArguement() {
    browser.navigateTo(baseURL + "/demo/php/fileUpload.htm");
    browser.setFile(browser.file("file4"), "scripts/demo/uploadme.txt", "fileUpload.php");
    browser.click(browser.submit("Submit Single"));
    assertTrue(browser.span("size").exists());
    assertTrue(browser.span("size").getText().contains("0.3046875 Kb"));
    assertTrue(browser.span("type").getText().contains("Single"));

  }

  @Test
  @Ignore("Move to integration test phase")
  public void xtestKeyUpAndKeyDown() {
    browser.navigateTo(baseURL + "/demo/keypress.htm");
    browser.check(browser.radio("r2"));
//		browser.keyDown(browser.textbox("t2"), "a", "CTRL");
//		assertEquals("key downed charCode=[0] keyCode=[90] CTRL",browser.getText(browser.textbox("t1")));
    browser.keyDown(browser.textbox("t2"), "a");
    browser.waitFor(10000);
    assertEquals("key downed charCode=[0] keyCode=[65] NONE", browser.getText(browser.textbox("t1")));

  }

  @Override
  public void setBrowser() {
    setBrowser("firefox");
  }
}
