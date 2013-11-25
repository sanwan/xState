package com.xross.tools.xstate.editor.treeparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;

import com.xross.tools.xstate.editor.Activator;
import com.xross.tools.xstate.editor.model.StateNode;

public class StateNodeTreePart extends AbstractTreeEditPart implements PropertyChangeListener {
	private StateNode node;
	public StateNodeTreePart(Object model) {
        super(model);
        this.node = (StateNode)model;
    }
	
    protected String getText() {
        return node.getId();
    }
    
    protected Image getImage() {
    	return Activator.getDefault().getImage(Activator.STATE_NODE);
    }
    
	public void activate() {
		super.activate();
		node.getListeners().addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((StateNode)getModel()).getListeners().removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		refreshVisuals();
	}
}
