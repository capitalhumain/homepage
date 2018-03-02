class Mouse extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return React.createElement(
            'div',
            null,
            React.createElement(
                'div',
                { style: { border: '1px sold red' },
                    onMouseOverCapture: (event => {
                        console.log('mouse over capture event');
                        console.dir(this);
                    }).bind(this),
                    onMouseOver: (event => {
                        console.log('mouse over event');
                        console.dir(this);
                    }).bind(this) },
                'Open DevTools and move your mouse cursor over here'
            )
        );
    }
}