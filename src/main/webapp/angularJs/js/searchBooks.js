var app = angular.module('myApp', []);
        
app.controller( 'searchController',  function($scope, $http, $window, $filter) {

	app.filter('urlencode', function() {
	  return function(input) {
	    return window.encodeURIComponent(input);
	  }
	});


	$scope.searchOption = "";
	$scope.searchTextExtra="";
	$scope.searchText="";
	$scope.placeholderMain="Exam, Subject, Career Stream, Institute, Author, Title";
	$scope.placeholderExtra = "Not Now";
	$scope.enableStatus="false";
	var user = window.localStorage.getItem("user");
	user = jQuery.parseJSON(user);
	console.log(user.careerStream);
	$scope.searchText = 	user.careerStream;
	var url = '../ws/rest/bookService/search/genericCriteria/'+user.careerStream+'/startFrom/1/maxResults/11/token/temp';
	console.log(url);
	   // $http.get('../ws/rest/bookService/search/criteria/Architecture/startFrom/1/maxResults/11/token/temp').
	   $http.get(url).
	    success(function(data) {
		    $scope.searchResponse = data;
		    var len = $scope.searchResponse.books.length;
		    console.log('Data got from server');
		    console.log(len);
		    
		    for(var count=0;count < len;count++){
			var cnt = count +1;
			$scope.searchResponse.books[count].tempId= cnt;
		    }
		    
		    var rows = Math.floor(len/3);
			if(rows == 0){
				rows = 1;
			}
			
		     var bookRows = new Array(rows);
		     
		     var i,j,temparray,chunk = 3, count=0;
		     for (i=0, j=len; i<j; i+=chunk) {
			bookRows[count] = $scope.searchResponse.books.slice(i, i+chunk);			
			count = count+1;
		     }	
		     
		     console.log(bookRows.length);
		     //console.log(bookRows[0][0].bookTitle);
		   $scope.bookRows = bookRows;
	});
	
		$scope.changeDisplay = function() {
		console.log('in changeDisplay function');
		    if($scope.searchOption == 'Generic Search'){
			$scope.placeholderExtra = "Not Needed Now";
			$scope.placeholderMain = "Exam, Subject, Career Stream, Institute, Author, Title";
			$scope.enableStatus="false";
			$scope.searchText="";
			$scope.searchTextExtra="";
			
  		    }
		    else if($scope.searchOption == 'Career Stream Or Subject Or Exam Or Institute'){
			$scope.placeholderExtra = "Not Needed Now";
			$scope.searchTextExtra="";
			$scope.enableStatus="false";
			$scope.searchText="";
			$scope.placeholderMain = "Career Stream Or Subject Or Exam Or Institute";
		    }
		     else if($scope.searchOption == 'Career Stream And Subject'){
			$scope.placeholderExtra = "Career Stream";
			$scope.placeholderMain = "Subject";
			$scope.searchText="";
			$scope.searchTextExtra="";
			$scope.enableStatus="true";
		    }	
		     else if($scope.searchOption == 'Career Stream And Exam'){
			$scope.placeholderExtra = "Career Stream";
			$scope.placeholderMain = "Exam";
			$scope.searchText="";
			$scope.searchTextExtra="";
			$scope.enableStatus="true";
		    }
		     else if($scope.searchOption == 'Subject And Exam'){
			$scope.placeholderExtra = "Subject";
			$scope.placeholderMain = "Exam";
			$scope.searchText="";
			$scope.searchTextExtra="";
			$scope.enableStatus="true";
		    }
		     else if($scope.searchOption == 'Institute And Exam'){
			$scope.placeholderExtra = "Institute";
			$scope.placeholderMain = "Exam";
			$scope.searchText="";
			$scope.searchTextExtra="";
			$scope.enableStatus="true";
		    }
		     else if($scope.searchOption == 'Institute And Subject'){
			$scope.placeholderExtra = "Institute";
			$scope.placeholderMain = "Subject";
			$scope.searchText="";
			$scope.searchTextExtra="";
			$scope.enableStatus="true";
		    }
		    else if($scope.searchOption == 'Book Title'){
			$scope.placeholderExtra = "Not needed now";
			$scope.placeholderMain = "Book Title";
			$scope.searchText="";
			$scope.searchTextExtra="";
			$scope.enableStatus="false";
		    }
		    else if($scope.searchOption == 'ISBN'){
			$scope.placeholderExtra = "Not needed now";
			$scope.placeholderMain = "ISBN";
			$scope.searchText="";
			$scope.searchTextExtra="";
			$scope.enableStatus="false";
		    }
		    else{
		    $scope.placeholderExtra = "Not needed now";
			$scope.placeholderMain = "Exam, Subject, Career Stream, Institute, Author, Title";
			$scope.searchText="";
			$scope.searchTextExtra="";
			$scope.enableStatus="false";
		    }
		};
		
	
		$scope.search = function() {
		console.log('in search function11 text selected '+$scope.searchText);
		    console.log('search option selected '+$scope.searchOption);
		    var url = '';
		    if($scope.searchOption == 'Generic Search'){
			console.log('1');
			var gC = "(?i).*"+$scope.searchText+".*";
			gC = window.encodeURIComponent(gC);
			url = '../ws/rest/bookService/search/genericCriteria/'+gC+'/startFrom/1/maxResults/11/token/temp';
		    }
		    else if($scope.searchOption == 'Career Stream Or Subject Or Exam Or Institute'){
			console.log('2');
			var crit = window.encodeURIComponent("(?i).*"+$scope.searchText+".*");
			
			url = '../ws/rest/bookService/search/criteria/'+crit+'/startFrom/1/maxResults/11/token/temp';
		    }
		     else if($scope.searchOption == 'Career Stream And Subject'){
			console.log('3');
			var cS = window.encodeURIComponent("(?i).*"+$scope.searchTextExtra+".*");
			var sub = window.encodeURIComponent($scope.searchText);
			url = '../ws/rest/bookService/search/careerStream/'+cS+'/subject/'+sub+'/startFrom/1/maxResults/11/token/temp';
		    }	
		     else if($scope.searchOption == 'Career Stream And Exam'){
			console.log('4');
			var cS = window.encodeURIComponent("(?i).*"+$scope.searchTextExtra+".*");
			var exam = window.encodeURIComponent("(?i).*"+$scope.searchText+".*");
			url = '../ws/rest/bookService/search/careerStream/'+cS+'/exam/'+exam+'/startFrom/1/maxResults/11/token/temp';
		    }
		     else if($scope.searchOption == 'Subject And Exam'){
			console.log('5');
			var sub = window.encodeURIComponent("(?i).*"+$scope.searchTextExtra+".*");
			var exam = window.encodeURIComponent("(?i).*"+$scope.searchText+".*");
			url = '../ws/rest/bookService/search/subject/'+sub+'/exam/'+exam+'/startFrom/1/maxResults/11/token/temp';
		    }
		     else if($scope.searchOption == 'Institute And Exam'){
			console.log('6');
			$window.location.href = 'wrongSelectionInfo.htm';	
			
		    }
		     else if($scope.searchOption == 'Institute And Subject'){
			console.log('7');
			var inst = window.encodeURIComponent("(?i).*"+$scope.searchTextExtra+".*");
			var sub = window.encodeURIComponent("(?i).*"+$scope.searchText+".*");
			url = '../ws/rest/bookService/search/institution/'+inst+'/subject/'+sub+'/startFrom/1/maxResults/11/token/temp';
		    }
		    else if($scope.searchOption == 'Book Title'){
			console.log('8');
			var title = window.encodeURIComponent("(?i).*"+$scope.searchText+".*");
			url = '../ws/rest/bookService/search/bookTitle/'+title+'/startFrom/1/maxResults/11/token/temp';
		    }
		    else if($scope.searchOption == 'ISBN'){
			console.log('9');
			var isbn = window.encodeURIComponent("(?i).*"+$scope.searchText+".*");
			url = '../ws/rest/bookService/search/isbn/'+isbn+'/startFrom/1/maxResults/11/token/temp';
		    }
		console.log(url);
			$http.get(url).
			    success(function(data) {
				    $scope.searchResponse = data;
				    var len = $scope.searchResponse.books.length;
				    console.log('Data got from server');
				    console.log(len);
				    
				    for(var count=0;count < len;count++){
					var cnt = count +1;
					$scope.searchResponse.books[count].tempId= cnt;
				    }
				    
				    var rows = Math.floor(len/3);
					if(rows == 0){
						rows = 1;
					}
					
				     var bookRows = new Array(rows);
				     
				     var i,j,temparray,chunk = 3, count=0;
				     for (i=0, j=len; i<j; i+=chunk) {
					bookRows[count] = $scope.searchResponse.books.slice(i, i+chunk);			
					count = count+1;
				     }	
				     
				     console.log(bookRows.length);
				     //console.log(bookRows[0][0].bookTitle);
				   $scope.bookRows = bookRows;
			});
		};
	
	jQuery.parseJSON = function( data ) {
		// Attempt to parse using the native JSON parser first
		if ( window.JSON && window.JSON.parse ) {
			// Support: Android 2.3
			// Workaround failure to string-cast null input
			return window.JSON.parse( data + "" );
		}
	 
		var requireNonComma,
			depth = null,
			str = jQuery.trim( data + "" );
	 
		// Guard against invalid (and possibly dangerous) input by ensuring that nothing remains
		// after removing valid tokens
		return str && !jQuery.trim( str.replace( rvalidtokens, function( token, comma, open, close ) {
	 
			// Force termination if we see a misplaced comma
			if ( requireNonComma && comma ) {
				depth = 0;
			}
	 
			// Perform no more replacements after returning to outermost depth
			if ( depth === 0 ) {
				return token;
			}
	 
			// Commas must not follow "[", "{", or ","
			requireNonComma = open || comma;
	 
			// Determine new depth
			// array/object open ("[" or "{"): depth += true - false (increment)
			// array/object close ("]" or "}"): depth += false - true (decrement)
			// other cases ("," or primitive): depth += true - true (numeric cast)
			depth += !close - !open;
	 
			// Remove this token
			return "";
		}) ) ?
			( Function( "return " + str ) )() :
			jQuery.error( "Invalid JSON: " + data );
	};
	    
 });
 
 
 app.controller( 'initController',  function($scope, $http, $window, $filter) {
	
	    $http.get('../ws/rest/resourceService/user/token/tok').
	    success(function(data) {
		    $scope.user = data;
		   
		});
		
		
		$scope.navigateToDashboard = function() {
		     
		   
			//$window.localStorage.setItem('careerStream', $scope.user.careerStream );
			$window.localStorage.setItem("user", JSON.stringify($scope.user) );
			//window.localStorage['user'] = angular.toJson($scope.user);
		
			$window.location.href = 'home.html';	
		
	
		};
	    
 });
           
        