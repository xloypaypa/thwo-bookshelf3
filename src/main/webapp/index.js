var Header = document.querySelector('#Header');
document.body.appendChild(Header.import.querySelector('.header'));

var homeButton = document.querySelector('#home');
homeButton.setAttribute('class', homeButton.getAttribute('class') + ' button--selected-tab');

window.onload = function () {
    var createRow = function (book) {
        var createButton = function (text, cb) {
            var button = document.createElement('button');
            button.textContent = text;
            button.addEventListener('click', cb);
            return button;
        };

        var tr = document.createElement('tr');
        if (!book) {
            tr.textContent = 'sorry, there is no record yet.';
            tr.setAttribute('class', 'table__row--no-book');
        } else {
            for (var key in tableHeaderMapper) {
                var td = document.createElement('td');
                td.textContent = book[tableHeaderMapper[key]];
                td.setAttribute('class', 'table__row--has-book');
                tr.appendChild(td);
            }

            var td = document.createElement('td');
            td.appendChild(createButton('edit', function () {
                location.href = '/pages/book/index.html?isbn=' + book[tableHeaderMapper.ISBN];
            }));
            td.appendChild(createButton('delete', function () {
                httpRequest('DELETE', baseUrl + '/' + book[tableHeaderMapper.ISBN], function () {
                    location.href = '/index.html';
                })
            }));
            td.setAttribute('class', 'table__row--has-book');

            tr.appendChild(td);
        }
        return tr;
    };

    var createTableHeader = function () {
        var tr = document.createElement('tr');
        for (var key in tableHeaderMapper) {
            var th = document.createElement('th');
            th.textContent = key;
            th.setAttribute('class', 'table__row--header');
            tr.appendChild(th);
        }

        var th = document.createElement('th');
        th.textContent = 'Operates';
        th.setAttribute('class', 'table__row--header');
        tr.appendChild(th);
        return tr;
    };

    httpRequest('GET', baseUrl, function (books) {
        var booksList = document.querySelector('#book-list');
        if (!books) {
            booksList.appendChild(createRow());
        } else {
            booksList.appendChild(createTableHeader());
            books.forEach(function (book) {
                booksList.appendChild(createRow(book));
            })
        }
    });
};
