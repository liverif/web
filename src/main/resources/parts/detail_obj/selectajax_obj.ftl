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
                    ${readonly?string('disabled="disabled"','')} >
            </select>
        </div>
        <script>
            /*<![CDATA[*/
            {
                var id = '${selected_id}';
                var value = '${selected_value}';
                var field = '${field}';
                var link = '${select_ajax_link}';
                var action = '${select_ajax_action}';
                if (link != '') {
                    selectAjaxItems(field, action, id, value, $('#' + link).val());
                    $('#' + link).change(function () {
                        var id = '${selected_id}';
                        var value = '${selected_value}';
                        var field = '${field}';
                        var link = '${select_ajax_link}';
                        var action = '${select_ajax_action}';
                        selectAjaxItems(field, action, id, value, $('#' + link).val());
                        $('#' + field).val(null).trigger('change');
                    });
                } else {
                    selectAjaxItems(field, action, id, value, 0);
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