class Counter extends React.Component {
    constructor(props) {
        super(props)
    }
    render() {
        return <span>Clicked {this.props.value} times.</span>
    }
}
