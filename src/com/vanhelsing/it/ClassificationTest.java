package com.vanhelsing.it;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.util.Log;

import com.vanhelsing.contentProvider.ClassificationTable;
import com.vanhelsing.contentProvider.SpamContentProvider;

public class ClassificationTest extends ProviderTestCase2<SpamContentProvider> {

	public ClassificationTest() {
		super(SpamContentProvider.class, SpamContentProvider.AUTHORITY);
	}

	@Override
	public void testAndroidTestCaseSetupProperly() {
		super.testAndroidTestCaseSetupProperly();
	}

	public void testInsertClassification() throws Exception {
		ContentValues contentValues = classificationData();
		Uri insertedFeature = getProvider().insert(ClassificationTable.URI, contentValues);
		Log.i("vanhelsing", insertedFeature.toString());

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
