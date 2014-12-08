<!DOCTYPE html>
<html lang="en">
  <head>
  	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
	<!-- Latest compiled and minified JavaScript -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script> 
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Movie Buddies!</title>
    
  </head>

  <body>
	<style type="text/css">
  /*
 * Base structure
 */

/* Move down content because we have a fixed navbar that is 50px tall */
body {
  padding-top: 50px;
}


/*
 * Global add-ons
 */

.sub-header {
  padding-bottom: 10px;
  border-bottom: 1px solid #eee;
}

/*
 * Top navigation
 * Hide default border to remove 1px line.
 */
.navbar-fixed-top {
  border: 0;
}

/*
 * Sidebar
 */

/* Hide for mobile, show later */
.sidebar {
  display: block;
}
@media (min-width: 768px) {
  .sidebar {
    position: fixed;
    top: 51px;
    bottom: 0;
    left: 0;
    z-index: 1000;
    display: block;
    padding: 20px;
    overflow-x: hidden;
    overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
    background-color: #f5f5f5;
    border-right: 1px solid #eee;
  }
}

/* Sidebar navigation */
.nav-sidebar {
  margin-right: -21px; /* 20px padding + 1px border */
  margin-bottom: 20px;
  margin-left: -20px;
}
.nav-sidebar > li > button{
  padding-right: 20px;
  padding-left: 5px;
  white-space: pre-wrap;
	width: %100:
	display: block;
	float: left;
  padding: 1em;
}
.nav-sidebar > .active > a,
.nav-sidebar > .active > a:hover,
.nav-sidebar > .active > a:focus {
  color: #fff;
  background-color: #428bca;
}


/*
 * Main content
 */

.main {
  padding: 20px;
}
@media (min-width: 768px) {
  .main {
    padding-right: 40px;
    padding-left: 40px;
  }
}
.main .page-header {
  margin-top: 0;
}


/*
 * Placeholder dashboard ideas
 */

.placeholders {
  margin-bottom: 30px;
  text-align: center;
}
.placeholders h4 {
  margin-bottom: 0;
}
.placeholder {
  margin-bottom: 20px;
}
.btn-block {
  display: block;
  width: 100%;
}
.placeholder img {
  display: inline-block;
  border-radius: 50%;
}
</style>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/moviebuddy/v1/home">Movie Buddy!</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          
        </div>
      </div>
    </nav>
    
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a id="chart1" >Average rating of movies by genre</a></li>
            <li><a id="chart2" >Number of movie ratings by genre</a></li>
            <li><a id="chart3" >Number of movie ratings by occupation</a></li>
            <li><a id="chart4" >Users by occupation</a></li>
			<li><a id="chart5" >Average rating of movies by occupation</a></li>
			<li><a id="chart6" >Effect of age on rating of 5</a></li>
			<li><a id="chart7" >Favorite genre for males</a></li>
			<li><a id="chart8" >Favorite genre for females</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" id="container" style="width:80%; height:400px;"></div>

	<script>
	$(function() {
		    $('#chart1').click(function() {
			chart1();	
			toggle();
		    });
		});
		
		$(function() {
		    $('#chart2').click(function() {
			chart2();	
			toggle();
		    });

		});
		
		$(function() {
		    $('#chart3').click(function() {
			chart3();	
			toggle();  
		    });
		});
		
		$(function() {
		    $('#chart4').click(function() {
			chart4();	
			toggle();  
		    });
		});
		
		$(function() {
		    $('#chart5').click(function() {
			chart5();	
			toggle();  
		    });
		});
		
		$(function() {
		    $('#chart6').click(function() {
			chart6();	
			toggle();  
		    });
		});
		
		$(function() {
		    $('#chart7').click(function() {
			chart7();	
			toggle();  
		    });
		});
		
		$(function() {
		    $('#chart8').click(function() {
			chart8();	
			toggle();  
		    });
		})

	function chart1(){
				$(function () { 
					$('#container').highcharts({
				chart: {
					type: 'line'
				},
				title: {
					text: 'Average rating of movies by genre'
				},
				xAxis: {
					categories: ['War', 'Film-Noir', 'Sci-Fi', 'Drama', 'Crime', 'Documentary', 'Horror', 'Musical', 'Western', 'Adventure', 'Mystery', 'Comedy', 'Thriller', 'Children', 'Action', 'Animation', 'Romance', 'Fantasy']
				},
				yAxis: {
					title: {
						text: 'Average Rating'
					}
				},
				series: [{
					name: 'ratings',
					data: [3.9, 4.3, 3.1, 3.8, 3.6, 4.0, 3.1, 3.8, 3.8, 3.7, 3.8, 3.5, 3.5, 2.9, 3.3, 4.4, 3.4, 4.0]
				}]
				});
			});
			}
			
	function chart2(){
		$(function () { 
	    		$('#container').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: 'Number of ratings by genre'
	        },
	        xAxis: {
	            categories: ['War', 'Film-Noir', 'Sci-Fi', 'Drama', 'Crime', 'Documentary', 'Horror', 'Musical', 'Western', 'Adventure', 'Mystery', 'Comedy', 'Thriller', 'Children', 'Action', 'Animation', 'Romance', 'Fantasy']
	        },
	        yAxis: {
	            title: {
	                text: 'number of ratings'
	            }
	        },
	        plotOptions: {
	            column: {
	                grouping: true,
	                shadow: false
	            }
	        },
	        series: [{
	            name: 'Genre',
	            data: [991, 802, 1495, 11423, 1889, 6265, 22563, 3211, 5689, 1711, 1168, 16883, 17851, 290, 12311, 459, 2077, 1]
	        }]
	    });
	});
	}
	

	function chart3(){
		$(function () { 
	    		$('#container').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: 'Number of movie ratings by occupation'
	        },
	        xAxis: {
	            categories: ['technician/engineer', 'other', 'student', 'scientist', 'farmer', 'doctor/health care', 'executive/managerial', 'writer', 'lawyer', 'programmer', 'tradesman/craftsman', 'artist', 'retired', 'customer service', 'unemployed', 'self-employed', 'college/grad student', 'academic/educator', 'clerical/admin', 'sales/marketing', 'homemaker']
	        },
	        yAxis: {
	            title: {
	                text: 'Number of Rating'
	            }
	        },
	        series: [{
	            name: 'ratings',
	            data: [72816, 130499, 23290, 22951, 2706, 37205, 105425, 60397, 20563, 57214, 12086, 50068, 13754, 21850, 14904, 46021, 131032, 85351, 31623, 49109, 11345]
	        }]
	    });
	});
	}
	function chart4(){
		$(function () { 
	    		$('#container').highcharts({
	        chart: {
	            type: 'bar'
	        },
	        title: {
	            text: 'Users by occupation'
	        },
	        xAxis: {
	            categories: ['technician/engineer', 'other', 'student', 'scientist', 'farmer', 'doctor/health care', 'executive/managerial', 'writer', 'lawyer', 'programmer', 'tradesman/craftsman', 'artist', 'retired', 'customer service', 'unemployed', 'self-employed', 'college/grad student', 'academic/educator', 'clerical/admin', 'sales/marketing', 'homemaker']
	        },
	        yAxis: {
	            title: {
	                text: 'number of users'
	            }
	        },
	        plotOptions: {
	            column: {
	                grouping: true,
	                shadow: false
	            }
	        },
	        series: [{
	            name: 'F',
	            data: [7649, 32945, 6983, 4198, 636, 14979, 17967, 15691, 2643, 6804, 414, 16050, 2799, 3905, 3473, 7439, 32464, 32533, 14325, 11989, 10554]
	        },
	        {
	            name: 'M',
	            data: [65167, 97554, 16307, 18753, 2070, 22226, 87458, 44706, 17920, 50410, 11672, 34018, 10955, 17945, 11431, 38582, 98568, 52818, 17298, 37120, 791]
	        }]
	    });
	});
	}
	function chart5(){
		$(function () { 
	    		$('#container').highcharts({
	        chart: {
	            type: 'line'
	        },
	        title: {
	            text: 'Average rating of movies by genre'
	        },
	        xAxis: {
	            categories: ['War', 'Film-Noir', 'Sci-Fi', 'Drama', 'Crime', 'Documentary', 'Horror', 'Musical', 'Western', 'Adventure', 'Mystery', 'Comedy', 'Thriller', 'Children', 'Action', 'Animation', 'Romance', 'Fantasy']
	        },
	        yAxis: {
	            title: {
	                text: 'Average Rating'
	            }
	        },
	        series: [{
	            name: 'ratings',
	            data: [3.9, 4.3, 3.1, 3.8, 3.6, 4.0, 3.1, 3.8, 3.8, 3.7, 3.8, 3.5, 3.5, 2.9, 3.3, 4.4, 3.4, 4.0]
	        }]
	    });
	});

	}
	
	function chart6(){
			$(function () { 
	    		$('#container').highcharts({
	        chart: {
	            type: 'spline'
	        },
	        title: {
	            text: 'Effect of age on rating of 5'
	        },
	        xAxis: {
	            categories: ['1', '18', '25', '35', '45', '50', '56']
	        },
	        yAxis: {
	            title: {
	                text: 'number of ratings'
	            }
	        },
	        plotOptions: {
	            column: {
	                grouping: true,
	                shadow: false
	            }
	        },
	        series: [{
	            name: 'Age',
	            data: [6802, 40558, 85730, 44710, 19142, 18600, 10768]
	        }]
	    });
	});
	}
	function toggle(){
	$('.sidebar li').click(function(e) {
		    $('.sidebar li.active').removeClass('active');
		    var $this = $(this);
		    if (!$this.hasClass('active')) {
		        $this.addClass('active');
		    }
		    e.preventDefault();
		});
	}
	
	function chart7(){
		  $.ajax({
		        url: "/moviebuddy/v1/charts/worldcloud1",
		        type: 'GET', 
		        error:function(data){
		       
		  		},
		        success: function(data) {
		            $("#container").empty().append(data);
			  	}
		  	});
	}

	function chart8(){
	 $.ajax({
		        url: "/moviebuddy/v1/charts/worldcloud2",
		        type: 'GET', 
		        error:function(data){
		  		},
		        success: function(data) {
		            $("#container").empty().append(data);
			  	}
		  	});
	}
			</script>
  </body>
</html>
