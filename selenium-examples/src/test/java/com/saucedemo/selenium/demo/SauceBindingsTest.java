package com.saucedemo.selenium.demo;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Demo tests using Sauce bindings.
 */
public class SauceBindingsTest {
    private SauceSession session;
    protected RemoteWebDriver driver;

    /**
     * A Test Watcher is needed to be able to get the results of a Test so that it can be sent to Sauce Labs.
     * Note that the name is never actually used
     */
    @RegisterExtension
    public SauceTestWatcher watcher = new SauceTestWatcher();

    @BeforeEach
    public void setup(TestInfo testInfo) {
        SauceOptions sauceOptions = SauceOptions.chrome()
                .setName(testInfo.getDisplayName())
                .build();
        session = new SauceSession(sauceOptions);
        driver = session.start();
    }

    @DisplayName("Sauce Bindings Navigation Test")
    @Test
    public void sauceBindingsNavigationTest() {
        driver.navigate().to("https://www.saucedemo.com");
        Assertions.assertEquals("Swag Labs", driver.getTitle());
    }

    /**
     * Custom TestWatcher for Sauce Labs projects.
     */
    public class SauceTestWatcher implements TestWatcher {
        @Override
        public void testSuccessful(ExtensionContext context) {
            session.stop(true);
        }

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            session.stop(false);
        }
    }
}
