class Mouse extends React.Component {
    constructor(props) {
        super(props)
    }
    render() {
        return <div>
            <div style={{border: '1px sold red'}}
                onMouseOverCapture={((event) => {
                    console.log('mouse over capture event')
                    console.dir(this)
                }).bind(this)}
                onMouseOver={((event) => {
                    console.log('mouse over event')
                    console.dir(this)
                }).bind(this)}>
            Open DevTools and move your mouse cursor over here
            </div>
        </div>
    }
}
