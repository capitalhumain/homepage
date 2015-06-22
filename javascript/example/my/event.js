/**
 * By Terence Chao ( modified from Nicholas )
 */
(function() {
    var _EventUtil = {
        /**
         * 註冊event handler
         */
        addHandler: function( element, type, handler ) {
            if( element.addEventListener ) {
                element.addEventListener( type, handler, false );
            } else if( element.attachEvent ) {
                element.attachEvent( 'on' + type, handler );
            } else {
                element[ 'on' + type ] = handler;
            }
        },
        /**
         * 停止event capturing and bubbling
         */
        preventDefault: function( event ) {
            if( event.preventDefault ) {
                event.preventDefault();
            } else {
                event.returnValue = false;
            }
        },
        /**
         * 取得event的存放被觸發的那個物件
         */
        target: function( event ) {
            return event.target || event.srcElement;
        },
        /**
         * 取得event物件...
         */
        event: function( event ) {
            return event || window.event;
        }
    }
    // 把這個物件放到global scope的EventUtil property
    window.EventUtil = _EventUtil;
})();
