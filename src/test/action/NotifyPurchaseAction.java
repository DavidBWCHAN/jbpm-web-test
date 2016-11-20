package test.action;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jbpm.JbpmConfiguration;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import test.Constants;

public class NotifyPurchaseAction implements ActionHandler, Constants {

	public void execute(ExecutionContext executionContext) throws Exception {
		// TODO Auto-generated method stub
		String result=null;
		System.out.println("开始发送通知邮件...........");
		Object managerApproveResult=executionContext.getVariable(this.PURCHASE_MANAGER_APPROVE_RESULT);
		if(managerApproveResult!=null){
			if(managerApproveResult.toString().equals(this.APPROVE_RESULT_OK)){
				result="主管同意采购";
			}else{
				result="采购申请被主管否决";
			}
		}else{
			Object usercancel = executionContext.getVariable(this.PURCHASE_APPLY_CANCEL);
			if(usercancel!=null){
				result="采购申请被用户自己取消";
			}
		}
		
		Properties mailServerProperties = new Properties();
		
		String smtpServer = JbpmConfiguration.Configs.getString("jbpm.mail.smtp.host");
		mailServerProperties.put("mail.smtp.host", smtpServer);
		//设置需要验证(给提供用户名、密码)  
		mailServerProperties.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(mailServerProperties, //返回验证信息的内部类  
                new Authenticator()  
                {  
              
                    protected PasswordAuthentication getPasswordAuthentication()  
                    {  
                          
                        return new PasswordAuthentication("995626566@qq.com","cbw472639");  
                    }  
                }  
        );  
		session.setDebug(true); 
		MimeMessage message = new MimeMessage(session);
		
		String fromAddress = JbpmConfiguration.Configs.getString("jbpm.mail.from.address");
		message.setFrom(new InternetAddress(fromAddress));
		
		String recipientAddress=null;
		String issueperson=executionContext.getVariable(this.ISSUE_PERSON).toString();
		if(issueperson.equals("user1")){
			recipientAddress="995626566@qq.com";
		}else{
			recipientAddress="946014574@qq.com";
		}
		InternetAddress recipient = new InternetAddress(recipientAddress);
		message.addRecipient(Message.RecipientType.TO, recipient);
		message.setSubject("采购申请处理结果通知");
		StringBuffer sb = new StringBuffer();
		sb.append("Dear " + executionContext.getVariable(this.ISSUE_PERSON)+":\\r\\r\\r");
		sb.append("你的采购申请结果是:" + result);
		message.setText(sb.toString());
		message.setSentDate(new Date());
		//Transport.send(message);

	}

}
