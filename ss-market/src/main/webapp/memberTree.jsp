<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="header.jsp"%>
<meta charset="utf-8" />
<title>MemberTree</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script type="text/javascript">
	$(function() {
		var jsondata = ${JSON_TREE};
		createJSTree(jsondata);
	});

	function createJSTree(jsondata) {
		$('#memberTree').jstree({
			"core" : {
				"check_callback" : true,
				'data' : jsondata
			},
			"plugins" : [ "contextmenu", "search", "types" ],
			"search" : {
				"case_insensitive" : true,
				"show_only_matches" : true
			},
			"types" : {
			      "default" : {
			        "icon" : "glyphicon glyphicon-flash"
			      }
			}
			
		});
		$('#memberTree').off("contextmenu.jstree", ".jstree-anchor");
	}
	
	$(document).ready(function () {
        $("#search").keyup(function () {
            var searchString = $(this).val();
            $('#memberTree').jstree('search', searchString);
        });
    });
</script>
</head>
<body>

	<div class="col-md-10 col-md-offset-2 row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="product-payment-inner-st">
				<ul id="myTabedu1" class="tab-review-design">
					<li class="active"><a href="">Member Tree</a></li>
				</ul>

				<div id="myTabContent" class="tab-content custom-product-edit">
					<div class="product-tab-list tab-pane fade active in"
						id="description">
						<div class="row">
							
							<table class="full-right">
								<tr>
									<td><a href="/menu"
										class="rmk btn btn-primary m-btn m-btn--custom m-btn--icon col-md-offset-1 col-md-12">
											<span><i class="fa fa-arrow-left"></i> <span>Back
													to Main</span> </span>
									</a></td>
								</tr>
							</table>
							<div class="row"></div>
							<br>
							<div class="row">
							<div class="input-group col-md-3 col-md-offset-5">
								<input type="text" class="form-control" style="border: 5px solid lightblue;"
									placeholder="Search node .." id="search"> <span
									class="input-group-btn">
								</span>
							</div>
							</div>
							<div class="row">
								<div class=" well col-md-offset-2 col-md-6" style="background-image: linear-gradient(#337ab7,#ffe6c5);">
									<div id="memberTree"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>