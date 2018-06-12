var map;
var tileLayer;
var staticLat = 42.828816;
var staticLon = 141.650705;

function drawMap(hukidasi) {
    map = L.map('map').setView([staticLat, staticLon], 13);

    tileLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
        maxZoom: 19
    });
    tileLayer.addTo(map);
    setMarker(hukidasi);
}

function setMarker(hukidasi){
    L.marker([staticLat, staticLon], {}).addTo(map).bindPopup(hukidasi);
}