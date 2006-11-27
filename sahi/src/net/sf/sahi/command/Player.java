/**
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

package net.sf.sahi.command;

import java.io.IOException;
import java.util.Properties;

import net.sf.sahi.config.Configuration;
import net.sf.sahi.playback.FileScript;
import net.sf.sahi.playback.SahiScript;
import net.sf.sahi.playback.SahiScriptHTMLAdapter;
import net.sf.sahi.playback.ScriptFactory;
import net.sf.sahi.playback.log.LogFileConsolidator;
import net.sf.sahi.report.Formatter;
import net.sf.sahi.report.HtmlFormatter;
import net.sf.sahi.report.JUnitFormatter;
import net.sf.sahi.report.Report;
import net.sf.sahi.request.HttpRequest;
import net.sf.sahi.response.HttpFileResponse;
import net.sf.sahi.response.HttpResponse;
import net.sf.sahi.response.NoCacheHttpResponse;
import net.sf.sahi.response.SimpleHttpResponse;
import net.sf.sahi.session.Session;
import net.sf.sahi.test.SahiTestSuite;

public class Player {
	public void stepWisePlay(HttpRequest request) {
		startPlayback(request.session(), false);
	}

	public void start(HttpRequest request) {
		startPlayback(request.session(), true);
	}

	public void stop(HttpRequest request) {
		request.session().getRecorder().stop();
		new PlayerStopThread(request.session()).start();
	}

	public void setScriptFile(HttpRequest request) {
		Session session = request.session();
		String fileName = request.getParameter("file");
		SahiScript script = new ScriptFactory().getScript(fileName);
		session.setScript(script);
		// session.setScript(new ScriptFactory().getScript(
		// Configuration.getScriptFileWithPath(fileName)));
		session.setReport(new Report(script.getScriptName(), session
				.getSuiteLogDir(), new HtmlFormatter()));
		startPlayback(session, true);
	}

	public void setScriptUrl(HttpRequest request) {
		Session session = request.session();
		String url = request.getParameter("url");
		session.setScript(new ScriptFactory().getScript(url));
		session.setReport(new Report(session.getScript().getScriptName(),
				session.getSuiteLogDir(), new HtmlFormatter()));
		startPlayback(session, true);
	}

	private void startPlayback(Session session, boolean resetConditions) {
		if (session.getScript() != null)
			session.startPlayBack();
		session.setVariable("sahi_play", "1");
		session.setVariable("sahi_paused", "1");
		if (resetConditions)
			session.removeVariables("condn.*");
	}

	public HttpResponse currentScript(HttpRequest request) {
		Session session = request.session();
		HttpResponse httpResponse;
		if (session.getScript() != null) {
			httpResponse = new SimpleHttpResponse("<pre>"
					+ SahiScriptHTMLAdapter.createHTML(session.getScript()
							.getOriginal()) + "</pre>");
		} else {
			httpResponse = new SimpleHttpResponse(
					"No Script has been set for playback.");
		}
		return httpResponse;
	}

	public HttpResponse currentParsedScript(HttpRequest request) {
		Session session = request.session();
		HttpResponse httpResponse;
		if (session.getScript() != null) {
			httpResponse = new SimpleHttpResponse("<pre>"
					+ session.getScript().modifiedScript().replaceAll("\\\\r",
							"").replaceAll("\\\\n", "<br>") + "</pre>");
		} else {
			httpResponse = new SimpleHttpResponse(
					"No Script has been set for playback.");
		}
		return httpResponse;
	}

	public HttpResponse script(HttpRequest request) {
		Session session = request.session();
		String s = (session.getScript() != null) ? session.getScript()
				.modifiedScript() : "";
		return new NoCacheHttpResponse(s);
	}

	public HttpResponse auto(HttpRequest request) {
		Session session = request.session();
		String fileName = request.getParameter("file");

		final String scriptFileWithPath;
		scriptFileWithPath = fileName;
		session.setScript(new FileScript(scriptFileWithPath));
		String startUrl = request.getParameter("startUrl");
		session.setIsWindowOpen(false);

		Formatter formatter = (session.getSuite().isJunitReport()) ? new JUnitFormatter()
				: new HtmlFormatter();
		session.setReport(new Report(session.getScript().getScriptName(),
				session.getSuiteLogDir(), formatter));

		session.startPlayBack();
		return proxyAutoResponse(startUrl, session.id());
	}

	public void success(HttpRequest request) {
		Session session = request.session();
		new SessionState().setVar("sahi_retries", "0", session);
		new Log().execute(request);
	}

	private HttpFileResponse proxyAutoResponse(String startUrl, String sessionId) {
		Properties props = new Properties();
		props.setProperty("startUrl", startUrl);
		props.setProperty("sessionId", sessionId);
		return new HttpFileResponse(Configuration.getHtdocsRoot()
				+ "spr/auto.htm", props, false, true);
	}

	class PlayerStopThread extends Thread {
		private final Session session;

		PlayerStopThread(Session session) {
			this.session = session;
		}

		public void run() {
			stopPlay();
		}

		private void stopPlay() {
			if (session.getScript() != null)
				session.stopPlayBack();
			SahiTestSuite suite = SahiTestSuite.getSuite(session.id());
			if (suite != null) {
				suite.stop(session.getScript().getScriptName());
				waitSomeTime();
				if (!suite.executeNext())
					consolidateLogs(session.getSuiteLogDir());
			} else {
				consolidateLogs(session.getScriptLogFile());
			}
		}

		private void waitSomeTime() {
			try {
				Thread.sleep(Configuration.getTimeBetweenTestsInSuite());
			} catch (Exception e) {

			}

		}

		private void consolidateLogs(String consolidateBy) {
			try {
				new LogFileConsolidator(consolidateBy).summarize();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
