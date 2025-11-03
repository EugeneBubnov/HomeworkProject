package otus.steps.commons;

import com.google.inject.Inject;
import io.cucumber.java.ru.Дано;
import support.GuiceScoped;

import java.util.Locale;

public class CommonSteps {
    @Inject
    private GuiceScoped guiceScoped;

    private String baseUrl = System.getProperty("base.url").toLowerCase(Locale.ROOT);

    @Дано("Открыть браузер chrome")
    public void openBrowser() {
        guiceScoped.getDriver().get(baseUrl);
    }
}
