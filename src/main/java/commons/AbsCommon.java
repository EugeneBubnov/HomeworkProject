package commons;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.GuiceScoped;

import java.time.Duration;

public abstract class AbsCommon {
    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected GuiceScoped guiceScoped;

    protected JavascriptExecutor js;

    public AbsCommon(GuiceScoped guiceScoped) {
        this.guiceScoped = guiceScoped;
        this.driver = guiceScoped.getDriver();
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;

        PageFactory.initElements(guiceScoped.getDriver(), this);
    }

    // actions template
    public AbsCommon waitSomeTime(int millis) {
        guiceScoped.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(millis));
        return this;
    }

    public AbsCommon shouldBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    public AbsCommon hover(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
        waitSomeTime(1000);
        return this;
    }
}
