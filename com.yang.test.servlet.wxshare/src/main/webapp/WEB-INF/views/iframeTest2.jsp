<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>22
</body>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
wx.config({
    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: 'wxcc79d67b43519897', // 必填，公众号的唯一标识     
　　timestamp: ${timestamp}, // 必填，生成签名的时间戳
    nonceStr: '${nonceStr}', // 必填，生成签名的随机串
    signature: '${signature}',// 必填，签名，见附录1
    jsApiList: [//需要调用的JS接口列表
        'checkJsApi',//判断当前客户端版本是否支持指定JS接口
        'onMenuShareTimeline',//分享给好友
        'onMenuShareAppMessage'//分享到朋友圈
    ]
});
wx.ready(function () {
    var link = window.location.href;
    var protocol = window.location.protocol;
    var host = window.location.host;
    //分享朋友圈
    wx.onMenuShareTimeline({
        title: '这是一个自定义的标题！',
        link: link,
        imgUrl: protocol+'//'+host+'/resources/images/icon.jpg',// 自定义图标
        trigger: function (res) {
            // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回.
            alert('click shared');
        },
        success: function (res) {
            alert('shared success');
            //some thing you should do
        },
        cancel: function (res) {
            alert('shared cancle');
        },
        fail: function (res) {
            alert(JSON.stringify(res));
        }
    });
    //分享给好友
    wx.onMenuShareAppMessage({
        title: '这是一个自定义的标题！', // 分享标题
        desc: '这是一个自定义的描述！', // 分享描述
        link: link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: protocol+'//'+host+'/resources/images/icon.jpg', // 自定义图标
        type: 'link', // 分享类型,music、video或link，不填默认为link
        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        success: function () {
            // 用户确认分享后执行的回调函数
            alert("123");
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        	console.log(778)
        }
    });
    wx.error(function (res) {
        alert(res.errMsg);
    });                
    wx.checkJsApi({
        jsApiList: ['onMenuShareTimeline'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
        success: function(res) {
        	console.log(123)
        }
    });
	console.log(110)
});
</script>
</html>