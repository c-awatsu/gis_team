var map;
var tileLayer;
var marker;
var staticLat = 42.828816;
var staticLon = 141.650705;

//引数を追加
function drawMap(hukidasi) {
    map = L.map('map').setView([staticLat, staticLon], 13);

    tileLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
        maxZoom: 19
    });
    tileLayer.addTo(map);
    //引数を渡して関数呼び出し
    setMarker(hukidasi);
}
//引数を追加
function setMarker(hukidasi){
    marker = L.marker([staticLat, staticLon], {}).addTo(map).bindPopup(hukidasi);
}