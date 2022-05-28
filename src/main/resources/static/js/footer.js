$(document).ready(function() {

    $('.loading').on('click', function () {
        loading();
    });

    $('.wconfirm').confirm({
        type: 'orange',
        buttons: {
            ok: {
                btnClass: 'btn-success',
                keys: ['enter'],
                action: function () {
                    loading();
                    location.href = this.$target.attr('href');
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

});

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})

view_menu_vertical=false;
$('#show_menu_vertical').click(function() {
    $(".slide-menu").removeClass("animate__slideOutLeft");
    $(".slide-menu").addClass("animate__slideInLeft");
    $(".slide-menu").show();
    setTimeout("viewMenuVertical()",800);
});

$('#hide_menu_vertical').click(function() {
    hideMenuVertical();
});

$('.container-fluid').mouseover(function() {
    if(view_menu_vertical) hideMenuVertical();
});

function viewMenuVertical() {
    view_menu_vertical=true;
}

function hideMenuVertical() {
    $(".slide-menu").removeClass("animate__slideInLeft");
    $(".slide-menu").addClass("animate__slideOutLeft");
    view_menu_vertical=false;
}

function setFixedTopHeight(){
    $("#fixed-top-height").height($(".fixed-top").height() + 16);
}

$( window ).resize(function() {setFixedTopHeight(); });
setFixedTopHeight();

window.addEventListener( "pageshow", function ( event ) {
    var historyTraversal = event.persisted || ( typeof window.performance != "undefined" && window.performance.navigation.type === 2 );
    if ( historyTraversal ) {
        window.location.reload();
    }
});
