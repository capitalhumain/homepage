/*
 * Description:
 *    Smith-Waterman Algorithm in JavaScript
 *    �u�O��@���Ҽ{ scoring matrix �M gap penality �]�w�o�Ӱ��D
 *    �ҥH�ܦh�F�賣�w�bglobal scope |-) 
 * Reference:
 *    http://en.wikipedia.org/wiki/Smith-Waterman_algorithm
 * Required: 
 *    enhancement.js
 * Author:
 *    Terence Chao, 2009
 */

var s = { 'AA': 2,
      'AG': -1,
      'AC': -1,
      'AT': -1,
      'GA': -1,
      'GG':  2,
      'GC': -1,
      'GT': -1,
      'CA': -1,
      'CG': -1,
      'CC':  2,
      'CT': -1,
      'TA': -1,
      'TG': -1,
      'TC': -1,
      'TT':  2 };

var gap = -1;

var smith = function( sequence, reference ) {
    var rows = reference.length + 1;
    var cols = sequence.length + 1;
    var a = Array.matrix( rows, cols, 0 );
    var i = 0, j = 0;
    var choice = [ 0, 0, 0, 0 ];
    var ref = '';
    var seq = '';
    var score, score_diag, score_up, scroe_left;
    
    // �p�� F Matrix
    for( i=1; i<rows; i++ ) {
        for( j=1; j<cols; j++ ) {
            choice[0] = 0;
            choice[1] = a[i-1][j-1] + s[ (reference.charAt(i-1) + sequence.charAt(j-1)).toUpperCase() ];
            choice[2] = a[i-1][j] + gap;
            choice[3] = a[i][j-1] + gap;
            a[i][j] = choice.max();
        }
    }

    // ��Ū
    i = reference.length;
    j = sequence.length;
    while( i>0 && j>0 ) {
        score = a[i][j];
        score_diag = a[i-1][j-1];
        score_up = a[i][j-1];
        score_left = a[i-1][j];
        if( score === (score_diag + s[ (reference.charAt(i-1) + sequence.charAt(j-1)).toUpperCase() ]) ) {
            ref = reference[i-1] + ref;
            seq = sequence[j-1] + seq;
            i -= 1;
            j -= 1;
        } else if( score === (score_left + gap) ) {
            ref = reference[i-1] + ref;
            seq = '-' + seq;
            i -= 1;
        } else if( score === (score_up + gap) ) {
            ref = '-' + ref;
            seq = sequence[j-1] + seq;
            j -= 1;
        }
    }
    
    while( i>0 ) {
        ref = reference[i-1] + ref;
        seq = '-' + seq;
        i -= 1;
    }
    
    while( j>0 ) {
        ref = '-' + ref;
        seq = sequence[j-1] + seq;
        j -= 1;
    }
    
    return [seq, ref];
};
