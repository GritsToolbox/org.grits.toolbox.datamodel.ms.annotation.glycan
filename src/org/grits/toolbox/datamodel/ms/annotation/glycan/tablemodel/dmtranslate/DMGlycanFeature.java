package org.grits.toolbox.datamodel.ms.annotation.glycan.tablemodel.dmtranslate;

/**
 * Abstraction for the GlycanFeature class in the GRITS object model. Note that GlycanFeature extends Feature, and there 
 * are no new fields. Here they are just named glycan-centric for readability. 
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 * 
 */
public enum DMGlycanFeature
{		
	glycan_feature_id("Glycan Feature Id", "The auto-assigned ID of the glycan object assigned to a scan."),
	glycan_feature_type("Glycan Type", "The system-assigned type of glycan assigned."),
	glycan_feature_sequence("Glycan Sequence", "The sequence of the annotation object associated with this glycan."),
	glycan_feature_mz("Glycan m/z", "The observed m/z of the glycan."),
	glycan_feature_deviation("Glycan Mass Error", "The delta between the observed precursor and glycan m/zs in parts-per-million (ppm)."),	
	glycan_feature_charge("Glycan Charge", "The observed charge of the glycan (could differ from scan if annotation considers other charge states).");
	
    private String sLabel;
    private String sDescription;

    private DMGlycanFeature( String sLabel, String sDescription ) {
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

    public static DMGlycanFeature lookUp( String _sKey ) {
    	if ( glycan_feature_charge.name().equals(_sKey ) )
    		return glycan_feature_charge;
    	if ( glycan_feature_id.name().equals(_sKey ) )
    		return glycan_feature_id;
    	if ( glycan_feature_type.name().equals(_sKey ) )
    		return glycan_feature_type;
    	if ( glycan_feature_sequence.name().equals(_sKey ) )
    		return glycan_feature_sequence;
    	if ( glycan_feature_mz.name().equals(_sKey ) )
    		return glycan_feature_mz;
    	if ( glycan_feature_deviation.name().equals(_sKey ) )
    		return glycan_feature_deviation;
    	return null;
    } 
    
}
