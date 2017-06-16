package com.chaoxing.oa.util.mail.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.chaoxing.oa.util.mail.MailManager;
import com.chaoxing.oa.util.mail.MailMessage;
import com.chaoxing.oa.util.system.DateUtil;


public class MailManagerImpl implements MailManager {
	private final static long MAX_SIZE = 20 * 1024 * 1000;//最大邮件大小20M
	private MailSender mailSender;
    private SimpleMailMessage templateMessage;
    private JavaMailSender javaMailSender;
    
	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getTemplateMessage() {
		return templateMessage;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}
	
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public int sendSimpleMail(MailMessage mailMessages) {
		 SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
	        msg.setTo(mailMessages.getTo());
	        msg.setSubject(mailMessages.getSubject());
	        msg.setFrom(templateMessage.getFrom());
	        msg.setText(mailMessages.getBody());
	        try{
	            this.mailSender.send(msg);
	            return 1;
	        }
	        catch (MailException ex) {
	            System.err.println(ex.getMessage());
	            return 0;
	        }
		
	}

	@Override
	public int sendSimpleMail(SimpleMailMessage simpleMailMessage) {
		simpleMailMessage.setFrom(this.templateMessage.getFrom());
		try {
			this.mailSender.send(simpleMailMessage);
		} catch (MailException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int sendMimeMail(MailMessage mailMessage, String filePath) {
		File[] files = new File[1];
		files[0] = new File(filePath);
		return sendMimeMail(mailMessage, files, false);
	}

	@Override
	public int sendMimeMail(MailMessage mailMessage, File file) {
		File[] files = new File[1];
		files[0] = file;
		return sendMimeMail(mailMessage, files, false);
	}

	@Override
	public int sendSimpleMail(List<MailMessage> mailMessages) {
		int sum = 0;
		for (int i = 0; i < mailMessages.size(); i++) {
			sum += this.sendSimpleMail(mailMessages.get(i));
		}
		return sum;
	}

	@Override
	public int sendMimeMail(MailMessage mailMessage, String filePath, boolean inline) {
		File[] files = new File[1];
		files[0] = new File(filePath);
		return sendMimeMail(mailMessage, files, inline);
	}
	
	@Override
	public int sendMimeMail(MailMessage mailMessage, String[] filePath) {
		File[] files = new File[filePath.length];
		for (int i = 0; i < files.length; i++) {
			files[i] = new File(filePath[i]);
		}
		return sendMimeMail(mailMessage, files, false);
	}

	@Override
	public int sendMimeMail(MailMessage mailMessage, File[] files) {
		return sendMimeMail(mailMessage, files, false);
	}
	
	public int sendMimeMail(MailMessage mailMessage, File[] files, boolean inline) {
		MimeMessage message = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			/** multipart 模式可以存放附件和资源。资源可以是图片，html，css样式
			 * Multipart email messages allow for both attachments and inline resources.
			 *  Examples of inline resources would be images or a stylesheet
			 *   you want to use in your message, but that you don’t want displayed as an attachment.
			 */
			helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(new InternetAddress(this.templateMessage.getFrom()));
			helper.setTo(mailMessage.getTo());
			helper.setSubject(mailMessage.getSubject());
			StringBuffer imgDiv = new StringBuffer("");
			if(null != files){
				if(inline){
					for (int i = 0; i < files.length; i++) {
						File file = files[i];
						if(file.exists() && file.length()<MAX_SIZE){
							imgDiv.append("<div style=\"text-align: center\"><img src=\"cid:img"
									+ i
									+ "\" alt=\"\"/>");
							helper.addInline("img" + i, file);
						}
					}
				}else{
					for (int i = 0; i < files.length; i++) {
						File file = files[i];
						if(file.exists() && file.length()<MAX_SIZE){
							helper.addAttachment(file.getName(), new FileSystemResource(file));
						}
					}
				}
			}
			//true 表示启用html格式的邮件
			if(inline){
				helper.setText(
						"<body><h3 style=\"text-align: center\">"
								+ mailMessage.getSubject() + "<span style=\"color: grey;font-size: 12px;\"><i>"
								+ "时间：" + DateUtil.format(new Date())
								+ "</i></span></h3>"
								+ "<div>" + mailMessage.getBody() + "</div>"
								+ imgDiv.toString()
								+ "</div>"
								, inline);
			}else{
				helper.setText(mailMessage.getBody());
			}
			//发送邮件
			this.javaMailSender.send(message);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		return 0;
	}
	
}
