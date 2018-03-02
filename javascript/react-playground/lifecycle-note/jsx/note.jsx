class Note extends React.Component {
    constructor(props) {
        super(props)
    }
    confirmLeave(e) {
        let confirmLeaveMessage = 'Do you realy want to close?'
        e.returnValue = confirmLeaveMessage
        return confirmLeaveMessage
    }
    componentDidMount() {
        console.log('Attaching confirmLeave event listener for beforeunload')
        window.addEventListener('beforeunload', this.confirmLeave)
    }
    componentWillUnmount() {
        console.log('Remove confirmLeave event listener for beforeunload')
        window.removeEventListener('beforeunload', this.confirmLeave)
    }
    render() {
        console.log('Render')
        return <div>
            Here will be our input field for notes (parent will remove in {this.props.secondLeft} seconds)
        </div>
    }
}
