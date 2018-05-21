
var app = angular.module("module",['ngRoute', 'angularUtils.directives.dirPagination']);

app.controller("mainController", function($rootScope , $scope, $location, $http) {
	
	
	$scope.successMessage = "";
	$scope.LoginSuccessMessage = "";
	$scope.LoginFailureMessage = "";
	$scope.LoginDiv = true;
	$scope.UserDiv = false;
	$scope.addDiv = false;
	$scope.requestDiv =false;
	$scope.suggestDiv =false;
	$scope.viewDiv =false;
	$scope.addVisibility =false;
	$rootScope.admin = false;

	
	

	$scope.go = function(path) {
		$location.path(path);
	};
	
	$scope.home = function() {
		 window.location = 'http://localhost:8080/Kilvish/';
	};
	
	$scope.clear = function()
	{
		$scope.bname ="";
		$scope.bauthor ="";
		$scope.bcount ="";
		$scope.bdescription ="";
	}

	
	 $scope.getAllBooks = function() {
		 $http.get(
			'http://localhost:8080/Kilvish/Api/BookService/ViewAllBooks/')
			.success(function(data) {
				$scope.libraryBooks = JSON.parse(data);
				
			});

    };	
    
    $scope.removeBook = function(bid) {
		 $http.get(
			'http://localhost:8080/Kilvish/Api/BookService/RemoveBook/'+ bid)
			.success(function(data) {
				
				$scope.getAllBooks();
				 
			});
		 
   };
   
   $scope.issueBook = function(bid) {
		 $http.get(
			'http://localhost:8080/Kilvish/Api/BookService/IssueBook/'+ bid)
			.success(function(data) {
				
				$scope.bookCount = data;
				$scope.getAllBooks();
				if($scope.bookCount==0)
					{
					alert("Ooopss..this book is out of stock.. !!");
					$scope.getAllBooks();
					}
				else
					{
					$scope.getAllBooks();
					console.log($scope.bookCount);
					alert("Book Issued...Please Collect it from Reception !!");
					}
				

			});	 
 };
 
 $scope.returnBook = function(bid) {
	 $http.get(
		'http://localhost:8080/Kilvish/Api/BookService/ReturnBook/'+ bid)
		.success(function(data) {
			
			$scope.getAllBooks();
			alert("Book Returned..!!!!");
			 
		});
	 
};
   
   $scope.Login = function(username,password) {
		 $http.get(
			'http://localhost:8080/Kilvish/Api/BookService/Login/'+username + '/' +password)
			.success(function(data) {
			    $scope.user = data;
				$scope.LoginSuccessMessage = "Successfully logged In";
				$scope.LoginFailureMessage = "";
				$scope.LoginDiv = false;
				$scope.UserDiv = true;
				$scope.requestDiv =true;
				$scope.suggestDiv =true;
				$scope.viewDiv =true;
				$scope.addVisibility = true;
				$rootScope.admin = true;
				
				
				
			}).
			error(function(data) {
				
				$scope.LoginSuccessMessage = "";
				$scope.LoginFailureMessage = "Login failed";

			});
		 
 };
 
 
 $scope.register = function(name,age,profession,username,password) {
	 $http.get(
		'http://localhost:8080/Kilvish/Api/BookService/Register/'+ name + '/' + age  + '/' + profession+ '/' + username + '/' + password)
		.success(function() {
     $scope.registerSuccess = "successfully registered.....!!!";
		});

};
    
    $scope.addBook = function(bname, bauthor, bcount, bdescription) {
		 $http.get(
			'http://localhost:8080/Kilvish/Api/BookService/AddBook/'+ bname + '/' + bauthor + '/' + bcount+ '/' + bdescription)
			.success(function() {
				$scope.successMessage = "successfully saved";
				$scope.clear();
			});

   };

});

app.config(['$routeProvider', function($routeProvider)
            {
	         $routeProvider.
	         
	         when('/AddBook',{
	        	 controller : 'mainController',
	             templateUrl : 'AddBook.html'		 
	         }).
	         
	         when('/SuggestedBooks',{
	        	 controller : 'mainController',
	             templateUrl : 'SuggestedBooks.html'		 
	         }).
	         
	         when('/ViewAllBooks',{
	        	 controller : 'mainController',
	             templateUrl : 'allBooks.html'		 
	         }).
	         
	         when('/RequestBook',{
	        	 controller : 'mainController',
	             templateUrl : 'RequestBook.html'		 
	         }).
	         
	         when('/Register',{
	        	 controller : 'mainController',
	             templateUrl : 'Register.html'		 
	         }).
	         
	         otherwise({
	        	 controller : 'mainController',
	             templateUrl : 'indexImage.html'
	         });
	         
            }]);

