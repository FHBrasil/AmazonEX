//sideflow.js
var gotoLabels= {};
var whileLabels = {};

// overload the original Selenium reset function
Selenium.prototype.reset = function() {
    // reset the labels
    this.initialiseLabels();
    // proceed with original reset code
    this.defaultTimeout = Selenium.DEFAULT_TIMEOUT;
    this.browserbot.selectWindow("null");
    this.browserbot.resetPopups();
}

Selenium.prototype.initialiseLabels = function()
{
    gotoLabels = {};
    whileLabels = { ends: {}, whiles: {} };
    var command_rows = [];
    var numCommands = testCase.commands.length;
    for (var i = 0; i < numCommands; ++i) {
        var x = testCase.commands[i];
        command_rows.push(x);
    }
    var cycles = [];
    for( var i = 0; i < command_rows.length; i++ ) {
        if (command_rows[i].type == 'command')
        switch( command_rows[i].command.toLowerCase() ) {
            case "label":
                gotoLabels[ command_rows[i].target ] = i;
                break;
            case "while":
            case "endwhile":
                cycles.push( [command_rows[i].command.toLowerCase(), i] )
                break;
        }
    }
    var i = 0;
    while( cycles.length ) {
        if( i >= cycles.length ) {
            throw new Error( "non-matching while/endWhile found" );
        }
        switch( cycles[i][0] ) {
            case "while":
                if( ( i+1 < cycles.length ) && ( "endwhile" == cycles[i+1][0] ) ) {
                    // pair found
                    whileLabels.ends[ cycles[i+1][1] ] = cycles[i][1];
                    whileLabels.whiles[ cycles[i][1] ] = cycles[i+1][1];
                    cycles.splice( i, 2 );
                    i = 0;
                } else ++i;
                break;
            case "endwhile":
                ++i;
                break;
        }
    }
}

Selenium.prototype.continueFromRow = function( row_num )
{
    if(row_num == undefined || row_num == null || row_num < 0) {
        throw new Error( "Invalid row_num specified." );
    }
    testCase.debugContext.debugIndex = row_num;
}

// do nothing. simple label
Selenium.prototype.doLabel = function(){};

Selenium.prototype.doGotolabel = function( label )
{
    if( undefined == gotoLabels[label] ) {
        throw new Error( "Specified label '" + label + "' is not found." );
    }
    this.continueFromRow( gotoLabels[ label ] );
};

Selenium.prototype.doGoto = Selenium.prototype.doGotolabel;

Selenium.prototype.doGotoIf = function( condition, label )
{
    if( eval(condition) ) this.doGotolabel( label );
}

Selenium.prototype.doWhile = function( condition )
{
    if( !eval(condition) ) {
        var last_row = testCase.debugContext.debugIndex;
        var end_while_row = whileLabels.whiles[ last_row ];
        if( undefined == end_while_row ) throw new Error( "Corresponding 'endWhile' is not found." );
        this.continueFromRow( end_while_row );
    }
}

Selenium.prototype.doEndWhile = function()
{
    var last_row = testCase.debugContext.debugIndex;
    var while_row = whileLabels.ends[ last_row ] - 1;
    if( undefined == while_row ) throw new Error( "Corresponding 'While' is not found." );
    this.continueFromRow( while_row );
}

// checkXML
// JavaScript Document
Selenium.prototype.isTextPresentXML = function(pattern) {
  /**
   * Verifies that the specified text pattern appears somewhere in the entire XML document
   * (as opposed to text only within &lt;body/&gt; tags).
   * Note that when defining text patterns in your test case you should
   * escape HTML characters. For example: <td>&lt;userInRole&gt;</td>
   * (not <td><userInRole></td>).
   * @param pattern a <a href="#patterns">pattern</a> to match with the text of the document
   * @return boolean true if the pattern matches the text, false otherwise
   */

    // the only code that differs from the stock Selenium isTextPresent
    //	is the code to retrieve allText...
    // IE
    var allText = this.browserbot.getDocument().xml;
    if (allText == null) {
    	// Firefox
    	allText = new XMLSerializer().serializeToString(this.browserbot.getDocument());
    }

    var patternMatcher = new PatternMatcher(pattern);
    if (patternMatcher.strategy == PatternMatcher.strategies.glob) {
            if (pattern.indexOf("glob:")==0) {
                    pattern = pattern.substring("glob:".length); // strip off "glob:"
                }
        patternMatcher.matcher = new PatternMatcher.strategies.globContains(pattern);
    }
    else if (patternMatcher.strategy == PatternMatcher.strategies.exact) {
                pattern = pattern.substring("exact:".length); // strip off "exact:"
        return allText.indexOf(pattern) != -1;
    }
    return patternMatcher.matches(allText);
};
//globalStore
/**
 * storeValue, storeText, storeAttribute and store actions now 
 * have 'global' equivalents.
 * Use storeValueGlobal, storeTextGlobal, storeAttributeGlobal or storeGlobal
 * will store the variable globally, making it available it subsequent tests.
 *
 * See the Reference.html for storeValue, storeText, storeAttribute and store
 * for the arguments you should send to the new Global functions.
 *
 * example of use
 * in testA.html:
 * +------------------+----------------------+----------------------+
 * |storeGlobal       | http://localhost/    | baseURL              |
 * +------------------+----------------------+----------------------+
 * 
 * in textB.html (executed after testA.html):
 * +------------------+-----------------------+--+
 * |open              | ${baseURL}Main.jsp    |  |
 * +------------------+-----------------------+--+
 *
 * Note: Selenium.prototype.replaceVariables from selenium-api.js has been replaced
 *       here to make it use global variables if no local variable is found.
 *       This might cause issues if you upgraded Selenium in the future and this function 
 *       has been changed.
 *
 * @author Guillaume Boudreau
 */
 
globalStoredVars = new Object();

/*
 * Globally store the value of a form input in a variable
 */
Selenium.prototype.doStoreValueGlobal = function(target, varName) {
    if (!varName) {
        // Backward compatibility mode: read the ENTIRE text of the page
        // and stores it in a variable with the name of the target
        value = this.page().bodyText();
        globalStoredVars[target] = value;
        return;
    }
    var element = this.page().findElement(target);
    globalStoredVars[varName] = getInputValue(element);
};

/*
 * Globally store the text of an element in a variable
 */
Selenium.prototype.doStoreTextGlobal = function(target, varName) {
    var element = this.page().findElement(target);
    globalStoredVars[varName] = getText(element);
};

/*
 * Globally store the value of an element attribute in a variable
 */
Selenium.prototype.doStoreAttributeGlobal = function(target, varName) {
    globalStoredVars[varName] = this.page().findAttribute(target);
};

/*
 * Globally store the result of a literal value
 */
Selenium.prototype.doStoreGlobal = function(value, varName) {
    globalStoredVars[varName] = value;
};

/*
 * Search through str and replace all variable references ${varName} with their
 * value in storedVars (or globalStoredVars).
 */
Selenium.prototype.replaceVariables = function(str) {
    var stringResult = str;

    // Find all of the matching variable references
    var match = stringResult.match(/\$\{\w+\}/g);
    if (!match) {
        return stringResult;
    }

    // For each match, lookup the variable value, and replace if found
    for (var i = 0; match && i < match.length; i++) {
        var variable = match[i]; // The replacement variable, with ${}
        var name = variable.substring(2, variable.length - 1); // The replacement variable without ${}
        var replacement = storedVars[name];
        if (replacement != undefined) {
            stringResult = stringResult.replace(variable, replacement);
        }
        var replacement = globalStoredVars[name];
        if (replacement != undefined) {
            stringResult = stringResult.replace(variable, replacement);
        }
    }
    return stringResult;
};

Selenium.prototype.doEncodePW = function( str_in, varName )
{
	if (str_in == null || str_in == "") {
  	throw new SeleniumError("You must specify an input string (Target)");
  }
	if (varName == null || varName == "") {
  	throw new SeleniumError("You must specify a variable name to store encoded password in (Value)");
  }
	num_out = "";
	str_in = escape(str_in);
	for(i = 0; i < str_in.length; i++) 
	{
		num_out += str_in.charCodeAt(i) - 23;
	}
	storedVars[varName] = num_out;
}

Selenium.prototype.doDecodePW = function( str_in, varName )
{
	if (str_in == null || str_in == "") {
  	throw new SeleniumError("You must specify an input string (Target)");
  }
	if (varName == null || varName == "") {
  	throw new SeleniumError("You must specify a variable name to store decoded password in (Value)");
  }
  str_out = "";
  for(i = 0; i < str_in.length; i += 2) 
  {
		num_in = parseInt(str_in.substr(i,[2])) + 23;
		num_in = unescape('%' + num_in.toString(16));
	str_out += num_in;
	}
	storedVars[varName] = str_out;
}
