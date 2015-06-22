/*
 * �ھ�The Art of C++/The Art of Java����AI Based Problem Solving
 * �bJavaScript����@
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

// �d�߬O�_���ŦX�����u
// ���ŦX�����u�h�|�^�ǸӸ��u��distance�F�_�h�|�^��0
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

// btStack�O�Ψө�ѵ���Stack
// arguments.callee�O�ΨӰ�recursive call�ϥ�
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
