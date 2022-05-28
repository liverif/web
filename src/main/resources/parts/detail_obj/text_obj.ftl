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
            <input type="${type}" class="form-control ${classinput}"
                   id="${field}"
                   name="${field}"
                   value="${(value)!''}"
                   ${readonly?string('disabled="disabled"','')}
                   maxlength="${maxlength}"
                   size="${size}">
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