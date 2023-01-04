window.addEventListener("load", checkForCookie);
window.addEventListener("pageshow", checkNavigationType);

function checkForError() {
    if (document.cookie.indexOf('errorCookie') !== -1) {
        errorMessage(getCookie('errorCookie').valueOf());
        document.cookie = "errorCookie= ; expires = Thu, 01 Jan 1970 00:00:00 UTC";
    }

}

function formAction() {
    const x = document.getElementById("inputform");
    const y = document.getElementById("city");
    const z = document.getElementById("country");
    if (y.value === "" || z.value === "") {
        alert("You must input both CITY and COUNTRY, please try again!")
    } else {
        x.addEventListener('animationend', ev => {
            ev.animationName = 'formanimation';
            document.getElementById("OWServlet").submit();
        });
        document.getElementById("inputform").style.animation = "formanimation 2s ease-out forwards";
        document.getElementById("inputform").removeEventListener("animationend", ev => {
            ev.animationName = 'formanimation';
        })

    }


}

function formAction2() {
    const x = document.getElementById("inputform2");
    const y = document.getElementById("city");
    const z = document.getElementById("country");
    if (y.value === "" || z.value === "") {
        alert("You must input both CITY and COUNTRY, please try again!")
    } else {
        x.addEventListener('click', ev => {

            document.getElementById("OWServlet").submit();

        })

    }

}


function hideCookieModal() {
    document.getElementById('indexCookieModal').style.display = "none";
    document.getElementById('cookieModal').style.display = "none";
    document.getElementById("inputform").style.display = "flex";
    document.getElementById("inputform").style.animation = "animateforegroundreverse 3s ease-in forwards";

}

function setCookie(name, value) {
    document.cookie = `${encodeURIComponent(name)}=${encodeURIComponent(value)}; path=/`;
}

function errorMessage(value) {
    alert("Something went wrong while fetching the data, please try again!\nError type: " + "\'" + value + "\'")
}

function checkForCookie() {
    checkForError();
    if (document.cookie.indexOf('acceptCookie') !== -1) {
        hideCookieModal()
    } else {
        document.getElementById('cookieChoiceYes').addEventListener('click', ev => {
            setCookie('acceptCookie', '');
            modalFormAnimation()
        });
        document.getElementById('cookieChoiceNo').addEventListener('click', ev => {
            modalFormAnimation()
        });


    }

}

function modalFormAnimation() {
    document.getElementById("indexCookieModal").style.animation = "animatebackgroundreverse 4s ease-out forwards";
    document.getElementById("cookieModal").style.animation = "animatetopreverse 2s ease-out forwards";
    document.getElementById("inputform").style.animation = "animateforegroundreverse 2s ease-in forwards";
    document.getElementById("inputform").style.display = "flex";

}

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function checkNavigationType(event) {
    const historyTraversal = event.persisted ||
        (typeof window.performance != "undefined" &&
            window.performance.getEntriesByType("back_forward"));
    if (historyTraversal === true) {
        window.location.reload();
    }
}


