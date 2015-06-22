/*
 * Description: hill climbing 
 * Required: core.js and inheritance AISearch
 */
var HillClimbing = function() {
    // 從最遠的開始考慮來規劃路線
    // 有找到的話會回傳該路線資訊的物件
    // 在JavaScript function沒寫return就是表示return undefined
    this.find = function( f ) {
        var dist = 0;
        var pos = -1;
        var i;
        
        with( this ) {
            for( i=0; i<flights.length; i++ ) {
                if( flights[i].from === f && !flights[i].skip ) {
                    if( flights[i].distance > dist ) {
                        pos = i;
                        dist = flights[i].distance;
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

HillClimbing.prototype = new AISearch();


