package it.dev.app.component;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Component
public class FreemarkerEngine {

    private static final String EXT_FTL=".ftl";

    public String create(String ftl, Map<String, Object> params) throws Exception {
        Configuration freemarkerConfiguration = configuration();
        Template template = freemarkerConfiguration.getTemplate(ftl+EXT_FTL);
        Writer finalTemplate = new StringWriter();
        template.process(params, finalTemplate);
        finalTemplate.flush();
        return finalTemplate.toString();
    }

    public String create(String ftl) throws Exception {
        Map<String, Object> params=new HashMap<>();
        return create(ftl, params);
    }

    public freemarker.template.Configuration configuration(){
        Configuration freemarkerConfiguration = new Configuration(freemarker.template.Configuration.VERSION_2_3_31);
        freemarkerConfiguration.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        freemarkerConfiguration.setLocale(Locale.ITALY);
        freemarkerConfiguration.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
        freemarkerConfiguration.setClassForTemplateLoading(FreemarkerEngine.class, "/parts");
        return freemarkerConfiguration;
    }

    public freemarker.template.Configuration configuration(StringTemplateLoader stringLoader){
        Configuration freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_31);
        freemarkerConfiguration.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        freemarkerConfiguration.setLocale(Locale.ITALY);
        freemarkerConfiguration.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
        freemarkerConfiguration.setTemplateLoader(stringLoader);
        return freemarkerConfiguration;
    }

}
