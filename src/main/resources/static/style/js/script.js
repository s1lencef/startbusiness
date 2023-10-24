window.onload = function () {
    document.getElementById('content').classList.toggle('blur');
    document.getElementById('content').classList.toggle('blur');
    document.getElementById('content').classList.toggle('blur');
    document.getElementById('content').classList.toggle('blur');
    document.getElementById('content').classList.toggle('blur');
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
