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

// let animatemenu = document.getElementById('bis-wrap');
// animatemenu.addEventListener('scroll', function() {
//     if(!document.getElementById('log-wrapper').classList.contains('wrapper-hover')){
//         document.getElementById('log-wrapper').classList.add('wrapper-hover');
//     }
// // });
//
// function addScrollListenerToElement(elementId) {
//     var element = document.getElementById(elementId);
//     var parent = element.parentNode;
//
//     window.addEventListener('scroll', function() {
//         var rect = element.getBoundingClientRect();
//         var top = rect.top;
//         console.log(top);
//         var bottom = top + parent.offsetHeight;
//         console.log(bottom);
//         if (window.scrollTop > top && window.scrollTop < bottom) {
//             console.log('Элемент находится внутри родительского контейнера');
//         } else {
//             console.log('Элемент выходит за пределы родительского контейнера');
//         }
//     });
// }
//
// addScrollListenerToElement('bis-menu');