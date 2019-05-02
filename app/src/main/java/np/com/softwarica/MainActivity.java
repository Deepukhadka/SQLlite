package np.com.softwarica;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import np.com.softwarica.Helper.MyHelper;

public class MainActivity extends AppCompatActivity {

    EditText etWord,etMeaning;
    Button btnAddWord,openListWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);
        btnAddWord = findViewById(R.id.btnAddWord);
        openListWord = findViewById(R.id.openListWord);

        openListWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this,DisplayWordActivity.class);
                startActivity(intent);
            }
        });

        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();


        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = myHelper.InsertData(etWord.getText().toString(),etMeaning.getText().toString(),sqLiteDatabase);
                if (id > 0){
                    Toast.makeText(MainActivity.this,"Successful " + id, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Error "+ id, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
