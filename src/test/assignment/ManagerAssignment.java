package test.assignment;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

import test.Constants;

public class ManagerAssignment implements AssignmentHandler, Constants{

	public void assign(Assignable arg0, ExecutionContext arg1) throws Exception {
		String issueperson = arg1.getContextInstance().getVariable(this.ISSUE_PERSON).toString();
		if(issueperson.equals("user1")){
			arg0.setActorId("manager1");
		}else{
			arg0.setActorId("manager2");
		}
		
	}

}
