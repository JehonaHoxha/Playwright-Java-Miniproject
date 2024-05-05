package com.nopcommerce.base;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class ScriptBase {

    protected static Playwright pw;
    protected static Browser browser;

    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    static void launchBrowser(){
        pw = Playwright.create();
        browser = pw.chromium().
                launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(400));
    }

    @BeforeEach
    void createContextAndPage(){
        context = browser.newContext();
        page = context.newPage();
        page.setViewportSize(1920, 1080);
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @AfterAll
    static void closeBrowser(){
        browser.close();
        pw.close();
    }

}
