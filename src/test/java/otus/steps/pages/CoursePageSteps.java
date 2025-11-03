package otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import pages.CoursePage;
import support.GuiceScoped;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoursePageSteps {

    @Inject
    private GuiceScoped guiceScoped;

    @Inject
    private CoursePage coursePage;

    @Тогда("Страница успешно открыта")
    public void shouldBeOpenedCoursePage() {
        String title = guiceScoped.retrieve("courseTitle");
        coursePage.headingIsValid(title);//validate in method
    }
}
