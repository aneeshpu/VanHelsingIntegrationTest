package com.vanhelsing;

import junit.framework.Assert;
import android.test.AndroidTestCase;

import com.vanhelsing.contentProvider.Category;
import com.vanhelsing.contentProvider.ClassificationDao;
import com.vanhelsing.contentProvider.FeatureClassificationDao;
import com.vanhelsing.contentProvider.FeatureDao;
import com.vanhelsing.contentProvider.FeatureTable;
import com.vanhelsing.contentProvider.SpamContentProvider;

public class FeatureDaoTest extends AndroidTestCase {

	private ClassificationDao classificationDao;
	private Category category;

	public void setUp() {

		classificationDao = new ClassificationDao(getContext());
		category = new Category(Classification.BAD, 1);
		classificationDao.persist(category);
	}

	public void testPersistsAFeature() throws Exception {

		final FeatureClassificationDao featureClassificationDao = new FeatureClassificationDao(classificationDao, getContext());
		final FeatureDao featureDao = new FeatureDao(null, getContext(), featureClassificationDao);

		final Word feature = new Word("Money", null);
		feature.incrementOccurrenceInClassification(Classification.BAD);
		Feature persistedFeature = null;
		try {
			Assert.assertTrue(featureDao.persist(feature));
			persistedFeature = featureDao.get(feature);
			Assert.assertEquals(feature, persistedFeature);
		} catch (Exception e) {
			e.printStackTrace();
		}

		featureDao.delete(persistedFeature);
		classificationDao.delete(category);
	}

	public void testPersistsAFeatureWithTheClassificationCount() {
		final FeatureClassificationDao featureClassificationDao = new FeatureClassificationDao(new ClassificationDao(getContext()), getContext());
		final FeatureDao featureDao = new FeatureDao(null, getContext(), featureClassificationDao);
		final Word feature = new Word("lottery", null);
		feature.incrementOccurrenceInClassification(Classification.BAD).incrementOccurrenceInClassification(Classification.BAD).incrementOccurrenceInClassification(Classification.BAD);

		Assert.assertTrue(featureDao.persist(feature));

		final Word persistedFeature = (Word) featureDao.get(feature);
		final int numberOfOccurrencesInClassification = persistedFeature.numberOfOccurrencesInClassification(Classification.BAD);

		assertEquals(3, numberOfOccurrencesInClassification);

		featureDao.delete(persistedFeature);
	}

	public void tearDown() {
		getContext().getContentResolver().delete(SpamContentProvider.FEATURE_URI, String.format("%s = '%s'", FeatureTable.DB_COL_NAME, "lottery"), null);
		getContext().getContentResolver().delete(SpamContentProvider.FEATURE_URI, String.format("%s = '%s'", FeatureTable.DB_COL_NAME, "Money"), null);
		getContext().getContentResolver().delete(SpamContentProvider.CLASSIFICATION_URI, String.format("%s = '%s'", FeatureTable.DB_COL_NAME, Classification.BAD.toString()), null);
	}
}
