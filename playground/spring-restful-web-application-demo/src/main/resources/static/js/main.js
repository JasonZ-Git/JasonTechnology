/**
 * Main file to process logic of main page.
 */

var app = angular.module("shopApp", []);
app.controller("shopController", function($scope, $window, $http) {
	// Get all items from server using http service.
	$http.get("getShoppingItems").then(function(response) {
		$scope.allItems = response.data;
	});

	$scope.aBoughtNumber = 0;
	$scope.bBoughtNumber = 0;

	// Change value of current page and server.
	$scope.buyItem = function() {
		var aBought = 0;
		if ($scope.currentABought) {
			aBought = parseInt($scope.currentABought);
		}
		var aInStock = parseInt($scope.allItems[0].number);
		if (aBought > aInStock) {
			$window.alert("There are not enought A items to buy");
			return;
		}

		var bBought = 0;
		if ($scope.currentBBought) {
			bBought = parseInt($scope.currentBBought);
		}
		var bInStock = parseInt($scope.allItems[1].number);
		if (bBought > bInStock) {
			$window.alert("There are not enought B items to buy");
			return;
		}

		document.getElementById("itemsBought").style.display = "block";

		// Decrease items from server.
		$http.post('/decreaseShoppingItem?name=A&number=' + aBought, null)
				.success(function(data, status) {
					$scope.allItems[0].number -= aBought;
					$scope.aBoughtNumber += aBought;
				}).error(function(data, status) {
					$window.alert("Server can't decrease item A");
				});

		$http.post('/decreaseShoppingItem?name=B&number=' + bBought, null)
				.success(function(data, status) {
					$scope.allItems[1].number -= bBought;
					$scope.bBoughtNumber += bBought;
				}).error(function(data, status) {
					$window.alert("Server can't decrease item B");
				});
	};

	// Make sure only positive digit could be input value.
	$scope.validateInput = function($event) {
		var currentVar = event.currentTarget.value;
		var isDigit = /^\d+$/.test(currentVar)
		if (isDigit && parseInt(currentVar) >= 0) {
			return;
		}
		event.currentTarget.value = "";
		$event.preventDefault();
	}
});
