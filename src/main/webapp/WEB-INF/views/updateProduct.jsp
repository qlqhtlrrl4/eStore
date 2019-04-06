<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-warpper">
	<div class="container">
		<h1>Update Product</h1>
		<p class="lead">Fill the below information to add a product:</p>

		<!-- modelAttribute에 value와 AdminController.java->addProduct에 return value와 일치해야함 -->
		
		<sf:form 
			action="${pageContext.request.contextPath}/admin/productInventory/updateProduct"
			method="post" modelAttribute="product">
			<sf:hidden path="id"/> <!-- 이부분을 넣어야지 실제로 updateProductPost함수에 id 값이 제대로 들어간다 -->
			
			<div class="form-group">
				<label for="name">Name</label>
				<sf:input path="name" id="name" class="form-control" />
				<sf:errors path="name" cssStyle="color:#ff0000;" />
				<!-- for에 있는 value랑 sf:input에 id에있는 value와 일치해야됨 -->
			</div>

			<div class="form-group">
				<label for="category">Category</label>
				<sf:radiobutton path="category" id="category" value="컴퓨터" />
				컴퓨터
				<sf:radiobutton path="category" id="category" value="가전" />
				가전
				<sf:radiobutton path="category" id="category" value="잡화" />
				잡화
			</div>

			<div class="form-group">
				<label for="description">Description</label>
				<sf:input path="description" id="description" class="form-control" />
			</div>
			<div class="form-group">
				<label for="price">Price</label>
				<sf:input path="price" id="price" class="form-control" />
				<sf:errors path="price" cssStyle="color:#ff0000;" />
			</div>

			<div class="form-group">
				<label for="unitInStock">Unit In Stock</label>
				<sf:textarea path="unitInStock" id="unitInStock"
					class="form-control" />
				<sf:errors path="unitInStock" cssStyle="color:#ff0000;" />
			</div>

			<div class="form-group">
				<label for="manufacturer">Manufacturer</label>
				<sf:textarea path="manufacturer" id="manufacturer"
					class="form-control" />
				<sf:errors path="manufacturer" cssStyle="color:#ff0000;" />
			</div>

			<input type="submit" value="submit" class="btn btn-default">
			<a href="<c:url value="/admin/productInventory"/>"
				class="btn btn-default">Cancel</a>

		</sf:form>
		<br />
	</div>
</div>
