var assert = function() {
    if( arguments.length === 0 )
        return;
    if( arguments.length === 2 ) {
        if( !arguments[ 0 ] ) {
            var msgElem = document.createElement( 'div' );
            msgElem.setAttribute( 'style', 'color:red;' );
            msgElem.innerHTML = arguments[ 1 ];
            document.body.appendChild( msgElem );
        }
    }
};
