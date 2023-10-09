function getAllEmployees(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/employee/getAllEmployees",
        async:true,
        success: function (data) {
            if (data.code==="00"){
                $('#empTable').empty()
                for (let emp of data.content){
                    let empID=emp.empId
                    let name=emp.empName
                    let contact=emp.empContact
                    let street=emp.empAddress.street
                    let city=emp.empAddress.city
                    let dept=emp.empDept.deptName

                    var row=`<tr><td>${empID}</td><td>${name}</td><td>${contact}</td><td>${street}</td><td>${city}</td><td>${dept}</td></tr>`;
                    $('#empTable').append(row);
                }
            }
        },
        error: function (xhr, exception) {
            alert("Error")
        }
    })
}

getAllEmployees()

function saveEmployee(){
    let name=$('#empName').val();
    let contact=$('#empContact').val();
    let street=$('#empStreet').val();
    let city=$('#empCity').val();
    let dept=$('#empDept').val();

    if(name !== "" && contact !== "" && street !== "" && city !== "" && dept !== "" ){
        $.ajax({
            method:"POST",
            contentType:"application/json",
            url:"http://localhost:8080/api/employee/saveEmployee",
            async:true,
            data:JSON.stringify({
                "empId":"",
                "empName":name,
                "empContact":contact,
                "empAddress" : {
                    "addressId" : "",
                    "street" : street,
                    "city" : city
                },
                "empDept" : {
                    "deptId" : "",
                    "deptName" : dept
                }

            }),
            success: function (data) {
                alert("Saved successfully!")
                getAllEmployees()
            },
            error: function (xhr, exception) {
                alert("Error!")
            }
        })
    }
    else{
        alert("The fields cannot be empy!")
    }
}

function updateEmployee(){
    let empID = $('#empID').val();
    let name=$('#empName').val();
    let contact=$('#empContact').val();
    let street=$('#empStreet').val();
    let city=$('#empCity').val();
    let dept=$('#empDept').val();

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        url:"http://localhost:8080/api/employee/updateEmployee",
        async:true,
        data:JSON.stringify({
            "empId":empID,
            "empName":name,
            "empContact":contact,
            "empAddress" : {
               "addressId" : "",
                "street" : street,
                "city" : city
            },
            "empDept" : {
                "deptId" : "",
                "deptName" : dept
            }
        }),
        success: function (data) {
            alert("Updated successfully!")
            getAllEmployees()
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function deleteEmployee(){
    let empID = $('#empID').val();
    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/employee/deleteEmployee/" + empID,
        async:true,
        success: function() {
            alert("Deleted successfully!")
            getAllEmployees()
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function searchById(){
    let empID = $('#inputID').val();

    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/employee/searchEmployee/" + empID,
        async:true,
        success: function (data) {
            if (data.code==="00"){
                $('#empTable').empty()

                let empID=data.content.empId
                let name=data.content.empName
                let contact=data.content.empContact
                let street=data.content.empAddress.street
                let city=data.content.empAddress.city
                let dept=data.content.empDept.deptName

                var row=`<tr><td>${empID}</td><td>${name}</td><td>${contact}</td><td>${street}</td><td>${city}</td><td>${dept}</td></tr>`;
                $('#empTable').append(row);
            }
        },
        error: function () {
            alert("No employee exist for given ID!")
        } 
    })
}

function searchByCity(){
    let city=$('#inputCity').val();
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/employee/getEmpByCity/"+ city,
        async:true,
        success: function (data) {
            if (data.code==="00"){
                $('#empTable').empty()
                for (let emp of data.content){
                    let empID=emp.empId
                    let name=emp.empName
                    let contact=emp.empContact
                    let street=emp.empAddress.street
                    let city=emp.empAddress.city
                    let dept=emp.empDept.deptName

                    var row=`<tr><td>${empID}</td><td>${name}</td><td>${contact}</td><td>${street}</td><td>${city}</td><td>${dept}</td></tr>`;
                    $('#empTable').append(row);
                }
            }
        },
        error: function () {
            alert("No Employees in given City!")
        }
    })
}

function searchByDept(){
    let dept=$('#inputDept').val();
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/employee/getEmpByDept/"+ dept,
        async:true,
        success: function (data) {
            if (data.code==="00"){
                $('#empTable').empty()
                for (let emp of data.content){
                    let empID=emp.empId
                    let name=emp.empName
                    let contact=emp.empContact
                    let street=emp.empAddress.street
                    let city=emp.empAddress.city
                    let dept=emp.empDept.deptName

                    var row=`<tr><td>${empID}</td><td>${name}</td><td>${contact}</td><td>${street}</td><td>${city}</td><td>${dept}</td></tr>`;
                    $('#empTable').append(row);
                }
            }
        },
        error: function () {
            alert("No Employees in given department!")
        }
    })
}

$(document).ready(function () {
    $(document).on('click', '#empTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();
        var col4 = $(this).find('td:eq(4)').text();
        var col5 = $(this).find('td:eq(5)').text();

        $('#empID').val(col0);
        $('#empName').val(col1);
        $('#empContact').val(col2);
        $('#empStreet').val(col3);
        $('#empCity').val(col4);
        $('#empDept').val(col5);
    })
})