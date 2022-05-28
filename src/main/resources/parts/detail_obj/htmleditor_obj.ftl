<li class="list-inline-item w-100">
    <#if htmlup?has_content >
        <ul class="list-inline form-element-row-up">
            <li class="list-inline-item form-element">${htmlup}</li>
        </ul>
    </#if>

    <ul class="list-inline form-element-row">
        <label class="control-label ${classlabel}" >${label}</label>
    </ul>
    <ul class="${htmllinecss}">
        <li class="list-inline-item w-100"><div class="div-htmlarea-preview">${value}</div>
        </li>
    </ul>

    <ul class="list-inline float-right">
        <#list buttons as item>
            <li class="list-inline-item mr-3">${item}</li>
        </#list>
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