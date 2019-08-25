/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var map;
var infoWindow;
var selectedLayer;
var selectedMarker = [];
var northWest,northEast,southEast,southWest;
var listCoordinates = [];
function initMap() {
    var latitude = 21.012633; // YOUR LATITUDE VALUE
    var longitude = 105.527423; // YOUR LONGITUDE VALUE

    var myLatLng = {lat: latitude, lng: longitude};

    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 14,
        disableDoubleClickZoom: true, // disable the default map zoom on double click
    });

    // Create new marker on single click event on the map
    google.maps.event.addListener(map, 'click', function (event) {
        var marker = new google.maps.Marker({
            position: event.latLng,
            map: map,
            title: event.latLng.lat() + ', ' + event.latLng.lng()
        });

        selectedMarker.push(marker);

        var content = document.getElementById('form:fname').value;
        document.getElementById('form:fname').value = content + event.latLng.lat() + "," + event.latLng.lng() + "\n\n";
        
        listCoordinates.push([event.latLng.lat(),event.latLng.lng()]);
        document.getElementById('form:json').value = JSON.stringify(listCoordinates);
    });
    google.maps.event.addListener(map, 'bounds_changed', function () {
        var bounds = map.getBounds();
        var ne = bounds.getNorthEast();
        var sw = bounds.getSouthWest();
        northWest = ne.lat() + ',' + sw.lng();
        northEast = ne.lat() + ',' + ne.lng();
        southEast = sw.lat() + ',' + ne.lng();
        southWest = sw.lat() + ',' + sw.lng();
        getFourCorner();
    });
    var input = document.getElementById('pac-input');
        var searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function() {
          searchBox.setBounds(map.getBounds());
        });

        var markers = [];
        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
        searchBox.addListener('places_changed', function() {
          var places = searchBox.getPlaces();

          if (places.length == 0) {
            return;
          }

          // Clear out the old markers.
          markers.forEach(function(marker) {
            marker.setMap(null);
          });
          markers = [];

          // For each place, get the icon, name and location.
          var bounds = new google.maps.LatLngBounds();
          places.forEach(function(place) {
            if (!place.geometry) {
              console.log("Returned place contains no geometry");
              return;
            }
            var icon = {
              url: place.icon,
              size: new google.maps.Size(71, 71),
              origin: new google.maps.Point(0, 0),
              anchor: new google.maps.Point(17, 34),
              scaledSize: new google.maps.Size(25, 25)
            };

            // Create a marker for each place.
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
}
function draw() {
    //Get data from textarea
    var cordi = [];
    cordi = document.getElementById('form:fname').value.trim().split("\n\n");
    for (var i = 0; i < cordi.length; i++) {
        var content = cordi[i];
        cordi[i] = [];
        cordi[i] = content.split(",");
    }
    //Parse to LatLong data
    var fixedPath = [];
    for (var i = 0; i < cordi.length; i++) {
        fixedPath.push({
            lat: parseFloat(cordi[i][0]),
            lng: parseFloat(cordi[i][1])
        });
    }
    //Draw
    var shape = new google.maps.Polygon({
        paths: fixedPath,
        strokeColor: '#FF0000',
        strokeOpacity: 0.8,
        strokeWeight: 2,
        fillColor: '#FF0000',
        fillOpacity: 0.35
    });
    shape.setMap(map);
    shape.addListener('click', show);
}

function removeMarker() {
    for (var i = 0; i < selectedMarker.length; i++) {
        selectedMarker[i].setMap(null);
    }
    selectedMarker = [];
}

function show(event) {
    selectedLayer = this;
    infoWindow = new google.maps.InfoWindow;

    var contentString = 'Hello World <br/>' + "<button type='button' onclick='remove()' id='btnRemove'>Remove</button>";

    // Replace the info window's content and position.
    infoWindow.setContent(contentString);
    infoWindow.setPosition(event.latLng);

    infoWindow.open(map);
}

function remove() {
    selectedLayer.setMap(null);
    infoWindow.close();
}

function clearInput() {
    document.getElementById('form:fname').value = '';
    document.getElementById('form:name').value = '';
    document.getElementById('form:description').value = '';
    document.getElementById('form:minValue').value = '0.0';
    document.getElementById('form:maxValue').value = '0.0';
    document.getElementById('form:currentPrice').value = '0.0';
    listCoordinates = [];
    removeMarker();
}
function getFourCorner(){
    document.getElementById('form:northWest').value = northWest;
    document.getElementById('form:northEast').value = northEast;
    document.getElementById('form:southEast').value = southEast;
    document.getElementById('form:southWest').value = southWest;
}