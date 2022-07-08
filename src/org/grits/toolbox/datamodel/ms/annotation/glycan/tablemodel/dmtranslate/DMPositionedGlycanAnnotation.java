package org.grits.toolbox.datamodel.ms.annotation.glycan.tablemodel.dmtranslate;

/**
 * Abstraction for the DMPositionedGlycanAnnotation class in the GRITS object model. Note that DMPositionedGlycanAnnotation extends GlycanAnnotation.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
public enum DMPositionedGlycanAnnotation // extends _GlycanAnnotation
{
	positioned_glycan_annotation_id("Annotation Id", ""),
	positioned_glycan_annotation_type("Annotation Type", ""),
	positioned_glycan_annotation_sequence("Sequence", ""),
	
	positioned_glycan_annotation_glycanId("Glycan Id", ""),
	positioned_glycan_annotation_perDerivatisationType("Derivatization Type", ""),
	positioned_glycan_annotation_composition("Composition", ""),
	positioned_glycan_annotation_sequenceGWB("GlycoWorkbench Sequence", ""),
	positioned_glycan_annotation_cartoon("Cartoon", ""),
	
	positioned_glycan_annotation_position("Glycan Attachment Position", ""),
	positioned_glycan_annotation_glycanAttachmentType("Glycan Attachment Type", "");

    private String sLabel;
    private String sDescription;

    private DMPositionedGlycanAnnotation( String sLabel, String sDescription ) {
        this.sLabel = sLabel;
        this.sDescription = sDescription;
    }

    public String getDescription() {
 		return sDescription;
 	}

    public String getLabel() 
    {  
        return this.sLabel;  
    }	
    
    public static DMPositionedGlycanAnnotation lookUp( String _sKey ) {
    	if ( positioned_glycan_annotation_id.name().equals(_sKey ) )
    		return positioned_glycan_annotation_id;
    	else if ( positioned_glycan_annotation_type.name().equals(_sKey) ) 
    		return positioned_glycan_annotation_type;
    	else if ( positioned_glycan_annotation_sequence.name().equals(_sKey) ) 
    		return positioned_glycan_annotation_sequence;
    	else if ( positioned_glycan_annotation_glycanId.name().equals(_sKey) ) 
    		return positioned_glycan_annotation_glycanId;
    	else if ( positioned_glycan_annotation_perDerivatisationType.name().equals(_sKey) ) 
    		return positioned_glycan_annotation_perDerivatisationType;
    	else if ( positioned_glycan_annotation_composition.name().equals(_sKey) ) 
    		return positioned_glycan_annotation_composition;
    	else if ( positioned_glycan_annotation_sequenceGWB.name().equals(_sKey) ) 
    		return positioned_glycan_annotation_sequenceGWB;
    	else if ( positioned_glycan_annotation_cartoon.name().equals(_sKey) ) 
    		return positioned_glycan_annotation_cartoon;
    	else if ( positioned_glycan_annotation_position.name().equals(_sKey) ) 
    		return positioned_glycan_annotation_position;
    	else if ( positioned_glycan_annotation_glycanAttachmentType.name().equals(_sKey) ) 
    		return positioned_glycan_annotation_glycanAttachmentType;
    	
    	return null;
    } 
    
}