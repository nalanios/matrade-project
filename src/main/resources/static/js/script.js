// for now, the button doesn't execute any complex JavaScript. 
// currently it shows how it could interact with JavaScript, by logging a message to the console when clicked


document.getElementById('createAccount').addEventListener('click', function() {
    console.log('Create Account button clicked');
    window.location.href = "http://localhost:8080/register"; //directs user to the account creation page
});

document.getElementById('signIn').addEventListener('click', function() {
    console.log('Sign In hyperlink was clicked');
    window.location.href = "signin.html"; //directs user to the sign in page
});

document.getElementById('signOut').addEventListener('click', function() {
    console.log('Sign Out button clicked');
    window.location.href = "signin.html"; // redirects user back to sign in page
});

document.getElementById('myProfile').addEventListener('click', function() {
    console.log('My Profile button clicked');
    window.location.href = "profile.html"; // directs user to their profile page
});

/*search for food button*/

document.getElementById('searchButton').addEventListener('click', function() {
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

