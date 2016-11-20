package test.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;

public class JbpmProcessImageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String processDefinitionId = req.getParameter("processDefinitionId");
		if(processDefinitionId==null){
			throw new IllegalArgumentException("processDefinitionId can not be null!");
		}
		JbpmContext jbpmContext=JbpmConfiguration.getInstance().createJbpmContext();
		try {
			ProcessDefinition pd = jbpmContext.getGraphSession().loadProcessDefinition(Long.parseLong(processDefinitionId));
			
			byte[] bytes = pd.getFileDefinition().getBytes("processimage.jpg");
			OutputStream out = resp.getOutputStream();
			out.write(bytes);
			out.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			jbpmContext.close();
		}
		
	}

	
}
