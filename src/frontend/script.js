// for now, the button doesn't execute any complex JavaScript. 
// currently it shows how it could interact with JavaScript, by logging a message to the console when clicked


document.getElementById('createAccount').addEventListener('click', function() {
    console.log('Create Account button clicked');
    window.location.href = "createaccount.html"; //Directs user to the account creation page
});

document.getElementById('signIn').addEventListener('click', function() {
    console.log('Sign In hyperlink was clicked');
    window.location.href = "signin.html"; //Directs user to the sign in page
});
