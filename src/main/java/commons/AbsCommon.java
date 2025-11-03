package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public abstract class AbsCommon {
    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;

    protected JavascriptExecutor js;

    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;

        PageFactory.initElements(driver, this);
    }

    // actions template
    public AbsCommon waitSomeTime(int millis) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(millis));
        return this;
    }

    public AbsCommon shouldBeVisible(WebElement element) {
        this.wait.until(d -> element.isDisplayed());
        return this;
    }

    public AbsCommon hover(WebElement element) {
        this.js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
        waitSomeTime(1000);
        return this;
    }
}
