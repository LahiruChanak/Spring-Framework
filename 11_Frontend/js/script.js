function saveCustomer() {
  let id = document.getElementById("id").value;
  let name = document.getElementById("name").value;

  if (!id || !name) {
    alert("ID and Name are required!");
    return;
  }

  let table = document.getElementById("customerTable");
  let row = table.insertRow();
  row.innerHTML = `
            <td><img src="#" alt="Profile Picture" class="profile-pic"></td>
            <td>${id}</td>
            <td>${name}</td>
            <td>
                <button onclick="editCustomer(this)">Edit</button>
                <button onclick="removeCustomer(this)">Delete</button>
            </td>`;

  clearForm();
}

function updateCustomer() {
  alert("Update functionality can be added here!");
}

function deleteCustomer() {
  alert("Delete functionality can be added here!");
}

function editCustomer(button) {
  let row = button.parentElement.parentElement;
  document.getElementById("id").value = row.cells[1].innerText;
  document.getElementById("name").value = row.cells[2].innerText;
}

function removeCustomer(button) {
  let row = button.parentElement.parentElement;
  row.remove();
}

function clearForm() {
  document.getElementById("id").value = "";
  document.getElementById("name").value = "";
  document.getElementById("address").value = "";
  document.getElementById("age").value = "";
  document.getElementById("profile-pic").value = "";
}
