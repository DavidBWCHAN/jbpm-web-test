package test.job;

import org.jbpm.JbpmConfiguration;

public class RunJobTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JbpmConfiguration jbpmConfiguration = JbpmConfiguration.getInstance();
		
		jbpmConfiguration.getJobExecutor().start();
	}

}
