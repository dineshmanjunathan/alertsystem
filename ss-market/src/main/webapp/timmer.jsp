<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<!-- <center class ="btn btn-primary"><b></b><p id="timmer"></p></b></center> -->
<label 
    style=' font-weight: bold; color: green;text-align: center;font-size: 182%;font-style: italic;'> Active days left!</label>
<center><p id="timmer"></p></center>
<script>
// Set the date we're counting down to
var activeDaysLeft = new Date();
activeDaysLeft.setDate(activeDaysLeft.getDate() + ${sessionScope.ACTIVE_DAYS});
 var countDownDate = activeDaysLeft.getTime();


// Update the count down every 1 second
var x = setInterval(function() {

  // Get today's date and time
  var now = new Date().getTime();
    
  // Find the distance between now and the count down date
  var distance = countDownDate - now;
    
  // Time calculations for days, hours, minutes and seconds
  var days = Math.floor(distance / (1000 * 60 * 60 * 24))+1;
   //var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
   //var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
   //var seconds = Math.floor((distance % (1000 * 60)) / 1000);
    
  // Output the result in an element with id="demo"
  if (days ==1) {
  
  document.getElementById("timmer").innerHTML = "<div style='font-size: 60px;color: #268f63; font-weight: bold;'>"+days +"</div><div style='font-size: 30px;color: #268f63; font-weight: bold;'> Day</div>" ;
  
  }else{
  
   document.getElementById("timmer").innerHTML = "<div style='font-size: 60px;color: #268f63; font-weight: bold;'>"+days +"</div><div style='font-size: 30px;color: #268f63; font-weight: bold;'> Days</div>" ;
   
  }
  /* document.getElementById("timmer").innerHTML = "<div style='font-size: 70px; font-weight: bold;'>"+days + "d</div> <div style='font-size: 28px;  font-weight: bold;'>" + hours + "h "
  + minutes + "m " + seconds + "s </div>"; */
    
  // If the count down is over, write some text 
  if (distance < 0) {
    clearInterval(x);
    document.getElementById("timmer").innerHTML = "<div style='font-size:60px;color: red; font-weight: bold;'>0</div>";
  }
}, 1000);
</script>

</body>
</html>

