package practice_one;

import com.google.inject.Inject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import extensions.UIExtension;
import pages.CoursePage;
import pages.CoursesPage;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class HomeworkTest {

    @Inject
    private CoursesPage coursesPage;
    @Inject
    private CoursePage coursePage;

    @Inject
    private MainPage mainPage;

    String[] neededCourse = {"Vue.js разработчик", "Kotlin QA Engineer", "Алгоритмы и структуры данных"};

    @Test
    @Disabled
    @DisplayName("Сценарий 1.")
    void findCourseTest() {
        coursesPage
                .open()
                .clickElementByName(neededCourse[0]);

        coursePage.headingIsValid(neededCourse[0]);
    }

    @Test
    @Disabled
    @DisplayName("Сценарий 2.")
    void findDifferentCourses() {
        coursesPage
                .open()
                .findAllCoursesByRequirements()
                .checkEarlyAndLatestCourses();
    }

    String[] categories = {"Программирование", "Архитектура", "GameDev"};

    @Test
    @Disabled
    @DisplayName("Сценарий 3.")
    void validateCategory() {
        mainPage
                .open()
                .openCategories()
                .findCategoryByName(categories[2]);
    }
}
