package io.github.notze.lffelma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
    }

    public void addNewEntry(View view){
        finish();
        //TODO add to database
    }

    public void abortAdding(View view){
        finish();
    }

}
