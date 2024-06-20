package tests;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Paths;

@ExtendWith(TestWatcherExtension.class)
public class ScriptBase {

    protected static Playwright pw;
    protected static Browser browser;

    protected static BrowserContext context;
    protected Page page;

    @BeforeAll
    static void launchBrowser(){
        pw = Playwright.create();
        browser = pw.chromium().
                launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
    }

    @BeforeEach
    void createContextAndPage(){
        context = browser.newContext();
        page = context.newPage();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @AfterEach
    void closeContext(TestInfo testInfo) {
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("traces" + testInfo.getDisplayName().replace(" ", "") + ".zip")));

        page.screenshot(new Page.ScreenshotOptions()
                        .setFullPage(true)
                .setPath(Paths.get("screenshot" + testInfo.getDisplayName().replace(" ", "") + ".png")));

        context.close();
    }

    @AfterAll
    static void closeBrowser(){
        browser.close();
        pw.close();
    }

}
