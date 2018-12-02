$(function() {
	// solving the active menu problem
	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'User Cart':
		$('#userCart').addClass('active');
		break;
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;

	}

	// To tackle the csrf token

	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');

	if (token.length > 0 && header.length > 0) {
		// set the token header for the ajax request

		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});

	}

	// code for jquery table
	var $table = $('#productListTable');

	// execute the below code only where we have this table
	if ($table.length) {
		// console.log('Inside the table.');

		jsonURL = '';

		if (window.categoryID == '') {

			jsonURL = window.contextRoot + '/json/data/all/products';
		} else {

			jsonURL = window.contextRoot + '/json/data/category/'
					+ window.categoryID + '/products';

		}
		$table
				.DataTable({
					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
					pageLength : 5,

					ajax : {
						url : jsonURL,
						dataSrc : ''
					},
					columns : [

							{
								bSortable : false,
								data : 'code',
								mRender : function(data, type, row) {

									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';
								}
							},

							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitprice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}

									return data;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = ' ';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									if (userRole == 'ADMIN') {
										str += '<a href="'
												+ window.contextRoot
												+ '/manage/'
												+ data
												+ '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';

									} else {
										if (row.quantity < 1) {
											str += '<a href="javascript.void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';

										} else {

											str += '<a href="'
													+ window.contextRoot
													+ '/cart/add/'
													+ data
													+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';

										}
									}

									return str;
								}
							} ]
				});
	}

	// dismissing the alerts after 3 seconds
	var $alert = $('.alert');

	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000);
	}

	// Data Table for Admin

	// code for jquery table
	var $adminProductstable = $('#adminProductsTable');

	// execute the below code only where we have this table
	if ($adminProductstable.length) {
		// console.log('Inside the table.');

		jsonURL = window.contextRoot + '/json/data/admin/all/products';

		$adminProductstable
				.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 Records', '30 Records', '50 Records', 'ALL' ] ],
					pageLength : 30,

					ajax : {
						url : jsonURL,
						dataSrc : ''
					},
					columns : [

							{
								data : 'id'
							},

							{
								bSortable : false,
								data : 'code',
								mRender : function(data, type, row) {

									return '<img src="'
											+ window.contextRoot
											+ '/resources/images/'
											+ data
											+ '.jpg" class="adminDataTableImg"/>';
								}
							},

							{
								data : 'name'
							},
							{
								data : 'brand'
							},

							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}

									return data;
								}
							},
							{
								data : 'unitprice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'active',
								bSortTable : false,
								mRender : function(data, type, row) {

									var str = '';

									str += ' <label class="switch">'
									if (data) {
										str += '<input type="checkbox" checked="checked" value="'
												+ row.id + '"/>'
									} else {
										str += '<input type="checkbox"  value="'
												+ row.id + '"/>'
									}

									str += '<div class="slider"></div></label>'
									return str;
								}

							},
							{

								data : 'id',
								bSortalble : false,
								mRender : function(data, type, row) {
									var str = ''

									str += '<a href="'
											+ window.contextRoot
											+ '/manage/'
											+ data
											+ '/product" class="btn btn-warning">';
									str += '<span class="glyphicon glyphicon-pencil"></span></a>';

									return str;

								}
							} ],

					initComplete : function() {
						var api = this.api();
						// Popup for activate and deactivate product
						api
								.$('.switch input[type="checkbox"]')
								.on(
										'change',
										function() {
											var checkbox = $(this);
											var checked = checkbox
													.prop('checked');
											var dMsg = (checked) ? 'You want to activate the product?'
													: 'You want to deactivate the product';
											var value = checkbox.prop('value');

											bootbox
													.confirm({
														size : 'medium',
														title : 'Product Activation & Deactivation',
														message : dMsg,
														callback : function(
																confirmed) {
															if (confirmed) {
																console
																		.log(value);

																var activationUrl = window.contextRoot
																		+ '/manage/product/'
																		+ value
																		+ '/activation';

																$
																		.post(
																				activationUrl,
																				function(
																						data) {
																					bootbox
																							.alert({
																								size : 'medium',
																								title : 'information',
																								message : data
																							});
																				});

															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked)
															}
														}
													});
										});

					}
				});
	}

	// Validation for Category Form

	$categoryForm = $('#categoryForm');

	if ($categoryForm.length) {
		$categoryForm
				.validate({

					rules : {
						name : {
							required : true,
							minlength : 3

						},
						description : {
							required : true,

						}
					},
					messages : {
						name : {
							required : 'Please add the Category name!',
							minlenth : 'The category name should not be less than 2 characters'
						},
						description : {
							required : 'Please add the description for this category'
						}
					},
					errorElement : 'em',
					errorPlacement : function(error, element) {
						// add the class for help-block
						error.addClass('help-block');
						// add the error element after the input element
						error.insertAfter(element);
					}

				});
	}

	// Validation for Login Form

	$loginForm = $('#loginForm');

	if ($loginForm.length) {
		$loginForm.validate({

			rules : {
				username : {
					required : true,
					email : true

				},
				password : {
					required : true,

				}
			},
			messages : {
				username : {
					required : 'Please enter the User name!',
					email : 'Please enter valid email address'
				},
				password : {
					required : 'Please enter the password!'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error, element) {
				// add the class for help-block
				error.addClass('help-block');
				// add the error element after the input element
				error.insertAfter(element);
			}

		});
	}

});
