SyntaxHighlighter.brushes.Ada = function()
{
	var funcs	=	'Put New_Line Put_Line ';

	var keywords =	'abort deley if overriding separate abs delta in package subtype ' +
					'abstract digits interface pragma synchronized accept do is private tagged ' +
					'aliased else limited procedure task all elsif loop protected terminate ' +
					'and end mod raise then array entry new range type ' +
					'at exception new record until begin exit null renames use ' +
					'body for of requeue when case function of return while ' +
					'constant generic others reverse with declare goto out select xor ';
                    
    var types = 'INTEGER NATURAL POSITIVE BOOLEAN ';

	this.regexList = [
		{ regex: /--(.*)$/gm,												css: 'comments' },			// one line and multiline comments
		{ regex: SyntaxHighlighter.regexLib.multiLineDoubleQuotedString,	css: 'string' },			// double quoted strings
		{ regex: SyntaxHighlighter.regexLib.multiLineSingleQuotedString,	css: 'string' },			// single quoted strings
		{ regex: new RegExp(this.getKeywords(types), 'gmi'),				css: 'color2' },			// standard types
        { regex: new RegExp(this.getKeywords(funcs), 'gmi'),				css: 'color1' },			// common functions & procedures
		{ regex: new RegExp(this.getKeywords(keywords), 'gmi'),				css: 'keyword' }			// keyword
		];
};

SyntaxHighlighter.brushes.Ada.prototype	= new SyntaxHighlighter.Highlighter();
SyntaxHighlighter.brushes.Ada.aliases	= ['ada'];

