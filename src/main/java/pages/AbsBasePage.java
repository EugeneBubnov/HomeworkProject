package pages;

import annotations.Path;
import com.google.inject.Inject;
import commons.AbsCommon;
import exceptions.PathNotFoundException;
import support.GuiceScoped;

public abstract class AbsBasePage<T> extends AbsCommon {
    private final String baseUrl = System.getProperty("base.url");

    @Inject
    public AbsBasePage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    private String getPath() {
        Class<T> tClass = (Class<T>) this.getClass();

        if (tClass.isAnnotationPresent(Path.class)) {
            Path pathObject = tClass.getDeclaredAnnotation(Path.class);

            return pathObject.value();
        }

        throw new PathNotFoundException();
    }

    public T open() {
        driver.get(baseUrl + getPath());

        return (T) this;
    }
}
