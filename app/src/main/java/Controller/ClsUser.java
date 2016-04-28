package Controller;

import android.content.Context;
import android.database.Cursor;

import Config.SQLite;
import Modell.User;

/**
 * Created by docente05 on 20/04/2016.
 * Esta clase es para mi CRUD
 */
public class ClsUser {
    SQLite cx;

    public ClsUser(Context context) {
        cx = new SQLite(context);
    }
    public boolean createUser(User u){
        if(u.getPassword().toString().equals(u.getConfirmpw().toString())) {
            cx.getWritableDatabase().execSQL("INSERT INTO user " +
                    "(campo1, campo2, campo3) " +
                    " VALUES " +
                    "('" + u.getName_user() + "', '" + u.getEmail() + "', '" + u.getPassword() + "' )");
            return true;
        }else{
            return false;
        }
    }
    public Cursor readUser(){
        return cx.getReadableDatabase().rawQuery("SELECT id_user as _id , campo1 , campo2 FROM user",null);
    }
}
