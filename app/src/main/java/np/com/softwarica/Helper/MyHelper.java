package np.com.softwarica.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import np.com.softwarica.Word;

public class MyHelper extends SQLiteOpenHelper {

    private static final String dbName = "DictionaryDb";
    private static final int dbVersion = 1;

    //tblWord fields
    private static final String tblWord = "tblWord";
    private static final String wordId= "wordId";
    private static final String word = "word";
    private static final String meaning = "meaning";


    public MyHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE "+tblWord+
                "(" +
                wordId + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                word + " TEXT," +
                meaning + " TEXT " +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long InsertData(String Word,String Meaning,SQLiteDatabase db){

        long id;
        ContentValues contentValues = new ContentValues();
        contentValues.put(word,Word);
        contentValues.put(meaning,Meaning);
        id = db.insert(tblWord,null,contentValues);
        return id;

    }

    public List<Word> getAllWords(SQLiteDatabase db){

        List<Word> dictionaryList = new ArrayList<>();
        String[] colums = {wordId,word,meaning};
        Cursor cursor = db.query(tblWord,colums,null,null,null,null,null);

        if (cursor.getCount() > 0  ){
            while (cursor.moveToNext()){
                dictionaryList.add(new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2) ));
            }
        }
        return dictionaryList;
    }
}
