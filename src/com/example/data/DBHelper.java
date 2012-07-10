package com.example.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "sample.db";
	private static final int DATABASE_VERSION = 1;

	private SQLiteDatabase mdb;
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		//Initialize database
		this.mdb = this.getWritableDatabase();  
	}
	
	public <T extends Table> Cursor get(T table ){
		
		return mdb.query(table.getTableName(), table.getFields(),   
                                null, null, null, null, null);  
	}
	
	public <T extends Table> long insert(T table ){
		return mdb.insert(table.getTableName(), null, table.writeTo(new ContentValues()));		
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		
		Table tables[] = new Table[]{ new Device() };
		String sqlStatement;
		for( int i=0; i< tables.length; i++) {
			sqlStatement = tables[i].getCreateStatement();
			database.execSQL( sqlStatement );
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {		
		//db.execSQL("DROP TABLE IF EXISTS " + );
		//onCreate(db);
	}

}
