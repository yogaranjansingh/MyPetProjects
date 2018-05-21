var app = angular.module('myApp', ['ngRoute']);

app.controller('myCtrl', function($scope,$location) {

   $scope.go = function(path) {
		$location.path(path);
	};
	
});

app.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.
                when('/browse', {
                    templateUrl: 'browse.html',
                    controller: 'myCtrl'
                }).
                when('/upload', {
                    templateUrl: 'upload.html',
                    controller: 'myCtrl'
                }).
                otherwise({
                    redirectTo: '/'
                });
        }]);
	

     
 
	
