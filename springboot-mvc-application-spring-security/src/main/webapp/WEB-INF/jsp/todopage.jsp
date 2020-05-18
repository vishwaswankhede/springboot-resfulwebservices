<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="contanier">
	<table class="table table-striped">
		<caption>
			<div>Hi ${name}</div>
			Your todos are
		</caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is it Done?</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todolist}" var="todo">
				<tr>
					<td>${todo.desc}</td>
					<td><fmt:formatDate value="${todo.targetDate}"
							pattern="dd/MM/yyyy" /></td>
					<td>${todo.done}</td>
					<td><a type="button" class="btn btn-success"
						href="/update-todo?uid=${todo.id}">Update</a></td>
					<td><a type="button" class="btn btn-warning"
						href="/delete-todo?did=${todo.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<%-- your name ${name}<br><br>
		to do list ${todolist}
		<br><br> --%>

	<div>
		<a class="button" href="addtodolist">Add todo list</a>
	</div>


	<%@ include file="common/footer.jspf"%>