package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Path("/catalog/courses")
public class CoursesPage extends AbsBasePage<CoursesPage> {

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h6/div")
    protected List<WebElement> courseNames;

    @FindBy(xpath = "//h6/div/ancestor::h6/following-sibling::div/div/div")
    protected List<WebElement> courseDates;

    public CoursesPage clickElementByName(String courseName) {
        WebElement currentCourse = courseNames.stream()
                .filter((WebElement course) -> courseName.equals(course.getText()))
                .findFirst()
                .get();

        hover(currentCourse);
        shouldBeVisible(currentCourse);
        currentCourse.click();

        return this;
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

    public CoursesPage findAllCoursesByRequirements() {
        // courseDates.stream().forEach(e -> System.out.println(e.getText()));

        List<Integer> allCourseDates = new ArrayList<>();
        courseDates.stream()
                .filter(e -> e.getText().contains("2025"))
                .forEach(webElement ->
                        allCourseDates.add(Integer.parseInt(webElement.getText()
                                .split("·")[0]
                                .replace("октября, 2025", "")
                                .trim()))
                );

        Integer minDate = allCourseDates.stream()
                .reduce((a, b) -> a < b ? a : b).get();
        List<Integer> earlyCourses = allCourseDates.stream()
                .filter(currentCourse -> currentCourse.equals(minDate))
                .toList();

        Integer maxDate = allCourseDates.stream().reduce((a, b) -> a > b ? a : b).get();
        List<Integer> lateCourses = allCourseDates.stream()
                .filter(e -> e.equals(maxDate))
                .toList();

        // todo: Даты, которые совпали
        /*
        Для каждого элемента n из списка:
        seen.add(n) пытается добавить число в множество, а!seen.add(n) инвертирует результат:
        - Если seen.add(n) вернул false (число УЖЕ было в множестве) → !false = true → элемент ПРОХОДИТ фильтр
        - Если seen.add(n) вернул true (число добавлено впервые) → !true = false → элемент НЕ проходит фильтр
         */
        Set<Integer> seen = new HashSet<>();
        List<Integer> duplicateDates = allCourseDates.stream()
                .filter(n -> !seen.add(n))//если seen.add(n) вернёт false
                .distinct()
                .toList();


        System.out.println(String.format("\nAll dates: %s\nMin dates: %s\nMax dates: %s\nDuplicate dates: %s",
                allCourseDates, earlyCourses, lateCourses, duplicateDates)
        );
        return this;
    }

    public void checkEarlyAndLatestCourses() {
        List<CourseCard> cardList = new ArrayList<>();

        for (int i = 0; i < courseNames.size(); i++) {
            hover(courseNames.get(i));
            cardList.add(new CourseCard(courseNames.get(i).getText(), courseDates.get(i).getText()));
        }

        /*
        for (CourseCard e : cardList) {
            System.out.println(e.getName() + "\n" + e.getDate() + "\n");
        }
         */

        //min
        List<Integer> allCourses = new ArrayList<>();
        courseDates.stream()
                .filter(e -> e.getText().contains("2025"))
                .forEach(webElement ->
                        allCourses.add(Integer.parseInt(webElement.getText()
                                .split("·")[0]
                                .replace("октября, 2025", "")
                                .trim()))
                );

        Integer minDate = allCourses.stream().reduce((first, second) -> first < second ? first : second).get();
        List<Integer> earlyCourses = allCourses.stream().filter(currentCourse -> currentCourse.equals(minDate)).toList();
        cardList.stream()
                .filter(e -> e.getDate().contains(String.valueOf(earlyCourses.getFirst())))
                .limit(1)
                .forEach(e -> {
                    System.out.println(String.format("Курс с минимальной датой: %s(%s)", e.getName(), e.getDate()));
                    clickElementByName(e.getName());
                    headingIsValid(e.getName());
                    dateIsValid(e.getDate().split(",")[0]);
                });
/*
        for (CourseCard e : allCoursesInfo) {
            if (e.getDate().contains(String.valueOf(earlyCourses.getFirst()))) {
                clickElementByName(e.getName());
                headingIsValid(e.getName());
                dateIsValid(e.getDate().split(",")[0]);
                break;
            }
        }
 */
        js.executeScript("window.history.back();");
        waitSomeTime(2000);

        Integer maxDate = allCourses.stream().reduce((first, second) -> first > second ? first : second).get();
        List<Integer> lateCourses = allCourses.stream().filter(currentCourse -> currentCourse.equals(maxDate)).toList();
        cardList.stream()
                .filter(e -> e.getDate().contains(String.valueOf(lateCourses.getFirst())))
                .limit(1)
                .forEach(e -> {
                    System.out.println(String.format("Курс с максимальной датой: %s(%s)", e.getName(), e.getDate()));
                    clickElementByName(e.getName());
                    headingIsValid(e.getName());
                    dateIsValid(e.getDate().split(",")[0]);
                });

        /*
        for (CourseCard e : allCoursesInfo) {
            if (e.getDate().contains(String.valueOf(lateCourses.getFirst()))) {
                clickElementByName(e.getName());
                headingIsValid(e.getName());
                dateIsValid(e.getDate().split(",")[0]);
                break;
            }
        }
         */

        js.executeScript("window.history.back();");
        waitSomeTime(2000);
    }
}
