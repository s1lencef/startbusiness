for (let i = 1; i <= 7; i++) {
    let menu_btn2 = document.getElementById('docs' + i);
    menu_btn2.addEventListener('click', function() {
        for (let j = 1; j <= 7; j++) {
            if(document.getElementById('p_' + j).classList.contains('p-open')) {
                document.getElementById('p_' + j).classList.toggle('p-open');
            }}
        document.getElementById('p_' + i).classList.toggle('p-open');
    });
}

let burger = document.getElementById('burger-btn');
burger.addEventListener('click', () => {
    if(!document.getElementById('drop-menu-2').classList.contains('close-menu-2')){
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
    if(!document.getElementById('drop-menu').classList.contains('close-menu')){
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
    if(!document.getElementById('drop-menu').classList.contains('close-menu-2')){
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
};


let requestToggle = document.getElementById('request-toggle')
requestToggle.addEventListener('click', () => {
    document.getElementById('req').classList.toggle('requests-closed');
    document.getElementById('request-toggle').classList.toggle('request-toggle-open');
    document.getElementById('req-form').classList.toggle('request-form-open');
    // document.getElementById('drop-menu').classList.toggle('close-menu');
    // document.getElementById('content').classList.toggle('blur');
    // burger.classList.toggle('close-burger');
});

// проверка на формат даты
let Input = document.getElementById('dateInput');
Input.addEventListener('input', function() {
    let dateInput = document.getElementById('dateInput').value;
    dateInput = dateInput.slice(0,10);
    let regex = /^(((0[1-9]|[1]\d|3[0])\/(0[-1]|1[2])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[1]\d|30)\/(0[-1]|1[1])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[-1]|[-1]|[-1])|(([-1]|[-1]|[-1])00))))$/;
    if (regex.test(dateInput)) {
        document.getElementById('date-result').textContent = 'Дата введена правильно!';
    } else {
        document.getElementById('date-result').textContent = 'Неверный формат даты!';
    }
});

// библиотека для шаблона даты
const element = document.getElementById('dateInput');
const mask = IMask(element, {
    mask: '00/00/0000',
    lazy: false, // make placeholder always visible
    placeholderChar: '_'    // defaults to '_'
});

let divToScroll = document.getElementById('scrollDiv');

divToScroll.addEventListener('wheel', function(e) {
    e.preventDefault();
    divToScroll.scrollLeft += e.deltaY*2;
}, false);

