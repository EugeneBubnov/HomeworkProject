package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

    @Inject
    public MainPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    @FindBy(css = "[title='Обучение']")
    private WebElement learningLink;

    public MainPage openCategories() {
        learningLink.click();
        return this;
    }

    public MainPage findCategoryByName(String categoryName) {
        WebElement category = driver.findElement(By.xpath("//div/a[contains(.,'" + categoryName + "')]"));

        shouldBeVisible(category);
        category.click();

        waitSomeTime(2000);
        String currentUrl = driver.getCurrentUrl();

        switch (categoryName) {
            case "Программирование" -> assertEquals("https://otus.ru/catalog/courses?categories=programming", currentUrl);
            case "Архитектура" -> assertEquals("https://otus.ru/categories/architecture/", currentUrl);
            case "GameDev" -> assertEquals("https://otus.ru/catalog/courses?categories=gamedev", currentUrl);
            default -> throw new RuntimeException(categoryName + " not supported");
        }

        return this;
    }
}
