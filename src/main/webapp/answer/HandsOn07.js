var map;
var tileLayer;
var marker;
var markers = [];
var staticLat = 42.828816;
var staticLon = 141.650705;

function drawMap() {
    map = L.map('map').setView([staticLat, staticLon], 13);

    tileLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
        maxZoom: 19
    });
    tileLayer.addTo(map);
    //
}

function setMarker(hukidasi){
    for(var i=0;i<5;i++){
        marker = L.marker([staticLat+(i/150), staticLon+(i/150)], {}).addTo(map).bindPopup(hukidasi);
        markers.push(marker);
    }

}


function deleteMarker(){
    //変更
    for(var i=0;i<5;i++){
        map.removeLayer(markers[i]);
    }
}