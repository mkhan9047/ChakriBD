package notification.push.com.chakribd.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import notification.push.com.chakribd.Adapter.NaviListCustomAdapter;
import notification.push.com.chakribd.POJO.FavoutitePojo;
import notification.push.com.chakribd.POJO.NaviListPojo;

/**
 * Created by Mujahid on 5/30/2018.
 */

public class DatabaseOperation {
    public static boolean InsertData(Context context, String title, String link){
        boolean isSaved;
        try {
            ContentValues values = new ContentValues();
            values.put("title", title);
            values.put("link", link);

            SQLiteOpenHelper helper = new ChakriDatabase(context);
            SQLiteDatabase database = helper.getWritableDatabase();
            database.insert("favourite", null, values);
          isSaved = true;
        }catch(SQLiteException e){
            e.getMessage();
            isSaved = false;
        }
        return isSaved;

    }
    public static List<FavoutitePojo> getFavourite(Context context) {
        SQLiteOpenHelper helper = new ChakriDatabase(context);
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = null;
        final List<FavoutitePojo> pjo = new ArrayList<>();
        String sqlQueary = "Select * from favourite";
        cursor = database.rawQuery(sqlQueary, null);
        while (cursor.moveToNext()) {
            FavoutitePojo locationPojo = new FavoutitePojo(cursor.getString(1), cursor.getString(0));
            pjo.add(locationPojo);
        }
        database.close();
        cursor.close();
        return pjo;
    }

    public static boolean DeleteFav(String title, Context context){
        boolean isDeletected = false;
        try{
            SQLiteOpenHelper helper = new ChakriDatabase(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            database.delete("favourite","title=?",new String[]{title});
            isDeletected = true;
        }catch (SQLiteException e){
            e.getMessage();
            isDeletected = false;
        }
        return isDeletected;
    }
}
