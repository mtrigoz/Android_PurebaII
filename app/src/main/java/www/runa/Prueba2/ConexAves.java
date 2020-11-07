package www.runa.Prueba2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class ConexAves extends SQLiteOpenHelper {

    public static final String db_aves="credenciales.db";
    public static final String table_aves="credenciales_table";

    public String col  = "id";
    public String col2 = "Aves";
    public String col3 = "Comentarios";

    public ConexAves(@Nullable Context context){
        super(context, db_aves, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+table_aves+"(ID INTEGER PRIMARY KEY, AVES TEXT, COMENTARIOS TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    ///////////////////////////////
    public  boolean AgregarDatos(String Aves,String Comentarios) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, Aves);
        contentValues.put(col3, Comentarios);

        long resultado = db.insert(table_aves, null, contentValues);

        if (resultado == -1) {
            return false;
        } else {
            return true;
        }
    }

    //////////////
    public Cursor verTodo(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor respuesta = database.rawQuery("select * from "+table_aves,null);
        return respuesta;
    }

    /////////////

    public  Integer EliminarDatos(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return  database.delete(table_aves,"id = ?", new String[]{id});
    }

    /////////////////
    public boolean ActualizarDatos(String id, String Aves,String Comentarios){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col,id);
        contentValues.put(col2, Aves);
        contentValues.put(col3, Comentarios);

        sqLiteDatabase.update(table_aves,contentValues,"id = ?", new String[]{id});
        return true;
    }


}

