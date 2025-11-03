package support;

import factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped {
    private WebDriver driver = WebDriverFactory.create();

    public WebDriver getDriver() {
        return driver;
    }
}
