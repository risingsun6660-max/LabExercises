// Add this function to clear specific errors
function clearSpecificError(elementId) {
    const errorElement = document.getElementById(elementId);
    if (errorElement) {
        errorElement.style.display = 'none';
        errorElement.textContent = '';
    }
}

// Update the real-time email validation
document.getElementById('email').addEventListener('blur', function() {
    const email = this.value.trim();
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    
    if (email && !emailRegex.test(email)) {
        showError('emailError', 'Please enter a valid email address');
    } else if (email) {
        clearSpecificError('emailError'); // Clear error when valid
    }
});

// Update the password input validation
document.getElementById('password').addEventListener('input', function() {
    updatePasswordRequirements(this.value);
    
    // Clear password error if requirements are met
    const passwordRequirements = validatePassword(this.value);
    const allRequirementsMet = Object.values(passwordRequirements).every(Boolean);
    
    if (allRequirementsMet) {
        clearSpecificError('passwordError');
    }
});

// Update the confirm password validation
document.getElementById('confirmPassword').addEventListener('input', function() {
    const password = document.getElementById('password').value;
    const confirmPassword = this.value;
    
    if (confirmPassword && password !== confirmPassword) {
        showError('confirmPasswordError', 'Passwords do not match');
    } else if (confirmPassword && password === confirmPassword) {
        clearSpecificError('confirmPasswordError'); // Clear error when passwords match
    }
});

// Add name validation
document.getElementById('name').addEventListener('input', function() {
    const name = this.value.trim();
    
    if (name.length >= 2) {
        clearSpecificError('nameError');
    }
});

// Update the form submission validation
document.getElementById('registrationForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    // Clear previous error messages
    clearErrors();
    
    // Get form values
    const name = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    
    let isValid = true;
    
    // Validate name
    if (name.length < 2) {
        showError('nameError', 'Name must be at least 2 characters long');
        isValid = false;
    }
    
    // Validate email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        showError('emailError', 'Please enter a valid email address');
        isValid = false;
    }
    
    // Validate password strength
    const passwordRequirements = validatePassword(password);
    const allRequirementsMet = Object.values(passwordRequirements).every(Boolean);
    
    if (!allRequirementsMet) {
        showError('passwordError', 'Password must meet all requirements above');
        isValid = false;
    }
    
    // Validate password confirmation
    if (password !== confirmPassword) {
        showError('confirmPasswordError', 'Passwords do not match');
        isValid = false;
    }
    
    // If all validations pass
    if (isValid) {
        console.log('Registration data:', { name, email, password: '***hidden***' });
        
        // Show success message
        document.getElementById('successMessage').style.display = 'block';
        
        // Reset form and clear requirements display
        this.reset();
        updatePasswordRequirements('');
        
        // Hide success message after 3 seconds
        setTimeout(() => {
            document.getElementById('successMessage').style.display = 'none';
        }, 3000);
    }
});