function upload_click() {
    $("#fileupload").click();
}

function fileupload_change() {
    $.ajaxFileUpload({
        url:"/setImage",
        secureuri:false,
        fileElementId:'fileupload',
        success:function (msg) {
            location.reload();
        }
    });

}