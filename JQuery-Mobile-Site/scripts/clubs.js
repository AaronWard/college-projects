/**
 * Created by aaron_000 on 03/12/2016.
 */
var clubTypes = ['Computing Society','Colours LGBTQI+* Society', 'GAA', 'Futsal', 'Harry Potter Society'];

$(document).ready(function () {
    for (var i = 0; i < clubTypes.length; i++){
        $('<div data-role="collapsible">'+
                    '<h3>' + clubTypes[i] + '</h3>'+
                        '<div id="club' + i + '"></div> </div>' +
           '</div>').appendTo('#clubDropDown');
    }
    jsonFeed();
});


function jsonFeed() {
    $.getJSON("../JSON/clubs.JSON", function (object) {
        $.each(object, function (i, value) {
            if(value.name === clubTypes[0]) {
                $('<p class="des">' + value.description + '</p>').appendTo('#club0');
            }
            if(value.name === clubTypes[1]) {
                $('<p class="des">' + value.description + '</p>').appendTo('#club1');
            }
            if(value.name === clubTypes[2]) {
                $('<p class="des">' + value.description + '</p>').appendTo('#club2');
            }
            if(value.name === clubTypes[3]) {
                $('<p class="des">' + value.description + '</p>').appendTo('#club3');
            }
            if(value.name === clubTypes[4]) {
                $('<p class="des">' + value.description + '</p>').appendTo('#club4');
            }


        });
    });
}




