package io.github.notze.lffelma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import io.github.notze.lffelma.persistent.Ingredient;
import io.github.notze.lffelma.persistent.IngredientDataSource;

public class Main extends AppCompatActivity {

    public static final String logTag = Main.class.getSimpleName();

    IngredientDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ingredient testIngredient = new Ingredient("Mehl", "1g" ,"2g");
        Log.d(logTag, "content of testIngredient: " + testIngredient.toString());

        dataSource = new IngredientDataSource(this);

        Log.d(logTag, "opening dataSource");
        dataSource.open();

        //Ingredient testIngredient2 = dataSource.createIngredient("Zucker", "0,3g", "0,6g");
        //Log.d(logTag, "content of testIngredient2: " + testIngredient2.toString());
        showAllListEntries();

        Log.d(logTag, "closing dataSource");
        dataSource.close();
    }

    public void clearSearch(View view){
        EditText searchfield = (EditText) findViewById(R.id.searchfield);
        searchfield.setText("");
    }

    private void showAllListEntries () {
        List<Ingredient> ingredientList = dataSource.getAllIngredients();

        ArrayAdapter<Ingredient> ingredientArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                ingredientList);

        ListView ingredientListView = (ListView) findViewById(R.id.listviewIngredients);
        ingredientListView.setAdapter(ingredientArrayAdapter);
    }

    public void newPrompt(View view){
        Intent newEntryIntent = new Intent(this, NewEntry.class);
        startActivity(newEntryIntent);
    }
}
