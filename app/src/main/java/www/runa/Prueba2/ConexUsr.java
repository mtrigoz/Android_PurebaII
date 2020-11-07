package www.runa.Prueba2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexUsr extends SQLiteOpenHelper {
    public static final String DBNAME  = "db_ornitólogo";
    public ConexUsr(Context context) {
        super(context, "db_ornitólogo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MiDB) {
        MiDB.execSQL("create Table ornitólogo(Usuario TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MiDB, int i, int i1) {
        MiDB.execSQL("drop Table if exists ornitólogo");
    }

    public Boolean insertData(String Usuario, String password){
        SQLiteDatabase MiDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Usuario", Usuario);
        contentValues.put("password", password);
        long result = MiDB.insert("ornitólogo", null, contentValues);
        return result != -1;
    }

    public Boolean checkusername(String Usuario) {
        SQLiteDatabase MiDB = this.getWritableDatabase();
        Cursor cursor = MiDB.rawQuery("Select * from ornitólogo where Usuario = ?", new String[]{Usuario});
        return cursor.getCount() > 0;
    }

    public Boolean checkusernamepassword(String Usuario, String password){
        SQLiteDatabase MiDB = this.getWritableDatabase();
        Cursor cursor = MiDB.rawQuery("Select * from ornitólogo where Usuario = ? and password = ?", new String[] {Usuario,password});
        return cursor.getCount() > 0;
    }
}
