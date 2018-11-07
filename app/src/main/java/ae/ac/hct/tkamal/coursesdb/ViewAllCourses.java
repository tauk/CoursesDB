package ae.ac.hct.tkamal.coursesdb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ViewAllCourses extends Activity {

    private TextView tvCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_courses);

        tvCourses = findViewById(R.id.tvCourses);

        CoursesDatabase cdb = CoursesDatabase.getDatabase(this.getApplicationContext());
        CourseDAO courseDAO = cdb.courseDAO();

        List<Course> courseList = courseDAO.getAllCourses();
        StringBuilder sbCourses = new StringBuilder();
        for (Course course : courseList) {
            sbCourses.append(course.getCourseCode()
                    + " " + course.getCourseName()
                    + " " + course.getCredits() + "\n");
        }
        tvCourses.setText(sbCourses.toString());
    }
}
