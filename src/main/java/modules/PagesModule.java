package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import pages.CoursesPage;
import pages.MainPage;
import support.GuiceScoped;

public class PagesModule extends AbstractModule {
    private final WebDriver driver;

    @Inject
    private GuiceScoped guiceScoped;

    public PagesModule(WebDriver driver) {
        this.driver = driver;
    }

    @Provides
    @Singleton
    public MainPage getMainPage() {
        return new MainPage(guiceScoped);
    }

    @Provides
    @Singleton
    public CoursesPage getCoursesPage() {
        return new CoursesPage(guiceScoped);
    }
}
