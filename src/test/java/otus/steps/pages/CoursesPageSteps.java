package otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import pages.CoursesPage;
import support.GuiceScoped;

public class CoursesPageSteps {

    @Inject
    private CoursesPage coursesPage;

    @Inject
    private GuiceScoped guiceScoped;

    @Дано("Открыта страница каталога курсов")
    public void openCoursesPage() {
        coursesPage.open();
    }

    @Когда("Кликнуть по случайному курсу")
    public void clickOnRandomCourse() {
        String title = coursesPage.getRandomCourseTitle();
        coursesPage.clickElementByName(title);

        guiceScoped.store("courseTitle", title);
    }
}
