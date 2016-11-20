package test.action;

import java.util.Collection;
import java.util.Iterator;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;

public class ModJoinNodeAction implements ActionHandler {

	public void execute(ExecutionContext executionContext) throws Exception {
		// TODO Auto-generated method stub
		TaskMgmtInstance tmi=executionContext.getTaskMgmtInstance();
		
		Token rootToken = executionContext.getProcessInstance().getRootToken();
		Collection childTokenList = rootToken.getChildren().values();
		for(Iterator iter = childTokenList.iterator();iter.hasNext();){
			Token childToken = (Token)iter.next();
			Collection c = tmi.getUnfinishedTasks(childToken);
			for(Iterator iterator=c.iterator();iterator.hasNext();){
				TaskInstance ti =(TaskInstance) iterator.next();
				//取消当前任务实例，在任务实例取消时，会自动将任务实例结束
				ti.cancel();
			}
			
		}

	}

}
