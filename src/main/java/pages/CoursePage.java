package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoursePage extends AbsBasePage<CoursePage>{

    public CoursePage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    @FindBy(tagName = "h1")
    protected WebElement heading;

    public void headingIsValid(String headerName) {
        shouldBeVisible(heading);
        assertEquals(headerName, heading.getText());
    }

    public void dateIsValid(String date) {
        WebElement webDate = driver.findElement(By.xpath("//div/p[.='" + date + "']"));
        hover(webDate);
        shouldBeVisible(webDate);
        assertEquals(date, webDate.getText());
    }
}
