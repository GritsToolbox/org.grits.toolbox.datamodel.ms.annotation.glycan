package org.grits.toolbox.datamodel.ms.annotation.glycan.tablemodel.dmtranslate;

/**
 * Abstraction for the simple elements of the GlycanSettings class in the GRITS object model.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
public enum DMGlycanSettings
{
	glycan_settings_perDerivatisationType("Derivatization Type", "The name of the derivitization method, if used, to create the glycan sample."),
	glycan_settings_maxNumOfCleavages("# Missed Cleavages", "The maximum number of missed cleavages for glycan annotation of an MS experiment."),
	glycan_settings_maxNumOfCrossRingCleavages("Max # Cross-ring Cleavages", "The maximum number of missed cross-ring cleavages for glycan annotation of an MS experiment."); 

    private String sLabel;
    private String sDescription;

    private DMGlycanSettings( String sLabel, String sDescription ) {
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
    
    public static DMGlycanSettings lookUp( String _sKey ) {
    	if ( glycan_settings_perDerivatisationType.name().equals(_sKey ) )
    		return glycan_settings_perDerivatisationType;
    	else if ( glycan_settings_maxNumOfCleavages.name().equals(_sKey) ) 
    		return glycan_settings_maxNumOfCleavages;
    	else if ( glycan_settings_maxNumOfCrossRingCleavages.name().equals(_sKey) ) 
    		return glycan_settings_maxNumOfCrossRingCleavages;
    	
    	return null;
    } 
    
}
