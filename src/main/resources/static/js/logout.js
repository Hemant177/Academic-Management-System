function logout() {
    fetch('/api/auth/logout', {
        method: 'POST',
        credentials: 'same-origin'
    }).then(() => {
        window.location.href = '/login.html?logout=true';
    });
}
