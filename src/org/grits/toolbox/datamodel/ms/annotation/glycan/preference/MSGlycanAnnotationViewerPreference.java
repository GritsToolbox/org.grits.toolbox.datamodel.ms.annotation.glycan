package org.grits.toolbox.datamodel.ms.annotation.glycan.preference;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;
import org.grits.toolbox.core.datamodel.UnsupportedVersionException;
import org.grits.toolbox.core.preference.share.PreferenceEntity;
import org.grits.toolbox.core.preference.share.PreferenceReader;
import org.grits.toolbox.core.preference.share.PreferenceWriter;
import org.grits.toolbox.datamodel.ms.annotation.glycan.tablemodel.dmtranslate.DMGlycanAnnotation;
import org.grits.toolbox.datamodel.ms.annotation.preference.MSAnnotationViewerPreference;
import org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate.DMFeature;
import org.grits.toolbox.datamodel.ms.tablemodel.FillTypes;
import org.grits.toolbox.display.control.table.datamodel.GRITSColumnHeader;
import org.grits.toolbox.ms.annotation.sugar.GlycanExtraInfo;

/**
 * Extends the MSAnnotation Viewer preferences to add elements that are relevant to 
 * MS Glycan Annotation of Mass Spec data.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
@XmlRootElement(name = "msGlycanAnnotationViewerPreference")
public class MSGlycanAnnotationViewerPreference extends MSAnnotationViewerPreference {
	private static final Logger logger = Logger.getLogger(MSGlycanAnnotationViewerPreference.class);
	private static final String PREFERENCE_NAME_ALL = "org.grits.toolbox.datamodel.ms.annotation.glycan.preference.MSGlycanAnnotationViewerPreference";
	/*
	 * Version history:
	 * 1.0 - Original release w/ versioning
	 * 1.1 - Committed 03/08/16. Re-versioned because parent class "TableViewerPreference" was modified
	 */
	private static final String CURRENT_VERSION = "1.1";
	protected boolean bShowExtraInfo = false;

	public MSGlycanAnnotationViewerPreference() {
		this( 0, FillTypes.Scans);
	}

	public MSGlycanAnnotationViewerPreference(int _iMSLevel, FillTypes fillType) {
		super(_iMSLevel, fillType);
	}

	/**
	 * @return true if the user wants to view the extra glycan info in the GRITS table, false otherwise
	 */
	public boolean getShowExtraInfo() {
		return this.bShowExtraInfo;
	}
	/**
	 * @param _bVal
	 * 		whether the user wants to view the extra glycan info in the GRITS table
	 */
	@XmlAttribute(name="showExtraInfo")
	public void setShowExtraInfo( boolean _bVal ) {
		this.bShowExtraInfo = _bVal;
	}

	/* (non-Javadoc)
	 * @see org.grits.toolbox.display.control.table.preference.TableViewerPreference#excludeColumn(java.lang.String, java.lang.String)
	 */
	@Override
	protected boolean excludeColumn(String _sColumnKey, String _sColumnLabel ) {
		/* added 03/10/16 to remove the Glycan Charge column generated by the GlycoVisitor */
		if( _sColumnKey.equals(GlycanExtraInfo.GLYCAN_CHARGE) ) {
			return true;
		} else if ( _sColumnKey.equals(DMFeature.feature_charge.name()) && 
				_sColumnLabel.equals(DMFeature.feature_charge.getLabel()) ) {
			return true; // skipping this now. it will get picked up w/ default column settings
		} else if ( _sColumnKey.equals(DMGlycanAnnotation.glycan_annotation_composition.name()) ) {
			return true;
		}
		return false;
	}

	/**
	 * Called to create the String ID of this MS Glycan Annotation GRITS table preference entry.
	 * @param _iMSLevel
	 * 		the MS Level of the GRITS Table
	 * @param _fillType
	 * 		the fill type of the GRITS Table
	 * @return an ID for the preference file for this GRITS Table
	 */
	protected static String getPreferenceID( int _iMSLevel, FillTypes fillType ) {
		String sAdder = "";
		String sName = PREFERENCE_NAME_ALL;
		if ( fillType == FillTypes.Scans ) {
			sAdder = ".Scans";
		}
		else if ( fillType == FillTypes.PeakList ) {
			sAdder = ".Peaks";
		}
		else if ( fillType == FillTypes.PeaksWithFeatures ) {
			sAdder = ".PeaksWithFeatures";
		}
		else if ( fillType == FillTypes.Selection ) {
			sAdder = ".Selection";
		}
		sName += sAdder;
		sName += ".MSLevel" + (_iMSLevel - 1);
		return sName;
	}

	/**
	 * @param _iMSLevel
	 * 		the MS Level of the GRITS Table
	 * @param _fillType
	 * 		the fill type of the GRITS Table
	 * @return the MSGlycanAnnotation PreferenceEntity for the GRITS Table with the specified MS level and fill type
	 * @throws UnsupportedVersionException
	 */
	public static PreferenceEntity getPreferenceEntity( int _iMSLevel, FillTypes fillType  ) throws UnsupportedVersionException {
		PreferenceEntity preferenceEntity = PreferenceReader.getPreferenceByName(MSGlycanAnnotationViewerPreference.getPreferenceID(_iMSLevel, fillType));
		return preferenceEntity;
	}

	/* (non-Javadoc)
	 * @see org.grits.toolbox.datamodel.ms.annotation.preference.MSAnnotationViewerPreference#getCurrentVersion()
	 */
	@Override
	protected String getCurrentVersion() {
		return MSGlycanAnnotationViewerPreference.CURRENT_VERSION;
	}

	/* (non-Javadoc)
	 * @see org.grits.toolbox.datamodel.ms.annotation.preference.MSAnnotationViewerPreference#writePreference()
	 */
	@Override
	public boolean writePreference() {
		PreferenceEntity preferenceEntity = new PreferenceEntity(MSGlycanAnnotationViewerPreference.getPreferenceID(getMSLevel(), getFillType()));
		preferenceEntity.setVersion(getCurrentVersion());
		preferenceEntity.setValue(marshalXML());
		return PreferenceWriter.savePreference(preferenceEntity);
	}

	/** 
	 * Creates MS Glycan Annotation column header objects (if the key is recognized). 
	 * If the key isn't recognized, call the super-class method to see if it is known there.
	 * @see org.grits.toolbox.datamodel.ms.annotation.preference.MSAnnotationViewerPreference#getColumnHeader(java.lang.String)
	 */
	@Override
	public GRITSColumnHeader getColumnHeader(String _sKey) {		
		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_glycanId.name() ) ) {
			return new GRITSColumnHeader( DMGlycanAnnotation.glycan_annotation_glycanId.getLabel(), DMGlycanAnnotation.glycan_annotation_glycanId.name());
		}
		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_glytoucanid.name()) ) {
			return new GRITSColumnHeader(  DMGlycanAnnotation.glycan_annotation_glytoucanid.getLabel(), 
					DMGlycanAnnotation.glycan_annotation_glytoucanid.name());
		}
		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_perDerivatisationType.name() ) ) {
			return new GRITSColumnHeader( DMGlycanAnnotation.glycan_annotation_perDerivatisationType.getLabel(), DMGlycanAnnotation.glycan_annotation_perDerivatisationType.name());
		}
		//		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_composition.name() ) ) {
		//			return new SimianColumnHeader( DMGlycanAnnotation.glycan_annotation_composition.getLabel(), DMGlycanAnnotation.glycan_annotation_composition.name());
		//		}
		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_sequenceGWB.name() ) ) {
			return new GRITSColumnHeader( DMGlycanAnnotation.glycan_annotation_sequenceGWB.getLabel(), DMGlycanAnnotation.glycan_annotation_sequenceGWB.name());
		}
		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_glycancartoon.name() ) ) {
			return new GRITSColumnHeader(DMGlycanAnnotation.glycan_annotation_glycancartoon.getLabel(), DMGlycanAnnotation.glycan_annotation_glycancartoon.name());
		}
		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_fragmentcartoon.name() ) ) {
			return new GRITSColumnHeader(DMGlycanAnnotation.glycan_annotation_fragmentcartoon.getLabel(), DMGlycanAnnotation.glycan_annotation_fragmentcartoon.name());
		}
		return super.getColumnHeader(_sKey);
	}	
}