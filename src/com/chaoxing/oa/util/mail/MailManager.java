package com.chaoxing.oa.util.mail;

import java.io.File;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;

public interface MailManager {
	/**
	 * 发送简单文本邮件
	 * @param mailMessages
	 * @return
	 */
	public int sendSimpleMail(MailMessage mailMessages);
	
	/**
	 * 批量发送简单文本邮件
	 * @param mailMessages
	 * @return
	 */
	public int sendSimpleMail(List<MailMessage> mailMessages);
	
	/**
	 * 简单文本邮件
	 * @param simpleMailMessage
	 * @return
	 */
	public int sendSimpleMail(SimpleMailMessage simpleMailMessage);

	/**
	 * 发送带附件html邮件
	 * @param mailMessage 文件信息
	 * @param filePath 文件路径
	 * @return
	 */
	public int sendMimeMail(MailMessage mailMessage, String filePath);
	
	/**
	 * 发送带附件邮件，选择是内联文件还是附件
	 * @param mailMessage
	 * @param filePath
	 * @param inline
	 * @return
	 */
	public int sendMimeMail(MailMessage mailMessage, String filePath, boolean inline);
	
	/**
	 * 发送带多个附件的邮件
	 * @param mailMessage
	 * @param filePath 附件路径
	 * @param inline
	 * @return
	 */
	public int sendMimeMail(MailMessage mailMessage, String[] filePath);
	
	/**
	 * 发送html邮件
	 * @param mailMessage
	 * @param file 文件流
	 * @return
	 */
	public int sendMimeMail(MailMessage mailMessage, File file);
	
	/**
	 * 发送带附件的html邮件
	 * @param mailMessage
	 * @param files 多个文件流
	 * @return
	 */
	public int sendMimeMail(MailMessage mailMessage, File[] files);
	
	/**
	 * 发送带附件的邮件
	 * @param mailMessage
	 * @param files 附件，单个附件最大为20m
	 * @param inline true 发送带img标签格式的 html邮件，否则为普通文本邮件。
	 * @return
	 */
	public int sendMimeMail(MailMessage mailMessage, File[] files, boolean inline);
}
