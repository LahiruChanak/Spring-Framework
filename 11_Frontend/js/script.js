// Constants
const BASE_URL =
  "http://localhost:8080/10_Backend_Web_exploded/api/v1/customer";

$(document).ready(function () {
  initializeProfilePicture();
  loadCustomers();
  updateNextCustomerId()

  // Initialize form submissions
  $("#customerForm").on("submit", function (e) {
    e.preventDefault();
    saveCustomer();
  });

  $("#editForm").on("submit", function (e) {
    e.preventDefault();
    updateCustomer();
  });
});

// Initialize profile picture handlers
function initializeProfilePicture() {
  // Stop propagation on file input click
  $('input[type="file"]').on("click", function (e) {
    e.stopPropagation();
  });

  // Add customer profile picture
  $(".profile-upload").on("click", function () {
    const fileInput = $(this).find('input[type="file"]');
    fileInput.trigger("click");
  });

  $("#profile-pic").on("change", function (e) {
    handleImageSelection(e, "profilePreview");
  });

  $("#edit-profile-pic").on("change", function (e) {
    handleImageSelection(e, "editProfilePreview");
    $(".remove-image-btn").show();
  });

  // Remove profile picture
  $(".remove-image-btn").on("click", function (e) {
    e.stopPropagation();
    const previewImg = $("#editProfilePreview");
    previewImg.addClass("removing");

    setTimeout(() => {
      previewImg.attr("src", "./img/placeholder.jpg").removeClass("removing");
      $("#edit-profile-pic").val("");
      $(this).hide();
    }, 300);
  });
}

// Handle image selection
function handleImageSelection(event, previewId) {
  const file = event.target.files[0];
  if (file) {
    if (file.type.match("image.*")) {
      const reader = new FileReader();
      reader.onload = function (e) {
        $(`#${previewId}`).attr("src", e.target.result);
      };
      reader.readAsDataURL(file);
    } else {
      showAlert("error", "Please select an image file");
      event.target.value = "";
    }
  }
}

// // Preview image helper function
// function previewImage(file, previewId) {
//   const reader = new FileReader();
//   reader.onload = function (e) {
//     $(`#${previewId}`).attr("src", e.target.result);
//     if (previewId === "editProfilePreview") {
//       $(".remove-image-btn").show();
//     }
//   };
//   reader.readAsDataURL(file);
// }

// Save customer
function saveCustomer() {
  const customerData = {
    id: $("#id").val(), // Use the ID displayed in the input field
    name: $("#name").val(),
    address: $("#address").val(),
    age: $("#age").val(),
    profilePic: $("#profilePreview").attr("src"),
  };

  if (!validateCustomerData(customerData)) {
    return;
  }

  $.ajax({
    url: `${BASE_URL}/save`,
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(customerData),
    success: function () {
      showAlert("success", "Customer saved successfully");

      // Reset the form and reload the customers
      resetForm();
      loadCustomers();
    },
    error: function (xhr) {
      showAlert("error", "Error saving customer: " + xhr.responseText);
    },
  });
}

// Load customers
function loadCustomers() {
  $.ajax({
    url: `${BASE_URL}/getAll`,
    method: "GET",
    success: function (customers) {
      const tableBody = $("#customerTable");
      tableBody.empty();

      customers.forEach(function (customer) {
        tableBody.append(`
                    <tr>
                        <td>
                            <img src="${customer.profilePic}" alt="${customer.name}" class="customer-photo">
                        </td>
                        <td>${customer.id}</td>
                        <td>${customer.name}</td>
                        <td>${customer.address}</td>
                        <td>${customer.age}</td>
                        <td>
                            <button class="btn btn-action btn-edit me-2" data-customer-id="${customer.id}">
                                <i class="hgi-stroke hgi-pencil-edit-02 fs-5"></i>
                            </button>
                            <button class="btn btn-action btn-delete" data-customer-id="${customer.id}">
                                <i class="hgi-stroke hgi-delete-02 fs-5"></i>
                            </button>
                        </td>
                    </tr>
                `);
      });

      // Attach event handlers to new buttons
      attachButtonHandlers();
      updateNextCustomerId();
    },
    error: function (xhr) {
      showAlert("error", "Error loading customers: " + xhr.responseText);
    },
  });
}

// Attach event handlers to dynamic buttons
function attachButtonHandlers() {
  $(".btn-edit").click(function () {
    const customerId = $(this).data("customer-id");
    editCustomer(customerId);
  });

  $(".btn-delete").click(function () {
    const customerId = $(this).data("customer-id");
    $("#deleteConfirmModal").data("customer-id", customerId);
    $("#deleteConfirmModal").modal("show");
  });
}

// Edit customer
function editCustomer(id) {
  $.ajax({
    url: `${BASE_URL}/getAll`,
    method: "GET",
    success: function (customers) {
      const customer = customers.find((c) => c.id === id);
      if (customer) {
        $("#editId").val(customer.id);
        $("#editName").val(customer.name);
        $("#editAddress").val(customer.address);
        $("#editAge").val(customer.age);
        $("#editProfilePreview").attr("src", customer.profilePic);

        new bootstrap.Modal("#editModal").show();
      }
    },
    error: function (xhr) {
      showAlert("error", "Error fetching customer data: " + xhr.responseText);
    },
  });
}

// Update customer
function updateCustomer() {
  const customerData = {
    id: $("#editId").val(),
    name: $("#editName").val(),
    address: $("#editAddress").val(),
    age: $("#editAge").val(),
    profilePic: $("#editProfilePreview").attr("src"),
  };

  if (!validateCustomerData(customerData)) {
    return;
  }

  $.ajax({
    url: `${BASE_URL}/update`,
    method: "PUT",
    contentType: "application/json",
    data: JSON.stringify(customerData),
    success: function () {
      showAlert("success", "Customer updated successfully");
      $("#editModal").modal("hide");
      loadCustomers();
    },
    error: function (xhr) {
      showAlert("error", "Error updating customer: " + xhr.responseText);
    },
  });
}


// Add handler for confirm delete button
$("#confirmDeleteBtn").click(function () {
  const customerId = $("#deleteConfirmModal").data("customer-id");
  deleteCustomer(customerId);
  $("#deleteConfirmModal").modal("hide");
});

// Delete customer
function deleteCustomer(id) {
  $.ajax({
    url: `${BASE_URL}/delete/${id}`,
    method: "DELETE",
    success: function () {
      showAlert("success", "Customer deleted successfully");
      loadCustomers();
    },
    error: function (xhr) {
      showAlert("error", "Error deleting customer: " + xhr.responseText);
    },
  });
}

// Helper functions
function validateCustomerData(data) {
  if (!data.name || !data.address || !data.age) {
    showAlert("error", "Please fill in all fields");
    return false;
  }
  if (data.name.length < 5 || !/^[A-Za-z\s]{5,}$/.test(data.name)) {
    showAlert(
      "error",
      "Name must be at least 5 characters and contain only letters and spaces"
    );
    return false;
  }
  if (data.address.length < 5 || !/^[A-Za-z0-9\s]{5,}$/.test(data.address)) {
    showAlert(
      "error",
      "Address must be at least 5 characters and contain only letters, numbers, and spaces"
    );
    return false;
  }
  if (data.age < 15 || data.age > 100) {
    showAlert("error", "Age must be between 15 and 100");
    return false;
  }
  return true;
}

// Update the next customer ID in the input field
function updateNextCustomerId() {
  const lastRow = $("#customerTable tr:last");
  if (lastRow.length === 0) {
    $("#id").val("C001"); // If no rows exist, start with C001
    return;
  }
  const lastId = lastRow.find("td:eq(1)").text(); // Get the last ID from the table
  const num = parseInt(lastId.substring(1)) + 1; // Extract numeric part and increment
  $("#id").val(`C${String(num).padStart(3, "0")}`); // Format as C001, C002, etc.
}

function resetForm() {
  $("#customerForm")[0].reset();
  $("#profilePreview").attr("src", "./img/placeholder.jpg");
}

function showAlert(type, message) {
  const alertClass = type === "success" ? "bg-success" : "bg-danger";
  const alertHtml = `
      <div class="alert ${alertClass} text-white alert-dismissible fade show" role="alert">
          ${message}
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
  `;

  $("#alertContainer").append(alertHtml);
  setTimeout(() => {
    $("#alertContainer .alert:first-child").alert("close");
  }, 3000);
}
