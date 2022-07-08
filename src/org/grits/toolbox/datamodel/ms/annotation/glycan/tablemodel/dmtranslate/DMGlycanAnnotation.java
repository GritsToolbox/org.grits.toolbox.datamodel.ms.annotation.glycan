package org.grits.toolbox.datamodel.ms.annotation.glycan.tablemodel.dmtranslate;

/**
 * Abstraction for the simple elements of the GlycanAnnotation class in the GRITS object model. Note that GlycanAnnotation extends Annotation.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 * 
 */
public enum DMGlycanAnnotation // extends Annotation
{
	glycan_annotation_id("Annotation Id", "The auto-assigned ID of the object in the annotation database."),
	glycan_annotation_type("Annotation Type", "The system-assigned type of annotation used."),
	glycan_annotation_sequence("Sequence", "If applicable, the sequence representation of the object in the database."),
	glycan_annotation_glytoucanid("Glytoucan Id", "If applicable, accession number of the glycan in GlyTouCan"),
	glycan_annotation_glycanId("Glycan Id", "The user-assigned ID of the glycan in the database."),
	glycan_annotation_perDerivatisationType("Derivatization Type", "The name of the derivitization method, if used, to create the glycan sample."),
	glycan_annotation_composition("Composition", "The text-based composition notation of the glycan structure."),
	glycan_annotation_sequenceGWB("GlycoWorkbench Sequence", "The text-based sequence of the glycan in GlycoWorkbench format."),
	glycan_annotation_fragmentcartoon("Cartoon", "The image-based representation of the glycan structure."),
	glycan_annotation_glycancartoon("Cartoon", "The image-based representation of the glycan fragment.");

    private String sLabel;
    private String sDescription;

    private DMGlycanAnnotation( String sLabel, String sDescription ) {
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

    public static DMGlycanAnnotation lookUp( String _sKey ) {
    	if ( glycan_annotation_id.name().equals(_sKey ) )
    		return glycan_annotation_id;
    	else if ( glycan_annotation_type.name().equals(_sKey) ) 
    		return glycan_annotation_type;
    	else if ( glycan_annotation_sequence.name().equals(_sKey) ) 
    		return glycan_annotation_sequence;
    	else if ( glycan_annotation_glycanId.name().equals(_sKey) ) 
    		return glycan_annotation_glycanId;
    	else if ( glycan_annotation_perDerivatisationType.name().equals(_sKey) ) 
    		return glycan_annotation_perDerivatisationType;
    	else if ( glycan_annotation_composition.name().equals(_sKey) ) 
    		return glycan_annotation_composition;
    	else if ( glycan_annotation_sequenceGWB.name().equals(_sKey) ) 
    		return glycan_annotation_sequenceGWB;
    	else if ( glycan_annotation_glycancartoon.name().equals(_sKey) ) 
    		return glycan_annotation_glycancartoon;
    	else if ( glycan_annotation_fragmentcartoon.name().equals(_sKey) ) 
    		return glycan_annotation_fragmentcartoon;
    	else if ( glycan_annotation_glytoucanid.name().equals(_sKey) ) 
    		return glycan_annotation_glytoucanid;
//    	else if ( glycan_annotation_glycan_code.name().equals(_sKey ) )
//    		return glycan_annotation_glycan_code;
//    	else if ( glycan_annotation_residue_count.name().equals(_sKey) ) 
//    		return glycan_annotation_residue_count;
//    	else if ( glycan_annotation_charge.name().equals(_sKey) ) 
//    		return glycan_annotation_charge;
//    	else if ( glycan_annotation_bisection.name().equals(_sKey) ) 
//    		return glycan_annotation_bisection;
//    	else if ( glycan_annotation_nglycan_branches.name().equals(_sKey) ) 
//    		return glycan_annotation_nglycan_branches;
//    	else if ( glycan_annotation_nglycan_type.name().equals(_sKey) ) 
//    		return glycan_annotation_nglycan_type;
    	return null;
    } 
    
}
