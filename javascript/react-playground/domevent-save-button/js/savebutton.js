class SaveButton extends React.Component {
    constructor(props) {
        super(props);
        this.handleSave = this.handleSave.bind(this);
    }
    handleSave(event) {
        console.log(this, event);
    }
    render() {
        return React.createElement(
            "button",
            { onClick: this.handleSave },
            "Save"
        );
    }
}