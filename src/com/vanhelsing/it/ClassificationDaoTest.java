package com.vanhelsing.it;

import junit.framework.Assert;
import android.test.AndroidTestCase;

import com.vanhelsing.Classification;
import com.vanhelsing.contentProvider.Category;
import com.vanhelsing.contentProvider.ClassificationDao;

public class ClassificationDaoTest extends AndroidTestCase {

	public void testPersistsAClassificationWithTheDocumentCount() throws Exception {
		final ClassificationDao classificationDao = new ClassificationDao(getContext());
		Assert.assertTrue(classificationDao.persist(Classification.BAD, 12));
		
		final Category bad = classificationDao.getBad();
		assertEquals(12, bad.documentCount());
	}
}