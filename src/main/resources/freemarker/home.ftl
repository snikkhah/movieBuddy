<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container theme-showcase" role="main">
        <!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>Welcom to Movie Buddies!</h1>
			<p>This website is a tool for finding movie buddies based on users' movie ratings. This tool looks into 1 million ratings from 6000 users for 
			about 4000 movies and uses the Pearson correlation similarity to find users with any desired similarity percentage.
			</p>
			<p><button class="btn btn-default">Statistical charts</button>
		</div>		
		<div class="panel panel-warning">
		<!-- Default panel contents -->
			<div class="panel-heading">Input Form</div>
			<div class="panel-body">
				<form method="post">
						<div class="input-group input-group">
							<span class="input-group-addon">UserID</span>
							<input type="text" name="userId" class="form-control" placeholder="Please enter a UserID between 1 and 6040">
						</div>
						<div class="input-group input-group">
							<span class="input-group-addon">Similarity Coefficient</span>
							<input type="text" name="similarity" class="form-control" placeholder="Please enter a similarity coefficient from 0 to 99">
						</div>
						<div class="input-group input-group">
							<span class="input-group-addon">No. of Movies</span>
							<input type="text" name="movieNo" class="form-control" placeholder="Please enter the number of movies you desire">
						</div>
						</form>
						<div class="btn-group btn-group-justified" role="group" aria-label="...">
						  <div class="btn-group" role="group">
						    <button type="submit" id="findBuddyAll" class="btn btn-default">Find movie buddy across US</button>
						  </div>
						  <div class="btn-group" role="group">
						    <button type="submit" id="findBuddyLocal" class="btn btn-info">Find movie buddy locally</button>
						  </div>
						  <div class="btn-group" role="group">
						    <button type="submit" id="findMovies" class="btn btn-default" >Recommend movies</button>
						  </div>
						</div>
						<h5 id ="errormsg style="color:red" class="error"></h5>
			</div>
		</div>
		
		
		<div class="jumbotron" id="buddies">
			
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
            <script src="//code.jquery.com/jquery.js"></script>
            
		<script>
			$.fn.serializeObject = function()
				{
				    var o = {};
				    var a = this.serializeArray();
				    $.each(a, function() {
				        if (o[this.name] !== undefined) {
				            if (!o[this.name].push) {
				                o[this.name] = [o[this.name]];
				            }
				            o[this.name].push(this.value || '');
				        } else {
				            o[this.name] = this.value || '';
				        }
				    });
				    return o;
				};
				
				
				$(function() {
				    $('#findBuddyAll').click(function() {
//				        alert(JSON.stringify($('form').serializeObject()));
				    $.ajax({
			        url: "/moviebuddy/v1/home/allbuddies",
			        type: 'POST',    
			        contentType: 'application/json',
			        data:JSON.stringify($('form').serializeObject()), 
			        error:function(data){
			        $("#errormsg").empty().append("Input missing or malformed. Please recheck your values and try again.");
			  		},
			        success: function(data) {
			           $("#buddies").empty().append(data);
			           $("#errormsg").empty();
			  	}
			  	});

				    });
				});
				
				$(function() {
				    $('#findBuddyLocal').click(function() {
//				        alert(JSON.stringify($('form').serializeObject()));
				    $.ajax({
			        url: "/moviebuddy/v1/home/localbuddies",
			        type: 'POST',    
			        contentType: 'application/json',
			        data:JSON.stringify($('form').serializeObject()), 
			        error:function(data){
			        $("#errormsg").empty().append("Input missing or malformed. Please recheck your values and try again.");
			  		},
			        success: function(data) {
			           $("#buddies").empty().append(data);
			           $("#errormsg").empty();
			  	}
			  	});

				    });
				});
				
				$(function() {
				    $('#findMovies').click(function() {
//				        alert(JSON.stringify($('form').serializeObject()));
				    $.ajax({
			        url: "/moviebuddy/v1/home/movies",
			        type: 'POST',    
			        contentType: 'application/json',
			        data:JSON.stringify($('form').serializeObject()), 
			        error:function(data){
			        $("#errormsg").empty().append("Input missing or malformed. Please recheck your values and try again.");
			  		},
			        success: function(data) {
			            $("#buddies").empty().append(data);
			            $("#errormsg").empty();
			  	}
			  	});

				    });
				});

			</script>
	</body>
</html>