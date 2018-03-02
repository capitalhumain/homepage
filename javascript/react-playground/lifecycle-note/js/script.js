let secondLeft = 5;

let interval = setInterval(() => {
    if (secondLeft == 0) {
        ReactDOM.render(React.createElement(
            'div',
            null,
            'Note was removed after ',
            secondLeft,
            ' seconds.'
        ), document.getElementById('content'));
        clearInterval(interval);
    } else {
        ReactDOM.render(React.createElement(Note, { secondLeft: secondLeft }), document.getElementById('content'));
    }
    secondLeft--;
}, 1000);