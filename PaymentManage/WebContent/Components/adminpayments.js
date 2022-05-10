$(document).ready(function()
{
	 $("#alertSuccess").hide();
 	 $("#alertError").hide();
});

	$(document).on("click", ".btnRemove", function(event)
	{
 		$.ajax(
 		{
 			url : "AdminPaymentsAPI",
 			type : "DELETE",
 			data : "payID=" + $(this).data("payid"),
 			dataType : "text",
 			complete : function(response, status)
 			{
 				onAdminPaymentDeleteComplete(response.responseText, status);
 			}
 		});
	
});
	function onAdminPaymentDeleteComplete(response, status)
	{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			if (resultSet.status.trim() == "success")
 			{
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				$("#divAdminPaymentsGrid").html(resultSet.data);
 			} 
 			else if (resultSet.status.trim() == "error")
 			{
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			}
 		} 
 		else if (status == "error")
 		{
 				$("#alertError").text("Error while deleting.");
 				$("#alertError").show();
 		} 
 		else
 		{
 				$("#alertError").text("Unknown error while deleting..");
 				$("#alertError").show();
 		}
}

	// CLIENT-MODEL================================================================
	function validateAdminPaymentForm()
	{
		// CARDTYPE
		if ($("#payCardType").val().trim() == "")
		{
 			return "Insert Card Type.";
 		}

		// CARDNO
		if ($("#payCardNO").val().trim() == "")
 		{
 			return "Insert Card NO.";
 		}
	
		// EXPIRYDATE
		if ($("#payExpiryDate").val().trim() == "")
 		{
 			return "Insert Expiry Date.";
 		}
	
		// CVV
		if ($("#payCVV").val().trim() == "")
 		{
 			return "Insert CVV.";
 		}
	
		// DATE
		if ($("#payDate").val().trim() == "")
 		{
 			return "Insert Date.";
 		}

		// TOTALAMOUNT-------------------------------
		if ($("#payTotalAmount").val().trim() == "")
 		{
 			return "Insert Total Amount.";
 		}
 		
		// is numerical value
		var tmptamount = $("#payTotalAmount").val().trim();
		if (!$.isNumeric(tmptamount))
		{
 			return "Insert a numerical value for Total Amount.";
 		}
 		
		// convert to decimal price
		$("#payTotalAmount").val(parseFloat(tmptamount).toFixed(2));

		// AMOUNT-------------------------------
		if ($("#payAmount").val().trim() == "")
 		{
 			return "Insert Amount.";
 		}
 		
		// is numerical value
		var tmpamount = $("#payAmount").val().trim();
		if (!$.isNumeric(tmpamount))
		{
 			return "Insert a numerical value for Amount.";
 		}
 		
		// convert to decimal price
		$("#payAmount").val(parseFloat(tmpamount).toFixed(2));

		return true;
	}