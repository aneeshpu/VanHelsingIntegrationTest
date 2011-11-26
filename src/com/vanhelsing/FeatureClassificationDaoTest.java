package com.vanhelsing;

import java.util.Map;

import android.test.AndroidTestCase;

import com.vanhelsing.contentProvider.Category;
import com.vanhelsing.contentProvider.ClassificationDao;
import com.vanhelsing.contentProvider.ClassificationTable;
import com.vanhelsing.contentProvider.FeatureClassificationDao;
import com.vanhelsing.contentProvider.FeatureDao;
import com.vanhelsing.contentProvider.FeatureTable;
import com.vanhelsing.contentProvider.IFeatureCategoryDao;
import com.vanhelsing.contentProvider.SpamContentProvider;

public class FeatureClassificationDaoTest extends AndroidTestCase {

	public void testPersistsAFeatureClassification() throws Exception {

		final ClassificationDao classificationDao = new ClassificationDao(getContext());
		classificationDao.persist(new Category(Classification.BAD, 1));

		final Feature feature = new Word("lottery", null);
		feature.incrementOccurrenceInClassification(Classification.BAD).incrementOccurrenceInClassification(Classification.BAD);

		final IFeatureCategoryDao featureClassificationDao = new FeatureClassificationDao(classificationDao, getContext());

		final FeatureDao featureDao = new FeatureDao(null, getContext(), featureClassificationDao);
		featureDao.persist(feature);

		final Feature persistedFeature = featureDao.get(feature);
		assertNotNull(persistedFeature);

		final Map<Classification, Integer> classificationCountMap = persistedFeature.getClassificationCountMap();
		assertEquals(new Integer(2), classificationCountMap.get(Classification.BAD));

		featureDao.delete(persistedFeature);
	}

	public void tearDown() {
		getContext().getContentResolver().delete(SpamContentProvider.FEATURE_URI, String.format("%s = '%s'", FeatureTable.DB_COL_NAME, "lottery"), null);
		getContext().getContentResolver().delete(SpamContentProvider.CLASSIFICATION_URI, String.format("%s = '%s'", ClassificationTable.DB_COL_NAME, Classification.BAD.toString()), null);
	}
}
