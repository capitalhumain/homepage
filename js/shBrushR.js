SyntaxHighlighter.brushes.R = function()
{
	var keywords =	'require library print matrix array list seq length NULL typeof ' +
	                'break while repeat for ' +
	                'else if ' + 
                    'class function ' ;

	this.regexList = [
		{ regex: /#(.*)$/gm,										        css: 'comments2' },  // one line comments
		{ regex: SyntaxHighlighter.regexLib.multiLineDoubleQuotedString,    css: 'string' },	// double-quoted string
		{ regex: SyntaxHighlighter.regexLib.singleQuotedString,				css: 'string' },	// strings
		{ regex: /0x[a-f0-9]+|\d+(\.\d+)?/gi,								css: 'value' },		// numbers
		{ regex: new RegExp(this.getKeywords(keywords), 'gm'),				css: 'keyword' }	// keywords
		];
}

SyntaxHighlighter.brushes.R.prototype	= new SyntaxHighlighter.Highlighter();
SyntaxHighlighter.brushes.R.aliases		= ['R'];
