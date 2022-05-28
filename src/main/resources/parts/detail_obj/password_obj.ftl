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
            <#if !readonly >
                <#if view_passwordold >
                    <button type="button"
                            class="btn btn-sm btn-outline-danger"
                            data-action="${password_change}"
                            data-info="${password_info}"
                            data-field="${field}"
                            data-label="${label}"
                            onclick="javascript:$('#x_modalpassword_password_old').removeClass('d-none');$('#x_modalpassword_password_old_label').removeClass('d-none');$('#x_modalpassword_title').text(this.getAttribute('data-label'));$('#x_modalpassword_info').text(this.getAttribute('data-info'));$('#x_modalpassword_password_repeat').val('');$('#x_modalpassword_password').val('');$('#x_modalpassword_password_old').val('');$('#x_modalpassword_field').val(this.getAttribute('data-field'));$('#x_modalpassword_action').val(this.getAttribute('data-action'));$('#modalpassword').modal('show');"
                    >${button_password_edit}</button>
                <#else>
                    <button type="button"
                            class="btn btn-sm btn-outline-danger"
                            data-action="${password_change}"
                            data-info="${password_info}"
                            data-field="${field}"
                            data-label="${label}"
                            onclick="javascript:$('#x_modalpassword_password_old').addClass('d-none');$('#x_modalpassword_password_old_label').addClass('d-none');$('#x_modalpassword_title').text(this.getAttribute('data-label'));$('#x_modalpassword_info').text(this.getAttribute('data-info'));$('#x_modalpassword_password_repeat').val('');$('#x_modalpassword_password').val('');$('#x_modalpassword_field').val(this.getAttribute('data-field'));$('#x_modalpassword_action').val(this.getAttribute('data-action'));$('#modalpassword').modal('show');"
                    >${button_password_edit}</button>
                </#if>
            <#else>
                <button type="button" class="btn btn-outline-danger" disabled="disabled" >${button_password_edit}</button>
            </#if>
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