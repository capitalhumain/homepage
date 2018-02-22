class Clock extends React.Component {
    constructor(props) {
        super(props);
        this.launchClock();
        this.state = {
            currentTime: new Date().toLocaleString()
        };
    }
    launchClock() {
        setInterval(() => {
            console.log('Updating Time...');
            this.setState({
                currentTime: new Date().toLocaleString()
            });
        }, 1000);
    }
    render() {
        return React.createElement(
            'div',
            null,
            React.createElement(DigitalDisplay, { time: this.state.currentTime }),
            React.createElement(AnalogDisplay, { time: this.state.currentTime })
        );
    }
}