<%@ page pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>SB Admin - Bootstrap Admin Template</title>
<!-- Bootstrap Core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="/css/sb-admin.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="wrapper" style="padding-left: 0px;">
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Bootstrap Elements</h1>
						<ol class="breadcrumb">
							<li><i class="fa fa-dashboard"></i> <a href="index.html">Dashboard</a>
							</li>
							<li class="active"><i class="fa fa-desktop"></i> Bootstrap
								Elements</li>
						</ol>
					</div>
				</div>
				<div class="jumbotron">
					<form action="/upload" enctype="multipart/form-data" method=post>
						<input type=hidden name=targetDirectory>
						<h1>Hello, world!</h1>
						<p>This is a template for a simple marketing or informational
							website. It includes a large callout called a jumbotron and three
							supporting pieces of content. Use it as a starting point to
							create something more unique.</p>
						<p>
							<a href="#" class="btn btn-primary btn-lg" role="button">Learn
								more &raquo;</a>
						</p>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>