var url = 'http://api.flickr.com/services/feeds/photos_public.gne?tags=Institute%20of%20technology%20blanchardstown&format=rest&' +
    'auth_token=72157673339675033-37763d324a7596f6&api_sig=1b8d4dbc82557a25f5bb77853875b7ec' +
    '&tagmode=any&format=json&jsoncallback=?';

$(document).ready(function () {
    showPics();

    /**
     * If the user clicks the button, They are brought
     * back to the top of page
     */
    $("#backToTop").click(function () {
        jQuery(window).scroll(function() {
            if (jQuery(this).scrollTop() > 250) {
                jQuery("#backToTop").fadeIn(500);
            }
        });
        jQuery("#backToTop").click(function(event) {
            event.preventDefault();
            jQuery("body").animate({scrollTop: 0}, 500);
            return false;
        });
    })
});

/**
 * Retrieves a series of photos from the Flickr API and fades them in over
 * 2 seconds. Also Random displays different photos each time
 */
function showPics() {
    $.getJSON(url, function(data){ $("#imageFeed").hide().html(data).fadeIn(2000);

        //Loop through the json response
        $.each(data.items, function(i,item) {
            //Give random photos to the feed each time
            var rand = Math.floor((Math.random() * 1000) + 1);
            if(rand % 2 === 0)
            {
                //Add an image with the src to the imageFeed div
                $("<img />").attr("src", item.media.m).appendTo("#imageFeed");
                $("<br>").appendTo("#imageFeed");


           $("<div data-role='popup' id='popupSydney' data-corners='false'><a href='#' data-rel='back'" +
               " class='ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right'>Close</a>" +
               "</div>").appendTo("#imageFeed");
            }
        });
    });
}
