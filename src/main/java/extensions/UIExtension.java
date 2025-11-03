package extensions;

import com.google.inject.Guice;
import factory.WebDriverFactory;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import modules.PagesModule;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {
    private WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext context) {
        driver = WebDriverFactory.create();

        Guice.createInjector(new PagesModule(driver)).injectMembers(context.getTestInstance().get());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (driver != null) {
            driver.quit();
        }
    }
}

