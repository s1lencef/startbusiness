for (let i = 1; i <= 5; i++) {
    let menu_btn2 = document.getElementById('docs' + i);
    menu_btn2.addEventListener('click', function () {
        for (let j = 1; j <= 5; j++) {
            if (document.getElementById('p_' + j).classList.contains('p-open')) {
                document.getElementById('p_' + j).classList.toggle('p-open');
            }
        }
        document.getElementById('p_' + i).classList.toggle('p-open');
    });
}

let burger = document.getElementById('burger-btn');
burger.addEventListener('click', () => {
    if (!document.getElementById('drop-menu-2').classList.contains('close-menu-2')) {
        document.getElementById('drop-menu-2').classList.toggle('close-menu-2');
        document.getElementById('menu-unfold-toggle').classList.toggle('menu-toggle-open');
        document.getElementById('content').classList.toggle('blur');
    }
    document.getElementById('drop-menu').classList.toggle('close-menu');
    document.getElementById('content').classList.toggle('blur');
    burger.classList.toggle('close-burger');
});

let drop_menu = document.getElementById('docs-btn');
drop_menu.addEventListener('click', () => {
    if (!document.getElementById('drop-menu').classList.contains('close-menu')) {
        document.getElementById('drop-menu').classList.toggle('close-menu');
        document.getElementById('content').classList.toggle('blur');
        burger.classList.toggle('close-burger');
    }
    console.log(document.getElementById('drop-menu-2').classList);
    document.getElementById('drop-menu-2').classList.toggle('close-menu-2');
    document.getElementById('menu-unfold-toggle').classList.toggle('menu-toggle-open');
    document.getElementById('content').classList.toggle('blur');
});

let drop_menu_2 = document.getElementById('docs-btn-2');
drop_menu_2.addEventListener('click', () => {
    if (!document.getElementById('drop-menu').classList.contains('close-menu-2')) {
        document.getElementById('drop-menu').classList.toggle('close-menu');
        document.getElementById('content').classList.toggle('blur');
        burger.classList.toggle('close-burger');
    }
    console.log(document.getElementById('drop-menu-2').classList);
    document.getElementById('drop-menu-2').classList.toggle('close-menu-2');
    document.getElementById('menu-unfold-toggle').classList.toggle('menu-toggle-open');
    document.getElementById('content').classList.toggle('blur');
});

let menuToggle = document.getElementById('menu-unfold-toggle')
menuToggle.addEventListener('click', () => {
    document.getElementById('drop-menu-2').classList.toggle('close-menu-2');
    document.getElementById('content').classList.toggle('blur');
    document.getElementById('menu-unfold-toggle').classList.toggle('menu-toggle-open');
});