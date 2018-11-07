package ae.ac.hct.tkamal.coursesdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CourseDAO {

    @Insert
    void insert(Course course);

    @Query("SELECT * FROM courses")
    List<Course> getAllCourses();

    @Query("SELECT * FROM courses WHERE course_code=:courseCode")
    Course getCourse(String courseCode);

    @Update
    int updateCourse(Course course);

    @Delete
    int deleteCourse(Course course);

    @Query("DELETE FROM courses WHERE course_code=:courseCode")
    int deleteByCourseCode(String courseCode);
}
