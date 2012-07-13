package com.example.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "sampledb";
	private static final int DATABASE_VERSION = 1;

	private static SQLiteDatabase mdb = null;
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		//Initialize database
		if( mdb == null ){
			this.mdb = this.getWritableDatabase();
		}
	}
	
	public <T extends Table> Cursor get(T table ){
		return mdb.query(table.getTableName(), table.getFields(),   
                                null, null, null, null, null);  
	}
	
	public <T extends Table> long insert(T table ){
		return mdb.insert(table.getTableName(), null, table.writeTo(new ContentValues()));		
	}
	
	public <T extends Table> int delete(T table ){
		return mdb.delete(table.getTableName(), table.getPrimaryField().mName + " = ?", 
				new String[] { table.getPrimaryField().getValue().toString() } );		
	}
	
	public <T extends Table> int delete(T table, int recID ){
		return mdb.delete(table.getTableName(), table.getPrimaryField().mName + " = ?", 
				new String[] { String.valueOf(recID) } );		
	}	

	@Override
	public void onCreate(SQLiteDatabase database) {
		
		Table tables[] = new Table[]{ new Device() };
		String sqlStatement;
		for( int i=0; i< tables.length; i++) {
			sqlStatement = tables[i].getCreateStatement();
			System.out.println(sqlStatement);
			database.execSQL( sqlStatement );
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {		
		//db.execSQL("DROP TABLE IF EXISTS " + );
		//onCreate(db);
	}

}
