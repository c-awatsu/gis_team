var map;
var tileLayer;
var staticLat = 42.828816;
var staticLon = 141.650705;

function drawMap() {
    //地図の初期表示座標とズーム度を設定
    map = L.map('map').setView([staticLat, staticLon], 13);

    //実際に表示するタイルデータを取得
    tileLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
        maxZoom: 19
    });
    //地図にタイルデータを追加
    tileLayer.addTo(map);
}