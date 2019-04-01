/**
 * Flag that indicates if a tracking is being done.
 * @type Boolean
 */
var doingTracking = false;

/**
 * Interval to check the user's location
 * @type number
 */
var myInterval;

/**
 * Set credentials.
 * @param obj Clicked object.
 */
function selectUser(obj) {
    stopTracking();
    var credentials = obj.textContent.split(" - ");
    $("#user").val(credentials[0]);
    $("#pass").val(credentials[1]);
    $("#content").html('');
}

/**
 * Send an ajax request with the specified parameters.
 * @param type Type of request (GET, POST, PUT)
 * @param url
 * @param success Success function.
 * @param data
 */
function sendAjaxRequest(type, url, success, data) {
    $.ajax({
        type: type,
        url: url,
        headers: {
            "Authorization": "Basic " + btoa($("#user").val() + ":" + $("#pass").val())
        },
        contentType: 'application/json',
        data: data,
        success: success,
        error: function (xhr) {
            console.error("Error HTTP code:", xhr.status);
            if (xhr.status === 401) {
                $("#content").html(
                        "<p class='alert alert-danger text-center'>Authentication has failed or has not been provided.</p>");
            }
        }
    });
}

/**
 * Send a request to the web service to validate the credentials .
 */
function validateCredentials() {
    stopTracking();
    sendAjaxRequest("GET", "/get-last-location", function (req) {
        var date = new Date(req.time);

        $("#content").html(
                `<div class='alert alert-success text-center'>
                <p><strong>Success</strong></p>
                <p>Last location:<br/>
                <a
                    class="alert-link" target='_blank'
                    href='https://www.openstreetmap.org/?mlat=${req.latitude}&mlon=${req.longitude}#map=19/${req.latitude}/${req.longitude}'>
                    ${req.latitude}, ${req.longitude}
                </a></p>
                <p><i>${formatDate(date)} ${formatTime(date)}</i></p>
                </div>`);
    });
}

/**
 * Get travels of the current user.
 */
function getHistorical() {
    stopTracking();
    sendAjaxRequest("GET", "/get-historical", function (req) {
        var content = `<h3 class='text-center'>Travels of ${req.first_name} ${req.last_name}</h3>`;

        for (var i = 0; i < req.travels.length; i++) {
            var travel = req.travels[i];

            if (travel.locations.length > 0) {

                var startDate = new Date(travel.locations[0].time);
                var endDate = new Date(travel.locations[travel.locations.length - 1].time);

                content += `<ul><li>
                        <strong><i>
                        ${formatDate(startDate)}
                        &#160;
                        ${formatTime(startDate)} - ${formatTime(endDate)}
                        </strong></i><ul>`;

            }

            for (var j = 0; j < travel.locations.length; j++) {
                var loc = travel.locations[j];
                var date = new Date(loc.time);
                content += `<li>
                            ${formatDate(date)} ${formatTime(date)} :
                            <a
                                target='_blank'
                                href='https://www.openstreetmap.org/?mlat=${loc.latitude}&mlon=${loc.longitude}#map=19/${loc.latitude}/${loc.longitude}'>
                                ${loc.latitude}, ${loc.longitude}
                            </a>
                            </li>`;
            }

            content += `</ul></li></ul>`;
        }

        $("#content").html(content);
    });
}

/**
 * Start a new tracking.
 */
function newTracking() {
    if (doingTracking) {
        stopTracking();

    } else {
        doingTracking = true;
        $('#tracking').removeClass('btn-success');
        $('#tracking').addClass('btn-danger');
        $('#tracking').text('Stop tracking');

        // Callback function to do the user tracking.
        sendAjaxRequest("GET", "/new-tracking", function (trackingId) {

            $("#content").html(
                    `<p class='text-center' id='content-loc'>
                    <strong>Locations</strong><br/>
                </p>`);

            console.log('Tracking ID', trackingId);
            getCurrentLocation(trackingId);

            // Create an unique interval to recover the user's location each N seconds.
            clearInterval(myInterval);
            myInterval = setInterval(getCurrentLocation, 10000, trackingId);
        });
    }
}

/**
 * Stop tracking.
 */
function stopTracking() {
    doingTracking = false;
    $('#tracking').addClass('btn-success');
    $('#tracking').removeClass('btn-danger');
    $('#tracking').text('Start tracking');

    clearInterval(myInterval);
}

/**
 * Format date (year, month and day).
 * @param {Date} date
 * @returns {String}
 */
function formatDate(date) {
    return `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()}`;
}

/**
 * Format time (hours and minutes).
 * @param {Date} date
 * @returns {String}
 */
function formatTime(date) {
    return `${date.getHours()}:${date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()}`;
}

/**
 * Get current location.
 * @param {int} trackingId
 */
function getCurrentLocation(trackingId) {
    // Recover the user's current location.
    if (navigator.geolocation) {

        navigator.geolocation.getCurrentPosition(function (loc) {
            console.log(loc.coords.latitude, loc.coords.longitude);

            // Send a new location.
            sendAjaxRequest("PUT", "/set-location", function (req) {

                // Render the user's location.
                var date = new Date();
                $("#content-loc").append(
                        `${formatDate(date)} ${formatTime(date)} :
                    <a  target='_blank'
                        href='https://www.openstreetmap.org/?mlat=${loc.coords.latitude}&mlon=${loc.coords.longitude}#map=19/${loc.coords.latitude}/${loc.coords.longitude}'>
                        ${loc.coords.latitude}, ${loc.coords.longitude}
                    </a><br/>`);

            }, `{"lat" : ${loc.coords.latitude}, "lng": ${loc.coords.longitude}, "id_travel": ${trackingId} }`);

        });
    } else {

        var msg = "Geolocation is not supported by this browser.";
        $("#content").html(msg);
        console.error(msg);
    }
}
