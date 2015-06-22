SyntaxHighlighter.brushes.MathProg = function()
{
    var datatypes =	'integer binary symbolic ' ;
    
	var keywords =	'var set end param subject to subj solve check display printf ' +
                    'and diff if less or union by div data tr ' +
                    'in mod symdiff within cross else inter not then ' ;
					
	var functions =	'abs ceil floor exp log log10 max min round sqrt ' +
                    'trunc Irand224 Uniform01 Uniform Normal10 Normal ' +
                    'sum prod ' ;
                    
    var objective = 'maximize minimize ';

	this.regexList = [
		{ regex: /#(.*)$/gm,										css: 'comments' },			// one line comments
		{ regex: SyntaxHighlighter.regexLib.multiLineCComments,		css: 'comments' },			// multiline comments
		{ regex: SyntaxHighlighter.regexLib.doubleQuotedString,		css: 'string' },			// strings
		{ regex: SyntaxHighlighter.regexLib.singleQuotedString,		css: 'string' },			// strings
        { regex: new RegExp(this.getKeywords(datatypes), 'gm'),		css: 'color1 bold' },
        { regex: new RegExp(this.getKeywords(objective), 'gm'),     css: 'color2 bold' },
		{ regex: new RegExp(this.getKeywords(functions), 'gm'),		css: 'functions bold' },
		{ regex: new RegExp(this.getKeywords(keywords), 'gm'),		css: 'keyword bold' },
        { regex: /s.t./gm,		css: 'keyword bold' }
		];
};

SyntaxHighlighter.brushes.MathProg.prototype	= new SyntaxHighlighter.Highlighter();
SyntaxHighlighter.brushes.MathProg.aliases	= ['MathProg'];
