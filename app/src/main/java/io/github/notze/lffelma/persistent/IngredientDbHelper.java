package io.github.notze.lffelma.persistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Notze on 08.05.2016.
 */
public class IngredientDbHelper extends SQLiteOpenHelper{

    private static final String logTag = IngredientDbHelper.class.getSimpleName();

    // to be increased in case of schema change
    public static final int VERSION = 1;
    public static final String NAME = "Ingredients.db";

    public static final String TABLE_NAME = "ingredientList";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TEASPOON = "teaspoon";
    public static final String COLUMN_TABLESPOON = "tablespoon";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_TEASPOON + " TEXT NOT NULL, " +
                COLUMN_TABLESPOON + " TEXT NOT NULL );";

    private static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    private static final String INIT_TABLE =
            "INSERT INTO " + TABLE_NAME +
            "(" + COLUMN_NAME + "," + COLUMN_TEASPOON + "," + COLUMN_TABLESPOON + ")" +
            " VALUES " +
            "(\"Backpulver\",\"3g\",\"10g\" )," +
            "(\"Bratensoße instant\",\"3g\",\"8g\" )," +
            "(\"Brühe instant\",\"3g\",\"8g\" )," +
            "(\"Butter\",\"4g\",\"10g\" )," +
            "(\"Creme fraiche\",\"5g\",\"15g\" )," +
            "(\"Fruchtzucker\",\"4g\",\"12g\" )," +
            "(\"Graupen\",\"10g\",\"18g\" )," +
            "(\"Grütze roh\",\"10g\",\"18g\" )," +
            "(\"Haferflocken blütenzart\",\"3g\",\"8g\" )," +
            "(\"Haferflocken roh\",\"3g\",\"8g\" )," +
            "(\"Honig\",\"10g\",\"20g\" )," +
            "(\"Kaffeepulver\",\"2g\",\"6g\" )," +
            "(\"Kaffeesahne\",\"5g\",\"15g\" )," +
            "(\"Kakaopulver\",\"2g\",\"5g\" )," +
            "(\"Käse gerieben\",\"3g\",\"8g\" )," +
            "(\"Konfitüre/Marmelade\",\"6g\",\"16g\" )," +
            "(\"Kräuter gehackt\",\"2g\",\"4g\" )," +
            "(\"Mandeln gemahlen\",\"3g\",\"8g\" )," +
            "(\"Margarine\",\"4g\",\"10g\" )," +
            "(\"Mayonnaise\",\"4g\",\"12g\" )," +
            "(\"Mehl\",\"3g\",\"10g\" )," +
            "(\"Milch\",\"5g\",\"15g\" )," +
            "(\"Öl\",\"3g\",\"10g\" )," +
            "(\"Puddingpulver\",\"3g\",\"10g\" )," +
            "(\"Puderzucker\",\"3g\",\"10g\" )," +
            "(\"Sahne sauer\",\"5g\",\"13g\" )," +
            "(\"Sahne süß flüssig\",\"5g\",\"13g\" )," +
            "(\"Salz\",\"5g\",\"15g\" )," +
            "(\"Senf\",\"5g\",\"9g\" )," +
            "(\"Speisestärke\",\"3g\",\"9g\" )," +
            "(\"Tomatenmark\",\"5g\",\"15g\" )," +
            "(\"Zucker\",\"5g\",\"15g\" );";



    public IngredientDbHelper(Context context){
        super(context, NAME, null, VERSION);
        Log.d(logTag, "DatabaseObject " + NAME + " created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(logTag, "creating table " + TABLE_NAME);
            db.execSQL(CREATE_TABLE);
            db.execSQL(INIT_TABLE);
        } catch (Exception e) {
            Log.d(logTag, "creation of table failed: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(logTag, "deleting table " + TABLE_NAME + " for recreation");
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
}
