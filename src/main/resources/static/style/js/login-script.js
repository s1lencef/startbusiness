


let animateLogin = document.getElementById('auth-page');
animateLogin.addEventListener('mouseleave', function() {
    if(document.getElementById('log-wrapper').classList.contains('wrapper-hover')){
        document.getElementById('log-wrapper').classList.remove('wrapper-hover');
    }
});

let animateLogin2 = document.getElementById('auth-page');
animateLogin2.addEventListener('mouseenter', function() {
    if(!document.getElementById('log-wrapper').classList.contains('wrapper-hover')){
        document.getElementById('log-wrapper').classList.add('wrapper-hover');
    }
});

