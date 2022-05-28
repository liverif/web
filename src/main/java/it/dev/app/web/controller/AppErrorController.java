package it.dev.app.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class AppErrorController {

    public static final String ERROR_STATUS="error_status";
    public static final String ERROR_DESCRIPTIONS="error_description";
    public static final String ERROR_EXCEPTION="error_exception";

    @Autowired
    MessageSource messageSource;

    @RequestMapping({"/error","/error/{code}"})
    public String app(@PathVariable(required = false) Integer code,ModelMap model)throws Exception{
        log.error("Error Server: {}",code);
        model.put(ERROR_STATUS,code);
        model.put(ERROR_DESCRIPTIONS,"");
        model.put(ERROR_EXCEPTION,"");
        if(code.equals(HttpStatus.FORBIDDEN.value())) model.put(ERROR_DESCRIPTIONS,messageSource.getMessage("error.server.403",null, LocaleContextHolder.getLocale()));
        if(code.equals(HttpStatus.NOT_FOUND.value())) model.put(ERROR_DESCRIPTIONS,messageSource.getMessage("error.server.404",null, LocaleContextHolder.getLocale()));
        if(code.equals(HttpStatus.INTERNAL_SERVER_ERROR.value())) model.put(ERROR_DESCRIPTIONS,messageSource.getMessage("error.server.500",null, LocaleContextHolder.getLocale()));

        return "error";
    }

}
