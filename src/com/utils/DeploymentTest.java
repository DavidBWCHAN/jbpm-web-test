package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;

public class DeploymentTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JbpmContext jbpmContext = JbpmConfiguration.getInstance().createJbpmContext();
		try {
			String archiveFile="E:/2016-work/wf/simple.zip";
			InputStream is = new FileInputStream(new File(archiveFile));
			ZipInputStream zis = new ZipInputStream(is);
			ProcessDefinition pd= ProcessDefinition.parseParZipInputStream(zis);
			jbpmContext.deployProcessDefinition(pd);
			zis.close();
			is.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			jbpmContext.close();
		}

	}

}
