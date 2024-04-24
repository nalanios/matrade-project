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
    myProfileButton.addEventListener('click', function() {
        const token = localStorage.getItem("jwt");
        console.log(token);
        if (token != null) {
            fetch("http://localhost:8080/check-roles?token="+JSON.parse(token))
            .then(response => {
                if (!response.ok) {
                    console.log("Failed to fetch roles");
                }
                return response.json();
            })
            .then(data => {
                console.log("Roles:", data);
                data.forEach(role => {
                    console.log("Role:", role);
                });
                if (data.includes("CUSTOMER")) {
                    window.location.href = "http://localhost:8080/customer/profile";
                } else if (data.includes("RESTAURANT")) {
                    window.location.href = "http://localhost:8080/restaurant/profile";
                } else {
                    window.location.href = "http://localhost:8080/login";
                    console.log("No valid route found");
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


