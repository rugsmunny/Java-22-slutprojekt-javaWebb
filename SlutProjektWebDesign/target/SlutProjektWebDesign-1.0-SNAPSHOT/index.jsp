<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./stylesheets/style.css">
    <title>JSP - Hello World</title>
    <script src="./scripts/scripts.js"></script>
    <script>
        if(entries === true){
            location.reload();
        }
    </script>
</head>
<body>
<div class="cookie-modal" id="indexCookieModal">
    <div class="cookie-modal-content" id="cookieModal">
        <img class="cookie-banner-text" src="./img/cookieText.png" alt="CookieText" width="750" height="750"
             style="z-index: 5; position: fixed; overflow: visible; margin-bottom: 7%; margin-left: 5%;">
        <img class="cookie-monster-img" src="./img/cookieMonster.png" alt="cookie monster">
        <div style="margin-top: 20%; width: 200%; z-index: 5; height: 40%; display: flex;
        flex-direction: row; justify-content: space-between; align-self: center; justify-self: center; overflow: visible;
        position: absolute; ">
                <img id="cookieChoiceNo" class="btn-img" src="./img/no.png">
           <div style="left: 15%; width: 75%; height: 100%; display: flex; flex-wrap: wrap; justify-content: center; position: absolute">
               <p class="cookie-question" style="align-self: end;">Accept and enjoy a better experience ></p>
               <p class="cookie-question" style="margin-right: 15%;">< Decline to see this message again.</p>
           </div>
                <img id="cookieChoiceYes" class="btn-img" src="./img/yes.png">
        </div>
    </div>
</div>

<div id="inputform" style="animation: animateforeground 2s ease-out; display: none; flex-wrap: nowrap; justify-content: center; height: 80vh; width: 100%;
align-items: center; align-content: center; ">

    <div style="position: absolute; display: flex; flex-direction: column; justify-content: center;
align-items: center; align-content: center; padding: 10px 10px; border: solid 2px black; border-radius: 10px;">
        <form action="OWServlet" id="OWServlet" method="get" style="display: flex; flex-direction: column; justify-content: space-around;">
            <input style="text-align: center; border-radius: 10px;" type="text" name="city" id="city" placeholder="City" required autocomplete="off"/><br/>
            <input style="text-align: center; border-radius: 10px;" type="text" name="country" id="country" placeholder="Country" required autocomplete="off"/><br/>
        </form>
        <button style="align-self: center"><span onmouseup="formAction()" class="btn-img">SUBMIT</span></button>
    </div>


</div>

</body>
</html>