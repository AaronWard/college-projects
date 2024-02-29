/**
 * Created by aaron_000 on 30/11/2016.
 */

/**
 * Used to load google maps API
 */
function loadMap() {
    //Initialize Google Map when loaded
    var mapCanvas = document.getElementById("map");
    var mapOptions = {
        center: new google.maps.LatLng(53.404819, -6.378041), //Coordinates of ITB
        zoom: 16
    };
    var map = new google.maps.Map(mapCanvas, mapOptions);
}

var array  = ['#a', '#linc', '#c', '#e', '#d', '#f'];
/**
 * Used to change the map photo depending on what
 * button the user has clicked
 */
$(document).ready(function() {
    var img = $("#mapImage");
    //If the person clicks Aontas
    $("#a").click(function(){
        var buttonClicked = $("#a");
        if (img.attr('src') != '../images/a.JPG') {
            $(img.attr('src', '../images/a.JPG'));
            generateFullMapButton();
            hideButton(buttonClicked);
        }
    });

    $("#linc").click(function(){
        var buttonClicked = $("#linc");
        if (img.attr('src') != '../images/linc.JPG') {
            $(img.attr('src', '../images/linc.JPG'));
            generateFullMapButton();
            hideButton(buttonClicked);
        }
    });

    $("#c").click(function(){
        var buttonClicked = $("#c");
        if (img.attr('src') != '../images/c.JPG') {
            $(img.attr('src', '../images/c.JPG'));
            generateFullMapButton();
            hideButton(buttonClicked);
        }
    });

    //D block
    $("#d").click(function(){
        var buttonClicked = $("#d");
        if (img.attr('src') != '../images/d.JPG') {
            $(img.attr('src', '../images/d.JPG'));
            generateFullMapButton();
            hideButton(buttonClicked);
        }
    });

    // E Block
    $("#e").click(function(){
        var buttonClicked = $("#e");
        if (img.attr('src') != '../images/e.JPG') {
            $(img.attr('src', '../images/e.JPG'));
            generateFullMapButton();
            hideButton(buttonClicked);
        }
    });

    //F block
    $("#f").click(function(){
        var buttonClicked = $("#f");
        if (img.attr('src') != '../images/f.JPG') {
            $(img.attr('src', '../images/f.JPG'));
            generateFullMapButton();
            hideButton(buttonClicked);
        }
    });

    //if the person wants to go back to full screen mode
    $("#fullButton").click(function(){
        $("#mapImage").attr('src', 'http://2.bp.blogspot.com/-ySm-Du-LLF4/Ubcn6Nvjc3I/AAAAAAAAABM/y9KQE39Pb9M/s1600/campus_map.gif');
        $('#full').remove();
        var buttonClicked = $("#fullButton");
        showButtons(buttonClicked);
    });
});

/**
 * if the user clicks on a building, the buttons
 * are hidden besides the full map button
 */
function hideButton(){
    for(var i = 0; i < array.length; i++){
        $(array[i]).hide();
    }
}

/**
 * If the user clicks on full map, the buttons
 * are reshown
 * @param buttonClicked
 */
function showButtons(buttonClicked){
    for(var i = 0; i < array.length; i++){
        if(array[i] != buttonClicked){
            $(array[i]).show();
        }
    }
}
/**
 * Add a button to the div
 */
function generateFullMapButton() {
    $('#fullButton').append("<a href='#' class='ui-btn' style=' background:#34495e; color: white;' id='full'>Full map</a>");
}


