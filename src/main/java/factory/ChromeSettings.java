package factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ChromeSettings implements ISettings {
    @Override
    public AbstractDriverOptions settings() {
        return new ChromeOptions() {{
            addArguments("start-maximized", "--remote-allow-origins=*");
        }};
    }
}
