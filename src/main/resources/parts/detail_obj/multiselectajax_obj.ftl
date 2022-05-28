<li class="list-inline-item m-0">
    <#if htmlup?has_content >
        <ul class="list-inline form-element-row-up">
            <li class="list-inline-item form-element">${htmlup}</li>
        </ul>
    </#if>
    <ul class="${htmllinecss}">
        <#if htmlleft?has_content >
            <li class="list-inline-item form-element" >${htmlleft}</li>
        </#if>
        <div style="min-width:300px;">
            <label class="control-label ${classlabel}" >${label}</label>
            <select id="${field}"
                    name="${field}"
                    multiple="multiple"
                    ${readonly?string('disabled="disabled"','')} >
                <#list multiselected as item>
                    <option value="${item.id}" selected="selected" >${item.text}</option>
                </#list>
            </select>
        </div>
        <script>
            /*<![CDATA[*/
            {
                var field = '${field}';
                var link = '${select_ajax_link}';
                var action = '${select_ajax_action}';
                multiselectAjaxItems(field, action, 0);
                if (link != '') {
                    multiselectAjaxItems(field, action, $('#' + link).val());
                    $('#' + link).change(function () {
                        var field = '${field}';
                        var link = '${select_ajax_link}';
                        var action = '${select_ajax_action}';
                        $('#' + field).empty();
                    });
                }else{
                    multiselectAjaxItems(field, action, 0);
                }
            }
            /*]]>*/
        </script>
        <#if htmlright?has_content >
            <li class="list-inline-item form-element">${htmlright}</li>
        </#if>
    </ul>
    <#if htmldown?has_content >
        <ul class="list-inline form-element-row-up">
            <li class="list-inline-item form-element">${htmldown}</li>
        </ul>
    </#if>
</li>
<#if newrow >
    </ul><ul class="list-inline mb-1">
</#if>