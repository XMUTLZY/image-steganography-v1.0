/*
* 登录逻辑实现
* */
phone = '未登录';
phone = window.location.href;
var twodata = phone.split("="); //截取 url中的“=”,获得“=”后面的参数
phone = decodeURI(twodata[1]); //decodeURI解码
if(phone=="undefined"){
    logOrre = '登录/注册';
    phone = '未登录';
    link = 'userLogin.html';
}else{
    logOrre = '退出登录';
    link = 'javascript:;';
}
/*
* 修改登录之后的用户名
* */
var data1={
    phone:phone
}
var html = template(document.getElementById('name_temp').innerHTML,data1);
document.getElementById('username').innerHTML = html;
/*
* 修改登录之后的"登录/注册按钮变更"
* */
var data2={
    logOrre:logOrre
}
var html = template(document.getElementById('logOrre_temp').innerHTML,data2);
document.getElementById('loginOrRegister').innerHTML = html;
/*
* 导航栏
* */
//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function () {
    var element = layui.element;

    //…
});
/*
* 退出登录
* */
function isLogout(){
    if(logOrre == '退出登录'){
        logOrre = '登录/注册';
        phone = '未登录';
        link = 'userLogin.html';
        window.location.href="userIndex.html";
    }
}
/*
* 轮播
* */
layui.use('carousel', function () {
    var carousel = layui.carousel;
    //建造实例
    carousel.render({
        elem: '#test'
        , width: '100%' //设置容器宽度
        , arrow: 'always' //始终显示箭头
        //,anim: 'updown' //切换动画方式
    });
});
/*
* 监听“生成藏入信息后的图片”按钮
* */
$("#btn_generate").click(function (){
    if(phone=='未登录'){
        layer.msg('请您先登录哦！')
    }else{
        generate();
    }
})
function generate(){
    $.ajax({
        url:'addInfo',
        type: 'post',
        data:{
            orginalImage:$("#orginalImage").val(),
            phone:phone,
            inputInfo:$("#inputInfo").val()
        },
        dataType:'html',
        success:function (result) {
            layer.msg("信息藏入成功");
        },
    })
}