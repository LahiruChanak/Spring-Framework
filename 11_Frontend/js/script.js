const BASE_URL = "http://localhost:8080/10_Backend_Web_exploded/api/v1/customer";

$(document).ready(function () {
  // Profile picture preview for add customer form
  const profilePicWrapper = $(".profile-upload");
  const profileInput = $("#profile-pic");
  const profilePreview = $("#profilePreview");

  profilePicWrapper.click(function () {
    profileInput.click();
  });

  profileInput.change(function (e) {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function (e) {
        profilePreview.attr('src', e.target.result);
      };
      reader.readAsDataURL(file);
    }
  });

  loadCustomers();
  $("#id").val(generateCustomerId());
});

// Save customer
function saveCustomer() {
  const id = $("#id").val();
  const name = $("#name").val();
  const address = $("#address").val();
  const age = $("#age").val();
  const profilePic = $("#profilePreview").attr('src');

  if (!name || !address || !age) {
    alert("Please fill in all fields");
    return;
  }

  const customer = {
    id,
    name,
    address,
    age,
    profilePic,
  };

  $.ajax({
    url: BASE_URL + "/save",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(customer),
    success: function () {
      alert("Customer saved successfully");
      resetForm();
      loadCustomers();
      $("#id").val(generateCustomerId());
    },
    error: function (error) {
      console.error("Error saving customer:", error);
    }
  });
}

// Load customers
function loadCustomers() {
  $.ajax({
    url: BASE_URL + "/getAll",
    type: "GET",
    success: function (customers) {
      const tableBody = $("#customerTable");
      tableBody.empty();

      customers.forEach((customer) => {
        const row = $("<tr>").html(`
          <td>
            <img src="${customer.profilePic}" alt="${customer.name}" class="customer-photo">
          </td>
          <td>${customer.id}</td>
          <td>${customer.name}</td>
          <td>${customer.address}</td>
          <td>${customer.age}</td>
          <td>
            <button class="btn btn-action btn-edit me-2" onclick="editCustomer('${customer.id}')">
              <i class="hgi-stroke hgi-pencil-edit-02 fs-5"></i>
            </button>
            <button class="btn btn-action btn-delete" onclick="deleteCustomer('${customer.id}')">
              <i class="hgi-stroke hgi-delete-02 fs-5"></i>
            </button>
          </td>
        `);
        tableBody.append(row);
      });
    },
    error: function (error) {
      alert("Error loading customers: " + error.statusText);
    }
  });
}

// Edit customer
function editCustomer(id) {
  $.ajax({
    url: `${BASE_URL}/getAll`,
    type: "GET",
    success: function (customers) {
      const customer = customers.find((c) => c.id === id);
      if (customer) {
        $("#editId").val(customer.id);
        $("#editName").val(customer.name);
        $("#editAddress").val(customer.address);
        $("#editAge").val(customer.age);
        $("#editProfilePreview").attr('src', customer.profilePic);

        const editModal = new bootstrap.Modal($("#editModal")[0]);
        editModal.show();
      }
    },
    error: function (error) {
      alert("Error fetching customer for edit: " + error.statusText);
    }
  });
}

// Update customer
function updateCustomer() {
  const id = $("#editId").val();
  const name = $("#editName").val();
  const address = $("#editAddress").val();
  const age = $("#editAge").val();
  const profilePic = $("#editProfilePreview").attr('src');

  if (!name || !address || !age) {
    alert("Please fill in all fields");
    return;
  }

  const customer = {
    id,
    name,
    address,
    age,
    profilePic,
  };

  $.ajax({
    url: BASE_URL + "/update",
    type: "PUT",
    contentType: "application/json",
    data: JSON.stringify(customer),
    success: function () {
      alert("Customer updated successfully");
      const editModal = bootstrap.Modal.getInstance($("#editModal")[0]);
      editModal.hide();
      loadCustomers();
    },
    error: function (error) {
      alert("Error updating customer: " + error.statusText);
    }
  });
}

// Delete customer
function deleteCustomer(id) {
  $.ajax({
    url: `${BASE_URL}/delete/${id}`,
    type: "DELETE",
    success: function () {
      alert("Customer deleted successfully");
      loadCustomers();
    },
    error: function (error) {
      alert("Error deleting customer: " + error.statusText);
    }
  });
}

// Profile picture preview for edit modal
$(document).ready(function () {
  const editProfilePicWrapper = $("#editModal .profile-upload");
  const editProfileInput = $("#edit-profile-pic");
  const editProfilePreview = $("#editProfilePreview");

  editProfilePicWrapper.click(function () {
    editProfileInput.click();
  });

  editProfileInput.change(function (e) {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function (e) {
        editProfilePreview.attr('src', e.target.result);
      };
      reader.readAsDataURL(file);
    }
  });
});

// Remove profile picture from edit modal
const editRemoveProfilePicBtn = $("#editModal .remove-image-btn");
const editProfileInput = $("#edit-profile-pic");
const editProfilePreview = $("#editProfilePreview");

// Show/hide the "X" button based on profile picture state
editProfileInput.change(function (e) {
  const file = e.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = function (e) {
      editProfilePreview.attr('src', e.target.result);
      editRemoveProfilePicBtn.css('display', 'flex');
    };
    reader.readAsDataURL(file);
  }
});

// Generate customer ID
function generateCustomerId() {
  const lastCustomer = $('#customerTable tr:last-child');
  if (lastCustomer.length === 0) return 'C001';

  const lastId = lastCustomer.children().eq(1).text();
  const num = parseInt(lastId.substring(1)) + 1;
  return `C${String(num).padStart(3, '0')}`;
}

// Reset profile picture to placeholder when "X" is clicked
editRemoveProfilePicBtn.click(function (e) {
  e.stopPropagation();
  editProfilePreview.addClass("removing");
  setTimeout(() => {
    editProfilePreview.attr('src', "./img/placeholder.jpg");
    editProfilePreview.removeClass("removing");
    editProfileInput.val("");
    editRemoveProfilePicBtn.css('display', 'none');
  }, 300);
});

// Reset form
function resetForm() {
  $("#customerForm")[0].reset();
  $("#profilePreview").attr('src', "./img/placeholder.jpg");
}
