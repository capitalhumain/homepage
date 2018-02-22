let helloWorldReactElement = <h1>Hello world!</h1>

class HelloWorld extends React.Component {
    render() {
        console.log(Object.isFrozen(this.props))
        return <div>
            {helloWorldReactElement}
            {helloWorldReactElement}
        </div>
    }
}

ReactDOM.render(
    <HelloWorld/>,
    document.getElementById('content')
)
