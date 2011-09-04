package com.vanhelsing.it;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.util.Log;

import com.vanhelsing.Classification;
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
		ContentValues contentValues = classificationGood();
		Uri insertedFeature = getProvider().insert(ClassificationTable.URI, contentValues);
		Log.i("vanhelsing", insertedFeature.toString());

		Cursor cursor = getProvider().query(ClassificationTable.URI, new String[] { ClassificationTable.DB_COL_NAME, ClassificationTable.DB_COL_DOCUMENT_COUNT }, null, null, null);
		cursor.moveToFirst();
		String classification = cursor.getString(cursor.getColumnIndex(ClassificationTable.DB_COL_NAME));
		int document_count = cursor.getInt(cursor.getColumnIndex(ClassificationTable.DB_COL_DOCUMENT_COUNT));
		assertEquals(Classification.GOOD.toString(), classification);
		assertEquals(16, document_count);

	}

	public void testSelectionOfBadClassification() throws Exception {

		Uri insertedFeature = getProvider().insert(ClassificationTable.URI, classificationGood());
		getProvider().insert(ClassificationTable.URI, classificationBad());
		
		Log.i("vanhelsing", insertedFeature.toString());

		final Cursor cursor = getProvider().query(ClassificationTable.URI, new String[] { ClassificationTable.DB_COL_NAME, ClassificationTable.DB_COL_DOCUMENT_COUNT },
				String.format("%s = '%s'", ClassificationTable.DB_COL_NAME, Classification.BAD.toString()), null, null);
	
		cursor.moveToFirst();
		assertEquals(1, cursor.getCount());
		assertEquals(20, cursor.getInt(cursor.getColumnIndex(ClassificationTable.DB_COL_DOCUMENT_COUNT)));
	}
	
	public void testSelectionOfGoodDocuments() throws Exception {
		Uri insertedFeature = getProvider().insert(ClassificationTable.URI, classificationGood());
		getProvider().insert(ClassificationTable.URI, classificationBad());
		
		Log.i("vanhelsing", insertedFeature.toString());

		final Cursor cursor = getProvider().query(ClassificationTable.URI, new String[] { ClassificationTable.DB_COL_NAME, ClassificationTable.DB_COL_DOCUMENT_COUNT },
				String.format("%s = '%s'", ClassificationTable.DB_COL_NAME, Classification.GOOD.toString()), null, null);
	
		cursor.moveToFirst();
		assertEquals(1, cursor.getCount());
		assertEquals(16, cursor.getInt(cursor.getColumnIndex(ClassificationTable.DB_COL_DOCUMENT_COUNT)));
		
	}

	private ContentValues classificationBad() {
		final ContentValues contentValues = new ContentValues();
		contentValues.put(ClassificationTable.DB_COL_NAME, Classification.BAD.toString());
		contentValues.put(ClassificationTable.DB_COL_DOCUMENT_COUNT, 20);
		return contentValues;
	}

	private ContentValues classificationGood() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(ClassificationTable.DB_COL_NAME, Classification.GOOD.toString());
		contentValues.put(ClassificationTable.DB_COL_DOCUMENT_COUNT, 16);
		return contentValues;
	}

}
