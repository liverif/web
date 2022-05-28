
function actionModel(action) {
    loading();
    $('#model_action').val(action);
    $('#model_form').submit();
}

function actionModelConfirm(action, title, content) {
    $.confirm({
        title: title,
        content: content,
        type: 'info',
        buttons: {
            ok: {
                btnClass: 'btn-success',
                keys: ['enter'],
                action: function () {
                    loading();
                    $('#model_action').val(action);
                    $('#model_form').submit();
                }
            },
            cancel: {
                btnClass: 'btn-danger',
                keys: ['enter'],
                action: function () {

                }
            }
        }
    });
}

function formConfirm(formid, title, content) {
    $.confirm({
        title: title,
        content: content,
        type: 'info',
        buttons: {
            ok: {
                btnClass: 'btn-success',
                keys: ['enter'],
                action: function () {
                    loading();
                    $('#'+formid).submit();
                }
            },
            cancel: {
                btnClass: 'btn-danger',
                keys: ['enter'],
                action: function () {

                }
            }
        }
    });
}

function loading(){
    if ($("#loading").length) $("#loading").modal('show');
}

function closeLoading(){
    $("#loading").modal("hide");
}

function sentFile(field, action, warning) {
    var files = $('#'+field).prop('files');
    if (files.length>0) {
        loading();
        var formData = new FormData();
        formData.append('field',field);
        formData.append('file', files[0]);
        $.ajax({
            url: action,
            type: "POST",
            data: formData,
            contentType: false,
            cache: false,
            processData: false,
            beforeSend: function () {
                console.log("Sent date file:", files[0].name);
            },
            success: function (msg) {
                console.log("SUCCESS: ", msg);
                if (msg[0] == 'OK'){
                    setTimeout("closeLoading()",500);
                    //$.alert(msg[0]);
                    $('#'+field+'_upload').addClass('d-none');
                    $('#'+field+'_view').removeClass('d-none');
                    $('#'+field+'_delete').removeClass('d-none');
                    $('#'+field+'_label').val(msg[1]);
                    $('#'+field+'_label').removeClass('d-none');
                    $('#'+field).val('');
                    $('#'+field).addClass('d-none');
                }else {
                    setTimeout("closeLoading()", 500);
                    $.alert(msg[1]);
                }
            },
            error: function (msg) {
                console.log("ERROR: ", msg);
                setTimeout("closeLoading()",500);
                $.alert("ERROR: " + msg);
            }
        });
    }else{
        $.alert(warning);
    }
}

function removeFile(field, action) {
    var formData = new FormData();
    formData.append('field',field);
    $.ajax({
        url: action,
        type: "POST",
        data: formData,
        contentType: false,
        cache: false,
        processData: false,
        success: function (msg) {
            console.log("SUCCESS: ", msg);
            //$.alert(msg);
            $('#'+field+"_upload").removeClass('d-none');
            $('#'+field+"_view").addClass('d-none');
            $('#'+field+"_delete").addClass('d-none');
            $('#'+field).val('');
            $('#'+field+'_label').val('');
            $('#'+field+'_label').addClass('d-none');
            $('#'+field).removeClass('d-none');
        },
        error: function (msg) {
            console.log("ERROR: ", msg);
            $.alert("ERROR: " + msg);
        }
    });
}

function changePassword() {
    var action=$("#x_modalpassword_action").val();
    var field=$("#x_modalpassword_field").val();
    var password_old=$("#x_modalpassword_password_old").val();
    var password=$("#x_modalpassword_password").val();
    var password_repeat=$("#x_modalpassword_password_repeat").val();

    loading();
    var formData = new FormData();
    formData.append('field',field);
    formData.append('password_old',password_old);
    formData.append('password',password);
    formData.append('password_repeat',password_repeat);
    $.ajax({
        url: action,
        type: "POST",
        data: formData,
        contentType: false,
        cache: false,
        processData: false,
        beforeSend: function () {
            console.log("Change password...");
        },
        success: function (msg) {
            console.log("SUCCESS: ", msg);
            if (msg[0] == 'OK') $("#modalpassword").modal('hide');
            setTimeout("closeLoading()", 500);
            $.alert(msg[1]);
            $("#x_modalpassword_password_old").val('');
            $("#x_modalpassword_password").val('');
            $("#x_modalpassword_password_repeat").val('');
        },
        error: function (msg) {
            console.log("ERROR: ", msg);
            setTimeout("closeLoading()",500);
            $.alert("ERROR: " + msg);
        }
    });

}

function selectAjaxItems(field,action,id,value,link){
    $('#'+field).append(new Option(value, id, true, true));
    $('#'+field).select2({
        width: '100%',
        containerCssClass : "form-control-select2",
        ajax: {
            url: action,
            dataType: 'json',
            data: function (data) {
                return {
                    search: data.term,
                    field: field,
                    link: link
                };
            },
            processResults: function (data) {
                console.log(data);
                return {
                    results: data
                };
            }
        }
    });
}

function multiselectAjaxItems(field,action,link){
    $('#'+field).select2({
        width: '100%',
        containerCssClass : "form-control-multiselect2",
        ajax: {
            url: action,
            dataType: 'json',
            data: function (data) {
                return {
                    search: data.term,
                    field: field,
                    link: link
                };
            },
            processResults: function (data) {
                console.log(data);
                return {
                    results: data
                };
            }
        }
    });
}
