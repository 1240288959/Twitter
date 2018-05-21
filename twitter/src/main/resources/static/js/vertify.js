function countTime() {
    var count=4;
    $("#a").text("还有5s自动跳转");
    setInterval(function () {
        $("#a").text("还有"+count+"s自动跳转");
        console.log(count);
        if(count==0){
            location.href="/tologin";
        }
        count--;
    },1000)
}