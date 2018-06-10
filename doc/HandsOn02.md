2.Leafletで地図にピン(吹き出し付き)を出してみる

`webapp/js`以下に作成したmap.jsを変更する

```javascript
var map;
var tileLayer;
var marker;
var staticLat = 42.828816;
var staticLon = 141.650705;

function drawMap() {
    /*残りは変更なしで下記のみ追加*/
    setMarker();
} 

function setMarker(){
    marker = L.marker([staticLat, staticLon], {}).addTo(map).bindPopup('吹き出しが表示されます');
}
```

**実行結果**
![地図](https://github.com/c-awatsu/gis_team/blob/master/doc/HandsOn02.jpg)