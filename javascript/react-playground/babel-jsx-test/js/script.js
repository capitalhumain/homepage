
class HelloWorld extends React.Component {
    render() {
        console.log(Object.isFrozen(this.props));
        return React.createElement(
            'div',
            null,
            React.createElement(
                'h1',
                { title: this.props.titls, id: this.props.id },
                'Hello ',
                this.props.frameworkName,
                ' world!'
            )
        );
    }
}

class Content extends React.Component {
    getUrl() {
        return 'http://webapplog.com';
    }
    render() {
        return React.createElement(
            'div',
            null,
            React.createElement(
                'p',
                null,
                'Your REST API URL is: ',
                React.createElement(
                    'a',
                    { href: this.getUrl() },
                    this.getUrl()
                )
            )
        );
    }
}

ReactDOM.render(React.createElement(
    'div',
    null,
    React.createElement(HelloWorld, { id: 'ember',
        frameworkName: 'Ember.js',
        title: 'A framework for creating ambitious web application.' }),
    React.createElement(HelloWorld, { id: 'backbone',
        frameworkName: 'Backbone.js',
        title: 'Backbone.js gives structure to web application.' }),
    React.createElement(HelloWorld, { id: 'angular',
        frameworkName: 'Angular.js',
        title: 'Superheroic JavaScript MVW Framework.' }),
    React.createElement(Content, null)
), document.getElementById('content'));
