package com.example.data;

import android.content.ContentValues;
import android.database.Cursor;

public abstract class Table {
	
	public enum FieldType{
		INT("INTEGER"), TEXT("TEXT"), PRIMARY("integer primary key autoincrement");
		public final String mType;
		
		FieldType(String type){
			mType = type;
		}
		
		@Override
		public String toString(){
			return mType;
		}
		
	}
	public class Field<T>{
		public String mName;
		public FieldType mDataType;
		
		private T mValue;
		public T getValue(){
			return mValue;
		}
		
		public void setValue(T value){
			mValue = value;
		}
		
		public Field(String name,FieldType dataType, T defaultValue){
			mName = name;
			mDataType = dataType;
			mValue = defaultValue;
		}
				
	}
	
	private String mName;
	
	public String getTableName(){
		return mName;
	}
	
	public Table(String name){
		mName = name;
	}

	public abstract String[] getFields();
	
	protected String buildCreateSQL(Field ...fields){
		StringBuilder builder = new StringBuilder();
		String comma = "";
		builder.append("CREATE TABLE " + getTableName() + " ( ");
		for(int i=0; i< fields.length; i++){
			builder.append( comma + fields[i].mName + " " + fields[i].mDataType.toString() );			
			comma = " , ";
		}
		builder.append( " ) ");		
		return builder.toString();
	}
	
	public static Field<String> loadString(Cursor cursor, Field<String> field){
		field.setValue(cursor.getString(cursor.getColumnIndex(field.mName)));
		return field;
	}
	
	public static Field<Integer> loadInteger(Cursor cursor, Field<Integer> field){
		field.setValue(cursor.getInt(cursor.getColumnIndex(field.mName)));
		return field;
	}
	
	public static Field<Double> loadDouble(Cursor cursor, Field<Double> field){
		field.setValue(cursor.getDouble(cursor.getColumnIndex(field.mName)));
		return field;
	}
	
	public abstract String getCreateStatement();
	public abstract void loadFrom(Cursor cursor);
	public abstract ContentValues writeTo(ContentValues values);
	
}
