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

var fileInput1 = document.getElementById('residentFile1');
fileInput1.addEventListener('change', function () {
    // var elementsToRemove = document.getElementById('residentFiles1').querySelectorAll('.remove-class');
    // elementsToRemove.forEach(function(element) {
    //     element.remove();
    // });
    if (fileInput1.files.length > 0) {
        let value = fileInput1.files[0].name;
        if (value.length > 20) {
            value = value.slice(0, 9) + '...' + value.slice(-7);
        }
        document.getElementById("file-name-1").value = value;
    } else {
        document.getElementById("file-name-1").textContent = "";
    }
    var files = this.files;
    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        var resultBlock = document.createElement('p');
        resultBlock.className = 'input-result';
        resultBlock.classList.add("remove-class");
        var fileResult = document.createElement('span');
        fileResult.className = 'file-result';
        var fileNameSpan = document.createElement('span');
        let fileName = file.name;
        if (fileName.length > 28) {
            fileName = fileName.slice(0, 18) + '...' + fileName.slice(-7);
        }
        fileNameSpan.textContent = fileName;
        var deleteLink = document.createElement('a');
        deleteLink.className = 'delete-file-result';
        deleteLink.addEventListener('click', function () {
            let index;
            this.parentNode.parentNode.remove();
            const dt = new DataTransfer();
            const input = document.getElementById('residentFile1');
            const {files} = input;
            for (let j = 0; j < files.length; j++) {
                const file = files[j];
                if (this.previousSibling.textContent == file.name){
                    index = j;
                    console.log(index);
                }
            }
            for (let j = 0; j < files.length; j++) {
                const file = files[j];
                console.log(index);
                if (j !== index)
                    dt.items.add(file);
            }
            console.log(fileInput1.files);
            input.files = dt.files;
            console.log(fileInput1.files);
            if (fileInput1.files.length > 0) {
                let value = fileInput1.files[0].name;
                if (value.length > 22) {
                    var start = value.slice(0, 12);
                    var end = value.slice(-7);
                    value = start + '...' + end;
                }
                document.getElementById("file-name-1").value = fileInput1.files[0].name;
            } else {
                document.getElementById("file-name-1").textContent = "";
            }
        });
        var deleteText = document.createElement('span');
        deleteText.textContent = 'Удалить файл';
        var svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
        svg.setAttribute('fill', 'none');
        svg.setAttribute('height', '11');
        svg.setAttribute('viewBox', '0 0 11 11');
        svg.setAttribute('width', '11');
        var path = document.createElementNS("http://www.w3.org/2000/svg", "path");
        path.setAttribute('d', 'M1 10L10 1M1 1L10 10');
        path.setAttribute('stroke', '#D34242');
        path.setAttribute('stroke-linecap', 'round');
        path.setAttribute('stroke-width', '1.6875');
        svg.appendChild(path);
        deleteLink.appendChild(deleteText);
        deleteLink.appendChild(svg);
        fileResult.appendChild(fileNameSpan);
        fileResult.appendChild(deleteLink);
        resultBlock.appendChild(fileResult);
        document.getElementById('residentFile1-result').parentNode.insertBefore(resultBlock, document.getElementById('residentFile1-result'));
    }
});

var fileInput2 = document.getElementById('residentFile2');
fileInput2.addEventListener('change', function () {
    var elementsToRemove = residentFiles2.querySelectorAll('.remove-class');
    elementsToRemove.forEach(function(element) {
        element.remove();
    });
    if (fileInput2.files.length > 0) {
        let value = fileInput2.files[0].name;
        if (value.length > 20) {
            value = value.slice(0, 9) + '...' + value.slice(-7);
        }
        document.getElementById("file-name-2").value = value;
    } else {
        document.getElementById("file-name-2").value = "";
    }
    var files = this.files;
    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        var resultBlock = document.createElement('p');
        resultBlock.className = 'input-result';
        resultBlock.classList.add("remove-class");
        var fileResult = document.createElement('span');
        fileResult.className = 'file-result';
        var fileNameSpan = document.createElement('span');
        let fileName = file.name;
        if (fileName.length > 28) {
            fileName = fileName.slice(0, 18) + '...' + fileName.slice(-7);
        }
        fileNameSpan.textContent = fileName;
        var deleteLink = document.createElement('a');
        deleteLink.className = 'delete-file-result';
        deleteLink.addEventListener('click', function () {
            let index;
            this.parentNode.parentNode.remove();
            const dt = new DataTransfer();
            const input = document.getElementById('residentFile2');
            const {files} = input;
            for (let j = 0; j < files.length; j++) {
                const file = files[j];
                if (this.previousSibling.textContent == file.name){
                    index = j;
                    console.log(index);
                }
            }
            for (let j = 0; j < files.length; j++) {
                const file = files[j];
                console.log(index);
                if (j !== index)
                    dt.items.add(file);
            }
            console.log(fileInput2.files);
            input.files = dt.files;
            console.log(fileInput2.files);
            if (fileInput2.files.length > 0) {
                let value = fileInput2.files[0].name;
                if (value.length > 22) {
                    var start = value.slice(0, 12);
                    var end = value.slice(-7);
                    value = start + '...' + end;
                }
                document.getElementById("file-name-2").value = fileInput2.files[0].name;
            } else {
                document.getElementById("file-name-2").value = "";
            }
        });
        var deleteText = document.createElement('span');
        deleteText.textContent = 'Удалить файл';
        var svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
        svg.setAttribute('fill', 'none');
        svg.setAttribute('height', '11');
        svg.setAttribute('viewBox', '0 0 11 11');
        svg.setAttribute('width', '11');
        var path = document.createElementNS("http://www.w3.org/2000/svg", "path");
        path.setAttribute('d', 'M1 10L10 1M1 1L10 10');
        path.setAttribute('stroke', '#D34242');
        path.setAttribute('stroke-linecap', 'round');
        path.setAttribute('stroke-width', '1.6875');
        svg.appendChild(path);
        deleteLink.appendChild(deleteText);
        deleteLink.appendChild(svg);
        fileResult.appendChild(fileNameSpan);
        fileResult.appendChild(deleteLink);
        resultBlock.appendChild(fileResult);
        document.getElementById('residentFile2-result').parentNode.insertBefore(resultBlock, document.getElementById('residentFile2-result'));
    }
});