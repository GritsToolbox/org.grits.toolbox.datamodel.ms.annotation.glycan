package org.grits.toolbox.datamodel.ms.annotation.glycan.preference;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.grits.toolbox.core.preference.share.PreferenceWriter;
import org.jdom.JDOMException;

import org.grits.toolbox.datamodel.ms.annotation.preference.MSAnnotationViewerPreferencePreVersion;
import org.grits.toolbox.datamodel.ms.tablemodel.FillTypes;
import org.grits.toolbox.display.control.table.preference.TableViewerPreference;

public class MSGlycanAnnotationViewerPreferencePreVersion extends MSAnnotationViewerPreferencePreVersion {
	private static final Logger logger = Logger.getLogger(MSGlycanAnnotationViewerPreferencePreVersion.class);
	public static final String PREVIOUS_PREFERENCE_ID = "org.grits.toolbox.datamodel.ms.annotation.glycan.preference.MSGlycanAnnotationViewerPreference";
	
	public MSGlycanAnnotationViewerPreferencePreVersion()
	{
		super();
	}
	
	public static boolean removeElements(int _iMSLevel, FillTypes fillType) {
		try {
			PreferenceWriter.deletePreference(getPreferenceID(_iMSLevel, fillType));
			return true;
		} catch( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}
		return false;
	}

	protected static String getPreferenceID(int _iMSLevel, FillTypes fillType) {
		String sAdder = "";
		String sName = PREVIOUS_PREFERENCE_ID;
		if ( fillType == FillTypes.Scans ) {
			sAdder = ".Scans";
		}
		else if ( fillType == FillTypes.PeakList ) {
			sAdder = ".Peaks";
		}
		else if ( fillType == FillTypes.PeaksWithFeatures ) {
			sAdder = ".PeaksWithFeatures";
		}
		sName += sAdder;
		sName += ".MSLevel" + (_iMSLevel - 1);
		return sName;
	}
	

	public static MSGlycanAnnotationViewerPreference getTableViewerPreferencesPreVersioning(int _iMSLevel, FillTypes fillType) {
		MSGlycanAnnotationViewerPreferenceReader reader = new MSGlycanAnnotationViewerPreferenceReader();
		MSGlycanAnnotationViewerPreference preference = new MSGlycanAnnotationViewerPreference();
		try {
			String colSettings = reader.getTableViewerColumnSettings(MSGlycanAnnotationViewerPreferencePreVersion.getPreferenceID(_iMSLevel, fillType));
			preference.setColumnSettings(colSettings);
			TableViewerPreference.initTableViewerColumnSettings(preference);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		} catch (JDOMException e) {
			logger.error(e.getMessage(),e);
		}
		preference.setFillType(fillType);
		preference.setMSLevel(_iMSLevel);
//		preference.setPreferenceSettings(columnSettings);
//		preference.setColumnSettingsString(columnSettings.toString());
		return preference;
	}	
}