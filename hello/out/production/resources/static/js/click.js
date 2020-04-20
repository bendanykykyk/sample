$(document).ready(function() {

    //添加用户信息,点击事件
    $('#submit_btn').click(function() {

        var myDate = new Date();
        var obj={};
        obj.account=$("#account").val();
        obj.password=$("#password").val();
        obj.userName=$("#userName").val();
        obj.age=$("#age").val();
        obj.sex=$("#sex").val();

        var description = "";
        for(var i in obj){
            var property=obj[i];
            description+=i+" = "+property+"\n";
        }
        console.log(description);

        $.ajax({
            type: "POST",
            url: "/insert",
            dataType: "json",
            data:JSON.stringify(obj),
            contentType : "application/json",
            success: function (data) {
                console.log(data)
            },
            error: function (e) {
                console.log(e)
            }
        })
    })
})