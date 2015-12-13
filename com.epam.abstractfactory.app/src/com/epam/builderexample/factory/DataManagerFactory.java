package com.epam.builderexample.factory;

import com.epam.builderexample.datamanager.DBDataManager;
import com.epam.builderexample.datamanager.FileDataManager;
import com.epam.builderexample.datamanager.DataManager;


public class DataManagerFactory extends AbstractDataManagerFactory {

	@Override
	public DataManager createDBDataManager() {
		return new DBDataManager();
	}

	@Override
	public DataManager createFileDataManager() {
		return new FileDataManager();
	}

}
