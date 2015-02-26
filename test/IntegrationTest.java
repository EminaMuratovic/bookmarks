import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {

	/**
	 * add your integration test here in this example we just check if the
	 * welcome page is being shown
	 */
	@Test
	public void test() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())),
				HTMLUNIT, new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {
						browser.goTo("http://localhost:3333");
						assertThat(browser.pageSource()).contains(
								"Welcome to Bookmark");
						assertThat(browser.pageSource()).contains(
								"Bookmark !");
					}
				});
	}
	
	@Test
	public void testUsername() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())),
				HTMLUNIT, new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {
						browser.goTo("http://localhost:3333/");
						browser.fill("#name").with("test");
						browser.submit("#nameForm");
						assertThat(browser.pageSource()).contains(
								"Welcome test");
						assertThat(browser.pageSource()).contains(
								"save");
					}
				});
	}
	
	@Test
	public void testURL() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())),
				HTMLUNIT, new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {
						browser.goTo("http://localhost:3333/");
						browser.fill("#name").with("test"); //with nam oznacava sta upisujemo u label
						browser.submit("#nameForm");
						browser.fill("#url").with("www.bitcamp.ba");
						browser.submit("#addbookmark");
						assertThat(browser.pageSource()).contains(
								"www.bitcamp.ba");
					}
				});
	}

}
