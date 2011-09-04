package com.vanhelsing.it;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.util.Log;

import com.vanhelsing.contentProvider.ClassificationTable;
import com.vanhelsing.contentProvider.FeatureTable;
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
		Uri insertedFeature = getProvider().insert(FeatureTable.URI, contentValues);
		Log.i("vanhelsing" , insertedFeature.toString());
		
		Cursor cursor = getProvider().query(FeatureTable.URI, new String[] { FeatureTable.DB_COL_NAME }, null, null, null);
		cursor.moveToFirst();
		String feature = cursor.getString(cursor.getColumnIndex(FeatureTable.DB_COL_NAME));
		assertEquals("money", feature);
	}

	private ContentValues spamData() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(FeatureTable.DB_COL_NAME, "money");
		return contentValues;
	}
	
	public void testInsertClassification() throws Exception {
		ContentValues contentValues = classificationData();
		Uri insertedFeature = getProvider().insert(ClassificationTable.URI, contentValues);
		Log.i("vanhelsing" , insertedFeature.toString());
		
		Cursor cursor = getProvider().query(ClassificationTable.URI, new String[] { ClassificationTable.DB_COL_NAME, ClassificationTable.DB_COL_DOCUMENT_COUNT }, null, null, null);
		cursor.moveToFirst();
		String classification = cursor.getString(cursor.getColumnIndex(ClassificationTable.DB_COL_NAME));
		int document_count = cursor.getInt(cursor.getColumnIndex(ClassificationTable.DB_COL_DOCUMENT_COUNT));
		assertEquals("good", classification);
		assertEquals(1, document_count);
		
	}

	private ContentValues classificationData() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(ClassificationTable.DB_COL_NAME, "good");
		contentValues.put(ClassificationTable.DB_COL_DOCUMENT_COUNT, 1);
		return contentValues;
	}

}
