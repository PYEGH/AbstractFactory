package com.epam.builderexample.factory;

import com.epam.builderexample.datamanager.DataManager;

public abstract class AbstractDataManagerFactory {
	public abstract DataManager createDBDataManager();
	public abstract DataManager createFileDataManager();
}
