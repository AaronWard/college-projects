var courseTypes = ['Computing','Engineering','Business', 'Social Care', 'Creative Digital Media'];

/**
 * Used to dynamically create a collapsible set and assign title according
 * to courses array
 */
$(document).ready(function () {
        for (var i = 0; i < courseTypes.length; i++) {
            $('<div data-role="collapsible">' +
                '<h3>' + courseTypes[i] + '</h3>' +
                '<div data-role="collapsible-set" id="list' + i + '"></div>' +
                '</div>').appendTo('#courseDropDown');
        }
        /**
         * Function that retrieves json data from files and dynamically inserts it into
         * collapsibles - Includes titles, code and description
         */

        /** Computing **/
        $.getJSON("../Mobile/JSON/Computing.JSON", function (object) {
            $.each(object, function (i, value) {
                $('<div data-role="collapsible" id="title"><h1>' + value.name + '</h1>' +
                    '<p><b>' + value.code + '</b></p><br><p>' + value.description + '</p></div></div>').appendTo('#list0');
            });
        });


        /** Engineering **/
        $.getJSON("../JSON/Engineering.JSON", function (object) {
            $.each(object, function (i, value) {
                $('<div data-role="collapsible" id="title"><h1>' + value.name + '</h1>' +
                    '<p><b>' + value.code + '</b><br>' + value.description+ '</p></div>').appendTo('#list1');
            });
        });

        /** Business **/
        $.getJSON("../JSON/Business.JSON", function (object) {
            $.each(object, function (i, value) {
                $('<div data-role="collapsible" id="title"><h1>' + value.name + '</h1>' +
                    '<p><b>' + value.code + '</b><br>' + value.description + '</p></div>').appendTo('#list2');
            });
        });

        /** Social Care **/
        $.getJSON("../JSON/Socialcare.JSON", function (object) {
            $.each(object, function (i, value) {
                $('<div data-role="collapsible" id="title"><h1>' + value.name + '</h1>' +
                    '<p><b>' + value.code + '</b><br>' + value.description + '</p></div>').appendTo('#list3');
            });
        });

        $.getJSON("../JSON/Creativedigitalmedia.JSON", function (object) {
            $.each(object, function (i, value) {
                $('<div data-role="collapsible" id="title"><h1>' + value.name + '</h1>' +
                    '<p><b>' + value.code + '</b><br>' + value.description + '</p></div>').appendTo('#list4');
            });
        });
});
