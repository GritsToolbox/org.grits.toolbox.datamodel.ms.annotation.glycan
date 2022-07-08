package org.grits.toolbox.datamodel.ms.annotation.glycan.preference;

import org.apache.log4j.Logger;
import org.grits.toolbox.core.datamodel.UnsupportedVersionException;
import org.grits.toolbox.core.preference.share.PreferenceEntity;

import org.grits.toolbox.datamodel.ms.tablemodel.FillTypes;
import org.grits.toolbox.display.control.table.preference.TableViewerPreference;

public class MSGlycanAnnotationSummaryViewerPreferenceLoader {
	private static final Logger logger = Logger.getLogger(MSGlycanAnnotationSummaryViewerPreferenceLoader.class);

	public static MSGlycanAnnotationSummaryViewerPreference getTableViewerPreference(int _iMSLevel, FillTypes fillType )  {
		MSGlycanAnnotationSummaryViewerPreference preferences = null;
		try {
			PreferenceEntity preferenceEntity = MSGlycanAnnotationSummaryViewerPreference.getPreferenceEntity(_iMSLevel, fillType); 
			if( preferenceEntity == null ) { // previous version
				preferences = MSGlycanAnnotationSummaryViewerPreferencePreVersion.getTableViewerPreferencesPreVersioning(_iMSLevel, fillType);
				
				if( preferences != null ) {
					MSGlycanAnnotationSummaryViewerPreferencePreVersion.removeElements(_iMSLevel, fillType);
				}
				if( preferences.getColumnSettings() != null ) {				
					preferences.writePreference();
				}
			} else {
				preferences = (MSGlycanAnnotationSummaryViewerPreference) TableViewerPreference.getTableViewerPreference(preferenceEntity, 
						MSGlycanAnnotationSummaryViewerPreference.class);
			}
		} catch (UnsupportedVersionException ex) {
			logger.error(ex.getMessage(), ex);
			
		} catch( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}		
		if( preferences == null ) { // well, either no preferences yet or some error. initialize to defaults and return
			preferences = new MSGlycanAnnotationSummaryViewerPreference();
			preferences.setFillType(fillType);
			preferences.setMSLevel(_iMSLevel);
			preferences.setColumnSettings("");
		}
		return preferences;
	}
	
}
