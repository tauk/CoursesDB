package ae.ac.hct.tkamal.coursesdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {

    private EditText etCourseCode;
    private EditText etCourseName;
    private EditText etCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCourseCode = findViewById(R.id.etCourseCode);
        etCourseName = findViewById(R.id.etCourseName);
        etCredits = findViewById(R.id.etCredits);
    }

    public void addCourse(View view) {
        CoursesDatabase cdb = CoursesDatabase.getDatabase(this.getApplicationContext());
        CourseDAO courseDAO = cdb.courseDAO();

        String courseCode = etCourseCode.getText().toString();
        String courseName = etCourseName.getText().toString();
        String strCredits = etCredits.getText().toString();

        int credits = Integer.parseInt(strCredits);

        if (courseDAO.getCourse(courseCode) != null) {
            Toast.makeText(this, courseCode + " already exists", Toast.LENGTH_LONG).show();
            return;
        }

        Course course = new Course(courseCode, courseName, credits);
        courseDAO.insert(course);

        Toast.makeText(this, "Course Added!", Toast.LENGTH_SHORT).show();
    }

    public void showAllCourses(View view) {
        Intent intent = new Intent(this, ViewAllCourses.class);
        startActivity(intent);
    }

    public void doFindByCode(View view) {
        CoursesDatabase cdb = CoursesDatabase.getDatabase(this.getApplicationContext());
        CourseDAO courseDAO = cdb.courseDAO();

        String courseCode = etCourseCode.getText().toString();
        Course course = courseDAO.getCourse(courseCode);

        if (course == null) {
            Toast.makeText(this, courseCode + " NOT found!", Toast.LENGTH_LONG).show();
            return;
        }

        //fill the course name and credits
        etCourseName.setText(course.getCourseName());
        etCredits.setText(course.getCredits() + "");
    }


    public void doUpdate(View view) {
        CoursesDatabase cdb = CoursesDatabase.getDatabase(this.getApplicationContext());
        CourseDAO courseDAO = cdb.courseDAO();

        String courseCode = etCourseCode.getText().toString();
        String courseName = etCourseName.getText().toString();
        String strCredits = etCredits.getText().toString();

        int credits = Integer.parseInt(strCredits);
        Course course = new Course(courseCode, courseName, credits);

        int updateCount = courseDAO.updateCourse(course);

        if (updateCount == 1) {
            Toast.makeText(this, courseCode + " data updated!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, courseCode + " could NOT update", Toast.LENGTH_LONG).show();
        }
    }

    public void doDelete(View view) {
        CoursesDatabase cdb = CoursesDatabase.getDatabase(this.getApplicationContext());
        CourseDAO courseDAO = cdb.courseDAO();

        String courseCode = etCourseCode.getText().toString();

        //check if course code exists
        Course course = courseDAO.getCourse(courseCode);
        if (course == null) {
            Toast.makeText(this, courseCode + " NOT found!", Toast.LENGTH_LONG).show();
            return;
        }

        //delete by course code
        int deleteCount = courseDAO.deleteByCourseCode(courseCode);
        if (deleteCount == 1) {
            Toast.makeText(this, courseCode + " deleted", Toast.LENGTH_LONG).show();
            etCourseCode.setText("");
            etCourseName.setText("");
            etCredits.setText("");
        }
        else {
            Toast.makeText(this, courseCode + " could NOT be deleted", Toast.LENGTH_LONG).show();
        }
    }
}
