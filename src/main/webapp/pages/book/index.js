var Header = document.querySelector('#Header');
document.body.appendChild(Header.import.querySelector('.header'));

var bookButton = document.querySelector('#book');
bookButton.setAttribute('class', bookButton.getAttribute('class') + ' button--selected-tab');

var Form = document.querySelector('#Form');
document.body.appendChild(Form.import.querySelector('.form'));

window.onload = function () {
  var isbn = getQueryParam('isbn');
  if (isbn) {
    httpRequest('GET', baseUrl + '/' + isbn, function (book) {
      for (var key in tableHeaderMapper) {
        document.querySelector('input[name="' + key + '"]').value = book[tableHeaderMapper[key]];
      }
      var isbnInput = document.querySelector('input[name="ISBN"]');
      isbnInput.disabled = true;
    });
  }

  var form = document.querySelector('.form');
  form.addEventListener('submit', function (e) {
    e.preventDefault();
    var formElements = e.target.elements;
    var book = {};
    for (var i = 0; i < formElements.length - 1; ++i) {
      book[tableHeaderMapper[formElements[i].name]] = formElements[i].value;
    }
    if (isbn) {
      httpRequest('PUT', baseUrl + '/' + isbn, function () {
        location.href = '/index.html';
      }, book);
    } else {
      httpRequest('POST', baseUrl, function () {
        location.href = '/index.html';
      }, book);
    }
  });
};
