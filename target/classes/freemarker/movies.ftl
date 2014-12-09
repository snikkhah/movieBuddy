		Recommended movies to user "${userId}" :
			<br><br>
			<table class="table table-striped">
				<tr>
					<th>MovieID</th>
					<th>Title</th>
					<th>Genre</th>
					<th>Predicted Rating</th>
				</tr>
				<#list recMovies as movie>
					<tr>
						<td>${movie.id}</td>
						<td>${movie.title}</td>
						<td>${movie.genre}</td>
						<td>${movie.rate}</td>
					</tr>
				</#list>
			</table>
		</div>