package de.srh.library.util;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class EmailSender {

    private static Logger logger = LoggerFactory.getLogger(EmailSender.class);
    public static void send(List<String> receivers, String emailSubject, Map<String, Object> dataModel,
                            String emailTemplate, boolean isHtml){
        try{
            TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template",
                    TemplateConfig.ResourceMode.CLASSPATH));
            Template template = engine.getTemplate(emailTemplate);
            String content = template.render(dataModel);
            MailUtil.send(receivers, emailSubject, content, isHtml);
        }catch (Exception e){
            logger.error("Email send failed, subject: {}",emailSubject, e);
        }
    }
}
