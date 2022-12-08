package it.dev.app.web.view;

import it.liverif.core.web.beans.SelectItem;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Component
public class CommonHtml {

    public String htmlTooltip(String nameElement, String message){
        StringBuilder out=new StringBuilder();
        out.append("&nbsp;<span id=\"info_tooltip_"+nameElement+"_icon\" class=\"fas fa-info-circle text-primary infotooltip\" onmouseout=\"javascript:$('#info_tooltip_"+nameElement+"').fadeOut();\" onmouseover=\"javascript:if ($('#info_tooltip_"+nameElement+":visible').length==0){$('#info_tooltip_"+nameElement+"').css('left',$('#info_tooltip_"+nameElement+"_icon').position().left + 'px');$('#info_tooltip_"+nameElement+"').fadeIn();}\"></span>");
        out.append("<div class=\"boxpreview\" id=\"info_tooltip_"+nameElement+"\" >");
        out.append(message);
        out.append("</div>");
        return out.toString();
    }

    public String htmlMultiselectList(ArrayList<SelectItem> selectItems){
        StringBuilder out=new StringBuilder();
        if(selectItems!=null) {
            for (SelectItem item : selectItems) {
                out.append("<span class=\"badge badge-info\">");
                out.append(item.getText());
                out.append("</span> ");
            }
        }
        return out.toString();
    }

    public String htmlCheckboxList(Boolean check){
        if(check==null) return "";
        return check ? "<i class=\"far fa-check-square\"></i>" : "<i class=\"far fa-square\"></i>";
    }

    public String htmlTextareaList(String value){
        String result="";
        if (StringUtils.hasText(value)){
            result= HtmlUtils.htmlEscape(value, StandardCharsets.UTF_8.name());
        }
        return "<pre class=\"pre-list\">"+result+"</pre>";
    }

}
