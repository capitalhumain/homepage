/**
 * SyntaxHighlighter
 * http://alexgorbatchev.com/
 *
 * SyntaxHighlighter is donationware. If you are using it, please donate.
 * http://alexgorbatchev.com/wiki/SyntaxHighlighter:Donate
 *
 * @version
 * 2.1.364 (October 15 2009)
 * 
 * @copyright
 * Copyright (C) 2004-2009 Alex Gorbatchev.
 *
 * @license
 * This file is part of SyntaxHighlighter.
 * 
 * SyntaxHighlighter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * SyntaxHighlighter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SyntaxHighlighter.  If not, see <http://www.gnu.org/copyleft/lesser.html>.
 * 
 * This assemby language SyntaxHighlight is created by Terence Chao.
 * inseruction set ºCºC¼W¥[
 */
SyntaxHighlighter.brushes.Asm = function()
{
	var x86ins =	'MOV CMP ADD JE DB DD DW CLI STI HLT ' +
					'JMP ORG INT IN OUT PUSH PUSHA PUSHAD PUSHF PUSHFD ' +
					'POP POPA POPAD POPF POPFD LGDT SGDT LIDT SIDT ' +
					'mov cmp add je db dd dw cli sti hlt ' +
					'pop popa popad popf popfd lgdt sgdt lidt sidt ' +
					'jmp org int in out push pusha pushad pushf pushfd ';
    var registers = 'AX BX CX DX AH AL BH BL CH CL DH DL EAX EBX ECX EDX ' +
                    'BP EBP SI ESI DI EDI SP ESP CS SS DS ES FS GS EIP EFLAGS ' +
                    'CR0 CR1 CR2 CR3 ' + 
                    'ax bx cx dx ah al bh bl ch cl dh dl eax ebx ecx edx ' +
                    'bp ebp si esi di edi sp esp cs ss ds es fs gs eip eflags ' +
                    'cr0 cr1 cr2 cr3 ';

	this.regexList = [
		{ regex: /;(.*)$/gm,	css: 'comments' },		                                    // one line comments
		{ regex: SyntaxHighlighter.regexLib.doubleQuotedString,		css: 'string' },		// strings
		{ regex: SyntaxHighlighter.regexLib.singleQuotedString,		css: 'string' },		// strings
		{ regex: /\b([\d]+(\.[\d]+)?|0x[a-f0-9]+)\b/gi,				css: 'value' },			// numbers
		{ regex: new RegExp(this.getKeywords(registers), 'gm'),		css: 'color2' },        // registers
		{ regex: new RegExp(this.getKeywords(x86ins), 'gm'),		css: 'keyword' }		// x86 instruction
		];

	this.forHtmlScript({
		left	: /(&lt;|<)%[@!=]?/g, 
		right	: /%(&gt;|>)/g 
	});
};

SyntaxHighlighter.brushes.Asm.prototype	= new SyntaxHighlighter.Highlighter();
SyntaxHighlighter.brushes.Asm.aliases		= ['asm'];
