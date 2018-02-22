class Logger extends React.Component {
    constructor(props) {
        super(props);
        console.log('constructor');
    }
    componentWillMount() {
        console.log('componentWillMount is triggered');
    }
    componentDidMount() {
        console.log('componentDidMount is triggered');
        console.log('DOM node: ', ReactDOM.findDOMNode(this));
    }
    componentWillReceiveProps(newProps) {
        console.log('componentWillReceiveProps is triggered');
        console.log('new props: ', newProps);
    }
    shouldCompoentUpdate(newProps, newState) {
        console.log('shouldComponentUpdate is triggered');
        console.log('new props: ', newProps);
        console.log('new state: ', newState);
        return true;
    }
    componentWillUpdate(newProps, newState) {
        console.log('componentWillUpdate is triggered');
        console.log('new props: ', newProps);
        console.log('new state: ', newState);
    }
    componentDidUpdate(newProps, oldProps) {
        console.log('componentDidUpdate is triggered');
        console.log('new props: ', newProps);
        console.log('old props: ', oldProps);
    }
    componentWillUnmount() {
        console.log('componentWillUnmount is triggered');
    }
    render() {
        return React.createElement(
            'div',
            null,
            this.props.time
        );
    }
}