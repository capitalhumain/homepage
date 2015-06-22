/*
 * 根據The Art of C++/The Art of Java有關AI Based Problem Solving
 * 在JavaScript的實作
 * version: 0.1
 * 
 */
var FlightInfo = function(f, t, d) {
    this.from = f;
    this.to = t;
    this.distance = d;
    this.skip = false;
};

var AISearch = function() {
    // for store flight information
    this.flights = [];
    // backtrack stack
    this.btStack = [];
};

AISearch.prototype.addFlightInfo = function(f, t, d) {
    this.flights.push( new FlightInfo(f, t, d) );
};

// 查詢是否有符合的路線
// 有符合的路線則會回傳該路線的distance；否則會回傳0
AISearch.prototype.matchFlight = function(f, t) {
    var i;
    for( i=0; i < this.flights.length; i++ ) {
        if( this.flights[i].from === f && this.flights[i].to === t && !this.flights[i].skip ) {
            this.flights[i].skip = true;
            return this.flights[i].distance;
        }
    }
    return 0;
};

AISearch.prototype.reset = function() {
    var i;
    for( i=0; i<this.flights.length; i++ ) {
        this.flights[i].skip = false;
    }
    this.btStack = [];
};

AISearch.prototype.resetSolution = function() {
    this.btStack = [];
};

// btStack是用來放解答的Stack
// arguments.callee是用來做recursive call使用
var solve = function(obj, f, t) {
    var dist;
    var tmp;
        
    dist = obj.matchFlight(f, t);
    if( dist !== 0 ) {
        obj.btStack.push( new FlightInfo( f, t, dist ) );
        return;
    }
        
    tmp = obj.find( f );
    if( tmp !== undefined ) {
        obj.btStack.push( new FlightInfo( tmp.from, tmp.to, tmp.distance ) );
        arguments.callee( obj, tmp.to, t );
    } else if( obj.btStack.length > 0 ) {
        tmp = obj.btStack.pop();
        arguments.callee( obj, tmp.from, t );
    }
};
