function findAllCity() {
       $.ajax({
        url: "http://localhost:8080/api/cities",
        type: "GET",
        success: function (data) {
            displayTableCity(data)
        }
    })
}
function displayTableCity(value){
    let content =`<h1>Danh sách thành phố</h1>
                        <button onclick="displayFormCreateCity()">Add new city</button>
                        <table> 
                        <tr><th>#</th>
                        <th>Thành phố</th>
                        <th>Quốc gia</th>
                        <th>Action</th></tr>`
    for (let i = 0; i < value.length; i++) {
        content += `<tr>
                    <th>${value[i].id}</th>
                    <th onclick="detailCity(${value[i].id})">${value[i].name}</th>
                    <th>${value[i].country.name}</th>
                    <th><button onclick="displayFormUpdateCity(${value[i].id})">Update</button></th>
                    <th><button onclick="deleteCity(${value[i].id})">Delete</button></th>
                    </tr>
                   `
    }
    content += `</table>`
    document.getElementById("list_city").innerHTML = content
    document.getElementById("list_city").style.display = "block"
    document.getElementById("create_city").style.display = "none"
    document.getElementById("update_city").style.display = "none"
    document.getElementById("list_city_views").style.display = "none"
}
function createCity() {
    let name = $("#name-c").val()
    let area = $("#area-c").val()
    let population = $("#population-c").val()
    let gdp = $("#gdp-c").val()
    let introduce = $("#introduce-c").val()
    let countryId = $("#country-id").val()
    let city = {
        name: name,
        area: area,
        population: population,
        gdp: gdp,
        introduce: introduce,
        country: {
            id: countryId
        }
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/api/cities",
        type: "POST",
        data: JSON.stringify(city),
        success: function () {
            findAllCity()
        }
    })
}

function detailCity(id) {
    $.ajax({
        url: `http://localhost:8080/api/cities/${id}`,
        type: "GET",
        success: function (data) {
            let content = `<div>
                                       <h3>Tên :${data.name}</h3>
                                         <span>Quốc gia :${data.country.name}</span>
                                         <p>Diện tích :${data.area}</p>
                                       <p> Dân số: ${data.population}</p>
                                         <p>GDP :${data.gdp}</p>
                                        <p>Giới thiệu :${data.introduce}</p>
                                        <button
                                            onclick="displayFormUpdateCity(${data.id})">Update</button>
                                        <button
                                            onclick="deleteCity(${data.id})">Delete</button>
                                        <button
                                            onclick="findAllCity()">Back to home</button`
            document.getElementById("list_city_views").innerHTML = content
            document.getElementById("list_city_views").style.display = "block"
            document.getElementById("list_city").style.display = "none"

        }
    })
}
function displayFormCreateCity(){
    getCountryCreate()
    document.getElementById("create_city").style.display = "block"
    document.getElementById("list_city").style.display = "none"
    document.getElementById("list_country").style.display = "none"
}
function getCountryCreate() {
    $.ajax({
        url: "http://localhost:8080/api/countries",
        type: "GET",
        success: function (countries) {
            let content = `<select id="country-id" class="form-select" aria-label="Default select example">
                <option selected>Open this select menu</option>`
            for (let i = 0; i < countries.length; i++) {
                content += `<option value="${countries[i].id}">${countries[i].name}</option>`
            }
            content += `</select>`
            $("#country-select").html(content)
        }
    })
}
function getCountryUpdate() {
    $.ajax({
        url: "http://localhost:8080/api/countries",
        type: "GET",
        success: function (countries) {
            let content = `<select id="country-id-u" class="form-select" aria-label="Default select example">
                <option selected>Open this select menu</option>`
            for (let i = 0; i < countries.length; i++) {
                content += `<option value="${countries[i].id}">${countries[i].name}</option>`
            }
            content += `</select>`
            $("#country-select-u").html(content)
        }
    })
}
function updateCity() {
    let name = $("#name-u").val()
    let area = $("#area-u").val()
    let population = $("#population-u").val()
    let gdp = $("#gdp-u").val()
    let introduce = $("#introduce-u").val()
    let countryId = $("#country-id-u").val()
    let city = {
        name: name,
        area: area,
        population: population,
        gdp : gdp,
        introduce : introduce,
        country: {
            id: countryId
    }
}
    $.ajax({
        url: `http://localhost:8080/api/cities/${idUpdate}`,
        processData: false,
        contentType: false,
        type: "PUT",
        data: JSON.stringify(city),
        success: function () {
            findAllCity()
        },
        error: function () {
            alert("Food not exists!")
        }
    })
}


function deleteCity(id) {
    if (confirm("Are you sure?")) {
        $.ajax({
            url: `http://localhost:8080/api/cities/${id}`,
            type: "DELETE",
            success: function (data) {
                findAllCity()
            }
        })
    }
}
let idUpdate
function displayFormUpdateCity(id){
    $.ajax({
        url: `http://localhost:8080/api/cities/${id}`,
        type: "GET",
        success: function (data) {
            idUpdate = data.id
            console.log(idUpdate)
            $("#name-u").val(data.name)
            $("#area-u").val(data.area)
            $("#population-u").val(data.population)
            $("#gdp-u").val(data.gdp)
            $("#introduce-u").val(data.introduce)
            getCountryUpdate()
        }
    })
    document.getElementById("update_city").style.display = "block"
    document.getElementById("create_city").style.display = "none"
    document.getElementById("list_city").style.display = "none"
}
