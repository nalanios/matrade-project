// for now, the button doesn't execute any complex JavaScript. 
// currently it shows how it could interact with JavaScript, by logging a message to the console when clicked

const brandButton = document.getElementById('brand');
if (brandButton) {
    brandButton.addEventListener('click', function() {
        if (localStorage.getItem('user') != null) {
            window.location.href = "http://localhost:8080/search";
        } else {
            window.location.href = "http://localhost:8080/";
        }
        
    });
}

const createAccountButton = document.getElementById('createAccount');
if (createAccountButton) {
    createAccountButton.addEventListener('click', function() {
        console.log('Create Account button clicked');
        window.location.href = "http://localhost:8080/register"; //directs user to the account creation page
    });
}

const signInSignOutButton = document.getElementById('signInSignOut');
if (signInSignOutButton) {
    if (localStorage.getItem('user') != null) { // if user is signed in
        signInSignOutButton.textContent = "Sign Out";
        signInSignOutButton.addEventListener('click', function() {
            localStorage.removeItem('user');
            localStorage.removeItem('jwt');
            window.location.href = "http://localhost:8080/";
        });
    } else { // if user is signed out
        signInSignOutButton.textContent = "Sign In";
        signInSignOutButton.addEventListener('click', function() {
            window.location.href = "http://localhost:8080/login";
        });
    }
}

const myProfileButton = document.getElementById('myProfile');
if (myProfileButton) {
    const token = localStorage.getItem("jwt");
    if (token != null) {
        const username = localStorage.getItem("user");
        checkRoles(token)
            .then(data => {
                data.forEach(role => {
                    console.log("Role:", role);
                });
                if (data.includes("CUSTOMER")) {
                    getCustomerInformation(username)
                    .then(responseData => {
                        myProfileButton.textContent = responseData.firstName+"\'s Profile";
                    })
                    .catch(error => {
                        console.error("Error fetching data:", error);
                    });
                } else if (data.includes("RESTAURANT")) {
                    getRestaurantInformation(username)
                        .then(responseData => {
                            myProfileButton.textContent = responseData.restaurantName+"\'s Profile";
                        })
                        .catch(error => {
                            console.error("Error fetching data:", error);
                        });
                }
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }
    myProfileButton.addEventListener('click', function() {
        if (token != null) {
            checkRoles(token)
            .then(data => {
                data.forEach(role => {
                    console.log("Role:", role);
                });
                if (data.includes("CUSTOMER")) {
                    window.location.href = "http://localhost:8080/customer/profile";
                } else if (data.includes("RESTAURANT")) {
                    window.location.href = "http://localhost:8080/restaurant/profile";
                } else {
                    window.location.href = "http://localhost:8080/login";
                }
            })
            .catch(error => {
                console.error("Error:", error);
            });
        } else {
            window.location.href = "http://localhost:8080/login";
        }
    });
}

const searchForRestaurantsButton = document.getElementById('searchForRestaurants');
if (searchForRestaurantsButton) {
    searchForRestaurantsButton.addEventListener('click', function() {
        window.location.href = "http://localhost:8080/search";
    })
}

/*search for food button*/
const searchButton = document.getElementById('searchButton');
if (searchButton) {
    searchButton.addEventListener('click', function() {
        const restaurantName = document.getElementById('restaurant').value;
        const cuisine = document.getElementById('cuisine').value;
        const price = document.getElementById('price').value;
        const rating = document.getElementById('rating').value;
    
        console.log('Searching with filters:');
        console.log('Restaurant Name:', restaurantName);
        console.log('Cuisine:', cuisine);
        console.log('Price:', price);
        console.log('Rating:', rating);
    });
    
    /* display search results */
    document.getElementById('searchButton').addEventListener('click', function() {
        const restaurantName = document.getElementById('restaurant').value;
        const cuisine = document.getElementById('cuisine').value;
        const price = document.getElementById('price').value;
        const rating = document.getElementById('rating').value;
    
        // logs the search criteria to the console
        console.log('Searching with filters:');
        console.log('Restaurant Name:', restaurantName);
        console.log('Cuisine:', cuisine);
        console.log('Price:', price);
        console.log('Rating:', rating);
    
        // example data - replace this with actual data once back end is hooked up
        const searchResults = [
            { name: "Restaurant A", cuisine: "Italian", rating: "★★★★☆" },
            { name: "Restaurant B", cuisine: "Mexican", rating: "★★★☆☆" }
        ];
    
        // clear previous results
        const resultsContainer = document.getElementById('searchResults');
        resultsContainer.innerHTML = '';
    
        // append new results
        searchResults.forEach(result => {
            const resultItem = document.createElement('div');
            resultItem.className = 'result-item';
            resultItem.innerHTML = `
                ${result.name} | ${result.cuisine} | ${result.rating}
                <button class="result-button">See Details</button>
                <button class="result-button">Check Availability</button>
            `;
            resultsContainer.appendChild(resultItem);
        });
    });
}


	
// helper functions
async function checkIfAccountExists(accountType) {
    const response = await fetch('http://localhost:8080/'+accountType+'/check-exists?username='+JSON.parse(localStorage.getItem('user')), {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    if (response.ok) {
        const data = await response.json();
        console.log(data)
        return data;
    } else {
        throw new Error('Failed to check if '+accountType+' account exists.');
    }
}

async function checkRoles(token) {
    const response = await fetch("http://localhost:8080/check-roles?token="+JSON.parse(token), {
        method: 'GET',
        headers: {
                Authorization: "Bearer " +  JSON.parse(localStorage.getItem("jwt"))
            }
    });
    if(response.status == 401){
		 localStorage.removeItem('user');
         localStorage.removeItem('jwt');
         window.location.href = 'http://localhost:8080/login';
	}
    const responseData = await response.json();
    return responseData;
}

async function getCustomerByID(customerID) {
    const response = await fetch('http://localhost:8080/customer/'+customerID+'/details', {
        method: 'GET',
        headers: {
                Authorization: "Bearer " +  JSON.parse(localStorage.getItem("jwt"))
            }
    });
    const responseData = await response.json();
    return responseData;
}

async function getCustomerInformation(username){
    const response = await fetch('http://localhost:8080/customer/'+JSON.parse(localStorage.getItem('user'))+'/information', {
        method: 'GET',
        headers: {
                Authorization: "Bearer " +  JSON.parse(localStorage.getItem("jwt"))
            }
    })
    const responseData = await response.json();
    return responseData;
}

async function getRestaurantInformation(username) {
    const response = await fetch('http://localhost:8080/profile/'+username+'/information', {
        method: 'GET',
        headers: {
                Authorization: "Bearer " +  JSON.parse(localStorage.getItem("jwt"))
            }
    });
    const responseData = await response.json();
    return responseData;
}

async function getRestaurantByID(restaurantID) {
    const response = await fetch('http://localhost:8080/restaurant/'+restaurantID+'/details', {
        method: 'GET',
        headers: {
                Authorization: "Bearer " +  JSON.parse(localStorage.getItem("jwt"))
            }
    });
    const responseData = await response.json();
    return responseData;
}

async function getRestaurantByName(name) {
    const response = await fetch('http://localhost:8080/restaurant/'+name+'/information', {
        method: 'GET',
        headers: {
                Authorization: "Bearer " +  JSON.parse(localStorage.getItem("jwt"))
            }
    });
    const responseData = await response.json();
    return responseData;
}

async function performRedirect(route, token) {
    const response = await fetch('http://localhost:8080'+route, {
        method: 'GET',
        headers: {
           Authorization: "Bearer " +  JSON.parse(token)
        } 
    });

    if(response.status == 200){
        console.log("success");
        const redirectUrl = "http://localhost:8080"+route;
        window.location.href = redirectUrl;
    }
    if(response.status == 401){
    console.log("fail");
    }
}

async function makeReservation(partySize, reservationTime, restaurantName){
	var customerID = await getCustomerInformation(localStorage.getItem("user"))
	.then(function(responseData) {
                        return responseData.customerID;
                    })
                    .catch(error => {
                        console.error("Error fetching data:", error);
                    });
    
    
    var restaurantID = await getRestaurantByName(JSON.stringify(restaurantName))
    .then(function(responseData) {
                        return responseData.restaurantID;
                    })
                 
                    .catch(error => {
                        console.error("Error fetching data:", error);
                    });                    
	
	const response = await fetch('http://localhost:8080/reserve', {
        method: 'POST',
        headers: {
		   'Content-Type': 'application/json',          
           Authorization: "Bearer " +  JSON.parse(localStorage.getItem("jwt"))
        }, 
        body: JSON.stringify({customerID, restaurantID, partySize, reservationTime})
    });
    if (response.status == 200) {
        window.location.href = "http://localhost:8080/customer/profile";
    } else {
        console.log("Failed to create reservation");
    }
}
