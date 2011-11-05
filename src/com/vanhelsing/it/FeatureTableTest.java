package com.vanhelsing.it;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.util.Log;

import com.vanhelsing.contentProvider.ClassificationTable;
import com.vanhelsing.contentProvider.FeatureTable;
import com.vanhelsing.contentProvider.SpamContentProvider;

public class FeatureTableTest extends ProviderTestCase2 {

	public FeatureTableTest() {
		super(SpamContentProvider.class, "com.vanhelsing.contentProvider");
	}
	
	@Override
	public void testAndroidTestCaseSetupProperly() {
		super.testAndroidTestCaseSetupProperly();
	}
	
	public void testInsertFeature() throws Exception {
		ContentValues contentValues = features();
		Uri insertedFeature = getProvider().insert(FeatureTable.URI, contentValues);
		Log.i("vanhelsing" , insertedFeature.toString());
		
		Cursor cursor = getProvider().query(FeatureTable.URI, new String[] { FeatureTable.DB_COL_NAME }, null, null, null);
		cursor.moveToFirst();
		String feature = cursor.getString(cursor.getColumnIndex(FeatureTable.DB_COL_NAME));
		assertEquals("money", feature);
	}

	private ContentValues features() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(FeatureTable.DB_COL_NAME, "money");
		return contentValues;
	}
	

}
