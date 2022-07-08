package org.grits.toolbox.datamodel.ms.annotation.glycan.preference.cartoon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.log4j.Logger;
import org.eurocarbdb.application.glycanbuilder.GraphicOptions;
import org.grits.toolbox.core.datamodel.UnsupportedVersionException;
import org.grits.toolbox.core.preference.PreferenceHandler;
import org.grits.toolbox.core.preference.share.PreferenceEntity;
import org.grits.toolbox.core.preference.share.PreferenceReader;
import org.grits.toolbox.core.preference.share.PreferenceWriter;
import org.grits.toolbox.core.utilShare.XMLUtils;

import org.grits.toolbox.datamodel.ms.annotation.glycan.Activator;

@XmlRootElement(name = "msGlycanAnnotationCartoonPreferences")
public class MSGlycanAnnotationCartoonPreferences {

	private static final Logger logger = Logger.getLogger(MSGlycanAnnotationCartoonPreferences.class);
	private static final String PREFERENCE_NAME_ALL = "org.grits.toolbox.datamodel.ms.annotation.glycan.preference.MSGlycanAnnotationCartoonPreferences.all";
	private static final String CURRENT_VERSION = "1.0";

	private static final String DEFAULT_IMAGE_LAYOUT_FILE = "cartoonTypes.imageLayout.xml";
	private static final String DEFAULT_IMAGE_ORIENTATION_FILE = "cartoonTypes.imageOrientation.xml";
	private static final String DEFAULT_IMAGE_STYLE_FILE = "cartoonTypes.imageStyle.xml";


	/* these values will get initialized either when loading default options or when reading preferences */
	private String imageLayout = null;
	private String imageStyle = null;
	private String orientation = null;

	/* these values are the defaults and can get overridden by preferences */
	private String scaleFactor = "0.5";
	private boolean showInfo = true;
	private boolean showMasses = false;
	private boolean showRedEnd = true;

	private Set<String> allLayouts = null;
	private Set<String> allOrientations = null;
	private Set<String> allStyles = null;

	/* the list of layouts, orientations, and styles is not user-modifiable, so it is always read from a static file. No need to store as a preference */
	public Set<String> getAllLayouts() {
		return allLayouts;
	}
	@XmlTransient
	public void setAllLayouts(Set<String> allLayouts) {
		this.allLayouts = allLayouts;
	}

	public Set<String> getAllOrientations() {
		return allOrientations;
	}
	@XmlTransient
	public void setAllOrientations(Set<String> allOrientations) {
		this.allOrientations = allOrientations;
	}

	public Set<String> getAllStyles() {
		return allStyles;
	}
	@XmlTransient
	public void setAllStyles(Set<String> allStyles) {
		this.allStyles = allStyles;
	}

	public String getOrientation() {
		return orientation;
	}
	@XmlAttribute(name="orientation")
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public boolean isShowInfo() {
		return showInfo;
	}
	@XmlAttribute(name="showInfo")
	public void setShowInfo(boolean showInfo) {
		this.showInfo = showInfo;
	}

	public boolean isShowMasses() {
		return showMasses;
	}
	@XmlAttribute(name="showMasses")
	public void setShowMasses(boolean showMasses) {
		this.showMasses = showMasses;
	}

	public boolean isShowRedEnd() {
		return showRedEnd;
	}
	@XmlAttribute(name="showRedEnd")
	public void setShowRedEnd(boolean showRedEnd) {
		this.showRedEnd = showRedEnd;
	}

	public String getImageScaleFactor() {
		return scaleFactor;
	}
	@XmlAttribute(name="scaleFactor")
	public void setImageScaleFactor(String scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	public String getImageLayout() {
		return imageLayout;
	}
	@XmlAttribute(name="imageLayout")
	public void setImageLayout(String imageLayout) {
		this.imageLayout = imageLayout;
	}

	public String getImageStyle() {
		return imageStyle;
	}
	@XmlAttribute(name="imageStyle")
	public void setImageStyle(String imageStyle) {
		this.imageStyle = imageStyle;
	}

	public static int getGWBOrientationCode(String orientation) {
		if( orientation == null || orientation.equals("RL"))
		{
			return GraphicOptions.RL;
		}
		else if(orientation.equals("LR"))
		{
			return GraphicOptions.LR;
		}
		else if(orientation.equals("TB"))
		{
			return GraphicOptions.TB;
		}
		else if(orientation.equals("BT"))
		{
			return GraphicOptions.BT;
		}
		else
		{
			Exception e = new Exception("Invalid orientation.");
			logger.error(e.getMessage(), e);
			return GraphicOptions.RL;
		}
	}

	public static String getGWBlayoutString(String layout) throws Exception {
		if(layout.equals("SNFG"))
		{
			return "snfg";
		}
		else if(layout.equals("CFG"))
		{
			return GraphicOptions.NOTATION_CFG;
		}
		else if(layout.equals("Oxford"))
		{
			return GraphicOptions.NOTATION_UOXF;
		}
		else if(layout.equals("Text"))
		{
			return GraphicOptions.NOTATION_TEXT;
		}
		else if(layout.equals("CFG-BW"))
		{
			return GraphicOptions.NOTATION_CFGBW;
		}
		else if(layout.equals("Oxford-Color"))
		{
			return GraphicOptions.NOTATION_UOXFCOL;
		}
		else if(layout.equals("CFG-Hybrid"))
		{
			return GraphicOptions.NOTATION_CFGLINK;
		}
		else
		{
			Exception e = new Exception("Invalid layout.");
			logger.error(e.getMessage(), e);
			return "snfg";
		}
	}

	public static String getGWBStyleString(String layout) throws Exception {
		if(layout.equals("Normal"))
		{
			return GraphicOptions.DISPLAY_NORMAL;
		}
		else if(layout.equals("Compact"))
		{
			return GraphicOptions.DISPLAY_COMPACT;
		}
		else if(layout.equals("Complete"))
		{
			return GraphicOptions.DISPLAY_NORMALINFO;
		}
		else
		{
			Exception e = new Exception("Invalid style.");
			logger.error(e.getMessage(), e);
			return GraphicOptions.DISPLAY_NORMALINFO;
		}
	}


	private static MSGlycanAnnotationCartoonPreferences getCartoonSettingsFromXML(String xmlString)
	{
		MSGlycanAnnotationCartoonPreferences cartoonSettings = (MSGlycanAnnotationCartoonPreferences) XMLUtils.getObjectFromXML(xmlString, MSGlycanAnnotationCartoonPreferences.class);
		return cartoonSettings;
	}

	public static String marshalXML(MSGlycanAnnotationCartoonPreferences object)
	{
		String xmlString = XMLUtils.marshalObjectXML(object);
		return xmlString;
	}

	private String loadDefaultOptions( Set<String> _sAllPreferences, String _sDefaultPreferenceFile ) {
		String sDefault = null;
		try {
			HashMap<String, Boolean> optionMap = PreferenceHandler.getPreferenceValues(_sDefaultPreferenceFile, Activator.PLUGIN_ID);
			for(String option : optionMap.keySet()) {
				_sAllPreferences.add(option);
				if(optionMap.get(option) ) {
					sDefault = option;
				}
			}
		} catch (Exception ex) {
			logger.error("Error loading default options from xml files.", ex);
			throw ex;
		}
		return sDefault;
	}

	public void loadDefaultOptions() {
		allLayouts = new HashSet<String>();
		allOrientations = new HashSet<String>();
		allStyles = new HashSet<String>();
		try	{
			// first load all of the options that go in controls. These are read from xml files in the plugin
			String sDefault = null;
			try {
				sDefault = loadDefaultOptions(allLayouts, DEFAULT_IMAGE_LAYOUT_FILE);
				if( this.imageLayout == null ) {
					this.imageLayout = sDefault;
				}
			} catch( Exception ex) {
				throw new Exception("Unable to load layout preferences", ex);
			}
			try {
				sDefault = loadDefaultOptions(allOrientations, DEFAULT_IMAGE_ORIENTATION_FILE);
				if( this.orientation == null ) {
					this.orientation = sDefault;
				}
			} catch( Exception ex) {
				throw new Exception("Unable to load orientation preferences", ex);
			}
			try {
				sDefault = loadDefaultOptions(allStyles, DEFAULT_IMAGE_STYLE_FILE);
				if( this.imageStyle == null ) {
					this.imageStyle = sDefault;
				}
			} catch( Exception ex) {
				throw new Exception("Unable to load style preferences", ex);
			}
		} catch (Exception ex) {
			logger.error("Error loading default options", ex);
		}
	}

	public static MSGlycanAnnotationCartoonPreferences getCartoonSettings(PreferenceEntity preferenceEntity) throws UnsupportedVersionException
	{
		MSGlycanAnnotationCartoonPreferences preferenceSettings = null;
		if(preferenceEntity != null)
		{
			preferenceSettings = MSGlycanAnnotationCartoonPreferences.getCartoonSettingsFromXML(preferenceEntity.getValue());
			preferenceSettings.loadDefaultOptions(); // we always go to the file for the default layout, orientations, and styles
		}
		return preferenceSettings;
	}

	public boolean saveValues()
	{
		PreferenceEntity preferenceEntity = new PreferenceEntity(PREFERENCE_NAME_ALL);
		preferenceEntity.setVersion(CURRENT_VERSION);
		preferenceEntity.setValue(MSGlycanAnnotationCartoonPreferences.marshalXML(this));
		return PreferenceWriter.savePreference(preferenceEntity);
	}

	public static PreferenceEntity getPreferenceEntity() throws UnsupportedVersionException {
		PreferenceEntity preferenceEntity = PreferenceReader.getPreferenceByName(PREFERENCE_NAME_ALL);
		return preferenceEntity;
	}
	public static String getPreferenceID() {
		return PREFERENCE_NAME_ALL;
	}

}
