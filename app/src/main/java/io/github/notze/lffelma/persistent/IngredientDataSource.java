package io.github.notze.lffelma.persistent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Notze on 08.05.2016.
 */
public class IngredientDataSource {

    private static final String logTag = IngredientDataSource.class.getSimpleName();

    private SQLiteDatabase db;
    private IngredientDbHelper dbHelper;

    private String[] columns = {
        IngredientDbHelper.COLUMN_ID,
        IngredientDbHelper.COLUMN_NAME,
        IngredientDbHelper.COLUMN_TEASPOON,
        IngredientDbHelper.COLUMN_TABLESPOON};

    public IngredientDataSource(Context context){
        Log.d(logTag, "DataSource will now instantiate DatabaseHelper");
        dbHelper = new IngredientDbHelper(context);
    }

    public List<Ingredient> getAllIngredients(){
        List<Ingredient> ingredientList = new ArrayList<Ingredient>();

        Cursor cursor = db.query(IngredientDbHelper.TABLE_NAME,
                columns,
                null, null, null, null, null);

        cursor.moveToFirst();
        Ingredient ingredient;

        while(!cursor.isAfterLast()){
            ingredient = cursorToIngredient(cursor);
            ingredientList.add(ingredient);
            Log.d(logTag, "ID: " + ingredient.getId() + ", Content: " + ingredient.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return ingredientList;
    }

    public Ingredient createIngredient(String name, String teaspoon, String tablespoon){
        ContentValues values = new ContentValues();
                values.put(IngredientDbHelper.COLUMN_NAME, name);
                values.put(IngredientDbHelper.COLUMN_TEASPOON, teaspoon);
                values.put(IngredientDbHelper.COLUMN_TABLESPOON, tablespoon);

        long insertID = db.insert(IngredientDbHelper.TABLE_NAME, null, values);

        Cursor cursor = db.query(IngredientDbHelper.TABLE_NAME,
                columns,
                IngredientDbHelper.COLUMN_ID + " = " + insertID,
                null, null, null, null);

        cursor.moveToFirst();
        Ingredient ingredient = cursorToIngredient(cursor);

        cursor.close();

        return ingredient;
    }

    private Ingredient cursorToIngredient(Cursor cursor){
        int name_id = cursor.getColumnIndex(IngredientDbHelper.COLUMN_NAME);
        int teaspoon_id = cursor.getColumnIndex(IngredientDbHelper.COLUMN_TEASPOON);
        int tablespoon_id = cursor.getColumnIndex(IngredientDbHelper.COLUMN_TABLESPOON);

        String name = cursor.getString(name_id);
        String teaspoon = cursor.getString(teaspoon_id);
        String tablespoon = cursor.getString(tablespoon_id);

        Ingredient ingredient = new Ingredient(name, teaspoon, tablespoon);

        return ingredient;
    }

    public void open(){
        Log.d(logTag, "calling writable database reference");
        db = dbHelper.getWritableDatabase();
        Log.d(logTag, "got database reference: " + db.getPath());
    }

    public void close(){
        db.close();
        Log.d(logTag, "database closed");
    }
}
