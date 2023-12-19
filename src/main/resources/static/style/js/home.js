for (let i = 1; i <= 5; i++) {
    let menu_btn2 = document.getElementById('business-' + i);
    menu_btn2.addEventListener('click', function() {
        for (let j = 1; j <= 5; j++) {
            if(document.getElementById('business-info-' + j).classList.contains('business-info-open')) {
                document.getElementById('business-info-' + j).classList.toggle('business-info-open');
            }}
        document.getElementById('business-info-' + i).classList.toggle('business-info-open');
    });
}

// Получаем все элементы с классом 'welcome-word'
var welcomeWords = document.querySelectorAll('.welcome-word');

// Для каждого элемента 'welcome-word'
for (var i = 0; i < welcomeWords.length; i++) {
    // Добавляем обработчик события 'mouseover'
    welcomeWords[i].addEventListener('mouseover', function() {
        // Получаем элемент 'p-wrap' внутри текущего 'welcome-word'
        var pWrap = this.querySelector('.p-wrap');
        var aWrap = this.querySelector('a');
        // Добавляем класс 'p-wrap-open' к 'p-wrap'
        pWrap.classList.add('p-wrap-open');
        aWrap.classList.add('a-open');
        // Удаляем класс 'p-wrap-open' со всех других 'p-wrap'
        for (var j = 0; j < welcomeWords.length; j++) {
            if (welcomeWords[j] !== this) {
                welcomeWords[j].querySelector('.p-wrap').classList.remove('p-wrap-open');
                welcomeWords[j].querySelector('a').classList.remove('a-open');
            }
        }
    });
}

window.addEventListener('scroll', function() {
    var element = document.getElementById('arrow');
    if (window.pageYOffset > 200) {
        element.classList.add('invisible');
        for (var j = 0; j < welcomeWords.length; j++) {
            welcomeWords[j].querySelector('.p-wrap').classList.remove('p-wrap-open');
        }
    } else {
        element.classList.remove('invisible');
    }
});


document.getElementById('prices-btn-2').addEventListener('click', function(e) {
    e.preventDefault();
    document.getElementById('payment-plan').scrollIntoView({behavior: 'smooth'});
});
