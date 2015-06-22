( function() {
    // private utilities
    // -- Cross Browser Evnet Utility
    var _XEvent = {
        getEvent : function( evt ) {
            return evt? evt : window.event;
        }
    };
    
    // Constructor
    function _$(els) {
        var element;
        this.elements = [];
        
        for(var i=0, len=els.length; i<len; i+=1 ) {
            element = els[i];
            if(typeof element === 'string') {
                element = document.getElementById(element);
            } // if
            this.elements.push(element);
        } // for
    };
    
    // Prototype facility
    _$.prototype = {
        each: function( fn ) {
            for(var i=0, len=this.elements.length; i<len; i+=1) {
                fn.call( this, this.elements[i] );
            } // for
            return this;
        },
        setStyle: function( styleName, styleValue ) {
            this.each( function ( elem ) {
                elem.style[ styleName ] = styleValue;
            });
            return this;
        },
        show : function() {
            this.each( function ( elem ) {
                elem.style[ 'display' ] = 'block';
            });
            return this;
        },
        addEvent: function( type, fn ) {
            var callback = function( evt ) {
                var e = _XEvent.getEvent( evt );
                fn( e );
            };
            var add = function( elem ) {
                if( window.addEventListener ) {
                    elem.addEventListener( type, callback, false );
                } // if
                else if( window.attachEvent ) {
                    elem.attachEvent( 'on' + type, callback );
                } // else if
            };
            
            this.each( function( elem ) {
                add(elem);
            } );
            return this;
        }
    };
    
    // public interface
    window.$JTool = function() {
        return new _$(arguments);
    };
    window.$ = window.$JTool;
} )();