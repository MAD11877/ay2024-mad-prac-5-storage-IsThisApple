package sg.edu.np.mad.madpractical5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String USERS = "Users";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String ID = "id";
    private static final String FOLLOWED = "followed";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + USERS + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, " + DESCRIPTION + " TEXT, "  + FOLLOWED + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME , user.getName());
        values.put(DESCRIPTION, user.getDescription());
        values.put(FOLLOWED, user.getFollowed());
        db.insert(USERS, null, values);
        //db.close();
    }

    public List<User> getUsers(){
        List<User> listofuser = new ArrayList<User>();
        String selectQuery =  "SELECT * FROM " + USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                boolean followed = Boolean.parseBoolean(cursor.getString(3));

                User newuser = new User(name, description, id, followed);
                listofuser.add(newuser);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listofuser;
    }

    public void updateUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FOLLOWED, user.getFollowed());
        String clause = "id=?";
        String[] args = {String.valueOf(user.getId())};
        db.update(USERS, values, clause, args);
    }

}
