package notification.push.com.chakribd.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mujahid on 5/30/2018.
 */

public class ChakriDatabase extends SQLiteOpenHelper {

    private static final int database_version = 1;
    private static final String database_name = "chakri";

    public ChakriDatabase(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String fav = "CREATE TABLE favourite (\n" +
                "link TEXT,\n" +
                "title TEXT \n" +
                ");";
        sqLiteDatabase.execSQL(fav);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
