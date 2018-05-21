
<html ng-app="module">
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.5/angular-route.min.js"></script>
	
<script src="Script.js"></script>

<script src="dirPagination.js"></script>

</head>


<intercept-url pattern="/favicon.ico" access="ROLE_ANONYMOUS" />
<body ng-controller="mainController">

	<div class="row">
		<div class="col-sm-2">
			<img src="images/home.jpg" ng-click = "home()"
				style="width: 100px; height: 100px; margin-left: 30px">
				
				<h3 style="margin-left: 50px">Home</h3>
			<h4 style="text-shadow: 2px 2px 8px orange;"><%=new java.util.Date()%></h4>
			
		</div>

		<div class="col-sm-7 ">
			<h1 style="color: red; text-align: center">Library Management
				System</h1>
		</div>

		<div class="col-sm-3 ">


			<div ng-show="LoginDiv">
				<form role="form">
					<div class="form-group">
						<b>Username: </b> <input type="text" class="form-control"
							ng-model="username">
					</div>
					<div class="form-group">
						<b>Password: </b> </label> <input type="password" class="form-control"
							ng-model="password">
					</div>
					<div class="checkbox">
						<label><input type="checkbox"> Remember me</label>
						<button type="submit" class="btn btn-default"
							ng-click="Login(username,password)">Submit</button>
						<a href="" class="bg-info" ng-click="go('/Register')"> Create
							Account </a>
					</div>
				</form>
			</div>

			<h3>{{LoginSuccessMessage}}</h3>
			<h3>{{LoginFailureMessage}}</h3>

			<div ng-show="UserDiv">
				<h2>
					Welcome
					<code>{{user}} </code>
				</h2>
			</div>


		</div>




	</div>
	<marquee>Latest books updated. check the explore library
		section..!!!</marquee>

	<div class="row">

		<div ng-show = "addVisibility" class="col-md-3 bg-success" ng-click="go('/AddBook')">
			<h2>Add a Book</h2>
		</div>

		<div class="{{suggestDiv ? 'col-md-3' : 'col-md-4'}} bg-info" ng-click="go('/SuggestedBooks')">
			<h2>Suggested Books</h2>
		</div>

		<div class="{{viewDiv ? 'col-md-3' : 'col-md-4'}} bg-success"
			ng-click="getAllBooks(); go('/ViewAllBooks')">
			<h2>View/Search All Books</h2>
		</div>

		<div class="{{requestDiv ? 'col-md-3' : 'col-md-4'}} bg-info" ng-click="go('/RequestBook')">
			<h2>Request a Book</h2>
		</div>

	</div>
	-


	<div ng-view style="min-height: 645px;"></div>



	<div class="panel panel-default bottomPanel bg-primary"
		style="text-align: center; color: red; background-color: grey">
		<kbd>Copyright 2008</kbd>
	</div>



</body>
</html>

</body>
</html>