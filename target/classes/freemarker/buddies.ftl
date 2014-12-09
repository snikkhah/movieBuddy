		
			There are ${count} similar users to user "${userId}" :
			<br><br>
			<table class="table table-striped">
				<tr>
					<th>UserID</th>
					<th>Gender</th>
					<th>Age</th>
					<th>Occupation</th>
					<th>Zip Code</th>
				</tr>
				<#list users as user>
					<tr>
						<td>${user.id}</td>
						<td>${user.gender}</td>
						<td>${user.age}</td>
						<td>${user.occupation}</td>
						<td>${user.zip}</td>
					</tr>
				</#list>
			</table>
		</div>