package org.grits.toolbox.datamodel.ms.annotation.glycan.tablemodel;

import java.util.ArrayList;

import org.grits.toolbox.datamodel.ms.annotation.glycan.preference.MSGlycanAnnotationViewerPreferenceLoader;
import org.grits.toolbox.datamodel.ms.annotation.glycan.preference.cartoon.MSGlycanAnnotationCartoonPreferences;
import org.grits.toolbox.datamodel.ms.annotation.glycan.preference.cartoon.MSGlycanAnnotationCartoonPreferencesLoader;
import org.grits.toolbox.datamodel.ms.annotation.tablemodel.MSAnnotationTableDataObject;
import org.grits.toolbox.datamodel.ms.tablemodel.FillTypes;
import org.grits.toolbox.utils.image.GlycanImageProvider;

public class MSGlycanAnnotationTableDataObject extends MSAnnotationTableDataObject {    
    protected ArrayList<Integer> alCartoonCols = null;
    protected ArrayList<Integer> alExtraCartoonCols = null;
    protected MSGlycanAnnotationCartoonPreferences cartoonPrefs = null;
	public static final GlycanImageProvider glycanImageProvider = new GlycanImageProvider();
    
    public MSGlycanAnnotationTableDataObject( int _iMSLevel, FillTypes fillTypes ) {
    	super(_iMSLevel, fillTypes);
        this.alCartoonCols = new ArrayList<Integer>();  
        this.alExtraCartoonCols = new ArrayList<Integer>();
 	}
          
    @Override
    public void initializePreferences() {
    	setTablePreferences(MSGlycanAnnotationViewerPreferenceLoader.getTableViewerPreference(getMSLevel(), getFillType()));
		setCartoonPrefs(MSGlycanAnnotationCartoonPreferencesLoader.getCartoonPreferences());
    }
    
    public void setCartoonPrefs(MSGlycanAnnotationCartoonPreferences cartoonPrefs) {
		this.cartoonPrefs = cartoonPrefs;
	}
    
    public MSGlycanAnnotationCartoonPreferences getCartoonPrefs() {
		return cartoonPrefs;
	}
        
    public void addCartoonCol( Integer _iCol ) {
    	this.alCartoonCols.add(_iCol);
    }
    
    public ArrayList<Integer> getCartoonCols() {
    	return this.alCartoonCols;
    }    

    public void addExtraCartoonCol( Integer _iCol ) {
    	this.alExtraCartoonCols.add(_iCol);
    }
    
    public ArrayList<Integer> getExtraCartoonCols() {
    	return this.alExtraCartoonCols;
    }    
    
    
	// this is needed because we added the "Selected" column and need to shift everything over 1
    @Override
    protected void setColIdSettingsForSubsetObject( MSAnnotationTableDataObject subsetSimianTableData ) {
    	super.setColIdSettingsForSubsetObject(subsetSimianTableData);
    	for( Integer iCartoonCol : getCartoonCols() ) {
    		( (MSGlycanAnnotationTableDataObject) subsetSimianTableData).addCartoonCol(iCartoonCol + 1);
    	}
    	for( Integer iCartoonCol : getExtraCartoonCols() ) {
    		( (MSGlycanAnnotationTableDataObject) subsetSimianTableData).addExtraCartoonCol(iCartoonCol + 1);
    	}
    }
    
    @Override
	public MSAnnotationTableDataObject getSubsetSimianTableDataObject(int _iScanNum, String _sRowId,  boolean _bCheckParentScan) {
		MSGlycanAnnotationTableDataObject subsetSimianTableData = new MSGlycanAnnotationTableDataObject(getMSLevel(), getFillType());
		return getSubsetSimianTableDataObject(_iScanNum, _sRowId, subsetSimianTableData, _bCheckParentScan);
	}
    
}
