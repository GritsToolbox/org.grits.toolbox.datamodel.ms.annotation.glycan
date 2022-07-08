package org.grits.toolbox.datamodel.ms.annotation.glycan.tablemodel.dmtranslate;

/**
 * Abstraction for the ReducingEnd class in the GRITS object model.
 * 
 * @author D Brent Weatherly (dbrentw@uga.edu)
 *
 */
public enum DMReducingEnd
{
	reducing_end_type("Reducing End Type", "The type of reducing end for glycan annotation of an MS experiment."),
	reducing_end_label("Reducing End Label", "The abbreviated name of the reducing end for glycan annotation of an MS experiment."),
	reducing_end_mass("Reducing End Mass", "The mass of the reducing end for glycan annotation of an MS experiment.");

    private String sLabel;
    private String sDescription;

    private DMReducingEnd( String sLabel, String sDescription ) {
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
    
    public static DMReducingEnd lookUp( String _sKey ) {
    	if ( reducing_end_type.name().equals(_sKey) ) 
    		return reducing_end_type;
    	else if ( reducing_end_label.name().equals(_sKey) ) 
    		return reducing_end_label;
    	else if ( reducing_end_mass.name().equals(_sKey) ) 
    		return reducing_end_mass;
    	
    	return null;
    } 
    
}
