package support;

import factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

@ScenarioScoped
public class GuiceScoped {
    private WebDriver driver = WebDriverFactory.create();

    public WebDriver getDriver() {
        return driver;
    }

    private HashMap<String, Object> storeObject = new HashMap<>();

    public <T> void store(String key, T object) {
        storeObject.put(key, object);
    }

    public <T> T retrieve(String key) {
        return (T) storeObject.get(key);
    }
}
