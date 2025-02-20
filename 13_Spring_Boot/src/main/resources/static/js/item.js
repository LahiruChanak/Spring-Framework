let isEditing = false;
let currentEditCode = null;

fetchItemData();

function fetchItemData() {
    $.ajax({
        url: "http://localhost:8080/api/v1/item/getAll",
        success: function (response) {
            $("#itemTable").empty();
            for (const data of response) {
                $("#itemTable").append(
                    `<tr data-code="${data.code}">
                            ${createTableRow(data.code, data.name, data.qtyOnHand, data.unitPrice)}
                        </tr>`
                );
            }
        },
        error: function (error) {
            alert(error);
        }
    });
}

$('#itemForm').on('submit', function (e) {
    e.preventDefault();
    saveItem();
});

function saveItem() {
    const code = $('#code').val();
    const name = $('#name').val();
    const qtyOnHand = $('#qtyOnHand').val();
    const unitPrice = $('#unitPrice').val();

    if (!code || !name || !qtyOnHand || !unitPrice) {
        alert('All fields are required!');
        return;
    }

    if (isEditing) {
        updateExistingItem(code, name, qtyOnHand, unitPrice);
    } else {
        addNewItem(code, name, qtyOnHand, unitPrice);
    }

    clearForm();
}

function addNewItem(code, name, qtyOnHand, unitPrice) {
    $.ajax({
        url: "http://localhost:8080/api/v1/item/save",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(
            {
                code: code,
                name: name,
                qtyOnHand: qtyOnHand,
                unitPrice: unitPrice
            }),
        success: function (response) {
            fetchItemData();
            clearForm();
            alert("Item Saved!");
        },
        error: function (error) {
            alert(error);
        }
    });
}

function updateExistingItem(code, name, qtyOnHand, unitPrice) {
    $.ajax({
        url: "http://localhost:8080/api/v1/item/update",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(
            {
                code: code,
                name: name,
                qtyOnHand: qtyOnHand,
                unitPrice: unitPrice
            }),
        success: function (response) {
            fetchItemData();
            clearForm();
            alert("Item Updated!");

            isEditing = false;
            currentEditCode = null;
            $('#saveBtn').html('<i class="bi bi-save"></i> Save Item');
        },
        error: function (error) {
            alert(error);
        }
    });
}

function createTableRow(code, name, qtyOnHand, unitPrice) {
    return `
            <td>${code}</td>
            <td>${name}</td>
            <td>${qtyOnHand}</td>
            <td>${unitPrice}</td>
            <td>
                <button class="btn btn-action btn-edit me-2" onclick="editItem(this)">
                    <i class="hgi-stroke hgi-pencil-edit-02 fs-5"></i>
                </button>
                <button class="btn btn-action btn-delete" onclick="removeItem(this)">
                    <i class="hgi-stroke hgi-delete-02 fs-5"></i>
                </button>
            </td>
        `;
}

window.editItem = function (button) {
    const $row = $(button).closest('tr');
    const $cells = $row.find('td');

    $('#code').val($cells.eq(0).text());
    $('#name').val($cells.eq(1).text());
    $('#qtyOnHand').val($cells.eq(2).text());
    $('#unitPrice').val($cells.eq(3).text());

    isEditing = true;
    currentEditCode = $cells.eq(0).text();
    $('#saveBtn').html('<i class="bi bi-check-circle"></i> Update Item');
    $('#code').prop('disabled', true);
};

window.removeItem = function (button) {
    const $row = $(button).closest('tr');
    const $cells = $row.find('td');

    let code = $cells.eq(0).text();

    if (confirm('Are you sure you want to delete this item?')) {
        $.ajax({
            url: "http://localhost:8080/api/v1/item/delete/" + code,
            method: "DELETE",
            success: function (response) {
                fetchItemData();
                alert("Item Deleted!");
            },
            error: function (error) {
                console.log(error)
            }
        })
    }
};

function clearForm() {
    $('#itemForm')[0].reset();
    $('#code').prop('disabled', false);
    isEditing = false;
    currentEditCode = null;
    $('#saveBtn').html('<i class="bi bi-save"></i> Save Item');
}