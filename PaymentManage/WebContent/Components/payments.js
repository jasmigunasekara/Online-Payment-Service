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
	var status = validateItemForm();
	if (status != true)
	{
		 $("#alertError").text(status);
 		 $("#alertError").show();
 		 return;
 	}

	// If valid-------------------------
 	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
 	{
 		url : "AdminPaymentsAPI",
 		type : type,
 		data : $("#formAdminPayment").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onItemSaveComplete(response.responseText, status);
 		}
 	}); 


	// If valid-------------------------
 	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
 	{
 		url : "CustomerPaymentsAPI",
 		type : type,
 		data : $("#formCustomerPayment").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onItemSaveComplete(response.responseText, status);
 		}
 	}); 
 });

function onItemSaveComplete(response, status)
	{
		if (status == "success")
		{
			 var resultSet = JSON.parse(response);
 			 if (resultSet.status.trim() == "success")
			 {
 				$("#alertSuccess").text("Successfully saved.");
 				$("#alertSuccess").show();
 				$("#divItemsGrid").html(resultSet.data);
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
		$("#hidItemIDSave").val("");
 		$("#formItem")[0].reset();
}

// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
		 $("#hidItemIDSave").val($(this).data("payid"));
		 $("#payCardType").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#payCardNo").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#payExpirtDate").val($(this).closest("tr").find('td:eq(2)').text());
 		 $("#payCVV").val($(this).closest("tr").find('td:eq(3)').text());
		 $("#payDate").val($(this).closest("tr").find('td:eq(4)').text());
		 $("#payTotalAmount").val($(this).closest("tr").find('td:eq(5)').text());
	     $("#payAmount").val($(this).closest("tr").find('td:eq(6)').text());
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
 				onItemDeleteComplete(response.responseText, status);
 			}
 		});
	
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
 				onItemDeleteComplete(response.responseText, status);
 			}
 		});
	});

	function onItemDeleteComplete(response, status)
	{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			if (resultSet.status.trim() == "success")
 			{
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				$("#divItemsGrid").html(resultSet.data);
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
	function validateItemForm()
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