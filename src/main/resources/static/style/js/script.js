window.onload = function () {
    document.getElementById('content').classList.toggle('blur');
}

function show_hide_password(target){
    let input = document.getElementById('password');
    if (input.getAttribute('type') === 'password') {
        target.classList.add('view');
        input.setAttribute('type', 'text');
    } else {
        target.classList.remove('view');
        input.setAttribute('type', 'password');
    }
    return false;
}

let burger = document.getElementById('burger-btn');
burger.addEventListener('click', () => {
    if(!document.getElementById('drop-menu-2').classList.contains('close-menu-2')){
        document.getElementById('drop-menu-2').classList.toggle('close-menu-2');
        document.getElementById('content').classList.toggle('blur');
    }
    document.getElementById('drop-menu').classList.toggle('close-menu');
    document.getElementById('content').classList.toggle('blur');
    burger.classList.toggle('close-burger');
})


let drop_menu = document.getElementById('docs-btn');
drop_menu.addEventListener('click', () => {
    if(!document.getElementById('drop-menu').classList.contains('close-menu')){
        document.getElementById('drop-menu').classList.toggle('close-menu');
        document.getElementById('content').classList.toggle('blur');
        burger.classList.toggle('close-burger');
    }
    console.log(document.getElementById('drop-menu-2').classList);
    document.getElementById('drop-menu-2').classList.toggle('close-menu-2');
    document.getElementById('content').classList.toggle('blur');
})