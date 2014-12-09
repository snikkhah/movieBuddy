<div id="container" style="width:100%; height:400px;"></div>
	<script> 
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

	</script>