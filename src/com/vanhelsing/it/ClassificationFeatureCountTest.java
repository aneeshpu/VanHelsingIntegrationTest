package com.vanhelsing.it;

import com.vanhelsing.contentProvider.ClassificationFeatureCountTable;
import com.vanhelsing.contentProvider.SpamContentProvider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;

public class ClassificationFeatureCountTest extends ProviderTestCase2 {

	public ClassificationFeatureCountTest() {
		super(SpamContentProvider.class, SpamContentProvider.AUTHORITY);
	}

	public void testInsertOfClassificationFeatureCount() throws Exception {

		final Uri insertedId = getProvider().insert(SpamContentProvider.CLASSIFICATION_FEATURE_COUNT_URI, classificationFeatureCount());
		assertNotNull(insertedId);

		final Cursor cursor = getProvider().query(SpamContentProvider.CLASSIFICATION_FEATURE_COUNT_URI,
				new String[] { ClassificationFeatureCountTable.DB_COL_FEATURE_ID, ClassificationFeatureCountTable.DB_COL_CLASSIFICATION_ID, ClassificationFeatureCountTable.DB_COL_FEATURE_COUNT },
				null, null, null);
		
		assertNotNull(cursor);

		cursor.moveToFirst();

		assertEquals(1, cursor.getInt(cursor.getColumnIndex(ClassificationFeatureCountTable.DB_COL_FEATURE_ID)));
		assertEquals(2, cursor.getInt(cursor.getColumnIndex(ClassificationFeatureCountTable.DB_COL_CLASSIFICATION_ID)));
		assertEquals(10, cursor.getInt(cursor.getColumnIndex(ClassificationFeatureCountTable.DB_COL_FEATURE_COUNT)));

	}

	private ContentValues classificationFeatureCount() {
		final ContentValues contentValues = new ContentValues();
		contentValues.put(ClassificationFeatureCountTable.DB_COL_FEATURE_ID, 1);
		contentValues.put(ClassificationFeatureCountTable.DB_COL_CLASSIFICATION_ID, 2);
		contentValues.put(ClassificationFeatureCountTable.DB_COL_FEATURE_COUNT, 10);
		return contentValues;
	}
}
