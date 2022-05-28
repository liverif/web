<li class="list-inline-item m-0">
    <#if htmlup?has_content >
        <ul class="list-inline form-element-row-up">
            <li class="list-inline-item form-element">${htmlup}</li>
        </ul>
    </#if>
    <#if label?has_content >
        <ul class="list-inline">
            <label class="control-label ${classlabel}" >${label}</label>
        </ul>
    </#if>
    <ul class="${htmllinecss}">
        <li class="list-inline-item">
            <div class="${htmlcss}" >${value}</div>
        </li>
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