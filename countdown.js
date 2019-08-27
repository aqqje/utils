<script src="/static/js/qrcode/qrcode.min.js"></script>
<script>
var urlPrefix = "http://"+window.location.host;

    var ptId = '[[${ptId}]]';
    var sjdd = '[[${sjdd}]]';
    var zffs = '[[${zffs}]]';
    var money = '[[${money}]]';
    var content = '[[${content}]]';
    content = encodeURI(encodeURI(content));
    console.log(content)
    //生成二维码
    var qrcode = new QRCode(document.getElementById("ewm"), {
        //这里的跳转路径需要进一步进行升级
        // text: urlPrefix+"/static/zf_hand.html?ptId="+ptId+"&sjdd="+sjdd+"&zffs="+zffs+"&money="+money+"&memberId=",
        text: urlPrefix+"/pay-code?ptId="+ptId+"&content="+content+"&memberId=",
        width: 152,
        height: 152,
        colorDark : "#000000",
        colorLight : "#ffffff",
        correctLevel : QRCode.CorrectLevel.H,
        alt: "请使用对应的付款软件扫描我",
        correctLevel: 3,
    });
	
	
	 /*日期格式化*/
    Date.prototype.pattern=function(fmt) {
        var o = {
            "M+" : this.getMonth()+1, //月份
            "d+" : this.getDate(), //日
            "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
            "H+" : this.getHours(), //小时
            "m+" : this.getMinutes(), //分
            "s+" : this.getSeconds(), //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S" : this.getMilliseconds() //毫秒
        };
        var week = {
            "0" : "/u65e5",
            "1" : "/u4e00",
            "2" : "/u4e8c",
            "3" : "/u4e09",
            "4" : "/u56db",
            "5" : "/u4e94",
            "6" : "/u516d"
        };
        if(/(y+)/.test(fmt)){
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        if(/(E+)/.test(fmt)){
            fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
        }
        for(var k in o){
            if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
        return fmt;
    }

window.onload=clock;
    function clock(){
        var time = "[[${endTime}]]";
        console.log("time:"+time.replace(/\-/g,"\/"));
        var today=new Date(),//当前时间
        h=today.getHours(),
        m=today.getMinutes(),
        s=today.getSeconds();
        var stopTime=new Date(time.replace(/\-/g,"\/")),//结束时间
        stopH=stopTime.getHours(),
        stopM=stopTime.getMinutes(),
        stopS=stopTime.getSeconds();
        var shenyu=stopTime.getTime()-today.getTime(),//倒计时毫秒数
        shengyuD=parseInt(shenyu/(60*60*24*1000)),//转换为天
        D=parseInt(shenyu)-parseInt(shengyuD*60*60*24*1000),//除去天的毫秒数
        shengyuH=parseInt(D/(60*60*1000)),//除去天的毫秒数转换成小时
        H=D-shengyuH*60*60*1000,//除去天、小时的毫秒数
        shengyuM=parseInt(H/(60*1000)),//除去天的毫秒数转换成分钟
        M=H-shengyuM*60*1000;//除去天、小时、分的毫秒数
        S=parseInt((shenyu-shengyuD*60*60*24*1000-shengyuH*60*60*1000-shengyuM*60*1000)/1000)//除去天、小时、分的毫秒数转化为秒

        //检查是否超时
        if(shenyu<=0) {
            //倒计时结束
            document.getElementById("ewm").innerHTML=("支付超时");
            document.getElementById("out-time").innerHTML=("0分0秒"+"<br>");
            // window.location.reload();
            var url = urlPrefix+"/goError?message=支付超时";
            window.location.href = url;
        } else {
            document.getElementById("out-time").innerHTML=(shengyuM+"分"+S+"秒"+"<br>");
            setTimeout(clock,500);
        }
    }
	
	
	
	/*function clock(){
        var date = new Date();
        date = date.pattern("yyyy-MM-dd hh:mm:ss");
        var EndTime= new Date("[[${endTime}]]");
        var NowTime = new Date(date.replace(/-/g, "/"));
        var t =EndTime.getTime() - NowTime.getTime();
        console.log("t:"+t)
        var h=Math.floor(t/1000/60/60),
            m=Math.floor(t/1000/60%60),
            s=Math.floor(t/1000%60);
        h <10 ? h = '0' +h :h =h;
        m <10 ? m = '0' +m :m =m;
        s <10 ? s = '0' +s :s =s;
        if(t < 0){
            //倒计时结束
            //倒计时结束
            document.getElementById("ewm").innerHTML=("支付超时");
            document.getElementById("out-time").innerHTML=("0分0秒"+"<br>");
            var url = urlPrefix+"/goError?message=支付超时";
            window.location.href = url;
        }else{
            document.getElementById("out-time").innerHTML=(m+"分"+s+"秒"+"<br>");
            setTimeout(clock,500);
        }
    }*/









</script>