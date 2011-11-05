package com.vanhelsing;

import junit.framework.Assert;
import android.test.AndroidTestCase;

import com.vanhelsing.contentProvider.FeatureDao;

public class FeatureDaoTest extends AndroidTestCase{

	public void testPersistsAFeature() throws Exception {
		
		final FeatureDao featureDao = new FeatureDao(getContext(), null);
		Assert.assertTrue(featureDao.persist(new Word("lottery", null)));
		
		final Feature feature = featureDao.get("lottery");
		Assert.assertEquals(new Word("lottery", null), feature);
	}
}
