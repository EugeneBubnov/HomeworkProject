package models;

public class CourseCard {
    private String name, date;

    public CourseCard() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CourseCard(String name, String date) {
        this.name = name;
        this.date = date;
    }
}
