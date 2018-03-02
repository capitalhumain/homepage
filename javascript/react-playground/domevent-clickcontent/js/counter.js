class Counter extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return React.createElement(
            "span",
            null,
            "Clicked ",
            this.props.value,
            " times."
        );
    }
}