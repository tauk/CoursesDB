package ae.ac.hct.tkamal.coursesdb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "course_code")
    private String courseCode;

    @NonNull
    @ColumnInfo(name = "course_name")
    private String courseName;

    @NonNull
    @ColumnInfo(name = "credits")
    private int credits;

    public Course(String courseCode, String courseName, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
    }

    public void setCourseCode(String code) {
        this.courseCode = code;
    }

    public void setCourseName(String name) {
        this.courseName = name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

}
