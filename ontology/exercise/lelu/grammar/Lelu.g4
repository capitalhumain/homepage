grammar Lelu;

prog: stat+ ;

stat
    : expr NEWLINE
    | NEWLINE
    ;

expr
    : importDeclaration     # importExpr
    | listCommand           # listExpr
    | saveCommand           # saveExpr
    | createTboxCommand     # createTboxExpr 
    ;

importDeclaration
    : 'import' qualifiedName 
    ;

listCommand
    : 'list'
    ;

saveCommand
    : 'save' 
    ;

createTboxCommand
    : Identifier ('is class'|'is subclass of' Identifier) 
    ;

qualifiedName
    :   Identifier ('.' Identifier)*
    ;


Identifier
    :   Letter (Letter)*
    ;

fragment
Letter
    :  '\u0024' |
       '\u0041'..'\u005a' |
       '\u005f' |
       '\u0061'..'\u007a' |
       '\u00c0'..'\u00d6' |
       '\u00d8'..'\u00f6' |
       '\u00f8'..'\u00ff' |
       '\u0100'..'\u1fff' |
       '\u3040'..'\u318f' |
       '\u3300'..'\u337f' |
       '\u3400'..'\u3d2d' |
       '\u4e00'..'\u9fff' |
       '\uf900'..'\ufaff'
    ;

Digit
    : [0-9]+
    ;

ID
    : [a-zA-z]+[a-zA-Z0-9]*
    ;

NEWLINE
    : '\r'? '\n' ;

WS
    : [ \t]+ -> skip ;
