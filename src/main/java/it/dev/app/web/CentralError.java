package it.dev.app.web;

import it.liverif.core.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CentralError {

    @Autowired
    MessageSource messageSource;

    public String getMessage(Exception ex){
        String message = ex.getMessage();
        String exception = CommonUtils.errorPrintStackTrace(ex).toString();
        String infoError = message;
        if (message == null) infoError = exception;

        if (infoError.contains("Not valid session")) {
            message = messageSource.getMessage("error.notvalidsession", null, LocaleContextHolder.getLocale());
        } else if (infoError.contains("un valore chiave duplicato viola il vincolo univoco")) {
            message = messageSource.getMessage("error.notunique", null, LocaleContextHolder.getLocale());
        } else if (infoError.contains("ConstraintViolationException")) {
            message = messageSource.getMessage("error.constraintviolation", null, LocaleContextHolder.getLocale());
        } else if (exception.contains("PolicyException")) {
            message = messageSource.getMessage("error.policy", null, LocaleContextHolder.getLocale());
        } else if(infoError.contains("it.dev.app.web")){
            message = messageSource.getMessage("error.application", null, LocaleContextHolder.getLocale());
        } else if(exception.contains("optimistic locking failed")){
            message = messageSource.getMessage("error.optimisticlockingfailed", null, LocaleContextHolder.getLocale());
        }

        return message;
    }

}
