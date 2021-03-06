var map;
var tileLayer;
var marker;
var markers = [];
var staticLat = 42.828816;
var staticLon = 141.650705;
//以下の4つを追加
var edgeLatlon;
var isChitose;
var checkBounds;
var edgeRect;

function drawMap() {
    map = L.map('map').setView([staticLat, staticLon], 13);

    tileLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
        maxZoom: 19
    });
    tileLayer.addTo(map);
    setClickEvent();
    setCurrentLocationMarker();
}

function setMarker(hukidasi) {
    markers = [];
    for (var i = 0; i < 5; i++) {
        marker = L.marker([staticLat + (i / 150), staticLon + (i / 150)],
            {
                icon: L.ExtraMarkers.icon({
                    icon: 'fa-number',
                    shape: 'penta',
                    prefix: 'fa',
                    markerColor: 'green',
                    number: i + 1,
                })
            }).addTo(map).bindPopup(hukidasi);
        markers.push(marker);
    }
}

function setClickEvent() {
    map.on("click", function (e) {
        //クリックされた場所の座標を取得
        var latlng = e.latlng;
        isChitoseLatlon(latlng);
        if (isChitose) {
            // その座標を元にマーカーを作成
            marker = L.marker([latlng.lat, latlng.lng],
                {
                    icon: L.ExtraMarkers.icon({
                        icon: 'fa-number',
                        shape: 'penta',
                        prefix: 'fa',
                        markerColor: 'green',
                    })
                }).addTo(map);
            markers.push(marker);
            console.log(latlng.lat,latlng.lng);
            sendLatLon(latlng.lat, latlng.lng, "千歳市内");
        } else {
            sendLatLon(latlng.lat, latlng.lng, "千歳市外");
        }
    });

}

function deleteMarker() {
    for (var i = 0; i < markers.length; i++) {
        map.removeLayer(markers[i]);
    }
}

function setCurrentLocationMarker() {
    //GeolocationAPIのgetCurrentPosition関数を使って現在地を取得
    navigator.geolocation.getCurrentPosition(function (currentPosition) {
        var lat = currentPosition.coords.latitude;
        var lon = currentPosition.coords.longitude;

        marker = L.marker([lat, lon], {
            icon: L.ExtraMarkers.icon({
                icon: 'fa-number',
                shape: 'penta',
                prefix: 'fa',
                markerColor: 'green',
            })
        }).addTo(map);
        markers.push(marker);

    }, function (error) {
        console.log(error);
        alert('位置情報が取得できません');
    });
}

function setContributionMarker(contributionList) {
    for (var i = 0; i < contributionList.length; i++) {
        //Javaから渡されたBeanを引数で渡された場合は引数名.Beanでの変数名で変数を呼び出すことができる
        marker = L.marker([contributionList[i].latlon.center.y, contributionList[i].latlon.center.x], {}).addTo(map).bindPopup(contributionList[i].postTime);
        markers.push(marker);
    }
}

//追加
function isChitoseLatlon(latlng) {
    //千歳の外周の座標が入ったjsonを読み込み
    getChitoseEdgeJson();
    //外周の形の多角形を作成
    edgeRect = L.polyline(edgeLatlon);
    //その多角形の面積を求める
    checkBounds = edgeRect.getBounds();
    //その範囲内にクリックした地点が入っているかを判定
    isChitose = checkBounds.contains(latlng);

}
//追加
function getChitoseEdgeJson() {
    $.ajax({
        url: './../js/chitoseArea.json',
        dataType: 'json',
        async: false,
        success: function (json) {//読み込みがうまくいくとこの処理が走る
            edgeLatlon = json.edgeLatlon;
        }
    });
}