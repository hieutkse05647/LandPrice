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
        zoom: 15,
        mapTypeControl: true,
          mapTypeControlOptions: {
              style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
              position: google.maps.ControlPosition.TOP_CENTER
          },
          zoomControl: true,
          zoomControlOptions: {
              position: google.maps.ControlPosition.LEFT_CENTER
          },
          scaleControl: true,
          streetViewControl: false
    });
    input = document.getElementById('searchForm:pac-input');
//    input = document.getElementsByClassName('search-location');
    var searchBox = new google.maps.places.SearchBox(input);
//    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
    
    var panelDetail = document.getElementById('panel-detail');
    map.controls[google.maps.ControlPosition.RIGHT_CENTER].push(panelDetail);
    
    map.addListener('bound_changed',function(){
        searchBox.setBounds(map.getBounds());
    });
    
    var markers = [];
    searchBox.addListener('places_changed', function(){
        var places = searchBox.getPlaces();
        if (places.length === 0){
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
    map.data.addListener('click', function(event){
        PF('accordian-widgetVar').select(event.feature.getProperty('id'));
    });

    var dataMap = document.getElementById('searchForm:data-map');
    console.log(JSON.parse(dataMap.value));
    dataLayer = map.data.addGeoJson(JSON.parse(dataMap.value));
    

}
var dataLayer;
function log(data){
    console.log(data);
    dataLayer = map.data.addGeoJson(data);

}
function remove(){
    if (dataLayer != null)
    for (var i = 0; i < dataLayer.length; i++)
                map.data.remove(dataLayer[i]);
}
function updateMarker(){
    var dataMap = document.getElementById('searchForm:data-map');
    console.log(JSON.parse(dataMap.value));
    dataLayer = map.data.addGeoJson(JSON.parse(dataMap.value));
}
function putPanel(){
    var panelDetail = document.getElementById('panel-detail');
    map.controls[google.maps.ControlPosition.RIGHT_CENTER].push(panelDetail);
}


