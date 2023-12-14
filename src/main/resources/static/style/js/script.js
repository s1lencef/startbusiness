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

function show_hide_password(target) {
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
});

let elements = document.querySelectorAll('.request-a');

// Определите функцию, которую вы хотите назначить
function paymentPlans() {
    // if(!document.getElementById('payment-page').classList.contains('payment-plan-requests')){
    document.getElementById('payment-page').classList.toggle('payment-plan-requests');
    // }
}

// Примените функцию ко всем выбранным элементам
elements.forEach(function (element) {
    element.addEventListener('click', paymentPlans);
});
document.getElementById('close-payment-plan').addEventListener('click', paymentPlans);


let divToScroll = document.getElementById('scrollDiv');

divToScroll.addEventListener('wheel', function (e) {
    e.preventDefault();
    divToScroll.scrollLeft += e.deltaY * 2;
}, false);

// библиотека для шаблона даты
const dateInput = document.getElementById('dateInput');
const INNInput = document.getElementById('INNInput');
const emailInput = document.getElementById('emailInput');
const telInput = document.getElementById('telInput');
const passportInput = document.getElementById('passportInput');
const passDateInput = document.getElementById('passDateInput');
const codeInput = document.getElementById('codeInput');
const docDateInput = document.getElementById('docDateInput');

const mask1 = IMask(dateInput, {
    mask: '00/00/0000',
    lazy: false,
    placeholderChar: '_'
});
// const mask2 = IMask(INNInput, {
//     mask: '0000000000',
//     lazy: true,
//     placeholderChar: '_'
// });
const mask3 = IMask(emailInput, {
    mask: /^\S*@?\S*$/,
    lazy: false,
    placeholderChar: '_'
});
const mask4 = IMask(telInput, {
    mask: '+0 000 000-00-00',
    startsWith: '7',
    lazy: false,
    country: 'Russia'
});
const mask5 = IMask(passportInput, {
    mask: '0000-000000',
    lazy: false,
    placeholderChar: '_'
});
const mask6 = IMask(passDateInput, {
    mask: '00/00/0000',
    lazy: false,
    placeholderChar: '_'
});
const mask7 = IMask(codeInput, {
    mask: '000-000',
    lazy: false,
    placeholderChar: '_'
});
const mask8 = IMask(docDateInput, {
    mask: '00/00/0000',
    lazy: false,
    placeholderChar: '_'
});

// проверка на формат даты
let Input1 = document.getElementById('dateInput');
Input1.addEventListener('input', function () {
    let Input = document.getElementById('dateInput').value;
    Input = Input.slice(0, 10);
    let regex = /^(((0[1-9]|[1]\d|3[0])\/(0[-1]|1[2])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[1]\d|30)\/(0[-1]|1[1])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[-1]|[-1]|[-1])|(([-1]|[-1]|[-1])00))))$/;
    if (regex.test(Input)) {
        document.getElementById('date-result').textContent = 'Дата введена правильно!';
    } else {
        document.getElementById('date-result').textContent = 'Неверный формат даты!';
    }
});

// проверка на формат даты
let Input8 = document.getElementById('docDateInput');
Input8.addEventListener('input', function () {
    let Input = document.getElementById('docDateInput').value;
    Input = Input.slice(0, 10);
    let regex = /^(((0[1-9]|[1]\d|3[0])\/(0[-1]|1[2])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[1]\d|30)\/(0[-1]|1[1])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[-1]|[-1]|[-1])|(([-1]|[-1]|[-1])00))))$/;
    if (regex.test(Input)) {
        document.getElementById('docDateInput-result').textContent = 'Дата введена правильно!';
    } else {
        document.getElementById('docDateInput-result').textContent = 'Неверный формат даты!';
    }
});

let Input2 = document.getElementById('INNInput');
Input2.addEventListener('input', function () {
    let Input = document.getElementById('INNInput').value;
    Input = Input.slice(0, 10);
    let regex = /^\d{10}$|^\d{12}$/;
    if (regex.test(Input)) {
        document.getElementById('INN-result').textContent = 'ИНН введен правильно!';
    } else {
        document.getElementById('INN-result').textContent = 'Неверный формат ИНН!';
    }
});

function checkFormFields(inputIds) {
    return !inputIds.some(function (inputId) {
        let inputElement = document.getElementById(inputId);
        return inputElement.value === '';
        console.log(inputId);
    });
}

function changeElementClass(inputIds, formId) {
    var element = document.getElementById(formId);
    console.log(checkFormFields(inputIds));
    if (checkFormFields(inputIds)) {

        element.classList.add('request-form-header-menu-btn-ready');
        console.log(element.classList);
    } else {
        element.classList.remove('request-form-header-menu-btn-ready');
        console.log("asd");
    }
}

// // Массив с ID полей ввода
const inputIds1 = ['name', 'middleName', 'surname', 'dateInput', 'birthInput', 'INNInput'];
const inputIds2 = ['emailInput', 'telInput'];
const inputIds3 = ['passportInput', 'passDateInput', 'departInput', 'codeInput'];
const inputIds4 = ['cityInput', 'streetInput', 'houseInput', 'officeInput'];
const inputIds5 = ['okvedInput', 'dopokvedInput'];
const inputIds6 = ['docDateInput'];

// Вызовите функцию changeElementClass при изменении любого поля формы
inputIds1.forEach(function(inputId) {
    // document.getElementById('form1-submit').addEventListener('click', function() {
    document.getElementById(inputId).addEventListener('input', function() {
        changeElementClass(inputIds1,'request-form-header-menu-btn-1')
    });
});

inputIds2.forEach(function(inputId) {
    // document.getElementById('form1-submit').addEventListener('click', function() {
    document.getElementById(inputId).addEventListener('input', function() {
        changeElementClass(inputIds2,'request-form-header-menu-btn-2')
    });
});

inputIds3.forEach(function(inputId) {
    // document.getElementById('form1-submit').addEventListener('click', function() {
    document.getElementById(inputId).addEventListener('input', function() {
        changeElementClass(inputIds3,'request-form-header-menu-btn-3')
    });
});

inputIds4.forEach(function(inputId) {
    // document.getElementById('form1-submit').addEventListener('click', function() {
    document.getElementById(inputId).addEventListener('input', function() {
        changeElementClass(inputIds4,'request-form-header-menu-btn-4')
    });
});

inputIds5.forEach(function(inputId) {
    // document.getElementById('form1-submit').addEventListener('click', function() {
    document.getElementById(inputId).addEventListener('input', function() {
        changeElementClass(inputIds5,'request-form-header-menu-btn-5')
    });
});

inputIds6.forEach(function(inputId) {
    // document.getElementById('form1-submit').addEventListener('click', function() {
    document.getElementById(inputId).addEventListener('input', function() {
        changeElementClass(inputIds6,'request-form-header-menu-btn-6')
    });
});