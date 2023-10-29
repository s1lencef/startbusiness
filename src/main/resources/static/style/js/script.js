window.onload = function () {
    document.getElementById('content').classList.toggle('blur');
    document.getElementById('content').classList.toggle('blur');
    document.getElementById('content').classList.toggle('blur');
    document.getElementById('content').classList.toggle('blur');
    document.getElementById('content').classList.toggle('blur');

    // Get the input element by its ID
    const inputElement = document.getElementById('password');

    // Create a new MouseEvent object
    const clickEvent = new MouseEvent('click', {
    bubbles: true,
    cancelable: true,
    view: window
    });

    // Dispatch the click event on the input element
    inputElement.dispatchEvent(clickEvent);
}

function show_hide_password(target){
    var input = document.getElementById('password');
    if (input.getAttribute('type') == 'password') {
        target.classList.add('view');
        input.setAttribute('type', 'text');
    } else {
        target.classList.remove('view');
        input.setAttribute('type', 'password');
    }
    return false;
}



let burger = document.getElementById('menu-btn');
burger.addEventListener('click', () => {
    console.log(document.getElementById('drop-menu').classList);
    document.getElementById('drop-menu').classList.toggle('active-menu');
    document.getElementById('drop-menu').classList.toggle('close-menu');

    document.getElementById('content').classList.toggle('blur');
    burger.classList.toggle('close-burger');
});


$('#contacts').on('click', function (e) {
    $('html,body').stop().animate({
        scrollTop: $('footer').offset().top - 100
    }, 800);
    e.preventDefault();
});

$('#contacts2').on('click', function (e) {
            $('html,body').stop().animate({
                scrollTop: $('footer').offset().top - 100
            }, 800);
            e.preventDefault();
            if ($("#drop-menu").hasClass('active-menu')) {
                document.getElementById('drop-menu').classList.toggle('active-menu');
                document.getElementById('drop-menu').classList.toggle('close-menu');
                document.getElementById('content').classList.toggle('blur');
                burger.classList.toggle('close-burger');
            }
            });
