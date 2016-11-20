package test.testprocess;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class TestProcess extends TestCase{

	/**
	 * @param args
	 */
	public void testPayProcess() throws Exception {
		// TODO Auto-generated method stub
		JbpmContext jbpmContext = JbpmConfiguration.getInstance().createJbpmContext();
		try {
			String isssueperson = "user1";
			String manager = "manager1";
			this.newProcessInstance(jbpmContext, isssueperson);
			//this.proccessManagerTask(jbpmContext, manager);
			//this.processSuperManagerTask(jbpmContext);
			//this.processCashierTask(jbpmContext);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			jbpmContext.close();
		}

	}
	
	private void newProcessInstance(JbpmContext jbpmContext, String issueperson) throws Exception{
		jbpmContext.setActorId(issueperson);
		
		ProcessDefinition pd = jbpmContext.getGraphSession().findLatestProcessDefinition("payment");
		ProcessInstance pi = pd.createProcessInstance();
		ContextInstance ci = pi.getContextInstance();
		
		ci.setVariable("issueperson", issueperson);
		
		TaskInstance ti = pi.getTaskMgmtInstance().createStartTaskInstance();
		ti.setVariable("title", "测试报销--");
		ti.setVariable("money_count", "1111");
		ti.setVariable("remark", "测试报销一的备注");
		
		ti.end();
	}
	
	private void proccessManagerTask(JbpmContext jbpmContext, String currentperson) throws Exception{
		@SuppressWarnings("unchecked")
		List<TaskInstance> taskList = jbpmContext.getTaskList(currentperson);
		for(Iterator iter = taskList.iterator();iter.hasNext();){
			TaskInstance ti = (TaskInstance)iter.next();
			System.out.println("处理部门经理的任务--打印从工作流程上下文当中读取的title、money_count、remark和issueperson变量的值");
			System.out.println("title:" + ti.getVariable("title"));
			System.out.println("money_count:" + ti.getVariable("money_count"));
			System.out.println("remark:" + ti.getVariable("remark"));
			System.out.println("issueperson:" + ti.getVariable("issueperson"));
			ti.getContextInstance().setVariable("manager_approve_result", "1");
			ti.end("agree");
		}
	}
	
	private void processSuperManagerTask(JbpmContext jbpmContext) throws Exception{
		List taskList = jbpmContext.getTaskList("supermanager");
		for(Iterator iter =  taskList.iterator();iter.hasNext();){
			TaskInstance ti = (TaskInstance)iter.next();
			System.out.println("处理总经理的任务--打印从工作流程上下文当中读取的title、money_count、remark和issueperson变量的值");
			System.out.println("title:" + ti.getVariable("title"));
			System.out.println("money_count:" + ti.getVariable("money_count"));
			System.out.println("remark:" + ti.getVariable("remark"));
			System.out.println("issueperson:" + ti.getVariable("issueperson"));
			System.out.println("manager_approve_result:" + ti.getVariable("manager_approve_result"));
			ti.getContextInstance().setVariable("super_manager_approve_result", "1");
			ti.end();
		}
	}
	
	private void processCashierTask(JbpmContext jbpmContext) throws Exception{
		List taskList = jbpmContext.getTaskList("cashier");
		for(Iterator iter = taskList.iterator();iter.hasNext();){
			TaskInstance ti = (TaskInstance)iter.next();
			System.out.println("处理财务的任务--打印从工作流程上下文当中读取的title、money_count、remark和issueperson变量的值");
			System.out.println("title:" + ti.getVariable("title"));
			System.out.println("money_count:" + ti.getVariable("money_count"));
			System.out.println("remark:" + ti.getVariable("remark"));
			System.out.println("issueperson:" + ti.getVariable("issueperson"));
			System.out.println("manager_approve_result:" + ti.getVariable("manager_approve_result"));
			System.out.println("super_manager_approve_result:" + ti.getVariable("super_manager_approve_result"));
			ti.end();
		}
	}

}
