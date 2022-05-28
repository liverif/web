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
        <li class="list-inline-item form-element m-0">
            <label class="control-label ${classlabel}" >${label}</label>
            <input class="form-control <#if htmlvalue?has_content>d-none</#if>"
                   type="file"
                   id="${field}"
                   name="${'NO_'+field}"
                   ${readonly?string('disabled="disabled"','')}
                   maxlength="${maxlength}"
                   size="${size}">
            <input class="form-control <#if !htmlvalue?has_content>d-none</#if>"
                   type="text"
                   size="40"
                   disabled="disabled"
                   id="${field}_label"
                   value="${htmlvalue}">
        </li>
        <#if !readonly >
            <li class="list-inline-item m-0 <#if htmlvalue?has_content>d-none</#if>"
                id="${field}_upload">
                <button class="form-control btn btn-sm btn-outline-primary detail-inner-row-element"
                        type="button"
                        data-field="${field}"
                        data-warning="${upload_warning}"
                        data-action="${file_action_upload}"
                        onclick="javascript:sentFile(this.getAttribute('data-field'), this.getAttribute('data-action'), this.getAttribute('data-warning'))">
                ${button_upload_sent}
                </button>
            </li>
            <li class="list-inline-item m-0 <#if !htmlvalue?has_content>d-none</#if>"
                id="${field}_delete">
                <button class="form-control btn btn-sm btn-outline-danger detail-inner-row-element"
                        type="button"
                        data-field="${field}"
                        data-action="${file_action_delete}"
                        onclick="javascript:removeFile(this.getAttribute('data-field'), this.getAttribute('data-action'))">
                    ${button_upload_delete}
                </button>
            </li>
        </#if>
        <li class="list-inline-item <#if !htmlvalue?has_content>d-none</#if>"
            id="${field}_view">
            ${linkdownloadfile}
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