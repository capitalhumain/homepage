/*
 * Description: Least Cost
 * Required: core.js
 */
var LeastCost = function() {
    this.find = function( f ) {
        var pos = -1;
        var dist = 10000; // ���ݤj��̪������|
        var idx = 0;
        
        with( this ) {
            // �q�̵u���}�l�w�Ƹ��|
            for( idx=0; idx<flights.length; idx++) {
                if( flights[idx].from === f && !flights[idx].skip ) {
                    if( flights[idx].distance < dist) {
                        pos = idx;
                        dist = flights[idx].distance;
                    }
                }
            }
            
            if( pos !== -1 ) {
                flights[pos].skip = true;
                return new FlightInfo( flights[pos].from,
                                       flights[pos].to,
                                       flights[pos].distance );
            }
        }
    };
};

LeastCost.prototype = new AISearch();
