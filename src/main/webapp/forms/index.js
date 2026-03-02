// Unique function names to avoid matching Minidu's index.js
function toggleNav() {
    const navMenu = document.querySelector('.top-nav');
    navMenu.classList.toggle('show');
}

// Custom form validator for the login page
document.addEventListener("DOMContentLoaded", () => {
    const authForm = document.getElementById("auth-form");
    if(authForm) {
        authForm.addEventListener("submit", (event) => {
            const userVal = document.getElementById("username").value.trim();
            const passVal = document.getElementById("password").value.trim();
            if (!userVal || !passVal) {
                alert("Authentication requires both username and password.");
                event.preventDefault();
            }
        });
    }
});