//map name, remove +/- control, set default map view, max zoom level
var map = L.map('map', {zoomControl: false}).setView([36.068196, -79.809081], 20);

//add +/- zoom control with home button to recenter map
var zoomHome = L.Control.zoomHome();
zoomHome.addTo(map);

//map creation
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoiYXIyYW5hIiwiYSI6ImNqbmk0aXZ2MjBreGMza28zc3NxYzk5dGYifQ.X9JsZz5dRnJpQQztg0kE1A', {
    maxZoom: 18,
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
        '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
        'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    id: 'mapbox.streets'
}).addTo(map);


//---------------------------------------------------------------

//marker test with popup
var marker = L.marker([36.068196, -79.809081]).addTo(map).openPopup();
marker.bindPopup("center of map. participant screen").openPopup();
var popup = L.popup();


//run through addPOI on click
map.on('click', addPOI);
