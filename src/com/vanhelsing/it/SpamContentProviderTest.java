package com.vanhelsing.it;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.util.Log;

import com.vanhelsing.contentProvider.SpamContentProvider;

public class SpamContentProviderTest extends ProviderTestCase2 {

	public SpamContentProviderTest() {
		super(SpamContentProvider.class, "com.vanhelsing.contentProvider");
	}
	
	@Override
	public void testAndroidTestCaseSetupProperly() {
		super.testAndroidTestCaseSetupProperly();
	}
	
	public void testInsertFeature() throws Exception {
		ContentValues contentValues = spamData();
		Uri insertedFeature = getProvider().insert(SpamContentProvider.FeatureTable.FEATURE_URI, contentValues);
		Log.i("vanhelsing" , insertedFeature.toString());
		
		Cursor cursor = getProvider().query(SpamContentProvider.FeatureTable.FEATURE_URI, new String[] { SpamContentProvider.FeatureTable.DB_COL_NAME }, null, null, null);
		cursor.moveToFirst();
		String feature = cursor.getString(cursor.getColumnIndex(SpamContentProvider.FeatureTable.DB_COL_NAME));
		assertEquals("money", feature);
	}

	private ContentValues spamData() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(SpamContentProvider.FeatureTable.DB_COL_NAME, "money");
		return contentValues;
	}
	
	public void testInsertClassification() throws Exception {
		
	}

	private ContentValues classificationData() {
		// TODO Auto-generated method stub
		return null;
	}

}
