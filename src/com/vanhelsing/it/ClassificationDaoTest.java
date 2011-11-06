package com.vanhelsing.it;

import android.test.AndroidTestCase;

import com.vanhelsing.Classification;
import com.vanhelsing.contentProvider.Category;
import com.vanhelsing.contentProvider.ClassificationDao;
import com.vanhelsing.contentProvider.IClassificationDao;

public class ClassificationDaoTest extends AndroidTestCase {

	public void testPersistsAClassificationWithTheDocumentCount() throws Exception {
		final IClassificationDao classificationDao = new ClassificationDao(getContext());
		assertNotNull(classificationDao.persist(new Category(Classification.BAD, 12)));
		
		final Category bad = classificationDao.getBad();
		assertEquals(12, bad.documentCount());
		
		final int noOfrowsDeleted = classificationDao.delete(bad);
		System.out.println(noOfrowsDeleted);
	}
	
	public void testGetsAClassificationFromDatabase() {
		
		final IClassificationDao classificationDao = new ClassificationDao(getContext());
		classificationDao.persist(new Category(Classification.BAD, 23));
		
		final Category category = classificationDao.get(Classification.BAD);
		assertNotNull(category);
		assertEquals(23, category.documentCount());
		
		classificationDao.delete(category);
	}
	
	public void testPersistsACategory() throws Exception {
		
		final ClassificationDao classificationDao = new ClassificationDao(getContext());
		final Category category = new Category(Classification.BAD, 24);
		final Category persistedCategory = classificationDao.persist(category);
		
		assertNotNull(persistedCategory);
		
		//TODO: How do I not use the DAOs for this.
		classificationDao.delete(persistedCategory);
	}
}