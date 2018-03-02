let secondLeft = 5

let interval = setInterval(() => {
    if(secondLeft == 0) {
        ReactDOM.render(
            <div>
            Note was removed after {secondLeft} seconds.
            </div>,
            document.getElementById('content')
        )
        clearInterval(interval)
    } else {
        ReactDOM.render(
            <Note secondLeft={secondLeft} />,
            document.getElementById('content')
        )
    }
    secondLeft--
}, 1000)
