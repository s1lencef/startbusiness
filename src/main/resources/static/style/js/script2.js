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