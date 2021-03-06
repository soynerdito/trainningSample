package com.example.data;

import android.content.ContentValues;
import android.database.Cursor;

public class Device extends Table {
	
	public Field<Integer> mRecID;
	public Field<String> mDeviceID;
	public Field<String> mDescription;
	
	
	public Device() {
		super("Device");
		mRecID = new Field<Integer>("_id", FieldType.PRIMARY, -1);
		mDeviceID = new Field<String>("DeviceID", FieldType.TEXT, "");
		mDescription = new Field<String>("Description", FieldType.TEXT, "");
		
		
		
	}

	@Override
	public String getCreateStatement() {		
		return buildCreateSQL(mRecID,mDeviceID,mDescription);
	}
	
	@Override
	public void loadFrom(Cursor cursor) {
		mRecID = Table.loadInteger(cursor, mRecID );
		mDeviceID = Table.loadString(cursor, mDeviceID );
		mDescription = Table.loadString(cursor, mDescription );
	}
	
	@Override
	public ContentValues writeTo(ContentValues values) {
		values = Table.writeString( values, mDeviceID );
		values = Table.writeString( values, mDescription );
		return values;
	}

	@Override
	public String[] getFields() {
		return new String[]{ mRecID.mName , mDeviceID.mName, mDescription.mName };
	}

	@Override
	public Field<Integer> getPrimaryField() {		
		return mRecID;
	}

}
