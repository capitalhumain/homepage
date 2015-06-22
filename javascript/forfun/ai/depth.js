/*
 * Description: 深度優先演算法
 * Required: core.js and inheritance AISearch
 */

var Depth = function() {
    this.find = function(f) {
        var i;
        for( i=0; i < this.flights.length; i++) {
            if( this.flights[i].from === f && !this.flights[i].skip ) {
                this.flights[i].skip = true;
                return new FlightInfo( this.flights[i].from, this.flights[i].to, this.flights[i].distance );
            }
        }
    };
};

Depth.prototype = new AISearch();
