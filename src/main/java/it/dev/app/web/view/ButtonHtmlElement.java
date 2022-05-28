package it.dev.app.web.view;

import org.springframework.data.util.Pair;
import org.springframework.ui.ModelMap;

import it.liverif.core.web.beans.StackWebBean;
import it.liverif.core.web.controller.ABaseController;
import it.liverif.core.web.controller.AController;
import it.liverif.core.web.view.detail.AView;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

@Component
public class ButtonHtmlElement extends AView {

    public String htmlDownloadLink(String label, String modelName, String field, Long id){
        String genericParameters=createGenericParameters(Pair.of(StackWebBean.PARAMETER_ID,id.toString()), Pair.of(StackWebBean.PARAMETER_FIELD, field));
        String url=path()+stackWebConfig.getStackContext()+"/"+modelName+"/downloadfile?"+ ABaseController.REQUEST_GENERIC_PARAMETERS+"="+genericParameters;
        return "<a class=\"form-control btn btn-sm btn-outline-info\" target=\"_blank\" href=\""+url+"\">"+label+"</a>";
    }

    public void breadcrumbHome(ModelMap model) {
        model.put(BREADCRUMB_HOME, createLinkClean());
    }

    public void breadcrumbBack(ModelMap model) {
        model.put(BREADCRUMB_BACK, createLinkBack());
    }

    public void breadcrumbRefresh(ModelMap model) {
        model.put(BREADCRUMB_REFRESH, createLinkRefresh());
    }

    public void listSearch(ModelMap model,String modelName) {
        String form_search_link = createLinkSkip(modelName, AController.METHOD_LIST);
        model.put(FORM_SEARCH_LINK, form_search_link);
        String button_search = createAction(ACTION_LIST_SEARCH);
        model.put(ACTION_LIST_SEARCH, button_search);
        String button_reset = createAction(ACTION_LIST_SEARCH_RESET);
        model.put(ACTION_LIST_SEARCH_RESET, button_reset);
    }

    public String htmlConfirmSubmit(String action,String label,String cssBtn, String msgConfirm){
        return htmlConfirmSubmit(action,label,cssBtn, false, msgConfirm);
    }

    public String htmlConfirmSubmit(String action,String label,String cssBtn, boolean readonly, String msgConfirm){
        if (readonly) return "<button disabled=\"disabled\" class=\""+cssBtn+"\" >"+label+"</button>";
        return "<button class=\""+cssBtn+"\" onclick=\"javascript:actionModelConfirm('"+createAction(action)+"','"+label.replaceAll("\\<.*?>", "")+"','"+msgConfirm+"');\">"+label+"</button>";
    }

    public String htmlSubmit(String action,String label,String cssBtn){
        return htmlSubmit(action,label,cssBtn,false);
    }

    public String htmlSubmit(String action,String label,String cssBtn, boolean readonly){
        if (readonly) return "<button disabled=\"disabled\" class=\""+cssBtn+"\" >"+label+"</button>";
        return "<button class=\""+cssBtn+"\" onclick=\"javascript:actionModel('"+createAction(action)+"');\">"+label+"</button>";
    }

    public String htmlConfirmLink(String link,String label,String cssBtn,String msgConfirm){
        return htmlConfirmLink(link,label,cssBtn, false, msgConfirm);
    }

    public String htmlConfirmLink(String link,String label,String cssBtn, boolean readonly, String msgConfirm){
        if (readonly) return "<a class=\""+cssBtn+"\" disabled=\"disabled\" href=\"#\">"+label+"</a>";
        return "<a class=\""+cssBtn+" wconfirm\" data-title=\""+label.replaceAll("\\<.*?>", "") +"\" data-content=\""+msgConfirm+"\" href=\""+path()+"/app/?s="+link+"\">"+label+"</a>";
    }

    public String htmlLink(String link,String label,String cssBtn){
        return htmlLink(link,label,cssBtn, false);
    }

    public String htmlLink(String link,String label,String cssBtn, boolean readonly){
        if (readonly) return "<a class=\"disabled "+cssBtn+"\" href=\"#\">"+label+"</a>";
        return "<a class=\""+cssBtn+" loading\" href=\""+path()+"/app/?s="+link+"\">"+label+"</a>";
    }

}
