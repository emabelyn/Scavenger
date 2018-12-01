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


//------------------- marker test with popup ---------------------------
//var marker = L.marker([36.068196, -79.809081]).addTo(map).openPopup();
//marker.bindPopup("test POI for scavenger creation. center of map").openPopup();
//var popup = L.popup();

//------------------------- location ---------------------------- 
function onLocationFound(e) {
    var radius = e.accuracy / 2;

    L.marker(e.latlng).addTo(map)
    .bindPopup("Current Location").openPopup();

    L.circle(e.latlng, radius).addTo(map);
}

function onLocationError(e) {
    alert(e.message);
}

map.on('locationfound', onLocationFound);
map.on('locationerror', onLocationError);

//sets map view to user location
map.locate({setView: true});
//--------------- POI marker creation + radius  --------------------
var markers = [];
var c;

function clue(){
    var x = document.createElement("BUTTON");
    var t = document.createTextNode("Click me");
    x.appendChild(t);
    document.body.appendChild(x);
    c = prompt("Enter clue:");
    if(c == null){
    c = "no clue data"
     }
}

//add marker at click location with popup describing coordinates
function addPOI(e){    
    var id;
    clue();
    if(markers.length<1){
        id=0;
    }else{
        id=markers[markers.length-1]._id+1;
    }    

    //POI marker and radius created on tap; POI marker assigned id num
    var markerPOI = new L.marker(e.latlng, {draggable:false}).addTo(map);
    markerPOI._id=id;
    var markerRadius = new L.circle(e.latlng, {radius: 50}).addTo(map);
   
    //bind radius circle to POI marker ^
    markerPOI.circle = markerRadius;

    markerPOI.bindPopup(
        'Point of Interest Created </br>' +
        'Clue Information: ' + c.toString() + ' </br>' +
        //'lat, lng at this POI: ' +
        //e.latlng.toString() + '</br>' +
        '<button onclick="removePOI(' + id +
        ')"> Remove POI</button></br>').openPopup();

    map.addLayer(markerPOI);
    markers.push(markerPOI);
}



//delete POI marker and corresponding radius circle
function removePOI(id){
    console.log(markers);
    var newMarkers = [];

    markers.forEach(function(marker){
        if(marker._id==id){
            map.removeLayer(marker);
            map.removeLayer(marker.circle);
        }else{
            newMarkers.push(marker);
            newMarkers.push(marker.circle);
        }
    })
}


//run through addPOI on click
map.on('click', addPOI);
