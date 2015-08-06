 function showMapData(orderid) {
        //弹出地图时，禁用滚动条
        document.documentElement.style.overflow = "hidden";
        document.body.style.overflow = "hidden";
        var paramaters = { "OrderId": orderid };
        var url = "/Order/OrderMap";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
                adminjs.openwinbox('#orderMapShow');//需要先弹层，否则地图第二次加载有问题，且无法显示连线
                showMap(result);
            },
            error: function (b)
            {
                alert(b.responseText);
            }
        });
}
function showMap(jsonstr) {
        if (jsonstr == null) {
            alert("没取到订单的地图数据！");
        } else {
            // 百度地图API功能
            //发单的坐标作为地图的中心点
            var map = new BMap.Map("map_contain");
            //没有发单经纬度时，地图中心点设置为天安门
            //var centerLongitude = 116.3972282409668;
            //var centerLatitude = 39.90960456049752;
            var centerLongitude = jsonstr.PubLongitude;
            var centerLatitude = jsonstr.PubLatitude;
            if (jsonstr.PubLongitude != 0 && jsonstr.PubLatitude!=0) {
              //也许需要重新计算中心点
            }
            map.centerAndZoom(new BMap.Point(centerLongitude, centerLatitude), 16);
            //map.centerAndZoom(new BMap.Point(116.404, 39.915), 16);
            map.enableScrollWheelZoom();

            var polyline = new BMap.Polyline([
                new BMap.Point(jsonstr.PubLongitude, jsonstr.PubLatitude),
                new BMap.Point(jsonstr.GrabLongitude, jsonstr.GrabLatitude),
                new BMap.Point(jsonstr.TakeLongitude, jsonstr.TakeLatitude),
                new BMap.Point(jsonstr.CompleteLongitude, jsonstr.CompleteLatitude)
            ], { strokeColor: "red", strokeWeight: 2, strokeOpacity: 0.5 });   //创建折线
            map.addOverlay(polyline);   //增加折线

            //发单
            var marker = new BMap.Marker(new BMap.Point(jsonstr.PubLongitude, jsonstr.PubLatitude)); // 创建点
            map.addOverlay(marker);
            var label = new BMap.Label("发单时间-" + jsonstr.PubDate, { offset: new BMap.Size(-165, -10) });
            marker.setLabel(label);

            //抢单
            var marker1 = new BMap.Marker(new BMap.Point(jsonstr.GrabLongitude, jsonstr.GrabLatitude)); // 创建点
            map.addOverlay(marker1);
            var label = new BMap.Label("抢单时间-" + jsonstr.GrabTime, { offset: new BMap.Size(20, -10) });
            marker1.setLabel(label);

            //取货
            var marker2 = new BMap.Marker(new BMap.Point(jsonstr.TakeLongitude, jsonstr.TakeLatitude)); // 创建点
            map.addOverlay(marker2);
            var label = new BMap.Label("取货时间-" + jsonstr.TakeTime, { offset: new BMap.Size(20, 20) });
            marker2.setLabel(label);

            //完成
            var marker3 = new BMap.Marker(new BMap.Point(jsonstr.CompleteLongitude, jsonstr.CompleteLatitude)); // 创建点
            map.addOverlay(marker3);
            var label = new BMap.Label("完成时间-" + jsonstr.ActualDoneDate, { offset: new BMap.Size(-165, 20) });
            marker3.setLabel(label);
        }
    }