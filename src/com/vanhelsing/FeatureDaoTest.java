package com.vanhelsing;

import junit.framework.Assert;
import android.test.AndroidTestCase;

import com.vanhelsing.contentProvider.FeatureDao;

public class FeatureDaoTest extends AndroidTestCase{

	public void testPersistsAFeature() throws Exception {
		
		final FeatureDao featureDao = new FeatureDao(null, getContext());
		final Word feature = new Word("lottery", null);
		Assert.assertTrue(featureDao.persist(feature));
		
		final Feature persistedFeature = featureDao.get(feature);
		Assert.assertEquals(feature, persistedFeature);
	}
}
