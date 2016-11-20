package test.action;

import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import test.Constants;

public class NotifyPaymentAction implements ActionHandler, Constants {

	public void execute(ExecutionContext executionContext) throws Exception {
		// TODO Auto-generated method stub
		ContextInstance ct = executionContext.getContextInstance();
		
		String issueperson = ct.getVariable(this.ISSUE_PERSON).toString();
		String managerApproveResult = ct.getVariable(this.MANAGER_APPROVE_RESULT).toString();
		boolean result = false;
		
		if(managerApproveResult.equals(this.APPROVE_RESULT_OK)){
			String moneyCount = ct.getVariable(this.MONEY_COUNT).toString();
			if(Integer.parseInt(moneyCount)>1000){
				String superManagerApproveResut = ct.getVariable(this.SUPER_MANAGER_APPROVE_RESULT).toString();
				if(superManagerApproveResut.equals(this.APPROVE_RESULT_OK)){
					result = true;
				}
			}else{
				result = true;
			}
		}
		
		if(result){
			System.out.println("亲爱的 " + issueperson + " 你的报销单审批已通过！");
		}else{
			System.out.println("亲爱的 " + issueperson + " ，很遗憾地通知你，你的报销单审批被否决！");
		}
		executionContext.getToken().signal();

	}

}
