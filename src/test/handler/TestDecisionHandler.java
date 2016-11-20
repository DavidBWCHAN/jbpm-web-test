package test.handler;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.DecisionHandler;

import test.Constants;

public class TestDecisionHandler implements DecisionHandler, Constants {

	public String decide(ExecutionContext executionContext) throws Exception {
		// TODO Auto-generated method stub
		String transitionName ="toend";
		
		Object managerApproveResult=executionContext.getVariable(this.PURCHASE_MANAGER_APPROVE_RESULT);
		if(managerApproveResult!=null && managerApproveResult.toString().equals(this.PURCHASE_MANAGER_APPROVE_RESULT_BACK)){
			transitionName="tobackmod";
			executionContext.getContextInstance().setVariable(this.PURCHASE_MANAGER_APPROVE_RESULT, null);
		}
		return transitionName;
	}

}
