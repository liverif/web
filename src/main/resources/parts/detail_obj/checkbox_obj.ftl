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
            <input type="checkbox"
                   class="${classinput}"
                   id="NO_${field}"
                   name="NO_${field}"
                   ${readonly?string('disabled="disabled"','')}
                   ${value?string('checked="checked"','')}
                   onclick="javascript:if ($('#NO_${field}').is(':checked')){$('#${field}').val('true');}else{$('#${field}').val('false');}">
            <input type="hidden" name="${field}" id="${field}" value="${value?string('true','false')}">
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