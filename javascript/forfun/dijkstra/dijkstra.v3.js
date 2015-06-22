/*
 * v3
 * �s���������@�˪���
 * �ݭn�Ƨ�
 * Required:
 *      enhacnement.js
 */
var Dijkstra = function( matrix, wall ) {
    this.wall = wall;
    this.field = matrix;
    this.x = matrix.length;
    this.y = matrix[0].length;
    this.dist = Array.matrix( this.x, this.y, undefined );
};

// �P�_���I�O�_�X�k
Dijkstra.prototype.isLegal = function( x, y ) {
    if( this.field[ x ] === undefined || this.field[ x ][ y ] === undefined ) {
        return false;
    }
    if( this.field[ x ][ y ] === this.wall ) {
        return false;
    }
    return true;
};

Dijkstra.prototype.reset = function() {
    this.dist = Array.matrix( this.x, this.y, undefined );
};

Dijkstra.prototype.search = function( fx, fy, tx, ty )  {
    // �O�@��
    if( !this.isLegal( fx, fy ) ) {
        throw { name: 'DataException', message: 'Start Node is illegal' };
    }
    if( !this.isLegal( tx, ty ) ) {
        throw { name: 'DataException', message: 'Target Node is illegal' };
    }

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

    // �p��C�@��node������
    // ������ت��a���I��������X�Ӭ��� => ������open list
    while( this.dist[ tx ][ ty ] === undefined ) {
        if( i > 0 && this.dist[ i-1 ][ j ] === undefined && this.field[ i-1 ][ j ] !== this.wall ) {
            this.dist[ i-1 ][ j ] = this.dist[ i ][ j ] + this.field[ i-1 ][ j ];
            cand.push( [i-1, j, this.dist[ i-1 ][ j ]] );
        }
        if( j > 0 && this.dist[i][j-1] === undefined && this.field[ i ][ j-1 ] !== this.wall ) {
            this.dist[i][j-1] = this.dist[ i ][ j ] + this.field[ i ][ j-1 ];
            cand.push( [i, j-1, this.dist[i][j-1]] );
        }
        if( i < this.x-1 && this.dist[ i+1 ][ j ] === undefined && this.field[ i+1 ][ j ] !== this.wall ) {
            this.dist[ i+1 ][ j ] = this.dist[ i ][ j ] + this.field[ i+1 ][ j ];
            cand.push( [i+1, j, this.dist[ i+1 ][ j ]] );
        }
        if( j < this.y-1 && this.dist[ i ][ j+1 ] === undefined && this.field[ i ][ j+1 ] !== this.wall ) {
            this.dist[ i ][ j+1 ] = this.dist[ i ][ j ] + this.field[ i ][ j+1 ];
            cand.push( [i, j+1, this.dist[ i ][ j+1 ]] );
        }
        
        // ���Ӧ����Ƨ�
        cand.sort( function(a, b) {
            if( a[2] === b[2] ) {
                return 0;
            }
            return a[2]<b[2]?-1:1;
        } );
        
        // ���o�̤p������node
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
            this.dist[ i-1 ][ j ] === this.dist[i][j] - this.field[ i ][ j ] ) {
            result.push( [i-1, j] );
            i = i-1;
        } else if( j > 0 && 
            this.dist[ i ][ j-1 ] !== undefined && 
            this.dist[ i ][ j-1 ] === this.dist[i][j] - this.field[ i ][ j ] ) {
            result.push( [i, j-1] );
            j = j-1;
        } else if( i < this.x-1 && 
            this.dist[ i+1 ][ j ] !== undefined && 
            this.dist[ i+1 ][ j ] === this.dist[i][j] - this.field[ i ][ j ] ) {
            result.push( [i+1, j] );
            i = i+1;
        } else if( j < this.y-1 && 
            this.dist[ i ][ j+1 ] !== undefined && 
            this.dist[ i ][ j+1 ] === this.dist[i][j] - this.field[ i ][ j ] ) {
            result.push( [i, j+1] );
            j = j+1;
        }
    }
    return result;
};


