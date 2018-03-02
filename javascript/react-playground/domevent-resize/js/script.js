ReactDOM.render(React.createElement(
    "div",
    null,
    React.createElement(Radio, { id: "radio1", name: "radio1", label: "Credit Card", order: "1" }),
    React.createElement(Radio, { id: "radio2", name: "radio2", label: "PayPal", order: "2" }),
    React.createElement(Radio, { id: "radio3", name: "radio3", label: "Check", order: "3" })
), document.getElementById('content'));