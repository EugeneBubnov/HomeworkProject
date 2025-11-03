package extensions;

import com.google.inject.Guice;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import modules.PagesModule;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {
    private WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext context) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions() {{
            addArguments("start-maximized", "--remote-allow-origins=*");
        }};
        driver = new ChromeDriver(options);

        Guice.createInjector(new PagesModule(driver)).injectMembers(context.getTestInstance().get());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (driver != null) {
            //driver.quit();
        }
    }
}

