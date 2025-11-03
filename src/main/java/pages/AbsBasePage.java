package pages;

import org.openqa.selenium.WebDriver;
import annotations.Path;
import commons.AbsCommon;
import exceptions.PathNotFoundException;

public abstract class AbsBasePage<T> extends AbsCommon {
    private final String baseUrl = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    private String getPath() {
        Class<T> tClass = (Class<T>) this.getClass();

        if (tClass.isAnnotationPresent(Path.class)) {
            Path pathObject = tClass.getDeclaredAnnotation(Path.class);

            return pathObject.value();
        }

        throw new PathNotFoundException();
    }

    /**
     * Переход по прямому path => /path
     *
     * @return текущий класс
     */
    public T open() {
        driver.get(baseUrl + getPath());

        return (T) this;
    }
}
