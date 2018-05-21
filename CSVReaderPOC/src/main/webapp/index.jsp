<html ng-app="myApp" >

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script src="scriptFile.js"> </script>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.5/angular-route.min.js"></script>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<!-- <intercept-url pattern="/favicon.ico" access="ROLE_ANONYMOUS" /> -->
<body  ng-controller="myCtrl">
{{2+2}}
<div class="container">
  <h1>Question Bank : </h1>
  <div  class="row">
    <div class="col-sm-6" style="background-color:yellow;" ng-click = "go('/upload')">
      <p>Upload a file</p>
    </div>
    <div class="col-sm-6" style="background-color:pink;" ng-click="go('/browse')">
      <p>Browse questions</p>
    </div>
  </div>
</div>

<div style="width:800px; height:250px; margin:0 auto;background-color: lightblue; margin-top: 50px" ng-view>
</div>
</body>
</html>
