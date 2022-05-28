package it.dev.app.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.liverif.core.web.component.Notification;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class BaseController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    Notification notification;

    @Autowired
    MessageSource messageSource;

    @RequestMapping({"","/"})
    public String app(ModelMap model)throws Exception{
        log.info("IN");
        return "redirect:app/";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap model){
        log.info("IN");
        if (error!=null) notification.addWarning(messageSource.getMessage("error.login",null, LocaleContextHolder.getLocale()));
        model.put("notification",notification);
        return "login";
    }

    @RequestMapping("memory-status")
    @ResponseBody
    public String getMemoryStatistics() {
        StringBuilder out=new StringBuilder();
        out.append("totalMemory:"+Runtime.getRuntime().totalMemory()/1024/1024+"Kb");
        out.append(" - maxMemory:"+Runtime.getRuntime().maxMemory()/1024/1024+"Kb");
        out.append(" - freeMemory:"+Runtime.getRuntime().freeMemory()/1024/1024+"Kb");
        return out.toString();
    }
}
