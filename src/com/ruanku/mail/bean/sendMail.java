package  com.ruanku.mail.bean;




import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ruanku.util.Constant;

public class sendMail {
	
	String username= Constant.mail.username;
	
	private Message getMessage(){

		Properties p=new Properties();
		p.put("mail.transport.protocol","smtp");
		p.put("mail.smtp.host",Constant.mail.mail163);
		p.put("mail.smtp.port","25");
		p.put("mail.smtp.auth","true");
		
		
		String password= Constant.mail.password;
		MyAuthor auth=new MyAuthor(username,password);
		Session session=Session.getDefaultInstance(p,auth);
		Message message=new MimeMessage(session);
		
		return message;
	}
		
	
	
	public void sendVerify(String stu_email,String stu_nameMd5,String randMd5)
	throws MessagingException {
		
		Message message=getMessage();
		
		
			message.setFrom(new InternetAddress(username));
			message.setRecipient(RecipientType.TO,new InternetAddress(stu_email));
			message.setSentDate(new Date());
			
			message.setSubject("信号与系统");
			String m="<a href=\""+Constant.mail.url+Constant.mail.projName+"/user/verify?nameMd5="+stu_nameMd5+"&randMd5="+randMd5+"\">" +
					Constant.mail.url+Constant.mail.projName+"/user/verify?nameMd5="+stu_nameMd5+"&randMd5="+randMd5+"</a>";
			
			//"127.0.0.1"为本人电脑临时IP地址ַ
			
			
			message.setContent(m,"text/html;charset=utf-8");
			
			Transport.send(message);
			
		}
	
	public void sendReset(String stu_email,String stu_nameMd5,String randMd5)
	throws MessagingException {
		Message message=getMessage();
		
		
		message.setFrom(new InternetAddress(username));
		message.setRecipient(RecipientType.TO,new InternetAddress(stu_email));
		message.setSentDate(new Date());
		
		message.setSubject("信号与系统");
		String m="<a href=\""+Constant.mail.url+Constant.mail.projName+"/user/reset?nameMd5="+stu_nameMd5+"&randMd5="+randMd5+"\">" +
				Constant.mail.url+Constant.mail.projName+"/user/reset?nameMd5="+stu_nameMd5+"&randMd5="+randMd5+"</a>";
		
		
		
		
		message.setContent(m,"text/html;charset=utf-8");
		
		Transport.send(message);
		
	}
	
	

}
