package Config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by docente05 on 20/04/2016.
 */
public class SQLite extends SQLiteOpenHelper {

    private static String NAME_DATABASE = "dbuser";
    private static int VERSION=1;

    private String TABLE_USER ="CREATE TABLE user " +
            "(id_user INTEGER PRIMARY KEY AUTOINCREMENT , " +
            " campo1 TEXT, " +
            " campo2 TEXT, " +
            " campo3 TEXT)";

    public SQLite(Context c) {
        super(c, NAME_DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        this.onCreate(db);
    }
}
