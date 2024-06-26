document.getElementById('prices-btn-2').addEventListener('click', paymentPlans);

let requestToggle = document.getElementById('request-toggle')
requestToggle.addEventListener('click', () => {
    document.getElementById('req').classList.toggle('requests-closed');
    document.getElementById('request-toggle').classList.toggle('request-toggle-open');
    document.getElementById('req-form').classList.toggle('request-form-open');
});

let elements = document.querySelectorAll('.request-a');

function paymentPlans() {
    document.getElementById('payment-page').classList.toggle('payment-plan-requests');
    if (!document.getElementById('drop-menu').classList.contains('close-menu')) {
        document.getElementById('drop-menu').classList.toggle('close-menu');
        document.getElementById('content').classList.toggle('blur');
        burger.classList.toggle('close-burger');
    }
}

elements.forEach(function (element) {
    element.addEventListener('click', paymentPlans);
});
document.getElementById('close-payment-plan').addEventListener('click', paymentPlans);

let divToScroll = document.getElementById('scrollDiv');

divToScroll.addEventListener('wheel', function (e) {
    e.preventDefault();
    divToScroll.scrollLeft += e.deltaY * 2;
}, false);

const foreignCitizenToggle = document.querySelector('#foreignCitizen');
const radioButtons = document.querySelectorAll('input[name="citizenship"]');
for (const radioButton of radioButtons) {
    radioButton.addEventListener("click", () => {
        if (foreignCitizenToggle.checked) {
            document.getElementById('foreignCitizenForm').classList.remove('request-form-page-hidden');
        } else {
            document.getElementById('foreignCitizenForm').classList.add('request-form-page-hidden');
        }
    });
}

let endDateToggle = document.getElementById('infiniteResidentCard');
endDateToggle.addEventListener('click', () => {
    if (endDateToggle.checked) {
        document.getElementById('endDate').classList.add('request-form-row-hidden');
        document.getElementById('infiniteResidentCard-span').classList.add('request-radio-btn-checked');
        console.log(document.getElementById('infiniteResidentCard-span').classList);
    } else {
        document.getElementById('endDate').classList.remove('request-form-row-hidden');
        document.getElementById('infiniteResidentCard-span').classList.remove('request-radio-btn-checked');
    }
});

const dateInput = document.getElementById('dateInput');
const INNInput = document.getElementById('INNInput');
const emailInput = document.getElementById('emailInput');
const telInput = document.getElementById('telInput');
const passportInput = document.getElementById('passportInput');
const passDateInput = document.getElementById('passDateInput');
const codeInput = document.getElementById('codeInput');
const docDateInput = document.getElementById('docDateInput');
const residentCardDateInput = document.getElementById('residentCardDateInput');
const residentCardEndDateInput = document.getElementById('residentCardEndDateInput');

const mask1 = IMask(dateInput, {
    mask: '00/00/0000',
    lazy: false,
    placeholderChar: '_'
});
const mask2 = IMask(INNInput, {
    mask: '0000000000',
    lazy: false,
    placeholderChar: '_'
});
const mask3 = IMask(emailInput, {
    mask: /^\S*@?\S*$/,
    lazy: false,
    placeholderChar: '_'
});
const mask4 = IMask(telInput, {
    mask: '+7(000)000-00-00',
    startsWith: '7',
    lazy: false,
    country: 'Russia'
});
const mask5 = IMask(passportInput, {
    mask: '0000000000',
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

const mask9 = IMask(residentCardDateInput, {
    mask: '00/00/0000',
    lazy: false,
    placeholderChar: '_'
});

const mask10 = IMask(residentCardEndDateInput, {
    mask: '00/00/0000',
    lazy: false,
    placeholderChar: '_'
});

// проверка на формат даты рождения
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

// проверка на формат ИНН
let Input2 = document.getElementById('INNInput');
Input2.addEventListener('input', function () {
    let Input = document.getElementById('INNInput').value;
    let regex = /^\d{10}$|^\d{12}$/;
    if (regex.test(Input)) {
        document.getElementById('INN-result').textContent = 'ИНН введен правильно!';
    } else {
        document.getElementById('INN-result').textContent = 'Неверный формат ИНН!';
    }
});

// проверка на формат email
let Input3 = document.getElementById('emailInput');
Input3.addEventListener('input', function () {
    let Input = document.getElementById('emailInput').value;
    let regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (regex.test(Input)) {
        document.getElementById('email-result').textContent = 'Почта введена правильно!';
    } else {
        document.getElementById('email-result').textContent = 'Неверный формат почты!';
    }
});

// проверка на формат паспортных данных
let Input5 = document.getElementById('passportInput');
Input5.addEventListener('input', function () {
    let Input = document.getElementById('passportInput').value;
    // Input = Input.slice(0, 10);
    let regex = /^\d{10}$/;
    if (regex.test(Input)) {
        document.getElementById('passport-result').textContent = 'Паспортные данные введены правильно!';
        console.log(Input);
    } else {
        document.getElementById('passport-result').textContent = 'Неверный формат серии и номера паспорта!';
    }
});

// проверка на формат даты выдачи
let Input6 = document.getElementById('passDateInput');
Input6.addEventListener('input', function () {
    let Input = document.getElementById('passDateInput').value;
    Input = Input.slice(0, 10);
    let regex = /^(((0[1-9]|[1]\d|3[0])\/(0[-1]|1[2])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[1]\d|30)\/(0[-1]|1[1])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[-1]|[-1]|[-1])|(([-1]|[-1]|[-1])00))))$/;
    if (regex.test(Input)) {
        document.getElementById('passDate-result').textContent = 'Дата введена правильно!';
    } else {
        document.getElementById('passDate-result').textContent = 'Неверный формат даты!';
    }
});

// проверка на формат кода подразделения
let Input7 = document.getElementById('codeInput');
Input7.addEventListener('input', function () {
    let Input = document.getElementById('codeInput').value;
    // Input = Input.slice(0, 10);
    let regex = /^\d{3}-\d{3}$/;
    if (regex.test(Input)) {
        document.getElementById('code-result').textContent = 'Код подразделения введен правильно!';
    } else {
        document.getElementById('code-result').textContent = 'Неверный формат кода подразделения!';
    }
});

// проверка на формат даты документов
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

// проверка на формат даты документов
let Input9 = document.getElementById('residentCardDateInput');
Input9.addEventListener('input', function () {
    let Input = document.getElementById('residentCardDateInput').value;
    Input = Input.slice(0, 10);
    let regex = /^(((0[1-9]|[1]\d|3[0])\/(0[-1]|1[2])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[1]\d|30)\/(0[-1]|1[1])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[-1]|[-1]|[-1])|(([-1]|[-1]|[-1])00))))$/;
    if (regex.test(Input)) {
        document.getElementById('residentCardDate-result').textContent = 'Дата введена правильно!';
    } else {
        document.getElementById('residentCardDate-result').textContent = 'Неверный формат даты!';
    }
});

// проверка на формат даты документов
let Input10 = document.getElementById('residentCardEndDateInput');
Input10.addEventListener('input', function () {
    let Input = document.getElementById('residentCardEndDateInput').value;
    Input = Input.slice(0, 10);
    let regex = /^(((0[1-9]|[1]\d|3[0])\/(0[-1]|1[2])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[1]\d|30)\/(0[-1]|1[1])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[-1]|[-1]|[-1])|(([-1]|[-1]|[-1])00))))$/;
    if (regex.test(Input)) {
        document.getElementById('residentCardEndDate-result').textContent = 'Дата введена правильно!';
    } else {
        document.getElementById('residentCardEndDate-result').textContent = 'Неверный формат даты!';
    }
});

function checkFormFields(inputIds) {
    return !inputIds.some(function (inputId) {
        let inputElement = document.getElementById(inputId);
        return (inputElement.validity.patternMismatch || inputElement.value=="");
    });
}

function changeElementClassName(inputIds, formId) {
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


inputIds1.forEach(function(inputId) {
    // document.getElementById('form1-submit').addEventListener('click', function() {
    document.getElementById(inputId).addEventListener('input', function() {
        changeElementClassName(inputIds1,'request-form-header-menu-btn-1')
    });
});

inputIds2.forEach(function(inputId) {
    document.getElementById(inputId).addEventListener('input', function() {
        changeElementClassName(inputIds2,'request-form-header-menu-btn-2')
    });
});

inputIds3.forEach(function(inputId) {
    document.getElementById(inputId).addEventListener('input', function() {
        changeElementClassName(inputIds3,'request-form-header-menu-btn-3')
    });
});

inputIds4.forEach(function(inputId) {
    document.getElementById(inputId).addEventListener('input', function() {
        changeElementClassName(inputIds4,'request-form-header-menu-btn-4')
    });
});

inputIds5.forEach(function(inputId) {
    document.getElementById(inputId).addEventListener('input', function() {
        changeElementClassName(inputIds5,'request-form-header-menu-btn-5')
    });
});

inputIds6.forEach(function(inputId) {
    document.getElementById(inputId).addEventListener('input', function() {
        changeElementClassName(inputIds6,'request-form-header-menu-btn-6')
    });
});

// Get all the divs
let divs = document.getElementsByClassName('request-form-page');

// Add event listeners to the 'next' and 'previous' links
for (let i = 0; i < divs.length; i++) {
    let nextLink = divs[i].querySelector('.next');
    let prevLink = divs[i].querySelector('.previous');

    nextLink.addEventListener('click', function(e) {
        e.preventDefault();
        let nextDiv = divs[i + 1];
        if (nextDiv) {
            nextDiv.scrollIntoView({behavior: 'smooth'});
        }
    });
    prevLink.addEventListener('click', function(e) {
        e.preventDefault();
        let prevDiv = divs[i - 1];
        if (prevDiv) {
            prevDiv.scrollIntoView({behavior: 'smooth'});
        }
    });
}




