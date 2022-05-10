$(document).ready(function()
{
	 $("#alertSuccess").hide();
 	 $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();

	// Form validation-------------------
	var status = validateCustomerPaymentForm();
	if (status != true)
	{
		 $("#alertError").text(status);
 		 $("#alertError").show();
 		 return;
 	}

	// If valid-------------------------
 	var type = ($("#hidPayIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
 	{
 		url : "CustomerPaymentsAPI",
 		type : type,
 		data : $("#formCustomerPayment").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onCustomerPaymentSaveComplete(response.responseText, status);
 		}
 	}); 
 });

function onCustomerPaymentSaveComplete(response, status)
	{
		if (status == "success")
		{
			 var resultSet = JSON.parse(response);
 			 if (resultSet.status.trim() == "success")
			 {
 				$("#alertSuccess").text("Successfully saved.");
 				$("#alertSuccess").show();
 				$("#divCustomerPaymentsGrid").html(resultSet.data);
 			 } 
 			 else if (resultSet.status.trim() == "error")
			 {
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			 }
 		} 
 		else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} 
 		else
 		{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
 		}
		$("#hidPayIDSave").val("");
 		$("#formCustomerPayment")[0].reset();
}

// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
		 $("#hidPayIDSave").val($(this).data("payid"));
		 $("#payCardType").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#payCardNO").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#payExpiryDate").val($(this).closest("tr").find('td:eq(2)').text());
 		 $("#payCVV").val($(this).closest("tr").find('td:eq(3)').text());
		 $("#payDate").val($(this).closest("tr").find('td:eq(4)').text());
		 $("#payTotalAmount").val($(this).closest("tr").find('td:eq(5)').text());
	     $("#payAmount").val($(this).closest("tr").find('td:eq(6)').text());
	});

	$(document).on("click", ".btnRemove", function(event)
	{
 		$.ajax(
 		{
 			url : "CustomerPaymentsAPI",
 			type : "DELETE",
 			data : "payID=" + $(this).data("payid"),
 			dataType : "text",
 			complete : function(response, status)
 			{
 				onCustomerPaymentDeleteComplete(response.responseText, status);
 			}
 		});
	});

	function onCustomerPaymentDeleteComplete(response, status)
	{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			if (resultSet.status.trim() == "success")
 			{
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				$("#divCustomerPaymentsGrid").html(resultSet.data);
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
	function validateCustomerPaymentForm()
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