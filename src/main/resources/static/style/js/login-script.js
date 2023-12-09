
let animateLogin = document.getElementById('auth-page');
animateLogin.addEventListener('mouseleave', function() {
    console.log(document.getElementById('log-wrapper').classList);
    // let wrapper = document.getElementById('log-wrapper');
    document.getElementById('log-wrapper').classList.toggle('wrapper-hover');
});

let animateLogin2 = document.getElementById('auth-page');
animateLogin2.addEventListener('mouseenter', function() {
    console.log(document.getElementById('log-wrapper').classList);
    // let wrapper = document.getElementById('log-wrapper');
    document.getElementById('log-wrapper').classList.toggle('wrapper-hover');
});

