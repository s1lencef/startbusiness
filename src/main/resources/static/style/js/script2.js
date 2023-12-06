
let requestToggle = document.getElementById('request-toggle')
requestToggle.addEventListener('click', () => {
    document.getElementById('req').classList.toggle('requests-closed');
    document.getElementById('request-toggle').classList.toggle('request-toggle-open');
    document.getElementById('req-form').classList.toggle('request-form-open');
    // document.getElementById('drop-menu').classList.toggle('close-menu');
    // document.getElementById('content').classList.toggle('blur');
    // burger.classList.toggle('close-burger');
})

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