
class HelloWorld extends React.Component {
    render() {
        console.log(Object.isFrozen(this.props))
        return <div>
            <h1 title={this.props.titls} id={this.props.id}>Hello {this.props.frameworkName} world!</h1>
        </div>
    }
}

class Content extends React.Component {
    getUrl() {
        return 'http://webapplog.com'
    }
    render() {
        return <div>
            <p>Your REST API URL is: <a href={this.getUrl()}>{this.getUrl()}</a></p>
        </div>
    }
}

ReactDOM.render(
    <div>
        <HelloWorld id='ember'
            frameworkName='Ember.js'
            title='A framework for creating ambitious web application.'/>
        <HelloWorld id='backbone'
             frameworkName='Backbone.js'
             title='Backbone.js gives structure to web application.'/>
        <HelloWorld id='angular'
             frameworkName='Angular.js'
             title='Superheroic JavaScript MVW Framework.'/>
        <Content />
    </div>,
    document.getElementById('content')
)

