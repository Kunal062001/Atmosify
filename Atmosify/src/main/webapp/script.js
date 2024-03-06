function updateDateTime(){
            var now = new Date();       
            
            var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
            var dateString = now.toLocaleDateString('en-US', options);                      
            var hours = now.getHours();
            var minutes = now.getMinutes();
            var seconds = now.getSeconds();            
            
            hours = (hours < 10) ? "0" + hours : hours;
            minutes = (minutes < 10) ? "0" + minutes : minutes;
            seconds = (seconds < 10) ? "0" + seconds : seconds;
            
            var timeString = hours + ":" + minutes + ":" + seconds;
            document.getElementById("datetime").innerHTML = dateString + " " + timeString;
        }
setInterval(updateDateTime, 1000);
var weatherIcon = document.getElementById("weather-icon");
var val = document.getElementById("wc").value;
	  switch (val) {
      case 'Clouds':
          weatherIcon.src = "images/clouds.gif";
          break;
      case 'clouds':
          weatherIcon.src = "images/clouds.gif";
          break;
      case 'Clear':
          weatherIcon.src ="images/sun.gif";
          break;
      case 'Rain':
          weatherIcon.src = "images/rain.gif";
          break;
      case 'Mist':
          weatherIcon.src = "images/foggy.gif";
          break;
      case 'Snow':
          weatherIcon.src = "images/snow.gif";
          break;
      case 'Haze':
          weatherIcon.src = "images/haze.gif";
          break;
  }
	  