package com.bordza.booking.bordzaBooking.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
// import static com.deolan.clip.bot.Utils.toArray;​
/**
 * Class in charge of mailing from the Bot.
 * @author Fabien Marsaud
 */



@Slf4j
@Component
public class MailService {

    private final static Pattern emailSplitPattern = Pattern.compile("[,; ]+");
    private final JavaMailSender mailSender;
    private final String defaultReplyTo;

    @Autowired
    public MailService(JavaMailSender mailSender,
                       @Value("${bordza.mail.default-reply}") String defaultReplyTo) {

        this.mailSender = mailSender;
        this.defaultReplyTo = defaultReplyTo;
    }

    @Async
    public void sendEmail(MimeMessage mail) throws MessagingException {
        log.info("sendEmail {} >> {} : {}", getFrom(mail), getAllRecipients(mail), mail.getSubject());
        mailSender.send(mail);

    }

    public static List<String> getFrom(MimeMessage mail) throws MessagingException {

        List<String> lf = new ArrayList<>();
        Address[] froms = mail.getFrom();
        if (froms != null) {
            for (Address from : froms) {
                lf.add(from.toString());
            }
        }
        return lf;
    }

    public static List<String> getAllRecipients(MimeMessage mail) throws MessagingException {

        List<String> lr = new ArrayList<>();
        Address[] tos = mail.getRecipients(Message.RecipientType.TO),
                ccs = mail.getRecipients(Message.RecipientType.CC),
                bccs = mail.getRecipients(Message.RecipientType.BCC);

        if (tos != null) {
            for (Address to : tos) {
                lr.add(to.toString());
            }
        }

        if (ccs != null) {

            for (Address cc : ccs) {

                lr.add(cc.toString());

            }

        }

        if (bccs != null) {

            for (Address bcc : bccs) {

                lr.add(bcc.toString());

            }

        }

        return lr;
    }

    public MimeMessage buildEmail(String toEmails, String subject, String contents, boolean html) throws MessagingException {

        return buildEmail(defaultReplyTo, toEmails, null, subject, contents, html);
    }

    public MimeMessage buildEmail(Collection<String> toEmails, String subject, String contents, boolean html) throws MessagingException {

        return buildEmail(defaultReplyTo, toEmails, null, subject, contents, html);
    }


    public MimeMessage buildEmail(String[] toEmails, String subject, String contents, boolean html) throws MessagingException {
        return buildEmail(defaultReplyTo, toEmails, null, subject, contents, html);
    }

    public MimeMessage buildEmail(String fromEmail, String toEmails, String ccEmails, String subject, String contents, boolean html) throws MessagingException {
        return buildEmail(fromEmail, toEmails, ccEmails, null, null, subject, contents, html);
    }


    public MimeMessage buildEmail(String fromEmail, Collection<String> toEmails, Collection<String> ccEmails, String subject, String contents, boolean html) throws MessagingException {
        return buildEmail(fromEmail, toEmails, ccEmails, null, null, subject, contents, html);
    }


    public MimeMessage buildEmail(String fromEmail, String[] toEmails, String[] ccEmails, String subject, String contents, boolean html) throws MessagingException {
        return buildEmail(fromEmail, toEmails, ccEmails, null, null, subject, contents, html);
    }

    public MimeMessage buildEmail(String fromEmail, String toEmails, String ccEmails, String bccEmails, String replyTo, String subject, String contents, boolean html) throws MessagingException {
        return buildEmail(fromEmail, splitEmails(toEmails), splitEmails(ccEmails), splitEmails(bccEmails), replyTo, subject, contents, html);
    }

    public MimeMessage buildEmail(String fromEmail, Collection<String> toEmails, Collection<String> ccEmails, Collection<String> bccEmails, String replyTo, String subject, String contents, boolean html) throws MessagingException {
        return buildEmail(fromEmail, toArray(toEmails), toArray(ccEmails), toArray(bccEmails), replyTo, subject, contents, html);
    }

    private static String[] toArray(Collection<String> emails) {
        if(emails != null) {
            return emails.toArray(new String[emails.size()]);
        }
        else {
            return null;
        }
    }


    public MimeMessage buildEmail(String fromEmail, String[] toEmails, String[] ccEmails, String[] bccEmails, String replyTo, String subject, String contents, boolean html) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, false); //false: not multipart​

        helper.setFrom(fromEmail);

        if(replyTo != null) {
            helper.setReplyTo(replyTo);
        }

        if (toEmails != null) {
            helper.setTo(toEmails);
        }

        if (ccEmails != null) {
            helper.setCc(ccEmails);
        }

        if (bccEmails != null) {
            helper.setBcc(bccEmails);
        }

        helper.setSubject(subject);

        helper.setText(contents, html);
        return message;
    }

    public static String[] splitEmails(String s) {

        return s != null ? emailSplitPattern.split(s) : null;

    }
}
