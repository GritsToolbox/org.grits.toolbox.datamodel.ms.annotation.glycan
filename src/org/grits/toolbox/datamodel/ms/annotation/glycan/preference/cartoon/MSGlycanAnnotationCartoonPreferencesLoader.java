package org.grits.toolbox.datamodel.ms.annotation.glycan.preference.cartoon;

import org.apache.log4j.Logger;
import org.grits.toolbox.core.datamodel.UnsupportedVersionException;
import org.grits.toolbox.core.preference.share.PreferenceEntity;

public class MSGlycanAnnotationCartoonPreferencesLoader {
	private static final Logger logger = Logger.getLogger(MSGlycanAnnotationCartoonPreferencesLoader.class);

	public final static MSGlycanAnnotationCartoonPreferences getCartoonPreferences()  {
		MSGlycanAnnotationCartoonPreferences cartoonPreferences = null;
		try {
			PreferenceEntity preferenceEntity = MSGlycanAnnotationCartoonPreferences.getPreferenceEntity(); 
			if( preferenceEntity == null ) { // previous version
				cartoonPreferences = MSGlycanAnnotationCartoonPreferencesPreVersion.getCartoonPreferences(preferenceEntity);
				if( cartoonPreferences != null ) {
					MSGlycanAnnotationCartoonPreferencesPreVersion.removeElements();
					cartoonPreferences.loadDefaultOptions(); // we need to go to the file for the default layout, orientations, and styles
					cartoonPreferences.saveValues(); // save into the new format
				}
			} else {
				cartoonPreferences = MSGlycanAnnotationCartoonPreferences.getCartoonSettings(preferenceEntity);
			}
		} catch (UnsupportedVersionException ex) {
			logger.error(ex.getMessage(), ex);
			
		} catch( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}		
		if( cartoonPreferences == null ) { // well, either no preferences yet or some error. initialize to defaults and return
			cartoonPreferences = new MSGlycanAnnotationCartoonPreferences();
			cartoonPreferences.loadDefaultOptions(); // we always go to the file for the default layout, orientations, and styles
		}
		return cartoonPreferences;
	}	
	
}

