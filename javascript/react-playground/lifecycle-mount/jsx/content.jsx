class Content extends React.Component {
    constructor(props) {
        super(props)
    }
    componentWillMount() {
        console.log(ReactDOM.findDOMNode(this))
    }
    componentDidMount() {
        console.dir(ReactDOM.findDOMNode(this))
    }
    render() {
        console.log('render')
        return <div />
    }
}
