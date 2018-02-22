class Clock extends React.Component {
    constructor(props) {
        super(props)
        this.launchClock()
        this.state = {
            currentTime: (new Date()).toLocaleString()
        }
    }
    launchClock() {
        setInterval(() => {
            console.log('Updating Time...')
            this.setState({
                currentTime: (new Date()).toLocaleString()
            })
        }, 1000)
    }
    render() {
        return <div>
            <DigitalDisplay time={this.state.currentTime} />
            <AnalogDisplay time={this.state.currentTime} />
        </div>
    }
}
