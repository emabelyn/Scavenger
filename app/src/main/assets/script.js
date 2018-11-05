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
marker.bindPopup("test POI for scavenger creation. center of map").openPopup();
var popup = L.popup();


    var markers = [];
//function to add marker at click location with popup describing coordinate
function addPOI(e){    
    var id;
    if(markers.length<1){
        id=0;
    }else{
        id=markers[markers.length-1]._id+1;
    }    

        var markerPOI = new L.marker(e.latlng, {draggable:false}).addTo(map);
        markerPOI._id=id;
        markerPOI.bindPopup('lat, lng at this POI: ' + 
            e.latlng.toString() + '</br>' +
            '<poi clue ssdfkjlfdskl </br>' +
            '<button onclick="inputClue"> Edit Clues</button> ' +
            '<button onclick="removePOI(' + id + 
            ')"> Remove POI</button></br>').openPopup();
        map.addLayer(markerPOI);
        markers.push(markerPOI);
    }

function editClue(c){

}

function removePOI(id){
    console.log(markers);
    var newMarkers = [];

    markers.forEach(function(marker){
        if(marker._id==id){
            map.removeLayer(marker);
        }else{
            newMarkers.push(marker);
        }
    })
    markers = newMarkers;
}


//run through addPOI on click
map.on('click', addPOI);
