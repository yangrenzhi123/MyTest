<%@ page pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<title>DataTables example</title>

<link href="http://www.datatables.net/favicon.ico" rel="shortcut icon"
	type="image/ico">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="DT_bootstrap.css" rel="stylesheet" type="text/css">

<script language="javascript"
	src="/jquery-1.7.2.js"
	type="text/javascript" charset="utf-8"></script>
<script language="javascript"
	src="http://www.datatables.net/release-datatables/media/js/jquery.dataTables.js"
	type="text/javascript" charset="utf-8"></script>
<script language="javascript" src="DT_bootstrap.js"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div class="container" style="margin-top: 10px;">

		<div class="dataTables_wrapper form-inline no-footer"
			id="example_wrapper">
			<div class="row">
				<div class="span6">
					<div class="dataTables_length" id="example_length">
						<label><select name="example_length"
							aria-controls="example"><option value="10">10</option>
								<option value="25">25</option>
								<option value="50">50</option>
								<option value="100">100</option></select> records per page</label>
					</div>
				</div>
				<div class="span6">
					<div class="dataTables_filter" id="example_filter">
						<label>Search:<input aria-controls="example" type="search"
							placeholder=""></label>
					</div>
				</div>
			</div>
			<table class="table table-striped table-bordered dataTable no-footer"
				id="example" role="grid" aria-describedby="example_info" border="0"
				cellspacing="0" cellpadding="0">
				<thead>
					<tr role="row">
						<th tabindex="0" class="sorting_asc" aria-controls="example"
							style="width: 161px;"
							aria-label="Rendering engine: activate to sort column descending"
							aria-sort="ascending" rowspan="1" colspan="1">Rendering
							engine</th>
						<th tabindex="0" class="sorting" aria-controls="example"
							style="width: 237px;"
							aria-label="Browser: activate to sort column ascending"
							rowspan="1" colspan="1">Browser</th>
						<th tabindex="0" class="sorting" aria-controls="example"
							style="width: 218px;"
							aria-label="Platform(s): activate to sort column ascending"
							rowspan="1" colspan="1">Platform(s)</th>
						<th tabindex="0" class="sorting" aria-controls="example"
							style="width: 138px;"
							aria-label="Engine version: activate to sort column ascending"
							rowspan="1" colspan="1">Engine version</th>
						<th tabindex="0" class="sorting" aria-controls="example"
							style="width: 99px;"
							aria-label="CSS grade: activate to sort column ascending"
							rowspan="1" colspan="1">CSS grade</th>
					</tr>
				</thead>
				<tbody>
					<tr class="gradeA odd" role="row">
						<td class="sorting_1">Gecko</td>
						<td>Firefox 1.0</td>
						<td>Win 98+ / OSX.2+</td>
						<td class="center">1.7</td>
						<td class="center">A</td>
					</tr>
					<tr class="gradeA even" role="row">
						<td class="sorting_1">Gecko</td>
						<td>Firefox 1.5</td>
						<td>Win 98+ / OSX.2+</td>
						<td class="center">1.8</td>
						<td class="center">A</td>
					</tr>
					<tr class="gradeA odd" role="row">
						<td class="sorting_1">Gecko</td>
						<td>Firefox 2.0</td>
						<td>Win 98+ / OSX.2+</td>
						<td class="center">1.8</td>
						<td class="center">A</td>
					</tr>
					<tr class="gradeA even" role="row">
						<td class="sorting_1">Gecko</td>
						<td>Firefox 3.0</td>
						<td>Win 2k+ / OSX.3+</td>
						<td class="center">1.9</td>
						<td class="center">A</td>
					</tr>
					<tr class="gradeA odd" role="row">
						<td class="sorting_1">Gecko</td>
						<td>Camino 1.0</td>
						<td>OSX.2+</td>
						<td class="center">1.8</td>
						<td class="center">A</td>
					</tr>
					<tr class="gradeA even" role="row">
						<td class="sorting_1">Gecko</td>
						<td>Camino 1.5</td>
						<td>OSX.3+</td>
						<td class="center">1.8</td>
						<td class="center">A</td>
					</tr>
					<tr class="gradeA odd" role="row">
						<td class="sorting_1">Gecko</td>
						<td>Netscape 7.2</td>
						<td>Win 95+ / Mac OS 8.6-9.2</td>
						<td class="center">1.7</td>
						<td class="center">A</td>
					</tr>
					<tr class="gradeA even" role="row">
						<td class="sorting_1">Gecko</td>
						<td>Netscape Browser 8</td>
						<td>Win 98SE+</td>
						<td class="center">1.7</td>
						<td class="center">A</td>
					</tr>
					<tr class="gradeA odd" role="row">
						<td class="sorting_1">Gecko</td>
						<td>Netscape Navigator 9</td>
						<td>Win 98+ / OSX.2+</td>
						<td class="center">1.8</td>
						<td class="center">A</td>
					</tr>
					<tr class="gradeA even" role="row">
						<td class="sorting_1">Gecko</td>
						<td>Mozilla 1.0</td>
						<td>Win 95+ / OSX.1+</td>
						<td class="center">1</td>
						<td class="center">A</td>
					</tr>
				</tbody>
			</table>
			<div class="row">
				<div class="span6">
					<div class="dataTables_info" id="example_info" role="status"
						aria-live="polite">Showing 1 to 10 of 57 entries</div>
				</div>
				<div class="span6">
					<div class="dataTables_paginate paging_bootstrap pagination"
						id="example_paginate">
						<ul>
							<li class="prev disabled"><a href="#">← Previous</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li class="next"><a href="#">Next → </a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>