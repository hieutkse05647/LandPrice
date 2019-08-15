/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global google */

var map;
var input;


function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 21.012633, lng: 105.527423},
        zoom: 15
    });
    input = document.getElementById('pac-input');
//    input = document.getElementsByClassName('search-location');
    var searchBox = new google.maps.places.SearchBox(input);
//    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
    
    map.addListener('bound_changed',function(){
        searchBox.setBounds(map.getBounds());
    });
    
    var markers = [];
    searchBox.addListener('places_changed', function(){
        var places = searchBox.getPlaces();
        if (places.length == 0){
            return;
        }
        markets = [];
        var bounds = new google.maps.LatLngBounds();
        places.forEach(function(place){
            if (!place.geometry){
                console.log("Return place contain no geometry");
                return;
            }
            var icon = {
                url: place.icon,
                size: new google.maps.Size(71,71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(25, 25)
            };
            markers.push(new google.maps.Marker({
                map: map,
                icon: icon,
                title: place.name,
                position: place.geometry.location
            }));
            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });
    map.data.setStyle(function(feature) {
          return /** @type {google.maps.Data.StyleOptions} */({
            fillColor: feature.getProperty('color'),
            strokeWeight: 1
          });
        });

        // Set mouseover event for each feature.
        map.data.addListener('mouseover', function(event) {
          document.getElementById('info-box').textContent = event.feature.getProperty('letter');
        });
}
function log(data){
    console.log(data);
    map.data.addGeoJson(data);
}


