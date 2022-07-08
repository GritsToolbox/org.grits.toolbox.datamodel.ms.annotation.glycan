package org.grits.toolbox.datamodel.ms.annotation.glycan.tablemodel.dmtranslate;

/**
 * Abstraction for the GlycanFilter (database used) class in the GRITS object model.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
public enum DMGlycanFilter
{
	glycan_filter_database("Glycan Database", "The name of the glycan database searched for annotation of an MS experiment."),
	glycan_filter_glycanType("Glycan Type Filter", "The type of the glycan database used for annotation of an MS experiment.");

    private String sLabel;
    private String sDescription;

    private DMGlycanFilter( String sLabel, String sDescription ) {
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
    
    public static DMGlycanFilter lookUp( String _sKey ) {
    	if ( glycan_filter_database.name().equals(_sKey ) )
    		return glycan_filter_database;
    	else if ( glycan_filter_glycanType.name().equals(_sKey) ) 
    		return glycan_filter_glycanType;
     	
    	return null;
    } 
    
}
