/**
 * By Terence Chao ( modified from Nicholas )
 */
(function() {
    var _EventUtil = {
        /**
         * ���Uevent handler
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
         * ����event capturing and bubbling
         */
        preventDefault: function( event ) {
            if( event.preventDefault ) {
                event.preventDefault();
            } else {
                event.returnValue = false;
            }
        },
        /**
         * ���oevent���s��QĲ�o�����Ӫ���
         */
        target: function( event ) {
            return event.target || event.srcElement;
        },
        /**
         * ���oevent����...
         */
        event: function( event ) {
            return event || window.event;
        }
    }
    // ��o�Ӫ�����global scope��EventUtil property
    window.EventUtil = _EventUtil;
})();
