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
        <li class="list-inline-item form-element">
            <label class="control-label ${classlabel}" >${label}</label>
            <select class="form-control ${classinput}"
                    id="${field}"
                    name="${field}"
                    ${readonly?string('disabled="disabled"','')} >
                <option value="">-</option>
                <#list select as item>
                    <option value="${item.id}"
                        <#if (item.id == selected_id)> selected="selected"</#if>
                    >${item.text}</option>
                </#list>
            </select>
        </li>
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