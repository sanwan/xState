package com.xross.tools.xstate.editor.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.xross.tools.xstate.editor.StateMachineDiagramGraphicalEditor;
import com.xross.tools.xstate.editor.commands.AddEventCommand;
import com.xross.tools.xstate.editor.model.Event;
import com.xross.tools.xstate.editor.model.StateMachine;
import com.xross.tools.xstate.editor.model.StateMachineDiagram;

public class StateMachineCreateEventAction extends WorkbenchPartAction implements StateMachineActionConstants, StateMachineMessages{
	public StateMachineCreateEventAction(IWorkbenchPart part){
		super(part);
		setId(ID_PREFIX + CREATE_EVENT);
		setText(CREATE_EVENT_MSG);
	}
	
	protected boolean calculateEnabled() {
		return true;
	}
	
	public void run() {
		InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(), "Create new Event: ", "Event", "event", null);
		if (dlg.open() != Window.OK)
			return;
		String name = dlg.getValue();
		
		StateMachineDiagramGraphicalEditor editor = (StateMachineDiagramGraphicalEditor)getWorkbenchPart();
		StateMachineDiagram diagram = (StateMachineDiagram)editor.getRootEditPart().getContents().getModel();
		StateMachine stateMachine = null;
		Event event = new Event();
		event.setName(name);
		execute(new AddEventCommand(stateMachine, event));
	}
}