$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
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
// If valid------------------------
var type = ($("#hidsupplierIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "itemsAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onsupplierSaveComplete(response.responseText, status); 
 } 
 }); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hidsupplierIDSave").val($(this).closest("tr").find('#hidsupplierIDUpdate').val()); 
 $("#Supplier_code").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#Name").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#Phone").val($(this).closest("tr").find('td:eq(2)').text()); 
  
}); 
// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// CODE
if ($("#Supplier_code").val().trim() == "") 
 { 
 return "Insert Supplier code."; 
 } 
// NAME
if ($("#Name").val().trim() == "") 
 { 
 return "Insert Name."; 
 } 
 
// Phone------------------------
if ($("#Phone").val().trim() == "") 
 { 
 return "Insert Phone."; 
 } 
return true; 
}

var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success") 
{ 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
$("#divItemsGrid").html(resultSet.data); 
} else if (resultSet.status.trim() == "error") 
{ 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
}
else if (status == "error")
{ 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
} else
{ 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
}

function onsupplierSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}

function onsupplierDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}