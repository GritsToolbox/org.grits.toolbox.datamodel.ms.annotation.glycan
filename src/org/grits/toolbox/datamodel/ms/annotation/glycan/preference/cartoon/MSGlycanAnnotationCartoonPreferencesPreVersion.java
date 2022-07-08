package org.grits.toolbox.datamodel.ms.annotation.glycan.preference.cartoon;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.grits.toolbox.core.preference.project.UtilityPreferenceValue;
import org.grits.toolbox.core.preference.share.PreferenceEntity;
import org.grits.toolbox.core.preference.share.PreferenceReader;
import org.grits.toolbox.core.preference.share.PreferenceWriter;
import org.jdom.Element;
import org.jdom.JDOMException;

public class MSGlycanAnnotationCartoonPreferencesPreVersion {
	private static final Logger logger = Logger.getLogger(MSGlycanAnnotationCartoonPreferencesPreVersion.class);
	/* Legacy info. The types were removed */
	private static final String PREVIOUS_CARTOON_PREFERENCE_ID = "org.grits.toolbox.datamodel.ms.annotation.glycan.preference.cartoon.MSGlycanAnnotationCartoonPreference";
	private static final String PREVIOUS_IMAGE_LAYOUT_ID = "org.grits.toolbox.datamodel.ms.annotation.glycan.preference.cartoon.CartoonTypes.imageLayout";
	private static final String PREVIOUS_IMAGE_ORIENTATION_ID = "org.grits.toolbox.datamodel.ms.annotation.glycan.preference.cartoon.CartoonTypes.imageOrientation";
	private static final String PREVIOUS_IMAGE_STYLE_ID = "org.grits.toolbox.datamodel.ms.annotation.glycan.preference.cartoon.CartoonTypes.imageStyle";

	public static boolean removeElements() {
		try {
			PreferenceWriter.deletePreference(PREVIOUS_CARTOON_PREFERENCE_ID);
			PreferenceWriter.deletePreference(PREVIOUS_IMAGE_LAYOUT_ID);
			PreferenceWriter.deletePreference(PREVIOUS_IMAGE_ORIENTATION_ID);
			PreferenceWriter.deletePreference(PREVIOUS_IMAGE_STYLE_ID);
			return true;
		} catch( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}
		return false;
	}


	public static MSGlycanAnnotationCartoonPreferences getCartoonPreferences(PreferenceEntity preferenceEntity) {
		MSGlycanAnnotationCartoonPreferences previousPreferences = null;
		try {
			previousPreferences = MSGlycanAnnotationCartoonPreferencesPreVersion.getMSAnnotationCartoonPreference();
			if( previousPreferences != null ) { // it was pre-versioning preferences...clean up and save
				Element preferenceElement = PreferenceReader.getPreferenceElement(PREVIOUS_IMAGE_LAYOUT_ID);
				if (preferenceElement.getAttributeValue("values") != null && preferenceElement.getAttributeValue("values").length() != 0 ) {
					previousPreferences.setAllLayouts( UtilityPreferenceValue.getPreversioningValues(preferenceElement) ); // do we need to do this? we're reading from file now, but this is how we'd do it if it were dynamic
					String selected = UtilityPreferenceValue.getPreversioningSelected(preferenceElement);				
					previousPreferences.setImageLayout( selected );

				} 
				preferenceElement = PreferenceReader.getPreferenceElement(PREVIOUS_IMAGE_ORIENTATION_ID);
				if (preferenceElement.getAttributeValue("values") != null && preferenceElement.getAttributeValue("values").length() != 0 ) {
					previousPreferences.setAllOrientations( UtilityPreferenceValue.getPreversioningValues(preferenceElement) ); // do we need to do this? we're reading from file now, but this is how we'd do it if it were dynamic
					String selected = UtilityPreferenceValue.getPreversioningSelected(preferenceElement);				
					previousPreferences.setOrientation( selected );

				} 
				preferenceElement = PreferenceReader.getPreferenceElement(PREVIOUS_IMAGE_STYLE_ID);
				if (preferenceElement.getAttributeValue("values") != null && preferenceElement.getAttributeValue("values").length() != 0 ) {
					previousPreferences.setAllStyles( UtilityPreferenceValue.getPreversioningValues(preferenceElement) ); // do we need to do this? we're reading from file now, but this is how we'd do it if it were dynamic
					String selected = UtilityPreferenceValue.getPreversioningSelected(preferenceElement);				
					previousPreferences.setImageStyle( selected );
				} 
			}
		} catch( Exception ex ) {
			logger.error(ex.getMessage(), ex);
			previousPreferences = null;
		}
		return previousPreferences;
	}
	
	private static MSGlycanAnnotationCartoonPreferences getMSAnnotationCartoonPreference() throws IOException, JDOMException
	{
		Element element = PreferenceReader.getPreferenceElement(PREVIOUS_CARTOON_PREFERENCE_ID);
		if(element == null)
		{
			return null;
		}
		MSGlycanAnnotationCartoonPreferences spf = new MSGlycanAnnotationCartoonPreferences();
		if (element.getAttributeValue("scalingFactor") != null && element.getAttributeValue("scalingFactor").length() != 0 ) {
			spf.setImageScaleFactor( element.getAttributeValue("scalingFactor") );
		} else {
			spf.setImageScaleFactor("0.5");
		}
		if (element.getAttributeValue("showMasses") != null && element.getAttributeValue("showMasses").length() != 0 ) {
			spf.setShowMasses( element.getAttributeValue("showMasses").equals(Boolean.toString(true)) );
		} else {
			spf.setShowMasses(false);
		}
		if (element.getAttributeValue("showRedEnd") != null && element.getAttributeValue("showRedEnd").length() != 0 ) {
			spf.setShowRedEnd( element.getAttributeValue("showRedEnd").equals(Boolean.toString(true)) );
		} else {
			spf.setShowRedEnd(true);
		}
		return spf;
	}
	

}
