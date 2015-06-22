/**
 * enhancement.js
 * 
 * Collect & Create by Terence Chao
 */

// String Enhancement
String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$|\n$|^\n/g, '');
};

String.prototype.escapeMarkup = function() {
    var character = {
        '<': '&lt;',
        '>': '&gt;',
        '&': '&amp;',
        '"': '&quot;'
    };
    return this.replace(/[<>&"]/g, function (c) {
        return character[c];
    });
};

String.prototype.isPartOf = function(str) {
    var re = new RegExp( '(^|\\s)' + this + '(^|\\s)' );
    return re.test( str );
};

// Array Enhancement
Array.prototype.foreach = function(fn) {
    for( var i=0; i<this.length; i++) {
        fn.call( this[i] );
    } // for
};

// Function Enhancement
Function.prototype.method = function(name, fn) {
    this.prototype[name] = fn;
};

// Interface class
var Interface = function(name, methods) {
    if(arguments.length != 2) {
        throw new Error( 'Interfac constructor called with ' + arguments.length + 
        ' arguments, but expected exactly 2' );
    } //if
    
    this.name = name;
    this.methods = [];
    
    for( var i=0, len=methods.length; i<len; i++) {
        if( typeof methods[i] !== 'string' ) {
            throw new Error( 'Interface constructor expects method names to be ' +
            'passed in as a string.' );
        } // if
        this.methods.push( methods[i] );
    } // for
};

// Static class method to ensure implements interface methods
Interface.ensureImplements = function(object) {
    if(arguments.length < 2) {
        throw new Error( 'Function Interface.ensureImplements called with ' + arguments.length +
        ' arguments, but expected at least 2.' );
    } // if
    
    for( var i=1, len=arguments.length; i<len; i++) {
        var interface = arguments[i];
        if(interface.constructor !== Interface) {
            throw new Error( 'Function Interface.ensureImplements expects arguments ' +
            'two and above to be instances of Interface' );
        } // if
        
        for( var j=0, methodsLen=interface.methods.length; j<methodsLen; j++) {
            var method = interface.methods[j];
            if( !object[methods] || typeof object[method] !== 'function' ) {
                throw new Error( 'Function Interface.ensureImplements: object ' +
                'does not implement the ' + interface.name +
                ' interface. Method ' + method + ' was not found.' );
            } // if
        } // for
    } // for
};

// Form Validation (from John Resig)
// CSS Class based JavaScript Form Validation
var errMsg = {
    // check for required field
    required: {
        msg: 'This field is required.',
        test: function( obj ) {
            return obj.value.length > 0;
        }
    },
    year: {
        msg: 'Year format error.',
        test: function( obj ) {
            return !obj.value || /^\d{4}$/.test( obj.value );
        }
    },
    creditcardtype: {
        msg: 'credit card type not valid.',
        test: function( obj ) {
            return !obj.value || obj.value === 'AE' || obj.value === 'VISA' || obj.value === 'MASTER';
        }
    },
    equmaxlen: {
        msg: 'length not valid.',
        test: function( obj ) {
            return !obj.value || obj.value.length.toString() === obj.getAttribute('maxlength');
        }
    },
    sameasref: {
        msg: 'two element are not equal.',
        test: function( obj ) {
            if( obj.getAttribute('ref') === null || document.getElementById( obj.getAttribute('ref') ) === null ) {
                return false;
            } else {
                return !obj.value || obj.value === document.getElementById( obj.getAttribute('ref') ).value;
            }
        }
    }
};

function validateForm( form ) {
    var valid = true;
    
    for( var i=0; i<form.elements.length; i++ ) {
        if( ! validateField( form.elements[i] ) ) {
            valid = false;
        } // if
    } // for
    
    return valid;
};

function validateField( elem ) {
    var errors = [];
    
    for( var name in errMsg ) {
        var re = new RegExp( '(^|\\s)' + name + '(\\s|$)' );
        if( re.test( elem.className ) && !errMsg[name].test( elem ) ) {
            errors.push( errMsg[name].msg );
            // alert( elem.getAttribute( 'alt' ) + ': ' + errMsg[name].msg );
            elem.setAttribute('style', 'border: 1px solid #cc0000;');
        } // if
    } // for
    
    if(errors.length>0) {
        errors.foreach( function() {
            alert(this);
        });
    }
    
    return !(errors.length > 0);
};

function setFormInputStyle( form, style, escapetype ) {
    for( var i=0; i<form.elements.length; i++ ) {
        if( form.elements[i].getAttribute('type') !== null && 
            !form.elements[i].getAttribute('type').isPartOf(escapetype) ) {
            form.elements[i].setAttribute('style', style);
        }
    } // for
};
