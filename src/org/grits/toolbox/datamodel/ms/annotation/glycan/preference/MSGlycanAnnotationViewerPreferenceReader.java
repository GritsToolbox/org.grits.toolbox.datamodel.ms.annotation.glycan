package org.grits.toolbox.datamodel.ms.annotation.glycan.preference;

import org.apache.log4j.Logger;

import org.grits.toolbox.datamodel.ms.annotation.glycan.tablemodel.dmtranslate.DMGlycanAnnotation;
import org.grits.toolbox.datamodel.ms.annotation.preference.MSAnnotationViewerPreferenceReader;
import org.grits.toolbox.display.control.table.datamodel.GRITSColumnHeader;

public class MSGlycanAnnotationViewerPreferenceReader extends MSAnnotationViewerPreferenceReader {
	private static final Logger logger = Logger.getLogger(MSGlycanAnnotationViewerPreferenceReader.class);

//	@Override
//	protected TableViewerColumnSettings getNewTableSettings() {
//		TableViewerColumnSettings spf = new MSGlycanAnnotationTableViewerSettings();
//		return spf;
//	}
//
//	@Override
//	protected TableViewerColumnSettings getTableViewerEntity() {
//		MSGlycanAnnotationTableViewerSettings tvs = (MSGlycanAnnotationTableViewerSettings) super.getTableViewerEntity();
//		if( tvs != null ) {
//			try {
//				boolean bShowExtra = new Boolean( element.getAttributeValue("bShowExtraInfo") );
//				tvs.setShowExtraInfo(bShowExtra);
//			} catch(  Exception e )  {
//				logger.error("Error writing preferences in MSGlycanAnnotationViewerPreferenceReader.getTableViewerEntity", e);
//				e.printStackTrace();
//			}
//		}
//		return tvs;
//	}
//	
//	@Override
//	public SimianColumnHeader getColumnHeader(String _sKey) {		
//		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_glycanId.name() ) ) {
//			return new SimianColumnHeader( DMGlycanAnnotation.glycan_annotation_glycanId.getLabel(), DMGlycanAnnotation.glycan_annotation_glycanId.name());
//		}
//		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_perDerivatisationType.name() ) ) {
//			return new SimianColumnHeader( DMGlycanAnnotation.glycan_annotation_perDerivatisationType.getLabel(), DMGlycanAnnotation.glycan_annotation_perDerivatisationType.name());
//		}
//		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_composition.name() ) ) {
//			return new SimianColumnHeader( DMGlycanAnnotation.glycan_annotation_composition.getLabel(), DMGlycanAnnotation.glycan_annotation_composition.name());
//		}
//		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_sequenceGWB.name() ) ) {
//			return new SimianColumnHeader( DMGlycanAnnotation.glycan_annotation_sequenceGWB.getLabel(), DMGlycanAnnotation.glycan_annotation_sequenceGWB.name());
//		}
//		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_glycancartoon.name() ) ) {
//			return new SimianColumnHeader(DMGlycanAnnotation.glycan_annotation_glycancartoon.getLabel(), DMGlycanAnnotation.glycan_annotation_glycancartoon.name());
//		}
//		if ( _sKey.equals(DMGlycanAnnotation.glycan_annotation_fragmentcartoon.name() ) ) {
//			return new SimianColumnHeader(DMGlycanAnnotation.glycan_annotation_fragmentcartoon.getLabel(), DMGlycanAnnotation.glycan_annotation_fragmentcartoon.name());
//		}
//		return super.getColumnHeader(_sKey);
//	}
//	
}
