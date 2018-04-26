var nowPic;

function showPictureModal(picArea){
    console.log("调用了showPictureModal方法");
    nowPic=$(picArea);
    $("#imgShow").attr("src",$(picArea).attr("src"));
    $("#pictureShowModal").modal("show");
}

function prevPicture(){
    var prevPic=$(nowPic).prev("img").last();
    if(prevPic.attr("src")==null||prevPic.attr("src")==""){
        prevPic=$(nowPic).parent().children().last("img");
    }
    console.log($(nowPic).attr("src"));
    $("#imgShow").attr("src",""+$(nowPic).attr("src"));
}

function nextPicture(){
    var nextPic=$(nowPic).next("img").first();
    if(nextPic.attr("src")==null||nextPic.attr("src")==""){
        nextPic=$(nowPic).parent().children().first("img");
    }
    nowPic=nextPic;
    $("#imgShow").attr("src",$(nowPic).attr("src"));
}