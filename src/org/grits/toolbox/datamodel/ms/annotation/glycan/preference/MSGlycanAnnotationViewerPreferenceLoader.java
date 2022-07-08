package org.grits.toolbox.datamodel.ms.annotation.glycan.preference;

import org.apache.log4j.Logger;
import org.grits.toolbox.core.datamodel.UnsupportedVersionException;
import org.grits.toolbox.core.preference.share.PreferenceEntity;

import org.grits.toolbox.datamodel.ms.tablemodel.FillTypes;
import org.grits.toolbox.display.control.table.preference.TableViewerPreference;

public class MSGlycanAnnotationViewerPreferenceLoader {
	private static final Logger logger = Logger.getLogger(MSGlycanAnnotationViewerPreferenceLoader.class);

	public static MSGlycanAnnotationViewerPreference getTableViewerPreference(int _iMSLevel, FillTypes fillType )  {
		MSGlycanAnnotationViewerPreference preferences = null;
		try {
			PreferenceEntity preferenceEntity = MSGlycanAnnotationViewerPreference.getPreferenceEntity(_iMSLevel, fillType); 
			if( preferenceEntity == null ) { // previous version
				preferences = MSGlycanAnnotationViewerPreferencePreVersion.getTableViewerPreferencesPreVersioning(_iMSLevel, fillType);
				
				if( preferences != null ) {
					MSGlycanAnnotationViewerPreferencePreVersion.removeElements(_iMSLevel, fillType);
				}
				if( preferences.getColumnSettings() != null ) {				
					preferences.writePreference();
				}
			} else {
				preferences = (MSGlycanAnnotationViewerPreference) TableViewerPreference.getTableViewerPreference(preferenceEntity, MSGlycanAnnotationViewerPreference.class);
			}
		} catch (UnsupportedVersionException ex) {
			logger.error(ex.getMessage(), ex);
			
		} catch( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}		
		if( preferences == null ) { // well, either no preferences yet or some error. initialize to defaults and return
			preferences = new MSGlycanAnnotationViewerPreference();
			preferences.setFillType(fillType);
			preferences.setMSLevel(_iMSLevel);
			preferences.setColumnSettings("");
		}
		return preferences;
	}
	
}
