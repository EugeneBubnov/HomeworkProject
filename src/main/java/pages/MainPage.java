package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

    public MainPage(WebDriver driver) {
        super(driver);
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
