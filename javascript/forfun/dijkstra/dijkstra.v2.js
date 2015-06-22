/*
 * v2
 * �������@�˪�
 * �]�]���O�o�ˤl�~��o�˨ϥ�array
 * Required:
 *      enhacnement.js
 */
var Dijkstra = function( matrix, wall ) {
    this.wall = wall;
    this.field = matrix;
    this.x = matrix.length;
    this.y = matrix[0].length;
};

Dijkstra.prototype.search = function( fx, fy, tx, ty )  {
    // reset dist
    this.dist = Array.matrix( this.x, this.y, undefined );
    var result = [];
    var i = fx;
    var j = fy;
    var idx = 0;
    var idy = 0;
    var min = 9999;
    var cand = [];
    var candidate;
    
    this.dist[ i ][ j ] = 0;

    while( this.dist[ tx ][ ty ] === undefined ) {
        if( i > 0 && this.dist[ i-1 ][ j ] === undefined && this.field[ i-1 ][ j ] !== this.wall ) {
            this.dist[ i-1 ][ j ] = this.dist[ i ][ j ] + 1;
            cand.push( [i-1, j] );
        }
        if( j > 0 && this.dist[i][j-1] === undefined && this.field[ i ][ j-1 ] !== this.wall ) {
            this.dist[i][j-1] = this.dist[ i ][ j ] + 1;
            cand.push( [i, j-1] );
        }
        if( i < this.x-1 && this.dist[ i+1 ][ j ] === undefined && this.field[ i+1 ][ j ] !== this.wall ) {
            this.dist[ i+1 ][ j ] = this.dist[ i ][ j ] + 1;
            cand.push( [i+1, j] );
        }
        if( j < this.y-1 && this.dist[ i ][ j+1 ] === undefined && this.field[ i ][ j+1 ] !== this.wall ) {
            this.dist[ i ][ j+1 ] = this.dist[ i ][ j ] + 1;
            cand.push( [i, j+1] );
        }
        
        // ���U�@�ӡA�S��Ҥl�~��Ϊ���k 
        // => �]���������@�ˡA�ҥH�q���Y�}�l�⪺�O�̤p��
        // �_�h�n�ھ�dist�ƧǤ@�����̤p�������Ӻ�
        candidate = cand.shift();
        i = candidate[0];
        j = candidate[1];
    }
    
    // ��Ū���|
    result.push( [tx, ty] );
    i = tx;
    j = ty;
    while( i !== fx || j !== fy ) {
        if( i > 0 && 
            this.dist[ i-1 ][ j ] !== undefined && 
            this.dist[ i-1 ][ j ] === this.dist[i][j] - 1 ) {
            result.push( [i-1, j] );
            i = i-1;
        } else if( j > 0 && 
            this.dist[ i ][ j-1 ] !== undefined && 
            this.dist[ i ][ j-1 ] === this.dist[i][j] - 1 ) {
            result.push( [i, j-1] );
            j = j-1;
        } else if( i < this.x-1 && 
            this.dist[ i+1 ][ j ] !== undefined && 
            this.dist[ i+1 ][ j ] === this.dist[i][j] - 1 ) {
            result.push( [i+1, j] );
            i = i+1;
        } else if( j < this.y-1 && 
            this.dist[ i ][ j+1 ] !== undefined && 
            this.dist[ i ][ j+1 ] === this.dist[i][j] - 1 ) {
            result.push( [i, j+1] );
            j = j+1;
        }
    }
    return result;
};


