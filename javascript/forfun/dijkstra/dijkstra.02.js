/*
 * 
 */
var Route = function(from, to, distance) {
    this.from = from;
    this.to = to;
    this.distance = distance;
};

var Dijkstra = function() {
    this.dist = [];
    this.graph = [];
};

Dijkstra.prototype.addRoute = function(f, t, d) {
    this.graph.push( new Route(f, t, d) );
};


