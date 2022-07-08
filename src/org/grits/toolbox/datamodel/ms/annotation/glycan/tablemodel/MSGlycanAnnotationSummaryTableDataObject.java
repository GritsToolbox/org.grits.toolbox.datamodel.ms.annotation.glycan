package org.grits.toolbox.datamodel.ms.annotation.glycan.tablemodel;

import java.util.ArrayList;

import org.eclipse.nebula.widgets.nattable.group.ColumnGroupModel;
import org.eclipse.nebula.widgets.nattable.group.ColumnGroupModel.ColumnGroup;

import org.grits.toolbox.datamodel.ms.annotation.glycan.preference.MSGlycanAnnotationSummaryViewerPreferenceLoader;
import org.grits.toolbox.datamodel.ms.annotation.glycan.preference.cartoon.MSGlycanAnnotationCartoonPreferencesLoader;
import org.grits.toolbox.datamodel.ms.tablemodel.FillTypes;

public class MSGlycanAnnotationSummaryTableDataObject extends MSGlycanAnnotationTableDataObject {    
	private ArrayList<Integer> alFirstGroupIndices = null;
  
    public MSGlycanAnnotationSummaryTableDataObject( int _iMSLevel, FillTypes fillTypes ) {
    	super(_iMSLevel, fillTypes);
    	this.alFirstGroupIndices = null;
 	}
           
    @Override
    public void initializePreferences() {
    	setTablePreferences(MSGlycanAnnotationSummaryViewerPreferenceLoader.getTableViewerPreference(getMSLevel(), getFillType()));	
		setCartoonPrefs(MSGlycanAnnotationCartoonPreferencesLoader.getCartoonPreferences());
    }
    
    public ArrayList<Integer> getFirstGroupIndices() {
		return alFirstGroupIndices;
	}
    
	public void discoverGroups( ColumnGroupModel groupModel ) {
		this.alFirstGroupIndices = new ArrayList<Integer>();
		if ( groupModel == null || groupModel.isEmpty() ) 
			return;
		
		ColumnGroup prevGroup = null;
		int iTotalGroups = groupModel.size();
		int iGrpCnt = 0;
		int i = 0;
		while( iGrpCnt < iTotalGroups ) {
			ColumnGroup group = groupModel.getColumnGroupByIndex(i++);  // change 07/31/2013. No longer using "Exp" text so relying on order
			if ( prevGroup != null && prevGroup.equals(group) ) {
				// HACK to get around their bad API:  if we've seen this group continue.
				continue;
			}
			prevGroup = group;
			iGrpCnt++;
			this.alFirstGroupIndices.add(i-1);
		}	
	}
    
        
}
