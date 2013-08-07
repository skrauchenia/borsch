package com.exadel.borsch.entiry;

/**
 * @author Tima
 */
public enum Course {
    FIRST_COURSE("firstCourse"),
    SECOND_COURSE("secondCourse"),
    DESSERT("dessert");

    private String name;
    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static Course getCourse(String courseStr) {
        Course course = null;
        switch (courseStr) {
            case "FIRST_COURSE":
                course = Course.FIRST_COURSE;
                break;
            case "SECOND_COURSE":
                course = Course.SECOND_COURSE;
                break;
            case "DESSERT":
                course = Course.DESSERT;
                break;
            default:
                throw new AssertionError();
        }
        return course;
    }
}
