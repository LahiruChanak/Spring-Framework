// Profile picture preview for add customer form
const profilePicWrapper = document.querySelector(".profile-upload");
const profileInput = document.getElementById("profile-pic");
const profilePreview = document.getElementById("profilePreview");

profilePicWrapper.addEventListener("click", function () {
  profileInput.click();
});

profileInput.addEventListener("change", function (e) {
  const file = e.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = function (e) {
      profilePreview.src = e.target.result;
    };
    reader.readAsDataURL(file);
  }
});

loadCustomers();

// Save customer
function saveCustomer() {
  const id = document.getElementById("id").value;
  const name = document.getElementById("name").value;
  const address = document.getElementById("address").value;
  const age = document.getElementById("age").value;
  const profilePic = document.getElementById("profilePreview").src;

  if (!id || !name || !address || !age) {
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

  fetch("http://localhost:8080/10_Backend_Web_exploded/api/v1/customer/save", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(customer),
  })
    .then((response) => response.json())
    .then((data) => {
      alert("Customer saved successfully");
      resetForm();
      loadCustomers();
    })
    .catch((error) => {
      alert("Error saving customer" + error);
    });
}

// Load customers
function loadCustomers() {
  fetch("http://localhost:8080/10_Backend_Web_exploded/api/v1/customer/getAll")
    .then((response) => response.json())
    .then((customers) => {
      const tableBody = document.getElementById("customerTable");
      tableBody.innerHTML = "";

      customers.forEach((customer) => {
        const row = document.createElement("tr");
        row.innerHTML = `
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
        `;
        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      alert("Error loading customers" + error);
    });
}

// Edit customer
function editCustomer(id) {
  fetch(`http://localhost:8080/10_Backend_Web_exploded/api/v1/customer/getAll`)
    .then((response) => response.json())
    .then((customers) => {
      const customer = customers.find((c) => c.id === id);
      if (customer) {
        document.getElementById("editId").value = customer.id;
        document.getElementById("editName").value = customer.name;
        document.getElementById("editAddress").value = customer.address;
        document.getElementById("editAge").value = customer.age;
        document.getElementById("editProfilePreview").src = customer.profilePic;

        const editModal = new bootstrap.Modal(
          document.getElementById("editModal")
        );
        editModal.show();
      }
    })
    .catch((error) => {
      alert("Error fetching customer for edit:" + error);
    });
}

// Update customer
function updateCustomer() {
  const id = document.getElementById("editId").value;
  const name = document.getElementById("editName").value;
  const address = document.getElementById("editAddress").value;
  const age = document.getElementById("editAge").value;
  const profilePic = document.getElementById("editProfilePreview").src;

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

  fetch(
    "http://localhost:8080/10_Backend_Web_exploded/api/v1/customer/update",
    {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(customer),
    }
  )
    .then((response) => response.json())
    .then(() => {
      alert("Customer updated successfully");

      const editModalEl = document.getElementById("editModal");
      const editModal = bootstrap.Modal.getInstance(editModalEl);
      editModal.hide();

      loadCustomers();
    })
    .catch((error) => {
      alert("Error updating customer:" + error);
    });
}

// Delete customer
function deleteCustomer(id) {
  fetch(
    `http://localhost:8080/10_Backend_Web_exploded/api/v1/customer/delete/${id}`,
    {
      method: "DELETE",
    }
  )
    .then((response) => {
      if (response.ok) {
        alert("Customer deleted successfully");
        loadCustomers();
      } else {
        alert("Error deleting customer");
      }
    })
    .catch((error) => {
      alert("Error deleting customer:" + error);
    });
}

// Profile picture preview for edit modal
const editProfilePicWrapper = document.querySelector(
  "#editModal .profile-upload"
);
const editProfileInput = document.getElementById("edit-profile-pic");
const editProfilePreview = document.getElementById("editProfilePreview");

editProfilePicWrapper.addEventListener("click", function () {
  editProfileInput.click();
});

editProfileInput.addEventListener("change", function (e) {
  const file = e.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = function (e) {
      editProfilePreview.src = e.target.result;
    };
    reader.readAsDataURL(file);
  }
});

// Remove profile picture from edit modal
const editRemoveProfilePicBtn = document.querySelector(
  "#editModal .remove-image-btn"
);

// Show/hide the "X" button based on profile picture state
editProfileInput.addEventListener("change", function (e) {
  const file = e.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = function (e) {
      editProfilePreview.src = e.target.result;
      editRemoveProfilePicBtn.style.display = "flex";
    };
    reader.readAsDataURL(file);
  }
});

// Reset profile picture to placeholder when "X" is clicked
editRemoveProfilePicBtn.addEventListener("click", function (e) {
  e.stopPropagation();
  editProfilePreview.classList.add("removing");
  setTimeout(() => {
    editProfilePreview.src = "./img/placeholder.jpg";
    editProfilePreview.classList.remove("removing");
    editProfileInput.value = "";
    editRemoveProfilePicBtn.style.display = "none";
  }, 300);
});

// Reset form
function resetForm() {
  document.getElementById("customerForm").reset();
  document.getElementById("profilePreview").src = "./img/placeholder.jpg";
}
