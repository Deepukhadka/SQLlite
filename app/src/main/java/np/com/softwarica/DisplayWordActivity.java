package np.com.softwarica;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.WorkSource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import np.com.softwarica.Helper.MyHelper;
import np.com.softwarica.model.Word;

public class DisplayWordActivity extends AppCompatActivity {

    private ListView lstWord;
    private Button openAddWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word);

        lstWord = findViewById(R.id.lstWords);
        openAddWord = findViewById(R.id.openAddWord);
        LoadWord();

        openAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(DisplayWordActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void LoadWord(){

        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();
        wordList = myHelper.getAllWords(sqLiteDatabase);

        HashMap<String,String> hashMap = new HashMap<>();
        for (int i=0;i< wordList.size(); i++){
            hashMap.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>( hashMap.keySet() )
        );
        lstWord.setAdapter(stringArrayAdapter);
    }


}
