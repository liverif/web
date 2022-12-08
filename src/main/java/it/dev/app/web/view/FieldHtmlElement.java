package it.dev.app.web.view;

import it.dev.app.component.FreemarkerEngine;
import it.liverif.core.format.DecimalFormatter;
import it.liverif.core.format.LocalDateFormatter;
import it.liverif.core.format.LocalDateTimeFormatter;
import it.liverif.core.format.LocalTimeFormatter;
import it.liverif.core.web.beans.StackWebBean;
import it.liverif.core.web.beans.StackWebConfig;
import it.liverif.core.web.controller.AController;
import it.liverif.core.web.view.AAttribute;
import it.liverif.core.web.view.detail.ADetailResponse;
import it.liverif.core.web.view.detail.AView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.Objects;

@Component
public class FieldHtmlElement extends AView {

    private static String TEMPLATE_TEXT="text_obj";
    private static String TEMPLATE_TEXTAREA="textarea_obj";
    private static String TEMPLATE_HTMLEDITOR="htmleditor_obj";
    private static String TEMPLATE_FILE="file_obj";
    private static String TEMPLATE_CHECKBOX="checkbox_obj";
    private static String TEMPLATE_PASSWORD="password_obj";
    private static String TEMPLATE_SELECT="select_obj";
    private static String TEMPLATE_SELECTAJAX="selectajax_obj";
    private static String TEMPLATE_MULTISELECTAJAX="multiselectajax_obj";
    private static String TEMPLATE_LINESEPARATOR="lineseparator_obj";
    private static String TEMPLATE_HTML="html_obj";
    private static String TEMPLATE_BUTTON="button_obj";

    protected LocalDateFormatter localDateFormatter;
    protected LocalTimeFormatter localTimeFormatter;
    protected LocalDateTimeFormatter localDateTimeFormatter;

    @PostConstruct
    protected void FieldHtmlElement() {
        this.localDateFormatter = new LocalDateFormatter(this.message("format.date.pattern"));
        this.localTimeFormatter = new LocalTimeFormatter(this.message("format.time.pattern"));
        this.localDateTimeFormatter = new LocalDateTimeFormatter(this.message("format.datetime.pattern"));
    }

    @Autowired
    protected StackWebConfig stackWebConfig;

    @Autowired
    ButtonHtmlElement button;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private FreemarkerEngine freemarkerEngine;

    public String text(ADetailResponse detailresponse, String field, Object value, boolean newRow) throws Exception {
        if(value==null) value="";
        String valueEscape = HtmlUtils.htmlEscape(value.toString(), StandardCharsets.UTF_8.name());
        return htmlObj(TEMPLATE_TEXT,detailresponse, field, valueEscape, newRow, null);
    }

    public String dateTime(ADetailResponse detailresponse, String field, Temporal value, boolean newRow) throws Exception {
        String tovalue="";
        if(value!=null) {
            if (value instanceof LocalDate) {
                tovalue = localDateFormatter.print((LocalDate) value, LocaleContextHolder.getLocale());
            } else if (value instanceof LocalTime) {
                tovalue = localTimeFormatter.print((LocalTime) value, LocaleContextHolder.getLocale());
            } else if (value instanceof LocalDateTime) {
                tovalue = localDateTimeFormatter.print((LocalDateTime) value, LocaleContextHolder.getLocale());
            }
        }
        return htmlObj(TEMPLATE_TEXT,detailresponse, field, tovalue, newRow, null);
    }

    public String html(ADetailResponse detailresponse, String field,String value,boolean newRow) throws Exception {
        if(value==null) value="";
        return htmlObj(TEMPLATE_HTML,detailresponse, field, value, newRow, null);
    }

    public String button(ADetailResponse detailresponse, String field,String value,boolean newRow) throws Exception {
        return htmlObj(TEMPLATE_BUTTON,detailresponse, field, value, newRow, null);
    }

    public String lineseparator(ADetailResponse detailresponse, String field) throws Exception {
        return htmlObj(TEMPLATE_LINESEPARATOR,detailresponse, field, null, true, null);
    }

    public String select(ADetailResponse detailresponse, String field,boolean newRow) throws Exception {
        return htmlObj(TEMPLATE_SELECT,detailresponse, field, null, newRow, null);
    }

    public String selectajax(ADetailResponse detailresponse, String field,boolean newRow) throws Exception {
        return htmlObj(TEMPLATE_SELECTAJAX,detailresponse, field, null, newRow, null);
    }

    public String multiselectajax(ADetailResponse detailresponse, String field,boolean newRow) throws Exception {
        return htmlObj(TEMPLATE_MULTISELECTAJAX,detailresponse, field, null, newRow, null);
    }

    public String number(ADetailResponse detailresponse, String field, BigDecimal value, boolean newRow, String pattern) throws Exception {
        DecimalFormatter numberFormatter=new DecimalFormatter(pattern);
        Integer ndecimal=0;
        if (pattern.indexOf('.')>-1) ndecimal=pattern.substring(pattern.indexOf('.')).length()-1;
        String jscript="<script>$('#"+field+"').focusout(function () { $('#"+field+"').val($.number( $('#"+field+"').val(), "+ndecimal+", ',', '.' )); })</script>";
        String tovalue=numberFormatter.print(value,LocaleContextHolder.getLocale());
        return htmlObj(TEMPLATE_TEXT,detailresponse, field, tovalue, newRow, null)+jscript;
    }

    public String textArea(ADetailResponse detailresponse, String field,String value,boolean newRow) throws Exception {
        if(value==null) value="";
        String valueEscape = HtmlUtils.htmlEscape((String) value, StandardCharsets.UTF_8.name());
        return htmlObj(TEMPLATE_TEXTAREA,detailresponse, field, valueEscape, newRow, null);
    }

    public String htmlEditor(ADetailResponse detailresponse, String field,String value,boolean newRow) throws Exception {
        return htmlObj(TEMPLATE_HTMLEDITOR,detailresponse, field, value, newRow, null);
    }

    public String file(ADetailResponse detailresponse, String field,boolean newRow) throws Exception {
        return htmlObj(TEMPLATE_FILE,detailresponse, field, null, newRow, null);
    }

    public String checkbox(ADetailResponse detailresponse, String field,boolean value,boolean newRow) throws Exception {
        return htmlObj(TEMPLATE_CHECKBOX,detailresponse, field, value, newRow, null);
    }

    public String password(ADetailResponse detailresponse, String field,String value,boolean newRow,Boolean viewPasswordOld) throws Exception {
        if(value==null) value="";
        return htmlObj(TEMPLATE_PASSWORD,detailresponse, field, value, newRow, viewPasswordOld);
    }

    private String htmlObj(String template, ADetailResponse detailresponse, String field, Object value, boolean newRow, Object parameter) throws Exception {
        AAttribute attribute=detailresponse.getAttribute();
        if(attribute.viewAccess(field)) {
            String tooltip=getTooltip(attribute, field);
            HashMap<String, Object> params = new HashMap<>();
            params.put("newrow", newRow);
            params.put("attribute", attribute);
            params.put("field", field);
            params.put("value", value);
            params.put("htmlvalue", detailresponse.getRecord().getHtmlvalue(field));
            params.put("type", attribute.getHtmlType(field));
            params.put("label", attribute.getLabel().get(field) + (StringUtils.hasText(tooltip)?" "+tooltip:""));
            params.put("htmllinecss", Objects.toString(attribute.getParams().htmllinecss().get(field)));
            params.put("htmlcss", Objects.toString(attribute.getParams().htmlcss().get(field),""));
            params.put("htmlup", Objects.toString(attribute.getParams().htmlup().get(field)));
            params.put("htmlleft", Objects.toString(attribute.getParams().htmlleft().get(field)));
            params.put("htmlright", Objects.toString(attribute.getParams().htmlleft().get(field)));
            params.put("htmldown", Objects.toString(attribute.getParams().htmldown().get(field)));
            params.put("classlabel", attribute.dataRequired(field) ? "label-required" : "");
            params.put("classinput", attribute.getParams().align().get(field));
            params.put("readonly", attribute.dataReadonly(field));
            params.put("maxlength", attribute.getParams().maxlen().get(field));
            params.put("size", attribute.getParams().colsize().get(field));
            params.put("rows", attribute.getParams().rowsize().get(field));
            params.put("cols", attribute.getParams().colsize().get(field));
            params.put("buttons", attribute.getParams().button().get(field));

            if(template.equals(TEMPLATE_FILE)) {
                String s_form_detail = createLinkSkip(detailresponse.modelName(), AController.METHOD_DETAIL);
                String g_download=button.htmlDownloadLink(message("button.upload.view"),detailresponse.tableName(), field,detailresponse.getRecord().getId());
                params.put("file_action_delete", path()+stackWebConfig.getStackContext()+"/"+detailresponse.tableName()+"/removefile?s="+s_form_detail);
                params.put("file_action_upload", path()+stackWebConfig.getStackContext()+"/"+detailresponse.tableName()+"/upload"+detailresponse.getAttribute().getFileType(field)+"?s="+s_form_detail);
                params.put("button_upload_sent", messageSource.getMessage("button.upload.sent", null, LocaleContextHolder.getLocale()));
                params.put("button_upload_delete", messageSource.getMessage("button.upload.delete", null, LocaleContextHolder.getLocale()));
                params.put("upload_warning", messageSource.getMessage("upload.warning", null, LocaleContextHolder.getLocale()));
                params.put("linkdownloadfile",g_download);
            }else if(template.equals(TEMPLATE_PASSWORD)) {
                String s_form_detail = createLinkSkip(detailresponse.modelName(), AController.METHOD_DETAIL);
                params.put("button_password_edit", messageSource.getMessage("button.password.edit", null, LocaleContextHolder.getLocale()));
                params.put("view_passwordold", parameter);
                params.put("password_change", path()+stackWebConfig.getStackContext()+"/"+detailresponse.tableName()+"/changepassword?s="+s_form_detail);
                params.put("password_info", detailresponse.getPasswordinfo(field));
            }else if(template.equals(TEMPLATE_SELECT)) {
                params.put("select", detailresponse.getAttribute().getParams().select().get(field));
                params.put("selected_id", detailresponse.getRecord().getHtmlitem(field).getId());
            }else if(template.equals(TEMPLATE_SELECTAJAX)) {
                String g_select=createGenericParameters(Pair.of(StackWebBean.PARAMETER_FIELD, field));
                params.put("selected_id", detailresponse.getRecord().getHtmlitem(field).getId());
                params.put("selected_value", detailresponse.getRecord().getHtmlitem(field).getText());
                params.put("select_ajax_link", detailresponse.getAjaxlink(field));
                params.put("select_ajax_action", path()+stackWebConfig.getStackContext()+"/"+detailresponse.tableName()+"/select/?g="+g_select);
            }else if(template.equals(TEMPLATE_MULTISELECTAJAX)) {
                String g_select=createGenericParameters(Pair.of(StackWebBean.PARAMETER_FIELD, field));
                params.put("multiselected", detailresponse.getRecord().getHtmlitems(field));
                params.put("select_ajax_link", detailresponse.getAjaxlink(field));
                params.put("select_ajax_action", path()+stackWebConfig.getStackContext()+"/"+detailresponse.tableName()+"/select/?g="+g_select);
            }
            return freemarkerEngine.create("detail_obj/"+template, params);
        }else return "";
    }


    private String getTooltip(AAttribute attribute,String field){
        if (attribute.getParams().tooltip().containsKey(field)) return attribute.getParams().tooltip().get(field);
        return "";
    }

}
