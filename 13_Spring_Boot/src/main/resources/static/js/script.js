let isEditing = false;
let currentEditId = null;

fetchCustomerData();

function fetchCustomerData() {
    $.ajax({
        url: "http://localhost:8080/api/v1/customer/getAll",
        success: function (response) {
            $("#customerTable").empty();
            for (const data of response) {
                $("#customerTable").append(
                    `<tr data-id="${data.id}">
                            ${createTableRow(data.id, data.name, data.address, data.age)}
                        </tr>`
                );
            }
        },
        error: function (error) {
            alert(error);
        }
    });
}

$('#customerForm').on('submit', function (e) {
    e.preventDefault();
    saveCustomer();
});

function saveCustomer() {
    const id = $('#id').val();
    const name = $('#name').val();
    const address = $('#address').val();

    if (!id || !name) {
        alert('ID and Name are required!');
        return;
    }

    if (isEditing) {
        updateExistingCustomer(id, name, address);
    } else {
        addNewCustomer(id, name, address);
    }

    clearForm();
}

function addNewCustomer(id, name, address) {
    $.ajax({
        url: "http://localhost:8080/api/v1/customer/save",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(
            {
                id: id,
                name: name,
                address: address
            }),
        success: function (response) {
            fetchCustomerData()
            clearForm();
            alert("Customer Saved!");
        },
        error: function (error) {
            alert(error);
        }
    });
}

function updateExistingCustomer(id, name, address) {
    $.ajax({
        url: "http://localhost:8080/api/v1/customer/update",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(
            {
                id: id,
                name: name,
                address: address
            }),
        success: function (response) {
            fetchCustomerData()
            clearForm();
            alert("Customer Updated!");

            isEditing = false;
            currentEditId = null;
            $('#saveBtn').html('<i class="bi bi-save"></i> Save Customer');
        },
        error: function (error) {
            alert(error);
        }
    });
}

function createTableRow(id, name, address) {
    return `
            <td>${id}</td>
            <td>${name}</td>
            <td>${address}</td>
            <td>
                <button class="btn btn-action btn-edit me-2" onclick="editCustomer(this)">
                    <i class="hgi-stroke hgi-pencil-edit-02 fs-5"></i>
                </button>
                <button class="btn btn-action btn-delete" onclick="removeCustomer(this)">
                    <i class="hgi-stroke hgi-delete-02 fs-5"></i>
                </button>
            </td>
        `;
}

window.editCustomer = function (button) {
    const $row = $(button).closest('tr');
    const $cells = $row.find('td');

    $('#id').val($cells.eq(0).text());
    $('#name').val($cells.eq(1).text());
    $('#address').val($cells.eq(2).text());

    isEditing = true;
    currentEditId = $cells.eq(0).text();
    $('#saveBtn').html('<i class="bi bi-check-circle"></i> Update Customer');
    $('#id').prop('disabled', true);
};

window.removeCustomer = function (button) {
    const $row = $(button).closest('tr');
    const $cells = $row.find('td');

    let id = $cells.eq(0).text();

    if (confirm('Are you sure you want to delete this customer?')) {
        $.ajax({
            url: "http://localhost:8080/api/v1/customer/delete/" + id,
            method: "DELETE",
            success: function (response) {
                fetchCustomerData();
                alert("Customer Deleted!");
            },
            error: function (error) {
                console.log(error)
            }
        })
    }
};

function clearForm() {
    $('#customerForm')[0].reset();
    $('#id').prop('disabled', false);
    isEditing = false;
    currentEditId = null;
    $('#saveBtn').html('<i class="bi bi-save"></i> Save Customer');
}
