// User extensions can be added here.
//
// Keep this file to avoid  mystifying "Invalid Character" error in IE

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


/*
 (C) Copyright MetaCommunications, Inc. 2006.
     http://www.meta-comm.com
     http://engineering.meta-comm.com

Distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND.
*/

function map_list( list, for_func, if_func )
    {
    var mapped_list = [];
    for ( var i = 0; i < list.length; ++i )
        {
        var x = list[i];
        if( null == if_func || if_func( i, x ) ) 
            mapped_list.push( for_func( i, x ) );
        }
    return mapped_list;
    }

    
// Modified to initialize GoTo labels/cycles list
HtmlRunnerTestLoop.prototype.old_initialize = HtmlRunnerTestLoop.prototype.initialize

HtmlRunnerTestLoop.prototype.initialize = function(htmlTestCase, metrics, seleniumCommandFactory)
    {
    this.gotoLabels  = {};
    this.whileLabels = { ends: {}, whiles: {} };
    
    this.old_initialize(htmlTestCase, metrics, seleniumCommandFactory);
    
    this.initialiseLabels();
    }

HtmlRunnerTestLoop.prototype.initialiseLabels = function()
    {
    var command_rows = map_list( this.htmlTestCase.getCommandRows() 
                               , function(i, x) { 
                                    return x.getCommand()
                                    }
                               );

    var cycles = [];
    for( var i = 0; i < command_rows.length; ++i )
        {
        switch( command_rows[i].command.toLowerCase() )
            {
            case "label":
                this.gotoLabels[ command_rows[i].target ] = i;
                break;
            case "while":
            case "endwhile":
                cycles.push( [command_rows[i].command.toLowerCase(), i] )
                break;
            }
        }        
        
    var i = 0;
    while( cycles.length )
        {
        if( i >= cycles.length )
            throw new Error( "non-matching while/endWhile found" );
            
        switch( cycles[i][0] )
            {
            case "while":
                if(    ( i+1 < cycles.length ) 
                    && ( "endwhile" == cycles[i+1][0] )
                    )
                    {
                    // pair found
                    this.whileLabels.ends[ cycles[i+1][1] ] = cycles[i][1]
                    this.whileLabels.whiles[ cycles[i][1] ] = cycles[i+1][1]
                    
                    cycles.splice( i, 2 );
                    i = 0;
                    }
                else
                    ++i;
                break;
            case "endwhile":
                ++i;
                break;
            }
        }
                    
    }    

HtmlRunnerTestLoop.prototype.continueFromRow = function( row_num ) 
    {
    if(    row_num == undefined
        || row_num == null
        || row_num < 0
        )
        throw new Error( "Invalid row_num specified." );
        
    this.htmlTestCase.nextCommandRowIndex = row_num;
    }
    


// do nothing. simple label
Selenium.prototype.doLabel      = function(){};

Selenium.prototype.doGotolabel  = function( label ) {

    if( undefined == htmlTestRunner.currentTest.gotoLabels[label] ) 
        throw new Error( "Specified label '" + label + "' is not found." );
    
    htmlTestRunner.currentTest.continueFromRow( htmlTestRunner.currentTest.gotoLabels[ label ] );
    };
    
Selenium.prototype.doGoto = Selenium.prototype.doGotolabel;


Selenium.prototype.doGotoIf = function( condition, label ) {
    if( eval(condition) ) 
        this.doGotolabel( label );
    }


    
Selenium.prototype.doWhile = function( condition ) {
    if( !eval(condition) )
        {
        var last_row = htmlTestRunner.currentTest.htmlTestCase.nextCommandRowIndex - 1
        var end_while_row = htmlTestRunner.currentTest.whileLabels.whiles[ last_row ]
        if( undefined == end_while_row ) 
            throw new Error( "Corresponding 'endWhile' is not found." );
        
        htmlTestRunner.currentTest.continueFromRow( end_while_row + 1 );
        }
    }


Selenium.prototype.doEndWhile = function() {
    var last_row = htmlTestRunner.currentTest.htmlTestCase.nextCommandRowIndex - 1
    var while_row = htmlTestRunner.currentTest.whileLabels.ends[ last_row ]
    if( undefined == while_row ) 
        throw new Error( "Corresponding 'While' is not found." );
    
    htmlTestRunner.currentTest.continueFromRow( while_row );
    }
   

