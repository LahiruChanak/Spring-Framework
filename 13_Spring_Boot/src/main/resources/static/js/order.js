// Initialize the page
$(document).ready(function () {
    fetchCustomerData();
    fetchItemData();
    setDateTime();
    fetchOrderTable();
    generateOrderId();
});

function generateOrderId() {
    const lastRow = $("#orderTable tr:last");

    if (lastRow.length === 0) {
        $("#orderId").val("ORD-001");
        return;
    }

    const lastId = lastRow.find("td:eq(0)").text().trim(); // Get the last ID from the table
    const num = parseInt(lastId.replace("ORD-", "")) + 1;
    $("#orderId").val(`ORD-${String(num).padStart(3, "0")}`);
}

// Load customer data into the dropdown
function fetchCustomerData() {
    $.ajax({
        url: "http://localhost:8080/api/v1/customer/getAll",
        success: function (response) {
            const $customerSelect = $('#customerId');
            $customerSelect.empty();
            $customerSelect.append('<option value="">Select Customer</option>');
            for (const customer of response.data || response) {
                $customerSelect.append(`<option value="${customer.id}" data-name="${customer.name}">${customer.id}</option>`);
            }
        },
        error: function (error) {
            console.error("Error fetching customer data:", error);
            showAlert("danger", "Failed to load customer data!");
        }
    });
}

// Load item data into the dropdown
function fetchItemData() {
    $.ajax({
        url: "http://localhost:8080/api/v1/item/getAll",
        success: function (response) {
            const $itemSelect = $('#itemCode');
            $itemSelect.empty();
            $itemSelect.append('<option value="">Select Item</option>');
            for (const item of response.data || response) {
                $itemSelect.append(`<option value="${item.itemCode}" data-name="${item.description}" data-price="${item.unitPrice}" data-qty="${item.qtyOnHand}">${item.itemCode}</option>`);
            }
        },
        error: function (error) {
            console.error("Error fetching item data:", error);
            showAlert("danger", "Failed to load item data!");
        }
    });
}

// Show customer name when customer is selected
$('#customerId').on('change', function () {
    const selectedOption = $(this).find(':selected');
    const customerName = selectedOption.data('name') || '';
    $('#customerName').val(customerName ? `${customerName}` : '');
});

// Show item name and price when item is selected
$('#itemCode').on('change', function () {
    const selectedOption = $(this).find(':selected');
    const itemName = selectedOption.data('name') || '';
    const itemPrice = selectedOption.data('price') || '';
    const itemQty = selectedOption.data('qty') || '';
    $('#itemName').val(itemName ? `${itemName}` : '');
    $('#itemPrice').val(itemPrice);
    $('#itemQty').val(itemQty);
});

// Calculate total when order quantity changes
$('#orderQty').on('input', function () {
    const qty = parseInt($(this).val()) || 0;
    const price = parseFloat($('#itemPrice').val()) || 0;
    const total = qty * price;
    $('#totalPrice').val(total.toFixed(2));
});

// Save order
function saveOrder() {
    const orderId = $('#orderId').val();
    const dateTime = $('#orderDate').val();
    const customerId = $('#customerId').val();
    const itemCode = $('#itemCode').val();
    const qty = $('#orderQty').val();
    const total = $('#totalPrice').val();

    if (!orderId || !dateTime || !customerId || !itemCode || !qty || !total) {
        showAlert("error", "All fields are required!");
        return;
    }

    const orderDTO = {
        orderId: orderId,
        dateTime: dateTime,
        customerId: customerId,
        orderDetails: [{
            orderId: orderId,
            itemCode: itemCode,
            qty: qty,
            subTotal: total
        }]
    };

    $.ajax({
        url: "http://localhost:8080/api/v1/order/save",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(orderDTO),
        success: function (response) {
            showAlert("success", "Order Placed Successfully!");
            fetchOrderTable();
            clearForm();
            $('#orderConfirmModal').modal('hide');
        },
        error: function (error) {
            console.error("Error saving order:", error);
            showAlert("error", "Failed to place order! -> " + error);
        }
    });
}

// Fetch and display orders in the table
function fetchOrderTable() {
    $.ajax({
        url: "http://localhost:8080/api/v1/order/getAll",
        success: function (response) {
            const $orderTable = $('#orderTable');
            $orderTable.empty();
            for (const order of response) {
                for (const detail of order.orderDetails) {

                    const unitPrice = detail.qty > 0 ? (detail.subTotal / detail.qty).toFixed(2) : 0;   // Calculate unit price, handle division by zero

                    $orderTable.append(`
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.customerId}</td>
                            <td>${detail.itemCode}</td>
                            <td>${detail.qty}</td>
                            <td>${unitPrice}</td>
                            <td>${detail.subTotal}</td>
                            <td>${order.dateTime}</td>
                        </tr>
                    `);
                }
            }

            generateOrderId();
        },
        error: function (error) {
            console.error("Error fetching order data:", error);
            showAlert("error", "Failed to load order data!" + error);
        }
    });
}

// Show toast
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
        $(".alert").alert("close");
    }, 3000);
}

// Set the current date and time
function setDateTime() {
    let now = new Date();
    now.setMinutes(now.getMinutes() + now.getTimezoneOffset() + 630);   // Set the timezone to Sri Lanka
    $('#orderDate').val(now.toISOString().split('T')[0] + 'T' + now.toISOString().split('T')[1].slice(0, 8));
}

setInterval(setDateTime, 1000); // Update the date-time every second

// Clear the form
function clearForm() {
    $('#orderForm')[0].reset();
    setDateTime();
}