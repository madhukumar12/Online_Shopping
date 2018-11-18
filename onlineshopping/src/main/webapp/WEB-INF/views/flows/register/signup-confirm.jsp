<%@include file="../shared/flows-header.jsp"%>
<div class="row">
	<!-- Display Personal details in column-->
	<div class="col-sm-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Persoanl Details</h4>
			</div>
			<div class="panel-body">
				<div class="text-center">
					<h4>${registerModel.user.firstName}
						${registerModel.user.lastName}</h4>
					<h5>Email: ${registerModel.user.email}</h5>
					<h5>Contact Number: ${registerModel.user.contactNumber}</h5>
					<h5>Role: ${registerModel.user.role}</h5>

				</div>
			</div>
			<div class="panel-footer">
				<a href="${flowExecutionUrl}&_eventId_personal"
					class="btn btn-primary">Edit</a>
			</div>
		</div>
	</div>
	<div class="col-sm-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Billing Address</h4>
			</div>
			<div class="panel-body">

				<h4>${registerModel.billing.addressLineOne}</h4>
				<h4>${registerModel.billing.addressLineTwo}</h4>
				<h4>${registerModel.billing.city} -
					${registerModel.billing.postalCode}</h4>
				<h4>${registerModel.billing.state} -
					${registerModel.billing.country}</h4>

			</div>
			<div class="panel-footer">
				<a href="${flowExecutionUrl}&_eventId_billing"
					class="btn btn-primary">Edit</a>

			</div>
		</div>
	</div>
</div>

<!--  Confirm button after displaying the details -->
<div class="row">
	<div class="col-sm-4 col-sm-offset-4">
		<div class="text-center">
			<a href="${flowExecutionUrl}&_eventId_submit"
				class="btn btn-primary">Confirm</a>
		</div>
	</div>
</div>

<%@include file="../shared/flows-footer.jsp"%>