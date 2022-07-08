package org.grits.toolbox.datamodel.ms.annotation.glycan.preference;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;
import org.grits.toolbox.core.datamodel.UnsupportedVersionException;
import org.grits.toolbox.core.preference.share.PreferenceEntity;
import org.grits.toolbox.core.preference.share.PreferenceReader;
import org.grits.toolbox.core.preference.share.PreferenceWriter;

import org.grits.toolbox.datamodel.ms.annotation.glycan.tablemodel.dmtranslate.DMGlycanAnnotation;
import org.grits.toolbox.datamodel.ms.annotation.glycan.tablemodel.dmtranslate.DMGlycanFeature;
import org.grits.toolbox.datamodel.ms.annotation.preference.MSAnnotationViewerPreference;
import org.grits.toolbox.datamodel.ms.annotation.tablemodel.dmtranslate.DMFeature;
import org.grits.toolbox.datamodel.ms.tablemodel.FillTypes;
import org.grits.toolbox.datamodel.ms.tablemodel.dmtranslate.DMPeak;
import org.grits.toolbox.display.control.table.datamodel.GRITSColumnHeader;
import org.grits.toolbox.display.control.table.preference.TableViewerPreferenceReader;

@XmlRootElement(name="msGlycanAnnotationSummaryViewerPreference")
public class MSGlycanAnnotationSummaryViewerPreference extends MSAnnotationViewerPreference {
	private static final Logger logger = Logger.getLogger(MSGlycanAnnotationSummaryViewerPreference.class);
	private static final String PREFERENCE_NAME_ALL = "org.grits.toolbox.datamodel.ms.annotation.glycan.preference.MSGlycanAnnotationSummaryViewerPreference";
	protected boolean bHideCommonFragments = false;
	/*
	 * Version history:
	 * 1.0 - Original release w/ versioning
	 * 1.1 - Committed 03/08/16. Re-versioned because parent class "TableViewerPreference" was modified
	 * 1.2 - Added ability to hide/show common fragments
	 */
	private static final String CURRENT_VERSION = "1.2";

	public MSGlycanAnnotationSummaryViewerPreference() {
		super();
	}
	
	public MSGlycanAnnotationSummaryViewerPreference(int _iMSLevel, FillTypes fillType) {
		super(_iMSLevel, fillType);
	}

	/**
	 * @return true if the user wants to hide fragments common to all structures in the Summary table, false otherwise
	 */
	public boolean isHideCommonFragments() {
		return this.bHideCommonFragments;
	}
	/**
	 * @param _bVal
	 * 		sets whether the user wants to hide fragments common to all structures in the Summary table
	 */
	@XmlAttribute(name="hideCommonFragments")
	public void setHideCommonFragments( boolean _bVal ) {
		this.bHideCommonFragments = _bVal;
	}
	
	@Override
	protected TableViewerPreferenceReader getNewReader() {
		return new MSGlycanAnnotationSummaryViewerPreferenceReader();
	}
	
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
		sName += sAdder;
		sName += ".MSLevel" + (_iMSLevel - 1);
		return sName;
	}
	
	public static PreferenceEntity getPreferenceEntity( int _iMSLevel, FillTypes fillType  ) throws UnsupportedVersionException {
		PreferenceEntity preferenceEntity = PreferenceReader.getPreferenceByName(MSGlycanAnnotationSummaryViewerPreference.getPreferenceID(_iMSLevel, fillType));
		return preferenceEntity;
	}
	
	
	@Override
	protected String getCurrentVersion() {
		return MSGlycanAnnotationSummaryViewerPreference.CURRENT_VERSION;
	}

	@Override
	public boolean writePreference() {
		PreferenceEntity preferenceEntity = new PreferenceEntity(MSGlycanAnnotationSummaryViewerPreference.getPreferenceID(getMSLevel(), getFillType()));
		preferenceEntity.setVersion(getCurrentVersion());
		preferenceEntity.setValue(marshalXML());
		return PreferenceWriter.savePreference(preferenceEntity);
	}
	
	@Override
	public GRITSColumnHeader getColumnHeader(String _sKey) {		
			
		if ( _sKey.equals( DMPeak.peak_mz.name()) ) {
			GRITSColumnHeader header = new GRITSColumnHeader(DMPeak.peak_mz.getLabel(), DMPeak.peak_mz.name());
			header.setIsGrouped(false);
			return header;
		}
		if ( _sKey.equals( DMPeak.peak_id.name()) ) {
			GRITSColumnHeader header = new GRITSColumnHeader(DMPeak.peak_id.getLabel(), DMPeak.peak_id.name());
			header.setIsGrouped(false);
			return header;
		}
		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_glycanId.name()) ) {
			return new GRITSColumnHeader(  DMGlycanAnnotation.glycan_annotation_glycanId.getLabel(), 
					DMGlycanAnnotation.glycan_annotation_glycanId.name());
		}
		
		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_glytoucanid.name()) ) {
			return new GRITSColumnHeader(  DMGlycanAnnotation.glycan_annotation_glytoucanid.getLabel(), 
					DMGlycanAnnotation.glycan_annotation_glytoucanid.name());
		}

		// Note that this is how I'm overriding "Feature Elements" to "Glycan Elements". I'm leaving the key the same
		// but using the DMGlycanFeature label.
		if ( _sKey.equals(DMFeature.feature_sequence.name() ) ) {
			return new GRITSColumnHeader( DMGlycanFeature.glycan_feature_sequence.getLabel(), DMFeature.feature_sequence.name() );
		}		
		if ( _sKey.equals(DMGlycanFeature.glycan_feature_sequence.name() ) ) {
			return new GRITSColumnHeader( DMGlycanFeature.glycan_feature_sequence.getLabel(), DMFeature.feature_sequence.name() );
		}		
		
		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_glycancartoon.name()) ) {
			return new GRITSColumnHeader(DMGlycanAnnotation.glycan_annotation_glycancartoon.getLabel(), 
					DMGlycanAnnotation.glycan_annotation_glycancartoon.name());
		}	
		
		if ( _sKey.equals(DMFeature.feature_id.name() ) ) {
			return new GRITSColumnHeader( DMGlycanFeature.glycan_feature_id.getLabel(), DMFeature.feature_id.name() );
		}		
		if ( _sKey.equals(DMGlycanFeature.glycan_feature_id.name() ) ) {
			return new GRITSColumnHeader( DMGlycanFeature.glycan_feature_id.getLabel(), DMFeature.feature_id.name() );
		}		

		if ( _sKey.equals(DMFeature.feature_charge.name() ) ) {
			return new GRITSColumnHeader( DMGlycanFeature.glycan_feature_charge.getLabel(), DMFeature.feature_charge.name() );
		}		
		if ( _sKey.equals(DMGlycanFeature.glycan_feature_charge.name() ) ) {
			return new GRITSColumnHeader( DMGlycanFeature.glycan_feature_charge.getLabel(), DMFeature.feature_charge.name() );
		}		
		return super.getColumnHeader(_sKey);
	}
	
}