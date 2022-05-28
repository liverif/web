package it.dev.app.web.controller;

import it.dev.app.web.CentralError;
import it.liverif.core.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class AppExceptionController extends ResponseEntityExceptionHandler  {

    public static final String ERROR_STATUS="error_status";
    public static final String ERROR_DESCRIPTIONS="error_description";
    public static final String ERROR_EXCEPTION="error_exception";

    @Autowired
    MessageSource messageSource;

    @Autowired
    CentralError centralError;

    @ExceptionHandler({Exception.class})
    public String handleException(Exception ex, Model model, HttpServletResponse response) {
        int status = response.getStatus();
        return pageError(ex,model,status);
    }

    public String pageError(Exception ex, Model model,int status){
        try {
            model.addAttribute(ERROR_STATUS, status);
            String message = centralError.getMessage(ex);
            String exception = CommonUtils.errorPrintStackTrace(ex).toString();

            model.addAttribute(ERROR_DESCRIPTIONS, message);

            log.error("ControllerExceptionHandler: " + status);
            model.addAttribute(ERROR_EXCEPTION, exception);
            log.error(exception);
        }catch (Exception e){
            model.addAttribute(ERROR_STATUS, status);
            model.addAttribute(ERROR_EXCEPTION, e.toString());
            model.addAttribute(ERROR_DESCRIPTIONS, "ERROR");
            log.error(CommonUtils.errorPrintStackTrace(e).toString());
        }
        return "error";
    }

}
