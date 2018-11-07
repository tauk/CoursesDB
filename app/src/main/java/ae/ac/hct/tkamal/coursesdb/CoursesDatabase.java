package ae.ac.hct.tkamal.coursesdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Course.class}, version = 1)
public abstract class CoursesDatabase extends RoomDatabase {

    public abstract CourseDAO courseDAO();

    private static volatile CoursesDatabase INSTANCE;

    static CoursesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CoursesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CoursesDatabase.class, "coursedb")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}