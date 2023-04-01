package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import model.Word;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create="create table "+Constants.TABLE_NAME+"("+Constants.FWORD+" text, "+Constants.MWORD+" text,"+Constants.KEY_ID+" integer primary key autoincrement);";
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String upgrade="drop table if exists "+Constants.TABLE_NAME+";";
        sqLiteDatabase.execSQL(upgrade);
    }

    public boolean insertData(Word word){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.FWORD,word.getF_word());
        values.put(Constants.MWORD,word.getM_word());
        long result = DB.insert(Constants.TABLE_NAME,null, values);
        DB.close();
        return result != -1;
    }



    public boolean updateData(Word word){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.FWORD,word.getF_word());
        values.put(Constants.MWORD,word.getM_word());
        Cursor cursor=db.rawQuery("select * from "+Constants.TABLE_NAME+" where "+Constants.KEY_ID+"=?",new String[]{String.valueOf(word.getItemId())});
        if(cursor.getCount()>0){
            long result=db.update(Constants.TABLE_NAME,values,Constants.KEY_ID+"=?", new String[]{String.valueOf(word.getItemId())});
            db.close();
            return result != -1;
        }else{
            db.close();
            return false;
        }

    }


    public boolean deleteData(Word word){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Constants.TABLE_NAME+" where "+Constants.KEY_ID+"=?",new String[]{String.valueOf(word.getItemId())});
        if(cursor.getCount()>0){
            long result = db.delete(Constants.TABLE_NAME, Constants.KEY_ID+"=?", new String[]{ String.valueOf(word.getItemId())});
            db.close();
            return result != -1;
        }else{
            db.close();
            return false;
        }
    }

    public  Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+Constants.TABLE_NAME,null);
        db.close();
        return cursor;
    }
    public ArrayList<Word> getWords(){
        ArrayList<Word> words=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+Constants.TABLE_NAME,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                String wordString = null;
                Word w=new Word(null);
                w.setF_word(cursor.getString(0));
                w.setM_word(cursor.getString(1));
                w.setItemId(cursor.getInt(2));
                words.add(w);
            }
        }else{
            db.close();
            return words;
        }
        db.close();
        return words;
    }
}
