package it.dev.app.web.view;

import java.util.ArrayList;
import it.liverif.core.web.beans.StackWebBean;
import it.liverif.core.web.controller.ABaseController;
import it.liverif.core.web.controller.StackWebEngine;
import it.liverif.core.web.view.detail.AView;
import it.liverif.core.web.view.list.AListResponse;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

@Component
public class PaginationHtmlElement extends AView {

    public void pagination(String modelName, Long totalElements, Integer totalPages, AListResponse listResponse) throws Exception {
        String link="";
        listResponse.setNumRecords(totalElements);
        Integer selectPage = listResponse.getSelectPage();

        listResponse.getPagelink().clear();
        if (selectPage != null && selectPage > 1) {
            link=createLink(StackWebEngine.Action.SKIP,modelName, ABaseController.METHOD_LIST, null, Pair.of(StackWebBean.PARAMETER_PAG,"1"));
            listResponse.setFirstPageLink(link);
            link=createLink(StackWebEngine.Action.SKIP,modelName,ABaseController.METHOD_LIST, null,Pair.of(StackWebBean.PARAMETER_PAG,String.valueOf(selectPage - 1)));
            listResponse.setPreviousPageLink(link);
        } else {
            listResponse.setFirstPageLink(null);
            listResponse.setPreviousPageLink(null);
        }
        link=createLink(StackWebEngine.Action.SKIP,modelName,ABaseController.METHOD_LIST, null,Pair.of(StackWebBean.PARAMETER_PAG,String.valueOf(totalPages)));
        listResponse.setLastPageLink(link);

        ArrayList<Integer> linkOfPages = linkOfPages(selectPage, totalPages, 7);
        for (Integer pag : linkOfPages) {
            link = createLink(StackWebEngine.Action.SKIP, modelName, ABaseController.METHOD_LIST, null, Pair.of(StackWebBean.PARAMETER_PAG, pag.toString()));
            listResponse.getPagelink().put(pag, link);
        }
        if (selectPage < totalPages) {
            link = createLink(StackWebEngine.Action.SKIP, modelName, ABaseController.METHOD_LIST, null, Pair.of(StackWebBean.PARAMETER_PAG, String.valueOf(selectPage + 1)));
            listResponse.setNextPageLink(link);
            link = createLink(StackWebEngine.Action.SKIP, modelName, ABaseController.METHOD_LIST, null, Pair.of(StackWebBean.PARAMETER_PAG, String.valueOf(totalPages)));
            listResponse.setLastPageLink(link);
        } else {
            listResponse.setNextPageLink(null);
            listResponse.setLastPageLink(null);
        }
    }
}
